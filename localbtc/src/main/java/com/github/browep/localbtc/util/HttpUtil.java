package com.github.browep.localbtc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {

    private static String TAG = HttpUtil.class.getCanonicalName();

    public static String encodeParameters(Map<String, String> params, String paramsEncoding) {
           StringBuilder encodedParams = new StringBuilder();
           try {
               for (Map.Entry<String, String> entry : params.entrySet()) {
                   encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                   encodedParams.append('=');
                   encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                   encodedParams.append('&');
               }
               return encodedParams.toString();
           } catch (UnsupportedEncodingException uee) {
               throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
           }
       }
}
