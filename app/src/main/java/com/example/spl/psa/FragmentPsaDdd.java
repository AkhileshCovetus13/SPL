package com.example.spl.psa;

import static com.example.spl.helper.CommonData.ampArray;
import static com.example.spl.helper.CommonData.aviArray;
import static com.example.spl.helper.CommonData.modeArray;
import static com.example.spl.helper.CommonData.pwArray;
import static com.example.spl.helper.CommonData.rateArray;
import static com.example.spl.helper.CommonData.refArray297;
import static com.example.spl.helper.CommonData.senArray;
import static com.example.spl.helper.CommonData.senArrayV;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.spl.R;

public class FragmentPsaDdd extends Fragment {
    Spinner spinMode;
    Spinner spinRate;
    Spinner spinAvi;
    Spinner spinSens_a;
    Spinner spinSens_v;
    Spinner spinPw_a;
    Spinner spinPw_v;
    Spinner spinRef_a;
    Spinner spinRef_v;
    Spinner spinAmp_v;
    TextView ampAtext;
    ImageButton increment_aAmp,decrement_aAmp,increment_vAmp,decrement_vAmp;
    Button setValuea1,setValuea2,setValuea3,setValuea4;
    Button setValuev1,setValuev2,setValuev3,setValuev4,setValuev5,setValuev6;
    private ArrayAdapter<String> modeAdapter;
    private ArrayAdapter<Integer> rateAdapter;
    private ArrayAdapter<Integer> aviAdapter;
    private ArrayAdapter<Double> pwAdapter;
    private ArrayAdapter<Double> senAdapter;
    private ArrayAdapter<Integer> refAdapter;
    private ArrayAdapter<Double> ampAdapter;

    private RadioButton redA,greenA,redV,greenV;
    private Handler handler = new Handler();
    private boolean isRadioButtonVisible = true;

    private int currentIndexA = 21;
    private int currentIndexV = 21;

    private int index;
    private int indexV;


    public FragmentPsaDdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_dddview, container, false);

        redA = v.findViewById(R.id.redA);
        greenA = v.findViewById(R.id.greenA);
        redV = v.findViewById(R.id.redV);
        greenV = v.findViewById(R.id.greenV);
        ampAtext  = v.findViewById(R.id.ampAtext);
        increment_aAmp = v.findViewById(R.id.increment_aAmp);
        decrement_aAmp = v.findViewById(R.id.decrement_aAmp);
        setValuea1 = v.findViewById(R.id.setValuea1);
        setValuea2 = v.findViewById(R.id.setValuea2);
        setValuea3 = v.findViewById(R.id.setValuea3);
        setValuea4 = v.findViewById(R.id.setValuea4);
        spinAmp_v = v.findViewById(R.id.spinAmp);
        increment_vAmp = v.findViewById(R.id.increment_vAmp);
        decrement_vAmp = v.findViewById(R.id.decrement_vAmp);
        setValuev1 = v.findViewById(R.id.setValuev1);
        setValuev2 = v.findViewById(R.id.setValuev2);
        setValuev3 = v.findViewById(R.id.setValuev3);
        setValuev4 = v.findViewById(R.id.setValuev4);
        setValuev5 = v.findViewById(R.id.setValuev5);
        setValuev6 = v.findViewById(R.id.setValuev6);
        spinMode = v.findViewById(R.id.spinMode);
        spinRate = v.findViewById(R.id.spinRate);
        spinAvi = v.findViewById(R.id.spinAvi);
        spinSens_a = v.findViewById(R.id.spinSens_a);
        spinSens_v = v.findViewById(R.id.spinSens_v);
        spinPw_a = v.findViewById(R.id.spinPw_a);
        spinPw_v = v.findViewById(R.id.spinPw_v);
        spinRef_a = v.findViewById(R.id.spinRef_a);
        spinRef_v = v.findViewById(R.id.spinRef_v);





        return v;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toggleRadioButtonVisibility();

        ampAtext.setText(String.valueOf(ampArray[currentIndexA]));

        increment_aAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexA < ampArray.length - 1) {
                    currentIndexA++;
                    ampAtext.setText(String.valueOf(ampArray[currentIndexA]));
                }
            }
        });

        decrement_aAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexA > 0) {
                    currentIndexA--;
                    ampAtext.setText(String.valueOf(ampArray[currentIndexA]));
                }
            }
        });


        setValuea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=17;
                currentIndexA=index;
                ampAtext.setText(String.valueOf(ampArray[currentIndexA]));
            }
        });
        if (index==currentIndexA){
            setValuea1.setBackgroundResource(R.drawable.border);
        }


        setValuea2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndexA=19;
                ampAtext.setText(String.valueOf(ampArray[currentIndexA]));
            }
        });


        setValuea3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index=21;
                currentIndexA=index;
                ampAtext.setText(String.valueOf(ampArray[currentIndexA]));

            }
        });


        setValuea4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndexA=26;
                ampAtext.setText(String.valueOf(ampArray[currentIndexA]));
            }
        });
//        Button setValuea5 = v.findViewById(R.id.setValuea5);
//
//        setValuea5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentIndexA=43;
//                ampAtext.setText(String.valueOf(ampArray[currentIndexA]));
//            }
//        });
//        Button setValuea6 = v.findViewById(R.id.setValuea6);
//
//        setValuea6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentIndexA=46;
//                ampAtext.setText(String.valueOf(ampArray[currentIndexA]));
//            }
//        });




        ampAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,ampArray);
        spinAmp_v.setAdapter(ampAdapter);
        spinAmp_v.setSelection(21);
        spinAmp_v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                currentIndexV=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        


        increment_vAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexV < ampArray.length - 1) {
                    currentIndexV++;
                    spinAmp_v.setSelection(currentIndexV);
                }
            }
        });

        decrement_vAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexV > 0) {
                    currentIndexV--;
                    spinAmp_v.setSelection(currentIndexV);
                }
            }
        });
        
        setValuev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexV=17;
                currentIndexV=indexV;
                spinAmp_v.setSelection(currentIndexV);
            }
        });
        setValuev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexV=19;
                currentIndexV=indexV;
                spinAmp_v.setSelection(currentIndexV);
            }
        });
        setValuev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexV=21;
                currentIndexV=indexV;
                spinAmp_v.setSelection(currentIndexV);
            }
        });
        setValuev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexV=26;
                currentIndexV=indexV;
                spinAmp_v.setSelection(currentIndexV);
            }
        });
        setValuev5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexV=26;
                currentIndexV=indexV;
                spinAmp_v.setSelection(currentIndexV);
            }
        });
        setValuev6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexV=28;
                currentIndexV=indexV;
                spinAmp_v.setSelection(currentIndexV);
            }
        });








        modeAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_item,modeArray);
        spinMode.setAdapter(modeAdapter);
        spinMode.setSelection(2);

        spinMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String mode = parent.getItemAtPosition(i).toString();
                if (mode=="VVI") {
                    FragmentManager vvifrag = getParentFragmentManager();
                    vvifrag.beginTransaction().replace(R.id.frame, new FragmentPsaVvi()).commit();
                    vvifrag.beginTransaction().addToBackStack(null);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rateAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), R.layout.dropdown_item,rateArray);
        spinRate.setAdapter(rateAdapter);
        spinRate.setSelection(15);
        spinRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Integer rateArray = Integer.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        aviAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), R.layout.dropdown_item,aviArray);
        spinAvi.setAdapter(aviAdapter);
        spinAvi.setSelection(8);
        spinAvi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Integer aviArray = Integer.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        senAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,senArray);
        spinSens_a.setAdapter(senAdapter);
        spinSens_a.setSelection(3);
        spinSens_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Double rateArray = Double.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        senAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,senArrayV);
        spinSens_v.setAdapter(senAdapter);
        spinSens_v.setSelection(1);
        spinSens_v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Double rateArray = Double.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        pwAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,pwArray);
        spinPw_a.setAdapter(pwAdapter);
        spinPw_a.setSelection(5);
        spinPw_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Double rateArray = Double.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        pwAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,pwArray);
        spinPw_v.setAdapter(pwAdapter);
        spinPw_v.setSelection(5);
        spinPw_v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Double rateArray = Double.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        refAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), R.layout.dropdown_item,refArray297);
        spinRef_a.setAdapter(refAdapter);
        spinRef_a.setSelection(5);
        spinRef_a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Integer rateArray297 = Integer.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        refAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), R.layout.dropdown_item,refArray297);
        spinRef_v.setAdapter(refAdapter);
        spinRef_v.setSelection(5);
        spinRef_v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Integer rateArray297 = Integer.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private String[] getDisplayedValues(Double[] ampArray) {
        String[] displayedValues = new String[ampArray.length];
        for (int i = 0; i < ampArray.length; i++) {
            displayedValues[i] = String.valueOf(ampArray[i]);
        }
        return displayedValues;
    }
    private void blinkRadioButtonAnimation() {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(500);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        redA.startAnimation(anim);
        greenA.startAnimation(anim);
        redV.startAnimation(anim);
        greenV.startAnimation(anim);

    }
    private void toggleRadioButtonVisibility() {
        if (isRadioButtonVisible) {
            redA.setVisibility(View.INVISIBLE);
            greenA.setVisibility(View.INVISIBLE);
            redV.setVisibility(View.INVISIBLE);
            greenV.setVisibility(View.INVISIBLE);
            handler.removeCallbacksAndMessages(null);
        } else {
            redA.setVisibility(View.VISIBLE);
            greenA.setVisibility(View.VISIBLE);
            redV.setVisibility(View.VISIBLE);
            greenV.setVisibility(View.VISIBLE);
            blinkRadioButtonAnimation();
        }
        isRadioButtonVisible = !isRadioButtonVisible;

        // Schedule the method to run again after a certain delay
        handler.postDelayed(this::toggleRadioButtonVisibility, 1000);
    }
}