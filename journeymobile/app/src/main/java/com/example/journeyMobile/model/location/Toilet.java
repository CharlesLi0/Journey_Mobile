package com.example.journeyMobile.model.location;

import com.google.android.gms.maps.model.LatLng;

public class Toilet extends Spot {
    private String[] toiletType;


    public Toilet(String name,LatLng latLng) {
        super(name,latLng);
    }

}
