package com.example.journeyMobile.model.location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class Bbq extends Spot {
    private int twoPersontable;
    private int fourPersonTable;
    private int sixPersonTable;
    private int eightPersonTable;
    private Date timeOfCount;


    public Bbq(String name,LatLng latLng,int twoPersontable, int fourPersonTable, int sixPersonTable
            , int eightPersonTable, Date timeOfCount) {

        super(name,latLng);
        this.twoPersontable = twoPersontable;
        this.fourPersonTable = fourPersonTable;
        this.sixPersonTable = sixPersonTable;
        this.eightPersonTable = eightPersonTable;
        this.timeOfCount = timeOfCount;

    }

    public Bbq(String name,LatLng latLng,int twoPersontable, int fourPersonTable, int sixPersonTable
            , int eightPersonTable) {

        this(name, latLng, twoPersontable, fourPersonTable, sixPersonTable, eightPersonTable, null);


    }


    public Bbq(String name, LatLng latLng) {
        super(name, latLng);

    }


    @Override
    public String getDescription() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("twoPersontable: ").append(twoPersontable).append("\n")
                .append("fourPersontable: ").append(fourPersonTable).append("\n")
                .append("sixPersontable: ").append(fourPersonTable).append("\n")
                .append("eightPersontable: ").append(eightPersonTable);

        return  stringBuilder.toString();
    }
}
