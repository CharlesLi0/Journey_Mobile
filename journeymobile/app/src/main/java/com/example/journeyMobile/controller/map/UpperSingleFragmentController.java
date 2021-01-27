package com.example.journeyMobile.controller.map;

import android.util.Log;
import android.view.View;

import com.example.journeyMobile.R;
import com.example.journeyMobile.view.MapActivity;
import com.example.journeyMobile.view.fragment.UpperSingelSearchFragment;

public class UpperSingleFragmentController implements View.OnClickListener {
    private String TAG = getClass().getName();

    private UpperSingelSearchFragment fragment;
    private UpperSingelSearchFragment.Viewholder viewHolder;
    private UpperSingelSearchFragment.OnSingleSearchFragmentListener toActivityListerner;

    public UpperSingleFragmentController(UpperSingelSearchFragment fragment) {
        this.fragment = fragment;
    }

    public void setViewHolder(UpperSingelSearchFragment.Viewholder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public void setToActivityListerner(UpperSingelSearchFragment.OnSingleSearchFragmentListener toActivityListerner) {
        this.toActivityListerner = toActivityListerner;
    }

    @Override
    public void onClick(View v) {
        v.setClickable(false);

        int id  = v.getId();
        switch (id) {
            case R.id.back_bt:
                backBTAction();
                break;

            case R.id.searchView:
                searchVAction();
                break;

            default:
                Log.d(TAG, "do not set the action for that menber here" );
                break;
        }


        v.setClickable(true);
    }

    private void backBTAction() {
        fragment.getActivity().finish();
    }

    private void searchVAction() {
        toActivityListerner.addressAutoCompleted(viewHolder.searchET, MapActivity.SINGLE_SEARCH_CODE);
    }

    private void searchBTAction() {

    }

    public void setSearchText(String name) {
        viewHolder.searchET.setText(name);
    }
}
