package com.example.journeyMobile.model.location;

import com.google.android.gms.maps.model.LatLng;

public class Bin extends Spot {
    private BinType grabageType;

    private double currentFillLevel;

    public Bin(String name,LatLng latLng) {
        super(name,latLng);
    }

    public Bin(String name,LatLng latLng, double currentFillLevel) {
        super(name,latLng);

        this.currentFillLevel = currentFillLevel;
    }

    public double getCurrentFillLevel() {
        return currentFillLevel;
    }

    @Override
    public String getDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("currentFillLevel: ").append(currentFillLevel);

        return  stringBuilder.toString();
    }

    public boolean isAvailable() {
        return currentFillLevel == 1;
    }

    public enum BinType {

    }
}
