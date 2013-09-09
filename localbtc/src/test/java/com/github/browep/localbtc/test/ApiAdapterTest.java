package com.github.browep.localbtc.test;

import com.android.volley.NetworkResponse;
import com.github.browep.localbtc.ApiAdapter;
import com.github.browep.localbtc.models.response.Places;
import com.github.browep.localbtc.util.IOUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Application;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/test/TestAndroidManifest.xml")
public class ApiAdapterTest {

    private static String TAG = ApiAdapterTest.class.getCanonicalName();

    private Application mApplication;

    private ApiAdapter mApiAdapter;

    @Before
    public void setUp() throws Exception {
        mApplication = Robolectric.application;
        mApiAdapter = new ApiAdapter();
    }

    @Test
    public void testPlaces() throws Exception {
        String json = getJson("mock-responses/places.json");
        Places places = (Places) mApiAdapter.parseNetworkResponse(Places.class, new NetworkResponse(json.getBytes()));
        assertNotNull(places);
        assertEquals(1, places.getList().size());
        Places.Place place = places.getList().get(0);
        assertEquals("80302, US", place.getLocationString());

    }

    private String getJson(String fileName) throws IOException {
        InputStream inputStream = mApplication.getAssets().open(fileName);
        return IOUtil.slurpInputStream(inputStream);
    }
}
