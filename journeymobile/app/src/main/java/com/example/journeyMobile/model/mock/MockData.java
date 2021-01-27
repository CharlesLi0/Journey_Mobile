package com.example.journeyMobile.model.mock;

import android.content.Context;
import android.util.Log;

import com.example.journeyMobile.model.journey.DailyPlan;
import com.example.journeyMobile.model.location.Bbq;
import com.example.journeyMobile.model.location.Bin;
import com.example.journeyMobile.model.location.Event;
import com.example.journeyMobile.model.location.Parking;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.model.location.Toilet;
import com.example.journeyMobile.service.util.DateUtil;
import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockData {
    private static String TAG = "MockData";
    private static Context applicationContext;
    private List<Spot> bbqList;
    private List<Spot> binList;
    private List<Spot> toiletList;
    private List<Spot> parkingList;
    private List<Spot> serviceList;
    private List<Spot> eventList;

    private final String[][] bbqData = {{"bbq1", "-38.365500,144.79000", "8", "3", "5", "2"},
            {"bbq2", "-38.367500,144.79900", "8", "2", "1", "3"},
            {"bbq3", "-38.375500,144.79000", "2", "3", "2", "1"},
            {"bbq4", "-38.371500,144.826000", "3", "2", "2", "2"},
            {"bbq5", "-38.37900,144.789500", "6", "7", "5", "2"},
            {"bbq6", "-38.381500,144.816000", "8", "5", "3", "2"},
            {"bbq7", "-38.384300,144.826500", "4", "3", "4", "2"}
    };

    private final  String[][] binData = {{"bin1", "-38.380200,144.80000", "0.6"},
            {"bin2", "-38.366100,144.80000", "0.1"},
            {"bin3", "-38.385010,144.82300", "0.4"},
            {"bin4", "-38.386090,144.79100", "0.7"},
            {"bin5", "-38.375500,144.83300", "0.3"},
            {"bin6", "-38.381800,144.81600", "0.3"},
            {"bin7", "-38.379500,144.82350", "0.3"}
    };

    private final String[][] toiletData = {{"toilet1", "-38.383400,144.79600"},
            {"toilet2", "-38.390000, 144.8500"},
            {"toilet3", "-39.390000, 144.7900"},
            {"toilet4", "-38.374444, 144.8000"},
            {"toilet5", "-38.382222, 144.8200"},
            {"toilet6", "-38.382000, 144.8430"},
            {"toilet7", "-38.383000, 144.8250"}
    };

    private final String[][] parkingData = {
            {"parking1", "-38.370000,144.79000", "30", "10"},
            {"parking2", "-38.380000, 144.8500", "20", "17"},
            {"parking3", "-38.381000, 144.7900", "10", "10"},
            {"parking4", "-38.374000, 144.8000", "10", "3"},
            {"parking5", "-38.383222, 144.8200", "22", "10"},
            {"parking6", "-38.382222, 144.8370", "22", "10"},
            {"parking7", "-38.384322, 144.8120", "22", "10"},
            {"parking8", "-38.385622, 144.8410", "22", "10"},
            {"parking9", "-38.385100, 144.7990", "22", "10"},
            {"parking10", "-38.383620, 144.8060", "22", "10"},
            {"parking11", "-38.377620, 144.8260", "22", "10"}
    };

    private final String[][] serviceData = {};


    private static final String[][] eventData = {
            {"event1", "-38.378000,155.78000", "11/10/2019", "30/10/2019", "The Festival has always brought together the best of traditional folk music."},
            {"event2", "-38.378500,155.79000", "21/10/2019", "30/10/2019", "The Festival has always brought together the best of funk music."},
            {"event3", "-38.382000,155.78400", "03/11/2019", "06/11/2019", "The Festival has always brought together the best of Jazz music."},
            {"event4", "-38.378300,155.78200", "02/11/2019", "03/11/2019", "The Festival has always brought together the best of hip-hop music."},
            {"event5", "-38.382000,155.77600", "01/11/2019", "01/11/2019", "The Festival has always brought together the best of new age music."},
            {"event6", "-38.384000,155.77200", "10/11/2019", "10/11/2019", "The Festival has always brought together the best of traditional music."},
            {"event7", "-38.383000,155.78000", "06/11/2019", "09/11/2019", "The Festival has always brought together the best of rock music."},
            {"event8", "-38.385000,155.78000", "02/11/2019", "09/11/2019", "The Festival has always brought together the best of funk music."},
            {"event9", "-38.380000,155.77500", "01/11/2019", "02/11/2019", "The Festival has always brought together the best of Soul music."},
            {"event10", "-38.374000,155.83000", "05/11/2019", "05/11/2019", "The Festival has always brought together the best of  POP music."},
            {"event11", "-38.375000,155.85000", "08/11/2019", "12/11/2019", "The Festival has always brought together the best of funk music."},
            {"event12", "-38.380000,155.80000", "08/11/2019", "09/11/2019", "The Festival has always brought together the best of R&B music."},
            {"event13", "-38.386000,155.79000", "06/11/2019", "06/11/2019", "The Festival has always brought together the best of electro music."},
            {"event14", "-38.385000,155.83000", "08/11/2019", "08/11/2019", "The Festival has always brought together the best of funk music."},
            {"event15", "-38.383000,155.81000", "13/11/2019", "19/11/2019", "The Festival has always brought together the best of reggae music."}
    };

    private static final String[][] mockPlanData = {{"spot1", "-37.8,144.95"},
            {"spot2", "-37.967500,145.30000"},
            {"spot3", "-38.137500,145.49900"},
            {"spot4", "-38.257500,145.12900"},
            {"spot5", "-38.380000,144.80000"}
    };



    private MockData() {
        bbqList = new ArrayList<>();
        binList = new ArrayList<>();
        toiletList = new ArrayList<>();
        parkingList = new ArrayList<>();
        serviceList = new ArrayList<>();
        eventList = new ArrayList<>();

        initialMockData();

    }

    //We need application context to access resource so that we can read from raw data files stored in resource
    public static MockData getSingletonInstance(Context appContext) {
        if (applicationContext == null) {
            applicationContext = appContext;
        }
        return MockData.LazyHolder.INSTANCE;
    }

    // singleton support
    // thread safe lazy initialisation: see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // IMPORTANT: we need a context but should pass getApplicationContext() since this exists for the lifetime
    // of the app anyway
    private static class LazyHolder {
        static final MockData INSTANCE = new MockData();
    }

    private void initialMockData() {
        mockBbqData();
        mockBinData();
        mockToiletData();
        mockParkingData();
        mockServiceData();
        mockEventList();

    }

    public List<Spot> getBbqList() {
        return bbqList;
    }

    public List<Spot> getBinList() {
        return binList;
    }

    public List<Spot> getToiletList() {
        return toiletList;
    }

    public List<Spot> getParkingList() {
        return parkingList;
    }

    public List<Spot> getEventList() {
        return eventList;
    }

    private void mockBbqData() {
        if (bbqList == null) return;

        for (String[] array : bbqData) {
            String title = array[0];

            String latlogString = array[1];
            double lat = Double.valueOf(latlogString.split(",")[0]);
            double lng = Double.valueOf(latlogString.split(",")[1]);
            LatLng latLng = new LatLng(lat, lng);

            int two = Integer.parseInt(array[2]);
            int four = Integer.parseInt(array[3]);
            int six = Integer.parseInt(array[4]);
            int eight = Integer.parseInt(array[5]);

            Bbq bbq = new Bbq(title, latLng, two, four, six, eight);
            bbqList.add(bbq);
        }

    }

    private void mockBinData() {
        if (binList == null) return;

        for (String[] array : binData) {
            String title = array[0];
            String latlogString = array[1];
            double lat = Double.valueOf(latlogString.split(",")[0]);
            double lng = Double.valueOf(latlogString.split(",")[1]);
            LatLng latLng = new LatLng(lat, lng);

            double a = Double.parseDouble(array[2]);
            Bin bin = new Bin(title, latLng, a);
            binList.add(bin);
        }
    }

    private void mockToiletData() {
        if (toiletList == null) return;

        for (String[] array : toiletData) {
            String title = array[0];
            String latlogString = array[1];
            double lat = Double.valueOf(latlogString.split(",")[0]);
            double lng = Double.valueOf(latlogString.split(",")[1]);
            LatLng latLng = new LatLng(lat, lng);


            Toilet toilet = new Toilet(title, latLng);
            toiletList.add(toilet);
        }
    }

    private void mockParkingData() {
        if (parkingList == null) return;

        for (String[] array : parkingData) {
            String title = array[0];
            String latlogString = array[1];
            double lat = Double.valueOf(latlogString.split(",")[0]);
            double lng = Double.valueOf(latlogString.split(",")[1]);
            LatLng latLng = new LatLng(lat, lng);

            int totalNumCarpark = Integer.valueOf(array[2]);
            int availableCarPark = Integer.valueOf  (array[3]);

            Parking parking = new Parking(title, latLng);
            parking.setTotalNumCar(totalNumCarpark);
            parking.setAviliableNumCar(availableCarPark);

            parkingList.add(parking);
        }
    }

    private void mockServiceData() {
        if (serviceList == null) return;


    }

    private void mockEventList() {
        if (eventList == null) return;

        for (String[] array : eventData) {
            String title = array[0];
            String latlogString = array[1];
            double lat = Double.valueOf(latlogString.split(",")[0]);
            double lng = Double.valueOf(latlogString.split(",")[1]);
            LatLng latLng = new LatLng(lat, lng);

            Date startDate;
            Date endDate;
            try {
                startDate = DateUtil.getDateFromString(array[2], "dd/MM/yyyy");
                endDate = DateUtil.getDateFromString(array[3], "dd/MM/yyyy");
            } catch (ParseException e) {
                Log.d(TAG, e.getMessage());
                continue;
            }

            String descrition = array[4];

            Event event = new Event(title, latLng, descrition, startDate, endDate);


            eventList.add(event);
        }
    }


    public static DailyPlan mockPlanData() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        Date date = calendar.getTime();

        List<Spot> spotList = new ArrayList<>();
        for (String[] array : mockPlanData) {
            String title = array[0];

            String latlogString = array[1];
            double lat = Double.valueOf(latlogString.split(",")[0]);
            double lng = Double.valueOf(latlogString.split(",")[1]);
            LatLng latLng = new LatLng(lat, lng);

            Spot spot = new Spot(title, latLng);

            spotList.add(spot);
        }

        return new DailyPlan(date, spotList);
    }







}
