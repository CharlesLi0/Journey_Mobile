package com.example.journeyMobile.model.journey;

import com.example.journeyMobile.model.location.Spot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class DailyPlan {
    private Date startDateTime;
    private List<Spot> spots;
    private Hashtable<Spot[], Route> routes;

    public DailyPlan(Date startDateTime, List<Spot> spots, Hashtable<Spot[], Route> routes){
        this.startDateTime = startDateTime;

        this.spots = spots;
        this.routes = routes;
    }

    public DailyPlan(Date startDateTime) {
        this(startDateTime, new ArrayList<Spot>(), new Hashtable<Spot[], Route>());
    }

    public DailyPlan(Date startDateTime, List<Spot> spots) {
        this(startDateTime, spots, new Hashtable<Spot[], Route>());
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Hashtable<Spot[], Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Hashtable<Spot[], Route> routes) {
        this.routes = routes;
    }

    public void setSpots(LinkedList<Spot> spots) {
        this.spots = spots;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void addSpots(Spot spot) {
        spots.add(spot);
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
}
