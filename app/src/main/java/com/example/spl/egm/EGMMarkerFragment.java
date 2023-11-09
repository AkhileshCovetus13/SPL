package com.example.spl.egm;

import static com.example.spl.programmer.ActivityProgrammer.createRequest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.spl.R;
import com.example.spl.helper.CommonData;

import java.text.DecimalFormat;

public class EGMMarkerFragment extends Fragment {

    public static boolean bMarkInitFlag = false;
    public static boolean bVPThr = false;
    public static boolean bVPWThr = false;
    public static boolean bVSThr = false;

    public static boolean bFst51;
    public static boolean bthrScreen = false;
    public static boolean bFlg51 = false;
    public static boolean bAPrevmark;
    public static boolean bVPrevmark;
    public static boolean bMarkON = false;
    public static boolean bFileWrite = false;
    public static boolean bThrChamber = true;

    public static ToggleButton butMarker;
    public static Button butThrNext;

    public static ImageView idMrkViewMain;
    private static TextView txtMarkPeriod;
    private static TextView txtMrkVPrd;
    private static TextView txtAVDelay;
    private static TextView txtShowHRVal;
    public static Bitmap markBitmap;
    private static Canvas markCanvas;
    private static Paint rulerPaint;
    private static Paint ecgPaint;
    private static Paint clrPaint;
    private static Paint markPaint;
    private static Paint txtPaint;
    private static Paint gridPaint;//To select color

    private static String strChamberPace;
    private static String strChamberSens;
    private static Rect clrRect;
    private static Rect markRect;

    public static int vWidth;
    public static int vHeight;
    public static int ecgBaseLine;
    public static int oldEcgX;
    public static int ecgX;
    public static int oldecgData;
    public static int ecgData;
    public static int ecgScale;
    public static int hrMaxVal;
    public static int ecgSens;
    public static int offset;
    public static int hrIDX;
    public static int vLine; // ECG variables

    private static float HR = 0;
    private static int val35;
    private static int prevVal35;
    private static int val51;
    private static int aMarkPos;
    private static int vMarkPos;
    private static int aMarkPosPrev;
    private static int vMarkPosPrev;
    private static int vMarkPrd;
    private static int vMarkPrdDisp;
    public static int aMarkVal;
    public static int vMarkVal;
    public static int curAVD;
    public static int prevAVD;
    public static int vaPrd;
    public static int prevAmrk;
    private static boolean bFlgDisp = false;
    private static boolean bAmrkOK = false;
    static String strPrd = ""; // To display pulse intervals
    private static int n;
    private final CommonData para = new CommonData(); // Parameter class object to access ECG data
    private final String[] thrPara = new String[]{"Amp", "PW", "Sens"};
    private final String[] chamber = new String[]{"A", "V"};
    private final String[] ecgGain = new String[]{"Gain 0", "Gain 1", "Gain 2"};
    private Spinner selectThrChSpn;

    static int incSize;
    static int markHeight; //var for ECG grid size
    static float txtFontSize; //Var for marker font size
    Spinner selectThrParaSpn;
    Spinner selectEcgGainSpn;



  /*  public static EGMMarkerFragment newInstance(String param1, String param2) {
        EGMMarkerFragment fragment = new EGMMarkerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        butThrNext.setEnabled(false);
        bMarkInitFlag = false;
        CommonData.bThrNext = false;
        CommonData.bThrStop = false;

        ArrayAdapter<String> chamberAdapter = new ArrayAdapter<>(getActivity(), R.layout.para_layout, chamber);
        selectThrChSpn.setAdapter(chamberAdapter);
        selectThrChSpn.setSelection(1);

        butThrNext.setVisibility(View.GONE);
        selectThrChSpn.setVisibility(View.GONE);
        selectThrParaSpn.setVisibility(View.GONE);
        if (CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 0x1B || CommonData.iPacerSelect == 2 || CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13) //SSI models
        {
            txtMrkVPrd.setVisibility(View.GONE);// invisible
            txtAVDelay.setVisibility(View.GONE);// invisible
        }

        ArrayAdapter<String> thrParaAdapter = new ArrayAdapter<>(getActivity(), R.layout.para_layout, thrPara);
        selectThrParaSpn.setAdapter(thrParaAdapter);
        selectThrParaSpn.setSelection(0); //Default Pacing

        bVPThr = true; //Default V pacing threshold
        //Load ECG Gain Values
        ArrayAdapter<String> ecgGainAdapter = new ArrayAdapter<>(getActivity(), R.layout.para_layout, ecgGain);
        selectEcgGainSpn.setAdapter(ecgGainAdapter);
        selectEcgGainSpn.setSelection(0);
        //Threshold Next Button Click Method
        butThrNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonData.bThrNext = true;
                CommonData.actionCode = 0x06;
                //mainAct.createRequest((byte) 0x06);
                CommonData.strAction = "Threshold";
            }
        });

        selectThrParaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bVPThr = position == 0;
                bVPWThr = position == 1;
                bVSThr = position == 2;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Select Chamber
        selectThrChSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //if V
                bThrChamber = position != 0; //if A
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Select ECG Gain
        selectEcgGainSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position < CommonData.prevGainPos) {
                    CommonData.actionCode = 0x15;
                    //mainAct.createRequest((byte) 0x15);
                    if (CommonData.prevGainPos - position == 2)
                        ;// if from 2 to 0 then call gain down Twice
                    // mainAct.createRequest((byte) 0x15);
                }
                if (position > CommonData.prevGainPos) {
                    CommonData.actionCode = 0x14;
                    //mainAct.createRequest((byte) 0x14);
                    if (position - CommonData.prevGainPos == 2)
                        ; // if from 0 to 2 then call gain up Twice
                    // mainAct.createRequest((byte) 0x14);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ecgData = 128;
        oldecgData = 128;
        Toast.makeText(getActivity(), "Press Start Button to start", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marker_layout, container, false);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (dm.widthPixels < 1200) {
            incSize = 24;
            txtFontSize = 14;
            markHeight = 25;
        } else if (dm.widthPixels < 1600) {
            incSize = 36;
            txtFontSize = 18;
            markHeight = 45;
        } else {
            incSize = 48;
            txtFontSize = 22;
            markHeight = 65;
        }

        butMarker = view.findViewById(R.id.idBtMark);
        butThrNext = view.findViewById(R.id.idBtThr);
        selectThrParaSpn = view.findViewById(R.id.idThrSelectSpn);
        selectEcgGainSpn = view.findViewById(R.id.idecgGainSpn);
        selectThrChSpn = view.findViewById(R.id.idThrChSpn);
        idMrkViewMain = (ImageView) view.findViewById(R.id.idMrkViewMain);
        txtMarkPeriod = view.findViewById(R.id.idMrkPeriod);
        txtMrkVPrd = view.findViewById(R.id.idMrkVPrd);
        txtAVDelay = view.findViewById(R.id.idAVPeriod);
        txtShowHRVal = view.findViewById(R.id.idShowHRVal);



        return view;
    }

    //marker On/Off Button
    public static void markONOFF(boolean checked) {

        if (checked) {
            //If Marker Start
            //debug updateMarkAnnotation();
            startMarker();
        } else
            //If Marker Stops
            stopMarker();

    }

    public static void updateMarkAnnotation() {
        //******** SSI Models **********
        //set annotation as per mode
        if (CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 0x1B || CommonData.iPacerSelect == 2 || CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13) {
            //Old SSI
            if (CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 0x1B || CommonData.iPacerSelect == 2) {
                if ((CommonData.iMode >= 0 && CommonData.iMode <= 3) || (CommonData.iMode >= 8 && CommonData.iMode <= 10)) {
                    markPaint.setColor(Color.BLACK);
                    txtPaint.setColor(Color.BLACK);
                    strChamberPace = "VP";
                    strChamberSens = "VS";
                } else if ((CommonData.iMode >= 4 && CommonData.iMode <= 7) || (CommonData.iMode >= 11 && CommonData.iMode <= 13)) {
                    markPaint.setColor(Color.RED);
                    txtPaint.setColor(Color.RED);
                    strChamberPace = "AP";
                    strChamberSens = "AS";
                }
            }
            //New SSI
            if (CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13) {
                if ((CommonData.iMode >= 0 && CommonData.iMode <= 3) || (CommonData.iMode >= 19 && CommonData.iMode <= 21)) {
                    markPaint.setColor(Color.BLACK);
                    txtPaint.setColor(Color.BLACK);
                    strChamberPace = "VP";
                    strChamberSens = "VS";
                } else if ((CommonData.iMode >= 4 && CommonData.iMode <= 7) || (CommonData.iMode >= 22 && CommonData.iMode <= 24)) {
                    markPaint.setColor(Color.RED);
                    txtPaint.setColor(Color.RED);
                    strChamberPace = "AP";
                    strChamberSens = "AS";
                }
            }

            if (bVPWThr) //If PW Threshold then show PW Val
            {
                if (CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13) //New Models
                    strChamberPace = strChamberPace + " ( " + CommonData.pwArray747[CommonData.iPW] + " ms)";
                else
                    strChamberPace = strChamberPace + " ( " + CommonData.pwArray[CommonData.iPW] + " ms)";
            } else // Show AMP Val
            {
                if (CommonData.iPacerSelect == 2)
                    strChamberPace = strChamberPace + " ( " + CommonData.amparray[CommonData.iAmp] + " V)";
                else if (CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13) //New Models
                    strChamberPace = strChamberPace + " ( " + CommonData.ampArrayP[CommonData.iAmp] + " V)";
                else
                    strChamberPace = strChamberPace + " ( " + CommonData.amparray297[CommonData.iAmp] + " V)";
            }

            if (CommonData.iPacerSelect == 2)
                strChamberSens = strChamberSens + " ( " + CommonData.senArray297old[CommonData.iSen] + " mV";
            else if (CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13) //New Models
                strChamberSens = strChamberSens + " ( " + CommonData.senArrayP[CommonData.iSen] + " mV";
            else {
                if (CommonData.iSrno < 3390)
                    strChamberSens = strChamberSens + " ( " + CommonData.senArray297old[CommonData.iSen] + " mV";
                else
                    strChamberSens = strChamberSens + " ( " + CommonData.senArray297old[CommonData.iSen] + " mV";
            }
        }
        //******** End SSI Models **********
        //******** DDD Models old and new**********
        if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24 || CommonData.iPacerSelect == 0x1C) {
            strChamberPace = " ";
            strChamberSens = " ";
            //if (vMarkVal>0) {
            if (bThrChamber || vMarkVal > 0) {
                markPaint.setColor(Color.BLACK);
                txtPaint.setColor(Color.BLACK);
                strChamberPace = "VP";
                strChamberSens = "VS";
                if (bVPWThr) //If PW Threshold then show V PW Val
                {
                    strChamberPace = strChamberPace + " ( " + CommonData.pwArray747[CommonData.iPW] + " ms)";
                } else // Show V AMP Val
                {
                    if (CommonData.iPacerSelect == 0x1C)
                        strChamberPace = strChamberPace + " ( " + CommonData.ampArrayP[CommonData.iAmp] + " V)";
                    else
                        strChamberPace = strChamberPace + " ( " + CommonData.amparray297[CommonData.iAmp] + " V)";
                }
                //Show Sens Val
                if (CommonData.iPacerSelect == 0x1C)
                    strChamberSens = strChamberSens + " ( " + CommonData.senArrayP[CommonData.iSen] + " mV";
                else
                    strChamberSens = strChamberSens + " ( " + CommonData.senArray297[CommonData.iSen] + " mV";

            }
            //if (aMarkVal>0) {
            else if (!bThrChamber || aMarkVal > 0) {
                markPaint.setColor(Color.RED);
                txtPaint.setColor(Color.RED);
                strChamberPace = "AP";
                strChamberSens = "AS";
                if (bVPWThr) //If PW Threshold then show A PW Val
                    strChamberPace = strChamberPace + " ( " + CommonData.pwArray747[CommonData.iAPW] + " ms)";
                else // Show A AMP Val
                {
                    if (CommonData.iPacerSelect == 0x1C)
                        strChamberPace = strChamberPace + " ( " + CommonData.ampArrayP[CommonData.iAAmp] + " V)";
                    else
                        strChamberPace = strChamberPace + " ( " + CommonData.amparray297[CommonData.iAAmp] + " V)";
                }
                //Show Sens Val
                if (CommonData.iPacerSelect == 0x1C)
                    strChamberSens = strChamberSens + " ( " + CommonData.aSenArrayC[CommonData.iASen] + " mV";
                else
                    strChamberSens = strChamberSens + " ( " + CommonData.aSenArray[CommonData.iASen] + " mV";
            }
        }

        //******** End DDD Models **********
    }

    public void setThrChamber() {
        if ((CommonData.iMode >= 0 && CommonData.iMode <= 3) || (CommonData.iMode >= 19 && CommonData.iMode <= 21)) //All VVI(R) modes
        {
            selectThrChSpn.setSelection(1);
            bThrChamber = true;
        }
        if ((CommonData.iMode >= 4 && CommonData.iMode <= 7) || (CommonData.iMode >= 22 && CommonData.iMode <= 24)) //All AAI(R) modes
        {
            selectThrChSpn.setSelection(0);
            bThrChamber = false;
        }
        if (CommonData.iMode >= 8 && CommonData.iMode <= 18) //All DDD(R) modes
        {
            selectThrChSpn.setSelection(1); //Default V chamber
            bThrChamber = true;
        }
    }

    public static void draw_marker(){
        System.out.println("<><><>SPL draw marker");

        if (!bMarkInitFlag)
        {
            //Initialize variables
            initMarker();

        }

        if (bthrScreen) { //Threshold
            if (CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 0x1B ||
                    CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13 || CommonData.iPacerSelect == 0x1C )
            {
                if (CommonData.iPacerSelect == 0x1C) // New Charak
                    decodeMarker_DDD();
                else if (CommonData.iPacerSelect ==25 || CommonData.iPacerSelect ==0x1B )
                    decodeMarker();
                else
                    decodeThreshold(); //Decode Threshold time and value
            }
            else
            {
                decodeThreshold(); //Decode Threshold time and value
            }
        }
        else  //Marker
        {
            if (CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 0x1B || CommonData.iPacerSelect == 0x02 ||
                    CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13)
                decodeMarker(); // Decode Marker time and Value
            else
                decodeMarker_DDD();
        }
        updateMarkAnnotation(); //to display text

        //if (bFileWrite)
            //CommonData.markerFileWrite();

        // Rectangle to clear area
        clrRect.left = ecgX ;
        clrRect.top = 7;
        clrRect.right = ecgX+incSize;
        clrRect.bottom = vHeight - 6;

        markCanvas.drawRect(clrRect,clrPaint);
        //Draw Marker Base Line

        //Draw Marker Base Line
        if((ecgX+incSize)<= (vWidth-6)) {
            markCanvas.drawLine(ecgX, (float) vHeight / 4, ecgX + incSize, (float) vHeight / 4, rulerPaint);
        }
        else{
            markCanvas.drawLine(ecgX, (float)vHeight / 4, vWidth-6, (float) vHeight / 4, rulerPaint);
        }
        //Draw ECG / Marker
        int markIndex = 0;
        int ecgDataIndex;
        for(ecgDataIndex =2; ecgDataIndex <35; ecgDataIndex++) {

            ecgData = (CommonData.markerData[ecgDataIndex] + CommonData.markerData[ecgDataIndex + 1]+ CommonData.markerData[ecgDataIndex + 2]) / 3;
            //for Calculating heart rate
            if (hrMaxVal < ecgData) {

                //   If hrDiffCnt >2 Then
                // hrDiff(0) = hrDiff(1) 'Shift left
                // hrDiff(1) = hrDiff(2) 'Shift left
                // hrDiffCnt = 2 ' current value index
                // End If
                // hrDiff(hrDiffCnt) = I 'For Slew Rate
                // hrDiffCnt = hrDiffCnt + 1
                hrMaxVal = ecgData;
                hrIDX = ecgDataIndex;
                //'hrDiff = b(n + 2 + p + 2) - b(n + 1 + p + 2) ' For Slew Rate
                //End If
            }
            // vertical grid line
            if (vLine % 22 == 0)
                markCanvas.drawLine(ecgX, 6, ecgX, vHeight - 6, gridPaint);

            vLine += 1;
            if (vLine >= vWidth - 6)
                vLine = 1;

            ecgData = (255 - ecgData);
            ecgData = ecgData / ecgScale; //: I = I + offset
            ecgData += ecgBaseLine;
            ecgDataIndex += 2;
            markCanvas.drawLine(oldEcgX, oldecgData, ecgX, ecgData, ecgPaint);
            oldecgData = ecgData;
            oldEcgX = ecgX;
            ecgX+=incSize/12;

            if (ecgX >= vWidth - 6) {
                ecgX = 6;
                oldEcgX = 6;
                clrRect.left = ecgX-1 ;
                clrRect.top = 7;
                clrRect.right = ecgX+incSize;
                clrRect.bottom = vHeight - 6;

                markCanvas.drawRect(clrRect,clrPaint); // clear area
                markCanvas.drawRect(markRect,rulerPaint); //
                markCanvas.drawLine(ecgX, (float)vHeight / 4, ecgX + incSize, (float)vHeight / 4, rulerPaint); // Base line
            }
            //A Mark
            if (markIndex ==aMarkPos)
            {
                if (aMarkVal ==3) {
                    markCanvas.save();

                    if (ecgX>17) {
                        markCanvas.drawLine(ecgX - aMarkPosPrev, (float)vHeight / 4, ecgX - aMarkPosPrev,(float) vHeight / 4 - markHeight, markPaint); //  Pace
                        markCanvas.rotate(-90,ecgX- aMarkPosPrev-2,(float)vHeight/4+70);
                        markCanvas.drawText(strChamberPace, ecgX - aMarkPosPrev -2,(float)vHeight / 4 +70, txtPaint);
                    }
                    else {
                        markCanvas.drawLine(ecgX,(float)vHeight / 4, ecgX,(float)vHeight / 4 - markHeight, markPaint); // Pace
                        markCanvas.rotate(-90,ecgX-2,(float)vHeight/4+70);
                        markCanvas.drawText(strChamberPace, ecgX - 2,(float)vHeight / 4 +70, txtPaint);
                    }
                    markCanvas.restore();
                    aMarkVal = 0;
                    aMarkPosPrev=0;
                    aMarkPos = 0x0F;
                }
                else if (aMarkVal ==1) {
                    markCanvas.save();
                    if (ecgX>17) {
                        markCanvas.drawLine(ecgX - aMarkPosPrev,(float)vHeight / 4, ecgX - aMarkPosPrev,(float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.rotate(-90,ecgX-aMarkPosPrev-2,(float)vHeight/4-5);
                        markCanvas.drawText(strChamberSens,ecgX-aMarkPosPrev-2,(float)vHeight/4-5,txtPaint);

                    }
                    else
                    {
                        markCanvas.drawLine(ecgX,(float)vHeight / 4, ecgX,(float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.rotate(-90,ecgX-2,(float)vHeight/4-5);
                        markCanvas.drawText(strChamberSens,ecgX-2,(float)vHeight/4-5,txtPaint);
                    }
                    markCanvas.restore();
                    aMarkVal = 0;
                    aMarkPosPrev = 0;
                    aMarkPos = 0x0F;
                }
                else if (aMarkVal ==6) {
                    //AAT Mode
                    if (ecgX>17) {
                        markCanvas.drawLine(ecgX - aMarkPosPrev,(float)vHeight / 4, ecgX - aMarkPosPrev,(float)vHeight / 4 - markHeight, markPaint); //  Pace
                        markCanvas.drawLine(ecgX - aMarkPosPrev, (float)vHeight / 4, ecgX - aMarkPosPrev, (float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.drawText(strChamberPace, ecgX - aMarkPosPrev -2, (float)vHeight / 4 +70, txtPaint);
                        markCanvas.drawText(strChamberSens,ecgX-aMarkPosPrev-2,(float)vHeight/4-5,txtPaint);
                    }
                    else {
                        markCanvas.drawLine(ecgX , (float)vHeight / 4, ecgX , (float)vHeight / 4 - markHeight, markPaint); //  Pace
                        markCanvas.drawLine(ecgX, (float)vHeight / 4, ecgX, (float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.drawText(strChamberPace, ecgX-2, (float)vHeight / 4 +70, txtPaint);
                        markCanvas.drawText(strChamberSens,ecgX-2,(float)vHeight/4-5,txtPaint);
                    }
                    aMarkVal = 0;
                    aMarkPosPrev = 0;
                    aMarkPos = 0x0F;
                }
                else{
                    aMarkPosPrev=11;
                }
            }
            //End A Mark
            //V Mark
            if (markIndex ==vMarkPos)
            {
                if ((vMarkVal & 0x06)==4) {
                    markCanvas.save();

                    if (ecgX>17) {
                        markCanvas.drawLine(ecgX - vMarkPosPrev, (float)vHeight / 4, ecgX - vMarkPosPrev, (float)vHeight / 4 - markHeight, markPaint); //  Pace
                        markCanvas.rotate(-90,ecgX- vMarkPosPrev-2,(float)vHeight/4+70);
                        markCanvas.drawText(strChamberPace, ecgX - vMarkPosPrev -2, (float)vHeight / 4 +70, txtPaint);
                    }
                    else {
                        markCanvas.drawLine(ecgX, (float)vHeight / 4, ecgX, (float)vHeight / 4 - markHeight, markPaint); // Pace
                        markCanvas.rotate(-90,ecgX-2,(float)vHeight/4+70);
                        markCanvas.drawText(strChamberPace, ecgX - 2, (float)vHeight / 4 +70, txtPaint);
                    }
                    markCanvas.restore();
                    vMarkVal = 0;
                    vMarkPosPrev=0;
                    vMarkPos = 0x0F;
                }
                else if ((vMarkVal & 0x06)==2) {
                    markCanvas.save();
                    if (ecgX>17) {
                        markCanvas.drawLine(ecgX - vMarkPosPrev, (float)vHeight / 4, ecgX - vMarkPosPrev, (float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.rotate(-90,ecgX-vMarkPosPrev-2,(float)vHeight/4-5);
                        markCanvas.drawText(strChamberSens,ecgX-vMarkPosPrev-2,(float)vHeight/4-5,txtPaint);

                    }
                    else
                    {
                        markCanvas.drawLine(ecgX, (float)vHeight / 4, ecgX, (float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.rotate(-90,ecgX-2,(float)vHeight/4-5);
                        markCanvas.drawText(strChamberSens,ecgX-2,(float)vHeight/4-5,txtPaint);
                    }
                    markCanvas.restore();
                    vMarkVal = 0;
                    vMarkPosPrev = 0;
                    vMarkPos = 0x0F;
                }
                else if ((vMarkVal & 0x06)==6) {
                    //VVT Mode
                    if (ecgX>17) {
                        markCanvas.drawLine(ecgX - vMarkPosPrev, (float)vHeight / 4, ecgX - vMarkPosPrev, (float)vHeight / 4 - markHeight, markPaint); //  Pace
                        markCanvas.drawLine(ecgX - vMarkPosPrev, (float)vHeight / 4, ecgX - vMarkPosPrev, (float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.drawText(strChamberPace, ecgX - vMarkPosPrev -2, (float)vHeight / 4 +70, txtPaint);
                        markCanvas.drawText(strChamberSens,ecgX-vMarkPosPrev-2,(float)vHeight/4-5,txtPaint);
                    }
                    else {
                        markCanvas.drawLine(ecgX , (float)vHeight / 4, ecgX , (float)vHeight / 4 - markHeight, markPaint); //  Pace
                        markCanvas.drawLine(ecgX, (float)vHeight / 4, ecgX, (float)vHeight / 4 + markHeight, markPaint); //  Sens
                        markCanvas.drawText(strChamberPace, ecgX-2, (float)vHeight / 4 +70, txtPaint);
                        markCanvas.drawText(strChamberSens,ecgX-2,(float)vHeight/4-5,txtPaint);
                    }
                    vMarkVal = 0;
                    vMarkPosPrev = 0;
                    vMarkPos = 0x0F;
                }
                else{
                    vMarkPosPrev=11;
                }
            }
            //End V Mark
            markIndex++;
        }

        //Draw Ruler
        markCanvas.drawLine(ecgX+2,6,ecgX+2,(float)vHeight-6,rulerPaint);
        idMrkViewMain.setImageBitmap(markBitmap);
        //*** Heart Rate Calculation ****
        if ((hrMaxVal - offset) >= ecgSens ) {
            HR = HR + (hrIDX * 3);
            if (HR > 300) {
                float fltHR =  ( 60000/HR);
                String strHR = "HR- " + new DecimalFormat("##").format(fltHR)+ "bpm";
                txtShowHRVal.setText(strHR);

                //txtShowHRVal.setText(getString(R.string.heartrate,fltHR));

                //    lblSlwRT.Caption = Format((hrDiff(2) - hrDiff(0)) / (18 * 32), "#.##") + " v/ms"
                //    ' slew rate V/ms
            }
            hrMaxVal = offset; //' baseline ' 81
            HR = 99 - (hrIDX * 3);
        }
        else{
            HR = HR + 99;

        }

        //*** Heart Rate Calculation END ****
    }

    private static void decodeMarker() {

        val35 = CommonData.markerData[35];
        if (CommonData.markerData[40] <= 2) {
            vMarkPrd += prdDiff(val35, prevVal35);

        } else {
            for (n = 0; n < CommonData.markerData[40]; n++) {
                if (CommonData.markerData[n + 41] == 0x51) {
                    n++;
                    val51 = CommonData.markerData[n + 41];
                    vMarkPos = prdDiff(val51, prevVal35) / 18;
                    vMarkPrd += prdDiff(val51, prevVal35);
                    prevVal35 = val51;
                } else if (CommonData.markerData[n + 41] == 0x35) {
                    n++;
                } else {
                    vMarkPrdDisp = vMarkPrd / 2;
                    vMarkPrd = prdDiff(val35, val51);
                    if (CommonData.iPacerID == 0x0E) {
                        vMarkVal = (CommonData.markerData[n + 41] & 0x0F);
                    } else if (CommonData.iPacerID == 0x19 || CommonData.iPacerID == 0x1B) {
                        if ((CommonData.markerData[n + 41] & 0xF0) == 0x10 || (CommonData.markerData[n + 41] & 0xF0) == 0x50)
                            vMarkVal = 4;
                        if ((CommonData.markerData[n + 41] & 0xF0) == 0x20)
                            vMarkVal = 2;
                        if ((CommonData.markerData[n + 41] & 0xF0) == 0x30)
                            vMarkVal = 6;
                    }

                    //'New Models
                    else if (CommonData.iPacerSelect == 0x13 || CommonData.iPacerSelect == 0x10) {

                        vMarkVal = (CommonData.markerData[n + 41] & 0x06);
                    }
                }
            }

        }

        prevVal35 = val35;
        // txtMarkPeriod.setText(vMarkPrdDisp +" ms");
        //strPrd = vMarkPrdDisp + getString(R.string.ms);
        txtMarkPeriod.setText(strPrd);

    }

    //Decode DDD marker
    private static void decodeMarker_DDD() {
        int mcnt, nt35, diff, mrkPos, tmpprd = 0, markValue;
        //'*** Decode marker parameters and calculate interval ***

        mrkPos = 0;
        mcnt = 41;
        val35 = CommonData.markerData[35];
        nt35 = CommonData.markerData[40] - 2;
        //'*** Dual Chamber
        if (nt35 > 0) {
            while (nt35 > 0) {
                if (CommonData.markerData[mcnt] == 81 && nt35 > 1) {
                    mcnt = mcnt + 1;
                    val51 = CommonData.markerData[mcnt];
                    diff = prdDiff(val51, prevVal35);
                    if (diff >= 0)
                        mrkPos = diff;

                    vMarkPrd += diff;
                    tmpprd = vMarkPrd;
                    prevVal35 = val51;
                    mcnt++;
                    nt35 -= 2;
                    bFlg51 = true;
                } else {
                    markValue = CommonData.markerData[mcnt];
                    //A Mark value
                    if ((markValue & 0x0F) == 1 || (markValue & 0x0F) == 3 || (markValue & 0x0F) == 0x0B || (markValue & 0x0F) == 0x06 || (markValue & 0x0F) == 0x08) {

                        if ((markValue & 0x0F) == 6) markValue = 0;
                        if (CommonData.iPacerSelect == 24 && (markValue & 0x0F) == 8)// 'Charak with AM
                        {
                            aMarkVal = 6;
                        } else {
                            aMarkVal = markValue & 0x03;
                        }
                        bAmrkOK = true;
                        if (aMarkVal > 0 && !bFlg51)
                            bAPrevmark = true;
                        prevAmrk = aMarkVal;
                        vaPrd = tmpprd; //'If A mark'
                        aMarkPos = (int) (mrkPos / 19.8);
                    }
                    // V Mark value
                    else if ((markValue & 0x0F) == 2 || (markValue & 0x0F) == 4 || (markValue & 0x0F) == 0x0C || (markValue & 0x0F) == 0x0E) {
                        vMarkVal = markValue & 6;

                        if (vMarkVal > 0 && !bFlg51)
                            bVPrevmark = true;

                        curAVD = tmpprd;

                        if ((aMarkVal > 0 || bVPrevmark) && (!bAPrevmark)) {
                            vMarkPos = aMarkPos + (int) (mrkPos / 19.8);
                        } else
                            vMarkPos = (int) (mrkPos / 19.8);
                    }

                    bFlgDisp = true;
                    vMarkPrd = 0;//prd = 0;
                    mcnt = mcnt + 1;
                    nt35 = nt35 - 1;
                    bFlg51 = false;

                } //End If //end of If ((b(mcnt) = 81 And nt35 > 1))
            }//End While
        }//End If 'End of if nt35>0
        bFlg51 = false;

        vMarkPrd += prdDiff(val35, prevVal35);

        prevVal35 = val35;
        //Show Intervals on screens
        int tVal;
        if (bFlgDisp) {
            bFlgDisp = false;

            int tmpMod = CommonData.modeCtrlWD747[CommonData.tMode] & 0x7C; //'remove R bit and noise bit

            if ((tmpprd / 2) > 17) {

                if (tmpMod == 0x14 || tmpMod == 4 || tmpMod == 0x1C || tmpMod == 0x10) {
                    // 'if AAI/AAIR modes

                    tVal = (vaPrd) / 2;
                    strPrd = "A- " + tVal;
                    txtMarkPeriod.setText(strPrd);
                    txtMrkVPrd.setText("0");
                    txtAVDelay.setText("0");

                    if (tmpMod == 0x10)
                        vaPrd = 0;
                } else if (tmpMod == 0x54 || tmpMod == 0x44 || tmpMod == 0x5c || tmpMod == 0x50) {

                    txtMarkPeriod.setText("0");

                    tVal = (curAVD) / 2;
                    strPrd = "V- " + tVal;
                    txtMrkVPrd.setText(strPrd);
                    txtAVDelay.setText("0");
                    //'OVO mode
                    if (tmpMod == 0x50)
                        curAVD = 0;
                }
                // DDD modes
                else {

                    if (aMarkVal > 0) {
                        tVal = (vaPrd + prevAVD) / 2;
                        strPrd = "A- " + tVal;
                        txtMarkPeriod.setText(strPrd);
                    }

                    if (vMarkVal > 0) {

                        if (bAmrkOK) {

                            tVal = (vaPrd + curAVD) / 2;
                            strPrd = ("V- ") + tVal;
                            txtMrkVPrd.setText(strPrd);
                            // 'VDD
                            if (tmpMod == 0x78) {

                                if (prevAmrk > 0) {

                                    tVal = (curAVD) / 2;
                                    strPrd = ("AVD- ") + tVal;
                                    txtAVDelay.setText(strPrd);
                                } else {

                                    txtMarkPeriod.setText("0");
                                    txtAVDelay.setText("0");
                                }
                            } else {

                                tVal = (curAVD) / 2;
                                strPrd = ("AVD- ") + tVal;
                                txtAVDelay.setText(strPrd);
                            }
                        } else {

                            txtMarkPeriod.setText("0");

                            tVal = (curAVD) / 2;
                            strPrd = ("V- ") + tVal;
                            txtAVDelay.setText(strPrd);

                            txtAVDelay.setText("0");
                        }

                        bAmrkOK = false;
                        prevAmrk = 0;

                        prevAVD = curAVD;
                    }
                }//End If 'end AAI/VVI/DDD mode
            } //End If 'End (Round(tmpprd / 2) > 17)
        }
    }

    //Decode Threshold Value
    private static void decodeThreshold() {

        val35 = CommonData.markerData[35];
        if (CommonData.markerData[40] <= 2) {
            vMarkPrd += prdDiff(val35, prevVal35);
        } else {
            for (n = 0; n < CommonData.markerData[40]; n++) {
                if (CommonData.markerData[n + 41] == 0x51) {
                    // To confirm both 51 values are received
                    bFst51 = !bFst51;

                    n++;
                    if (CommonData.markerData[n + 41] == 0x5c) {
                        CommonData.markerData[40] += 1;
                        n++;
                    }

                    val51 = CommonData.markerData[n + 41];
                    vMarkPos = prdDiff(val51, prevVal35) / 18;
                    vMarkPrd += prdDiff(val51, prevVal35);
                    prevVal35 = val51;
                } else if (CommonData.markerData[n + 41] == 0x35) {
                    n++;
                    if (!bFst51) {
                        vMarkPrdDisp = vMarkPrd / 2;
                        vMarkPrd = prdDiff(val35, val51);

                        //Marker value for new models
                        if (CommonData.iPacerSelect == 0x10 || CommonData.iPacerSelect == 0x13) {
                            if ((CommonData.markerData[n + 39] & 0x70) == 0x20) //Sensing Marker
                            {
                                //if ((para.iMode >= 4 && para.iMode<=7) ||(para.iMode >= 22 && para.iMode<=24)) //AAI Modes
                                //  aMarkVal=1;
                                //else //VVI Modes
                                vMarkVal = 2;

                            }
                            if ((CommonData.markerData[n + 39] & 0x70) == 0x40) //Pacing Marker
                            {
                                //if ((para.iMode >= 4 && para.iMode<=7) ||(para.iMode >= 22 && para.iMode<=24)) //AAI Modes
                                //  aMarkVal=3;
                                //else //VVI Modes
                                vMarkVal = 4;

                            }
                        }

                        // Other Models
                        else {

                            if (vMarkPrdDisp >= (60000 / CommonData.rateArray[CommonData.iRate]) - 10)
                                vMarkVal = 4; //Pace
                            else if (vMarkPrdDisp < (60000 / CommonData.rateArray[CommonData.iRate]) - 10)
                                vMarkVal = 2; //Sens
                        }
                    }
                }

            }

        }
        prevVal35 = val35;
        //txtMarkPeriod.setText(vMarkPrdDisp +" ms");

        String strPrd = String.valueOf(vMarkPrdDisp);  // + getString(R.string.ms);
        txtMarkPeriod.setText(strPrd);
    }

    //Find difference of prev period value and current value
    private static int prdDiff(int curVal, int prevVal) {
        if (curVal > prevVal) {
            return (curVal - prevVal);
        } else {
            return (0xFF - prevVal + curVal + 1);
        }

    }

    //Initialize marker window
    private static void initMarker() {
        //System.out.println("<><><>call......99999");

        bMarkInitFlag = true; //Flag to init only once
        Paint bitmapPaint = new Paint(Paint.DITHER_FLAG);

        rulerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rulerPaint.setColor(Color.GRAY);
        rulerPaint.setStyle(Paint.Style.STROKE);
        rulerPaint.setStrokeWidth(2);

        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setColor(Color.LTGRAY);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(0);

        ecgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ecgPaint.setColor(Color.RED);
        ecgPaint.setStyle(Paint.Style.STROKE);

        clrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clrPaint.setColor(Color.WHITE);

        markPaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        markPaint.setStyle(Paint.Style.STROKE);
        markPaint.setStrokeWidth(2);


        txtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        txtPaint.setStyle(Paint.Style.STROKE);
        txtPaint.setStrokeWidth((float) 0.5);
        txtPaint.setTextSize(txtFontSize);

        vWidth = idMrkViewMain.getWidth();
        vHeight = idMrkViewMain.getHeight();
        clrRect = new Rect();
        markRect = new Rect(6, 6, vWidth - 6, vHeight - 4);
        ecgX = 6;//markX=6;
        oldEcgX = 6;
        //hLine=0;
        vLine = 0; // Grid lines
        //ECG/Marker init

        markBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        markCanvas = new Canvas(markBitmap);
        markCanvas.drawBitmap(markBitmap, 0, 0, bitmapPaint);
        markCanvas.drawRect(markRect, rulerPaint);

        ecgBaseLine = vHeight / 2;// + vHeight/4) + 5;
        oldecgData = ecgBaseLine + (vHeight / 4);
        ecgSens = 20;
        ecgScale = 6 - ((vHeight - ecgBaseLine - 5) / 22);
        if (ecgScale <= 0) {
            ecgScale = 1;
        }
        offset = ecgBaseLine - (132 / ecgScale);
    }


    public static void startMarker() {
        //ECG/Marker init

        ecgX = 6;//markX=6;
        oldEcgX = 6;
        // Rectangle to clear area
        clrRect.left = ecgX - 2;
        clrRect.top = 6;
        clrRect.right = vWidth - 6;
        clrRect.bottom = vHeight - 6;

        markCanvas.drawRect(clrRect, clrPaint);
        markCanvas.drawRect(markRect, rulerPaint);
        CommonData.bThrNext = false;
        CommonData.actionCode = 0x11;
        createRequest((byte) 0x11);
        CommonData.strAction = "Marker";
        //If Marker Start
        /*if (!mainAct.bthrScreen) {
            CommonData.actionCode = 0x11;
            mainAct.createRequest((byte) 0x11);
            CommonData.strAction ="Marker";
        }
        //If Threshold Test Start
        else{
            CommonData.actionCode = 0x1B;
           // mainAct.createRequest((byte) 0x1B);
            CommonData.strAction ="Threshold";
        }*/

    }

    public static void stopMarker() {
        strPrd = "";//clear string
        //ECG/Marker init

        ecgX = 6;//markX=6;
        oldEcgX = 6;
        // Rectangle to clear area
        clrRect.left = ecgX - 2;
        clrRect.top = 6;
        clrRect.right = vWidth - 6;
        clrRect.bottom = vHeight - 6;

        markCanvas.drawRect(clrRect, clrPaint);
        markCanvas.drawRect(markRect, rulerPaint);
        bMarkON = false;
        CommonData.bThrNext = false;

        bFileWrite = false; //No File Write
        butThrNext.setEnabled(false); //NEXT button Disabled
        CommonData.actionCode = 0x18;
        //If Marker Stops
       /* if (!mainAct.bthrScreen) {
            CommonData.actionCode = 0x18;
            //mainAct.createRequest((byte) 0x18);
        }
        //If Threshold Stops
        else{
            CommonData.bThrStop =true;
            CommonData.actionCode = 0x06;
           // mainAct.createRequest((byte) 0x06);
            System.arraycopy(CommonData.pacerData,0, CommonData.pacerDataPROG,0, CommonData.pacerData[1]+5); // Restore para
        }*/
        butMarker.setChecked(false);
        // mainAct.waitTimer.cancel();
        CommonData.decodeBytes();
        //mainAct.refreshScreen(CommonData.iPacerSelect);

    }
}