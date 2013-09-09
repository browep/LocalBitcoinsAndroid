package com.github.browep.localbtc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import com.android.volley.NetworkResponse;

import android.util.Log;

import java.lang.reflect.Field;

public class ApiAdapter {

    private static String TAG = ApiAdapter.class.getCanonicalName();

    private final Gson mGson;

    private final JsonParser mJsonParser;

    public ApiAdapter() {
        mGson = new GsonBuilder().create();
        mJsonParser = new JsonParser();
    }

    public Object parseNetworkResponse(Class aClass, NetworkResponse networkResponse) {
        String jsonStr = new String(networkResponse.data);
        try {
            JsonElement jsonElement = mJsonParser.parse(jsonStr);
            if (jsonElement instanceof JsonObject && jsonElement.getAsJsonObject().has("data")) {
                jsonElement = jsonElement.getAsJsonObject().get("data");
                try {
                    Field jsonSubItemField = aClass.getField("JSON_SUB_ITEM");
                    String jsonSubItemString = (String) jsonSubItemField.get(String.class);
                    if (jsonElement instanceof JsonObject && jsonElement.getAsJsonObject().has(jsonSubItemString)) {
                        jsonElement = jsonElement.getAsJsonObject().get(jsonSubItemString);
                    }
                } catch (NoSuchFieldException e) {

                } catch (IllegalAccessException e) {

                }

            }
            return mGson.fromJson(jsonElement, aClass);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "error with: " + jsonStr, e);
            throw new RuntimeException(e);
        }
    }
}
