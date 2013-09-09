package com.github.browep.localbtc;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

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
