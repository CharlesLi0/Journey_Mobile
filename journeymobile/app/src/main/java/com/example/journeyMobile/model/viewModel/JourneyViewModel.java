package com.example.journeyMobile.model.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.journeyMobile.model.JourneyModel;
import com.example.journeyMobile.model.journey.DailyPlan;
import com.example.journeyMobile.model.location.Spot;

import java.util.Date;
import java.util.List;

public class JourneyViewModel extends AndroidViewModel {
    private static String TAG = "JourneyViewModel";
    private MutableLiveData<DailyPlan> dailiyPlanLiveData;
    private JourneyModel journeyModel;

    public JourneyViewModel(@NonNull Application application) {
        super(application);
        journeyModel = JourneyModel.getSingletonInstance(application);

        if ( dailiyPlanLiveData == null) {

            dailiyPlanLiveData = new MutableLiveData<>();
            dailiyPlanLiveData.setValue(journeyModel.getPlanList());
        }

    }

    public void refreshData() {
        dailiyPlanLiveData.setValue(journeyModel.getPlanList());

    }

    public MutableLiveData<DailyPlan> getDailiyPlanLiveData() {
        return dailiyPlanLiveData;
    }

    public boolean addSpot(Spot spot) {
        if (!journeyModel.addSpot(spot))
            return false;
        refreshData();
        return true;
    }

    public boolean addSpot(Spot spot, Date date) {
        return journeyModel.addSpot(spot, date);
    }

    public List<Date> getDateList() {
        return  journeyModel.getDateList();
    }

    public void setCurrentDate(Date date) {
        journeyModel.setIndexByDate(date);

        refreshData();
    }

    public void removeDailyPlan(Date date) {
        journeyModel.removeDailyPlan(date);
        refreshData();
    }

}
