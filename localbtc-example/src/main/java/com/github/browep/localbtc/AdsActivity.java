package com.github.browep.localbtc;

import com.android.volley.Response;
import com.github.browep.localbtc.models.response.LocalBuyAds;
import com.github.browep.localbtc.models.response.Places;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        mProgressDialog.show();

        getApi().getLocationId(40.0176, -105.2797, new Response.Listener<Places>() {
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
            if(viewGroup == null) {
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

}

