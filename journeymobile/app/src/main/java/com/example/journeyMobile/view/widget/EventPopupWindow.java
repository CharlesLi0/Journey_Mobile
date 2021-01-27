package com.example.journeyMobile.view.widget;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.model.location.Event;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.model.viewModel.JourneyViewModel;
import com.example.journeyMobile.service.util.CalendarUtils;
import com.example.journeyMobile.service.util.DateUtil;
import com.example.journeyMobile.view.adapter.EventListAdapter;
import com.example.journeyMobile.view.fragment.pickers.DatePickerFragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventPopupWindow extends PopupWindow implements EventListAdapter.EventItemListener
        , DatePickerFragment.DatePickerListener {

    private String TAG = getClass().getName();
    private Context context;
    private EventPopupListener eventPopupListener;
    private JourneyViewModel journeyViewModel;

    private Date selectedDate;
    private List<Spot> spotList;
    private ViewHolder viewHolder;
    private View view;

    public EventPopupWindow(Context context, List<Spot> spotList, EventPopupListener eventPopupListener) {
        this.context = context;
        this.spotList = spotList;
        this.eventPopupListener = eventPopupListener;

        initialView();
        initialProperty();
    }



    /**
     * show the popup on the center of the provided view
     * @param view provide a view to show the popup on the bottom of it
     */
    public void show(View view) {

        showAtLocation(view, Gravity.CENTER, 0, 0);
        showAsDropDown(view);
    }


    private void initialView() {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.event_popup, null);
        viewHolder = new ViewHolder(view);
        setDate(new Date());

    }

    private void initialProperty() {
        // set PopupWindow's width
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // set PopupWindow's height
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // set PopupWindow's View
        this.setContentView(view);
        // set PopupWindow touchable
        this.setFocusable(true);
        this.setOutsideTouchable(true);
    }

    public void setDate(Date date) {
        if (viewHolder == null) return;

        selectedDate = date;
        String y = DateUtil.getStringFromDate(date, "yyyy");
        String d = DateUtil.getStringFromDate(date, "MMMM dd");
        String w = DateUtil.getStringFromDate(date, "EEEE");
        viewHolder.yearTV.setText(y);
        viewHolder.dateTV.setText(d);
        viewHolder.weekDayTV.setText(w);

        refreshDataList();
    }

    private void refreshDataList() {
        if (spotList == null || viewHolder == null) return;

        List<Spot> showList = new ArrayList<>();
        for (Spot spot : spotList) {
            Event event = (Event)spot;
            if (DateUtil.isInThePeriod(event.getStartDateTime(), event.getEndDateTime(), selectedDate)) {

                showList.add(event);
            }


        }

        viewHolder.eventListAdapter.setSpotList(showList);
    }

    private void setSelectedDate(int year, int month, int dayOfMonth) {

        String s = dayOfMonth + "/" + month + "/" + year;
        Log.d("Charles", s);
        try {
            selectedDate = DateUtil.getDateFromString(s, "dd/MM/yyyy");
        } catch (ParseException e) {
            Log.d(TAG, e.getMessage());

        }

    }

    @Override
    public void addEvent(Spot spot) {
        eventPopupListener.addEventToList(spot, selectedDate);
    }

    @Override
    public void onDateSet(int year,int month,int dayOfMonth) {
        setSelectedDate(year, month + 1, dayOfMonth);

        // set the text
        setYear(String.valueOf(year));
        Date date = new Date(year,month, dayOfMonth);
        setDayAndMonth(DateUtil.getStringFromDate(date, "MMMM dd"));
        setDayOfWeek(CalendarUtils.getDayOfWeek(year, month, dayOfMonth));

        // refresh the list
        refreshDataList();
    }

    private void setYear(String year) {
        viewHolder.yearTV.setText(year);
    }

    private void setDayAndMonth(String dayOfMonth) {
        viewHolder.dateTV.setText(dayOfMonth);
    }

    private void setDayOfWeek(String dayOfWeek) {
        viewHolder.weekDayTV.setText(dayOfWeek);
    }

    private class ViewHolder {
        private TextView yearTV;
        private TextView weekDayTV;
        private TextView dateTV;
        private Button selectedDateBt;
        private Button backBT;

        private RecyclerView recyclerView;
        private EventListAdapter eventListAdapter;

        ViewHolder(View view) {
            yearTV = view.findViewById(R.id.eventList_popup_year);
            weekDayTV = view.findViewById(R.id.eventList_popup_weekday);
            dateTV = view.findViewById(R.id.eventList_popup_date);
            selectedDateBt = view.findViewById(R.id.eventList_selectDate);
            selectedDateBt.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    DatePickerFragment datePickerFragment = new DatePickerFragment(EventPopupWindow.this);
                    datePickerFragment.show(((FragmentActivity)context).getSupportFragmentManager(), "DatePicker");
                }
            });
            backBT = view.findViewById(R.id.eventList_popup_backBT);
            backBT.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    EventPopupWindow.this.dismiss();
                }
            });

            recyclerView = view.findViewById(R.id.event_popup_reclycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            eventListAdapter = new EventListAdapter(EventPopupWindow.this);
            recyclerView.setAdapter(eventListAdapter);
        }
    }

}
