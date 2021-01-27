package com.example.journeyMobile.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.journeyMobile.R;
import com.example.journeyMobile.controller.map.PlanListFragmentController;
import com.example.journeyMobile.view.adapter.ItemMoveCallback;
import com.example.journeyMobile.view.adapter.PlanListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlanListFragment.OnPlanListFragmentListener} interface
 * to handle interaction events.
 */
public class PlanListFragment extends Fragment {


    private OnPlanListFragmentListener mListener;
    private ViewHolder viewHolder;
    private PlanListFragmentController controller;

    public PlanListFragment() {
        // Required empty public constructor
    }

    public PlanListFragmentController getController() {
        return controller;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlanListFragmentListener) {
            mListener = (OnPlanListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString( )
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan_list,container,false);
        viewHolder = new ViewHolder(view);

        controller = new PlanListFragmentController(this, viewHolder, mListener);
        setAction();

        return view;
    }

    private void setAction() {

    }


    @Override
    public void onDetach() {
        super.onDetach( );
        mListener = null;
    }


    public class ViewHolder {
        public RecyclerView plansRecyclerView;
        public PlanListAdapter planListAdapter;
        public TextView dateTV;

        private ViewHolder(View view) {
            dateTV = view.findViewById(R.id.dailyPlan_date);

            plansRecyclerView = view.findViewById(R.id.planlist_recyclerview);
            plansRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            planListAdapter = new PlanListAdapter();
            planListAdapter.setToActivityListener(mListener);
            plansRecyclerView.setAdapter(planListAdapter);

            ItemMoveCallback callback = new ItemMoveCallback(planListAdapter);
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(plansRecyclerView);
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
    public interface OnPlanListFragmentListener {
        void refreshData();
    }
}
