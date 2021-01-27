package com.example.journeyMobile.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.journeyMobile.R;

public class HomepageDateChosenFragment extends Fragment {

    private static final String TAG = "HomepageDateChosenFragment";

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homepage_date_chosen_fragment, container, false);
        return view;
    }
}
