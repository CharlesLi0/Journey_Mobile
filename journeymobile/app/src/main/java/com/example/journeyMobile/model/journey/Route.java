package com.example.journeyMobile.model.journey;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    private Integer duration;
    private List<LatLng> route = new ArrayList<>();

    public Route(Integer duration, List<LatLng> route) {
        this.duration = duration;
        this.route.addAll(route);
    }

    public Route(List<LatLng> route) {
        this.route = route;
    }

    public Route() {

    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setRoute(List<LatLng> coordination) {
        this.route.clear();
        this.route.addAll(coordination);
    }

    public List<LatLng> getRoute() {
        return route;
    }

    public void copy(Route route) {
        if (route == null) return;
        this.duration = route.getDuration();
        setRoute(route.getRoute());
    }
}
