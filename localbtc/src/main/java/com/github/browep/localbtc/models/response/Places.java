package com.github.browep.localbtc.models.response;

import java.util.List;

public class Places {

    private static String TAG = Places.class.getCanonicalName();

    private List<Place> places;

    public List<Place> getList() {
        return places;
    }

    public static class Place {
        private String sell_local_url;
        private String location_string;
        private String url;
        private double lon;
        private double lat;
        private String buy_local_url;

        public String getBuyLocalUrl() {
            return buy_local_url;
        }

        public double getLat() {
            return lat;
        }

        public String getLocationString() {
            return location_string;
        }

        public double getLon() {
            return lon;
        }

        public String getSellLocalUrl() {
            return sell_local_url;
        }

        public String getUrl() {
            return url;
        }
    }
}
