package com.github.browep.localbtc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class BaseActivity extends ActionBarActivity {

    private static String TAG = BaseActivity.class.getCanonicalName();

    private Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = new Api(this);
    }

    public Api getApi() {
        return mApi;
    }
}
