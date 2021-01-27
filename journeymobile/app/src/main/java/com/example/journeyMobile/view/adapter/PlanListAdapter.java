package com.example.journeyMobile.view.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.view.fragment.PlanListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.MyViewHolder>
        implements ItemMoveCallback.ItemTouchHelperContract {

    private List<Spot> spotList;

    private PlanListFragment.OnPlanListFragmentListener toActivityListener;

    public PlanListAdapter() {
        spotList = new ArrayList<>();
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;

        notifyDataSetChanged();
    }



    public void setToActivityListener(PlanListFragment.OnPlanListFragmentListener toActivityListener) {
        this.toActivityListener = toActivityListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plan_item
                , viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,final int i) {

        if (i == 0 ) {
            myViewHolder.durationBoxLL.setVisibility(View.INVISIBLE);
            myViewHolder.arrivalBox.setVisibility(View.INVISIBLE);
            myViewHolder.delectButton.setVisibility(View.INVISIBLE);
            myViewHolder.spotTitleTV.setText(spotList.get(i).getTitle());

        } else {
            myViewHolder.durationBoxLL.setVisibility(View.VISIBLE);
            myViewHolder.arrivalBox.setVisibility(View.INVISIBLE);
            myViewHolder.delectButton.setVisibility(View.VISIBLE);
            myViewHolder.spotTitleTV.setText(spotList.get(i).getTitle());

            myViewHolder.delectButton.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    spotList.remove(i);
                    notifyDataSetChanged();
                }
            });

        }

//        myViewHolder.spotTitleTV.setText(sampleList.get(i));
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }

    @Override
    public void onRowMoved(int fromPostion,int toPostion) {
        if (fromPostion == 0) return;
        if (toPostion == 0) toPostion = 1;

        if (fromPostion < toPostion) {
            for (int i = fromPostion; i < toPostion; i++) {
                Collections.swap(spotList, i, i + 1);
            }

        } else {
            for (int i = fromPostion; i > toPostion; i--) {
                Collections.swap(spotList, i, i - 1);
            }
        }

        notifyItemMoved(fromPostion, toPostion);
    }




    @Override
    public void onRowSelected(MyViewHolder myViewHolder) {
        myViewHolder.anItem.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(MyViewHolder myViewHolder) {
        myViewHolder.anItem.setBackgroundColor(Color.WHITE);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout anItem;

        private TextView spotTitleTV;


        private LinearLayout durationBoxLL;
        private EditText durationET;

        private LinearLayout arrivalBox;
        private TextView arrivalTimeTV;

        private TextView departureTime;

        private TextView travelTimeTV;

        private Button delectButton;

        // constructor
        private MyViewHolder(@NonNull View itemView) {
            super(itemView);

            anItem = itemView.findViewById(R.id.plan_item_linearlayout);

            spotTitleTV = itemView.findViewById(R.id.planList_spotTitle_planItem);

            durationBoxLL = itemView.findViewById(R.id.duration_box);
            durationET = itemView.findViewById(R.id.duration_ET_planItem);

            arrivalBox = itemView.findViewById(R.id.arrivalBox_LinearL_planItem);
            arrivalTimeTV = itemView.findViewById(R.id.arrivalTime_TV_planItem);

            departureTime = itemView.findViewById(R.id.departure_time_text);

            travelTimeTV = itemView.findViewById(R.id.travelTime_TV_planItem);

            delectButton = itemView.findViewById(R.id.delete_plan_item);
        }
    }
}
