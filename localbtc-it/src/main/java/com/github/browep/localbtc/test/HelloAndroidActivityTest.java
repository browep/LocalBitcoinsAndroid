package com.github.browep.localbtc.test;

import com.github.browep.localbtc.AdsActivity;

import android.test.ActivityInstrumentationTestCase2;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<AdsActivity> {

    public HelloAndroidActivityTest() {
        super(AdsActivity.class);
    }

    public void testActivity() {
        AdsActivity activity = getActivity();
        assertNotNull(activity);
    }
}

