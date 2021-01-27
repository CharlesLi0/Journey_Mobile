package com.example.journeyMobile.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.journeyMobile.R;
import com.example.journeyMobile.controller.map.UpperDoubleSearchFragmentController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpperTwoSearchFragment.OnUpperDoubleSearchListener} interface
 * to handle interaction events.
 */
public class UpperTwoSearchFragment extends Fragment {
    public static String TAG = "UpperTwoSearchFragment";
    private OnUpperDoubleSearchListener mListener;
    private ViewHolder viewHolder;
    private UpperDoubleSearchFragmentController controller;

    public UpperTwoSearchFragment() {
        // Required empty public constructor
    }

    public UpperDoubleSearchFragmentController getController() {
        return controller;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUpperDoubleSearchListener) {
            mListener = (OnUpperDoubleSearchListener) context;

        } else {
            throw new RuntimeException(context.toString( )
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upper_double_search_tool, container, false);
        viewHolder = new ViewHolder(view);

        controller = new UpperDoubleSearchFragmentController(this);
        controller.setViewHolder(viewHolder);
        controller.setToActivityListerner(mListener);

        setAction();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach( );
        mListener = null;
    }

    private void setAction() {
        viewHolder.backBT.setOnClickListener(controller);
        viewHolder.fromET.setOnClickListener(controller);
        viewHolder.toET.setOnClickListener(controller);
        viewHolder.createRouteBT.setOnClickListener(controller);
    }

    public class ViewHolder {
        public ImageButton backBT;
        public EditText fromET;
        public EditText toET;
        public ImageButton createRouteBT;

        private ViewHolder(View view) {
            this.backBT = view.findViewById(R.id.back_bt);
            this.fromET = view.findViewById(R.id.from_EditText);
            this.toET = view.findViewById(R.id.to_EditText);
            this.createRouteBT = view.findViewById(R.id.route_bt);
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
    public interface OnUpperDoubleSearchListener {

        void addressAutoCompleted(EditText editText, int code);

        void createRoute();
    }
}
