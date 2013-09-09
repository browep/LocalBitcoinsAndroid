package com.github.browep.localbtc;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.github.browep.localbtc.util.HttpUtil;

import java.util.Map;

public class GsonRequest<T> extends Request<T> {

    private static String TAG = GsonRequest.class.getCanonicalName();

    private ApiAdapter mApiAdapter;

    private Class mResponseObjectClass;

    private Response.Listener<T> mListener;

    private Map<String, String> params;

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

    public GsonRequest(ApiAdapter apiAdapter,
            Class responseObjectClass,
            int method,
            String url,
            Map<String, String> params,
            Response.Listener<T> listener,
            Response.ErrorListener errorListener) {
        this(apiAdapter, responseObjectClass, method, url, listener, errorListener);
        this.params = params;
    }

    @Override
    public String getUrl() {
        if (getMethod() == Method.GET && params != null && !params.isEmpty()) {
            return super.getUrl() + "?" + HttpUtil.encodeParameters(params, "UTF-8");
        } else {
            return super.getUrl();
        }
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

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
