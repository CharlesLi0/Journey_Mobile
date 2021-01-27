package com.example.journeyMobile.controller.map;

import android.view.View;
import android.widget.Toast;

import com.example.journeyMobile.R;
import com.example.journeyMobile.view.fragment.FacilitiesFragment;


public class FacilitiesFragmentController implements View.OnClickListener {
    private FacilitiesFragment fragment;
    private FacilitiesFragment.ViewHolder viewHolder;
    private FacilitiesFragment.OnFacilitiesFragmentListener toActivityListener;

    // switch of all the facilities
    private boolean isShowBBq;
    private boolean isShowBin;
    private boolean isShowToilet;
    private boolean isShowrestaurant;
    private boolean isShowSupermarket;

    public FacilitiesFragmentController(FacilitiesFragment facilitiesFragment) {
        this.fragment = facilitiesFragment;
    }

    public void setViewHolder(FacilitiesFragment.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public void setToActivityListener(FacilitiesFragment.OnFacilitiesFragmentListener toActivityListener) {
        this.toActivityListener = toActivityListener;
    }

    @Override
    public void onClick(View v) {
        v.setClickable(false);

        int id = v.getId();
        switch (id) {
            case R.id.facility_button:
                break;

            case R.id.bbq_bt:
                bbqAction();
                break;

            case R.id.toilet_bt:
                toiletAction();
                break;

            case R.id.bin_bt:
                binAction();
                break;

            case R.id.restaurant_bt:
                restaurantAction();
                break;

            case R.id.supermarket_bt:
                supermarketAction();
                break;

            default:
                Toast.makeText(fragment.getContext(), "do not have this ONCLICK action"
                        , Toast.LENGTH_SHORT).show();
                break;
        }

        showFacility();
        v.setClickable(true);
    }

    private void showFacility() {

        toActivityListener.switchOfFacilities(isShowBBq, isShowToilet, isShowBin, isShowrestaurant, isShowSupermarket);

    }

    private void bbqAction() {

        isShowBBq = !isShowBBq;
        if (isShowBBq) {
            viewHolder.bbqBT.setBackground(fragment.getResources().getDrawable(R.drawable.bbq_selected));
        }
        else {
            viewHolder.bbqBT.setBackground(fragment.getResources().getDrawable(R.drawable.bbq));
        }
    }

    private void toiletAction() {
        isShowToilet = !isShowToilet;

        if (isShowToilet) {

            viewHolder.toiletBT.setBackground(fragment.getResources().getDrawable(R.drawable.toilet_selected));

        }
        else {
            viewHolder.toiletBT.setBackground(fragment.getResources().getDrawable(R.drawable.toliet));
        }
    }

    private void binAction() {
        isShowBin = !isShowBin;

        if (isShowBin) {
            viewHolder.binBT.setBackground(fragment.getResources().getDrawable(R.drawable.rubbish_bin_selected));
        }
        else {
            viewHolder.binBT.setBackground(fragment.getResources().getDrawable(R.drawable.rubbish_bin));
        }
    }

    private void restaurantAction() {
        isShowrestaurant = !isShowrestaurant;

        if (isShowrestaurant) {
            viewHolder.restaurantBT.setBackground(fragment.getResources().getDrawable(R.drawable.restaurant_selected));
        }
        else {
            viewHolder.restaurantBT.setBackground(fragment.getResources().getDrawable(R.drawable.restaurant));
        }
    }

    private void supermarketAction() {
        isShowSupermarket = !isShowSupermarket;

        if (isShowSupermarket) {
            viewHolder.supermarketBT.setBackground(fragment.getResources().getDrawable(R.drawable.supermarket_selected));
        }
        else {
            viewHolder.supermarketBT.setBackground(fragment.getResources().getDrawable(R.drawable.supermarket));
        }
    }



}
