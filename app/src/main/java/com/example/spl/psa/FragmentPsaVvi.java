package com.example.spl.psa;

import static com.example.spl.helper.CommonData.ampArray;
import static com.example.spl.helper.CommonData.modeArray;
import static com.example.spl.helper.CommonData.pwArray;
import static com.example.spl.helper.CommonData.rateArray;
import static com.example.spl.helper.CommonData.refArray297;
import static com.example.spl.helper.CommonData.senArray;

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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.spl.R;

public class FragmentPsaVvi extends Fragment {
    Spinner spinMode;
    Spinner spinRate;
    Spinner spinSens;
    Spinner spinPw;
    Spinner spinRef;
    Spinner spinAmp;
    Button setValue1;
    Button setValue2;
    Button setValue3;
    Button setValue4;
    private RadioButton red,green;
    private Handler handler = new Handler();
    private boolean isRadioButtonVisible = true;
    private int currentIndex=21;
    private int index;

    ImageButton incrementAmp;
    ImageButton decrementAmp;


    private ArrayAdapter<String> modeAdapter;
    private ArrayAdapter<Integer> rateAdapter;
    private ArrayAdapter<Double> pwAdapter;
    private ArrayAdapter<Double> senAdapter;
    private ArrayAdapter<Integer> refAdapter;
    private ArrayAdapter<Double> ampAdapter;
    int REQUEST_ENABLE_BT = 1;
//    SharedViewModel sharedViewModel = new ViewModelProvider(requireParentFragment()).get(SharedViewModel.class);



    public FragmentPsaVvi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_vvi_psa, container, false);
        red = v.findViewById(R.id.red);
        green = v.findViewById(R.id.green);
        spinAmp =v.findViewById(R.id.spinAmp);
        incrementAmp = v.findViewById(R.id.incrementAmp);
        decrementAmp = v.findViewById(R.id.decrementAmp);
        setValue1 = v.findViewById(R.id.setValue1);
        setValue2 = v.findViewById(R.id.setValue2);
        setValue3 = v.findViewById(R.id.setValue3);
        setValue4 = v.findViewById(R.id.setValue4);
        spinMode = v.findViewById(R.id.spinMode);
        spinRate = v.findViewById(R.id.spinRate);
        spinSens = v.findViewById(R.id.spinSens);
        spinPw = v.findViewById(R.id.spinPw);
        spinRef = v.findViewById(R.id.spinRef);
        return v;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        toggleRadioButtonVisibility();


        ampAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,ampArray);
        spinAmp.setAdapter(ampAdapter);
        spinAmp.setSelection(21);
        spinAmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                currentIndex=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        incrementAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < ampArray.length - 1) {
                    currentIndex++;
                    spinAmp.setSelection(currentIndex);
                }
            }
        });

        decrementAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex> 0) {
                    currentIndex--;
                    spinAmp.setSelection(currentIndex);
                }
            }
        });


        setValue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=17;
                currentIndex=index;
                spinAmp.setSelection(currentIndex);

            }

        });

        setValue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=19;
                currentIndex=index;
                spinAmp.setSelection(currentIndex);
            }
        });

        setValue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=21;
                currentIndex=index;
                spinAmp.setSelection(currentIndex);
            }
        });

        setValue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=26;
                currentIndex=index;
                spinAmp.setSelection(currentIndex);
            }
        });




        modeAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_item,modeArray);
        modeAdapter.setDropDownViewResource(R.layout.dropdown_item);
        spinMode.setAdapter(modeAdapter);
        spinMode.setSelection(1);
//        String selectedValue = sharedViewModel.getValue();

//        if (selectedValue != null) {
//            int position = modeAdapter.getPosition(selectedValue);
//            spinMode.setSelection(position);
//        }
        spinMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                String mode = parent.getItemAtPosition(i).toString();
//                sharedViewModel.setValue(mode);
                if (mode=="DDD"){
                    FragmentManager dddfrag = getParentFragmentManager();
                    dddfrag.beginTransaction().replace(R.id.frame, new FragmentPsaDdd()).commit();
                    dddfrag.beginTransaction().addToBackStack(null);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(getContext(),"Nothing",Toast.LENGTH_SHORT).show();
            }

        });





        rateAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), R.layout.dropdown_item,rateArray);
        rateAdapter.setDropDownViewResource(R.layout.dropdown_item);
        spinRate.setAdapter(rateAdapter);
        spinRate.setSelection(15);
        spinRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Integer rate = Integer.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        senAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,senArray);
        spinSens.setAdapter(senAdapter);
        spinSens.setSelection(3);
        spinSens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Double sens = Double.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        pwAdapter = new ArrayAdapter<Double>(getActivity().getApplicationContext(), R.layout.dropdown_item,pwArray);
        spinPw.setAdapter(pwAdapter);
        spinPw.setSelection(5);
        spinPw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Double pw = Double.valueOf(parent.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        refAdapter = new ArrayAdapter<Integer>(getActivity().getApplicationContext(), R.layout.dropdown_item,refArray297);
        spinRef.setAdapter(refAdapter);
        spinRef.setSelection(5);
        spinRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Integer ref = Integer.valueOf(parent.getItemAtPosition(i).toString());

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
        red.startAnimation(anim);
        green.startAnimation(anim);

    }
    private void toggleRadioButtonVisibility() {
        if (isRadioButtonVisible) {
            red.setVisibility(View.INVISIBLE);
            green.setVisibility(View.INVISIBLE);
            handler.removeCallbacksAndMessages(null);
        } else {
            red.setVisibility(View.VISIBLE);
            green.setVisibility(View.VISIBLE);
            blinkRadioButtonAnimation();
        }
        isRadioButtonVisible = !isRadioButtonVisible;

        // Schedule the method to run again after a certain delay
        handler.postDelayed(this::toggleRadioButtonVisibility, 1000);
    }

}