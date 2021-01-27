package com.example.journeyMobile.model;

import android.content.Context;

import com.example.journeyMobile.model.mock.MockData;
import com.example.journeyMobile.model.journey.DailyPlan;
import com.example.journeyMobile.model.location.Spot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class JourneyModel {
    public static String TAG = "JourneyModel";
    private List<DailyPlan> planList;
    private int currentIndex = 0;

    private static Context applicationContext;;

    private JourneyModel() {
        if (planList == null){
            planList = new ArrayList<>();
            planList.add(MockData.mockPlanData());
        }

    }

    // singleton support
    // thread safe lazy initialisation: see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // IMPORTANT: we need a context but should pass getApplicationContext() since this exists for the lifetime
    // of the app anyway
    private static class LazyHolder {
        static final JourneyModel INSTANCE = new JourneyModel();
    }

    //We need application context to access resource so that we can read from raw data files stored in resource
    public static JourneyModel getSingletonInstance(Context appContext) {
        if (applicationContext == null) {
            applicationContext = appContext;
        }
        return LazyHolder.INSTANCE;
    }

    public DailyPlan getPlanList() {
        return planList.get(currentIndex);
    }

    public void addDailyPLan(DailyPlan dailyPlan){
        if (planList != null) planList.add(dailyPlan);
    }

    public boolean addSpot(Spot spot) {
        // check the new one is not on the list
        for (Spot s : planList.get(currentIndex).getSpots()) {
            if (s.getCoordination().equals(spot.getCoordination())) return false;
        }

        // add on the list
        planList.get(currentIndex).addSpots(spot);
        return true;
    }

    public boolean addSpot(Spot spot, Date date) {
        int index = checkHasDate(date);
        DailyPlan dailyPlan = null;

        // check the plan list whether has the daily plan, if not then create
        if (index == -1) {
            dailyPlan = new DailyPlan(date);
            planList.add(dailyPlan);
        }
        else {
            dailyPlan = planList.get(index);
            // check whether it already has the coordination
            for (Spot s : dailyPlan.getSpots()) {
                if (s.getCoordination().equals(spot.getCoordination())) return false;
            }
        }

        dailyPlan.addSpots(spot);

        return true;
    }

    public List<Date> getDateList() {
        // sort the plan list first
//        sortPlanListByDate();

        // get all the date
        List<Date> dateList = new ArrayList<>();
        for (DailyPlan dailyPlan : planList ) {
            dateList.add(dailyPlan.getStartDateTime());
        }

        // return the date list
        return  dateList;
    }


    /**
     * sort the plan list base on the date
     */
    private void sortPlanListByDate() {
        if (planList.size() == 0) return;

        Date currentDate = planList.get(currentIndex).getStartDateTime();

        Collections.sort(planList, new Comparator<DailyPlan>() {
            public int compare(DailyPlan m1, DailyPlan m2) {
                if (m1.getStartDateTime() != null && m2.getStartDateTime() != null)
                    return m1.getStartDateTime().compareTo(m2.getStartDateTime());
                else
                    return 0;
            }
        });

        setIndexByDate(currentDate);
    }

    public void setIndexByDate(Date date) {
        for (int i = 0; i < planList.size(); i++) {
            if (date.compareTo(planList.get(i).getStartDateTime()) == 0) currentIndex = i;
        }
    }

    /**
     * add daily plan
     */
    public void addDailyPLan(Date date) {
        if (checkHasDate(date) == -1) return;

        // new a daily plan and add it to the daily plan
        DailyPlan dailyPlan = new DailyPlan(date);
        planList.add(dailyPlan);

        // set the current index to the last item
        currentIndex = planList.size() - 1;

        // reorder the list by date
        sortPlanListByDate();
    }

    public int checkHasDate(Date date) {
        for (int i = 0; i < planList.size(); i++) {
            if (planList.get(i).getStartDateTime().compareTo(date) == 0) return i;
        }

        return  -1;
    }

    /**
     * remove daily plan
     */
    public void removeDailyPlan(Date date) {
        for (int i = 0; i < planList.size(); i++) {
            if (date.compareTo(planList.get(i).getStartDateTime()) == 0) {
                planList.remove(i);
                break;
            }
        }

        if (planList.size() == 0) {
            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();
            planList.add(new DailyPlan(today));
        }

        currentIndex = 0;
    }

}
