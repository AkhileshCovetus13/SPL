package com.example.spl.programmer.dualchamber;


import static com.example.spl.helper.CommonData.bARC;
import static com.example.spl.helper.CommonData.bAutoMsr;
import static com.example.spl.helper.CommonData.bHystON;
import static com.example.spl.helper.CommonData.bHystSrchON;
import static com.example.spl.helper.CommonData.funSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funSetArrayAdapterString;
import static com.example.spl.helper.CommonData.getColor747;
import static com.example.spl.helper.CommonData.iAmp;
import static com.example.spl.helper.CommonData.iDownTime;
import static com.example.spl.helper.CommonData.iPW;
import static com.example.spl.helper.CommonData.iRef;
import static com.example.spl.helper.CommonData.iScreenFlag;
import static com.example.spl.helper.CommonData.iSen;
import static com.example.spl.helper.CommonData.iSlope;
import static com.example.spl.helper.CommonData.iTrigUprRate;
import static com.example.spl.helper.CommonData.ihystVAL;
import static com.example.spl.helper.CommonData.iuptime;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.mapPW297;
import static com.example.spl.helper.CommonData.mapSlope297;
import static com.example.spl.helper.CommonData.pwArray747;
import static com.example.spl.helper.CommonData.tAmp;
import static com.example.spl.helper.CommonData.tDownTime;
import static com.example.spl.helper.CommonData.tPW;
import static com.example.spl.helper.CommonData.tRef;
import static com.example.spl.helper.CommonData.tSen;
import static com.example.spl.helper.CommonData.tSensorRate;
import static com.example.spl.helper.CommonData.tSlope;
import static com.example.spl.helper.CommonData.tTrigUprRate;
import static com.example.spl.helper.CommonData.tbAutoMsr;
import static com.example.spl.helper.CommonData.thystVAL;
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

public class FragmentDualVVIR extends Fragment {

    //common
    Button tab1, tab2, tab3, tab4;
    public static Activity activity;
    String mode;
    LinearLayout mViewVVI;
    LinearLayout mViewPolarity;
    LinearLayout mViewOther;
    LinearLayout mViewRateResponse;

    // main
    public static Spinner spinSens;
    public static Spinner spinPw;
    public static Spinner spinRef;
    public static Spinner spinAmp;

    private ArrayAdapter<Double> pwAdapter;
    private ArrayAdapter<Double> senAdapter;
    private ArrayAdapter<Integer> refAdapter;
    private ArrayAdapter<Double> ampAdapter;
    private ArrayAdapter<String> na;
    boolean bTuchSpinAmp = false;
    boolean bTuchSpinPw = false;
    boolean bTuchSpinRef = false;
    boolean bTuchSpinSens = false;


    //polarity
    RadioButton paceBi, paceUni, sensBi, sensUni;
    RadioGroup radioGroupPace;
    RadioGroup radioGroupSensitivity;

    //other
    public static Spinner spinHys, spinAutomeas, spinUpper;
    CheckBox checkSearch;
    CheckBox checkEnable;
    CheckBox checkMri;
    boolean bCheckEnable = false;
    boolean bCheckMri = false;
    boolean bCheckSearch = false;
    boolean bTuchSpinHys = false;
    boolean bTuchSpinAutomeas = false;
    boolean bTuchSpinUpper = false;

    private ArrayAdapter<String> hysAdapter;
    private ArrayAdapter<String> automeasAdapter;
    private ArrayAdapter<Integer> upperAdapter;

    //Rate Response
    Spinner spinUptime, spinDownTime, spinSlope;
    public static Spinner UpperRate;
    private ArrayAdapter<Integer> upperrateAdapter;
    private ArrayAdapter<Integer> uptimeAdapter;
    private ArrayAdapter<Integer> downtimeAdapter;
    private ArrayAdapter<Integer> slopeAdapter;
    public static CheckBox checkARC;

    boolean bTuchcheckARC = false;
    boolean bTuchSpinUpperRate = false;
    boolean bTuchSpinUptime = false;
    boolean bTuchSpinDownTime = false;
    boolean bTuchSpinSlope = false;


    public FragmentDualVVIR() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityProgrammer activityProgrammer = (ActivityProgrammer) getActivity();
        mode = activityProgrammer.getMyData();
        activity = getActivity();
        iScreenFlag = 12;
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

        //polarity
        paceBi = v.findViewById(R.id.paceBi);
        paceUni = v.findViewById(R.id.paceUni);
        sensBi = v.findViewById(R.id.sensBi);
        sensUni = v.findViewById(R.id.sensUni);
        radioGroupPace = v.findViewById(R.id.radioGroupPace);
        radioGroupSensitivity = v.findViewById(R.id.radioGroupSensitivity);

        //other
        spinHys = v.findViewById(R.id.spinHys);
        spinAutomeas = v.findViewById(R.id.spinAutomeas);
        spinUpper = v.findViewById(R.id.spinUpper);
        checkSearch = v.findViewById(R.id.checkSearch);
        checkEnable = v.findViewById(R.id.checkEnable);
        checkMri = v.findViewById(R.id.checkMri);

        //rate response
        UpperRate = v.findViewById(R.id.UpperRate);
        spinUptime = v.findViewById(R.id.spinUpTime);
        spinSlope = v.findViewById(R.id.spinSlope);
        spinDownTime = v.findViewById(R.id.spinDownTime);
        checkARC = v.findViewById(R.id.checkARC);

        return v;
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
        funInitPolarity();
        funInitOther();
        funInitRateResponse();

        mFunSpinnerOnItemClick();
      /*  if (isFirstDualVviMain) {
            isFirstDualVviMain = false;
            validateParaMain();
            validateParaOther();
            validateParaRateResponse();
        }*/
    }

    void funInitOther() {
        hysAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.hystArray297), 0, (byte) 'H');
        spinHys.setAdapter(hysAdapter);


        upperAdapter = funSetArrayAdapterInteger(14,getActivity(), Arrays.asList(CommonData.uprArray297), tTrigUprRate, (byte) 'U');
        spinUpper.setAdapter(upperAdapter);

        if (thystVAL > 0) {
            hysAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.hystArray297), thystVAL - 1, (byte) 'H');
            spinHys.setAdapter(hysAdapter);
            spinHys.setSelection(thystVAL - 1);
        }

        spinUpper.setSelection(tTrigUprRate);
        checkEnable.setChecked(bHystON);


        if (ihystVAL == 1) {
            checkSearch.setEnabled(false);
        }
        if (bHystSrchON)
            checkSearch.setChecked(true);
        else
            checkSearch.setChecked(false);

        if (bHystON) {
            spinHys.setVisibility(View.VISIBLE);
            checkSearch.setVisibility(View.VISIBLE);
        } else {
            spinHys.setVisibility(View.INVISIBLE);
            checkSearch.setVisibility(View.GONE);
        }

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


        if (mode == "VOO" || mode == "AOO" || mode == "VOOR" || mode == "AOOR" || mode == "VVI" || mode == "AAI" || mode == "OVO" || mode == "OAO" || mode == "VVIR" || mode == "AAIR" || mode == "OOO") {
            spinUpper.setVisibility(View.INVISIBLE);
        } else if (mode == "VOO" || mode == "AOO" || mode == "OVO" || mode == "OAO" || mode == "VVIR" || mode == "AAIR" || mode == "VOOR" || mode == "AOOR" || mode == "VVTR" || mode == "AATR" || mode == "OOO") {
            spinHys.setVisibility(View.INVISIBLE);

        }
    }

    void funInitMain() {
        List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
        ampAdapter = CommonData.funSetArrayAdapterDouble(3,getActivity(), keysAMP, tAmp, (byte) 'A');
        spinAmp.setAdapter(ampAdapter);


        pwAdapter = CommonData.funSetArrayAdapterDouble(4,getActivity(), Arrays.asList(pwArray747), tPW, (byte) 'W');
        spinPw.setAdapter(pwAdapter);

        senAdapter = CommonData.funSetArrayAdapterDouble(5,getActivity(), Arrays.asList(CommonData.senArray297), tSen, (byte) 0);
        spinSens.setAdapter(senAdapter);

        refAdapter = CommonData.funSetArrayAdapterInteger(15,getActivity(), Arrays.asList(CommonData.refArray747), tRef, (byte) 'F');
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

    void funInitPolarity() {
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

    void funInitRateResponse() {
        upperrateAdapter = funSetArrayAdapterInteger(16,getActivity(), Arrays.asList(CommonData.uprArray297), tSensorRate, (byte) 'C');
        UpperRate.setAdapter(upperrateAdapter);

        uptimeAdapter = funSetArrayAdapterInteger(17,getActivity(), Arrays.asList(CommonData.upTimeArray), tuptime, (byte) 0);
        spinUptime.setAdapter(uptimeAdapter);

        downtimeAdapter = funSetArrayAdapterInteger(18,getActivity(), Arrays.asList(CommonData.dnTimeArray), tDownTime, (byte) 0);
        spinDownTime.setAdapter(downtimeAdapter);

        slopeAdapter = funSetArrayAdapterInteger(19,getActivity(), new ArrayList<>(mapSlope297.keySet()), tSlope, (byte) 0);
        spinSlope.setAdapter(slopeAdapter);

        spinSlope.setSelection(tSlope);
        spinDownTime.setSelection(tDownTime);
        spinUptime.setSelection(tuptime);
        UpperRate.setSelection(tSensorRate);

        if (bARC)
            checkARC.setChecked(true);
        else
            checkARC.setChecked(false);
    }

    public void mFunSpinnerOnItemClick() {
        //rate response

        checkARC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    CommonData.tbARC = b;
                    //'Hyst On/OFF + ARC + AMP
                    CommonData.pacerDataPROG[8] &= 0xBF;
                    if (CommonData.tbARC)
                        CommonData.pacerDataPROG[8] |= 0x40;
                    blnkProgBut();
                }
        });

        UpperRate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinUpperRate = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        UpperRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinUpperRate) {
                    bTuchSpinUpperRate = false;
                    CommonData.bSpinClick = true;
                    tSensorRate = i;
                    CommonData.pacerDataPROG[13] &= 0xC0;
                    CommonData.pacerDataPROG[13] |= CommonData.tSensorRate ;
                    validateParaRateResponse();
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
                    if (iuptime != tuptime) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
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
                bTuchSpinDownTime = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinDownTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinDownTime) {
                    bTuchSpinDownTime = false;
                    CommonData.bSpinClick = true;
                    tDownTime = i;
                    if (iDownTime != tDownTime) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
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
                bTuchSpinSlope = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinSlope.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinSlope) {
                    bTuchSpinSlope = false;
                    CommonData.bSpinClick = true;
                    tSlope = i;
                    if (iSlope != tSlope) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    CommonData.pacerDataPROG[15] = mapSlope297.get(spinSlope.getSelectedItem());
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //other

        checkEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    CommonData.tbHystON = b;
                    CommonData.pacerDataPROG[8] &= 0x7F;
                    if (b) {
                        CommonData.pacerDataPROG[8] |= 0x80;
                        spinHys.setVisibility(View.VISIBLE);
                        checkSearch.setVisibility(View.VISIBLE);
                    } else {
                        spinHys.setVisibility(View.INVISIBLE);
                        checkSearch.setVisibility(View.GONE);
                    }
                    blnkProgBut();


            }
        });


        checkMri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    bCheckMri = false;
                    CommonData.tbMRI = b;

                    // Sensitivity AM and MRI
                    CommonData.pacerDataPROG[11] &= 0xBF;
                    if (CommonData.tbMRI)
                        CommonData.pacerDataPROG[11] = CommonData.pacerDataPROG[11] | 0x40;
                    blnkProgBut();
                }


        });

        checkSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    bCheckSearch = false;
                    CommonData.tbHystSrchON = b;
                    //Hyst SRCH + Ref
                    CommonData.pacerDataPROG[10] &= 0x1F;
                    if (CommonData.tbHystSrchON)
                        CommonData.pacerDataPROG[10] = CommonData.pacerDataPROG[10] | 0x20;
                    blnkProgBut();


            }
        });
        spinHys.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinHys = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinHys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinHys) {
                    bTuchSpinHys = false;
                    CommonData.bSpinClick = true;
                    if (i != 0)
                        checkSearch.setEnabled(true);
                    else {
                        checkSearch.setChecked(false);
                        checkSearch.setEnabled(false);
                    }
                    thystVAL = i;
                    if (ihystVAL != thystVAL) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    CommonData.pacerDataPROG[9] = thystVAL;
                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                        tbAutoMsr = true;
                    else
                        tbAutoMsr = false;
                    // Sensitivity  AM and MRI

                    CommonData.pacerDataPROG[11] &= 0x7F;
                    if (tbAutoMsr)
                        CommonData.pacerDataPROG[11] = CommonData.pacerDataPROG[11] | 0x80;
                    validateParaOther();
                    validateCommonData();
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
                    if (iTrigUprRate != tTrigUprRate) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    CommonData.pacerDataPROG[13] = tTrigUprRate + 24;
                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                    if (iAmp != tAmp) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    CommonData.pacerDataPROG[8] &= 0xC0;
                    CommonData.pacerDataPROG[8] += mapAMP297.get(spinAmp.getSelectedItem());
                    validateParaMain();
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
                bTuchSpinPw = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinPw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinPw) {
                    bTuchSpinPw = false;
                    CommonData.bSpinClick = true;
                    tPW = i;
                    if (iPW != tPW) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    CommonData.pacerDataPROG[7] &= 0xC0;
                    CommonData.pacerDataPROG[7] += mapPW297.get(spinPw.getSelectedItem());
                    validateParaMain();
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
                    if (iRef != tRef) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    CommonData.pacerDataPROG[10] &= 0xE0;
                    CommonData.pacerDataPROG[10] += CommonData.tRef;
                    validateParaMain();
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
                    if (iSen != tSen) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
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

    public void onPause() {
        super.onPause();
        // Perform cleanup or save data when the fragment is being paused
    }

    @Override
    public void onResume() {
        super.onResume();
        // Perform initialization or reload data when the fragment is being resumed
    }

    public static void validateParaMain() {

        System.out.println("<><><><>@@@@@@ call");

        int iColor;
        iColor = getColor747(activity, CommonData.tAmp, (byte) 'A');
        if (spinAmp.getSelectedView() != null)
            ((TextView) spinAmp.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, CommonData.tPW, (byte) 'W');
        if (spinPw.getSelectedView() != null)
            ((TextView) spinPw.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, CommonData.tRef, (byte) 'F');
        if (spinRef.getSelectedView() != null)
            ((TextView) spinRef.getSelectedView()).setTextColor(iColor);


    }

    public static void validateParaOther() {
        int iColor;
        iColor = getColor747(activity, tTrigUprRate, (byte) 'U');
        if (spinUpper.getSelectedView() != null)
            ((TextView) spinUpper.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, thystVAL, (byte) 'H');
        if (spinHys.getSelectedView() != null)
            ((TextView) spinHys.getSelectedView()).setTextColor(iColor);

        if (CommonData.tAmp < 16 || CommonData.tAmp > 28) {
            CommonData.bAutoMsr = false;
            tbAutoMsr = false;
            spinAutomeas.setEnabled(false);
            spinAutomeas.setAlpha(0.5f);
            // Sensitivity  AM and MRI
            CommonData.pacerDataPROG[11] &= 0x7F;
            if ((CommonData.iAmp != CommonData.tAmp))
                Toast.makeText(activity, "AM will be ON between 2.5V and 4.7V", Toast.LENGTH_SHORT).show();
        } else {
            spinAutomeas.setEnabled(true);
            spinAutomeas.setAlpha(0);
        }
    }

    public static void validateParaRateResponse() {
        int iColor;
        iColor = getColor747(activity, tSensorRate, (byte) 'C');
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