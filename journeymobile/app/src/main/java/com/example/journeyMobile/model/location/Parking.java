package com.example.journeyMobile.model.location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.Hashtable;

public class Parking extends Spot {
    private int totalNumCar;
    private int availableNumCar;


    public Parking(String name,LatLng latLng) {
        super(name,latLng);

    }

    public void setTotalNumCar(int totalNumCar) {
        this.totalNumCar = totalNumCar;
    }

    public void setAviliableNumCar(int aviliableNumCar) {
        this.availableNumCar = aviliableNumCar;
    }

    public enum ParkingType{
        Car, MotorBike, BoatAndTrailer, Disabled;
    }

    @Override
    public String getDescription() {
        return "total number of car: " + totalNumCar + "\n" +
                "available number of car: " + availableNumCar;
    }
}
