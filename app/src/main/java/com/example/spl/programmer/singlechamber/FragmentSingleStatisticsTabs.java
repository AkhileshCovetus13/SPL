package com.example.spl.programmer.singlechamber;

import static com.example.spl.helper.CommonData.flagSubFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.spl.programmer.ActivityProgrammer;
import com.example.spl.R;

public class FragmentSingleStatisticsTabs extends Fragment {

String mode;
    public static FragmentManager fm;
    public static int pos;

    public FragmentSingleStatisticsTabs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityProgrammer activity = (ActivityProgrammer) getActivity();
        mode = activity.getMyData();
        View v= inflater.inflate(R.layout.fragment_statistics_tabs, container, false);


        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flagSubFragment = 1;
        fm =getChildFragmentManager();
        fm.beginTransaction().replace(R.id.frame_vvi_tabs, new StatisticsSingleFragment(),"StatisticsFragment").commit();
        fm.beginTransaction().addToBackStack(null);
    }

    public static void callGraph(){
        fm.beginTransaction().replace(R.id.frame_vvi_tabs, new StatisticsSingleGraphFragment(),"StatisticsGraphFragment").commit();
        fm.beginTransaction().addToBackStack(null);
    }

}