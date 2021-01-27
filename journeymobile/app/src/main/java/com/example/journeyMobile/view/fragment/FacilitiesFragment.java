package com.example.journeyMobile.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.controller.map.FacilitiesFragmentController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FacilitiesFragment.OnFacilitiesFragmentListener} interface
 * to handle interaction events.
 */
public class FacilitiesFragment extends Fragment {
    public static String TAG = "FacilitiesFragment";

    private OnFacilitiesFragmentListener mListener;
    private ViewHolder viewHolder;
    private FacilitiesFragmentController controller;

    public FacilitiesFragment() {
        // Required empty public constructor
    }

    public FacilitiesFragmentController getController() {
        return controller;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFacilitiesFragmentListener) {
            mListener = (OnFacilitiesFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString( )
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facilities_button, container, false);
        viewHolder = new ViewHolder(view);
        controller = new FacilitiesFragmentController(this);
        controller.setViewHolder(viewHolder);
        controller.setToActivityListener(mListener);

        setAction();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach( );
        controller = null;
        mListener = null;
    }

    private void setAction() {
        viewHolder.facilitiesTV.setOnClickListener(controller);
        viewHolder.bbqBT.setOnClickListener(controller);
        viewHolder.toiletBT.setOnClickListener(controller);
        viewHolder.binBT.setOnClickListener(controller);
        viewHolder.restaurantBT.setOnClickListener(controller);
        viewHolder.supermarketBT.setOnClickListener(controller);
    }


    public class ViewHolder {
        public TextView facilitiesTV;
        public Button bbqBT;
        public Button toiletBT;
        public Button binBT;
        public Button restaurantBT;
        public Button supermarketBT;

        ViewHolder(View view) {
            facilitiesTV = view.findViewById(R.id.facility_button);
            bbqBT = view.findViewById(R.id.bbq_bt);
            toiletBT = view.findViewById(R.id.toilet_bt);
            binBT = view.findViewById(R.id.bin_bt);

            // have not implement
            restaurantBT = view.findViewById(R.id.restaurant_bt);
            restaurantBT.setVisibility(View.INVISIBLE);
            supermarketBT = view.findViewById(R.id.supermarket_bt);
            supermarketBT.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFacilitiesFragmentListener {

        void switchOfFacilities(boolean isShowBBq, boolean isShowToilet, boolean isShowBin
                , boolean isShowRestaurant, boolean isShowSupermarket);
    }
}
