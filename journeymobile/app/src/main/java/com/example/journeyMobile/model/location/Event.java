package com.example.journeyMobile.model.location;

import com.example.journeyMobile.service.util.DateUtil;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class Event extends Spot {

    public String description;
    public Date startDateTime;
    public Date endDateTime;

    public Event(String title, LatLng coordination, String description,Date startDateTime
            ,Date endDateTime,String website) {

        super(title,coordination);
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.website = website;
    }

    public Event(String title, LatLng coordination, String description,Date startDateTime
            ,Date endDateTime) {

        this(title, coordination, description, startDateTime, endDateTime, null);

    }

    public String website;

    public Event(String name,LatLng latLng) {
        super(name,latLng);
    }

    public enum EventType {
        Entertainment, Festival, Sport
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public String getDuringDate() {
        return DateUtil.getStringFromDate(startDateTime, "MMMM dd");
    }

    public String getDuringTime(){
        return DateUtil.getStringFromDate(startDateTime, "hh:mm a") + " - "
                + DateUtil.getStringFromDate(endDateTime, "hh:mm a");
    }

    @Override
    public String getDescription() {
        return description;
    }
}
