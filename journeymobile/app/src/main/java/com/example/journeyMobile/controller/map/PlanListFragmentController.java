package com.example.journeyMobile.controller.map;

import android.view.View;

import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.service.util.DateUtil;
import com.example.journeyMobile.view.fragment.PlanListFragment;

import java.util.Date;
import java.util.List;

public class PlanListFragmentController implements View.OnClickListener {

    private PlanListFragment fragment;
    private PlanListFragment.OnPlanListFragmentListener toActivityListener;

    private PlanListFragment.ViewHolder viewHolder;


    public PlanListFragmentController(PlanListFragment fragment, PlanListFragment.ViewHolder viewHolder
            , PlanListFragment.OnPlanListFragmentListener toActivityListener) {
        this.fragment = fragment;
        this.viewHolder = viewHolder;
        this.toActivityListener = toActivityListener;

        initital();
    }

    public void setListOnAdapter(List<Spot> list) {
        viewHolder.planListAdapter.setSpotList(list);
    }

    public void setDate(Date date) {
        String dateString = DateUtil.getStringFromDate(date, "MMMM dd YYYY");
        viewHolder.dateTV.setText(dateString);
    }

    private void initital() {
//        String[] stringList = {"start", "building 80", "Melbourne", "Sydney", "Singapore", "China"};
//        List<String> list = new ArrayList<>();
//        Collections.addAll(list,stringList);
//
//        viewHolder.planListAdapter.setSampleList(list);
    }

    @Override
    public void onClick(View v) {

    }




}
