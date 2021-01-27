package com.example.journeyMobile.controller.map;

import android.view.View;

import com.example.journeyMobile.R;
import com.example.journeyMobile.view.MapActivity;
import com.example.journeyMobile.view.fragment.UpperTwoSearchFragment;

public class UpperDoubleSearchFragmentController implements View.OnClickListener {
    private UpperTwoSearchFragment fragment;
    private UpperTwoSearchFragment.ViewHolder viewHolder;
    private UpperTwoSearchFragment.OnUpperDoubleSearchListener toActivityListerner;

    public UpperDoubleSearchFragmentController(UpperTwoSearchFragment fragment) {
        this.fragment = fragment;
    }

    public void setViewHolder(UpperTwoSearchFragment.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public void setToActivityListerner(UpperTwoSearchFragment.OnUpperDoubleSearchListener toActivityListerner) {
        this.toActivityListerner = toActivityListerner;
    }

    @Override
    public void onClick(View v) {
        v.setClickable(false);

        int id = v.getId();
        switch (id) {
            case R.id.back_bt:
                backBTAction();
                break;

            case R.id.from_EditText:
                fromETAction();
                break;

            case R.id.to_EditText:
                toETAction();
                break;

            case R.id.route_bt:
                routeBTAction();
                break;
        }

        v.setClickable(true);
    }

    private void backBTAction() {
        fragment.getActivity().finish();
    }

    private void fromETAction() {
        toActivityListerner.addressAutoCompleted(viewHolder.fromET, MapActivity.START_CODE);
    }
    private void toETAction() {
        toActivityListerner.addressAutoCompleted(viewHolder.toET, MapActivity.END_CODE);
    }
    private void routeBTAction() {
        toActivityListerner.createRoute();
    }

    public void setFromText(String fromText) {
        viewHolder.fromET.setText(fromText);
    }

    public void setToText(String toText) {
        viewHolder.toET.setText(toText);
    }


}
