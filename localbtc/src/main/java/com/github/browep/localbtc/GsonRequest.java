package com.github.browep.localbtc;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

public class GsonRequest<T> extends Request<T> {

    private static String TAG = GsonRequest.class.getCanonicalName();

    private ApiAdapter mApiAdapter;

    private Class mResponseObjectClass;

    private Response.Listener<T> mListener;

    public GsonRequest(ApiAdapter apiAdapter,
            Class responseObjectClass,
            int method,
            String url,
            Response.Listener<T> listener,
            Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mApiAdapter = apiAdapter;
        mResponseObjectClass = responseObjectClass;

        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return (Response<T>) Response.success(mApiAdapter.parseNetworkResponse(mResponseObjectClass, response),
                        HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
