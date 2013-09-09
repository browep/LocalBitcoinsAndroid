package com.github.browep.localbtc.models.response;


import java.util.LinkedList;

public class LocalBuyAds extends LinkedList<LocalBuyAds.LocalBuyAd> {

    public static final String JSON_SUB_ITEM = "ad_list";

    private static String TAG = LocalBuyAds.class.getCanonicalName();

    public static class LocalBuyAd {

        private Actions actions;

        private Data data;

        public Actions getActions() {
            return this.actions;
        }

        public void setActions(Actions actions) {
            this.actions = actions;
        }

        public Data getData() {
            return this.data;
        }

        public void setData(Data data) {
            this.data = data;
        }


        public static class Actions {

            private String public_view;

            public String getPublic_view() {
                return this.public_view;
            }

            public void setPublic_view(String public_view) {
                this.public_view = public_view;
            }
        }


        public static class Profile {

            private Number id;

            private String name;

            private String username;

            public Number getId() {
                return this.id;
            }

            public void setId(Number id) {
                this.id = id;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUsername() {
                return this.username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class Data {

            private Number ad_id;

            private String age_days_coefficient_limit;

            private String city;

            private String countrycode;

            private String created_at;

            private String currency;

            private Number distance;

            private String email;

            private String first_time_limit_btc;

            private Number lat;

            private String location_string;

            private Number lon;

            private String max_amount;

            private String min_amount;

            private String online_provider;

            private Profile profile;

            private String reference_type;

            private boolean sms_verification_required;

            private String temp_price;

            private String temp_price_usd;

            private String trade_type;

            private boolean visible;

            private String volume_coefficient_btc;

            public Number getAd_id() {
                return this.ad_id;
            }

            public void setAd_id(Number ad_id) {
                this.ad_id = ad_id;
            }

            public String getAge_days_coefficient_limit() {
                return this.age_days_coefficient_limit;
            }

            public void setAge_days_coefficient_limit(String age_days_coefficient_limit) {
                this.age_days_coefficient_limit = age_days_coefficient_limit;
            }

            public String getCity() {
                return this.city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountrycode() {
                return this.countrycode;
            }

            public void setCountrycode(String countrycode) {
                this.countrycode = countrycode;
            }

            public String getCreated_at() {
                return this.created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getCurrency() {
                return this.currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public Number getDistance() {
                return this.distance;
            }

            public void setDistance(Number distance) {
                this.distance = distance;
            }

            public String getEmail() {
                return this.email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFirst_time_limit_btc() {
                return this.first_time_limit_btc;
            }

            public void setFirst_time_limit_btc(String first_time_limit_btc) {
                this.first_time_limit_btc = first_time_limit_btc;
            }

            public Number getLat() {
                return this.lat;
            }

            public void setLat(Number lat) {
                this.lat = lat;
            }

            public String getLocation_string() {
                return this.location_string;
            }

            public void setLocation_string(String location_string) {
                this.location_string = location_string;
            }

            public Number getLon() {
                return this.lon;
            }

            public void setLon(Number lon) {
                this.lon = lon;
            }

            public String getMax_amount() {
                return this.max_amount;
            }

            public void setMax_amount(String max_amount) {
                this.max_amount = max_amount;
            }

            public String getMin_amount() {
                return this.min_amount;
            }

            public void setMin_amount(String min_amount) {
                this.min_amount = min_amount;
            }

            public String getOnline_provider() {
                return this.online_provider;
            }

            public void setOnline_provider(String online_provider) {
                this.online_provider = online_provider;
            }

            public Profile getProfile() {
                return this.profile;
            }

            public void setProfile(Profile profile) {
                this.profile = profile;
            }

            public String getReference_type() {
                return this.reference_type;
            }

            public void setReference_type(String reference_type) {
                this.reference_type = reference_type;
            }

            public boolean getSms_verification_required() {
                return this.sms_verification_required;
            }

            public void setSms_verification_required(boolean sms_verification_required) {
                this.sms_verification_required = sms_verification_required;
            }

            public String getTemp_price() {
                return this.temp_price;
            }

            public void setTemp_price(String temp_price) {
                this.temp_price = temp_price;
            }

            public String getTemp_price_usd() {
                return this.temp_price_usd;
            }

            public void setTemp_price_usd(String temp_price_usd) {
                this.temp_price_usd = temp_price_usd;
            }

            public String getTrade_type() {
                return this.trade_type;
            }

            public void setTrade_type(String trade_type) {
                this.trade_type = trade_type;
            }

            public boolean getVisible() {
                return this.visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }

            public String getVolume_coefficient_btc() {
                return this.volume_coefficient_btc;
            }

            public void setVolume_coefficient_btc(String volume_coefficient_btc) {
                this.volume_coefficient_btc = volume_coefficient_btc;
            }
        }
    }
}
