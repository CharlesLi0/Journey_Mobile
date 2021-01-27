package com.example.journeyMobile.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.service.util.DateUtil;

import java.util.Date;

public class HomepageWeatherFragment extends Fragment {

    private static final String TAG = "HomepageWeatherFragment";

    private View view;
    private ViewHolder viewHolder;
    private Date date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homepage_weather_fragment, container, false);
        viewHolder = new ViewHolder(view);
        setAction();

        return view;

    }

    public void setDate(Date date) {
        this.date = date;

        viewHolder.dateTV.setText(DateUtil.getStringFromDate(date, "dd MMMM yyyy"));
    }

    private void setAction() {
        viewHolder.lastDayBt.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                int current = date.getDate() - 1;
                date.setDate(current);
                setDate(date);
            }
        });

        viewHolder.nextDayBt.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                int current = date.getDate() + 1;
                date.setDate(current);
                setDate(date);
            }
        });
    }



    public class ViewHolder {
        private Button lastDayBt;
        private Button nextDayBt;
        private TextView dateTV;

        private ViewHolder(View view) {
            lastDayBt = view.findViewById(R.id.homepage_weather_lastDay);
            nextDayBt = view.findViewById(R.id.homepage_weather_nextDay);
            dateTV = view.findViewById(R.id.homepage_weather_date);
        }

    }
}

