package com.example.spl.programmer.dualchamber;

import static com.example.spl.programmer.ActivityProgrammer.createRequest;
import static com.example.spl.programmer.dualchamber.FragmentStatisticsTabs.callGraph;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.spl.R;
import com.example.spl.adapter.StatisticsDualAdapter;
import com.example.spl.helper.CommonData;
import com.example.spl.model.StatisticsDual;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class StatisticsFragment extends Fragment {

    public static ListView mListLogs;
    public static ArrayList<StatisticsDual> logArrayList = new ArrayList<>();
    private ArrayAdapter<String> logArrayAdapter;
    ImageView mImgRead;
    public static Activity activity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistics_list, container, false);
        activity = getActivity();
        mListLogs = v.findViewById(R.id.mListLogs);
        mImgRead = v.findViewById(R.id.mImgRead);

        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        System.out.println("<><><>#####callllll " + CommonData.filePath);
        showlogparaDual();

        mImgRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonData.actionCode = 7;
                CommonData.strAction = "Statistics";
                createRequest((byte) 7);
            }
        });

    }


    public static void showlogparaDual() {
        logArrayList.clear();
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
            StatisticsDualAdapter statisticsDualAdapter = new StatisticsDualAdapter(logArrayList, activity);
            mListLogs.setAdapter(statisticsDualAdapter);
            statisticsDualAdapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //**************

    }

    public static StatisticsDual update_Counts_DDD() {
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
        //statisticsDual.setmIntAtriumPaceCount(0);
//A Sens
        strText = CommonData.cnt100 + " (" + new DecimalFormat("##.###").format(percSensA) + " %)";
        //Total A Sens
        //statisticsDual.setmIntAtriumSensCount(Integer.parseInt(strText));
        statisticsDual.setmStrAtriumSensCount(strText);
        //statisticsDual.setmIntAtriumSensCount(0);

//V Pace
        strText = CommonData.cntPace + " (" + new DecimalFormat("##.###").format(percPaceV) + " %)";
        statisticsDual.setmStrVentriclePaceCount(strText);
        //statisticsDual.setmIntVentriclePaceCount(0);
//V Sens
        strText = CommonData.cntSens + " (" + new DecimalFormat("##.###").format(percSensV) + " %)";
        //Total V Sens
        //statisticsDual.setmIntVentricleSensCount(Integer.parseInt(strText));
        //statisticsDual.setmStrVentriclePaceCount(strText);
        statisticsDual.setmStrVentricleSensCount(strText);
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

    public static void openGraph(int p){
        FragmentStatisticsTabs.pos = p;
        callGraph();
    }
}