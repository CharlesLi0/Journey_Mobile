package com.example.journeyMobile.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.model.location.Event;
import com.example.journeyMobile.model.location.Spot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder>{
    private  final String TAG = getClass().getName();

    private List<Spot> spotList;
    private EventItemListener eventItemListener;
    private Date date;

    public EventListAdapter(EventItemListener eventItemListener) {
        this.eventItemListener = eventItemListener;
        this.spotList = new ArrayList<>();
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item
                , viewGroup, false);


        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,int i) {
        final Event event = (Event) spotList.get(i);

        myViewHolder.titleTV.setText(event.getTitle());
        myViewHolder.descriptionTV.setText(event.getDescription());
        myViewHolder.imageView.setBackgroundResource(R.drawable.event_image);
        myViewHolder.timeTV.setText(event.getDuringTime());
        myViewHolder.dateTV.setText(event.getDuringDate());

        myViewHolder.addBT.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                eventItemListener.addEvent(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }


    public interface EventItemListener {
        void addEvent(Spot spot);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTV;
        private TextView timeTV;
        private TextView dateTV;
        private TextView descriptionTV;
        private Button addBT;

        // constructor
        private MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.event_image);
            titleTV = itemView.findViewById(R.id.event_title);
            timeTV = itemView.findViewById(R.id.event_time);
            dateTV = itemView.findViewById(R.id.event_date);
            descriptionTV = itemView.findViewById(R.id.event_description);
            addBT = itemView.findViewById(R.id.event_add);

        }


    }



}
