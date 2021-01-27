package com.example.journeyMobile.model.location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class Spot {
    private String title;
    private LatLng coordination;
    private Date arriveTime;
    private Date leaveTime;

    // minutes
    private Integer duration = 0;

    public Spot(String title,LatLng coordination,Date arriveTime,Date leaveTime, Integer duration) {
        this.title = title;
        this.coordination = coordination;
        this.arriveTime = arriveTime;
        this.leaveTime = leaveTime;
        this.duration = duration;
    }

    public Spot(String title, LatLng coordination) {
        this(title, coordination, null, null, null);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getCoordination() {
        return coordination;
    }

    public void setCoordination(LatLng coordination) {
        this.coordination = coordination;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDescription() {
        return "";
    }

    public enum SpotType {
        BBQ, BIN, Event, PARKING, SERVICE, TOILET

    }
}
