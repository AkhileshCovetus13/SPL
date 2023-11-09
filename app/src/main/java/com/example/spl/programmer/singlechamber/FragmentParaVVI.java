package com.example.spl.programmer.singlechamber;


import static com.example.spl.helper.CommonData.bAutoMsr;
import static com.example.spl.helper.CommonData.bHystON;
import static com.example.spl.helper.CommonData.bHystSrchON;
import static com.example.spl.helper.CommonData.funSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funSetArrayAdapterString;
import static com.example.spl.helper.CommonData.getColor297;
import static com.example.spl.helper.CommonData.getColor297_B;
import static com.example.spl.helper.CommonData.iPacerSelect;
import static com.example.spl.helper.CommonData.ihystVAL;
import static com.example.spl.helper.CommonData.mFunAlert;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.mapPW8820;
import static com.example.spl.helper.CommonData.tAmp;
import static com.example.spl.helper.CommonData.tPW;
import static com.example.spl.helper.CommonData.tRef;
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
import android.widget.RelativeLayout;
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

public class FragmentParaVVI extends Fragment {
    //common
    Button tab1, tab2, tab3;
    public static Activity activity;
    String mode;
    LinearLayout mViewVVI;
    LinearLayout mViewPolarity;
    LinearLayout mViewOther;



    //other
    public static Spinner spinUpper,spinHys,spinAutomeas;
    CheckBox checkSearch;
    CheckBox checkEnable;
    CheckBox checkMri;
RelativeLayout mLayoutAM;
TextView txtAutomeasTxt;
    private ArrayAdapter<String> hysAdapter;
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


    public FragmentParaVVI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityProgrammer activityProgrammer = (ActivityProgrammer) getActivity();
        mode = activityProgrammer.getMyData();
        System.out.println("<><><><>@###Where is onnnnn ");
        activity = getActivity();
        CommonData.iScreenFlag = 2;
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

        //other
        spinHys = v.findViewById(R.id.spinHys);
        spinAutomeas = v.findViewById(R.id.spinAutomeas);
        spinUpper = v.findViewById(R.id.spinUpper);
        checkSearch = v.findViewById(R.id.checkSearch);
        checkEnable = v.findViewById(R.id.checkEnable);
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
        hysAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.hystArray297), 0, (byte) 'H');
        spinHys.setAdapter(hysAdapter);

        if (mode == "VVT" || mode == "AAT") {
            spinUpper.setVisibility(View.VISIBLE);
            upperAdapter = funSetArrayAdapterInteger(62,getActivity(), Arrays.asList(CommonData.uprArray297), tTrigUprRate, (byte) 'U');
            spinUpper.setAdapter(upperAdapter);
        }else {
            spinUpper.setVisibility(View.GONE);
        }

        if (mode == "VVI" || mode == "AAI" || mode == "VVT" || mode == "AAT") {
            spinHys.setVisibility(View.VISIBLE);
            checkSearch.setVisibility(View.VISIBLE);
            checkEnable.setVisibility(View.VISIBLE);
            hysAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.hystArray297), thystVAL, (byte) 'H');
            spinHys.setAdapter(hysAdapter);
            spinHys.setSelection(thystVAL);
        }else {
            checkSearch.setVisibility(View.GONE);
            checkEnable.setVisibility(View.GONE);
            spinHys.setVisibility(View.GONE);
        }

        spinUpper.setSelection(tTrigUprRate);
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

        checkSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CommonData.tbHystSrchON = b;
                //Hyst SRCH + Ref
                CommonData.pacerDataPROG[10] &= 0x1F;
                if (CommonData.tbHystSrchON)
                    CommonData.pacerDataPROG[10] = CommonData.pacerDataPROG[10] | 0x20;
            }
        });


        if (mode == "VOO" || mode == "AOO" || mode == "VOOR" || mode == "AOOR" || mode == "VVI" || mode == "AAI" || mode == "OVO" || mode == "OAO" || mode == "VVIR" || mode == "AAIR" || mode == "OOO") {
            spinUpper.setVisibility(View.INVISIBLE);
        } else if (mode == "VOO" || mode == "AOO" || mode == "OVO" || mode == "OAO" || mode == "VVIR" || mode == "AAIR" || mode == "VOOR" || mode == "AOOR" || mode == "VVTR" || mode == "AATR" || mode == "OOO") {
            spinHys.setVisibility(View.INVISIBLE);

        }
    }

    void funInitMain() {
        List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
        ampAdapter = CommonData.funSetArrayAdapterDouble(22,getActivity(), keysAMP, tAmp, (byte) 'A');
        spinAmp.setAdapter(ampAdapter);

        if(iPacerSelect == 02 || iPacerSelect == 27 || iPacerSelect == 2){
            List<Double> keysPW = new ArrayList<>(mapPW8820.keySet());
            pwAdapter = CommonData.funSetArrayAdapterDouble(23,getActivity(), keysPW, tPW, (byte) 'W');
            spinPw.setAdapter(pwAdapter);
        }else {
            List<Double> keysPW = new ArrayList<>(CommonData.mapPW297.keySet());
            //pwAdapter = CommonData.funSetArrayAdapterDouble(24,getActivity(), Arrays.asList(pwArray747), tPW, (byte) 'W');
            pwAdapter  = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item, keysPW) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    int col = getColor297_B(position,(byte)'W',activity);
                    System.out.println("<><><><>col####     "+col);
                    tv.setTextColor(col);
                    return view;
                }

                @NonNull
                @Override
                public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    tv.setTextColor(getColor297_B(position,(byte)'W',activity));

                    return view;
                }
            };
            pwAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
            spinPw.setAdapter(pwAdapter);
        }


        senAdapter = CommonData.funSetArrayAdapterDouble(25,getActivity(), Arrays.asList(CommonData.senArray297), tSen, (byte) 0);
        spinSens.setAdapter(senAdapter);

        refAdapter = funSetArrayAdapterInteger(63,getActivity(), Arrays.asList(CommonData.refArray297), tRef, (byte) 'F');
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
                if (CommonData.tAmp < 16 || CommonData.tAmp > 28) {
                    CommonData.bAutoMsr = false;
                    CommonData.tbAutoMsr = false;
                    spinAutomeas.setEnabled(false);
                    spinAutomeas.setVisibility(View.GONE);
                    txtAutomeasTxt.setVisibility(View.VISIBLE);
                    // Sensitivity  AM and MRI
                    CommonData.pacerDataPROG[11] &= 0x7F;
                } else {
                    spinAutomeas.setVisibility(View.VISIBLE);
                    txtAutomeasTxt.setVisibility(View.GONE);
                    spinAutomeas.setEnabled(true);
                    //spinAutomeas.setAlpha(0);
                }
                highlightButton(tab3);
            }
        });


        funInitMain();
        funInitOther();
        funInitPolarity();

        mFunSpinnerOnItemClick();
       /* if (CommonData.isFirstVviMain) {
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

    public static void validateParaMainSingle() {
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

        iColor = getColor297(thystVAL, (byte) 'H', activity);
        if (spinHys.getSelectedView() != null)
            ((TextView) spinHys.getSelectedView()).setTextColor(iColor);


    }



    public void mFunSpinnerOnItemClick() {
         //other
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
                    System.out.println("<><><>###^^^^ call");
                    CommonData.bSpinClick = true;
                    if (i != 0)
                        checkSearch.setEnabled(true);
                    else {
                        checkSearch.setChecked(false);
                        checkSearch.setEnabled(false);
                    }
                    thystVAL = i;
                    CommonData.pacerDataPROG[9] = thystVAL;
                    validateParaMainSingle();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                    validateParaMainSingle();
                    validateCommonData();
                    blnkProgBut();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CommonData.bSpinClick = true;
                CommonData.tbHystON = b;
                CommonData.pacerDataPROG[8] &= 0x7F;
                if (b) {
                    hysAdapter = funSetArrayAdapterString(getActivity(), Arrays.asList(CommonData.hystArray297), thystVAL, (byte) 'H');
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
                    checkSearch.setChecked(false);
                    CommonData.pacerDataPROG[10] &= 0x1F;
                    spinHys.setVisibility(View.INVISIBLE);
                    checkSearch.setVisibility(View.GONE);
                }
                validateParaMainSingle();
                validateCommonData();
                blnkProgBut();
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
                    validateParaMainSingle();
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

                    System.out.println("<><><><>@@VVI PW");
                    //A PW + V PW
                    CommonData.pacerDataPROG[7] &= 0xF0;
                    CommonData.pacerDataPROG[7] += CommonData.tPW;
                    validateParaMainSingle();
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
                    validateParaMainSingle();
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
    }
}