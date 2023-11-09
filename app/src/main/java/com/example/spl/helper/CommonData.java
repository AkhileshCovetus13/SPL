package com.example.spl.helper;

import static com.example.spl.programmer.ActivityProgrammer.funCheckValidationList;
import static java.lang.Math.round;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.spl.BluetoothDevicesActivity;
import com.example.spl.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CommonData {
    public static ProgressDialog pDialog;
    public static final String[] modeArray747 = new String[]{"VVI", "VOO", "VVT", "OVO", "AAI", "AOO", "AAT", "OAO", "DDD", "VDD", "DOO", "DDI", "ODO", "DVI",
            "DDDR", "VDDR", "DOOR", "DDIR", "DVIR", "VVIR", "VOOR", "VVTR", "AAIR", "AOOR", "AATR", "OFF"};
    public static final int[] modeCtrlWD747 = new int[]{0x54, 0x44, 0x5C, 0x50, 0x14, 0x04, 0x1C, 0x10, 0x7C, 0x78, 0x64, 0x74, 0x30, 0x34, 0xFC, 0xF8, 0xE4, 0xF4, 0xB4, 0xD4,
            0xC4, 0xDC, 0x94, 0x84, 0x9C, 0};

    public static final String[] modeArrayThr747 = new String[]{"VVI", "VOO", "AAI", "AOO", "DDD", "DOO", "VDD"};
    public static final int[] modeCtrlWDThr747 = new int[]{0x54, 0x44, 0x14, 0x04, 0x14, 0x64, 0x78};

    //Dual chamber
    public static final String[] modeArray = new String[]{"AAI", "VVI", "DDD", "VDD"};
    public static final Double[] senArrayV = new Double[]{0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5, 13.5, 14.5, 16.0};

    public static final Double[] pwArray747 = new Double[]{0.07, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5};
    public static final Double[] aSenArray = new Double[]{0.2, 0.6, 1.0, 1.4, 2.2, 3.0, 3.8, 4.6};
    public static final Integer[] refArray747 = new Integer[]{200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500};
    public static final Integer[] blnkArray = new Integer[]{21, 36, 52, 68};
    public static final Double[] senArray297 = new Double[]{0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5};
    public static Double[] senArray8820 = new Double[]{0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
    public static final String[] wenkebachArray = new String[]{"Wenkebach"};
    public static final String[] demoModelArray = new String[]{"Pinnacle R+ 297", "Charak 747R", "Pinnacle 8820AM"};
    //END Dual Chamber
    public static Integer[] ampctrlWD = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 16,
            33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 32};
    private final BluetoothDevicesActivity mainActivity = BluetoothDevicesActivity.getInstance();
    public static LinkedHashMap<String, Integer> map297_8820 = new LinkedHashMap<String, Integer>();
    public static LinkedHashMap<String, Integer> map8820 = new LinkedHashMap<String, Integer>();
    public static LinkedHashMap<String, Integer> map111111747 = new LinkedHashMap<String, Integer>();
    public static LinkedHashMap<String, Integer> mapOld = new LinkedHashMap<String, Integer>();
    public static LinkedHashMap<Double, Integer> mapPW297 = new LinkedHashMap<Double, Integer>();
    public static LinkedHashMap<Double, Integer> mapPW747 = new LinkedHashMap<Double, Integer>();
    public static LinkedHashMap<Double, Integer> mapPW8820 = new LinkedHashMap<Double, Integer>();
    public static LinkedHashMap<Double, Integer> mapAMP297 = new LinkedHashMap<Double, Integer>();
    public static LinkedHashMap<Integer, Integer> mapSlope297 = new LinkedHashMap<Integer, Integer>();
    public static int flagMainFragment;
    public static int flagSubFragment;
    public static boolean bSpinClick = false;
    public static boolean bParaOK = true;
    public static int iScreenFlag = 0;
    public static String tempFile = "";
    public static int tempFileOption = 0;
    public static LinkedHashMap<Integer, String> mapValidationMsg = new LinkedHashMap<Integer, String>();
    public static final String[] polArray=new String[]{"Bi","Uni"};
    static {
        map297_8820.put("VVI", 0x58);
        map297_8820.put("VOO", 0x50);
        map297_8820.put("VVT", 0x78);
        map297_8820.put("OVO", 0x48);
        map297_8820.put("AAI", 0x18);
        map297_8820.put("AOO", 0x10);
        map297_8820.put("AAT", 0x38);
        map297_8820.put("OAO", 0x08);

        map297_8820.put("VVIR", 0xD8);
        map297_8820.put("VOOR", 0xD0);
        map297_8820.put("VVTR", 0xF8);
        map297_8820.put("AAIR", 0x98);
        map297_8820.put("AOOR", 0x90);
        map297_8820.put("AATR", 0xB8);

        map297_8820.put("OOO", 0);

        map8820.put("VVI", 0x58);
        map8820.put("VOO", 0x50);
        map8820.put("VVT", 0x78);
        map8820.put("OVO", 0x48);
        map8820.put("AAI", 0x18);
        map8820.put("AOO", 0x10);
        map8820.put("AAT", 0x38);
        map8820.put("OAO", 0x08);
        map8820.put("OOO", 0);

        mapOld.put("VVI", 0x58);
        mapOld.put("VOO", 0x50);
        mapOld.put("VVT", 0x78);
        mapOld.put("OVO", 0x48);
        mapOld.put("AAI", 0x18);
        mapOld.put("AOO", 0x10);
        mapOld.put("AAT", 0x38);
        mapOld.put("OAO", 0x08);


       /* map747.put("VVI", 0x54);
        map747.put("VOO", 0x44);
        map747.put("VVT", 0x5C);
        map747.put("OVO", 0x50);
        map747.put("AAI", 0x14);
        map747.put("AOO", 0x04);
        map747.put("AAT", 0x1C);
        map747.put("OAO", 0x10);
        map747.put("DDD", 0x7C);
        map747.put("VDD", 0x78);
        map747.put("DOO", 0x64);
        map747.put("DDI", 0x74);
        map747.put("ODO", 0x30);
        map747.put("DVI", 0x34);
        map747.put("DDDR", 0xFC);
        map747.put("VDDR", 0xF8);
        map747.put("DOOR", 0xE4);
        map747.put("DDIR", 0xF4);
        map747.put("DVIR", 0xB4);
        map747.put("VVIR", 0xD4);
        map747.put("VOOR", 0xC4);
        map747.put("VVTR", 0xDC);
        map747.put("AAIR", 0x94);
        map747.put("AOOR", 0x84);
        map747.put("AATR", 0x9C);
        map747.put("OFF", 0);*/

        mapPW297.put(0.07, 2);
        mapPW297.put(0.10, 3);
        mapPW297.put(0.13, 4);
        mapPW297.put(0.16, 5);
        mapPW297.put(0.19, 6);
        mapPW297.put(0.22, 7);
        mapPW297.put(0.25, 8);
        mapPW297.put(0.3, 0x0A);
        mapPW297.put(0.4, 0x0D);
        mapPW297.put(0.5, 0x10);
        mapPW297.put(0.6, 0x14);
        mapPW297.put(0.7, 0x17);
        mapPW297.put(0.8, 0x1A);
        mapPW297.put(0.9, 0x1D);
        mapPW297.put(1.0, 0x21);
        mapPW297.put(1.1, 0x24);
        mapPW297.put(1.2, 0x27);
        mapPW297.put(1.3, 0x2A);
        mapPW297.put(1.4, 0x2E);
        mapPW297.put(1.5, 0x31);






        mapPW8820.put(0.07, 1);
        mapPW8820.put(0.10, 2);
        mapPW8820.put(0.13, 3);
        mapPW8820.put(0.16, 4);
        mapPW8820.put(0.19, 5);
        mapPW8820.put(0.22, 6);
        mapPW8820.put(0.25, 7);
        mapPW8820.put(0.3, 9);
        mapPW8820.put(0.4, 12);
        mapPW8820.put(0.5, 15);
        mapPW8820.put(0.6, 19);
        mapPW8820.put(0.7, 22);
        mapPW8820.put(0.8, 25);
        mapPW8820.put(0.9, 28);
        mapPW8820.put(1.0, 32);
        mapPW8820.put(1.1, 35);
        mapPW8820.put(1.2, 38);
        mapPW8820.put(1.3, 41);
        mapPW8820.put(1.4, 45);
        mapPW8820.put(1.5, 48);

        mapAMP297.put(0.2, 17);
        mapAMP297.put(0.3, 18);
        mapAMP297.put(0.5, 19);
        mapAMP297.put(0.6, 20);
        mapAMP297.put(0.8, 21);
        mapAMP297.put(0.9, 22);
        mapAMP297.put(1.1, 23);
        mapAMP297.put(1.3, 24);
        mapAMP297.put(1.4, 25);
        mapAMP297.put(1.6, 26);
        mapAMP297.put(1.7, 27);
        mapAMP297.put(1.9, 28);
        mapAMP297.put(2.0, 29);
        mapAMP297.put(2.2, 30);
        mapAMP297.put(2.4, 31);
        mapAMP297.put(2.5, 16);
        mapAMP297.put(2.7, 1);
        mapAMP297.put(2.8, 2);
        mapAMP297.put(3.0, 3);
        mapAMP297.put(3.1, 4);
        mapAMP297.put(3.3, 5);
        mapAMP297.put(3.5, 6);
        mapAMP297.put(3.6, 7);
        mapAMP297.put(3.8, 8);
        mapAMP297.put(3.9, 9);
        mapAMP297.put(4.1, 10);
        mapAMP297.put(4.2, 11);
        mapAMP297.put(4.4, 12);
        mapAMP297.put(4.5, 13);
        mapAMP297.put(4.7, 14);
        mapAMP297.put(4.9, 15);
        mapAMP297.put(5.0, 0);
        mapAMP297.put(5.6, 36);
        mapAMP297.put(6.3, 40);
        mapAMP297.put(6.9, 44);
        mapAMP297.put(7.5, 32);

        mapSlope297.put(1, 7);
        mapSlope297.put(2, 8);
        mapSlope297.put(3, 10);
        mapSlope297.put(4, 12);
        mapSlope297.put(5, 14);
        mapSlope297.put(6, 17);
        mapSlope297.put(7, 21);
        mapSlope297.put(8, 26);
        mapSlope297.put(9, 31);
        mapSlope297.put(10, 36);
        mapSlope297.put(11, 44);
        mapSlope297.put(12, 53);
        mapSlope297.put(13, 64);
        mapSlope297.put(14, 76);
        mapSlope297.put(15, 92);
        mapSlope297.put(16, 110);
    }

    /*Threshold*/
    public static final Integer[] thrCyclesArr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    //POLARITY
    public static final String[] thrPolarityArray = new String[]{"Bi Polar", "Uni Polar"};

    //For Pinnacle R, Pinnacle 8820 (Current Version)
//MODE
    public static String mStrBluetoothAddress;
    public static boolean bThrNext;
    public static byte actionCode;
    public static boolean bThrStop;
    public static boolean bThrLimit;
    public static String currModel = "Charak747";
    public static int[] pacerData = new int[55];
    //Array of Pacemaker Data to Program
    public static int[] pacerDataPROG = new int[55];
    public static int[] pacerDataPROGImp = new int[55];


    //RATE
    public static final Integer[] rateArray = new Integer[]{32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80,
            82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120};
    public static final Integer[] rateArray747 = new Integer[]{30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80,
            82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120};
    public static final Integer[] rateArrayP = new Integer[]{30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80,
            82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 124, 126, 128, 130, 132, 134, 136, 138,
            140, 142, 144, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180};
    //HYST
    public static final String[] HystArray = new String[]{"0%", "5%", "10%", "15%", "20%"};
    public static final String[] hystArray297 = new String[]{"OFF", "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "32", "34", "36", "38", "40"};
    public static final Integer[] hystArray297_int = new Integer[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};
    public static final Integer[] hystArrayInt297 = new Integer[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};
    public static final Integer[] hystArray747 = new Integer[]{0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60};
    //AMP
    public static final Double[] amparray = new Double[]{0.2, 0.3, 0.5, 0.6, 0.8, 0.9, 1.1, 1.3, 1.4, 1.6, 1.7, 1.9, 2.0, 2.2, 2.4, 2.5, 2.7, 2.8, 3.0, 3.1,
            3.3, 3.5, 3.6, 3.8, 3.9, 4.1, 4.2, 4.4, 4.5, 4.7, 4.9, 5.0,
            5.2, 5.3, 5.5, 5.6, 5.8, 5.9, 6.1, 6.3, 6.4, 6.6, 6.7, 6.9, 7.0, 7.2, 7.3, 7.5};


    public static final Double[] amparray297 = new Double[]{0.2, 0.3, 0.5, 0.6, 0.8, 0.9, 1.1, 1.3, 1.4, 1.6, 1.7, 1.9, 2.0, 2.2, 2.4, 2.5, 2.7, 2.8, 3.0, 3.1,
            3.3, 3.5, 3.6, 3.8, 3.9, 4.1, 4.2, 4.4, 4.5, 4.7, 4.9, 5.0, 5.6, 6.3, 6.9, 7.5};
    public static final Integer[] ampctrlWD297 = new Integer[]{17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 16, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0,
            36, 40, 44, 32};

    public static final Double[] ampArrayP = new Double[]{0.2, 0.4, 0.6, 0.8, 1.0, 1.3, 1.6, 1.8, 2.0, 2.3, 2.4, 2.6, 2.9, 3.1, 3.3, 3.5,
            3.8, 4.1, 4.4, 4.7, 5.0, 5.2, 5.4, 5.7, 6.0, 6.2, 6.5, 6.7, 7.0, 7.1, 7.3, 7.5};


    //PW
    public static final Double[] pwArray = new Double[]{0.07, 0.10, 0.13, 0.16, 0.19, 0.22, 0.25, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5};

    public static final int[] pwCtrlWD = new int[]{1, 2, 3, 4, 5, 6, 7, 9, 12, 15, 19, 22, 25, 28, 32, 35, 38, 41, 45, 48};
    public static final int[] pwCtrlWD297 = new int[]{2, 3, 4, 5, 6, 7, 8, 0x0A, 0x0D, 0x10, 0x14, 0x17, 0x1A, 0x1D, 0x21, 0x24, 0x27, 0x2A, 0x2E, 0x31};
    //SENSING and blanking
    public static final Double[] senArray = new Double[]{0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};

    public static final Double[] senArray297old = new Double[]{0.4, 0.8, 1.2, 1.6, 2.0, 2.4, 2.8, 3.2, 3.6, 4.0, 4.4, 4.8, 5.2, 5.6, 6.0, 6.4};
    public static final Double[] senArrayP = new Double[]{0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5, 13.5, 14.5, 16.0};
    public static final Double[] aSenArrayC = new Double[]{0.2, 0.6, 1.0, 1.4, 1.8, 2.2, 2.6, 3.0, 3.4, 3.8, 4.2, 4.6, 5.0, 5.4, 5.8, 6.4};
    public static int[] markerData = new int[55];
    private static byte[] crcData = new byte[55];
    public static final int[] senCtrlWD = new int[]{224, 96, 160, 32, 192, 64, 128, 0};
    //REFRACTORY
    //public final Integer[] refArray747= new Integer[]{200,225,250,275,300,325,350,375,400,425,450,475,500};

    public static final Integer[] refArray = new Integer[]{203, 219, 234, 250, 266, 281, 297, 313, 328, 344, 359, 375, 391, 406, 422, 438};
    public static final Integer[] refArray297 = new Integer[]{200, 215, 230, 245, 260, 275, 290, 305, 320, 335, 350, 365, 380, 395, 410, 425, 440, 455, 470, 485, 500};
    //public static final Integer[] refArray747 = new Integer[]{200, 215, 230, 245, 260, 275, 290, 305, 320, 335, 350, 365, 380, 395, 410, 425, 440, 455, 470, 485, 500};
    //UPPER RATE (Also for RR upper Rate and ATachy Rate)
    public static final Integer[] uprArray297 = new Integer[]{80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 124, 126, 128, 130, 132,
            134, 136, 138, 140, 142, 144, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180};
    public static final Integer[] uprArray = new Integer[]{78, 80, 84, 88, 93, 96, 100, 104, 108, 111, 116, 120, 124, 128, 132, 137, 140, 145, 148, 154, 157, 160, 163, 167, 171, 175
            , 179, 183};
    public static final Integer[] uprCtrlWD = new Integer[]{98, 96, 91, 87, 82, 80, 77, 73, 71, 69, 66, 64, 61, 60, 58, 56, 54, 53, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42};
    //UP TIME (RR Parameter)
    public static final Integer[] upTimeArray = new Integer[]{10, 20, 30, 40, 50, 60};

    //DOWN TIME (RR Parameter)
    public static final Integer[] dnTimeArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    //SLOPE (RR Parameter)
    public static final Integer[] slpArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    public static final Integer[] slpCtrlWD = new Integer[]{7, 8, 10, 12, 14, 17, 21, 26, 31, 36, 44, 53, 64, 76, 92, 110};
    public static final Integer[] aviArray = new Integer[]{20, 35, 50, 65, 80, 95, 110, 125, 140, 155, 170, 185, 200, 215, 230, 245, 260, 275, 290, 305, 320,
            335, 350};
    public static final Integer[] avhArray = new Integer[]{0, 10, 20, 30, 40, 50, 60, 70};
    //AT Entry & Exit Counts
    public static final Integer[] atEnExCounts = new Integer[]{4, 8, 16, 32};

    //Rate Drop Para
//RD rate
    public static final String[] auto = new String[]{"ON", "OFF"};
    public static final String[] na = {"NA"};
    public static final Double[] ampArray = new Double[]{0.2, 0.3, 0.5, 0.6, 0.8, 0.9, 1.1, 1.3, 1.4, 1.6, 1.7, 1.9, 2.0, 2.2, 2.4, 2.5, 2.7, 2.8, 3.0, 3.1,
            3.3, 3.5, 3.6, 3.8, 3.9, 4.0, 4.2, 4.4, 4.5, 4.7, 4.9, 5.0,
            6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
    public static final Integer[] rdRate = new Integer[]{80, 82, 84, 86, 88, 90, 93, 94, 96, 99, 100, 102, 104, 107, 108, 110, 111, 115, 116, 118, 120, 122, 124, 126, 128,
            130, 132, 134, 136, 138, 140};
    //RD Cycle (Duration)
    public final Integer[] rdCycle = new Integer[]{30, 60, 90, 120, 150, 180, 210, 240};
    //RD 1st Detection window
    public final Integer[] rdFstWindow = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    //RD 2nd Detection window
    public final Integer[] rdSndWindow = new Integer[]{1, 2, 3, 4, 5, 6, 7};
    //RD Upr Limit
    public final Integer[] rdUprLmt = new Integer[]{50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100};
    //RD Lo Limit
    public final Integer[] rdLowLmt = new Integer[]{40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80};

    //Night Rate Para
    //Night Rate
    public final Integer[] nightRate = new Integer[]{32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80,
            82, 84, 86, 88, 90, 92, 94, 96, 98, 100};
    //Night / Day Time Hrs
    public final Integer[] hours = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    //Night / Day Time Min
    public final Integer[] minute = new Integer[]{0, 15, 30, 45};

    public static String strAction = "";
    //Pacemaker CommonData Variables for Display
    public static int iSrnoH;
    public static int iSrnoL;
    public static int iSrno;
    public static int iPacerID;
    public static int iPacerSelect;
    public static int iBat;
    public static int iMode;
    public static int iRate;
    public static int iAmp;
    public static int iAAmp;
    public static int iPW;
    public static int iAPW;
    public static int iRef;
    public static int iARef;
    public static int iPvarp;
    public static int iASen;
    public static int iSen;
    public static int iBlnk;
    public static int iPacePol;
    public static int iAPacepol;
    public static int iSenPol;
    public static int iUpRate;
    public static int ihystVAL;
    public static int iTrigUprRate;
    public static int iSensorRate, iSlope, iuptime, iDownTime, iPAVI, iSAVI, iAVHyst, iATRate, iATExt, iATEnt, iMagnet, iNoise, iANoise, iABlnk;
    //Night Rate
    static int iNightRT, iNightMIN, iNightHRS, iDayMIN, iDayHRS;
    //Rate DRop
    static int iRdRate, iRdPRD, iRdUpLmt, iRdLoLmt, iRdFstW, iRdSndW;
    //BiVent
    static int iLVAmp, iLVPW, iLVPol, iVVInterval;
    //Flags
    public static final Integer[] pvarp = new Integer[]{200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500};
    public static boolean bARC;
    public static boolean bHystON;
    public static boolean bHystSrchON;
    public static boolean bMRI;
    public static boolean bAutoMsr;
    public static boolean bPin297AM;
    public static boolean bAVHSrch;
    public static boolean bAutoPol;
    public static boolean bATEna;
    public static boolean bATAF;
    static boolean bNightEna;
    static boolean bDayEna;
    static boolean bLDMON, bPVC, bVSP, bNCAP, bAAM, bVAM, bRDON, bVRR;
    static boolean bBIEna, bLVOnly, bLVFst;
    //Temp Pacemaker CommonData Variables for Display
    public static int tMode;
    public static int tModeValue;
    public static int tRate;
    public static int tAmp;
    public static int tAAmp;
    public static int tPW;
    public static int tAPW;
    public static int tRef;
    public static int tARef;
    public static int tPvarp;
    public static int tASen;
    public static int tSen;
    public static int tBlnk;
    public static int tPacePol;
    public static int tAPacepol;
    public static int tSenPol;
    static int tUpRate;
    public static int thystVAL;
    public static int tTrigUprRate;
    public static int tSensorRate, tSlope, tuptime, tDownTime, tPAVI, tSAVI, tAVHyst, tATRate, tATExt, tATEnt, tABlnk;
    //Night Rate
    static int tNightRT, tNightMIN, tNightHRS, tDayMIN, tDayHRS;
    //Rate DRop
    static int tRdRate, tRdPRD, tRdUpLmt, tRdLoLmt, tRdFstW, tRdSndW;
    //BiVent
    static int tLVAmp, tLVPW, tLVPol, tVVInterval;
    //Flags
    public static boolean tbARC, tbHystON, tbHystSrchON, tbMRI, tbAutoMsr, tbAVHSrch, tbAutoPol, tbATEna, tbATAF, tbNightEna, tbDayEna;
    static boolean tbLDMON, tbPVC, tbVSP, tbNCAP, tbAAM, tbVAM, tbRDON, tbVRR;
    static boolean tbBIEna, tbLVOnly, tbLVFst;
    //Statistics Counts variables
    public static long cntPace;
    public static long cntSens;
    public static long cntL80;
    public static long cnt100;
    public static long cnt120;
    public static long cnt140;
    public static long cntG140;
    public static long cntNoise;
    public static long cntNoisePace;
    static long[] apCnt = new long[5];
    static long[] asCnt = new long[5];
    static long[] vpCnt = new long[5];
    static long[] vsCnt = new long[5];
    //static long ;
    //static long ;
    //static long ;
    public static int statChamber = 0;
    public static int statTab = 0;
    // long decimalVal;
    private static String counts;
    public static String strDateTime; //For statistics calculations
    public static String strDateImp; //For statistics calculations
    public static String strLastDateTime = ""; //For statistics calculations
    public static boolean bshowStat = false; //to show stats on Log click event
    public static boolean bStatiscticsON = false; // To check if activity is running
    static int statIndex = 0; // to store the index of stat in list
    // Marker next button flag
    // ECG Gain Variable
    public static int prevGainPos = 0;
    // File path and name var
    public static String filePath = "";
    public static String fileName = "";
    public static String tempFilePath = "";
    //Life Calucation
    static int lifeYear;
    static int lifeMonth;
    public static int remYear;
    public static int remMonth;
    //CRC functions and variables
    private static int crc_datlen;
    private static int crcl;
    private static int crch;
    //byte[] tstbt=new byte[55];
    private static long p_ccitt = 0;
    public static boolean isFirstVviMain = true;
    public static boolean isFirstVviOther = true;
    public static boolean isFirstVviRateResponse = true;


    public static boolean isFirstDualRateResponse = true;
    public static boolean isFirstDualVviMain = true;
    public static boolean isFirstDualVviOthers = true;
    public static boolean isFirstDualVentricle = true;
    public static boolean isFirstDualAtrium = true;
    public static boolean isFirstDualOthers = true;

    public static void resetVariableDualChamber(String mStrScreenName) {
        if (mStrScreenName.equals("main")) {
            isFirstDualRateResponse = false;
            isFirstDualVviOthers = false;
            isFirstDualVentricle = false;
            isFirstDualAtrium = false;
            isFirstDualOthers = false;
        } else if (mStrScreenName.equals("rate_response")) {
            isFirstDualVviMain = false;
            isFirstDualVviOthers = false;
            isFirstDualVentricle = false;
            isFirstDualAtrium = false;
            isFirstDualOthers = false;
        } else if (mStrScreenName.equals("ventricle")) {
            isFirstDualVviMain = false;
            isFirstDualVviOthers = false;
            isFirstDualRateResponse = false;
            isFirstDualAtrium = false;
            isFirstDualOthers = false;
        } else if (mStrScreenName.equals("atrium")) {
            isFirstDualVviMain = false;
            isFirstDualVviOthers = false;
            isFirstDualRateResponse = false;
            isFirstDualVentricle = false;
            isFirstDualOthers = false;
        } else if (mStrScreenName.equals("other")) {
            isFirstDualVviMain = false;
            isFirstDualAtrium = false;
            isFirstDualRateResponse = false;
            isFirstDualVentricle = false;
            isFirstDualOthers = false;
        } else if (mStrScreenName.equals("dual_other")) {
            isFirstDualVviMain = false;
            isFirstDualAtrium = false;
            isFirstDualRateResponse = false;
            isFirstDualVentricle = false;
            isFirstDualVviOthers = false;
        } else if (mStrScreenName.equals("polarity")) {
            isFirstDualVviMain = false;
            isFirstDualAtrium = false;
            isFirstDualRateResponse = false;
            isFirstDualVentricle = false;
            isFirstDualVviOthers = false;
            isFirstDualOthers = false;
        }
    }

    public static void resetVariableSingleChamber(String mStrScreenName) {
        if (mStrScreenName.equals("main")) {
            isFirstVviOther = true;
            isFirstVviRateResponse = true;
        } else if (mStrScreenName.equals("other")) {
            isFirstVviMain = true;
            isFirstVviRateResponse = true;
        } else if (mStrScreenName.equals("rate_response")) {
            isFirstVviMain = true;
            isFirstVviOther = true;
        } else if (mStrScreenName.equals("polarity")) {
            isFirstVviMain = true;
            isFirstVviOther = true;
            isFirstVviRateResponse = true;
        }

    }

    public static void POR_Pinn8820() {
        iPacerID = 14;
        iPacerSelect = 2;
        pacerData[0] = 4;
        pacerData[1] = 11;
        pacerData[2] = 1;
        pacerData[3] = 0x0E;
        pacerData[4] = 0xFF;
        pacerData[5] = 0xFF;
        pacerData[6] = 0x58;
        pacerData[7] = 0x0F;
        pacerData[8] = 0xD0;
        pacerData[9] = 0;
        pacerData[10] = 0xB0;
        pacerData[11] = 0x28;
        pacerData[12] = 0x42;
        pacerData[13] = 0;
        pacerData[14] = 0;
        pacerData[15] = 0xFF;
        System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);

    }

    public static void POR_Pinr297() {
        iPacerID = 25;
        iPacerSelect = 25;
        pacerData[0] = 4;
        pacerData[1] = 17;
        pacerData[2] = 1;
        pacerData[3] = 0x19;
        pacerData[4] = 0xFF;
        pacerData[5] = 0xFF;
        pacerData[6] = 0x5A;
        pacerData[7] = 0xD0;
        pacerData[8] = 0x40;
        pacerData[9] = 1;
        pacerData[10] = 8;
        pacerData[11] = 1;
        pacerData[12] = 0x0E;
        pacerData[13] = 0x2C;
        pacerData[14] = 0x2C;
        pacerData[15] = 0x0A;
        pacerData[16] = 4;
        pacerData[17] = 0x90;
        pacerData[18] = 0;
        pacerData[19] = 0;
        pacerData[20] = 0;
        pacerData[21] = 0xFF;
        System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
    }

    public static void POR_Pinn8820AM() {
        iPacerID = 0x1B;
        iPacerSelect = 0x1B; //(27)
        pacerData[0] = 4;
        pacerData[1] = 17;
        pacerData[2] = 1;
        pacerData[3] = 0x1B;
        pacerData[4] = 0xFF;
        pacerData[5] = 0xFF;
        pacerData[6] = 0x5A;
        pacerData[7] = 0xD0;
        pacerData[8] = 0x40;
        pacerData[9] = 1;
        pacerData[10] = 8;
        pacerData[11] = 1;
        pacerData[12] = 0x0E;
        pacerData[13] = 0x2C;
        pacerData[14] = 0x2C;
        pacerData[15] = 0x0A;
        pacerData[16] = 4;
        pacerData[17] = 0x90;
        pacerData[18] = 0;
        pacerData[19] = 0;
        pacerData[20] = 0;
        pacerData[21] = 0xFF;
        System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
    }

    //POR of DDDR_AM
    public static void POR_DDDR_AM() {
        iPacerID = 0x18;
        iPacerSelect = 0x18; //24
        pacerData[0] = 4;
        pacerData[1] = 19;
        pacerData[2] = 1;
        pacerData[3] = 0x18;
        pacerData[4] = 0xFF;
        pacerData[5] = 0xFF;
        pacerData[6] = 0x7C;
        pacerData[7] = 0x44;
        pacerData[8] = 0x50;
        pacerData[9] = 0xD0;
        pacerData[10] = 0x50;
        pacerData[11] = 0x62;
        pacerData[12] = 0x54;
        pacerData[13] = 0x94;
        pacerData[14] = 0xC9;
        pacerData[15] = 0xF;
        pacerData[16] = 0x8;
        pacerData[17] = 0x47;
        pacerData[18] = 0x14;
        pacerData[19] = 0x63;
        pacerData[20] = 0;
        pacerData[21] = 0x8E;
        pacerData[22] = 0;
        pacerData[23] = 0xFF;
        System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
    }

    public void ID_PinnP() {
        iPacerID = 0x10;
        iPacerSelect = 0x10; //(16)
    }

    public void ID_PinnR() {
        iPacerID = 0x13;
        iPacerSelect = 0x13; //(19)
        pacerData[3] = 0x13;
        System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
    }

    public void ID_PinnC() {
        iPacerID = 0x1C;
        iPacerSelect = 0x1C; //(28)
        pacerData[3] = 0x1C;
        pacerData[6] = 0x7C;
        System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
    }

    public void POR_PinnP() {
        pacerData[0] = 4;
        pacerData[1] = 28;
        pacerData[2] = 1;
        pacerData[3] = 0x10;
        pacerData[4] = 0xFF;
        pacerData[5] = 0xFF;
        pacerData[6] = 0x54;
        pacerData[7] = 0x44;
        pacerData[8] = 0xF4;
        pacerData[9] = 0x74;
        pacerData[10] = 0x50;
        pacerData[11] = 0x62;
        pacerData[12] = 0x54;
        pacerData[13] = 0x14;
        pacerData[14] = 0x11;
        pacerData[15] = 0x0F;
        pacerData[16] = 0x08;
        pacerData[17] = 0x47;
        pacerData[18] = 0x14;
        pacerData[19] = 0x63;
        pacerData[20] = 0x0A;
        //Night Rate
        pacerData[21] = 0x16;
        pacerData[22] = 0x06;
        //Flags
        pacerData[23] = 0;
        //Rate Drop V-V Delay in pacerdata[25]
        pacerData[24] = 0xA8;
        pacerData[25] = 0x07;
        pacerData[26] = 0x25;
        pacerData[27] = 0x4B;
        //LW PW, A Blank
        pacerData[28] = 0x42;
        //LV Pol, V-V Timing, LV Amp
        pacerData[29] = 0x14;
        pacerData[30] = 0;
        pacerData[31] = 0;
        pacerData[32] = 0xFF;
        System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
    }

    // Save temp para as prog OK
    public static void save_tPara() {

        iMode = tMode;
        iRate = tRate;
        iAmp = tAmp;
        iAAmp = tAAmp;
        iPW = tPW;
        iAPW = tAPW;
        iRef = tRef;
        iARef = tARef;
        iPvarp = tPvarp;
        iASen = tASen;
        iSen = tSen;
        iBlnk = tBlnk;
        iPacePol = tPacePol;
        iAPacepol = tAPacepol;
        iSenPol = tSenPol;
        iUpRate = tUpRate;
        ihystVAL = thystVAL;
        iTrigUprRate = tTrigUprRate;
        iSensorRate = tSensorRate;
        iSlope = tSlope;
        iuptime = tuptime;
        iDownTime = tDownTime;
        iPAVI = tPAVI;
        iSAVI = tSAVI;
        iAVHyst = tAVHyst;
        iATRate = tATRate;
        iATExt = tATExt;
        iATEnt = tATEnt;
        iNightRT = tNightRT;
        iNightMIN = tNightMIN;
        iNightHRS = tNightHRS;
        iDayMIN = tDayMIN;
        iDayHRS = tDayHRS;
        iRdRate = tRdRate;
        iRdPRD = tRdPRD;
        iRdUpLmt = tRdUpLmt;
        iRdLoLmt = tRdLoLmt;
        iRdFstW = tRdFstW;
        iRdSndW = tRdSndW;
        iLVAmp = tLVAmp;
        iLVPW = tLVPW;
        iLVPol = tLVPol;
        iVVInterval = tVVInterval;
        iABlnk = tABlnk;

        bARC = tbARC;
        bHystON = tbHystON;
        bHystSrchON = tbHystSrchON;
        bMRI = tbMRI;
        bAutoMsr = tbAutoMsr;
        bAVHSrch = tbAVHSrch;
        bAutoPol = tbAutoPol;
        bATEna = tbATEna;
        bATAF = tbATAF;
        bNightEna = tbNightEna;
        bDayEna = tbDayEna;
        bLDMON = tbLDMON;
        bPVC = tbPVC;
        bVSP = tbVSP;
        bNCAP = tbNCAP;
        bAAM = tbAAM;
        bVAM = tbVAM;
        bRDON = tbRDON;
        bBIEna = tbBIEna;
        bLVOnly = tbLVOnly;
        bLVFst = tbLVFst;

    }


    //CRC Calculation Functions
    private static long init_tab(long n) {
        long crc, C;

        crc = 0;
        C = n * 256;
        if (C > 65535) {
            C = C - 65536;
        }

        for (int j = 0; j < 8; j++) {
            if (((crc ^ C) & 0x8000) > 0)
                crc = (crc * 2) ^ p_ccitt;
            else
                crc = crc * 2;

            if (crc > 65535) {
                crc = crc - 65536;
            }
            C = C * 2;
            if (C > 65535)
                C = C - 65536;
        }
        return crc;
    }

    private static long update_crc_ccitt(long crc, int C) {
        int tmp, short_c, lp;

        short_c = 0xFF & C;

        tmp = (int) (crc / 256);
        if (tmp > 255) {
            tmp = 255;
        }
        tmp = tmp ^ short_c;

        for (lp = 0; lp < 8; lp++) {
            crc = (crc * 2);
            if (crc > 65535) {
                crc = crc - 65536;
            }
        }

        crc = crc ^ init_tab(tmp);
        return crc;
    }


    public static boolean VARIFYCRC(int[] teleBT) {
        int Idx;

        crc_datlen = teleBT[1] + 1;
        for (Idx = 0; Idx <= crc_datlen; Idx++) {
            crcData[Idx] = (byte) (teleBT[Idx] & 0xFF);
        }

        crcData = CRCCAL(crcData);
        // crcl = (0xFF & crcData[crc_datlen + 1]);
        //  crch = (0xFF & crcData[crc_datlen + 2]);

        return crcl == teleBT[crc_datlen + 1] && crch == teleBT[crc_datlen + 2];

    }


    public static byte[] CRCCAL(byte[] BT) {

        int Index;
        long crc_ccitt_ffff = 65535;
        p_ccitt = 0x1021;

        crc_datlen = BT[1] + 1;

        for (Index = 0; Index <= crc_datlen; Index++) {

            crc_ccitt_ffff = update_crc_ccitt(crc_ccitt_ffff, BT[Index]);

        }
        crch = (int) (crc_ccitt_ffff / 256);
        crcl = (int) (crc_ccitt_ffff % 256);
        Index = crc_datlen;
        Index = Index + 1;
        BT[Index] = (byte) crcl;
        Index = Index + 1;
        BT[Index] = (byte) crch;

        for (int i = 0; i <= Index; i++) {

            if (BT[i] == 0x5C || BT[i] == 0xFF) {
                System.arraycopy(BT, i, BT, i + 1, (Index - i) + 1);

                BT[i] = (byte) 0x5C;
                i++;
                Index++;
            }
        }
        BT[Index + 1] = (byte) 0xFF;
        return BT;
    }

    //******************* Decode Para******************************
// Receive Bytes from prog
    public static void decodeBytes() {

        //Decode ID
        iPacerID = pacerData[3];
        // Decode serial no.
        iSrnoH = pacerData[4];
        iSrnoL = pacerData[5];
        iSrno = (256 * iSrnoH) + iSrnoL;

        switch (iPacerID) {
            case 0x0E:
                iPacerSelect = 0x02;
                decode8820();
                break;
            case 0x19:
                iPacerSelect = 0x19;
                decode297();
                break;
            case 0x1B:
                iPacerSelect = 0x1B;
                decode8820AM();
                break;
            case 0x18:
                iPacerSelect = 0x18;
                decode747R();
                break;
            case 0x0C:
                iPacerSelect = 0x0C;
                decode747R();
                break;
            case 0x10:
                iPacerSelect = 0x10;
                decode_pinnP();
                break;
            case 0x13:
                iPacerSelect = 0x13;
                decode_pinnP();
                break;
            case 0x1C:
                iPacerSelect = 0x1C;
                decode_pinnP();
                break;
            default:
                break;
        }

    }

    private static void decode8820() {
//Show Battery
        iBat = pacerData[13];

        //Decode Mode
        List<Integer> values = new ArrayList<>(mapOld.values());
        if (values.indexOf(pacerData[6]) >= 0) {
            iMode = values.indexOf(pacerData[6]);
            tMode = iMode;
        }


        //Decode Rate
        iRate = (pacerData[11] / 2) - 1;
        tRate = iRate;
        //Decode Amplitude
        /*iAmp=(pacerData[8] & 0x3F) ;
        if (iAmp==0 || iAmp==16 || iAmp==32)
            iAmp+=15;
        else
            iAmp-=1;

        tAmp=iAmp;*/
        for (int i = 0; i < ampctrlWD.length; i++) {
            if (ampctrlWD[i] == (pacerData[8] & 0x3F)) {
                iAmp = i;
                break;
            }
        }
        tAmp = iAmp;

        //Decode Polarity
        iPacePol = 0;
        iSenPol = 0;
        if ((pacerData[8] & 0x80) == 0x80)
            iSenPol = 1;
        if ((pacerData[8] & 0x40) == 0x40)
            iPacePol = 1;

        tSenPol = iSenPol;
        tPacePol = iPacePol;

        //Decode PW
        for (int i = 0; i < (pwCtrlWD.length); i++) {
            if (pwCtrlWD[i] == pacerData[7]) {
                iPW = i;
                break;
            }
        }
        tPW = iPW;
        //Decode Sensitivity
        int tmpSen = pacerData[10] & 0xE0;
        for (int i = 0; i < (senCtrlWD.length); i++) {
            if (senCtrlWD[i] == tmpSen) {
                iSen = i;
                break;
            }

        }
        tSen = iSen;

        // Decode Refrectory
        int tmpref = pacerData[10] & 0x1F;
        tmpref = (int) ((tmpref * 7.8125) + 203);
        for (int i = 0; i < refArray.length; i++) {
            if (refArray[i] >= (tmpref - 8) && refArray[i] <= (tmpref + 8)) {
                iRef = i;
                break;
            }
        }
        tRef = iRef;
        // Decode hysterisis
        int temp = (int) round((pacerData[9] & 0x3F) * 7.8125);
        if ((pacerData[9] & 0xC0) == 0xC0) {
            temp += 6;
        } else if ((pacerData[9] & 0x80) == 0x80) {
            temp += 4;
        } else if ((pacerData[9] & 0x40) == 0x40) {
            temp += 2;
        }

        int ihystVAL = 0;
        double tmpratems = (double) 60000 / (rateArrayP[iRate]);
        if (temp > (int) (tmpratems * 0.03) && temp < (int) (tmpratems * 0.08)) {
            ihystVAL = 1;
        }
        if (temp > (int) (tmpratems * 0.08) && temp < (int) (tmpratems * 0.13)) {
            ihystVAL = 2;
        }
        if (temp > (int) (tmpratems * 0.13) && temp < (int) (tmpratems * 0.18)) {
            ihystVAL = 3;
        }
        if (temp > (int) (tmpratems * 0.18) && temp < (int) (tmpratems * 0.23)) {
            ihystVAL = 4;
        }

        thystVAL = ihystVAL;
        //Decode Upper Rate, SST mode
        for (int i = 0; i < uprArray.length; i++) {
            if (pacerData[12] == uprCtrlWD[i]) {
                iUpRate = i;
                break;
            }
        }
        tUpRate = iUpRate;
        //Decode Noise and Magnet
        iMagnet = pacerData[13] & 0x80;
        iNoise = pacerData[13] & 0x40;

        //}
    }

    //Decode para of Pinnacle R 297
    private static void decode297() {
//1st BYTE
        //Decode Mode
        List<Integer> values = new ArrayList<>(map297_8820.values());
        if (values.indexOf((pacerData[6] & 0xF8)) >= 0) {
            iMode = values.indexOf((pacerData[6] & 0xF8));
        }
        tMode = iMode;
        System.out.println("<><><>!!!! " + tMode);
        //Decode Up Time
        iuptime = pacerData[6] & 7;
        tuptime = iuptime;
//2nd Byte
        //Decode PW
        for (int i = 0; i < (pwCtrlWD297.length); i++) {
            if (pwCtrlWD297[i] == (pacerData[7] & 0x3F)) {
                iPW = i;
                //i=pwCtrlWD.length;
                break;
            }
        }
        tPW = iPW;
        //Decode Polarity
        iPacePol = 0;
        iSenPol = 0;
        if ((pacerData[7] & 0x40) == 0x40)
            iPacePol = 1;
        if ((pacerData[7] & 0x80) == 0x80)
            iSenPol = 1;

        tPacePol = iPacePol;
        tSenPol = iSenPol;
//3rd Byte
        //Decode Amplitude
        for (int i = 0; i < ampctrlWD297.length; i++) {
            if (ampctrlWD297[i] == (pacerData[8] & 0x3F)) {
                iAmp = i;
                break;
                //i=ampctrlWD297.length;
            }
        }
        tAmp = iAmp;
        // Decode ARC
        bARC = (pacerData[8] & 0x40) == 0x40;
        tbARC = bARC;
        //HYST ON/OFF
        bHystON = (pacerData[8] & 0x80) == 0x80;

        tbHystON = bHystON;

//4th Byte
        //Hyst Value
        ihystVAL = pacerData[9] & 0x1F;
        thystVAL = ihystVAL;
//5th Byte
        // Decode Refrectory
        iRef = pacerData[10] & 0x1F;
        tRef = iRef;
        //Hyst Search ON/OFF
        bHystSrchON = (pacerData[10] & 0x20) == 0x20;

        tbHystSrchON = bHystSrchON;
//6th Byte
        //Decode Sensitivity
        iSen = pacerData[11] & 0x0F;
        //AM and MRI
        bAutoMsr = (pacerData[11] & 0x80) == 0x80;
        tbAutoMsr = bAutoMsr;

        bMRI = (pacerData[11] & 0x40) == 0x40;
        tbMRI = bMRI;

//7th Byte
        //Decode Rate
        iRate = pacerData[12];
        tRate = iRate;
//8th Byte
        //Decode Trigger Upper Rate (T modes)
        iTrigUprRate = pacerData[13] - 24;
        tTrigUprRate = iTrigUprRate;
//9th Byte
        //Decode Target Upper Rate (R modes)
        iSensorRate = pacerData[14] - 24;
        tSensorRate = iSensorRate;
//10th Byte
        //Decode Slope
        for (int i = 0; i < 16; i++) {
            if (pacerData[15] == slpCtrlWD[i]) {
                iSlope = i;
                break;
            }
        }

//11th Byte
        //Decode Down Time
        iDownTime = pacerData[16];
        tDownTime = iDownTime;
//12th Byte
        //Show Battery
        iBat = pacerData[17];

//13th Byte
        //Decode Magnet, Noise
        iNoise = pacerData[18] & 1;
        iMagnet = pacerData[18] & 8;
        //If (wd(31) And 4) = 4 Then temp = "ERI": bats.ForeColor = vbBlue
        //If (wd(31) And 2) = 2 Then temp = "EOL": bats.ForeColor = vbRed

    }

    //Decode para of Pinnacle 8820 AM
    private static void decode8820AM() {
//1st BYTE
        //Decode Mode
        List<Integer> values = new ArrayList<>(map297_8820.values());
        if (values.indexOf((pacerData[6] & 0xF8)) >= 0) {
            iMode = values.indexOf((pacerData[6] & 0xF8));
            tMode = iMode;
        }
        //Decode Up Time
        iuptime = pacerData[6] & 7;
        tuptime = iuptime;
//2nd Byte
        //Decode PW
        for (int i = 0; i < (pwCtrlWD297.length); i++) {
            if (pwCtrlWD297[i] == (pacerData[7] & 0x3F)) {
                iPW = i;
                tPW = iPW;
                //i=pwCtrlWD.length;
                break;
            }
        }
        //Decode Polarity
        iPacePol = 0;
        iSenPol = 0;
        if ((pacerData[7] & 0x40) == 0x40)
            iPacePol = 1;
        if ((pacerData[7] & 0x80) == 0x80)
            iSenPol = 1;

        tPacePol = iPacePol;
        tSenPol = iSenPol;

//3rd Byte
        //Decode Amplitude
        for (int i = 0; i < ampctrlWD297.length - 1; i++) {
            if (ampctrlWD297[i] == (pacerData[8] & 0x3F)) {
                iAmp = i;
                break;
                //i=ampctrlWD297.length;
            }
        }
        tAmp = iAmp;
        // Decode ARC
        bARC = (pacerData[8] & 0x40) == 0x40;

        tbARC = bARC;
        //HYST ON/OFF
        bHystON = (pacerData[8] & 0x80) == 0x80;

        tbHystON = bHystON;
//4th Byte
        //Hyst Value
        ihystVAL = pacerData[9] & 0x1F;
        thystVAL = ihystVAL;
//5th Byte
        // Decode Refrectory
        iRef = pacerData[10] & 0x1F;
        tRef = iRef;
        //Hyst Search ON/OFF
        bHystSrchON = (pacerData[10] & 0x20) == 0x20;

        tbHystSrchON = bHystSrchON;
//6th Byte
        //Decode Sensitivity
        iSen = pacerData[11] & 0x0F;
        tSen = iSen;
        //AM and MRI
        bAutoMsr = (pacerData[11] & 0x80) == 0x80;

        tbAutoMsr = bAutoMsr;
        bMRI = (pacerData[11] & 0x40) == 0x40;

        tbMRI = bMRI;
//7th Byte
        //Decode Rate
        iRate = pacerData[12];
        tRate = iRate;
//8th Byte
        //Decode Trigger Upper Rate (T modes)
        iTrigUprRate = pacerData[13] - 24;
        tTrigUprRate = iTrigUprRate;
//9th Byte
        //Decode Target Upper Rate (R modes)
        iSensorRate = pacerData[14] - 24;
        tSensorRate = iSensorRate;
//10th Byte
        //Decode Slope
        for (int i = 0; i < 16; i++) {
            if (pacerData[15] == slpCtrlWD[i]) {
                iSlope = i;
                break;
            }
        }
        tSlope = iSlope;
//11th Byte
        //Decode Down Time
        iDownTime = pacerData[16];
        tDownTime = iDownTime;
//12th Byte
        //Show Battery
        iBat = pacerData[17];

//13th Byte
        //Decode Magnet, Noise
        iNoise = pacerData[18] & 1;
        iMagnet = pacerData[18] & 8;
    }

    //Decode para of Charak 747R
    private static void decode747R() {


        //1st byte
        //MODE
        for (int i = 0; i < modeCtrlWD747.length; i++) {
            if (modeCtrlWD747[i] == (pacerData[6] & 0xFC)) {
                iMode = i;
                tMode = iMode;
                break;
            }
        }


        //A NOISE
        // '(wd (19) nd &H2)/2
        //iANoise = (pacerData[19] & 2) / 2;
        iANoise = (pacerData[6] & 2) / 2;
        //'V NOISE
        //'        wd (19) And &H1
        iNoise = pacerData[6] & 1;
        //2nd byte
        //APW VPW
        //pwlm = (wd(20) And &HF0) / 16
        iAPW = (pacerData[7] & 0xF0) / 16;
        tAPW = iAPW;
        //pwhm = wd(20) And &HF
        iPW = pacerData[7] & 0x0F;
        tPW = iPW;
        System.out.println("<><><>@@@###TP  " + tPW);

        //3rd byte
        //MAGNET
        //'(wd (21) And &H80)/128
        iMagnet = pacerData[8] & 0x80;
        //AP POL
        //fapol = (wd(21) And &H40) / 64
        iAPacepol = (pacerData[8] & 0x40) / 64;
        tAPacepol = iAPacepol;
        //A AMP
       /* bam = wd(21) And &H3F
        If bam = 0 Or bam = 16 Or bam = 32 Then
            bam = bam + 15
        Else
                bam = bam - 1
        End If
        If bam > 31 Then bam = Int((bam + 93) / 4)*/
        iAAmp = pacerData[8] & 0x3F;
        if (iAAmp == 0 || iAAmp == 0x10 || iAAmp == 0x20)
            iAAmp += 15;
        else
            iAAmp -= 1;

        if (iAAmp > 31)
            iAAmp = (iAAmp + 93) / 4;

        tAAmp = iAAmp;
        //4th byte
        //sens POL
        //fspol = (wd(22) And &H80) / 128
        iSenPol = (pacerData[9] & 0x80) / 128;
        tSenPol = iSenPol;
        //VP POL
        //fvpol = (wd(22) And &H40) / 64
        iPacePol = (pacerData[9] & 0x40) / 64;
        tPacePol = iPacePol;
        //V AMP
        //bvm = wd(22) And &H3F
        iAmp = pacerData[9] & 0x3F;

       /* If bvm = 0 Or bvm = 16 Or bvm = 32 Then
            bvm = bvm + 15
        Else
                bvm = bvm - 1
        End If*/
        if (iAmp == 0 || iAmp == 0x10 || iAmp == 0x20)
            iAmp += 0x0F;
        else
            iAmp -= 1;

        //If bvm > 31 Then bvm = Int((bvm + 93) / 4)
        if (iAmp > 0x1F)
            iAmp = (iAmp + 93) / 4;

        tAmp = iAmp;
        //5th byte
        //V REF
        //rvlm = (wd(23) And &HF0) / 16
        iRef = (pacerData[10] & 0xF0) / 16;
        tRef = iRef;
        //Hyst Val
        //rhy = wd(23) And &HF
        ihystVAL = pacerData[10] & 0x0F;
        thystVAL = ihystVAL;
        //6th byte
        //PVARP
        // pvlm = (wd(24) And &HF0) / 16
        iPvarp = (pacerData[11] & 0xF0) / 16;
        tPvarp = iPvarp;
        //A REF
        //rflm = (wd(24) And &HF)
        iARef = pacerData[11] & 0x0F;
        tARef = iARef;
        //7th byte
        //AT ENTRY COUNT
        //ATent = (wd(25) And &HC0) / 64
        iATEnt = (pacerData[12] & 0xC0) / 64;
        tATEnt = iATEnt;
        //UPPER RATE (SST and DDD Mode)
        // uplm = wd(25) And &H3F
        iTrigUprRate = pacerData[12] & 0x3F;
        tTrigUprRate = iTrigUprRate;
        //8th byte
        //ARC
        /*If (wd(26) And &H80) = &H80 Then
        farc = True
        Else
                farc = False
        End If*/
        bARC = (pacerData[13] & 0x80) == 0x80;
        /*if ((pacerData[13] & 0x80) == 0x80)
            bARC = true;
        else
            bARC = false;*/

        tbARC = bARC;
        //AT ENABLE
        //fATena = (wd(26) And &H40) / 64
        if ((pacerData[13] & 0x40) == 0x40)
            bATEna = true;
        else
            bATEna = false;

        tbATEna = bATEna;
        // TARGET (Sensor) UPPER RATE (RR)
        //tup = wd(26) And &H3F 'frc
        iSensorRate = pacerData[13] & 0x3F;
        tSensorRate = iSensorRate;
        //9th byte
        //BLANKING
        //bklm = (wd(27) And &HC0) / 64
        iBlnk = (pacerData[14] & 0xC0) / 64;
        tBlnk = iBlnk;
        //ASEN
        //snlm = (wd(27) And &H38) / 8
        iASen = (pacerData[14] & 0x38) / 8;
        tASen = iASen;
        //VSEN
        //svlm = wd(27) And &H7
        iSen = pacerData[14] & 0x07;
        tSen = iSen;
        //10th byte
        //Hyst Search ON/OFF
        //hystsrch = (wd(28) And &H80) / 128
        if ((pacerData[15] & 0x80) == 0x80)
            bHystSrchON = true;
        else
            bHystSrchON = false;

        tbHystSrchON = bHystSrchON;

        //AV Hyst Search ON/OFF
        //avhsrch = (wd(28) And &H40) / 64
        if ((pacerData[15] & 0x40) == 0x40)
            bAVHSrch = true;
        else
            bAVHSrch = false;

        tbAVHSrch = bAVHSrch;
        //RATE
        //rtbm = wd(28) And &H3F
        iRate = pacerData[15] & 0x3F;
        tRate = iRate;
        //11th byte
        //AV Hyst Value
        //avhyst = (wd(29) And &HE0) / 32
        iAVHyst = (pacerData[16] & 0xE0) / 32;
        tAVHyst = iAVHyst;
        //P AVD
        //avlm = wd(29) And &H1F
        iPAVI = pacerData[16] & 0x1F;
        tPAVI = iPAVI;
        //12th byte
        //Up Time
        //uptm = (wd(30) And &HE0) / 32
        iuptime = (pacerData[17] & 0xE0) / 32;
        tuptime = iuptime;
        //S AVD
        //d1lm = wd(30) And &H1F
        iSAVI = pacerData[17] & 0x1F;
        tSAVI = iSAVI;
        //13th byte
        //SLOPE
        //slp = (wd(31) And &HF0) / 16
        iSlope = (pacerData[18] & 0xF0) / 16;
        tSlope = iSlope;
        //Down Time
        //dntm = wd(31) And &HF
        iDownTime = pacerData[18] & 0x0F;
        tDownTime = iDownTime;
        //14th byte
        //AT EXIT COUNT
        //AText = (wd(32) And &HC0) / 64
        iATExt = (pacerData[19] & 0xC0) / 64;
        tATExt = iATExt;
        //AT TRIGGER RATE
        //ATrt = wd(32) And &H3F
        iATRate = pacerData[19] & 0x3F;
        tATRate = iATRate;

        //15th byte
        //Auto POL SW
        /*If (wd(33) And &H80) = &H80 Then
        bAUTOSW = 1
        Else
                bAUTOSW = 0
        End If*/
        if ((pacerData[20] & 0x80) == 0x80)
            bAutoPol = true;
        else
            bAutoPol = false;

        tbAutoPol = bAutoPol;
        //MRI
        /*If (wd(33) And &H40) = &H40 Then
        fMRI = True
        Else
                fMRI = False
        End If*/
        if ((pacerData[20] & 0x40) == 0x40)
            bMRI = true;
        else
            bMRI = false;

        tbMRI = bMRI;
        //FOR CHARAK WITH AM AND AF
        //  If pacerselect = 24 Then
        //V AM
        /*If (wd(33) And &H20) = &H20 Then
        VAMENA = 1
        Else
                VAMENA = 0
        End If*/
        if (iPacerSelect == 0x18) {
            if ((pacerData[20] & 0x20) == 0x20)
                bAutoMsr = true;
            else
                bAutoMsr = false;
            tbAutoMsr = bAutoMsr;

        /*If (wd(33) And &H8) = 8 Then
            ATAF = 1
        Else
                ATAF = 0
        End If
        End If*/
            if ((pacerData[20] & 0x08) == 0x08)
                bATAF = true;
            else
                bATAF = false;

            tbATAF = bATAF;
        }
        //16th byte
        //BATTERY
        iBat = pacerData[21];
    }
    //End of Decode para of Charak 747R

    //Decode Pinnacle P series
    private static void decode_pinnP() {
        //1st byte
        //'MODE

        for (int i = 0; i < modeCtrlWD747.length; i++) {
            if (modeCtrlWD747[i] == (pacerData[6] & 0xFC)) {
                iMode = i;
                tMode = iMode;
                break;
            }
        }


        iANoise = pacerData[6] & 2;
        iNoise = pacerData[6] & 1;

        //'2nd byte
        //'APW VPW
        iAPW = (pacerData[7] & 0xF0) / 16;
        tAPW = iAPW;
        iPW = pacerData[7] & 0x0F;
        tPW = iPW;
        //'3rd byte
        //'sens POL

        iSenPol = (pacerData[8] & 0x80) / 128;
        tSenPol = iSenPol;
        //'AP POL

        iAPacepol = (pacerData[8] & 0x40) / 64;
        tAPacepol = iAPacepol;
        //'VP POL

        iPacePol = (pacerData[8] & 0x20) / 32;
        tPacePol = iPacePol;
        //'A AMP

        iAAmp = pacerData[8] & 0x1F;
        tAAmp = iAAmp;
        //'4th byte
        //'Magnet decoded in screen display
        iMagnet = pacerData[9] & 0x80;

        //'Blanking
        //bklm = (wd(22) And &H60) / 32
        iBlnk = (pacerData[9] & 0x60) / 32;
        tBlnk = iBlnk;
        //'V AMP

        iAmp = (pacerData[9] & 0x1F);
        tAmp = iAmp;
        //'5th byte
        //'V REF

        iRef = (pacerData[10] & 0xF0) / 16;
        tRef = iRef;
        //'Hyst Val

        ihystVAL = (pacerData[10] & 0x0F);
        thystVAL = ihystVAL;
        //'6th byte
        //'PVARP

        iPvarp = (pacerData[11] & 0xF0) / 16;
        tPvarp = iPvarp;
        //'A REF

        iARef = (pacerData[11] & 0x0F);
        tARef = iARef;
        //'7th byte
        //'AT ENTRY COUNT

        iATEnt = (pacerData[12] & 0xC0) / 64;
        tATEnt = iATEnt;
        //'COUNT UPPER RATE

        iTrigUprRate = (pacerData[12] & 0x3F);
        tTrigUprRate = iTrigUprRate;
        //'8th byte
        //'ARC
        if ((pacerData[13] & 0x80) == 0x80)
            bARC = true;
        else
            bARC = false;

        tbARC = bARC;
        //'AT ENABLE


        if ((pacerData[13] & 0x40) == 0x40)
            bATEna = true;
        else
            bATEna = false;

        tbATEna = bATEna;

        // ' TARGET UPPER RATE (RR)

        iSensorRate = pacerData[13] & 0x3F;
        tSensorRate = iSensorRate;
        //'9th byte

        //'ASEN

        iASen = (pacerData[14] & 0xF0) / 16;
        tASen = iASen;
        //'VSEN

        iSen = pacerData[14] & 0x0F;
        tSen = iSen;
        //'10th byte
        //'Hyst Search ON/OFF

        if ((pacerData[15] & 0x80) == 0x80)
            bHystSrchON = true;
        else
            bHystSrchON = false;

        tbHystSrchON = bHystSrchON;
        //'RATE

        iRate = pacerData[15] & 0x7F;
        tRate = iRate;
        //'11th byte
        //'AV Hyst Value

        iAVHyst = (pacerData[16] & 0xE0) / 32;
        tAVHyst = iAVHyst;
        //'P AVD

        iPAVI = pacerData[16] & 0x1F;
        tPAVI = iPAVI;
        //'12th byte
        //'Up Time

        iuptime = (pacerData[17] & 0xE0) / 32;
        tuptime = iuptime;
        //'S AVD

        iSAVI = pacerData[17] & 0x1F;
        tSAVI = iSAVI;
        //'13th byte
        //' SLOPE
        iSlope = (pacerData[18] & 0xF0) / 16;
        tSlope = iSlope;
        //'Down Time
        iDownTime = pacerData[18] & 0x0F;
        tDownTime = iDownTime;
        //'14th byte
        //'AT EXIT COUNT

        iATExt = (pacerData[19] & 0xC0) / 64;
        tATExt = iATExt;
        //'AT TRIGGER RATE

        iATRate = pacerData[19] & 0x3F;
        tATRate = iATRate;
        //'15th byte
        //'Auto POL SW
        if ((pacerData[20] & 0x80) == 0x80)
            bAutoPol = true;
        else
            bAutoPol = false;

        tbAutoPol = bAutoPol;

        //'AV Hyst Search ON/OFF

        if ((pacerData[20] & 0x40) == 0x40)
            bAVHSrch = true;
        else
            bAVHSrch = false;

        tbAVHSrch = bAVHSrch;
        //' Night Low rate

        iNightRT = (pacerData[20] & 0x3F) - 1;
        tNightRT = iNightRT;
        //'16th byte
        //'Night rate enable

        if ((pacerData[21] & 0x80) == 0x80)
            bNightEna = true;
        else
            bNightEna = false;

        tbNightEna = bNightEna;
        //'Night rate switch time Hours:Min
        iNightMIN = (pacerData[21] & 0x60) / 32;
        tNightMIN = iNightMIN;

        iNightHRS = pacerData[21] & 0x1F;
        tNightHRS = iNightHRS;
        //'17th byte
        //'Day rate switch if activity detected enable

        if ((pacerData[22] & 0x80) == 0x80)
            bDayEna = true;
        else
            bDayEna = false;

        tbDayEna = bDayEna;
        //'Day rate switch time Hours:Min
        iDayMIN = (pacerData[22] & 0x60) / 32;
        tDayMIN = iDayMIN;

        iDayHRS = pacerData[22] & 0x1F;
        tDayHRS = iDayHRS;
        //'18th byte
        //'Flags for
        //'LEAD MONITOR

        if ((pacerData[23] & 0x40) == 0x40)
            bLDMON = true;
        else
            bLDMON = false;

        tbLDMON = bLDMON;
        //'PVC/PMT RESPONCE

        if ((pacerData[23] & 0x20) == 0x20)
            bPVC = true;
        else
            bPVC = false;

        tbPVC = bPVC;
        //'Ventricular Safety Pacing
        //VSP = (wd(36) And &H10) / 16
        if ((pacerData[23] & 0x10) == 0x10)
            bVSP = true;
        else
            bVSP = false;

        tbVSP = bVSP;
        //'Non Competetive Atrial Pacing
        //NCAP = (wd(36) And &H8) / 8
        if ((pacerData[23] & 0x08) == 0x08)
            bNCAP = true;
        else
            bNCAP = false;

        tbNCAP = bNCAP;
        //'Auto Measure

        if ((pacerData[23] & 0x80) == 0x80)
            bVAM = true;
        else
            bVAM = false;

        tbVAM = bVAM;

        if ((pacerData[23] & 0x04) == 0x04)
            bAAM = true;
        else
            bAAM = false;

        tbAAM = bAAM;
        //'AT/AF Responce
        if ((pacerData[23] & 0x02) == 0x02)
            bATAF = true;
        else
            bATAF = false;

        tbATAF = bATAF;
        //'Rate Drop enable/disable

        if ((pacerData[23] & 0x01) == 0x01)
            bRDON = true;
        else
            bRDON = false;

        tbRDON = bRDON;
        //'19th byte
        //'Rate Drop Rate

        iRdRate = pacerData[24] & 0x1F;
        tRdRate = iRdRate;
        //'Rate Drop Duration (no. of beats)

        iRdPRD = (pacerData[24] & 0xE0) / 32;
        tRdPRD = iRdPRD;

        //'20th byte
        //'Biv ON/OFF
        bBIEna = (pacerData[25] & 0x80) == 0x80;

        tbBIEna = bBIEna;
        //'MRI
        if ((pacerData[25] & 0x40) == 0x40)
            bMRI = true;
        else
            bMRI = false;

        tbMRI = bMRI;
        //VRR (VRS)
        if ((pacerData[25] & 0x20) == 0x20)
            bVRR = true;
        else
            bVRR = false;
        tbVRR = bVRR;
        //'Rate Drop upper limit

        iRdUpLmt = (pacerData[25] & 0x1F);//+10;
        tRdUpLmt = iRdUpLmt;

        //'21th byte
        //'V-V interval

        iVVInterval = (pacerData[26] & 0xE0) / 32;
        tVVInterval = iVVInterval;

        //'Rate Drop lower limit

        iRdLoLmt = (pacerData[26] & 0x1F);//+ 5;
        tRdLoLmt = iRdLoLmt;
        //'22th byte
        //'Rate Drop 2nd window

        iRdSndW = (pacerData[27] & 0xE0) / 32;
        tRdSndW = iRdSndW;
        //'Rate Drop 1st window

        iRdFstW = (pacerData[27] & 0x1F);
        tRdFstW = iRdFstW;
        //'23rd byte
        //'LV PW

        iLVPW = (pacerData[28] & 0xF0) / 16;
        tLVPW = iLVPW;
        //'Atrial Blanking

        iABlnk = pacerData[28] & 0x07;
        tABlnk = iABlnk;
        //'LV Only

        if ((pacerData[28] & 0x08) == 0x08)
            bLVOnly = true;
        else
            bLVOnly = false;

        tbLVOnly = bLVOnly;
        //'24th byte

        //'LV Pol

        iLVPol = (pacerData[29] & 0xC0) / 64;
        tLVPol = iLVPol;
        //'VV Timing

        if ((pacerData[29] & 0x20) == 0x20)
            bLVFst = true;
        else
            bLVFst = false;

        tbLVFst = bLVFst;
        //'LV Amplitude

        iLVAmp = pacerData[29] & 0x1F;
        tLVAmp = iLVAmp;
        //'25th byte
        //'BATTERY
        iBat = pacerData[30];

    }

    //End of decode Pinn P series
    //code Pinnacle P series
    public void code_pinnP() {
        //1st byte
        //'MODE
        for (int i = 0; i < modeCtrlWD747.length; i++) {
            if (modeCtrlWD747[i] == (pacerData[6] & 0xFC)) {
                iMode = i;
                tMode = iMode;
                break;
            }
        }

        //'2nd byte
        //'APW VPW
        pacerDataPROG[7] = tAPW * 16 + tPW;

        //'3rd byte
        //'sens POL + 'AP POL + 'VP POL + 'A AMP
        pacerDataPROG[8] = tSenPol * 128 + tAPacepol * 64 + tPacePol * 32 + tAAmp;

        //'4th byte
        //'Magnet + 'Blanking +  'V AMP +
        pacerDataPROG[9] &= 0x80;
        pacerDataPROG[9] |= tBlnk * 32 + tAmp;

        //'5th byte
        //'V REF + Hyst Val
        pacerDataPROG[10] = tRef * 16 + thystVAL;

        //'6th byte
        //'PVARP + A Ref
        pacerDataPROG[11] = tPvarp * 16 + tARef;

        //'7th byte
        //'AT ENTRY COUNT + 'UPPER RATE
        pacerDataPROG[12] = tATEnt * 64 + tTrigUprRate;

        //'8th byte
        //'ARC + 'AT ENABLE + 'TARGET UPPER RATE (RR)

        pacerDataPROG[13] = tSensorRate;
        if (tbARC)
            pacerDataPROG[13] |= 0x80;

        if (tbATEna)
            pacerDataPROG[13] |= 0x40;

        //'9th byte
        //'ASEN + 'VSEN
        pacerDataPROG[14] = tASen * 16 + tSen;

        //'10th byte
        //'Hyst Search ON/OFF + 'RATE
        pacerDataPROG[15] = tRate;
        if (tbHystSrchON)
            pacerDataPROG[15] |= 0x80;

        //'11th byte
        //'AV Hyst Value + 'P AVD
        pacerDataPROG[16] = tAVHyst * 32 + iPAVI;

        //'12th byte
        //'Up Time + 'S AVD
        pacerDataPROG[17] = tuptime * 32 + tSAVI;

        //'13th byte
        //' SLOPE + 'Down Time
        pacerDataPROG[18] = tSlope * 16 + tDownTime;

        //'14th byte
        //'AT EXIT COUNT + 'AT TRIGGER RATE
        pacerDataPROG[19] = tATExt * 64 + tATRate;

        //'15th byte
        //'Auto POL SW + 'AV Hyst Search ON/OFF + ' Night Low rate
        pacerDataPROG[20] = tNightRT + 1;
        if (tbAutoPol)
            pacerDataPROG[20] |= 0x80;
        if (tbAVHSrch)
            pacerDataPROG[20] |= 0x40;

        //'16th byte
        //'Night rate enable + 'Night rate switch time Min:Hours
        pacerDataPROG[21] = tNightMIN * 32 + tNightHRS;

        if (tbNightEna)
            pacerDataPROG[21] |= 0x80;

        //'17th byte
        //'Enable Day rate switch on activity  + 'Day rate switch time Min:Hours
        pacerDataPROG[22] = tDayMIN * 32 + tDayHRS;
        if (tbDayEna)
            pacerDataPROG[22] |= 0x80;

        //'18th byte
        //'Flags for
        //'Auto Measure + 'LEAD MON  + 'PVC/PMT + 'VSP + NCAP + A AM + AT/AF + 'RD ena
        pacerDataPROG[23] = 0; //Clear value
        if (tbVAM)
            pacerDataPROG[23] |= 0x80;
        if (tbLDMON)
            pacerDataPROG[23] |= 0x40;
        if (tbPVC)
            pacerDataPROG[23] |= 0x20;
        if (tbVSP)
            pacerDataPROG[23] |= 0x10;
        if (tbNCAP)
            pacerDataPROG[23] |= 0x08;
        if (tbAAM)
            pacerDataPROG[23] |= 0x04;
        if (tbATAF)
            pacerDataPROG[23] |= 0x02;
        if (tbRDON)
            pacerDataPROG[23] |= 0x01;


        //'19th byte
        // 'Rate Drop Duration (no. of beats) + 'Rate Drop Rate
        pacerDataPROG[24] = tRdPRD * 32 + tRdRate;

        //'20th byte
        //'Biv ON/OFF + MRI + VRR + RD Upper Limit
        pacerDataPROG[25] = tRdUpLmt;
        if (tbBIEna)
            pacerDataPROG[25] |= 0x80;
        if (tbMRI)
            pacerDataPROG[25] |= 0x40;
        if (tbVRR)
            pacerDataPROG[25] |= 0x20;

        //'21th byte
        //'V-V interval + 'Rate Drop lower limit
        pacerDataPROG[26] = tVVInterval * 32 + tRdLoLmt;

        //'22th byte
        //'RD 2nd window + 'RD 1st window
        pacerDataPROG[27] = tRdSndW * 32 + tRdFstW;

        //'23rd byte
        //'LV PW + LV Only + 'Atrial Blanking
        pacerDataPROG[28] = tLVPW * 16 + tABlnk;
        if (tbLVOnly)
            pacerDataPROG[28] |= 0x08;

        //'24th byte
        //'LV Pol + 'VV Timing + 'LV Amplitude
        pacerDataPROG[29] = tLVPol * 64 + tLVAmp;
        if (tbLVFst)
            pacerDataPROG[29] |= 0x20;

        //'25th byte
        //'BATTERY

    }


    //End of code Pinn P series

    //**** Decode Statistics Counts *****
    public static void decode_stat_counts() {
        // **** Pinnacle R 297 / Pinnacle AM 8820 Stat Counts ***
        long cntExtSystole;
        if (iPacerSelect == 0x1B || iPacerSelect == 0x19) {
            // Pace Counts
            createCountString(0, pacerDataPROG[5], pacerDataPROG[4], pacerDataPROG[3], pacerDataPROG[2]);
            cntPace = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[9], pacerDataPROG[8], pacerDataPROG[7], pacerDataPROG[6]);
            cntPace = cntPace + Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[13], pacerDataPROG[12], pacerDataPROG[11], pacerDataPROG[10]);
            cntPace = cntPace + Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[17], pacerDataPROG[16], pacerDataPROG[15], pacerDataPROG[14]);
            cntPace = cntPace + Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[21], pacerDataPROG[20], pacerDataPROG[19], pacerDataPROG[18]);
            cntPace = cntPace + Long.parseLong(counts, 16);

            //Noise counts
            createCountString(0, pacerDataPROG[25], pacerDataPROG[24], pacerDataPROG[23], pacerDataPROG[22]);
            cntNoise = Long.parseLong(counts, 16);

            //Noise pace Counts
            createCountString(0, pacerDataPROG[29], pacerDataPROG[28], pacerDataPROG[27], pacerDataPROG[26]);
            cntNoisePace = Long.parseLong(counts, 16);

            //Exe Systole
            createCountString(0, pacerDataPROG[33], pacerDataPROG[32], pacerDataPROG[31], pacerDataPROG[30]);
            cntExtSystole = Long.parseLong(counts, 16);

            //Sens Counts
            //<80
            createCountString(0, pacerDataPROG[37], pacerDataPROG[36], pacerDataPROG[35], pacerDataPROG[34]);
            cntL80 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[41], pacerDataPROG[40], pacerDataPROG[39], pacerDataPROG[38]);
            cnt100 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[45], pacerDataPROG[44], pacerDataPROG[43], pacerDataPROG[42]);
            cnt120 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[49], pacerDataPROG[48], pacerDataPROG[47], pacerDataPROG[46]);
            cnt140 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[53], pacerDataPROG[52], pacerDataPROG[51], pacerDataPROG[50]);
            cntG140 = Long.parseLong(counts, 16);

            cntSens = cntL80 + cnt100 + cnt120 + cnt140 + cntG140;
        }
        // **** Pinnacle 8820 Stat Counts ***
        if (iPacerSelect == 0x02) {
            // Pace Counts
            createCountString(pacerDataPROG[6], pacerDataPROG[5], pacerDataPROG[4], pacerDataPROG[3], pacerDataPROG[2]);
            cntPace = Long.parseLong(counts, 16);

            createCountString(0, 0, pacerDataPROG[9], pacerDataPROG[8], pacerDataPROG[7]);
            cntNoise = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[13], pacerDataPROG[12], pacerDataPROG[11], pacerDataPROG[10]);
            cntNoisePace = Long.parseLong(counts, 16);


            createCountString(0, pacerDataPROG[17], pacerDataPROG[16], pacerDataPROG[15], pacerDataPROG[14]);
            cntExtSystole = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[21], pacerDataPROG[20], pacerDataPROG[19], pacerDataPROG[18]);
            cntL80 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[25], pacerDataPROG[24], pacerDataPROG[23], pacerDataPROG[22]);
            cnt100 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[29], pacerDataPROG[28], pacerDataPROG[27], pacerDataPROG[26]);
            cnt120 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[33], pacerDataPROG[32], pacerDataPROG[31], pacerDataPROG[30]);
            cnt140 = Long.parseLong(counts, 16);

            createCountString(0, pacerDataPROG[37], pacerDataPROG[36], pacerDataPROG[35], pacerDataPROG[34]);
            cntG140 = Long.parseLong(counts, 16);

            cntSens = cntL80 + cnt100 + cnt120 + cnt140 + cntG140;
        }
        //**** Charak DR (AT/AF)
        if (iPacerSelect == 12 || iPacerSelect == 24) {
            //V Pace Counts
            createCountString(0, pacerDataPROG[2], pacerDataPROG[3], pacerDataPROG[4], pacerDataPROG[5]);
            cntPace = Long.parseLong(counts, 16);
            //V Sens
            createCountString(0, pacerDataPROG[6], pacerDataPROG[7], pacerDataPROG[8], pacerDataPROG[9]);
            cntSens = Long.parseLong(counts, 16);

            //A Pace Counts
            createCountString(0, pacerDataPROG[10], pacerDataPROG[11], pacerDataPROG[12], pacerDataPROG[13]);
            cntL80 = Long.parseLong(counts, 16);
            //A Sens
            createCountString(0, pacerDataPROG[14], pacerDataPROG[15], pacerDataPROG[16], pacerDataPROG[17]);
            cnt100 = Long.parseLong(counts, 16);

            //AF Counts for Charak with AM/ AF
            if (iPacerSelect == 24) {
                //AF Counts
                createCountString(0, 0, 0, pacerDataPROG[18], pacerDataPROG[19]);
                cnt140 = Long.parseLong(counts, 16);
                //AT Counts
                createCountString(0, 0, 0, pacerDataPROG[20], pacerDataPROG[21]);
                cnt120 = Long.parseLong(counts, 16);
            } else {
                //AT Counts
                createCountString(0, pacerDataPROG[18], pacerDataPROG[19], pacerDataPROG[20], pacerDataPROG[21]);
                cnt120 = Long.parseLong(counts, 16);
            }
            //Noise Counts
            createCountString(0, 0, pacerDataPROG[22], pacerDataPROG[23], pacerDataPROG[24]);
            cntNoise = Long.parseLong(counts, 16);
            //Noise Pace Counts
            createCountString(0, pacerDataPROG[25], pacerDataPROG[26], pacerDataPROG[27], pacerDataPROG[28]);
            cntNoisePace = Long.parseLong(counts, 16);


        }
    }


    //Create Counts string for statistics
    private static void createCountString(int d4, int d3, int d2, int d1, int d0) {
        counts = "";

        if (d4 < 0x10)
            counts = counts + "0" + Integer.toHexString(d4);
        else
            counts = counts + Integer.toHexString(d4);
        if (d3 < 0x10)
            counts = counts + "0" + Integer.toHexString(d3);
        else
            counts = counts + Integer.toHexString(d3);
        if (d2 < 0x10)
            counts = counts + "0" + Integer.toHexString(d2);
        else
            counts = counts + Integer.toHexString(d2);
        if (d1 < 0x10)
            counts = counts + "0" + Integer.toHexString(d1);
        else
            counts = counts + Integer.toHexString(d1);
        if (d0 < 0x10)
            counts = counts + "0" + Integer.toHexString(d0);
        else
            counts = counts + Integer.toHexString(d0);

    }

    //Clear stat vars
    public void clrStatVar() {
        cntPace = 0; //Total Pace
        cntSens = 0; //Total Sens
        cntNoise = 0; //Noise Counts
        cntNoisePace = 0; //Noise Counts
        cntL80 = 0; //Sens less than 80ppm
        cnt100 = 0; //Sens less than 100ppm
        cnt120 = 0; //Sens less than 120ppm
        cnt140 = 0; //Sens less than 140ppm
        cntG140 = 0; //Sens more than 140ppm
    }
    // ***** Log Files Read / Write *******


    //**************

    // ***** ECG Marker Log Files Write *******

    /*   public void markerFileWrite() {
           //**** File Write ****
   //Get Marker Class obj
           marker markClassInst = marker.getInstance();
           String path;
           if (mainActivity.bthrScreen)
               path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SPL_LOG/thrfiles/";
           else
               path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SPL_LOG/markfiles/";

           File dir = new File(path);
           if (!dir.exists()) {
               dir.mkdirs();
           }

           String fileName;
           if (mainActivity.bthrScreen)
               fileName= iSrno + ".thr"; // Threshold File
           else
               fileName= iSrno + ".mrk"; // Marker File

           OutputStream outputStream = null;
           try {
               File file1 = new File(dir, fileName);
               if (!file1.exists())
                   file1.createNewFile();

               outputStream = new FileOutputStream(file1, true);
               int nBytes = markerData[1]+markerData[40] + 10 + 3;

               outputStream.write(0x4); // marker/ecg Data Bytes to write
               outputStream.write(nBytes); //total bytes
               byte[] paraDT = new byte[nBytes-3];
               for (int i = 0; i < (nBytes-3); i++) {
                   paraDT[i] = (byte) (0xFF & markerData[i]);
               }
               outputStream.write(paraDT);

               //Write para,paraindex and Chamber value as per Mode
               //set annotation as per mode for DDD
               if (iPacerSelect==12 || iPacerSelect==24) {
                   if (mainActivity.bthrScreen) //if threshold test in DDD
                   {
                       if (markClassInst.bThrChamber==false) {
                           if (markClassInst.bVPWThr) {
                               outputStream.write(0x01);
                               outputStream.write(iAPW);
                           } else if (markClassInst.bVSThr) {
                               outputStream.write(0x02);
                               outputStream.write(iASen);
                           } else
                           {
                               outputStream.write(0);
                               outputStream.write(iAAmp);
                           }
                           //Chamber Value
                           outputStream.write(0x41); //A
                       }
                       else {
                           if (markClassInst.bVPWThr) {
                               outputStream.write(0x01);
                               outputStream.write(iPW);
                           } else if (markClassInst.bVSThr) {
                               outputStream.write(0x02);
                               outputStream.write(iSen);
                           } else
                           {
                               outputStream.write(0);
                               outputStream.write(iAmp);
                           }
                           //Chamber Value
                           outputStream.write(0x56); //V
                       }
                   }
                   else {// if Marker in DDD
                       if (markClassInst.aMarkVal>0) {
                           if (markClassInst.aMarkVal == 1) { //if Sens
                               outputStream.write(0x02);
                               outputStream.write(iASen);
                           } else {
                               outputStream.write(0);
                               outputStream.write(iAAmp);
                           }
                           outputStream.write(0x41); //A
                       }
                       else if (markClassInst.vMarkVal>0) {

                           if (markClassInst.vMarkVal==2) { //if Sens
                               outputStream.write(0x02);
                               outputStream.write(iSen);
                           } else
                           {
                               outputStream.write(0);
                               outputStream.write(iAmp);
                           }
                           outputStream.write(0x56); //V
                       }
                       else{
                           outputStream.write(0);
                           outputStream.write(iAmp);
                           outputStream.write(0x56); //V //default
                       }

                   }
               }
               else { //set annotation as per mode for SSI
                   //If Threshold
                   if (mainActivity.bthrScreen) {
                       if (markClassInst.bVPWThr) {
                           outputStream.write(0x01);
                           outputStream.write(iPW);
                       } else if (markClassInst.bVSThr) {
                           outputStream.write(0x02);
                           outputStream.write(iSen);
                       } else {
                           outputStream.write(0);
                           outputStream.write(iAmp);
                       }
                       if ((iMode >= 0 && iMode <= 3) || (iMode >= 8 && iMode <= 10)) {
                           outputStream.write(0x56); //"V"
                       } else if ((iMode >= 4 && iMode <= 7) || (iMode >= 11 && iMode <= 13)) {
                           outputStream.write(0x41); //"A"
                       } else
                           outputStream.write(0);
                   }
                   //If Marker
                   else {
                       if (markClassInst.vMarkVal==2)//if sens
                       {
                           outputStream.write(2);
                           outputStream.write(iSen);
                       }
                       else{
                           outputStream.write(0);
                           outputStream.write(iAmp);
                       }
                       if ((iMode >= 0 && iMode <= 3) || (iMode >= 8 && iMode <= 10)) {
                           outputStream.write(0x56); //"V"
                       } else if ((iMode >= 4 && iMode <= 7) || (iMode >= 11 && iMode <= 13)) {
                           outputStream.write(0x41); //"A"
                       } else
                           outputStream.write(0x56);//default V
                   }

               }

               outputStream.write(0x20); //End of data
               outputStream.flush();

           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               if (outputStream != null) {
                   try {
                       outputStream.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
       }*/
    //**************
//***********   Calculate Remaining Life **********
    public static void remLifeCalc() {
        int perPacing;
        double fRate, aAMP, vAMP, aPW, vPW, CDS, CDS1, bps;
        double batAh = 1.26, rmbatah;
        double expLife, rmLife;
        double bat;
        if (iPacerSelect == 12 || iPacerSelect == 24) //Charak DR
            bat = 313.65 / (255 - iBat);
        else if (iPacerSelect == 2)// Old pinn 8820
            bat = 312.34 / (164 - iBat);
        else
            bat = (312.32 / (255 - iBat));

        bat = Double.parseDouble(new DecimalFormat("##.##").format(bat));
        boolean bARange1, bARange2, bARange3, bVRange1, bVRange2, bVRange3;
        bARange1 = false;
        bARange2 = false;
        bARange3 = false;
        bVRange1 = false;
        bVRange2 = false;
        bVRange3 = false;

        //per = Val(Combo1.Text)
        perPacing = 100;
        //f = Val(Flongrate.Caption)
        if (iPacerSelect == 12 || iPacerSelect == 24) //If Dual Chamber
        {

            //fRate = rateArray747[iRate];
            fRate = rateArray747[tRate];
            //a = Val(Flongamp.Caption)
            //aAMP= amparray297[iAAmp];
            aAMP = amparray297[tAAmp];
            //a1 = Val(FlongVamp.Caption)
            //vAMP = amparray297[iAmp];
            vAMP = amparray297[tAmp];
            //te = Val(Flongpw.Caption)
            //aPW = pwArray747[iAPW];
            aPW = pwArray747[tAPW];
            //tw1 = Val(FlongVpw.Caption)
            //vPW = pwArray747[iPW];
            vPW = pwArray747[tPW];

        } else //Single Chambe
        {
            //fRate = rateArray[iRate];
            fRate = rateArray[tRate];
            aAMP = 5.0;
            //vAMP = amparray297[iAmp];
            if (iPacerSelect == 2) //old Pinn 8820
                vAMP = amparray[tAmp];
            else
                vAMP = amparray297[tAmp];
            aPW = 0.5;
            //vPW = pwArray[iPW];
            vPW = pwArray[tPW];
        }


        CDS = 0;
        CDS1 = 0;
        //'A Amp
        //If (0.2 <= a And a <= 2.5) Then Range1 = True
        if (0.2 <= aAMP && aAMP <= 2.5) bARange1 = true;
        //If (2.6 <= a And a <= 5) Then Range2 = True
        if (2.6 <= aAMP && aAMP <= 5) bARange2 = true;
        //If (5.2 <= a And a <= 7.5) Then Range3 = True
        if (5.2 <= aAMP && aAMP <= 7.5) bARange3 = true;
        //'V Amp
        //If (0.2 <= a1 And a1 <= 2.5) Then vrange1 = True
        if (0.2 <= vAMP && vAMP <= 2.5) bVRange1 = true;
        //If (2.6 <= a1 And a1 <= 5) Then vrange2 = True
        if (2.6 <= vAMP && vAMP <= 5) bVRange2 = true;
        //If (5.2 <= a1 And a1 <= 7.5) Then vrange3 = True
        if (5.2 <= vAMP && vAMP <= 7.5) bVRange3 = true;


        //p = f / 60
        bps = fRate / 60;
        //tw = te / 1000
        aPW = aPW / 1000;
        //tw1 = tw1 / 1000
        vPW = vPW / 1000;

        String strMode;
        if (iPacerSelect == 12 || iPacerSelect == 24) // if Dual Chamber
            strMode = modeArray747[iMode];
        else if (iPacerSelect == 2 || iPacerSelect == 27) {
            List<String> key8820 = new ArrayList<>(map8820.keySet());
            strMode = key8820.get(iMode);
        } else {
            List<String> key297 = new ArrayList<>(map297_8820.keySet());
            strMode = key297.get(iMode);
        }


        if (iPacerSelect == 5 || iPacerSelect == 6 || iPacerSelect == 7 || iPacerSelect == 8 || iPacerSelect == 10 ||
                iPacerSelect == 12 || iPacerSelect == 24 || iPacerSelect == 0x15 || iPacerSelect == 0x1C || iPacerSelect == 0x13 ||
                iPacerSelect == 0x10) {
            if (iPacerSelect != 10 && iPacerSelect != 8) //'if VDD pacer then no ' A '
            {
                if (bARange1)
                    //' CDS1 = ((2 * 10 * 2.8 * 1.147 * a * (15 / 25) * (1 - Exp(-tw / (532 * 0.000006))) * p / 2.8))
                    CDS1 = (13.76 * aAMP * (1 - Math.exp(-aPW / 0.003192)) * bps);
                else if (bARange3)
                    //CDS1 = (3 * 10 * 2.8 * 0.638 * aAMP * (1 - Math.exp(-aPW / (520 * 0.00000566))) * bps / 2.8);
                    CDS1 = (19.14 * aAMP * (1 - Math.exp(-aPW / 0.0029432)) * bps);
                else //'Range2
                    //CDS1 = ((2 * 10 * 2.8 * 0.638 * aAMP * (1 - Math.exp(-aPW / (520 * 0.00000566))) * bps / 2.8));
                    CDS1 = (12.76 * aAMP * (1 - Math.exp(-aPW / 0.0029432)) * bps);

            }

            if (bVRange1) {
                //'CDS = ((( 2 * 10 * 2.8 * 1.147 * a1 * (15 / 25) * (1 - Exp(-tw1 / (532 * 0.000006))) * p / 2.8)))
                CDS = (13.76 * vAMP * (1 - Math.exp(-vPW / 0.003192)) * bps);
                //'        CDS = 6 + 6 + 2.1 * p + CDS * per / 100
            } else if (bVRange3) {
                //CDS = ((3 * 10 * 2.8 * 0.638 * vAMP * (1 - Math.exp(-vPW / (520 * 0.00000566))) * bps / 2.8));
                CDS = (19.14 * vAMP * (1 - Math.exp(-vPW / 0.0029432)) * bps);
                //'        CDS = 6 + 6 + 2.4 * p + CDS * per / 100
            } else {//'vRange2
                //CDS = (((2 * 10 * 2.8 * 0.638 * vAMP * (1 - Math.exp(-vPW / (520 * 0.00000566))) * bps / 2.8)));
                CDS = (12.76 * vAMP * (1 - Math.exp(-vPW / 0.0029432)) * bps);
                //'        CDS = 6 + 6 + 2.1 * p + CDS * per / 100
            }

            // If(auto1.lmo.Caption = "VVI"Or auto1.lmo.Caption = "VOO"Or auto1.lmo.Caption = "VVT") Then

            if (strMode == "VVI" || strMode == "VOO" || strMode == "VVT") {
                // Do Nothing
            }
            //   ElseIf auto1.lmo.Caption = "VDD" Then
            else if (strMode == "VDD") {
                CDS = CDS + 6;
            }
            //ElseIf(auto1.lmo.Caption = "AAI"Or auto1.lmo.Caption = "AOO"Or auto1.lmo.Caption = "AAT") Then
            else if (strMode == "AAI" || strMode == "AOO" || strMode == "AAT") {
                CDS = CDS1;
            }
            //ElseIf(auto1.lmo.Caption = "DDD"Or auto1.lmo.Caption = "DOO"Or auto1.lmo.Caption = "DDI"Or _
            //      auto1.lmo.Caption = "DVI") Then
            else if (strMode == "DDD" || strMode == "DOO" || strMode == "DDI" || strMode == "DVI") {
                CDS = CDS + CDS1;
            }
            //ElseIf(auto1.lmo.Caption = "ODO"Or auto1.lmo.Caption = "OAO"Or auto1.lmo.Caption = "OVO") Then
            else if (strMode == "ODO" || strMode == "OAO" || strMode == "OVO") {
                CDS = 0;
            }
            //End If
            //    '**** if CHARAK-DR
            //If(auto1.lmo.Caption = "VVIR"Or auto1.lmo.Caption = "VOOR"Or auto1.lmo.Caption = "VVTR")
            //Then
            if (strMode == "VVIR" || strMode == "VOOR" || strMode == "VVTR") {
                CDS = CDS + 2.5;
            }
            //ElseIf auto1.lmo.Caption = "VDDR" Then
            else if (strMode == "VDDR") {
                CDS = CDS + 8.5;
            }
            //ElseIf(auto1.lmo.Caption = "AAIR"Or auto1.lmo.Caption = "AOOR"Or auto1.lmo.Caption = "AATR")  Then
            else if (strMode == "AAIR" || strMode == "AOOR" || strMode == "AATR") {
                CDS = CDS1 + 2.5;
            }
            //ElseIf(auto1.lmo.Caption = "DDDR"Or auto1.lmo.Caption = "DOOR"Or auto1.lmo.Caption = "DDIR"Or _
            //      auto1.lmo.Caption = "DVIR") Then
            else if (strMode == "DDDR" || strMode == "DOOR" || strMode == "DDIR" || strMode == "DVIR") {
                CDS = CDS + CDS1 + 2.5;
            }
            //ElseIf(auto1.lmo.Caption = "ODO"Or auto1.lmo.Caption = "OAO"Or auto1.lmo.Caption = "OVO")  Then
            else if (strMode == "ODO" || strMode == "OAO" || strMode == "OVO") {
                CDS = 0;
            }
            //End If
            //'***********************
            CDS = 6 + 6 + 2.1 * bps + CDS * perPacing / 100;
        } else // Single Chamber
        {
            //If(Range1) Then
            //  '        CDS = CDS + (((2 * 10 * 2.8 * 1.147 * a * (15 / 25) * (1 - Exp(-tw / (532 * 0.000006))) * p / 2.8)))
            if (bVRange1) {
                CDS = CDS + (13.76 * vAMP * (1 - Math.exp(-vPW / 0.003192)) * bps);

                CDS = 6 + 2.1 * bps + CDS * perPacing / 100;
            }
            //ElseIf(Range3) Then
            else if (bVRange3) {
                CDS = CDS + (19.14 * vAMP * (1 - Math.exp(-vPW / 0.0029432)) * bps);
                CDS = 6 + 2.4 * bps + CDS * perPacing / 100;
            }
            //Else 'Range2
            else {
                CDS = CDS + (12.76 * vAMP * (1 - Math.exp(-vPW / 0.0029432)) * bps);
                CDS = 6 + 2.1 * bps + CDS * perPacing / 100;
            }
            //End If
            //If(pacerselect = 25Or pacerselect = 13Or pacerselect = & H13)Then
            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x13) {
                // 'Or pacerselect = &H1B Or pacerselect = &H1D _
                //'Or pacerselect = &H1E Then

                //If(auto1.lmo.Caption = "VVIR"Or auto1.lmo.Caption = "VOOR"Or auto1.lmo.Caption = "VVTR"Or _
                //      auto1.lmo.Caption = "AAIR"Or auto1.lmo.Caption = "AOOR"Or auto1.lmo.Caption = "AATR") Then
                if (strMode == "VVIR" || strMode == "VOOR" || strMode == "VVTR" || strMode == "AAIR" || strMode == "AOOR" || strMode == "AATR")
                    CDS = CDS + 4.5;// '2.5
                else
                    CDS = CDS + 1.5;
                //End If
            }//End If
        }//End If

        //If (pacerselect = 12 Or pacerselect = 24 Or pacerselect = &H1C) Then
        if (iPacerSelect == 12 || iPacerSelect == 24 || iPacerSelect == 0x1C) {
            //If CDS >3 Then CDS = CDS - 2.5
            if (CDS > 3)
                CDS -= 2.5;
        }//End If
        CDS = CDS + 3;
        //te = (batAh * 1000000 / (24 * 365 * CDS))

        expLife = (batAh * 1000000 / (24 * 365 * CDS));
        //exlife = CStr(Int(te)) ' Year
        remYear = (int) (expLife);

        //te = Int((te - Val(exlife)) * 365 / 30) 'Months
        remMonth = (int) ((expLife - remYear) * 365 / 30);
        //'''te = te Mod 12
        //exlife = exlife + " Yrs " + CStr(te) + " Mon"

        //Label1(15) = exlife
        if (bat >= 2.79) rmbatah = batAh;
        else if (bat >= 2.75 && bat < 2.79) rmbatah = batAh - (batAh * 0.15);
        else if (bat >= 2.7 && bat < 2.75) rmbatah = batAh - (batAh * 0.35);
        else if (bat >= 2.65 && bat < 2.7) rmbatah = batAh - (batAh * 0.55);
        else if (bat >= 2.6 && bat < 2.65) rmbatah = batAh - (batAh * 0.75);
        else if (bat >= 2.55 && bat < 2.6) rmbatah = batAh - (batAh * 0.8);
        else if (bat >= 2.5 && bat < 2.55) rmbatah = batAh - (batAh * 0.85);
        else if (bat >= 2.45 && bat < 2.5) rmbatah = batAh - (batAh * 0.9);
        else if (bat >= 2.4 && bat < 2.45) rmbatah = batAh - (batAh * 0.95);
        else rmbatah = 0;

        //vent1:

        //if bat >2.6v then reduce current by 15%
        //if bat <=2.6 then increase current by 15%
        /*if (bat>2.6)
            CDS = CDS - ((CDS * 15)/100);
        else
            CDS = CDS + ((CDS * 15)/100);*/

        rmLife = ((rmbatah * 1000000) / (24 * 365 * CDS));

        //if bat >2.6v then increase life by 15%
        //if bat <=2.6 then reduce life by 15%
        if (bat > 2.6)
            rmLife = rmLife + ((rmLife * 15) / 100);
        else
            rmLife = rmLife - ((rmLife * 15) / 100);

        remYear = (int) (rmLife);

        remMonth = (int) ((((rmLife - remYear) * 365) / 30));

    }


    public static ArrayAdapter<String> funSetArrayAdapterStringLong(Activity activity, List<String> keys, int selection, byte paraCode) {
        ArrayAdapter<String> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<String>(activity, R.layout.dropdown_item_long, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        return mStringArrayAdapter;

    }

    public static ArrayAdapter<String> funMediumSetArrayAdapterString(Activity activity, List<String> keys, int selection, byte paraCode) {
        ArrayAdapter<String> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<String>(activity, R.layout.dropdown_item_medium, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item_medium);
        return mStringArrayAdapter;

    }

    public static ArrayAdapter<Integer> funMediumSetArrayAdapterInteger(Activity activity, List<Integer> keys, int selection, byte paraCode) {
        ArrayAdapter<Integer> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<Integer>(activity, R.layout.dropdown_item_medium, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item_medium);
        return mStringArrayAdapter;

    }

    public static ArrayAdapter<Double> funMediumSetArrayAdapterDouble(Activity activity, List<Double> keys, int selection, byte paraCode) {
        ArrayAdapter<Double> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<Double>(activity, R.layout.dropdown_item_medium, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item_medium);
        return mStringArrayAdapter;

    }


    public static ArrayAdapter<String> funSmallSetArrayAdapterString(Activity activity, List<String> keys, int selection, byte paraCode) {
        ArrayAdapter<String> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<String>(activity, R.layout.dropdown_item_small, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        return mStringArrayAdapter;

    }

    public static ArrayAdapter<Integer> funSmallSetArrayAdapterInteger(Activity activity, List<Integer> keys, int selection, byte paraCode) {
        ArrayAdapter<Integer> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<Integer>(activity, R.layout.dropdown_item_small, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        return mStringArrayAdapter;

    }

    public static ArrayAdapter<Double> funSmallSetArrayAdapterDouble(Activity activity, List<Double> keys, int selection, byte paraCode) {
        ArrayAdapter<Double> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<Double>(activity, R.layout.dropdown_item_small, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        return mStringArrayAdapter;

    }

    public static ArrayAdapter<String> funSetArrayAdapterString(Activity activity, List<String> keys, int selection, byte paraCode) {
        ArrayAdapter<String> mStringArrayAdapter;

        mStringArrayAdapter = new ArrayAdapter<String>(activity, R.layout.dropdown_item, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }

                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mStringArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        return mStringArrayAdapter;

    }

    public static ArrayAdapter<Integer> funSetArrayAdapterInteger(int a, Activity activity, List<Integer> keys, int selection, byte paraCode) {
        System.out.println("<><><><>@@@@@#####$$$$$  " + a);
        ArrayAdapter<Integer> mIntegerArrayAdapter;
        mIntegerArrayAdapter = new ArrayAdapter<Integer>(activity, R.layout.dropdown_item, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24)
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    ;
                    //
                }
                return textView;
            }
        };
        mIntegerArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        return mIntegerArrayAdapter;

    }

    public static ArrayAdapter<Double> funSetArrayAdapterDouble(int pp, Activity activity, List<Double> keys, int selection, byte paraCode) {
        System.out.println("<><><>@@$$$$$$<><> " + pp);
        ArrayAdapter<Double> mDoubleArrayAdapter;
        mDoubleArrayAdapter = new ArrayAdapter<Double>(activity, R.layout.dropdown_item, keys) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (paraCode == 0) {
                    if (selection != position)
                        tv.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else
                        tv.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    if (iPacerSelect == 12 || iPacerSelect == 24) {
                        System.out.println("<><><><>@@@@@@@ " + paraCode);
                        tv.setTextColor(getColor747_B(activity, position, paraCode));
                    } else
                        tv.setTextColor(getColor297_B(position, paraCode, activity));
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == selection) {
                    textView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    // for other views
                    if (paraCode == 0)
                        textView.setTextColor(activity.getResources().getColor(R.color.Blue));
                    else {
                        if (iPacerSelect == 12 || iPacerSelect == 24)
                            textView.setTextColor(getColor747_B(activity, position, paraCode));
                        else
                            textView.setTextColor(getColor297_B(position, paraCode, activity));
                    }
                    //
                }
                return textView;
            }
        };
        mDoubleArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        return mDoubleArrayAdapter;

    }

    public static int getColor297(int position, byte paraCode, Activity activity) {
        funCheckValidationList();
        List<String> keysValue = new ArrayList<>(mapValidationMsg.values());
        int iColor = Color.BLUE;

        switch (paraCode) {

            case 'M': //MODE
                if (position == iMode)
                    iColor = Color.BLACK;

                break;

            case 'R': //RATE
                if (position == iRate)
                    iColor = Color.BLACK;
                if (position == 44 && tRef == 20) //Chk with Ref
                {
                    if (tMode != 1 && tMode != 5 && tMode != 9 && tMode != 12) //if not AOO VOO AOOR VOOR
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Refractory Period should be Less Than Period of Rate") < 0)
                                mapValidationMsg.put(10, "Refractory Period should be Less Than Period of Rate");

                            //activity, "Refractory Period should be Less Than Period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(10);
                    }
                }

                if ((rateArray[position] - hystArrayInt297[thystVAL] < 32) && tbHystON) {
                    if (tMode == 0 || tMode == 2 || tMode == 4 || tMode == 6) //VVI VVT AAI AAT
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("(Rate - Hysteresis) > 32 ppm") < 0)
                                mapValidationMsg.put(11, "(Rate - Hysteresis) > 32 ppm");
                            //activity, "(Rate - Hysteresis) > 32 ppm", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(11);
                    }

                }
                if (tMode > 7 && tMode < 14) // If R mode ON then chk trig up rate
                {
                    if (rateArray[position] >= uprArray297[tSensorRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Target Upper Rate should be Greater Than basic Rate") < 0)
                                mapValidationMsg.put(12, "Target Upper Rate should be Greater Than basic Rate");
                            //activity, "Target Upper Rate should be Greater Than basic Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(12);
                    }

                }
                if (tMode == 2 || tMode == 6 || tMode == 10 || tMode == 13) // SST/SSTR mode then  chk with upr rate
                {
                    if (rateArray[position] >= uprArray297[tTrigUprRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Upper Rate should be Greater Than basic Rate") < 0)
                                mapValidationMsg.put(13, "Upper Rate should be Greater Than basic Rate");
                            //activity, "Upper Rate should be Greater Than basic Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(13);
                    }
                }

                break;

            case 'A': // Amplitude
                if (position == iAmp)
                    iColor = Color.BLACK;
                List<Double> keyAmp297_1 = new ArrayList<>(mapAMP297.keySet());
                List<Double> keyPw297_1 = new ArrayList<>(mapPW297.keySet());
                if ((keyAmp297_1.get(position) * keyPw297_1.get(tPW) * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                        if (keysValue.indexOf("This Amplitude and PulseWidth value is not Valid") < 0)
                            mapValidationMsg.put(14, "This Amplitude and PulseWidth value is not Valid");
                        //activity, "This Amplitude and PulseWidth value is not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(14);
                }
                break;

            case 'W': // PW
                if (position == iPW)
                    iColor = Color.BLACK;
                List<Double> keyAmp297_2 = new ArrayList<>(mapAMP297.keySet());
                List<Double> keyPw297_2 = new ArrayList<>(mapPW297.keySet());
                if ((keyAmp297_2.get(tAmp) * keyPw297_2.get(position) * 10) >= 76) {

                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                        if (keysValue.indexOf("This Amplitude and PulseWidth value is not Valid") < 0)
                            mapValidationMsg.put(15, "This Amplitude and PulseWidth value is not Valid");
                        //activity, "This Amplitude and PulseWidth value is not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(15);
                }
                break;
            case 'S'://Sens
                if (position == iSen)
                    iColor = Color.BLACK;

                break;
            case 'F': // Ref
                if (position == iRef)
                    iColor = Color.BLACK;
                if (position == 20 && tRate == 44) //Check para selection is valid
                {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                        if (keysValue.indexOf("Refractory Period should be Less Than Period of Rate") < 0)
                            mapValidationMsg.put(16, "Refractory Period should be Less Than Period of Rate");
                        //activity, "Refractory Period should be Less Than Period of Rate", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(16);
                }

                if (tMode == 8 || tMode == 9 || tMode == 11 || tMode == 12) // SSIR mode then  chk with upr rate
                {
                    if ((60000 / uprArray297[tSensorRate]) <= refArray297[position]) // Chk with Target Upr Rate
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Target Upper Rate should be Greater Than Refractory Period") < 0)
                                mapValidationMsg.put(17, "Period of Target Upper Rate should be Greater Than Refractory Period");
                            //activity, "Period of Target Upper Rate should be Greater Than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(17);
                    }
                }
                if (tMode == 10 || tMode == 13) // SSTR mode then  chk with upr rate
                {
                    if ((60000 / uprArray297[tSensorRate]) <= refArray297[position]) // Chk with Target Upr Rate
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Target Upper Rate should be Greater Than Refractory Period") < 0)
                                mapValidationMsg.put(17, "Period of Target Upper Rate should be Greater Than Refractory Period");
                            //activity, "Period of Target Upper Rate should be Greater Than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(17);
                    }
                    if ((60000 / uprArray297[tTrigUprRate]) <= refArray297[position]) //Chk with trig Upr Rate
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Upper Rate should be Greater Than Refractory Period") < 0)
                                mapValidationMsg.put(19, "Period of Upper Rate should be Greater Than Refractory Period");
                            //activity, "Period of Upper Rate should be Greater Than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(19);
                    }
                }
                if (tMode == 2 || tMode == 6) // SST mode then  chk with upr rate
                {
                    if ((60000 / uprArray297[tTrigUprRate]) <= refArray297[position]) //Chk with Ref
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Upper Rate should be Greater Than Refractory Period") < 0)
                                mapValidationMsg.put(19, "Period of Upper Rate should be Greater Than Refractory Period");
                            //activity, "Period of Upper Rate should be Greater Than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(19);
                    }
                }
                break;
            case 'H': //Hyst
                if (position == ihystVAL)
                    iColor = Color.BLACK;
                else if (rateArray[tRate] - hystArrayInt297[position] < 32) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                        if (keysValue.indexOf("(Rate - Hysteresis) > 32 ppm") < 0)
                            mapValidationMsg.put(11, "(Rate - Hysteresis) > 32 ppm");
                        //activity, "(Rate - Hysteresis) > 32 ppm", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(11);
                }

                break;
            case 'C': //Sensor Rate
                if (position == iSensorRate)
                    iColor = Color.BLACK; //1;
                if (tMode > 7 && tMode < 14) // If R mode ON then chk target up rate
                {
                    //With Rate
                    if (rateArray[tRate] >= uprArray297[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Target Upper Rate should be Greater Than basic Rate") < 0)
                                mapValidationMsg.put(22, "Target Upper Rate should be Greater Than basic Rate");
                            //activity, "Target Upper Rate should be Greater Than basic Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(22);
                    }
                    //With Ref
                    if ((60000 / uprArray297[position]) <= refArray297[tRef]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Target Upper Rate should be Greater Than Refractory Period") < 0)
                                mapValidationMsg.put(17, "Period of Target Upper Rate should be Greater Than Refractory Period");
                            //activity, "Period of Target Upper Rate should be Greater Than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(17);
                    }
                }
                break;
            case 'U': // Upper Rate
                if (position == iTrigUprRate)
                    iColor = Color.BLACK; //1;
                if (tMode == 2 || tMode == 6 || tMode == 10 || tMode == 13) // SST/SSTR mode then  chk with upr rate
                {
                    if (uprArray297[position] <= rateArray[tRate]) // Chk with Rate
                    {
                        iColor = Color.RED; //2;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Upper Rate should be Greater Than basic Rate") < 0)
                                mapValidationMsg.put(24, "Upper Rate should be Greater Than basic Rate");
                            //activity, "Upper Rate should be Greater Than basic Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(24);
                    }
                    if ((60000 / uprArray297[position]) <= refArray297[tRef]) //Chk with Ref
                    {
                        iColor = Color.RED; //2;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Upper Rate should be Greater Than Refractory Period") < 0)
                                mapValidationMsg.put(19, "Period of Upper Rate should be Greater Than Refractory Period");
                            //activity, "Period of Upper Rate should be Greater Than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(19);
                    }
                }
                break;

        }
        return iColor;
    }

    public static int getColor297_B(int position, byte paraCode, Activity activity) {

        int iColor = Color.BLUE;

        switch (paraCode) {

            case 'M': //MODE
                if (position == iMode)
                    iColor = Color.BLACK;

                break;

            case 'R': //RATE
                if (position == iRate)
                    iColor = Color.BLACK;
                if (position == 44 && tRef == 20) //Chk with Ref
                {
                    if (tMode != 1 && tMode != 5 && tMode != 9 && tMode != 12) //if not AOO VOO AOOR VOOR
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            //activity, "Refractory Period should be Less Than Period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                if ((rateArray[position] - hystArrayInt297[thystVAL] < 32) && tbHystON) {
                    if (tMode == 0 || tMode == 2 || tMode == 4 || tMode == 6) //VVI VVT AAI AAT
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                if (tMode > 7 && tMode < 14) // If R mode ON then chk trig up rate
                {
                    if (rateArray[position] >= uprArray297[tSensorRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                if (tMode == 2 || tMode == 6 || tMode == 10 || tMode == 13) // SST/SSTR mode then  chk with upr rate
                {
                    if (rateArray[position] >= uprArray297[tTrigUprRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }

                break;

            case 'A': // Amplitude
                if (position == iAmp)
                    iColor = Color.BLACK;
                List<Double> keyAmp297_1 = new ArrayList<>(mapAMP297.keySet());
                List<Double> keyPw297_1 = new ArrayList<>(mapPW297.keySet());
                if ((keyAmp297_1.get(position) * keyPw297_1.get(tPW) * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                    }
                }
                break;

            case 'W': // PW
                if (position == iPW)
                    iColor = Color.BLACK;
                List<Double> keyAmp297_2 = new ArrayList<>(mapAMP297.keySet());
                List<Double> keyPw297_2 = new ArrayList<>(mapPW297.keySet());
                if ((keyAmp297_2.get(tAmp) * keyPw297_2.get(position) * 10) >= 76) {

                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                    }
                } else {
                    mapValidationMsg.remove(15);
                }
                break;
            case 'S'://Sens
                if (position == iSen)
                    iColor = Color.BLACK;

                break;
            case 'F': // Ref
                if (position == iRef)
                    iColor = Color.BLACK;
                if (position == 20 && tRate == 44) //Check para selection is valid
                {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                    }
                }

                if (tMode == 8 || tMode == 9 || tMode == 11 || tMode == 12) // SSIR mode then  chk with upr rate
                {
                    if ((60000 / uprArray297[tSensorRate]) <= refArray297[position]) // Chk with Target Upr Rate
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (tMode == 10 || tMode == 13) // SSTR mode then  chk with upr rate
                {
                    if ((60000 / uprArray297[tSensorRate]) <= refArray297[position]) // Chk with Target Upr Rate
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if ((60000 / uprArray297[tTrigUprRate]) <= refArray297[position]) //Chk with trig Upr Rate
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (tMode == 2 || tMode == 6) // SST mode then  chk with upr rate
                {
                    if ((60000 / uprArray297[tTrigUprRate]) <= refArray297[position]) //Chk with Ref
                    {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                break;
            case 'H': //Hyst
                if (position == ihystVAL)
                    iColor = Color.BLACK;
                else if (rateArray[tRate] - hystArrayInt297[position] < 32) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                    }
                }

                break;
            case 'C': //Sensor Rate
                if (position == iSensorRate)
                    iColor = Color.BLACK; //1;
                if (tMode > 7 && tMode < 14) // If R mode ON then chk target up rate
                {
                    //With Rate
                    if (rateArray[tRate] >= uprArray297[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    //With Ref
                    if ((60000 / uprArray297[position]) <= refArray297[tRef]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                break;
            case 'U': // Upper Rate
                if (position == iTrigUprRate)
                    iColor = Color.BLACK; //1;
                if (tMode == 2 || tMode == 6 || tMode == 10 || tMode == 13) // SST/SSTR mode then  chk with upr rate
                {
                    if (uprArray297[position] <= rateArray[tRate]) // Chk with Rate
                    {
                        iColor = Color.RED; //2;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if ((60000 / uprArray297[position]) <= refArray297[tRef]) //Chk with Ref
                    {
                        iColor = Color.RED; //2;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                break;

        }
        return iColor;
    }

    public static void logFileWrite(Activity activity, String msg, int[] fileData, String model) {
        System.out.println("<><><><>##### log call");
        String curDate, curTime;
        //get Current Date and Time
        curDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        curTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());

        //**** File Write ****

        String path = activity.getExternalFilesDir("") + "/SPL_LOG/" + model + "/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("<><><><>##### Dir Created " + path);
            //this, "Dir Created", Toast.LENGTH_SHORT).show();
        }

        String strIntr = msg + "\n" + curDate + " " + curTime;
        String fileName = iSrno + ".log";
        //InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            File file1 = new File(dir, fileName);
            if (!file1.exists())
                file1.createNewFile();

            outputStream = new FileOutputStream(file1, true);

            byte[] wrBytes = strIntr.getBytes();
            byte[] encWrBytes = new byte[strIntr.length()];
            for (int i = 0; i < (strIntr.length()); i++) {

                encWrBytes[i] = (byte) (0x99 ^ wrBytes[i]);
            }
            outputStream.write(0x2); // Start of String to write
            outputStream.write(strIntr.length());
            outputStream.write(encWrBytes);
            //outputStream.write(wrBytes);
            outputStream.write(0x20); //End Byte

            outputStream.write(0x3); // Start of Data Bytes to write
            //outputStream.write(pacerData[1] + 5); // total data
            if (msg.contains("Statistics")) {
                outputStream.write(fileData[1] + 2); // total data
                //byte[] paraDT = new byte[(int) pacerData[1] + 5];
                byte[] paraDT = new byte[fileData[1] + 2];
                //for (int i = 0; i < pacerData[1] + 5; i++) {
                for (int i = 0; i < fileData[1] + 2; i++) {
                    paraDT[i] = (byte) (0xFF & fileData[i]); //(byte) (0xFF & pacerData[i]);
                }
                //String strParaDT = new String(paraDT);
                outputStream.write(paraDT);
            } else {
                outputStream.write(fileData[1] + 5); // total data
                //byte[] paraDT = new byte[(int) pacerData[1] + 5];
                byte[] paraDT = new byte[fileData[1] + 5];
                //for (int i = 0; i < pacerData[1] + 5; i++) {
                for (int i = 0; i < fileData[1] + 5; i++) {
                    paraDT[i] = (byte) (0xFF & fileData[i]); //(byte) (0xFF & pacerData[i]);
                }
                //String strParaDT = new String(paraDT);
                outputStream.write(paraDT);
            }

            outputStream.write(0x20); //End Byte
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getColor747(Activity activity, int position, byte paraCode) {

        System.out.println("<><><>@@@@@");
        funCheckValidationList();
        List<String> keysValue = new ArrayList<>(mapValidationMsg.values());
        int iColor = Color.BLUE;


        switch (paraCode) {

            case 'M': //MODE
                if (position == CommonData.iMode)
                    iColor = Color.BLACK;

                break;
            case 'R': //Rate
                if (position == CommonData.iRate)
                    iColor = Color.BLACK;

                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 4 || CommonData.tMode == 6) //VVI VVT AAI AAT
                {
                    if ((CommonData.rateArray747[position] - CommonData.thystVAL * 4) < 30) {
                        System.out.println("<><><>!!!!!A  "+CommonData.thystVAL);
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic Rate - Hysteresis can not be less than 30 BPM") < 0)
                                mapValidationMsg.put(61, "Basic Rate - Hysteresis can not be less than 30 BPM");

                            //Toast.makeText(activity, "Basic Rate - Hysteresis can not be less than 30 BPM", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        mapValidationMsg.remove(61);
                    }
                }
                if (CommonData.tMode == 4 || CommonData.tMode == 6 || CommonData.tMode == 22 || CommonData.tMode == 24)//'AAI AAT AAIR AATR
                {
                    if (CommonData.rateArray747[CommonData.iARef] >= (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Basic rate should be more than Refractory Period") < 0)
                                mapValidationMsg.put(11, "Period of Basic rate should be more than Refractory Period");
                            //Toast.makeText(activity, "Period of Basic rate should be more than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(11);
                    }
                }
                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 19 || CommonData.tMode == 21)//'VVI VVT VVIR VVTR
                {
                    if (CommonData.rateArray747[CommonData.iRef] >= (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Basic rate should be more than Refractory Period") < 0)
                                mapValidationMsg.put(12, "Period of Basic rate should be more than Refractory Period");
                            //Toast.makeText(activity, "Period of Basic rate should be more than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(12);
                    }
                }
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tPvarp]) > (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("Period of Basic rate should be more than Refractory Period") < 0)
                                    mapValidationMsg.put(13, "Period of Basic rate should be more than Refractory Period");
                            } else {
                                if (keysValue.indexOf("S AVD + AV Hyst + PVARP should be less than period of Rate") < 0)
                                    mapValidationMsg.put(13, "S AVD + AV Hyst + PVARP should be less than period of Rate");
                            }

                            //Toast.makeText(activity, "S AVD + PVARP should be less than period of Rate", Toast.LENGTH_SHORT).show();


                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP should be less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(13);
                    }
                }
                if (CommonData.tMode == 2 || CommonData.tMode == 6 || CommonData.tMode == 8 || CommonData.tMode == 9) //'AAT VVT DDD VDD
                {
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tTrigUprRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic rate should be less than Upper Rate") < 0)
                                mapValidationMsg.put(14, "Basic rate should be less than Upper Rate");
                            //Toast.makeText(activity, "Basic rate should be less than Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(14);
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 21 || CommonData.tMode == 24) //'DDDR VDDR VVTR AATR
                {
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tTrigUprRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic rate should be less than Upper Rate") < 0)
                                mapValidationMsg.put(15, activity.getString(R.string.msg));
                            //Toast.makeText(activity, "Basic rate should be less than Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(15);
                    }
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tSensorRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic rate should be less than Target (Sensor)Upper Rate") < 0)
                                mapValidationMsg.put(16, "Basic rate should be less than Target (Sensor)Upper Rate");
                            //Toast.makeText(activity, "Basic rate should be less than Target (Sensor)Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(16);
                    }
                }
                //'DOOR DDIR DVIR VVIR VOOR AAIR AOOR
                if (CommonData.tMode == 16 || CommonData.tMode == 17 || CommonData.tMode == 18 || CommonData.tMode == 19
                        || CommonData.tMode == 20 || CommonData.tMode == 22 || CommonData.tMode == 23) {
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tSensorRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic rate should be less than Target (Sensor)Upper Rate") < 0)
                                mapValidationMsg.put(17, "Basic rate should be less than Target (Sensor)Upper Rate");
                            //Toast.makeText(activity, "Basic rate should be less than Target (Sensor)Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(17);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.rateArray747[CommonData.tPvarp] + 5) >
                            (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("PVARP + AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(18, "PVARP + AV Delay should be at least 5ms less than period of Rate");
                            } else {
                                if (keysValue.indexOf("PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(18, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            }


                            //Toast.makeText(activity, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(18);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("V REF + AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(19, "V REF + AV Delay should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("V REF + AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(19, "V REF + AV Delay should be at least 5ms less than period of Rate");
                            }
                            //mapValidationMsg.put(19, "V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            //Toast.makeText(activity, "V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(19);
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                            }
                            if (keysValue.indexOf("V REF + S AV Delay should be at least 5ms less than period of Rate") < 0)
                                mapValidationMsg.put(20, "V REF + S AV Delay should be at least 5ms less than period of Rate");
                                //Toast.makeText(activity, "V REF + S AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else
                                mapValidationMsg.put(20, "V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            //Toast.makeText(activity, "V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(20);
                    }
                }
                if (CommonData.tMode == 10 || CommonData.tMode == 16) // DOO(R)
                {
                    if ((((int) (60000 / CommonData.rateArray747[position])) - (CommonData.aviArray[CommonData.tPAVI])) <= 250) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Basic Rate - AV Interval should not be less than 250") < 0)
                                mapValidationMsg.put(21, "Period of Basic Rate - AV Interval should not be less than 250");
                            //Toast.makeText(activity, "Period of Basic Rate - AV Interval should not be less than 250", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(21);
                    }
                }
                //A Tachy
                //DDD VDD DDDR VDDR
                if (CommonData.tbATEna) {
                    if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 15) {
                        if (CommonData.uprArray297[CommonData.tATRate] <= CommonData.rateArray747[position] + 4) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                                if (keysValue.indexOf("AT Rate should be 4 bpm more than basic Rate") < 0)
                                    mapValidationMsg.put(22, "AT Rate should be 4 bpm more than basic Rate");
                                //Toast.makeText(activity, "AT Rate should be 4 bpm more than basic Rate", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        mapValidationMsg.remove(22);
                    }
                }
                break;
            case 'A': //A AMP
                if (position == CommonData.iAAmp)
                    iColor = Color.BLACK;
                if ((CommonData.amparray297[position] * CommonData.pwArray747[CommonData.tAPW] * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;
                        if (keysValue.indexOf("This Amplitude and PulseWidth value is not Valid") < 0)
                            mapValidationMsg.put(23, "This Amplitude and PulseWidth value is not Valid");
                        //Toast.makeText(activity, "This Amplitude and PulseWidth value is not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(23);
                }
                break;
            case 'V': //V AMP
                if (position == CommonData.iAmp)
                    iColor = Color.BLACK;
                if ((CommonData.amparray297[position] * CommonData.pwArray747[CommonData.tPW] * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;
                        if (keysValue.indexOf("This Amplitude and PulseWidth value is not Valid") < 0)
                            mapValidationMsg.put(24, "This Amplitude and PulseWidth value is not Valid");
                        //Toast.makeText(activity, "This Amplitude and PulseWidth value is not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(24);
                }
                break;
            case 'P': // A PW
                if (position == CommonData.iAPW)
                    iColor = Color.BLACK;
                if ((CommonData.amparray297[CommonData.tAAmp] * CommonData.pwArray747[position] * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;
                        if (keysValue.indexOf("This Amplitude and PulseWidth value is not Valid") < 0)
                            mapValidationMsg.put(25, "This Amplitude and PulseWidth value is not Valid");
                        //Toast.makeText(activity, "This Amplitude and PulseWidth value is not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(25);
                }
                break;
            case 'W': // V PW
                if (position == CommonData.iPW)
                    iColor = Color.BLACK;
                System.out.println("<><><>$$$$$ " + position);

                if ((CommonData.amparray297[CommonData.tAmp] * CommonData.pwArray747[position] * 10) >= 76) {
                    System.out.println("<><><>$$$$$@@$$$$$%% " + CommonData.amparray297[CommonData.tAmp]);
                    System.out.println("<><><>$$$$$@@$$$$$%% " + CommonData.pwArray747[position]);
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;
                        if (keysValue.indexOf("This Amplitude and PulseWidth value is not Valid") < 0)
                            mapValidationMsg.put(26, "This Amplitude and PulseWidth value is not Valid");
                        //Toast.makeText(activity, "This Amplitude and PulseWidth value is not Valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(26);
                }
                break;
            case 'E': // A REF
                if (position == CommonData.iARef)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 4 || CommonData.tMode == 6 || CommonData.tMode == 22 || CommonData.tMode == 24)//'AAI AAT AAIR AATR
                {
                    if (CommonData.refArray747[position] >= (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Basic rate should be more than Refractory Period") < 0)
                                mapValidationMsg.put(27, "Period of Basic rate should be more than Refractory Period");
                            //Toast.makeText(activity, "Period of Basic rate should be more than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(27);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) {
                    if (CommonData.refArray747[position] > CommonData.refArray747[CommonData.tPvarp]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("A Ref should be less than PVARP") < 0)
                                mapValidationMsg.put(29, "A Ref should be less than PVARP");
                        }

                    } else {
                        mapValidationMsg.remove(29);
                    }
                }
                //AAT(R)
                if (CommonData.tMode == 6 || CommonData.tMode == 24) {
                    if (CommonData.refArray747[position] >= (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            //if (keysValue.indexOf("A Ref period should be less than period of Upper Rate") < 0)
                            // mapValidationMsg.put(29, "A Ref period should be less than period of Upper Rate");
                            //Toast.makeText(activity, "A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(29);
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[position]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + A Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(30, "S AVD + A Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + A Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(30, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate");
                            }
                            //mapValidationMsg.put(30, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate");
                            //Toast.makeText(activity, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(30);
                    }
                }
                //Check TUR with REFs
                //'AAIR AATR  'DDDR VDDR DDIR
                if (CommonData.tMode == 22 || CommonData.tMode == 24 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[CommonData.tSensorRate]) <= CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("A Ref period should be less than period of target Upper Rate") < 0)
                                mapValidationMsg.put(31, "A Ref period should be less than period of target Upper Rate");
                            //Toast.makeText(activity, "A Ref period should be less than period of target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(31);
                    }

                }
                break;
            case 'F': // V REF
                if (position == CommonData.iRef)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 19 || CommonData.tMode == 21)//'VVI VVT VVIR VVTR
                {
                    if (CommonData.refArray747[position] >= (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Basic rate should be more than Refractory Period") < 0)
                                mapValidationMsg.put(32, "Period of Basic rate should be more than Refractory Period");
                            //Toast.makeText(activity, "Period of Basic rate should be more than Refractory Period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(32);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("Period of Basic rate should be more than Refractory Period") < 0)
                                    mapValidationMsg.put(33, "V REF + AV Delay should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(33, "V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(33);
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[position] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("V REF + S AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(34, "V REF + S AV Delay should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + S AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(34, "V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(34);
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.rateArray747[position] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                }
                //'VVT VVTR DDD VDD DDDR VDDR
                if (CommonData.tMode == 2 || CommonData.tMode == 21 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position]) > (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(37, "V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("AV Hyst + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(37, "AV Hyst + V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "AV Hyst + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(37);
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    //Check with V Ref
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[position]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(38, "S AVD + V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(38, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate");
                            }

                            // Toast.makeText(activity, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(38);
                    }
                }
                //Check TUR with REFs
                //'DVIR VVIR VVTR 'DDDR VDDR DDIR
                if (CommonData.tMode == 18 || CommonData.tMode == 19 || CommonData.tMode == 21 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[CommonData.tSensorRate]) <= CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("V Ref period should be less than period of target Upper Rate") < 0)
                                mapValidationMsg.put(39, "V Ref period should be less than period of target Upper Rate");
                            //Toast.makeText(activity, "V Ref period should be less than period of target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(39);
                    }

                }
                break;
            case 'O': //PVARP
                if (position == CommonData.iPvarp)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[position]) > (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + PVARP should be less than period of Rate") < 0)
                                    mapValidationMsg.put(40, "S AVD + PVARP should be less than period of Rate");
                            }

                            //Toast.makeText(activity, "S AVD + PVARP should be less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + PVARP should be less than period of Rate") < 0)
                                    mapValidationMsg.put(40, "S AVD + AV Hyst + PVARP should be less than period of Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP should be less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(40);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("PVARP + AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(41, "PVARP + AV Delay should be at least 5ms less than period of Rate");
                            }

                            // Toast.makeText(activity, "PVARP + AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(41, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            }

                            // Toast.makeText(activity, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(41);
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(42, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(42);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) {
                    if (CommonData.refArray747[CommonData.tARef] > CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            //if (keysValue.indexOf("A Ref should be less than PVARP") < 0)
                            // mapValidationMsg.put(29, "A Ref should be less than PVARP");
                            //Toast.makeText(activity, "A Ref should be less than PVARP", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        mapValidationMsg.remove(29);
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    //Check with PVARP
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[position]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + PVARP period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(44, "S AVD + PVARP period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + PVARP period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(71, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(44);
                        mapValidationMsg.remove(71);
                    }
                }
                //Check TUR with REFs
                // 'DDDR VDDR DDIR
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[CommonData.tSensorRate]) <= CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("PVARP should be less than period of target Upper Rate") < 0)
                                mapValidationMsg.put(45, "PVARP should be less than period of target Upper Rate");
                            //Toast.makeText(activity, "PVARP should be less than period of target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(45);
                    }

                }
                break;
            case 'B': //V Blanking
                if (position == CommonData.iBlnk)
                    iColor = Color.BLACK;
                //'if DDD(R)DDI(R)DVI(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) {
                    //'Chk blnk with AVD
                    if (CommonData.aviArray[CommonData.tPAVI] < (CommonData.blnkArray[position] + 20)) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV/SAV Delay should be Greater than Blanking period") < 0)
                                mapValidationMsg.put(46, "AV/SAV Delay should be Greater than Blanking period");
                            //Toast.makeText(activity, "AV/SAV Delay should be Greater than Blanking period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(46);
                    }
                }
                break;
            case 'S'://A Sens
                if (position == CommonData.iASen)
                    iColor = Color.BLACK;

                break;
            case 'N'://V Sens
                if (position == CommonData.iSen)
                    iColor = Color.BLACK;

                break;
            case 'I': //P AV Interval
                if (position == CommonData.iPAVI)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tPvarp] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("PVARP + AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(47, "PVARP + AV Delay should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "PVARP + AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(47, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(47);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("V REF + AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(48, "V REF + AV Delay should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(48, "V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(48);
                    }
                }
                if (CommonData.tMode == 10 || CommonData.tMode == 16) // DOO(R)
                {
                    if ((((int) (60000 / CommonData.rateArray747[CommonData.tRate])) - (CommonData.aviArray[position])) <= 250) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Period of Basic Rate - AV Interval should not be less than 250") < 0)
                                mapValidationMsg.put(49, "Period of Basic Rate - AV Interval should not be less than 250");
                            //Toast.makeText(activity, "Period of Basic Rate - AV Interval should not be less than 250", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(49);
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.aviArray[CommonData.tPvarp] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(50, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(50);
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                    if (CommonData.tbARC) {
                        if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position]) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                                if (keysValue.indexOf("AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate") < 0)
                                    mapValidationMsg.put(52, "AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate");
                                //Toast.makeText(activity, "AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mapValidationMsg.remove(52);
                        }
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                }
                //'if DDD(R)DDI(R)DVI(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) {
                    //'Chk blnk with AVD
                    if (CommonData.aviArray[position] < (CommonData.blnkArray[CommonData.tBlnk] + 20)) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV/SAV Delay should be Greater than Blanking period") < 0)
                                mapValidationMsg.put(54, "AV/SAV Delay should be Greater than Blanking period");
                            //Toast.makeText(activity, "AV/SAV Delay should be Greater than Blanking period", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(54);
                    }
                }
                //DOOR
                if (CommonData.tMode == 16 && (((int) (60000 / (CommonData.uprArray297[CommonData.tSensorRate])) - (CommonData.aviArray[position])) <= 250)) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                        if (keysValue.indexOf("Period of Target Upper rate - AV Delay should not be less than 250ms") < 0)
                            mapValidationMsg.put(55, "Period of Target Upper rate - AV Delay should not be less than 250ms");
                        //Toast.makeText(activity, "Period of Target Upper rate - AV Delay should not be less than 250ms", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(55);
                }
                break;
            case 'J': //S AVI
                if (position == CommonData.iSAVI)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tPvarp]) > (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + PVARP should be less than period of Rate") < 0)
                                    mapValidationMsg.put(56, "S AVD + PVARP should be less than period of Rate");
                            }

                            //Toast.makeText(activity, "S AVD + PVARP should be less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + PVARP should be less than period of Rate") < 0)
                                    mapValidationMsg.put(56, "S AVD + AV Hyst + PVARP should be less than period of Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP should be less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(56);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("V REF + S AV Delay should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(57, "V REF + S AV Delay should be at least 5ms less than period of Rate");
                            }

                            // Toast.makeText(activity, "V REF + S AV Delay should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                    mapValidationMsg.put(57, "V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            }

                            //Toast.makeText(activity, "V REF + S AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(57);
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tARef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + A Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(58, "S AVD + A Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + A Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(58, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(58);
                    }
                    //Check with V Ref
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tRef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(59, "S AVD + V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(59, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(59);
                    }
                    //Check with PVARP
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tPvarp]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + PVARP period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(60, "S AVD + PVARP period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + PVARP period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(71, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(60);
                        mapValidationMsg.remove(71);
                    }
                }
                break;
            case 'H': // Hysteresis
                if (position == CommonData.ihystVAL)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 4 || CommonData.tMode == 6) //VVI VVT AAI AAT
                {
                    if ((CommonData.rateArray747[CommonData.tRate] - position * 4) < 30) {
                        System.out.println("<><><>!!!!!B  "+position);
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic Rate - Hysteresis can not be less than 30 BPM") < 0)
                                mapValidationMsg.put(61, "Basic Rate - Hysteresis can not be less than 30 BPM");
                            //Toast.makeText(activity, "Basic Rate - Hysteresis can not be less than 30 BPM", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        mapValidationMsg.remove(61);
                    }
                }
                break;
            case 'Y': //AV Hyst
                if (position == CommonData.iAVHyst)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tPvarp]) > (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("S AVD + AV Hyst + PVARP should be less than period of Rate") < 0)
                                mapValidationMsg.put(62, "S AVD + AV Hyst + PVARP should be less than period of Rate");
                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP should be less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(62);
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[CommonData.tPvarp] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate") < 0)
                                mapValidationMsg.put(63, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate");
                            //Toast.makeText(activity, "PVARP + AV Delay + AV Hyst should be at least 5ms less than period of Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(63);
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tPvarp] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(64, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(64);
                    }
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            // Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                    if (CommonData.tbARC) {
                        if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + 255) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                                if (keysValue.indexOf("AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate") < 0)
                                    mapValidationMsg.put(66, "AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate");
                                // Toast.makeText(activity, "AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mapValidationMsg.remove(66);
                        }
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                }
                //'VVT VVTR DDD VDD DDDR VDDR
                if (CommonData.tMode == 2 || CommonData.tMode == 21 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[tPAVI] + CommonData.refArray747[CommonData.tRef]) > (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst > 0)
                                //Toast.makeText(activity, "V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                                //else
                                if (keysValue.indexOf("AV Hyst + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(68, "AV Hyst + V Ref period should be less than period of Upper Rate");
                            //Toast.makeText(activity, "AV Hyst + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(68);
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tARef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            // if (CommonData.tAVHyst==0)
                            //   Toast.makeText(activity, "S AVD + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            //else
                            if (keysValue.indexOf("S AVD + AV Hyst + A Ref period should be less than period of Upper Rate") < 0)
                                mapValidationMsg.put(69, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate");
                            //Toast.makeText(activity, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(69);
                    }
                    //Check with V Ref
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tRef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            // if (CommonData.tAVHyst==0)
                            //   Toast.makeText(activity, "S AVD + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            // else
                            if (keysValue.indexOf("S AVD + AV Hyst + V Ref period should be less than period of Upper Rate") < 0)
                                mapValidationMsg.put(70, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate");
                            //Toast.makeText(activity, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(70);
                    }
                    //Check with PVARP
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tPvarp]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            // if (CommonData.tAVHyst==0)
                            //   Toast.makeText(activity, "S AVD + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            //else
                            if (keysValue.indexOf("S AVD + AV Hyst + PVARP period should be less than period of Upper Rate") < 0)
                                mapValidationMsg.put(71, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate");
                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(71);
                    }
                }
                break;
            case 'U': // Upper Rate/ Trigger Rate
                if (position == CommonData.iTrigUprRate)
                    iColor = Color.BLACK;
                //'AAT VVT DDD VDD //'DDDR VDDR VVTR AATR
                if (CommonData.tMode == 2 || CommonData.tMode == 6 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 21 || CommonData.tMode == 24) {
                    if (CommonData.rateArray747[CommonData.tRate] >= CommonData.uprArray297[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic rate should be less than Upper Rate") < 0)
                                mapValidationMsg.put(72, "Basic rate should be less than Upper Rate");
                            //Toast.makeText(activity, "Basic rate should be less than Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(72);
                    }
                }
                //AAT(R)
                if (CommonData.tMode == 6 || CommonData.tMode == 24) {
                    if (CommonData.refArray747[CommonData.tARef] >= (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("A Ref period should be less than period of Upper Rate") < 0)
                                mapValidationMsg.put(73, "A Ref period should be less than period of Upper Rate");
                            //Toast.makeText(activity, "A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(73);
                    }
                }
                //'VVT VVTR DDD VDD DDDR VDDR
                if (CommonData.tMode == 2 || CommonData.tMode == 21 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst]+ CommonData.aviArray[tPAVI] + CommonData.refArray747[CommonData.tRef]) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(74, "V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("AV Hyst + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(74, "AV Hyst + V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "AV Hyst + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(74);
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tARef]) >=
                            (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + A Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(75, "S AVD + A Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + A Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(75, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + A Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(75);
                    }
                    //Check with V Ref
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tRef]) >=
                            (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(76, "S AVD + V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + V Ref period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(76, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + V Ref period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(76);
                    }
                    //Check with PVARP
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tPvarp]) >=
                            (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (CommonData.tAVHyst == 0) {
                                if (keysValue.indexOf("S AVD + PVARP period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(77, "S AVD + PVARP period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                            else {
                                if (keysValue.indexOf("S AVD + AV Hyst + PVARP period should be less than period of Upper Rate") < 0)
                                    mapValidationMsg.put(71, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate");
                            }

                            //Toast.makeText(activity, "S AVD + AV Hyst + PVARP period should be less than period of Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(77);
                        mapValidationMsg.remove(71);
                    }

                    //A Tachy
                    if (CommonData.tbATEna) {
                        if (CommonData.uprArray297[CommonData.tATRate] <= CommonData.uprArray297[position] + 4) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                                if (keysValue.indexOf("AT Rate should be 4 bpm more than Upper Rate") < 0)
                                    mapValidationMsg.put(78, "AT Rate should be 4 bpm more than Upper Rate");
                                //Toast.makeText(activity, "AT Rate should be 4 bpm more than Upper Rate", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mapValidationMsg.remove(78);
                        }
                    }
                }

                break;
            case 'C': //Sensor Rate
                if (position == CommonData.iSensorRate)
                    iColor = Color.BLACK;
                //'DOOR DDIR DVIR VVIR VOOR AAIR AOOR  //'DDDR VDDR VVTR AATR
                if (CommonData.tMode == 16 || CommonData.tMode == 17 || CommonData.tMode == 18 || CommonData.tMode == 19
                        || CommonData.tMode == 20 || CommonData.tMode == 22 || CommonData.tMode == 23 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 21 || CommonData.tMode == 24) {
                    if (CommonData.rateArray747[CommonData.tRate] >= CommonData.uprArray297[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("Basic rate should be less than Target (Sensor)Upper Rate") < 0)
                                mapValidationMsg.put(79, "Basic rate should be less than Target (Sensor)Upper Rate");
                            //Toast.makeText(activity, "Basic rate should be less than Target (Sensor)Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(79);
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tPvarp] + 5) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(80, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+PVARP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(80);
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                    if (CommonData.tbARC) {
                        if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + 255) > (int) (60000 / CommonData.uprArray297[position])) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                                if (keysValue.indexOf("AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate") < 0)
                                    mapValidationMsg.put(82, "AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate");
                                //Toast.makeText(activity, "AV Hyst+AVD+250ms (ARC) should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mapValidationMsg.remove(82);
                        }
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate") < 0)
                                mapValidationMsg.put(35, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate");
                            //Toast.makeText(activity, "AV Hyst+AVD+VRP should be at least 5ms less than period of Target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(35);
                    }
                }
                //DOOR
                if (CommonData.tMode == 16 && (((int) (60000 / (CommonData.uprArray297[position])) - (CommonData.aviArray[CommonData.tPAVI])) <= 250)) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                        if (keysValue.indexOf("Period of Target Upper rate - AV Delay should not be less than 250ms") < 0)
                            mapValidationMsg.put(84, "Period of Target Upper rate - AV Delay should not be less than 250ms");
                        //Toast.makeText(activity, "Period of Target Upper rate - AV Delay should not be less than 250ms", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mapValidationMsg.remove(84);
                }
                //Check TUR with REFs
                //'DVIR VVIR VVTR 'DDDR VDDR DDIR
                if (CommonData.tMode == 18 || CommonData.tMode == 19 || CommonData.tMode == 21 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[position]) <= CommonData.rateArray747[CommonData.tRef]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("V Ref period should be less than period of target Upper Rate") < 0)
                                mapValidationMsg.put(85, "V Ref period should be less than period of target Upper Rate");
                            //Toast.makeText(activity, "V Ref period should be less than period of target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(85);
                    }

                }
                //'AAIR AATR  'DDDR VDDR DDIR
                if (CommonData.tMode == 22 || CommonData.tMode == 24 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[position]) <= CommonData.rateArray747[CommonData.tARef]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("A Ref period should be less than period of target Upper Rate") < 0)
                                mapValidationMsg.put(86, "A Ref period should be less than period of target Upper Rate");
                            // Toast.makeText(activity, "A Ref period should be less than period of target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(86);
                    }

                }
                // 'DDDR VDDR DDIR
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[position]) <= CommonData.rateArray747[CommonData.tPvarp]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("PVARP should be less than period of target Upper Rate") < 0)
                                mapValidationMsg.put(87, "PVARP should be less than period of target Upper Rate");
                            //Toast.makeText(activity, "PVARP should be less than period of target Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(87);
                    }

                }
                //A Tachy
                // DDDR VDDR
                if (CommonData.tbATEna) {
                    if (CommonData.tMode == 14 || CommonData.tMode == 15) {
                        if (CommonData.uprArray297[CommonData.tATRate] <= CommonData.uprArray297[position] + 4) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                                if (keysValue.indexOf("AT Rate should be 4 bpm more than Sensor Rate") < 0)
                                    mapValidationMsg.put(88, "AT Rate should be 4 bpm more than Sensor Rate");
                                //Toast.makeText(activity, "AT Rate should be 4 bpm more than Sensor Rate", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mapValidationMsg.remove(88);
                        }
                    }
                }
                break;
            case 'D': //A Tachy Rate

                if (position == CommonData.iATRate)
                    iColor = Color.BLACK;
                //If fATena <> 0 Then
                //DDD VDD DDDR VDDR
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if (CommonData.uprArray297[position] <= CommonData.rateArray747[CommonData.tRate] + 4) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AT Rate should be 4 bpm more than basic Rate") < 0)
                                mapValidationMsg.put(89, "AT Rate should be 4 bpm more than basic Rate");
                            //Toast.makeText(activity, "AT Rate should be 4 bpm more than basic Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(89);
                    }
                    if (CommonData.uprArray297[position] <= CommonData.uprArray297[CommonData.tTrigUprRate] + 4) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AT Rate should be 4 bpm more than Upper Rate") < 0)
                                mapValidationMsg.put(90, "AT Rate should be 4 bpm more than Upper Rate");
                            //Toast.makeText(activity, "AT Rate should be 4 bpm more than Upper Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(90);
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if (CommonData.uprArray297[position] <= CommonData.uprArray297[CommonData.tSensorRate] + 4) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                            if (keysValue.indexOf("AT Rate should be 4 bpm more than Sensor Rate") < 0)
                                mapValidationMsg.put(91, "AT Rate should be 4 bpm more than Sensor Rate");
                            //Toast.makeText(activity, "AT Rate should be 4 bpm more than Sensor Rate", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mapValidationMsg.remove(91);
                    }
                }

                break;

        }
        return iColor;
    }

    public static int getColor747_B(Activity activity, int position, byte paraCode) {

        System.out.println("<><><>@@@@@");
        funCheckValidationList();
        int iColor = Color.BLUE;


        switch (paraCode) {

            case 'M': //MODE
                if (position == CommonData.iMode)
                    iColor = Color.BLACK;

                break;
            case 'R': //Rate
                if (position == CommonData.iRate)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 4 || CommonData.tMode == 6) //VVI VVT AAI AAT
                {
                    if ((CommonData.rateArray747[position] - CommonData.thystVAL * 4) < 30) {
                        System.out.println("<><><>!!!!!A  "+CommonData.thystVAL);
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }

                    }
                }
                if (CommonData.tMode == 4 || CommonData.tMode == 6 || CommonData.tMode == 22 || CommonData.tMode == 24)//'AAI AAT AAIR AATR
                {
                    if (CommonData.rateArray747[CommonData.iARef] >= (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 19 || CommonData.tMode == 21)//'VVI VVT VVIR VVTR
                {
                    if (CommonData.rateArray747[CommonData.iRef] >= (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tPvarp]) > (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                if (CommonData.tMode == 2 || CommonData.tMode == 6 || CommonData.tMode == 8 || CommonData.tMode == 9) //'AAT VVT DDD VDD
                {
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tTrigUprRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 21 || CommonData.tMode == 24) //'DDDR VDDR VVTR AATR
                {
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tTrigUprRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tSensorRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                //'DOOR DDIR DVIR VVIR VOOR AAIR AOOR
                if (CommonData.tMode == 16 || CommonData.tMode == 17 || CommonData.tMode == 18 || CommonData.tMode == 19
                        || CommonData.tMode == 20 || CommonData.tMode == 22 || CommonData.tMode == 23) {
                    if (CommonData.rateArray747[position] >= CommonData.uprArray297[CommonData.tSensorRate]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.rateArray747[CommonData.tPvarp] + 5) >
                            (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 10 || CommonData.tMode == 16) // DOO(R)
                {
                    if ((((int) (60000 / CommonData.rateArray747[position])) - (CommonData.aviArray[CommonData.tPAVI])) <= 250) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                //A Tachy
                //DDD VDD DDDR VDDR
                if (CommonData.tbATEna) {
                    if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 15) {
                        if (CommonData.uprArray297[CommonData.tATRate] <= CommonData.rateArray747[position] + 4) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;

                            }
                        }
                    }
                }
                break;
            case 'A': //A AMP
                if (position == CommonData.iAAmp)
                    iColor = Color.BLACK;
                if ((CommonData.amparray297[position] * CommonData.pwArray747[CommonData.tAPW] * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;

                    }
                }
                break;
            case 'V': //V AMP
                if (position == CommonData.iAmp)
                    iColor = Color.BLACK;
                if ((CommonData.amparray297[position] * CommonData.pwArray747[CommonData.tPW] * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;

                    }
                }
                break;
            case 'P': // A PW
                if (position == CommonData.iAPW)
                    iColor = Color.BLACK;
                if ((CommonData.amparray297[CommonData.tAAmp] * CommonData.pwArray747[position] * 10) >= 76) {
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;

                    }
                }
                break;
            case 'W': // V PW
                if (position == CommonData.iPW)
                    iColor = Color.BLACK;
                System.out.println("<><><>$$$$$ " + position);

                if ((CommonData.amparray297[CommonData.tAmp] * CommonData.pwArray747[position] * 10) >= 76) {
                    System.out.println("<><><>$$$$$@@$$$$$%% " + CommonData.amparray297[CommonData.tAmp]);
                    System.out.println("<><><>$$$$$@@$$$$$%% " + CommonData.pwArray747[position]);
                    iColor = Color.RED;
                    if (bSpinClick) {

                        bParaOK = false;

                    }
                }
                break;
            case 'E': // A REF
                if (position == CommonData.iARef)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 4 || CommonData.tMode == 6 || CommonData.tMode == 22 || CommonData.tMode == 24)//'AAI AAT AAIR AATR
                {
                    if (CommonData.refArray747[position] >= (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) {
                    if (CommonData.refArray747[position] > CommonData.refArray747[CommonData.tPvarp]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }

                    }
                }
                //AAT(R)
                if (CommonData.tMode == 6 || CommonData.tMode == 24) {
                    if (CommonData.refArray747[position] >= (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;

                        }
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[position]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //Check TUR with REFs
                //'AAIR AATR  'DDDR VDDR DDIR
                if (CommonData.tMode == 22 || CommonData.tMode == 24 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[CommonData.tSensorRate]) <= CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                break;
            case 'F': // V REF
                if (position == CommonData.iRef)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 19 || CommonData.tMode == 21)//'VVI VVT VVIR VVTR
                {
                    if (CommonData.refArray747[position] >= (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[position] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.rateArray747[position] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //'VVT VVTR DDD VDD DDDR VDDR
                if (CommonData.tMode == 2 || CommonData.tMode == 21 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position]) > (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    //Check with V Ref
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[position]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //Check TUR with REFs
                //'DVIR VVIR VVTR 'DDDR VDDR DDIR
                if (CommonData.tMode == 18 || CommonData.tMode == 19 || CommonData.tMode == 21 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[CommonData.tSensorRate]) <= CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                break;
            case 'O': //PVARP
                if (position == CommonData.iPvarp)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[position]) > (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[position] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) {
                    if (CommonData.refArray747[CommonData.tARef] > CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }

                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    //Check with PVARP
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[position]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //Check TUR with REFs
                // 'DDDR VDDR DDIR
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[CommonData.tSensorRate]) <= CommonData.refArray747[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                break;
            case 'B': //V Blanking
                if (position == CommonData.iBlnk)
                    iColor = Color.BLACK;
                //'if DDD(R)DDI(R)DVI(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) {
                    //'Chk blnk with AVD
                    if (CommonData.aviArray[CommonData.tPAVI] < (CommonData.blnkArray[position] + 20)) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                break;
            case 'S'://A Sens
                if (position == CommonData.iASen)
                    iColor = Color.BLACK;

                break;
            case 'N'://V Sens
                if (position == CommonData.iSen)
                    iColor = Color.BLACK;

                break;
            case 'I': //P AV Interval
                if (position == CommonData.iPAVI)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tPvarp] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 10 || CommonData.tMode == 16) // DOO(R)
                {
                    if ((((int) (60000 / CommonData.rateArray747[CommonData.tRate])) - (CommonData.aviArray[position])) <= 250) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.aviArray[CommonData.tPvarp] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if (CommonData.tbARC) {
                        if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position]) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                            }
                        }
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //'if DDD(R)DDI(R)DVI(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) {
                    //'Chk blnk with AVD
                    if (CommonData.aviArray[position] < (CommonData.blnkArray[CommonData.tBlnk] + 20)) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //DOOR
                if (CommonData.tMode == 16 && (((int) (60000 / (CommonData.uprArray297[CommonData.tSensorRate])) - (CommonData.aviArray[position])) <= 250)) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                    }
                }
                break;
            case 'J': //S AVI
                if (position == CommonData.iSAVI)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tPvarp]) > (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 13 || CommonData.tMode == 14 || CommonData.tMode == 17 || CommonData.tMode == 18) // DDD(R) DDI(R) DVI(R)
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tRef] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tARef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    //Check with V Ref
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tRef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    //Check with PVARP
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[position] + CommonData.refArray747[CommonData.tPvarp]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                break;
            case 'H': // Hysteresis
                if (position == CommonData.ihystVAL)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 4 || CommonData.tMode == 6) //VVI VVT AAI AAT
                {
                    if ((CommonData.rateArray747[CommonData.tRate] - position * 4) < 30) {
                        System.out.println("<><><>!!!!!B  "+CommonData.thystVAL);
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                break;
            case 'Y': //AV Hyst
                if (position == CommonData.iAVHyst)
                    iColor = Color.BLACK;
                if (CommonData.tMode == 9 || CommonData.tMode == 15) // 'VDD(R)
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tPvarp]) > (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 8 || CommonData.tMode == 11 || CommonData.tMode == 14 || CommonData.tMode == 17) // DDD(R) DDI(R)
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.refArray747[CommonData.tPvarp] + 5) >
                            (int) (60000 / CommonData.rateArray747[CommonData.tRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tPvarp] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if (CommonData.tbARC) {
                        if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + 255) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                            }
                        }
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[CommonData.tSensorRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //'VVT VVTR DDD VDD DDDR VDDR
                if (CommonData.tMode == 2 || CommonData.tMode == 21 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[position]+ CommonData.aviArray[tPAVI] + CommonData.refArray747[CommonData.tRef]) > (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tARef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    //Check with V Ref
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tRef]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    //Check with PVARP
                    if ((CommonData.avhArray[position] + CommonData.aviArray[CommonData.tSAVI] + CommonData.refArray747[CommonData.tPvarp]) >=
                            (int) (60000 / CommonData.uprArray297[CommonData.tTrigUprRate])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                break;
            case 'U': // Upper Rate/ Trigger Rate
                if (position == CommonData.iTrigUprRate)
                    iColor = Color.BLACK;
                //'AAT VVT DDD VDD //'DDDR VDDR VVTR AATR
                if (CommonData.tMode == 2 || CommonData.tMode == 6 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 21 || CommonData.tMode == 24) {
                    if (CommonData.rateArray747[CommonData.tRate] >= CommonData.uprArray297[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //AAT(R)
                if (CommonData.tMode == 6 || CommonData.tMode == 24) {
                    if (CommonData.refArray747[CommonData.tARef] >= (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    } else {
                        mapValidationMsg.remove(73);
                    }
                }
                //'VVT VVTR DDD VDD DDDR VDDR
                if (CommonData.tMode == 2 || CommonData.tMode == 21 || CommonData.tMode == 8 || CommonData.tMode == 9 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[tPAVI] + CommonData.refArray747[CommonData.tRef]) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //'DDD(R) VDD(R)
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tARef]) >=
                            (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    //Check with V Ref
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tRef]) >=
                            (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    //Check with PVARP
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tSAVI] + CommonData.rateArray747[CommonData.tPvarp]) >=
                            (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                    //A Tachy
                    if (CommonData.tbATEna) {
                        if (CommonData.uprArray297[CommonData.tATRate] <= CommonData.uprArray297[position] + 4) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                            }
                        }
                    }
                }

                break;
            case 'C': //Sensor Rate
                if (position == CommonData.iSensorRate)
                    iColor = Color.BLACK;
                //'DOOR DDIR DVIR VVIR VOOR AAIR AOOR  //'DDDR VDDR VVTR AATR
                if (CommonData.tMode == 16 || CommonData.tMode == 17 || CommonData.tMode == 18 || CommonData.tMode == 19
                        || CommonData.tMode == 20 || CommonData.tMode == 22 || CommonData.tMode == 23 ||
                        CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 21 || CommonData.tMode == 24) {
                    if (CommonData.rateArray747[CommonData.tRate] >= CommonData.uprArray297[position]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17)//'DDDR VDDR DDIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tPvarp] + 5) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if (CommonData.tbARC) {
                        if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + 255) > (int) (60000 / CommonData.uprArray297[position])) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                            }
                        }
                    }
                }
                if (CommonData.tMode == 18) //DVIR
                {
                    if ((CommonData.avhArray[CommonData.tAVHyst] + CommonData.aviArray[CommonData.tPAVI] + CommonData.aviArray[CommonData.tRef] + 5) > (int) (60000 / CommonData.uprArray297[position])) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                //DOOR
                if (CommonData.tMode == 16 && (((int) (60000 / (CommonData.uprArray297[position])) - (CommonData.aviArray[CommonData.tPAVI])) <= 250)) {
                    iColor = Color.RED;
                    if (bSpinClick) {
                        bParaOK = false;
                    }
                }
                //Check TUR with REFs
                //'DVIR VVIR VVTR 'DDDR VDDR DDIR
                if (CommonData.tMode == 18 || CommonData.tMode == 19 || CommonData.tMode == 21 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[position]) <= CommonData.rateArray747[CommonData.tRef]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                //'AAIR AATR  'DDDR VDDR DDIR
                if (CommonData.tMode == 22 || CommonData.tMode == 24 || CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[position]) <= CommonData.rateArray747[CommonData.tARef]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                // 'DDDR VDDR DDIR
                if (CommonData.tMode == 14 || CommonData.tMode == 15 || CommonData.tMode == 17) {
                    if ((int) (60000 / CommonData.uprArray297[position]) <= CommonData.rateArray747[CommonData.tPvarp]) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }

                }
                //A Tachy
                // DDDR VDDR
                if (CommonData.tbATEna) {
                    if (CommonData.tMode == 14 || CommonData.tMode == 15) {
                        if (CommonData.uprArray297[CommonData.tATRate] <= CommonData.uprArray297[position] + 4) {
                            iColor = Color.RED;
                            if (bSpinClick) {
                                bParaOK = false;
                            }
                        }
                    }
                }
                break;
            case 'D': //A Tachy Rate

                if (position == CommonData.iATRate)
                    iColor = Color.BLACK;
                //If fATena <> 0 Then
                //DDD VDD DDDR VDDR
                if (CommonData.tMode == 8 || CommonData.tMode == 9 || CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if (CommonData.uprArray297[position] <= CommonData.rateArray747[CommonData.tRate] + 4) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                    if (CommonData.uprArray297[position] <= CommonData.uprArray297[CommonData.tTrigUprRate] + 4) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }
                if (CommonData.tMode == 14 || CommonData.tMode == 15) {
                    if (CommonData.uprArray297[position] <= CommonData.uprArray297[CommonData.tSensorRate] + 4) {
                        iColor = Color.RED;
                        if (bSpinClick) {
                            bParaOK = false;
                        }
                    }
                }

                break;

        }
        return iColor;
    }

    public static void mFunAlert(Activity activity, String mStrTitle, String mStrMsg) {
        AlertDialog.Builder alertdlg = new AlertDialog.Builder(Objects.requireNonNull(activity));

        alertdlg.setTitle(mStrTitle);
        alertdlg.setMessage(mStrMsg);
        alertdlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertdlg.create();
        alertDialog.show();
    }

    public static void mFunAlertAuto(Activity activity, String mStrTitle, String mStrMsg) {
        AlertDialog.Builder alertdlg = new AlertDialog.Builder(Objects.requireNonNull(activity));

        alertdlg.setTitle(mStrTitle);
        alertdlg.setMessage(mStrMsg);

        AlertDialog alertDialog = alertdlg.create();
        alertDialog.show();
        // Hide after some seconds
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
        };

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 1000);
    }

    public static void killProcessesAround(Activity activity) throws PackageManager.NameNotFoundException {
        ActivityManager am = (ActivityManager)activity.getSystemService(Context.ACTIVITY_SERVICE);
        String myProcessPrefix = activity.getApplicationInfo().processName;
        String myProcessName = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 0).processName;
        for (ActivityManager.RunningAppProcessInfo proc : am.getRunningAppProcesses()) {
            if (proc.processName.startsWith(myProcessPrefix) && !proc.processName.equals(myProcessName)) {
                android.os.Process.killProcess(proc.pid);
            }
        }
    }


    public static void mFunAlertAutoLong(Activity activity, String mStrTitle, String mStrMsg) {
        AlertDialog.Builder alertdlg = new AlertDialog.Builder(Objects.requireNonNull(activity));

        alertdlg.setTitle(mStrTitle);
        alertdlg.setMessage(mStrMsg);

        AlertDialog alertDialog = alertdlg.create();
        alertDialog.show();
        // Hide after some seconds
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
        };

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }

    //Alert Dialog
    public static void showAlertMessage(Activity activity, String title1, String msg1, final boolean bPositive, final boolean bNegative) {

        final String title = title1, msg = msg1;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog.Builder alertBld = new AlertDialog.Builder(activity);

                alertBld.setTitle(title);
                alertBld.setMessage(msg);
                alertBld.setCancelable(false);

                if (bPositive) {
                    alertBld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            /*if (context==statistics.statInstance)
                            {
                                CommonData.actionCode =8; // Stat Counts Reset
                                createRequest((byte)8);
                            }
                            else if (bReset){ //If Reset request
                                bReset=false;
                                para.actionCode = 0x09;
                                createRequest((byte)0x09); //To Reset Pacemaker
                            }*/
                            dialog.cancel();
                        }
                    });
                }
                if (bNegative) {
                    alertBld.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //mprogressbar.setVisibility(View.INVISIBLE); //circular progress bar invisible
                            dialog.cancel();
                        }
                    });
                }
                AlertDialog alertDlg = alertBld.create();

                //alertDlg.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
                //alertDlg.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                alertDlg.show();
                alertDlg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.DKGRAY);
                alertDlg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.DKGRAY);
            }
        });
    }

    //for showing progress
    public static void show(Context context) {
        pDialog = new ProgressDialog(context);
        if (pDialog != null && pDialog.isShowing()) {
        }else {
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
            pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pDialog.setContentView(R.layout.progress_box);
            ImageView imgProgress = (ImageView) pDialog.findViewById(R.id.imgProgress);
            Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.rotating);
            imgProgress.startAnimation(animation1);
        }
    }

    //for hiding progress
    public static void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
