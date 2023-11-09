package com.example.spl.programmer.dualchamber;


import static com.example.spl.helper.CommonData.ampctrlWD;
import static com.example.spl.helper.CommonData.avhArray;
import static com.example.spl.helper.CommonData.bAutoPol;
import static com.example.spl.helper.CommonData.getColor747;
import static com.example.spl.helper.CommonData.getColor747_B;
import static com.example.spl.helper.CommonData.iAAmp;
import static com.example.spl.helper.CommonData.iAPW;
import static com.example.spl.helper.CommonData.iARef;
import static com.example.spl.helper.CommonData.iASen;
import static com.example.spl.helper.CommonData.iAVHyst;
import static com.example.spl.helper.CommonData.iAmp;
import static com.example.spl.helper.CommonData.iBlnk;
import static com.example.spl.helper.CommonData.iPW;
import static com.example.spl.helper.CommonData.iRef;
import static com.example.spl.helper.CommonData.iScreenFlag;
import static com.example.spl.helper.CommonData.iSen;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.tAAmp;
import static com.example.spl.helper.CommonData.tAPW;
import static com.example.spl.helper.CommonData.tARef;
import static com.example.spl.helper.CommonData.tASen;
import static com.example.spl.helper.CommonData.tAmp;
import static com.example.spl.helper.CommonData.tBlnk;
import static com.example.spl.helper.CommonData.tPAVI;
import static com.example.spl.helper.CommonData.tPW;
import static com.example.spl.helper.CommonData.tPvarp;
import static com.example.spl.helper.CommonData.tRef;
import static com.example.spl.helper.CommonData.tSAVI;
import static com.example.spl.helper.CommonData.tSen;
import static com.example.spl.helper.CommonData.tTrigUprRate;
import static com.example.spl.helper.CommonData.thystVAL;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.spl.R;
import com.example.spl.helper.CommonData;
import com.example.spl.programmer.ActivityProgrammer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentParaDDDNoATP extends Fragment {

    //common
    Button tab1,tab4,tab3,tab2;
    String mode;
    public static Activity activity;
    LinearLayout mViewAtrium;
    LinearLayout mViewDDDOther;
    LinearLayout mViewPolarity;
    LinearLayout mViewVentricle;
    LinearLayout mViewAtp;

    //atrium
    Spinner spinSens;
    public static Spinner spinPw;
    public static Spinner spinRef;
    public static Spinner spinAmp;

    private ArrayAdapter<Double> pwAdapter;
    private ArrayAdapter<Double> senAdapter;
    private ArrayAdapter<Integer> refAdapter;
    private ArrayAdapter<Double> ampAdapter;

    boolean bTuchSpinAmp = false;
    boolean bTuchSpinPW = false;
    boolean bTuchSpinRef = false;
    boolean bTuchSpinSens = false;

    //ventricle
    Spinner spinVentricleSens;
    public static Spinner spinVentriclePw;
    public static Spinner spinVentricleRef;
    public static Spinner spinVentricleAmp;
    private ArrayAdapter<Double> pwVentricleAdapter;
    private ArrayAdapter<Double> senVentricleAdapter;
    private ArrayAdapter<Integer> refVentricleAdapter;
    private ArrayAdapter<Double> ampVentricleAdapter;

    boolean bTuchSpinVentricleAmp = false;
    boolean bTuchSpinVentriclePW = false;
    boolean bTuchSpinVentricleRef = false;
    boolean bTuchSpinVentricleSens = false;


    // other tab
    Spinner spinAutomeas, spinWenkebach;
    public static Spinner spinPVARP,spinBlanking,spinAVIPace,spinAVISens,spinHys,spinUpper;
    CheckBox checkSearch;
    private ArrayAdapter<Integer> hysAdapter;
    private ArrayAdapter<String> automeasAdapter;
    private ArrayAdapter<Integer> upperAdapter;
    private ArrayAdapter<Integer> aviPaceAdapter;
    private ArrayAdapter<Integer> aviSensAdapter;
    private ArrayAdapter<Integer> blankAdapter;
    private ArrayAdapter<Integer> pvarpAdapter;
    private ArrayAdapter<String> wenkebachAdapter;


    Boolean bSpinHys = false;
    Boolean bSpinBlanking = false;
    Boolean bSpinUpper = false;
    Boolean bSpinPVARP = false;
    Boolean bSpinAVIPace = false;
    Boolean bSpinAVISens = false;
    Boolean bCheckSearch = false;

    //polarity
    RadioButton paceABi, paceAUni, paceVBi, paceVUni, sensBi, sensUni;
    RadioGroup radioGroupPaceA;
    RadioGroup radioGroupPaceV;
    RadioGroup radioGroupSensitivity;



    //ATP
    Spinner spinATentry,spinExitCount;
    public static Spinner spinATrate;
    CheckBox checkAtEnable;
    private ArrayAdapter<Integer> ATrateAdapter;
    private ArrayAdapter<Integer> ATentryAdapter;
    private ArrayAdapter<Integer> ExitCountAdapter;

    Boolean bSpinATrate = false;
    Boolean bSpinATentry = false;
    Boolean bSpinExitCount = false;
    Boolean bCheckAtEnable = false;


    public FragmentParaDDDNoATP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ActivityProgrammer activityProgrammer = (ActivityProgrammer) getActivity();
        mode = activityProgrammer.getMyData();
        activity = getActivity();
        iScreenFlag = 16;
        View v = inflater.inflate(R.layout.fragment_ddd_no_atp, container, false);

        //ddd atrium
        spinAmp = v.findViewById(R.id.spinAmp);
        spinSens = v.findViewById(R.id.spinSens);
        spinPw = v.findViewById(R.id.spinPw);
        spinRef = v.findViewById(R.id.spinRef);

        //DDD other
        spinHys = v.findViewById(R.id.spinHys);
        spinAutomeas = v.findViewById(R.id.spinAutomeas);
        spinUpper = v.findViewById(R.id.spinUpper);
        spinAVISens = v.findViewById(R.id.spinAVISens);
        spinAVIPace = v.findViewById(R.id.spinAVIPace);
        spinBlanking = v.findViewById(R.id.spinBlanking);
        spinPVARP = v.findViewById(R.id.spinPVARP);
        spinWenkebach = v.findViewById(R.id.spinWenkebach);
        checkSearch = v.findViewById(R.id.checkSearch);

        //polarity
        paceABi = v.findViewById(R.id.paceABi);
        paceAUni = v.findViewById(R.id.paceAUni);
        paceVBi = v.findViewById(R.id.paceVBi);
        paceVUni = v.findViewById(R.id.paceVUni);
        sensBi = v.findViewById(R.id.sensBi);
        sensUni = v.findViewById(R.id.sensUni);
        radioGroupPaceA = v.findViewById(R.id.radioGroupPaceA);
        radioGroupPaceV = v.findViewById(R.id.radioGroupPaceV);
        radioGroupSensitivity = v.findViewById(R.id.radioGroupSensitivity);

        //ventricle
        spinVentricleAmp = v.findViewById(R.id.spinVentricleAmp);
        spinVentricleSens = v.findViewById(R.id.spinVentricleSens);
        spinVentriclePw = v.findViewById(R.id.spinVentriclePw);
        spinVentricleRef = v.findViewById(R.id.spinVentricleRef);

        //ATP
        spinATrate=v.findViewById(R.id.spinATrate);
        spinATentry=v.findViewById(R.id.spinATentry);
        spinExitCount=v.findViewById(R.id.spinExitCount);
        checkAtEnable=v.findViewById(R.id.checkAtEnable);



        //common
        tab1 = v.findViewById(R.id.tab1);
        tab4 = v.findViewById(R.id.tab4);
        tab3 = v.findViewById(R.id.tab3);
        tab2 = v.findViewById(R.id.tab2);
        mViewAtrium = v.findViewById(R.id.mViewAtrium);
        mViewDDDOther = v.findViewById(R.id.mViewDDDOther);
        mViewPolarity = v.findViewById(R.id.mViewPolarity);
        mViewVentricle = v.findViewById(R.id.mViewVentricle);
        mViewAtp = v.findViewById(R.id.mViewAtp);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        highlightButton(tab1);
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewAtrium.setVisibility(View.VISIBLE);
                mViewDDDOther.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.GONE);
                mViewVentricle.setVisibility(View.GONE);
                mViewAtp.setVisibility(View.GONE);
                highlightButton(tab1);
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewAtrium.setVisibility(View.GONE);
                mViewDDDOther.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.GONE);
                mViewVentricle.setVisibility(View.VISIBLE);
                mViewAtp.setVisibility(View.GONE);
                highlightButton(tab2);
            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewDDDOther.setVisibility(View.GONE);
                mViewAtrium.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.VISIBLE);
                mViewVentricle.setVisibility(View.GONE);
                mViewAtp.setVisibility(View.GONE);
                highlightButton(tab3);
            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewDDDOther.setVisibility(View.VISIBLE);
                mViewAtrium.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.GONE);
                mViewVentricle.setVisibility(View.GONE);
                mViewAtp.setVisibility(View.GONE);
                highlightButton(tab4);
            }
        });




        funInitOther();
        funInitAtrium();
        funInitVentricle();
        funInitPolarity();

       /* if (isFirstDualAtrium) {
            isFirstDualAtrium = false;
            validateParaAtrium();
            validateParaOther();
            validateParaVentricle();
            validateParaATP();
            resetVariableDualChamber("atrium");
        }*/

        mFunSpinnerOnItemClick();

    }




    void funInitVentricle(){
        List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
        ampVentricleAdapter = CommonData.funSetArrayAdapterDouble(6,getActivity(), keysAMP, tAmp, (byte) 'V');
        spinVentricleAmp.setAdapter(ampVentricleAdapter);
        spinVentricleAmp.setSelection(tAmp);

        pwVentricleAdapter = CommonData.funSetArrayAdapterDouble(7,getActivity(), Arrays.asList(CommonData.pwArray747), tPW, (byte) 'W');


        pwVentricleAdapter  = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item, CommonData.pwArray747) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getColor747_B(activity,position,(byte)'W'));
                return view;
            }

            @NonNull
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getColor747_B(activity,position,(byte)'W'));

                return view;
            }
        };
        pwVentricleAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        spinVentriclePw.setAdapter(pwVentricleAdapter);
        spinVentriclePw.setSelection(tPW);


        senVentricleAdapter = CommonData.funSetArrayAdapterDouble(8,getActivity(), Arrays.asList(CommonData.senArray297), tSen, (byte) 0);
        spinVentricleSens.setAdapter(senVentricleAdapter);
        spinVentricleSens.setSelection(tSen);

        refVentricleAdapter = CommonData.funSetArrayAdapterInteger(24,getActivity(), Arrays.asList(CommonData.refArray747), tRef, (byte) 'F');
        spinVentricleRef.setAdapter(refVentricleAdapter);
        spinVentricleRef.setSelection(tRef);


        if (mode == "ODO") {
            spinVentricleAmp.setVisibility(View.INVISIBLE);
            spinVentriclePw.setVisibility(View.INVISIBLE);
        }else
        if (mode == "DOO" || mode == "DOOR") {
            spinVentricleSens.setVisibility(View.INVISIBLE);
            spinVentricleRef.setVisibility(View.INVISIBLE);
        }


    }

    void funInitPolarity(){
        if (mode == "VDD" || mode == "ODO" || mode == "VDDR") {
            paceABi.setVisibility(View.INVISIBLE);
            paceAUni.setVisibility(View.INVISIBLE);
        } else if (mode == "ODO") {
            paceVBi.setVisibility(View.INVISIBLE);
            paceVUni.setVisibility(View.INVISIBLE);
        } else if (mode == "DOO" || mode == "DOOR") {
            sensBi.setVisibility(View.INVISIBLE);
            sensUni.setVisibility(View.INVISIBLE);
        }

        if (CommonData.iAPacepol == 1)
            paceAUni.setChecked(true);
        else
            paceABi.setChecked(true);

        if (CommonData.iPacePol == 1)
            paceVUni.setChecked(true);
        else
            paceVBi.setChecked(true);

        if (CommonData.iSenPol == 1)
            sensUni.setChecked(true);
        else
            sensBi.setChecked(true);

        radioGroupPaceA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position;
                if (checkedId == R.id.paceABi) {
                    position = 0;
                } else {
                    position = 1;
                }

                CommonData.tAPacepol = position;
                //Mag +AP Pol + A AMP
                CommonData.pacerDataPROG[8] &= 0xBF;
                CommonData.pacerDataPROG[8] = CommonData.tAPacepol * 64 + CommonData.pacerDataPROG[8];
                blnkProgBut();


            }
        });

        radioGroupPaceV.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position;
                if (checkedId == R.id.paceVBi) {
                    position = 0;
                } else {
                    position = 1;
                }


                CommonData.tPacePol = position;
                //Sen Pol +VP Pol + V AMP
                CommonData.pacerDataPROG[9] &= 0xBF;
                CommonData.pacerDataPROG[9] += CommonData.tPacePol * 64;
                blnkProgBut();

            }
        });

        radioGroupSensitivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position;
                if (checkedId == R.id.sensBi) {
                    position = 0;
                } else {
                    position = 1;
                }


                CommonData.tSenPol = position;
                // S pol + VP pol + V AMP
                CommonData.pacerDataPROG[9] &= 0x7F;
                CommonData.pacerDataPROG[9] |= CommonData.tSenPol * 128;
                blnkProgBut();

            }
        });
    }

    void funInitOther(){
        if (mode == "VDD" || mode == "DOO" || mode == "VDDR" || mode == "DOOR" || mode == "ODO") {
            spinBlanking.setVisibility(View.INVISIBLE);
        }else if (mode == "DOO" || mode == "DOOR" || mode == "ODO") {
            spinHys.setVisibility(View.INVISIBLE);
            checkSearch.setVisibility(View.INVISIBLE);
        }else if (mode == "DDI" || mode == "DOO" || mode == "DVI" || mode == "DOOR" || mode == "DDIR" || mode == "DVIR" || mode == "ODO") {
            spinUpper.setVisibility(View.INVISIBLE);
        }else if (mode == "DOO" || mode == "DVI" || mode == "DOOR" || mode == "DVIR" || mode == "ODO") {
            spinPVARP.setVisibility(View.INVISIBLE);
        }else if (mode == "DOO" || mode == "DOOR" || mode == "ODO") {
            spinAutomeas.setVisibility(View.INVISIBLE);
        }else if (mode == "ODO") {
            spinAVIPace.setVisibility(View.INVISIBLE);
        }

        hysAdapter = CommonData.funSetArrayAdapterInteger(25,getActivity(), Arrays.asList(avhArray), iAVHyst, (byte) 'H');
        spinHys.setAdapter(hysAdapter);
        blankAdapter = CommonData.funSetArrayAdapterInteger(26,getActivity(), Arrays.asList(CommonData.blnkArray), tBlnk, (byte) 'B');
        spinBlanking.setAdapter(blankAdapter);

        upperAdapter = CommonData.funSetArrayAdapterInteger(27,getActivity(), Arrays.asList(CommonData.uprArray297), tTrigUprRate, (byte) 'U');
        spinUpper.setAdapter(upperAdapter);

        pvarpAdapter = CommonData.funSetArrayAdapterInteger(28,getActivity(), Arrays.asList(CommonData.refArray747), tPvarp, (byte) 'O');
        spinPVARP.setAdapter(pvarpAdapter);

        aviPaceAdapter = CommonData.funSetArrayAdapterInteger(29,getActivity(), Arrays.asList(CommonData.aviArray), tPAVI, (byte) 'I');
        spinAVIPace.setAdapter(aviPaceAdapter);

        aviSensAdapter = CommonData.funSetArrayAdapterInteger(30,getActivity(), Arrays.asList(CommonData.aviArray), tSAVI, (byte) 'J');
        spinAVISens.setAdapter(aviSensAdapter);

        wenkebachAdapter = CommonData.funSetArrayAdapterStringLong(getActivity(), Arrays.asList(CommonData.wenkebachArray), 0, (byte) 0);
        spinWenkebach.setAdapter(wenkebachAdapter);
        spinWenkebach.setEnabled(false);
        spinWenkebach.setAlpha(0.5f);

        if (mode == "DDD" || mode == "DDDR" || mode == "VDDR" || mode == "VDD") {
            spinAVISens.setEnabled(false);
            spinAVISens.setAlpha(0.5f);
        }


        if (bAutoPol)
            automeasAdapter = CommonData.funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.auto), 0, (byte) 0);
        else
            automeasAdapter = CommonData.funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.auto), 1, (byte) 0);
        spinAutomeas.setAdapter(automeasAdapter);


        if (CommonData.tMode <= 7)//SSI modes then Show Hyst else AV Hyst
        {
            spinHys.setSelection(thystVAL);
            checkSearch.setChecked(CommonData.bHystSrchON);
        } else {
            spinHys.setSelection(CommonData.tAVHyst);
            if(CommonData.tAVHyst==0){
                checkSearch.setVisibility(View.GONE);
            }else {
                checkSearch.setVisibility(View.VISIBLE);
            }
            checkSearch.setChecked(CommonData.bAVHSrch);
        }

        if (bAutoPol)
            spinAutomeas.setSelection(0);
        else
            spinAutomeas.setSelection(1);
        spinAVISens.setSelection(tSAVI);
        spinAVIPace.setSelection(tPAVI);
        spinPVARP.setSelection(tPvarp);
        spinUpper.setSelection(tTrigUprRate);
        spinBlanking.setSelection(tBlnk);



    }

    void funInitAtrium(){
        List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
        ampAdapter = CommonData.funSetArrayAdapterDouble(9,getActivity(), keysAMP, tAAmp, (byte) 'A');
        spinAmp.setAdapter(ampAdapter);
        spinAmp.setSelection(tAAmp);

        pwAdapter = CommonData.funSetArrayAdapterDouble(10,getActivity(), Arrays.asList(CommonData.pwArray747), tAPW, (byte) 'P');
        spinPw.setAdapter(pwAdapter);
        spinPw.setSelection(tAPW);


        senAdapter = CommonData.funSetArrayAdapterDouble(11,getActivity(), Arrays.asList(CommonData.aSenArray), tASen, (byte) 0);
        spinSens.setAdapter(senAdapter);
        spinSens.setSelection(iASen);

        refAdapter = CommonData.funSetArrayAdapterInteger(31,getActivity(), Arrays.asList(CommonData.refArray747), tARef, (byte) 'E');
        spinRef.setAdapter(refAdapter);
        spinRef.setSelection(tARef);


        if (mode == "VDD" || mode == "ODO" || mode == "VDDR") {
            spinAmp.setVisibility(View.INVISIBLE);
            spinPw.setVisibility(View.INVISIBLE);
        }
        if (mode == "DOO" || mode == "DVI" || mode == "DOOR" || mode == "DVIR") {
            spinSens.setVisibility(View.INVISIBLE);
            spinRef.setVisibility(View.INVISIBLE);
        }


    }

    public void mFunSpinnerOnItemClick() {


        //venticle
        spinVentricleAmp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinVentricleAmp = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinVentricleAmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinVentricleAmp) {
                    bTuchSpinVentricleAmp = false;
                    CommonData.bSpinClick = true;
                    tAmp = i;

                    if (iAmp != tAmp) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }

                    //S Pol + VP Pol + V AMP
                    int temp;
                    if (tAmp > 31)
                        temp = (tAmp - 31) * 3 + tAmp;
                    else
                        temp = tAmp;
                    CommonData.pacerDataPROG[9] &= 0xC0;
                    CommonData.pacerDataPROG[9] += ampctrlWD[temp];;
                    validateParaVentricle();
                    validateCommonData();
                    blnkProgBut();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinVentriclePw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinVentriclePW = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinVentriclePw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinVentriclePW) {
                    bTuchSpinVentriclePW = false;
                    CommonData.bSpinClick = true;
                    tPW = i;
                    if (iPW != tPW) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    //A PW + V PW
                    CommonData.pacerDataPROG[7] &= 0xF0;
                    CommonData.pacerDataPROG[7] += tPW;
                    validateParaVentricle();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinVentricleSens.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinVentricleSens = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinVentricleSens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinVentricleSens) {
                    bTuchSpinVentricleSens = false;
                    CommonData.bSpinClick = true;
                    tSen = i;
                    if (iSen != tSen) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    // Blnk + A Sens + V Sens
                    CommonData.pacerDataPROG[14] &= 0xF8;
                    CommonData.pacerDataPROG[14] += tSen;
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        spinVentricleRef.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinVentricleRef = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinVentricleRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinVentricleRef) {
                    bTuchSpinVentricleRef = false;
                    CommonData.bSpinClick = true;
                    tRef = i;
                    if (iRef != tRef) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    // VREF + Hyst Val
                    CommonData.pacerDataPROG[10] &= 0x0F;
                    CommonData.pacerDataPROG[10] += (tRef * 16);
                    validateParaVentricle();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //venticle END

        //atrium
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
                System.out.println("<><><>@@@$$ "+i);
                if (bTuchSpinAmp) {
                    bTuchSpinAmp = false;
                    CommonData.bSpinClick = true;
                    tAAmp = i;
                    if (iAAmp != tAAmp) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    List<Integer> ampValue = new ArrayList<>(mapAMP297.values());
                    //Magnet + AP Pol + A AMP
                    int temp;
                    if (tAAmp > 31)
                        temp = (tAAmp - 31) * 3 + tAAmp;
                    else
                        temp = tAAmp;

                    CommonData.pacerDataPROG[8] &= 0xC0;
                    CommonData.pacerDataPROG[8] += ampctrlWD[temp];
                    validateParaAtrium();
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
                    tAPW = i;
                    if (iAPW != tAPW) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    //A PW + V PW
                    CommonData.pacerDataPROG[7] &= 0x0F;
                    CommonData.pacerDataPROG[7] += tAPW * 16;
                    validateParaAtrium();
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
                    tASen = i;
                    if (iASen != tASen) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    // Blnk + A Sens + V Sens
                    CommonData.pacerDataPROG[14] &= 0xC7;
                    CommonData.pacerDataPROG[14] += (tASen * 8);
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
                    tARef = i;
                    if (iARef != tARef) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    // PVARP + A Refrectory
                    CommonData.pacerDataPROG[11] &= 0xF0;
                    CommonData.pacerDataPROG[11] += tARef;
                    validateParaAtrium();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //atrium END

        // others
        spinHys.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bSpinHys = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinHys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bSpinHys) {
                    bSpinHys = false;
                    CommonData.bSpinClick = true;
                    CommonData.tAVHyst = i;
                    CommonData.pacerDataPROG[16] &= 0x1F;
                    CommonData.pacerDataPROG[16] |= CommonData.tAVHyst*32;
                    if(CommonData.tAVHyst==0){
                        checkSearch.setVisibility(View.GONE);
                    }else {
                        checkSearch.setVisibility(View.VISIBLE);
                    }
                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinBlanking.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bSpinBlanking = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinBlanking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bSpinBlanking) {
                    bSpinBlanking = false;
                    CommonData.bSpinClick = true;
                    tBlnk = i;
                    if (iBlnk != tBlnk) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }
                    // Blanking + A SEn + V Sen
                    CommonData.pacerDataPROG[14] &= 0x3F;
                    CommonData.pacerDataPROG[14] += (tBlnk * 64);
                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Upper Rate (SST and DDD Mode)
        spinUpper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bSpinUpper = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinUpper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bSpinUpper) {
                    bSpinUpper = false;
                    CommonData.bSpinClick = true;
                    CommonData.tTrigUprRate = i;
                    CommonData.pacerDataPROG[12] &= 0xC0;
                    CommonData.pacerDataPROG[12] |= CommonData.tTrigUprRate;





                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinPVARP.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bSpinPVARP = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinPVARP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bSpinPVARP) {
                    bSpinPVARP = false;
                    CommonData.bSpinClick = true;
                    tPvarp = i;
                    // PVARP + A Refrectory
                    CommonData.pacerDataPROG[11] &= 0x0F;
                    CommonData.pacerDataPROG[11] += (tPvarp * 16);
                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinAVIPace.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bSpinAVIPace = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinAVIPace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bSpinAVIPace) {
                    bSpinAVIPace = false;
                    CommonData.bSpinClick = true;
                    tPAVI = i;
                    CommonData.pacerDataPROG[16] &= 0xE0;
                    CommonData.pacerDataPROG[16] |= tPAVI;
                    //SAVI will always be less than PAVI
                    //UP Time  + SAVI
                    if (i > 0) {
                        tSAVI = i - 1;
                    } else {
                        tSAVI = i;
                    }
                    CommonData.pacerDataPROG[17] &= 0xE0;
                    CommonData.pacerDataPROG[17] |= tSAVI;
                    if (mode == "DDD" || mode == "DDDR" || mode == "VDDR" || mode == "VDD") {
                        if (i > 0)
                            spinAVISens.setSelection(i - 1);
                        else
                            spinAVISens.setSelection(i);
                    }
                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinAVISens.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bSpinAVISens = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinAVISens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bSpinAVISens) {
                    bSpinAVISens = false;
                    CommonData.bSpinClick = true;
                    tSAVI = i;
                    CommonData.pacerDataPROG[17] &= 0xE0;
                    CommonData.pacerDataPROG[17] |= tSAVI;
                    validateParaOther();
                    validateCommonData();
                    blnkProgBut();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        checkSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (CommonData.tMode <= 7) //if SSI mode then Hyst srch else AV Hyst Srch
                    {
                        CommonData.tbHystSrchON = isChecked;
                        CommonData.pacerDataPROG[15] &= 0x7F;
                        if (CommonData.tbHystSrchON)
                            CommonData.pacerDataPROG[15] |= 0x80;
                    } else {
                        CommonData.tbAVHSrch = isChecked;

                        CommonData.pacerDataPROG[15] &= 0xBF;
                        if (CommonData.tbAVHSrch)
                            CommonData.pacerDataPROG[15] |= 0x40;
                    }
                    blnkProgBut();
                }
        });
    }

    public static void validateParaAtrium() {
        int iColor;
        iColor = getColor747(activity, tAAmp, (byte) 'A');//A AMP
        if (spinAmp.getSelectedView() != null)
            ((TextView) spinAmp.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tAPW, (byte) 'P');//A PW
        if (spinPw.getSelectedView() != null)
            ((TextView) spinPw.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tARef, (byte) 'E'); //A REF
        if (spinRef.getSelectedView() != null)
            ((TextView) spinRef.getSelectedView()).setTextColor(iColor);
    }
    public static void validateParaOther() {
        CommonData.bParaOK = true;
        int iColor;

        iColor = getColor747(activity, tPvarp, (byte) 'O'); //PVARP
        if (spinPVARP.getSelectedView() != null)
            ((TextView) spinPVARP.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tBlnk, (byte) 'B'); //V Blanking
        if (spinBlanking.getSelectedView() != null)
            ((TextView) spinBlanking.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tPAVI, (byte) 'I'); //Pace A V Interval
        if (spinAVIPace.getSelectedView() != null)
            ((TextView) spinAVIPace.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tSAVI, (byte) 'J'); //Sens A V Interval
        if (spinAVISens.getSelectedView() != null)
            ((TextView) spinAVISens.getSelectedView()).setTextColor(iColor);

        if (CommonData.tMode <= 7) {
            iColor = getColor747(activity, thystVAL, (byte) 'H'); // Hyst
        } else {
            iColor = getColor747(activity, CommonData.tAVHyst, (byte) 'Y'); // A V Hyst
        }
        if (spinHys.getSelectedView() != null)
            ((TextView) spinHys.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tTrigUprRate, (byte) 'U'); //Upper Rate
        if (spinUpper.getSelectedView() != null)
            ((TextView) spinUpper.getSelectedView()).setTextColor(iColor);
    }
    public static void validateParaVentricle() {
        int iColor;

        iColor = getColor747(activity, tAmp, (byte) 'V');//V AMP
        if (spinVentricleAmp.getSelectedView() != null)
            ((TextView) spinVentricleAmp.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tPW, (byte) 'W');//V PW
        if (spinVentriclePw.getSelectedView() != null)
            ((TextView) spinVentriclePw.getSelectedView()).setTextColor(iColor);

        iColor = getColor747(activity, tRef, (byte) 'F'); //V REF
        if (spinVentricleRef.getSelectedView() != null)
            ((TextView) spinVentricleRef.getSelectedView()).setTextColor(iColor);
    }


    private void highlightButton(Button button) {
        resetButtonHighlight();
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void resetButtonHighlight() {
        tab1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tab4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tab2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tab3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

    }
}