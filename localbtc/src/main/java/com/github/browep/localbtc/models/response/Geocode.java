package com.github.browep.localbtc.models.response;

import java.util.List;

public class Geocode {

    private static String TAG = Geocode.class.getCanonicalName();

    private List<Result> results;

    public double getLatitude() {
        try {
            return results.get(0).geometry.location.lat;
        } catch (Exception e) {
            return 0;
        }
    }

    public double getLongitude() {
        try {
            return results.get(0).geometry.location.lng;
        } catch (Exception e) {
            return 0;
        }
    }

    public static class Result {
        Geometry geometry;

        public static class Geometry {
            Location location;

            public static class Location {
                private double lat;
                private double lng;
            }
        }
    }
}
