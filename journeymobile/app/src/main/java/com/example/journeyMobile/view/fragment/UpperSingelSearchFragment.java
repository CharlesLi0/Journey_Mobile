package com.example.journeyMobile.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.journeyMobile.R;
import com.example.journeyMobile.controller.map.UpperSingleFragmentController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpperSingelSearchFragment.OnSingleSearchFragmentListener} interface
 * to handle interaction events.
 */
public class UpperSingelSearchFragment extends Fragment {
    public static String TAG = "UpperSingelSearchFragment";



    private UpperSingleFragmentController controller;
    private OnSingleSearchFragmentListener mListener;
    private Viewholder viewholder;

    public UpperSingelSearchFragment() {
        // Required empty public constructor
    }

    public UpperSingleFragmentController getController() {
        return controller;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSingleSearchFragmentListener) {
            mListener = (OnSingleSearchFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString( )
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upper_singel_search_tool, container, false);

        viewholder = new Viewholder(view);
        controller = new UpperSingleFragmentController(this);
        controller.setToActivityListerner(mListener);
        controller.setViewHolder(viewholder);


        setAction();

        return view;
    }





    @Override
    public void onDetach() {
        super.onDetach( );
        mListener = null;
    }

    private void setAction(){
        viewholder.backTV.setOnClickListener(controller);
        viewholder.searchET.setOnClickListener(controller);

    }

    public class Viewholder {
        public ImageButton backTV;
        public EditText searchET;


        private Viewholder(View view) {
            backTV = view.findViewById(R.id.back_bt);
            searchET = view.findViewById(R.id.searchView);

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
    public interface OnSingleSearchFragmentListener {

        void addressAutoCompleted(EditText editText, int code);

        void createRoute();
    }
}
