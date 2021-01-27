package com.example.journeyMobile.model.location;

import com.google.android.gms.maps.model.LatLng;

public class Service extends Spot {
    private ServiceType serviceType;
    private String title;
    private String description;
    private String website;

    public Service(String name,LatLng latLng) {
        super(name,latLng);
    }


    public enum ServiceType {
        Cafe, Restaurant, Pub, Shop
    }
}
