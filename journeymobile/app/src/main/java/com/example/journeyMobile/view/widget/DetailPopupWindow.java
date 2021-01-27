package com.example.journeyMobile.view.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.model.location.Bbq;
import com.example.journeyMobile.model.location.Parking;
import com.example.journeyMobile.model.location.Spot;


public class DetailPopupWindow extends PopupWindow {
    private Context context;
    private Spot spot;
    private DetailPopupListener detailPopupListener;

    private ViewHolder viewHolder;
    private View view;

    public DetailPopupWindow(Context context, Spot spot, DetailPopupListener detailPopupListener) {
        this.context = context;
        this.spot = spot;
        this.detailPopupListener = detailPopupListener;

        initial();
    }



    /**
     * show the popup on the bottom of the provided view
     * @param view provide a view to show the popup on the bottom of it
     */
    public void show(View view) {

        showAtLocation(view, Gravity.BOTTOM, 0, 0);
        showAsDropDown(view);
    }


    private void initial() {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.view = inflater.inflate(R.layout.detail_of_spot1, null);
        viewHolder = new ViewHolder(view);

        if (spot != null){
            viewHolder.titleTV.setText(spot.getTitle());
            viewHolder.descriotionTV.setText(spot.getDescription());
            setImageView();
        }

        // set PopupWindow's width
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // set PopupWindow's height
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // set PopupWindow's View
        this.setContentView(view);
        // set PopupWindow touchable
        this.setFocusable(true);
        this.setOutsideTouchable(true);
    }

    private void setImageView() {
        if (spot instanceof Parking) {
            viewHolder.imageView.setBackgroundResource(R.drawable.carparking_icon);
        } else if (spot instanceof Bbq) {
            viewHolder.imageView.setBackgroundResource(R.drawable.bbq_image);
        } else {
            viewHolder.imageView.setBackgroundResource(R.drawable.nav_car_parking);
        }
    }

    private class ViewHolder {
        private TextView titleTV;
        private TextView descriotionTV;
        private ImageView imageView;
        private Button addButton;
        ViewHolder(View view) {
            titleTV =view.findViewById(R.id.popup_title_TV);
            descriotionTV = view.findViewById(R.id.popuip_descrition_TV);
            imageView = view.findViewById(R.id.popup_image);
            addButton = view.findViewById(R.id.popup_add_BT);

            addButton.setOnClickListener(new View.OnClickListener( ) {

                @Override
                public void onClick(View v) {
                    // add spot to the plan list
                    if (detailPopupListener != null) {
                        detailPopupListener.addSpotToList(spot);
                    }

                    // close the popup
                    DetailPopupWindow.this.dismiss();
                }
            });
        }
    }
}
