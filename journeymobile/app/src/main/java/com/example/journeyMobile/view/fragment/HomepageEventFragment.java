package com.example.journeyMobile.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.model.location.Event;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.model.mock.MockData;
import com.example.journeyMobile.service.util.DateUtil;
import com.example.journeyMobile.view.adapter.HomepageEventlistAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomepageEventFragment extends Fragment {

    private static final String TAG = "HomepageEventFragment";

    private View view;
    private ViewHolder viewHolder;
    private MockData mockData;
    private Date date;
    private HomepageEventListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomepageEventListener) {
            listener = (HomepageEventListener) context;
        } else {
            throw new RuntimeException(context.toString( )
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homepage_event_fragment, container, false);
        viewHolder = new ViewHolder(view);
        setAction();

        mockData = MockData.getSingletonInstance(getContext().getApplicationContext());

        return view;
    }

    public void setDate(Date date) {
        this.date = date;
        viewHolder.dateTv.setText(DateUtil.getStringFromDate(date, "dd MMMM yyyy"));

        // refresh the list
        refreshDate();
    }

    public void refreshDate() {
        List<Spot> showList = new ArrayList<>();
        for (Spot spot : mockData.getEventList()) {
            Event event = (Event)spot;
            if (DateUtil.isInThePeriod(event.getStartDateTime(), event.getEndDateTime(), this.date)) {
                showList.add(event);
            }
        }

        viewHolder.eventlistAdapter.setSpotList(showList);
    }



    public void setAction() {
        viewHolder.lastDayBT.setOnClickListener(new View.OnClickListener( ) {
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
        private Button lastDayBT;
        private Button nextDayBt;
        private TextView dateTv;
        private RecyclerView recyclerView;
        private HomepageEventlistAdapter eventlistAdapter;

        private ViewHolder(View view) {
            lastDayBT = view.findViewById(R.id.homepage_event_lastDay);
            nextDayBt = view.findViewById(R.id.homepageEvent_nextDay);
            dateTv = view.findViewById(R.id.homepage_event_date);

            recyclerView = view.findViewById(R.id.homepage_eventList);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            eventlistAdapter = new HomepageEventlistAdapter(listener);
            recyclerView.setAdapter(eventlistAdapter);

        }
    }

    public interface HomepageEventListener {
        void showEventList();
    }
}

