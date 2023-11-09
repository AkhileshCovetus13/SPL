package com.example.spl.programmer.dualchamber;

import static com.example.spl.programmer.ActivityProgrammer.closeThr;
import static com.example.spl.helper.CommonData.flagSubFragment;
import static com.example.spl.helper.CommonData.funMediumSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funMediumSetArrayAdapterString;
import static com.example.spl.helper.CommonData.iAAmp;
import static com.example.spl.helper.CommonData.iAPW;
import static com.example.spl.helper.CommonData.iASen;
import static com.example.spl.helper.CommonData.iAmp;
import static com.example.spl.helper.CommonData.iPAVI;
import static com.example.spl.helper.CommonData.iPW;
import static com.example.spl.helper.CommonData.iRate;
import static com.example.spl.helper.CommonData.iSAVI;
import static com.example.spl.helper.CommonData.iSen;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.rateArray747;

import android.app.Activity;
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

import com.example.spl.R;
import com.example.spl.helper.CommonData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentThresholdTestDual extends Fragment {
    Button tabCapture, tabSense;
    String mode;
    Spinner mSpinRate;
    Spinner mSpinMode;
    public static Spinner mSpinAmp;
    public static Spinner mSpinPw;
    Spinner mSpinPaceDelay;
    public static Spinner mSpinSense;
    Spinner mSpinSenseDelay;
    TextView mStartValueSense;
    TextView mStartValueCapture;
    TextView mTxtPwStartValue;
    public static LinearLayout mLayoutAmp, mLayoutPw;
    ArrayAdapter<String> modeSpinnerArrayAdapter;
    ArrayAdapter<Integer> rateSpinnerArrayAdapter;
    ArrayAdapter<Integer> aviPaceSpinnerArrayAdapter;
    ArrayAdapter<Integer> aviSensSpinnerArrayAdapter;
    public static ArrayAdapter<Double> ampSpinnerArrayAdapter;
    public static ArrayAdapter<Double> senseSpinnerArrayAdapter;
    public static ArrayAdapter<Double> pwSpinnerArrayAdapter;
    List<String> keys;
    int mIntCapture = 1;
    int mIntSense = 0;
    public static Activity activity;
    ImageView mImgClose;

    TextView mTxtCurrentMode;
    public static TextView mTxtCurrentAmp;
    TextView mTxtCurrentPaceAv;
    TextView mTxtCurrentRate;
    public static TextView mTxtCurrentSense;
    TextView mTxtCurrentSenseAv;
    public static TextView mTxtCurrentPW;


    public FragmentThresholdTestDual() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_threshold_test_dual, container, false);
        activity = getActivity();
        mSpinRate = v.findViewById(R.id.mSpinRate);
        mSpinMode = v.findViewById(R.id.mSpinMode);
        mSpinAmp = v.findViewById(R.id.mSpinAmp);
        mSpinPw = v.findViewById(R.id.mSpinPw);
        mSpinPaceDelay = v.findViewById(R.id.mSpinPaceDelay);
        mSpinSense = v.findViewById(R.id.mSpinSense);
        mSpinSenseDelay = v.findViewById(R.id.mSpinSenseDelay);
        mLayoutAmp = v.findViewById(R.id.mLayoutAmp);
        mLayoutPw = v.findViewById(R.id.mLayoutPw);
        tabCapture = v.findViewById(R.id.tabCapture);
        tabSense = v.findViewById(R.id.tabSense);
        mStartValueSense = v.findViewById(R.id.mStartValueSense);
        mStartValueCapture = v.findViewById(R.id.mStartValueCapture);
        mTxtPwStartValue = v.findViewById(R.id.mTxtPwStartValue);
        mImgClose = v.findViewById(R.id.mImgClose);
        mTxtCurrentMode = v.findViewById(R.id.mTxtCurrentMode);
        mTxtCurrentAmp = v.findViewById(R.id.mTxtCurrentAmp);
        mTxtCurrentPaceAv = v.findViewById(R.id.mTxtCurrentPaceAv);
        mTxtCurrentRate = v.findViewById(R.id.mTxtCurrentRate);
        mTxtCurrentSense = v.findViewById(R.id.mTxtCurrentSense);
        mTxtCurrentSenseAv = v.findViewById(R.id.mTxtCurrentSenseAv);
        mTxtCurrentPW = v.findViewById(R.id.mTxtCurrentPW);




        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flagSubFragment = 1;


        tabCapture.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
        tabSense.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        modeSpinnerArrayAdapter = funMediumSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.modeArrayThr747), 0, (byte) 0);
        mSpinMode.setAdapter(modeSpinnerArrayAdapter);
        mSpinMode.setSelection(0);
        mTxtCurrentMode.setText(mSpinMode.getSelectedItem()+"");

        rateSpinnerArrayAdapter = funMediumSetArrayAdapterInteger(getActivity(), Arrays.asList(rateArray747), iRate, (byte) 'R');
        mSpinRate.setAdapter(rateSpinnerArrayAdapter);
        mSpinRate.setSelection(iRate);
        mTxtCurrentRate.setText(mSpinRate.getSelectedItem()+"");

        aviPaceSpinnerArrayAdapter = CommonData.funMediumSetArrayAdapterInteger(getActivity(), Arrays.asList(CommonData.aviArray), iPAVI, (byte) 'I');
        mSpinPaceDelay.setAdapter(aviPaceSpinnerArrayAdapter);
        mSpinPaceDelay.setSelection(iPAVI);
        mTxtCurrentPaceAv.setText(mSpinPaceDelay.getSelectedItem()+"");

        aviSensSpinnerArrayAdapter = CommonData.funSetArrayAdapterInteger(7,getActivity(), Arrays.asList(CommonData.aviArray), iSAVI, (byte) 'J');
        mSpinSenseDelay.setAdapter(aviSensSpinnerArrayAdapter);
        mSpinSenseDelay.setSelection(iSAVI);
        mTxtCurrentSenseAv.setText(mSpinSenseDelay.getSelectedItem()+"");


        mImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeThr();
            }
        });


        tabCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntCapture = 1;
                mIntSense = 0;
                tabCapture.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                tabSense.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mStartValueSense.setVisibility(View.GONE);
                mStartValueCapture.setVisibility(View.VISIBLE);
                mTxtPwStartValue.setVisibility(View.VISIBLE);
            }
        });

        tabSense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntCapture = 0;
                mIntSense = 1;
                tabCapture.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tabSense.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                mStartValueSense.setVisibility(View.VISIBLE);
                mStartValueCapture.setVisibility(View.GONE);
                mTxtPwStartValue.setVisibility(View.GONE);
            }
        });

        funUpdateViews(1);

    }

    public static void funUpdateViews(int op){
        if(op==0){
            //Atrium
            List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
            ampSpinnerArrayAdapter = CommonData.funMediumSetArrayAdapterDouble(activity, keysAMP, iAAmp, (byte) 'A');
            mSpinAmp.setAdapter(ampSpinnerArrayAdapter);
            mSpinAmp.setSelection(iAAmp);
            mTxtCurrentAmp.setText(mSpinAmp.getSelectedItem()+"");

            senseSpinnerArrayAdapter = CommonData.funMediumSetArrayAdapterDouble(activity, Arrays.asList(CommonData.aSenArray), iASen, (byte) 0);
            mSpinSense.setAdapter(senseSpinnerArrayAdapter);
            mSpinSense.setSelection(iASen);
            mTxtCurrentSense.setText(mSpinSense.getSelectedItem()+"");

            pwSpinnerArrayAdapter = CommonData.funMediumSetArrayAdapterDouble(activity, Arrays.asList(CommonData.pwArray747), iAPW, (byte) 'P');
            mSpinPw.setAdapter(pwSpinnerArrayAdapter);
            mSpinPw.setSelection(iAPW);
            mTxtCurrentPW.setText(mSpinPw.getSelectedItem()+"");
        }else {

            List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
            ampSpinnerArrayAdapter = CommonData.funMediumSetArrayAdapterDouble(activity, keysAMP, iAmp, (byte) 'V');
            mSpinAmp.setAdapter(ampSpinnerArrayAdapter);
            mSpinAmp.setSelection(iAmp);
            mTxtCurrentAmp.setText(mSpinAmp.getSelectedItem()+"");

            senseSpinnerArrayAdapter = CommonData.funMediumSetArrayAdapterDouble(activity, Arrays.asList(CommonData.senArray297), iSen, (byte) 0);
            mSpinSense.setAdapter(senseSpinnerArrayAdapter);
            mSpinSense.setSelection(iSen);
            mTxtCurrentSense.setText(mSpinSense.getSelectedItem()+"");

            pwSpinnerArrayAdapter = CommonData.funMediumSetArrayAdapterDouble(activity, Arrays.asList(CommonData.pwArray747), iPW, (byte) 'W');
            mSpinPw.setAdapter(pwSpinnerArrayAdapter);
            mSpinPw.setSelection(iPW);
            mTxtCurrentPW.setText(mSpinPw.getSelectedItem()+"");

        }

    }

}