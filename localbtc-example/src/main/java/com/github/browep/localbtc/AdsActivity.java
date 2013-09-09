package com.github.browep.localbtc;

import com.android.volley.Response;
import com.github.browep.localbtc.models.response.Places;

import android.os.Bundle;
import android.util.Log;

public class AdsActivity extends BaseActivity {

    public static final String TAG = AdsActivity.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_activity);

        getApi().getLocationId(40.0176, -105.2797, new Response.Listener<Places>() {
                    public void onResponse(Places response) {
                        Log.d(TAG, response.toString());
                    }
                }
                , new Api.LoggingErrorListener(TAG)
        );
    }

}

