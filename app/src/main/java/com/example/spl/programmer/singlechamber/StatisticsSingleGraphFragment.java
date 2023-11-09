package com.example.spl.programmer.singlechamber;

import static com.example.spl.programmer.singlechamber.FragmentSingleStatisticsTabs.pos;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.spl.R;
import com.example.spl.helper.CommonData;
import com.example.spl.model.StatisticsSingle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class StatisticsSingleGraphFragment extends Fragment {

    BarChart statChart;
    private final ArrayList<StatisticsSingle> logArrayList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistics_graph, container, false);
        statChart = v.findViewById(R.id.idStatBarChart);

        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showlogpara();

    }





    private void showlogpara() {
        String strAction = "";
        // ***** Read Log Files *******
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SPLLOG/logfiles/";
        System.out.println("<><><>#####call@@ " + CommonData.filePath);
        System.out.println("<><><>#####call@@ " + CommonData.fileName);
        File dir = new File(CommonData.filePath);
        if (!dir.exists()) {
            System.out.println("<><><>#####call@@ 2");
            dir.mkdirs();
            //Toast.makeText(this, "Dir Created", Toast.LENGTH_SHORT).show();
        }

        //String fileName = new String(String.valueOf(para.iSrno).toString() + ".log");
        InputStream inputStream;
        try {
            File file1 = new File(dir, CommonData.fileName);
            if (!file1.exists())
                file1.createNewFile();

            inputStream = new FileInputStream(file1);
            byte[] rdBytes = new byte[(int) file1.length()];
            int nBytes;
            inputStream.read(rdBytes);

            for (int p = 0; p < file1.length(); p++) {
                System.out.println("<><><>#####callllll12");

                if (rdBytes[p] == 0x2) {
                    nBytes = rdBytes[p + 1]; // no of Bytes

                    byte[] activity = new byte[nBytes];
                    for (int i = 0; i < (nBytes); i++) {
                        activity[i] = (byte) (0x99 ^ (byte) (0xFF & rdBytes[p + i + 2]));
                    }
                    strAction = new String(activity);
                    p += nBytes; // Skip bytes

                }
                //Check for para bytes to skip
                if (rdBytes[p] == 0x03) {
                    System.out.println("<><><>#####myyyyyy");

                    nBytes = rdBytes[p + 1]; // no of Bytes to skip
                    System.out.println("<><><>#####@@ " + strAction);
                    //System.arraycopy(rdBytes, p+2, CommonData.pacerData, 0, rdBytes[p+3]+5);
                    if (strAction.contains("Statistics")) {
                        System.out.println("<><><>#####ommmmm   " + strAction);
                        CommonData.strDateTime = strAction.substring(strAction.indexOf("\n") + 1);

                        for (int q = 0; q < rdBytes[p + 1]; q++) {
                            CommonData.pacerDataPROG[q] = 0xFF & rdBytes[q + p + 2];
                        }

                        CommonData.decode_stat_counts();
                        //Restore pacerDataPROG
                        System.arraycopy(CommonData.pacerData, 0, CommonData.pacerDataPROG, 0, CommonData.pacerData[1] + 5);
                        CommonData.bshowStat = true;
                        if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24)
                            System.out.println("<><><>#####cal13333333333333");
                        else {
                            StatisticsSingle statisticsSingle = update_Counts();
                            logArrayList.add(statisticsSingle);

                        }

                        //else
                        //update_Counts();
                    }


                    p += nBytes;
                }
            }
            Collections.reverse(logArrayList);
            initBarGraph(pos);



        } catch (IOException e) {
            e.printStackTrace();
        }
        //**************

    }

    public StatisticsSingle update_Counts() {
        StatisticsSingle statisticsSingle = new StatisticsSingle();

        float percPace = 0, percSens = 0, perS80 = 0, perS100 = 0, perS120 = 0, perS140 = 0, perSG140 = 0;
        long tempTotal;
        tempTotal = CommonData.cntPace + CommonData.cntSens;
        if (tempTotal != 0) {

            percPace = (float) ((CommonData.cntPace * 100) / (double) tempTotal);
            percSens = (float) ((CommonData.cntSens * 100) / (double) tempTotal);
            perS80 = (float) ((CommonData.cntL80 * 100) / (double) tempTotal);
            perS100 = (float) ((CommonData.cnt100 * 100) / (double) tempTotal);
            perS120 = (float) ((CommonData.cnt120 * 100) / (double) tempTotal);
            perS140 = (float) ((CommonData.cnt140 * 100) / (double) tempTotal);
            perSG140 = (float) ((CommonData.cntG140 * 100) / (double) tempTotal);
        }

        SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String curDateTime = dtFormat.format(new Date());
        if (CommonData.bshowStat) {
            String[] separated = CommonData.strDateTime.split(" ");
            statisticsSingle.setmStrDate(separated[0]);
            statisticsSingle.setmStrTime(separated[1]);
        } else {
            String[] separated = curDateTime.split(" ");
            statisticsSingle.setmStrDate(separated[0]);
            statisticsSingle.setmStrTime(separated[1]);
        }

        CommonData.bshowStat = false;

        String strText;

        strText = CommonData.cntPace + " (" + new DecimalFormat("##.###").format(percPace) + " %)";
        SpannableString strSP = new SpannableString(strText);
        strSP.setSpan(new ForegroundColorSpan(Color.BLACK), strText.indexOf("("), strSP.length(), 0);
        //showPace.setText(strSP); //Total Pace
        statisticsSingle.setmStrPace(strText);
        statisticsSingle.setmfloatPace(percPace);

        strText = CommonData.cntSens + " (" + new DecimalFormat("##.###").format(percSens) + " %)";
        //strSP = new SpannableString(strText);
        //strSP.setSpan(new ForegroundColorSpan(Color.GRAY), strText.indexOf("("), strSP.length(), 0);
        //showSens.setText(strSP); //Total Sens
        statisticsSingle.setmStrSensMV(strText); //Total Sens
        statisticsSingle.setmfloatSensMV(percSens); //Total Sens

        //showNoise.setText(String.valueOf(CommonData.cntNoise)); //Noise Counts
        statisticsSingle.setmStrNoisePpm(String.valueOf(CommonData.cntNoise)); //Noise Counts
        statisticsSingle.setmlongNoisePpm(CommonData.cntNoise); //Noise Counts
        //showNoisP.setText(String.valueOf(CommonData.cntNoisePace)); //Noise Pace Counts
        statisticsSingle.setmStrNoisePacePpm(String.valueOf(CommonData.cntNoisePace)); //Noise Pace Counts
        statisticsSingle.setmlongNoisePacePpm(CommonData.cntNoisePace); //Noise Pace Counts

        strText = CommonData.cntL80 + " (" + new DecimalFormat("##.###").format(perS80) + " %)";
        strSP = new SpannableString(strText);
        strSP.setSpan(new ForegroundColorSpan(Color.GRAY), strText.indexOf("("), strSP.length(), 0);
        // showS80.setText(strSP); //Sens less than 80ppm
        statisticsSingle.setmStrS80Ppm(strText); //Sens less than 80ppm
        statisticsSingle.setmfloatS80Ppm(perS80);

        strText = CommonData.cnt100 + " (" + new DecimalFormat("##.###").format(perS100) + " %)";
        strSP = new SpannableString(strText);
        strSP.setSpan(new ForegroundColorSpan(Color.GRAY), strText.indexOf("("), strSP.length(), 0);
        //showS100.setText(strSP); //Sens less than 100ppm
        statisticsSingle.setmStrS100Ppm(strText);
        statisticsSingle.setmfloatS100Ppm(perS100);

        strText = CommonData.cnt120 + " (" + new DecimalFormat("##.###").format(perS120) + " %)";
        //strSP = new SpannableString(strText);
        //strSP.setSpan(new ForegroundColorSpan(Color.GRAY), strText.indexOf("("), strSP.length(), 0);
        //showS120.setText(strSP); //Sens less than 120ppm
        statisticsSingle.setmStrS120Ppm(strText);
        statisticsSingle.setmfloatS120Ppm(perS120);

        strText = CommonData.cnt140 + " (" + new DecimalFormat("##.###").format(perS140) + " %)";
        // strSP = new SpannableString(strText);
        //strSP.setSpan(new ForegroundColorSpan(Color.GRAY), strText.indexOf("("), strSP.length(), 0);
        //showS140.setText(strSP); //Sens less than 140ppm
        statisticsSingle.setmStrS140Ppm(strText);
        statisticsSingle.setmfloatS140Ppm(perS140);

        strText = CommonData.cntG140 + " (" + new DecimalFormat("##.###").format(perSG140) + " %)";
        //strSP = new SpannableString(strText);
        //strSP.setSpan(new ForegroundColorSpan(Color.GRAY), strText.indexOf("("), strSP.length(), 0);
        //showSG140.setText(strSP); //Sens more than 140ppm
        statisticsSingle.setmStrS140PpmB(strText);
        statisticsSingle.setmfloatS140PpmB(perSG140);

        //waitCirBar.setVisibility(View.GONE);
        //Clear All counts variables

        CommonData.strDateTime = "";
        //curDateTime ="";


        return statisticsSingle;
    }
    public void initBarGraph(int pos){

        StatisticsSingle statisticsSingle = logArrayList.get(pos);

        //*******************************
        //******* Draw BAR Graph ********
        //*******************************
        ArrayList<BarEntry> perCounts = new ArrayList<>();

        perCounts.add(new BarEntry(0, Float.parseFloat(new DecimalFormat("##.###").format(statisticsSingle.getmfloatPace()))));
        perCounts.add(new BarEntry(1, Float.parseFloat(new DecimalFormat("##.###").format(statisticsSingle.getmfloatSensMV()))));
        perCounts.add(new BarEntry(3, Float.parseFloat(new DecimalFormat("##.###").format(statisticsSingle.getmfloatS80Ppm()))));
        perCounts.add(new BarEntry(4, Float.parseFloat(new DecimalFormat("##.###").format(statisticsSingle.getmfloatS100Ppm()))));
        perCounts.add(new BarEntry(5, Float.parseFloat(new DecimalFormat("##.###").format(statisticsSingle.getmfloatS120Ppm()))));
        perCounts.add(new BarEntry(6, Float.parseFloat(new DecimalFormat("##.###").format(statisticsSingle.getmfloatS140Ppm()))));
        perCounts.add(new BarEntry(7, Float.parseFloat(new DecimalFormat("##.###").format(statisticsSingle.getmfloatS140PpmB()))));

        BarDataSet statDataSet = new BarDataSet(perCounts, " % Counts");
        //Values to show in decimal format
        statDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value);
            }
        });
        //Set Bar Colors
        statDataSet.setColors(Color.GRAY, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY);

        final String[] statLabels = {"Pace", "Sens", " ", "S<80", "S<100", "S<120", "S<140", "S>140"};

        YAxis yAxis = statChart.getAxisLeft();
        yAxis.setGranularity(1f);
        yAxis.setTextSize(18f);

        XAxis xAxis = statChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setYOffset(10f);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.DKGRAY);
        xAxis.setXOffset(10f);
        xAxis.setTextSize(18f);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(90);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return statLabels[(int) value];
            }
        });

        BarData statBarData = new BarData(statDataSet);
        statChart.setData(statBarData);

        statChart.getAxisRight().setEnabled(false);
        statChart.getDescription().setEnabled(false);
        statChart.setDrawValueAboveBar(true);
        statChart.getBarData().setValueTextSize(18f);
        statChart.getBarData().setBarWidth(0.6f);
        statChart.invalidate();

    }
}