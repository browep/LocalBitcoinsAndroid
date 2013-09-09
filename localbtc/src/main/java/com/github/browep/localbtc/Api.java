package com.github.browep.localbtc;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Api {

    private static String TAG = Api.class.getCanonicalName();

    private static String HOST = "localbitcoins.com";

    private static String PROTOCOL = "https://";

    private static String API_PATH_PREFIX = "/api";

    private Context mContext;

    private static RequestQueue mRequestQueue;

    public Api(Context context) {
        mContext = context;
        synchronized (Api.class) {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(mContext);
            }
        }
    }

    public void getLocationId(final double lat, final double lon, Response.Listener<String> successListener,
            Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("lat", String.valueOf(lat));
        params.put("lon", String.valueOf(lon));

        FormRequest postRequest = new FormRequest(Request.Method.POST, getUrl("/places/"), params, successListener,
                errorListener);
        mRequestQueue.add(postRequest);
    }

    private static String getUrl(String pathSuffix) {
        return PROTOCOL + HOST + API_PATH_PREFIX + pathSuffix;
    }

    public static class LoggingErrorListener implements Response.ErrorListener {

        String tag;

        public LoggingErrorListener(String tag) {
            this.tag = tag;
        }

        public void onErrorResponse(VolleyError error) {

            if (error.networkResponse != null) {
                String networkResponseStr = error.networkResponse.statusCode + ": " + new String(
                        error.networkResponse.data);
                Log.e(tag, networkResponseStr, error);
            } else {
                Log.e(tag, error.getMessage(), error);
            }
        }
    }

}
