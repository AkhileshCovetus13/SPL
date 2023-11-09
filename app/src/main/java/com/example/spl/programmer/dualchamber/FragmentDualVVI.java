package com.example.spl.programmer.dualchamber;


import static com.example.spl.helper.CommonData.bAutoMsr;
import static com.example.spl.helper.CommonData.bHystON;
import static com.example.spl.helper.CommonData.bHystSrchON;
import static com.example.spl.helper.CommonData.funSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funSetArrayAdapterString;
import static com.example.spl.helper.CommonData.getColor747;
import static com.example.spl.helper.CommonData.getColor747_B;
import static com.example.spl.helper.CommonData.iRef;
import static com.example.spl.helper.CommonData.iScreenFlag;
import static com.example.spl.helper.CommonData.iSen;
import static com.example.spl.helper.CommonData.iTrigUprRate;
import static com.example.spl.helper.CommonData.ihystVAL;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.tAmp;
import static com.example.spl.helper.CommonData.tPW;
import static com.example.spl.helper.CommonData.tRef;
import static com.example.spl.helper.CommonData.tSen;
import static com.example.spl.helper.CommonData.tTrigUprRate;
import static com.example.spl.helper.CommonData.tbAutoMsr;
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

public class FragmentDualVVI extends Fragment {

    //common
    Button tab1, tab2, tab3;
    public static Activity activity;
    String mode;
    LinearLayout mViewVVI;
    LinearLayout mViewPolarity;
    LinearLayout mViewOther;

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

    private ArrayAdapter<Integer> hysAdapter;
    private ArrayAdapter<String> automeasAdapter;
    private ArrayAdapter<Integer> upperAdapter;


    public FragmentDualVVI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityProgrammer activityProgrammer = (ActivityProgrammer) getActivity();
        mode = activityProgrammer.getMyData();
        System.out.println("<><><><>@###Where is ttttttttttttt ");
        activity = getActivity();
        iScreenFlag = 13;
        View v = inflater.inflate(R.layout.fragment_vvi, container, false);

        //common
        tab1 = v.findViewById(R.id.tab1);
        tab2 = v.findViewById(R.id.tab2);
        tab3 = v.findViewById(R.id.tab3);

        mViewVVI = v.findViewById(R.id.mViewVVI);
        mViewPolarity = v.findViewById(R.id.mViewPolarity);
        mViewOther = v.findViewById(R.id.mViewOther);


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
                highlightButton(tab1);
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewVVI.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.VISIBLE);
                mViewOther.setVisibility(View.GONE);
                highlightButton(tab2);
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewVVI.setVisibility(View.GONE);
                mViewPolarity.setVisibility(View.GONE);
                mViewOther.setVisibility(View.VISIBLE);
                highlightButton(tab3);
            }
        });


        funInitMain();
        funInitPolarity();
        funInitOther();

        mFunSpinnerOnItemClick();
        /*if (isFirstDualVviMain) {
            isFirstDualVviMain = false;
            validateParaMain();
            validateParaOther();
        }*/
    }

    void funInitOther() {
        //hysAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.hystArray297), 0, (byte) 'H');
        //spinHys.setAdapter(hysAdapter);


        //upperAdapter = funSetArrayAdapterInteger(12,getActivity(), Arrays.asList(CommonData.uprArray297), tTrigUprRate, (byte) 'U');
        //spinUpper.setAdapter(upperAdapter);


            hysAdapter = funSetArrayAdapterInteger(11,getActivity(), Arrays.asList(CommonData.hystArray747), thystVAL, (byte) 'H');
            spinHys.setAdapter(hysAdapter);
            spinHys.setSelection(thystVAL);


        // spinUpper.setSelection(tTrigUprRate);
        checkEnable.setChecked(bHystON);


        if (ihystVAL == 0) {
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
            //spinHys.setVisibility(View.INVISIBLE);

        }

        if (mode == "VVI" || mode == "VVT" || mode == "AAI" || mode == "AAT") {
            spinHys.setVisibility(View.VISIBLE);
            checkEnable.setVisibility(View.VISIBLE);
            checkSearch.setVisibility(View.VISIBLE);
        }else {
            spinHys.setVisibility(View.GONE);
            checkEnable.setVisibility(View.GONE);
            checkSearch.setVisibility(View.GONE);
        }


    }

    void funInitMain() {
        System.out.println("<><><>!!!!!!yess  call001");
        List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
        //ampAdapter = CommonData.funSetArrayAdapterDouble(1,getActivity(), keysAMP, tAmp, (byte) 'A');
        ampAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item, keysAMP) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getColor747_B(activity, position, (byte) 'A'));
                return view;
            }

            @NonNull
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getColor747_B(activity, position, (byte) 'A'));
                return view;
            }
        };
        ampAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        spinAmp.setAdapter(ampAdapter);


        pwAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item, CommonData.pwArray747) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getColor747_B(activity, position, (byte) 'W'));
                return view;
            }

            @NonNull
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getColor747_B(activity, position, (byte) 'W'));

                return view;
            }
        };
        pwAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        spinPw.setAdapter(pwAdapter);
        //spinPw.setAdapter(pwAdapter);

        senAdapter = CommonData.funSetArrayAdapterDouble(2, getActivity(), Arrays.asList(CommonData.senArray297), tSen, (byte) 0);
        spinSens.setAdapter(senAdapter);

        refAdapter = CommonData.funSetArrayAdapterInteger(13, getActivity(), Arrays.asList(CommonData.refArray747), tRef, (byte) 'F');
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


    public void mFunSpinnerOnItemClick() {
        //other

        checkEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CommonData.bSpinClick = true;
                CommonData.tbHystON = b;
                CommonData.pacerDataPROG[8] &= 0x7F;
                if (b) {
                    hysAdapter = funSetArrayAdapterInteger(55,getActivity(), Arrays.asList(CommonData.hystArray747), thystVAL, (byte) 'H');
                    spinHys.setAdapter(hysAdapter);
                    spinHys.setSelection(thystVAL);

                    CommonData.pacerDataPROG[8] |= 0x80;
                    spinHys.setVisibility(View.VISIBLE);
                    checkSearch.setVisibility(View.VISIBLE);
                } else {
                    CommonData.pacerDataPROG[10] &= 0x1F;
                    CommonData.thystVAL = 0;
                    CommonData.pacerDataPROG[10] &= 0xF0;
                    CommonData.pacerDataPROG[10] |= CommonData.thystVAL;
                    spinHys.setAdapter(null);
                    spinHys.setVisibility(View.INVISIBLE);
                    checkSearch.setVisibility(View.GONE);
                }
                validateParaOther_non_msg();
                validateCommonData();
                blnkProgBut();
            }
        });

        checkMri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bCheckMri) {
                    bCheckMri = false;
                    CommonData.tbMRI = b;

                    // Sensitivity AM and MRI
                    CommonData.pacerDataPROG[11] &= 0xBF;
                    if (CommonData.tbMRI)
                        CommonData.pacerDataPROG[11] = CommonData.pacerDataPROG[11] | 0x40;
                    blnkProgBut();
                }


            }
        });

        checkSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (bCheckSearch) {
                    bCheckSearch = false;
                    CommonData.bSpinClick = true;
                    CommonData.tbHystSrchON = b;
                    //Hyst SRCH + Ref
                    CommonData.pacerDataPROG[10] &= 0x1F;
                    if (CommonData.tbHystSrchON)
                        CommonData.pacerDataPROG[10] = CommonData.pacerDataPROG[10] | 0x20;
                    blnkProgBut();
                }

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
                    System.out.println("<><><>####ZZZZ    xxxx");
                    bTuchSpinHys = false;
                    CommonData.bSpinClick = true;
                    if (i != 0)
                        checkSearch.setEnabled(true);
                    else {
                        checkSearch.setChecked(false);
                        checkSearch.setEnabled(false);
                    }
                    //V Ref + Hyst Val
                    CommonData.thystVAL = i;
                    CommonData.pacerDataPROG[10] &= 0xF0;
                    CommonData.pacerDataPROG[10] |= CommonData.thystVAL;

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
                    CommonData.tAmp = i;
                    //S Pol + VP Pol + V AMP
                    int temp;
                    if (CommonData.tAmp > 31)
                        temp = (CommonData.tAmp - 31) * 3 + CommonData.tAmp;
                    else
                        temp = CommonData.tAmp;
                    CommonData.pacerDataPROG[9] &= 0xC0;
                    CommonData.pacerDataPROG[9] += CommonData.ampctrlWD[temp];
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
                    /*if (iPW != tPW) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.Blue));
                    }*/
                    CommonData.pacerDataPROG[7] &= 0xF0;
                    CommonData.pacerDataPROG[7] += CommonData.tPW;
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
        iColor = getColor747(activity, CommonData.tAmp, (byte) 'V');//V AMP
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
        System.out.println("<><><>####   calll !!!");

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
            //if ((CommonData.iAmp != CommonData.tAmp))
            //Toast.makeText(activity, "AM will be ON between 2.5V and 4.7V", Toast.LENGTH_SHORT).show();
        } else {
            spinAutomeas.setEnabled(true);
            spinAutomeas.setAlpha(0);
        }
    }

    public static void validateParaOther_non_msg() {
        int iColor;
        System.out.println("<><><>####   calll !!!");

        iColor = getColor747_B(activity, thystVAL, (byte) 'H');
        if (spinHys.getSelectedView() != null)
            ((TextView) spinHys.getSelectedView()).setTextColor(iColor);

        if (CommonData.tAmp < 16 || CommonData.tAmp > 28) {
            CommonData.bAutoMsr = false;
            tbAutoMsr = false;
            spinAutomeas.setEnabled(false);
            spinAutomeas.setAlpha(0.5f);
            // Sensitivity  AM and MRI
            CommonData.pacerDataPROG[11] &= 0x7F;
            //if ((CommonData.iAmp != CommonData.tAmp))
            //Toast.makeText(activity, "AM will be ON between 2.5V and 4.7V", Toast.LENGTH_SHORT).show();
        } else {
            spinAutomeas.setEnabled(true);
            spinAutomeas.setAlpha(0);
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
    }

}