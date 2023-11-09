package com.example.spl.programmer.dualchamber;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.spl.R;
import com.example.spl.helper.CommonData;
import com.example.spl.model.StatisticsDual;
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

public class StatisticsGraphFragment extends Fragment {

    BarChart statChart;
    private final ArrayList<StatisticsDual> logArrayList = new ArrayList<>();


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
                    System.out.println("<><><>#####@@ "+strAction);
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
                        if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24) {
                            System.out.println("<><><>#####cal13333333333333");
                            StatisticsDual statisticsDual = update_Counts_DDD();
                            logArrayList.add(statisticsDual);
                        }

                        //else
                        //update_Counts();
                    }


                    p += nBytes;
                }
            }
            Collections.reverse(logArrayList);
            initBarGraph(FragmentStatisticsTabs.pos);


        } catch (IOException e) {
            e.printStackTrace();
        }
        //**************

    }

    public StatisticsDual update_Counts_DDD() {
        StatisticsDual statisticsDual = new StatisticsDual();
        float percPaceA = 0, percSensA = 0, percPaceV = 0, percSensV = 0;
        long tempTotalA, tempTotalV;
        //A Counts Total
        tempTotalA = CommonData.cntL80 + CommonData.cnt100;
        //V Counts Total
        tempTotalV = CommonData.cntPace + CommonData.cntSens;
        if (tempTotalA != 0) {
            percPaceA = (float) ((CommonData.cntL80 * 100) / (double) tempTotalA);
            percSensA = (float) ((CommonData.cnt100 * 100) / (double) tempTotalA);

        }
        if (tempTotalV != 0) {
            percPaceV = (float) ((CommonData.cntPace * 100) / (double) tempTotalV);
            percSensV = (float) ((CommonData.cntSens * 100) / (double) tempTotalV);

        }
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String curDateTime = dtFormat.format(new Date());
        if (CommonData.bshowStat) {
            String[] separated = CommonData.strDateTime.split(" ");
            statisticsDual.setmStrDate(separated[0]);
            statisticsDual.setmStrTime(separated[1]);
        } else {
            String[] separated = curDateTime.split(" ");
            statisticsDual.setmStrDate(separated[0]);
            statisticsDual.setmStrTime(separated[1]);
        }


        CommonData.bshowStat = false;

        String strText;
//A Pace
        strText = CommonData.cntL80 + " (" + new DecimalFormat("##.###").format(percPaceA) + " %)";
        statisticsDual.setmStrAtriumPaceCount(strText);
        statisticsDual.setmIntAtriumPaceCount(CommonData.cntL80);
        //statisticsDual.setmIntAtriumPaceCount(0);
//A Sens
        strText = CommonData.cnt100 + " (" + new DecimalFormat("##.###").format(percSensA) + " %)";
        //Total A Sens
        //statisticsDual.setmIntAtriumSensCount(Integer.parseInt(strText));
        statisticsDual.setmStrAtriumSensCount(strText);
        statisticsDual.setmIntAtriumSensCount(CommonData.cnt100);
        //statisticsDual.setmIntAtriumSensCount(0);

//V Pace
        strText = CommonData.cntPace + " (" + new DecimalFormat("##.###").format(percPaceV) + " %)";
        statisticsDual.setmStrVentriclePaceCount(strText);
        statisticsDual.setmIntVentriclePaceCount(CommonData.cntPace);
        //statisticsDual.setmIntVentriclePaceCount(0);
//V Sens
        strText = CommonData.cntSens + " (" + new DecimalFormat("##.###").format(percSensV) + " %)";
        //Total V Sens
        //statisticsDual.setmIntVentricleSensCount(Integer.parseInt(strText));
        //statisticsDual.setmStrVentriclePaceCount(strText);
        statisticsDual.setmStrVentricleSensCount(strText);
        statisticsDual.setmIntVentricleSensCount(CommonData.cntSens);
//AT Counts
        //AT Counts
        //statisticsDual.setmIntAtCount((int) CommonData.cnt120);
        statisticsDual.setmStrAtCount(String.valueOf(CommonData.cnt120));
//AF Counts
        if (CommonData.iPacerSelect == 24) {

            //AF Counts
            //statisticsDual.setmIntAfCount((int) CommonData.cnt140);
            statisticsDual.setmStrAfCount(String.valueOf(CommonData.cnt140));
            //Noise Counts
            //statisticsDual.setmIntNoise((int) CommonData.cntNoise); //Noise Counts
            statisticsDual.setmStrNoise(String.valueOf(CommonData.cntNoise)); //Noise Counts
            //Noise Pace Counts
            //statisticsDual.setmIntNoisePace((int) CommonData.cntNoisePace); //Noise Pace Counts
            statisticsDual.setmStrNoisePace(String.valueOf(CommonData.cntNoisePace)); //Noise Pace Counts
        } else {
            //Noise Counts
            //statisticsDual.setmIntNoise((int) CommonData.cntNoise); //Noise Counts
            statisticsDual.setmStrNoise(String.valueOf(CommonData.cntNoise)); //Noise Counts
            //Noise Pace Counts
            //statisticsDual.setmIntNoisePace((int) CommonData.cntNoisePace); //Noise Pace Counts
            statisticsDual.setmStrNoisePace(String.valueOf(CommonData.cntNoisePace)); //Noise Pace Counts
        }


        return statisticsDual;

    }

    public void initBarGraph(int pos){

        StatisticsDual statisticsDual = logArrayList.get(pos);

        float  percPaceA=0,percSensA=0,percPaceV=0,percSensV=0;
        long tempTotalA,tempTotalV;
        //A Counts Total
        tempTotalA= statisticsDual.getmIntAtriumPaceCount() + statisticsDual.getmIntAtriumSensCount();
        //V Counts Total
        tempTotalV= statisticsDual.getmIntVentriclePaceCount() + statisticsDual.getmIntVentricleSensCount();
        if (tempTotalA!=0) {
            percPaceA = (float)((statisticsDual.getmIntAtriumPaceCount() * 100) / (double) tempTotalA);
            percSensA =(float) ((statisticsDual.getmIntAtriumSensCount() * 100) / (double)tempTotalA);

        }
        if (tempTotalV!=0) {
            percPaceV = (float) ((statisticsDual.getmIntVentriclePaceCount() * 100) / (double) tempTotalV);
            percSensV = (float)((statisticsDual.getmIntVentricleSensCount() * 100) / (double) tempTotalV);

        }

        ArrayList<BarEntry> perCountsD = new ArrayList<>();

        perCountsD.add(new BarEntry(0,Float.parseFloat(new DecimalFormat("##.###").format(percPaceA))));
        perCountsD.add(new BarEntry(1,Float.parseFloat(new DecimalFormat("##.###").format(percSensA))));
        perCountsD.add(new BarEntry(3,Float.parseFloat(new DecimalFormat("##.###").format(percPaceV))));
        perCountsD.add(new BarEntry(4,Float.parseFloat(new DecimalFormat("##.###").format(percSensV))));


        BarDataSet statDataSet = new BarDataSet(perCountsD, " % Counts");
        //Values to show in decimal format
        statDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                return super.getFormattedValue(value);
            }
        });
        //Set Bar Colors
        statDataSet.setColors(Color.LTGRAY,Color.LTGRAY,Color.LTGRAY,Color.LTGRAY,Color.LTGRAY);

        final String[] statLabelsD = {"A Pace","A Sens","","V Pace","V Sens"};

        XAxis xAxis = statChart.getXAxis();
        YAxis yAxis = statChart.getAxisLeft();
        yAxis.setGranularity(1f);
        yAxis.setTextSize(18f);


        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setYOffset(10f);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.DKGRAY);
        xAxis.setTextSize(18f);//(12f);
        xAxis.setGranularity(1f);
        xAxis.setXOffset(10f);
        xAxis.setLabelRotationAngle(90);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return statLabelsD[(int)value];
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