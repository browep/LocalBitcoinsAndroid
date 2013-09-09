package com.github.browep.localbtc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import com.android.volley.NetworkResponse;

import android.util.Log;

public class ApiAdapter {

    private static String TAG = ApiAdapter.class.getCanonicalName();

    private final Gson mGson;

    public ApiAdapter() {
        mGson = new GsonBuilder().create();
    }

    public Object parseNetworkResponse(Class aClass, NetworkResponse networkResponse) {
        String json = new String(networkResponse.data);
        try {
            return mGson.fromJson(json, aClass );
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "error with: " + json, e);
            throw new RuntimeException(e);
        }
    }
}
