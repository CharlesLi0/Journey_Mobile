package com.example.journeyMobile.service.util;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class UrlCreatorUtil {


    public static String getDirectionsUrl(LatLng start, LatLng dest, String apiKey) {
        if (start == null || dest == null) return null;
        // Origin of route
        String str_origin = "origin=" + start.latitude + "," + start.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        String key = "key=" + apiKey;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode + "&" + key;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        Log.d("CharlesUrl", url);
        return url;
    }
}
