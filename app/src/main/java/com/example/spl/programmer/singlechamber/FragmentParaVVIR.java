package com.example.spl.programmer.singlechamber;


import static com.example.spl.helper.CommonData.bARC;
import static com.example.spl.helper.CommonData.bAutoMsr;
import static com.example.spl.helper.CommonData.funSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funSetArrayAdapterString;
import static com.example.spl.helper.CommonData.getColor297;
import static com.example.spl.helper.CommonData.mFunAlert;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.mapPW297;
import static com.example.spl.helper.CommonData.mapSlope297;
import static com.example.spl.helper.CommonData.tAmp;
import static com.example.spl.helper.CommonData.tDownTime;
import static com.example.spl.helper.CommonData.tPW;
import static com.example.spl.helper.CommonData.tRef;
import static com.example.spl.helper.CommonData.tSen;
import static com.example.spl.helper.CommonData.tSensorRate;
import static com.example.spl.helper.CommonData.tSlope;
import static com.example.spl.helper.CommonData.tTrigUprRate;
import static com.example.spl.helper.CommonData.tuptime;
import static com.example.spl.programmer.ActivityProgrammer.blnkProgBut;
import static com.example.spl.programmer.ActivityProgrammer.validateCommonData;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.spl.R;
import com.example.spl.helper.CommonData;
import com.example.spl.programmer.ActivityProgrammer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentParaVVIR extends Fragment {
    //common
    Button tab1, tab2, tab3, tab4;
    public static Activity activity;
    String mode;
    LinearLayout mViewVVI;
    LinearLayout mViewPolarity;
    LinearLayout mViewOther;
    LinearLayout mViewRateResponse;

    //rate response
    Spinner spinUptime, spinDownTime, spinSlope;
    public static Spinner UpperRate;
    private ArrayAdapter<Integer> upperrateAdapter;
    private ArrayAdapter<Integer> uptimeAdapter;
    private ArrayAdapter<Integer> downtimeAdapter;
    private ArrayAdapter<Integer> slopeAdapter;
    public static CheckBox checkARC;
    Boolean bTuchcheckARC = true;
    Boolean bTuchSpinRate = false;
    Boolean bTuchSpinUptime = false;
    Boolean bTuchDownTime = false;
    Boolean bTuchSlope = false;

    //other
    public static Spinner spinUpper,spinAutomeas;
    CheckBox checkMri;
RelativeLayout mLayoutAM;
TextView txtAutomeasTxt;
    private ArrayAdapter<String> automeasAdapter;
    private ArrayAdapter<Integer> upperAdapter;

    Boolean bTuchSpinHys = false;
    Boolean bTuchSpinAutomeas = false;
    Boolean bTuchSpinUpper = false;
    Boolean bTuchcheckEnable = false;


    //main
    public static Spinner spinSens;
    public static Spinner spinPw;
    public static Spinner spinRef;
    public static Spinner spinAmp;
    private ArrayAdapter<Double> pwAdapter;
    private ArrayAdapter<Double> senAdapter;
    private ArrayAdapter<Integer> refAdapter;
    private ArrayAdapter<Double> ampAdapter;
    private ArrayAdapter<String> na;
    boolean isFirst = true;
    boolean bTuchSpinAmp = false;
    boolean bTuchSpinPW = false;
    boolean bTuchSpinRef = false;
    boolean bTuchSpinSens = false;

    //polarity
    RadioButton paceBi, paceUni, sensBi, sensUni;
    RadioGroup radioGroupPace;
    RadioGroup radioGroupSensitivity;


    public FragmentParaVVIR() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityProgrammer activityProgrammer = (ActivityProgrammer) getActivity();
        mode = activityProgrammer.getMyData();
        activity = getActivity();
        CommonData.iScreenFlag = 1;
        View v = inflater.inflate(R.layout.fragment_vvir, container, false);

        //common
        tab1 = v.findViewById(R.id.tab1);
        tab2 = v.findViewById(R.id.tab2);
        tab3 = v.findViewById(R.id.tab3);
        tab4 = v.findViewById(R.id.tab4);

        mViewVVI = v.findViewById(R.id.mViewVVI);
        mViewPolarity = v.findViewById(R.id.mViewPolarity);
        mViewOther = v.findViewById(R.id.mViewOther);
        mViewRateResponse = v.findViewById(R.id.mViewRateResponse);

        //main
        spinAmp = v.findViewById(R.id.spinAmp);
        spinSens = v.findViewById(R.id.spinSens);
        spinPw = v.findViewById(R.id.spinPw);
        spinRef = v.findViewById(R.id.spinRef);

        //other
        spinAutomeas = v.findViewById(R.id.spinAutomeas);
        spinUpper = v.findViewById(R.id.spinUpper);
        checkMri = v.findViewById(R.id.checkMri);
        mLayoutAM = v.findViewById(R.id.mLayoutAM);
        txtAutomeasTxt = v.findViewById(R.id.txtAutomeasTxt);

        //polarity
        paceBi = v.findViewById(R.id.paceBi);
        paceUni = v.findViewById(R.id.paceUni);
        sensBi = v.findViewById(R.id.sensBi);
        sensUni = v.findViewById(R.id.sensUni);
        radioGroupPace = v.findViewById(R.id.radioGroupPace);
        radioGroupSensitivity = v.findViewById(R.id.radioGroupSensitivity);

        //rate response
        UpperRate = v.findViewById(R.id.UpperRate);
        spinUptime = v.findViewById(R.id.spinUpTime);
        spinSlope = v.findViewById(R.id.spinSlope);
        spinDownTime = v.findViewById(R.id.spinDownTime);
        checkARC = v.findViewById(R.id.checkARC);


        return v;
    }

    void funInitPolarity(){
        if (mode == "VOO" || mode == "A00" || mode == "V00R" || mode == "A00R") {
            sensUni.setVisibility(View.INVISIBLE);
            sensBi.setVisibility(View.INVISIBLE);
        } else if (mode == "OVO" || mode == "OAO") {
            paceUni.setVisibility(View.INVISIBLE);
            paceBi.setVisibility(View.INVISIBLE);
        } else if (mode == "OOO") {
            paceUni.setVisibility(View.INVISIBLE);
            paceBi.setVisibility(View.INVISIBLE);
            sensUni.setVisibility(View.INVISIBLE);
            sensBi.setVisibility(View.INVISIBLE);
        }

        if (CommonData.iPacePol == 1)
            paceUni.setChecked(true);
        else
            paceBi.setChecked(true);

        if (CommonData.iSenPol == 1)
            sensUni.setChecked(true);
        else
            sensBi.setChecked(true);

        radioGroupPace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position;
                if (checkedId == R.id.paceBi)
                    position = 0;
                else
                    position = 1;
                CommonData.tPacePol = position;
                //P pol +S pol + PW
                CommonData.pacerDataPROG[7] &= 0x7F;
                CommonData.pacerDataPROG[7] = CommonData.tPacePol * 128 + CommonData.pacerDataPROG[7];
                blnkProgBut();
            }
        });

        radioGroupSensitivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position;
                if (checkedId == R.id.sensBi)
                    position = 0;
                else
                    position = 1;
                CommonData.tSenPol = position;
                //P pol +S pol + PW
                CommonData.pacerDataPROG[7] &= 0xBF;
                CommonData.pacerDataPROG[7] = CommonData.tSenPol * 64 + CommonData.pacerDataPROG[7];
                blnkProgBut();
            }
        });
    }

    void funInitOther(){


        if (mode == "VVT" || mode == "AAT" || mode == "VVTR" || mode == "AATR") {
            spinUpper.setVisibility(View.VISIBLE);
            upperAdapter = funSetArrayAdapterInteger(62,getActivity(), Arrays.asList(CommonData.uprArray297), tTrigUprRate, (byte) 'U');
            spinUpper.setAdapter(upperAdapter);
            spinUpper.setSelection(tTrigUprRate);
        }else {
            spinUpper.setAdapter(null);
            spinUpper.setVisibility(View.GONE);
        }





        System.out.println("<><><><>@### " + bAutoMsr);

        if (bAutoMsr) {
            automeasAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.auto), 0, (byte) 0);
            spinAutomeas.setAdapter(automeasAdapter);
            spinAutomeas.setSelection(0);
        } else {
            automeasAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.auto), 1, (byte) 0);
            spinAutomeas.setAdapter(automeasAdapter);
            spinAutomeas.setSelection(1);
        }

        if (CommonData.bMRI)
            checkMri.setChecked(true);
        else
            checkMri.setChecked(false);


        checkMri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CommonData.tbMRI = b;
                // Sensitivity AM and MRI
                CommonData.pacerDataPROG[11] &= 0xBF;
                if (CommonData.tbMRI)
                    CommonData.pacerDataPROG[11] = CommonData.pacerDataPROG[11] | 0x40;
            }
        });




        if (mode == "VOO" || mode == "AOO" || mode == "VOOR" || mode == "AOOR" || mode == "VVI" || mode == "AAI" || mode == "OVO" || mode == "OAO" || mode == "VVIR" || mode == "AAIR" || mode == "OOO") {
            spinUpper.setVisibility(View.INVISIBLE);
        } else if (mode == "VOO" || mode == "AOO" || mode == "OVO" || mode == "OAO" || mode == "VVIR" || mode == "AAIR" || mode == "VOOR" || mode == "AOOR" || mode == "VVTR" || mode == "AATR" || mode == "OOO") {

        }
    }

    void funInitMain() {


        List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
        ampAdapter = CommonData.funSetArrayAdapterDouble(26,getActivity(), keysAMP, tAmp, (byte) 'A');
        spinAmp.setAdapter(ampAdapter);

        List<Double> keysPW = new ArrayList<>(mapPW297.keySet());
        pwAdapter = CommonData.funSetArrayAdapterDouble(27,getActivity(), keysPW, tPW, (byte) 'W');
        spinPw.setAdapter(pwAdapter);

        senAdapter = CommonData.funSetArrayAdapterDouble(28,getActivity(), Arrays.asList(CommonData.senArray297), tSen, (byte) 0);
        spinSens.setAdapter(senAdapter);

        refAdapter = CommonData.funSetArrayAdapterInteger(68,getActivity(), Arrays.asList(CommonData.refArray297), tRef, (byte) 'F');
        spinRef.setAdapter(refAdapter);

        spinPw.setSelection(tPW);
        spinAmp.setSelection(tAmp);
        spinSens.setSelection(tSen);
        spinRef.setSelection(tRef);


        if (mode == "VOO" || mode == "A00" || mode == "A00R" || mode == "A00R") {
            na = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_item, CommonData.na);
            spinSens.setAdapter(na);
            spinRef.setAdapter(na);

        } else if (mode == "OVO" || mode == "OAO") {
            spinAmp.setVisibility(View.INVISIBLE);
            spinPw.setVisibility(View.INVISIBLE);
        } else if (mode == "OOO") {
            spinAmp.setVisibility(View.INVISIBLE);
            spinPw.setVisibility(View.INVISIBLE);
            spinSens.setVisibility(View.INVISIBLE);
            spinRef.setVisibility(View.INVISIBLE);
        }
    }

    void funInitRateResponse(){
        upperrateAdapter = funSetArrayAdapterInteger(69,getActivity(), Arrays.asList(CommonData.uprArray297), tSensorRate, (byte) 'C');
        UpperRate.setAdapter(upperrateAdapter);
        UpperRate.setSelection(tSensorRate);

        uptimeAdapter = funSetArrayAdapterInteger(70,getActivity(), Arrays.asList(CommonData.upTimeArray), tuptime, (byte) 0);
        spinUptime.setAdapter(uptimeAdapter);

        downtimeAdapter = funSetArrayAdapterInteger(71,getActivity(), Arrays.asList(CommonData.dnTimeArray), tDownTime, (byte) 0);
        spinDownTime.setAdapter(downtimeAdapter);

        slopeAdapter = funSetArrayAdapterInteger(72,getActivity(), new ArrayList<>(mapSlope297.keySet()), tSlope, (byte) 0);
        spinSlope.setAdapter(slopeAdapter);

        spinSlope.setSelection(tSlope);
        spinDownTime.setSelection(tDownTime);
        spinUptime.setSelection(tuptime);


        if (bARC)
            checkARC.setChecked(true);
        else
            checkARC.setChecked(false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        highlightButton(tab1);
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewVVI.setVisibility(View.VISIBLE);
                mViewPolarity.setVisibility(View.GONE);
                mViewOther.setVisibility(View.GONE);
                mViewRateResponse.setVisibility(View.GONE);
                highlightButton(tab1);
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewVVI.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.VISIBLE);
                mViewOther.setVisibility(View.GONE);
                mViewRateResponse.setVisibility(View.GONE);
                highlightButton(tab2);
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewVVI.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.GONE);
                mViewOther.setVisibility(View.VISIBLE);
                mViewRateResponse.setVisibility(View.GONE);
                if (CommonData.tAmp < 16 || CommonData.tAmp > 28) {
                    CommonData.bAutoMsr = false;
                    CommonData.tbAutoMsr = false;
                    spinAutomeas.setEnabled(false);
                    spinAutomeas.setAlpha(0.5f);
                    spinAutomeas.setVisibility(View.GONE);
                    txtAutomeasTxt.setVisibility(View.VISIBLE);
                    // Sensitivity  AM and MRI
                    CommonData.pacerDataPROG[11] &= 0x7F;
                } else {
                    spinAutomeas.setVisibility(View.VISIBLE);
                    txtAutomeasTxt.setVisibility(View.GONE);
                    spinAutomeas.setEnabled(true);
                    spinAutomeas.setAlpha(0);
                }
                highlightButton(tab3);
            }
        });
        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewVVI.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.GONE);
                mViewOther.setVisibility(View.GONE);
                mViewRateResponse.setVisibility(View.VISIBLE);
                highlightButton(tab4);
            }
        });

        funInitMain();
        funInitOther();
        funInitPolarity();
        funInitRateResponse();

        mFunSpinnerOnItemClick();
     /*   if (CommonData.isFirstVviMain) {
            validateParaMain();
            validateParaOther();
            resetVariableSingleChamber("main");
        }*/

    }

    public void onPause() {
        super.onPause();
        // Perform cleanup or save data when the fragment is being paused
    }

    @Override
    public void onResume() {
        super.onResume();
        // Perform initialization or reload data when the fragment is being resumed
    }

    public static void validateParaMain(int a) {
        System.out.println("<><>@@$Main "+a);
        CommonData.isFirstVviMain = false;
        int iColor;
        iColor = getColor297(CommonData.tAmp, (byte) 'A', activity);
        if (spinAmp.getSelectedView() != null)
            ((TextView) spinAmp.getSelectedView()).setTextColor(iColor);

        iColor = getColor297(CommonData.tPW, (byte) 'W', activity);
        if (spinPw.getSelectedView() != null)
            ((TextView) spinPw.getSelectedView()).setTextColor(iColor);

        iColor = getColor297(CommonData.tRef, (byte) 'F', activity);
        if (spinRef.getSelectedView() != null)
            ((TextView) spinRef.getSelectedView()).setTextColor(iColor);

        iColor = getColor297(tTrigUprRate, (byte) 'U', activity);
        if (spinUpper.getSelectedView() != null)
            ((TextView) spinUpper.getSelectedView()).setTextColor(iColor);
//other


        if (CommonData.tAmp < 16 || CommonData.tAmp > 28) {
            CommonData.bAutoMsr = false;
            CommonData.tbAutoMsr = false;
            spinAutomeas.setEnabled(false);
            spinAutomeas.setAlpha(0.5f);
            // Sensitivity  AM and MRI
            CommonData.pacerDataPROG[11] &= 0x7F;
            if ((CommonData.iAmp != CommonData.tAmp))
                Toast.makeText(activity, "AM will be ON between 2.5V and 4.5V", Toast.LENGTH_SHORT).show();
        } else {
            spinAutomeas.setEnabled(true);
            spinAutomeas.setAlpha(0);
        }

        //rate response
        iColor = getColor297(CommonData.tSensorRate, (byte) 'C', activity);
        if (UpperRate.getSelectedView() != null)
            ((TextView) UpperRate.getSelectedView()).setTextColor(iColor);

        //If Ref <260 then ARC is not valid ,so update control word also
        if (CommonData.tMode == 8 || CommonData.tMode == 10 || CommonData.tMode == 11 || CommonData.tMode == 13) {
            if (CommonData.tRef < 4) {
                if ((CommonData.bARC || CommonData.tbARC)) {
                    Toast.makeText(activity, "If Refractory < 260 ms then ARC is not valid", Toast.LENGTH_SHORT).show();
                }

                CommonData.bARC = false;
                CommonData.tbARC = false;
                CommonData.pacerDataPROG[8] &= 0xBF;
                checkARC.setEnabled(false);
            } else
                checkARC.setEnabled(true);

            checkARC.setChecked(CommonData.bARC);
        }

        if (CommonData.tMode == 8 || CommonData.tMode == 10 || CommonData.tMode == 11 || CommonData.tMode == 13) {
            if (CommonData.uprArray297[tSensorRate] < 120) {

                CommonData.bARC = false;
                CommonData.tbARC = false;
                CommonData.pacerDataPROG[8] &= 0xBF;
                checkARC.setEnabled(false);
            } else
                checkARC.setEnabled(true);

            checkARC.setChecked(CommonData.bARC);
        }
    }
   /* public static void validateParaOther(int a) {
        System.out.println("<><>@@$Other "+a);
        isFirstVviOther = false;
        int iColor;

    }
    public static void validateParaRateResponse(int a) {
        System.out.println("<><>@@$RateResponse "+a);
        isFirstVviRateResponse = false;
        int iColor;

    }*/


    public void mFunSpinnerOnItemClick() {
        //rate response

        checkARC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println("<><>@@$ 1");
                    CommonData.tbARC = b;
                    //'Hyst On/OFF + ARC + AMP
                    CommonData.pacerDataPROG[8] &= 0xBF;
                    if (CommonData.tbARC)
                        CommonData.pacerDataPROG[8] |= 0x40;
                    //blnkProgBut();// rate respoce click its shwoing error
                }
        });

        UpperRate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinRate = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        UpperRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinRate) {
                    bTuchSpinRate = false;
                    CommonData.bSpinClick = true;
                    CommonData.tSensorRate = i;
                    // Target Upper Rate (R modes)
                    CommonData.pacerDataPROG[14] = CommonData.tSensorRate + 24;
                    validateParaMain(1);
                    validateCommonData();
                    blnkProgBut();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinUptime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinUptime = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinUptime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinUptime) {
                    bTuchSpinUptime = false;
                    CommonData.bSpinClick = true;
                    tuptime = i;
                    // MODE + UpTime
                    CommonData.pacerDataPROG[6] &= 0xF8;
                    CommonData.pacerDataPROG[6] |= tuptime;
                    blnkProgBut();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinDownTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchDownTime = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinDownTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchDownTime) {
                    bTuchDownTime = false;
                    CommonData.bSpinClick = true;
                    tDownTime = i;
                    // Down Time
                    CommonData.pacerDataPROG[16] = tDownTime;
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinSlope.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSlope = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinSlope.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSlope) {
                    bTuchSlope = false;
                    CommonData.bSpinClick = true;
                    tSlope = i;
                    CommonData.pacerDataPROG[15] = mapSlope297.get(spinSlope.getSelectedItem());
                    blnkProgBut();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

         //other


        mLayoutAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonData.tAmp < 16 || CommonData.tAmp > 28) {
                    bTuchSpinAutomeas = false;
                    mFunAlert(activity, "Message", "AM will be ON between 2.5V and 4.5V");
                    //Toast.makeText(activity, "AM will be ON between 2.5V and 4.7V", Toast.LENGTH_SHORT).show();
                }
            }
        });
        spinAutomeas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinAutomeas = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinAutomeas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinAutomeas) {
                    bTuchSpinAutomeas = false;
                    CommonData.bSpinClick = true;
                    if (spinAutomeas.getSelectedItem().toString().equals("ON"))
                        CommonData.tbAutoMsr = true;
                    else
                        CommonData.tbAutoMsr = false;
                    // Sensitivity  AM and MRI
                    CommonData.pacerDataPROG[11] &= 0x7F;
                    if (CommonData.tbAutoMsr)
                        CommonData.pacerDataPROG[11] = CommonData.pacerDataPROG[11] | 0x80;
                    blnkProgBut();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinUpper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinUpper = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinUpper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinUpper) {
                    bTuchSpinUpper = false;
                    CommonData.bSpinClick = true;
                    tTrigUprRate = i;
                    CommonData.pacerDataPROG[13] = tTrigUprRate + 24;
                    validateParaMain(8);
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        //main
        spinAmp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinAmp = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinAmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinAmp) {
                    bTuchSpinAmp = false;
                    CommonData.bSpinClick = true;
                    tAmp = i;
                    CommonData.pacerDataPROG[8] &= 0xC0;
                    CommonData.pacerDataPROG[8] += mapAMP297.get(spinAmp.getSelectedItem());
                    System.out.println("<><><><>@@VVI AMP");
                    validateParaMain(11);
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinPw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinPW = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinPw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinPW) {
                    bTuchSpinPW = false;
                    CommonData.bSpinClick = true;
                    tPW = i;
                    CommonData.pacerDataPROG[7] &= 0xC0;
                    CommonData.pacerDataPROG[7] += mapPW297.get(spinPw.getSelectedItem());
                    System.out.println("<><><><>@@VVI PW");
                    validateParaMain(12);
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinRef.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinRef = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinRef) {
                    bTuchSpinRef = false;
                    CommonData.bSpinClick = true;
                    tRef = i;
                    CommonData.pacerDataPROG[10] &= 0xE0;
                    CommonData.pacerDataPROG[10] += CommonData.tRef;
                    System.out.println("<><><><>@@VVI RE");
                    validateParaMain(13);
                    validateCommonData();
                    blnkProgBut();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinSens.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinSens = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinSens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinSens) {
                    bTuchSpinSens = false;
                    CommonData.bSpinClick = true;
                    tSen = i;
                    CommonData.pacerDataPROG[11] &= 0xC0;
                    CommonData.pacerDataPROG[11] += CommonData.tSen;
                    blnkProgBut();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void highlightButton(Button button) {
        resetButtonHighlight();
        button.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
    }

    private void resetButtonHighlight() {
        tab1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        tab2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        tab3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        tab4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }
}