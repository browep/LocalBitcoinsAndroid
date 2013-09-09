package com.github.browep.localbtc;

import com.android.volley.Response;
import com.github.browep.localbtc.models.response.Geocode;
import com.github.browep.localbtc.models.response.LocalBuyAds;
import com.github.browep.localbtc.models.response.Places;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdsActivity extends BaseActivity {

    public static final String TAG = AdsActivity.class.getCanonicalName();

    private ProgressDialog mProgressDialog;

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_activity);

        getSupportActionBar().setTitle(getString(R.string.buy_locally));

        mListView = (ListView) findViewById(R.id.listview);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (location != null) {
            getAds(location.getLatitude(), location.getLongitude());
        } else {
            promptLocation();
        }
    }

    private void promptLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.location_prompt));

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                final String locationStr = input.getText().toString();
                Log.d(TAG, locationStr);
                getApi().geocode(locationStr, new Response.Listener<Geocode>() {
                    public void onResponse(Geocode response) {
                        if (response.getLatitude() != 0) {
                            getAds(response.getLatitude(), response.getLongitude());
                        } else {
                            new AlertDialog.Builder(AdsActivity.this)
                                    .setMessage(MessageFormat.format(getString(R.string.location_error_promp),locationStr))
                                    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            promptLocation();
                                        }
                                    }).create().show();
                        }
                    }
                }, new Api.LoggingDismissingErrorListener(TAG, mProgressDialog));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void getAds(double latitude, double longitude) {
        mProgressDialog.show();
        getApi().getLocationId(latitude, longitude, new Response.Listener<Places>() {
            public void onResponse(Places response) {
                Log.d(TAG, response.toString());
                onFoundPlace(response.get(0));
            }
        }
                , new Api.LoggingErrorListener(TAG)
        );
    }

    public void onFoundPlace(Places.Place place) {
        getApi().getLocalBuyAds(place, new Response.Listener<LocalBuyAds>() {
            public void onResponse(final LocalBuyAds buyAds) {

                // sort
                Collections.sort(buyAds, new Comparator<LocalBuyAds.LocalBuyAd>() {
                    public int compare(LocalBuyAds.LocalBuyAd lhs, LocalBuyAds.LocalBuyAd rhs) {
                        return lhs.getData().getTemp_price_usd().compareTo(rhs.getData().getTemp_price());
                    }
                });
                mListView.setAdapter(new AdsListAdapter(buyAds));

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(buyAds.get(i).getActions().getPublic_view()));
                        startActivity(intent);
                    }
                });

                mProgressDialog.dismiss();
            }
        }, new Api.LoggingDismissingErrorListener(TAG, mProgressDialog));

    }

    private class AdsListAdapter extends BaseAdapter {

        private List<LocalBuyAds.LocalBuyAd> mLocalBuyAds;

        private AdsListAdapter(List<LocalBuyAds.LocalBuyAd> localBuyAds) {
            mLocalBuyAds = localBuyAds;
        }

        public int getCount() {
            return mLocalBuyAds.size();
        }

        public Object getItem(int i) {
            return mLocalBuyAds.get(i);
        }

        public long getItemId(int i) {
            return i;
        }

        public View getView(int i, View viewGroup, ViewGroup listview) {

            ViewHolder viewHolder;
            if (viewGroup == null) {
                viewGroup = getLayoutInflater().inflate(R.layout.ads_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) viewGroup.findViewById(R.id.name);
                viewHolder.price = (TextView) viewGroup.findViewById(R.id.price);
                viewHolder.distance = (TextView) viewGroup.findViewById(R.id.distance);

                viewGroup.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) viewGroup.getTag();
            }

            LocalBuyAds.LocalBuyAd buyAd = (LocalBuyAds.LocalBuyAd) getItem(i);
            viewHolder.name.setText(buyAd.getData().getProfile().getName());
            viewHolder.price.setText(buyAd.getData().getTemp_price_usd());
            viewHolder.distance.setText(buyAd.getData().getDistance().toString() + " miles");

            return viewGroup;

        }

        private class ViewHolder {

            TextView price;

            TextView name;

            TextView distance;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ads_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.location) {
            promptLocation();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

