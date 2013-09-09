package com.github.browep.localbtc;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

public class FormRequest<T> extends Request<T> {

    private static String TAG = FormRequest.class.getCanonicalName();

    private final Response.Listener listener;

    private Class mResponseObjectClass;

    private Map<String, String> mParams;

    private ApiAdapter mApiAdapter;

    public FormRequest(ApiAdapter apiAdapter,
            Class aClass,
            int method,
            String url,
            Map<String, String> params,
            Response.Listener<T> listener,
            Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mApiAdapter = apiAdapter;
        mResponseObjectClass = aClass;
        mParams = params;
        this.listener = listener;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return (Response<T>) Response.success(mApiAdapter.parseNetworkResponse(mResponseObjectClass, response),
                HttpHeaderParser.parseCacheHeaders(response));

    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

}
