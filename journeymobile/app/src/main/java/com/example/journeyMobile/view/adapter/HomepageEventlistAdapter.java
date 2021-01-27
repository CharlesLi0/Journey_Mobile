package com.example.journeyMobile.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.model.location.Event;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.service.util.DateUtil;
import com.example.journeyMobile.view.fragment.HomepageEventFragment;

import java.util.ArrayList;
import java.util.List;

public class HomepageEventlistAdapter extends RecyclerView.Adapter<HomepageEventlistAdapter.ViewHolder> {
    private  final String TAG = getClass().getName();

    private List<Spot> spotList;
    private int itemCount = 1;
    private boolean isExpand = false;
    private HomepageEventFragment.HomepageEventListener toActivity;

    public HomepageEventlistAdapter(HomepageEventFragment.HomepageEventListener toActivity) {
        this.spotList = new ArrayList<>();
        this.toActivity = toActivity;
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
        setExpand(false);
        notifyDataSetChanged();
    }

    private void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if (spotList == null || spotList.size( ) == 0) itemCount = 0;
        else if (isExpand) itemCount = 1;
        else itemCount = 1;

        notifyDataSetChanged();
    }

    private void showList() {
        isExpand = !isExpand;

        if (spotList == null || spotList.size( ) == 0) itemCount = 0;
        else if (isExpand) itemCount = 1;
        else itemCount = 1;

        notifyDataSetChanged();
        toActivity.showEventList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homepage_event_item
                , viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,int i) {
        if (spotList == null || spotList.size() == 0) {
            viewHolder.eventBox.setVisibility(View.INVISIBLE);
            return;
        }

        viewHolder.eventBox.setVisibility(View.VISIBLE);

        Event event = (Event) spotList.get(i);
        viewHolder.titleTV.setText(event.getTitle());
        viewHolder.startTimeTV.setText(DateUtil.getStringFromDate(event.getStartDateTime(), "hh:mm a"));
        viewHolder.endTimeTV.setText(DateUtil.getStringFromDate(event.getEndDateTime(), "hh:mm a"));


        if (!isExpand) {
            String s = "+" + (spotList.size( ) - 1);
            viewHolder.hideTv.setText(s);
            viewHolder.hideTv.setVisibility(View.VISIBLE);
            viewHolder.hideTv.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    HomepageEventlistAdapter.this.showList();
                }
            });
        } else {
            viewHolder.hideTv.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout eventBox;
        private TextView titleTV;
        private TextView startTimeTV;
        private TextView endTimeTV;
        private TextView hideTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventBox = itemView.findViewById(R.id.homepage_event_box);
            titleTV = itemView.findViewById(R.id.homepageEvent_title);
            startTimeTV = itemView.findViewById(R.id.homepageEvent_startTime);
            endTimeTV = itemView.findViewById(R.id.homepageEvent_endTime);
            hideTv = itemView.findViewById(R.id.homepageEvent_hiddenNum);
        }
    }



}
