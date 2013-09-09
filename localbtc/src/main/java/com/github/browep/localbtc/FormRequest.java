package com.github.browep.localbtc;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class FormRequest extends StringRequest {

    private static String TAG = FormRequest.class.getCanonicalName();

    private Map<String, String> mParams;

    public FormRequest(int method, String url, Map<String,String> params, Response.Listener<String> listener,
            Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
