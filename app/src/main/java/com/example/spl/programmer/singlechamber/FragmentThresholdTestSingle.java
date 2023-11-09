package com.example.spl.programmer.singlechamber;

import static com.example.spl.programmer.ActivityProgrammer.closeThr;
import static com.example.spl.helper.CommonData.flagSubFragment;
import static com.example.spl.helper.CommonData.funMediumSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funMediumSetArrayAdapterString;
import static com.example.spl.helper.CommonData.iAmp;
import static com.example.spl.helper.CommonData.iMode;
import static com.example.spl.helper.CommonData.iPW;
import static com.example.spl.helper.CommonData.iRate;
import static com.example.spl.helper.CommonData.iSen;
import static com.example.spl.helper.CommonData.map297_8820;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.mapPW297;
import static com.example.spl.helper.CommonData.rateArray;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.spl.programmer.ActivityProgrammer;
import com.example.spl.R;
import com.example.spl.helper.CommonData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentThresholdTestSingle extends Fragment {
    Button tabCapture, tabSense;
    String mode;
    Spinner mSpinRate;
    Spinner mSpinMode;
    Spinner mSpinAmp;
    Spinner mSpinPw;
    Spinner mSpinSense;
    TextView mStartValueSense;
    TextView mStartValueCapture;
    TextView mStartValuePw;
    public static LinearLayout mLayoutAmp, mLayoutPw;
    ArrayAdapter<String> modeSpinnerArrayAdapter;
    ArrayAdapter<Integer> rateSpinnerArrayAdapter;
    ArrayAdapter<Double> mDoubleArrayAdapterAMP;
    ArrayAdapter<Double> mDoubleArrayAdapterSense;
    ArrayAdapter<Double> mDoubleArrayAdapterPW;
    List<String> keys;
    int mIntCapture = 0;

    TextView mTxtModeCurrent;
    TextView mTxtAmpCurrent;
    TextView mTxtRateCurrent;
    TextView mTxtSenseCurrent;
    TextView mTxtPwCurrent;
    ImageView mImgClose;

    public FragmentThresholdTestSingle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityProgrammer activity = (ActivityProgrammer) getActivity();
        mode = activity.getMyData();
        View v = inflater.inflate(R.layout.fragment_threshold_test_single, container, false);

        mSpinRate = v.findViewById(R.id.mSpinRate);
        mSpinMode = v.findViewById(R.id.mSpinMode);
        mSpinAmp = v.findViewById(R.id.mSpinAmp);
        mSpinPw = v.findViewById(R.id.mSpinPw);
        mSpinSense = v.findViewById(R.id.mSpinSense);
        mLayoutAmp = v.findViewById(R.id.mLayoutAmp);
        mLayoutPw = v.findViewById(R.id.mLayoutPw);
        tabCapture = v.findViewById(R.id.tabCapture);
        tabSense = v.findViewById(R.id.tabSense);
        mStartValueSense = v.findViewById(R.id.mStartValueSense);
        mStartValueCapture = v.findViewById(R.id.mStartValueCapture);
        mTxtModeCurrent = v.findViewById(R.id.mTxtModeCurrent);
        mTxtAmpCurrent = v.findViewById(R.id.mTxtAmpCurrent);
        mTxtRateCurrent = v.findViewById(R.id.mTxtRateCurrent);
        mTxtSenseCurrent = v.findViewById(R.id.mTxtSenseCurrent);
        mTxtPwCurrent = v.findViewById(R.id.mTxtPwCurrent);
        mStartValuePw = v.findViewById(R.id.mStartValuePw);
        mImgClose = v.findViewById(R.id.mImgClose);

        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flagSubFragment = 1;

        tabCapture.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
        tabSense.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        if(mIntCapture == 0){
            tabCapture.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
            tabSense.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            mStartValueSense.setVisibility(View.GONE);
            mStartValueCapture.setVisibility(View.VISIBLE);
        }

        mImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeThr();
            }
        });

        tabCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntCapture = 0;
                tabCapture.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                tabSense.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mStartValueSense.setVisibility(View.GONE);
                mStartValueCapture.setVisibility(View.VISIBLE);
                mStartValuePw.setVisibility(View.VISIBLE);
            }
        });

        tabSense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntCapture = 1;
                tabCapture.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tabSense.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                mStartValueSense.setVisibility(View.VISIBLE);
                mStartValueCapture.setVisibility(View.GONE);
                mStartValuePw.setVisibility(View.GONE);
            }
        });

        modeSpinnerArrayAdapter = funMediumSetArrayAdapterString(getActivity(), new ArrayList<>(map297_8820.keySet()), iMode, (byte) 0);
        mSpinMode.setAdapter(modeSpinnerArrayAdapter);
        mSpinMode.setSelection(iMode);
        mTxtModeCurrent.setText(mSpinMode.getSelectedItem()+"");

        rateSpinnerArrayAdapter = funMediumSetArrayAdapterInteger(getActivity(), Arrays.asList(rateArray), iRate, (byte) 'R');
        mSpinRate.setAdapter(rateSpinnerArrayAdapter);
        mSpinRate.setSelection(iRate);
        mTxtRateCurrent.setText(mSpinRate.getSelectedItem()+"");

        List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
        mDoubleArrayAdapterAMP = CommonData.funSmallSetArrayAdapterDouble(getActivity(), keysAMP, iAmp, (byte) 'A');
        mSpinAmp.setAdapter(mDoubleArrayAdapterAMP);
        mSpinAmp.setSelection(iAmp);
        mTxtAmpCurrent.setText(mSpinAmp.getSelectedItem()+"");

        mDoubleArrayAdapterSense = CommonData.funSmallSetArrayAdapterDouble(getActivity(), Arrays.asList(CommonData.senArray297), iSen, (byte) 0);
        mSpinSense.setAdapter(mDoubleArrayAdapterSense);
        mSpinSense.setSelection(iSen);
        mTxtSenseCurrent.setText(mSpinSense.getSelectedItem()+"");

        List<Double> keysPW = new ArrayList<>(mapPW297.keySet());
        mDoubleArrayAdapterPW = CommonData.funSmallSetArrayAdapterDouble(getActivity(), keysPW, iPW, (byte) 'W');
        mSpinPw.setAdapter(mDoubleArrayAdapterPW);
        mTxtPwCurrent.setText(mSpinPw.getSelectedItem()+"");

    }

}