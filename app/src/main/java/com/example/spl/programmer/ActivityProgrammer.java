package com.example.spl.programmer;


import static com.example.spl.egm.EGMMarkerFragment.bThrChamber;
import static com.example.spl.egm.EGMMarkerFragment.bVPThr;
import static com.example.spl.egm.EGMMarkerFragment.bVPWThr;
import static com.example.spl.egm.EGMMarkerFragment.bVSThr;
import static com.example.spl.egm.EGMMarkerFragment.butMarker;
import static com.example.spl.egm.EGMMarkerFragment.butThrNext;
import static com.example.spl.egm.EGMMarkerFragment.draw_marker;
import static com.example.spl.egm.EGMMarkerFragment.markONOFF;
import static com.example.spl.egm.EGMMarkerFragment.stopMarker;
import static com.example.spl.helper.CommonData.hidePDialog;
import static com.example.spl.helper.CommonData.iSrno;
import static com.example.spl.helper.CommonData.show;
import static com.example.spl.helper.CommonData.tempFileOption;
import static com.example.spl.programmer.dualchamber.StatisticsFragment.showlogparaDual;
import static com.example.spl.helper.CommonData.POR_DDDR_AM;
import static com.example.spl.helper.CommonData.POR_Pinn8820AM;
import static com.example.spl.helper.CommonData.POR_Pinr297;
import static com.example.spl.helper.CommonData.ampctrlWD297;
import static com.example.spl.helper.CommonData.bARC;
import static com.example.spl.helper.CommonData.bATAF;
import static com.example.spl.helper.CommonData.bATEna;
import static com.example.spl.helper.CommonData.bAVHSrch;
import static com.example.spl.helper.CommonData.bAutoMsr;
import static com.example.spl.helper.CommonData.bAutoPol;
import static com.example.spl.helper.CommonData.bHystON;
import static com.example.spl.helper.CommonData.bHystSrchON;
import static com.example.spl.helper.CommonData.bMRI;
import static com.example.spl.helper.CommonData.demoModelArray;
import static com.example.spl.helper.CommonData.flagMainFragment;
import static com.example.spl.helper.CommonData.funSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funSetArrayAdapterString;
import static com.example.spl.helper.CommonData.funSmallSetArrayAdapterInteger;
import static com.example.spl.helper.CommonData.funSmallSetArrayAdapterString;
import static com.example.spl.helper.CommonData.getColor297;
import static com.example.spl.helper.CommonData.getColor747;
import static com.example.spl.helper.CommonData.iAAmp;
import static com.example.spl.helper.CommonData.iAPW;
import static com.example.spl.helper.CommonData.iAPacepol;
import static com.example.spl.helper.CommonData.iARef;
import static com.example.spl.helper.CommonData.iASen;
import static com.example.spl.helper.CommonData.iATEnt;
import static com.example.spl.helper.CommonData.iATExt;
import static com.example.spl.helper.CommonData.iATRate;
import static com.example.spl.helper.CommonData.iAVHyst;
import static com.example.spl.helper.CommonData.iAmp;
import static com.example.spl.helper.CommonData.iBat;
import static com.example.spl.helper.CommonData.iBlnk;
import static com.example.spl.helper.CommonData.iDownTime;
import static com.example.spl.helper.CommonData.iMode;
import static com.example.spl.helper.CommonData.iPAVI;
import static com.example.spl.helper.CommonData.iPW;
import static com.example.spl.helper.CommonData.iPacePol;
import static com.example.spl.helper.CommonData.iPacerSelect;
import static com.example.spl.helper.CommonData.iPvarp;
import static com.example.spl.helper.CommonData.iRate;
import static com.example.spl.helper.CommonData.iRef;
import static com.example.spl.helper.CommonData.iSAVI;
import static com.example.spl.helper.CommonData.iScreenFlag;
import static com.example.spl.helper.CommonData.iSen;
import static com.example.spl.helper.CommonData.iSenPol;
import static com.example.spl.helper.CommonData.iSensorRate;
import static com.example.spl.helper.CommonData.iSlope;
import static com.example.spl.helper.CommonData.iSrnoH;
import static com.example.spl.helper.CommonData.iSrnoL;
import static com.example.spl.helper.CommonData.iTrigUprRate;
import static com.example.spl.helper.CommonData.ihystVAL;
import static com.example.spl.helper.CommonData.iuptime;
import static com.example.spl.helper.CommonData.mFunAlert;
import static com.example.spl.helper.CommonData.mFunAlertAuto;
import static com.example.spl.helper.CommonData.map297_8820;
import static com.example.spl.helper.CommonData.map8820;
import static com.example.spl.helper.CommonData.mapAMP297;
import static com.example.spl.helper.CommonData.mapPW297;
import static com.example.spl.helper.CommonData.mapPW8820;
import static com.example.spl.helper.CommonData.mapSlope297;
import static com.example.spl.helper.CommonData.mapValidationMsg;
import static com.example.spl.helper.CommonData.modeArray747;
import static com.example.spl.helper.CommonData.modeCtrlWD747;
import static com.example.spl.helper.CommonData.pacerData;
import static com.example.spl.helper.CommonData.pacerDataPROG;
import static com.example.spl.helper.CommonData.pacerDataPROGImp;
import static com.example.spl.helper.CommonData.rateArray;
import static com.example.spl.helper.CommonData.rateArray747;
import static com.example.spl.helper.CommonData.rateArrayP;
import static com.example.spl.helper.CommonData.remLifeCalc;
import static com.example.spl.helper.CommonData.save_tPara;
import static com.example.spl.helper.CommonData.showAlertMessage;
import static com.example.spl.helper.CommonData.tAmp;
import static com.example.spl.helper.CommonData.tMode;
import static com.example.spl.helper.CommonData.tPacePol;
import static com.example.spl.helper.CommonData.tRate;
import static com.example.spl.helper.CommonData.tSenPol;
import static com.example.spl.helper.CommonData.tbHystSrchON;
import static com.example.spl.programmer.singlechamber.StatisticsSingleFragment.showlogpara;
import static java.lang.Math.log;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.spl.BluetoothDevicesActivity;
import com.example.spl.FolderListActivity;
import com.example.spl.egm.EGMMarkerFragment;
import com.example.spl.ListLogFragment;
import com.example.spl.R;
import com.example.spl.programmer.dualchamber.FragmentDualVVI;
import com.example.spl.programmer.dualchamber.FragmentDualVVIR;
import com.example.spl.programmer.dualchamber.FragmentParaDDD;
import com.example.spl.programmer.dualchamber.FragmentParaDDDNoATP;
import com.example.spl.programmer.dualchamber.FragmentParaDDDR;
import com.example.spl.programmer.dualchamber.FragmentStatisticsTabs;
import com.example.spl.programmer.dualchamber.FragmentThresholdTestDual;
import com.example.spl.helper.CommonData;
import com.example.spl.helper.CustomProgressBar;
import com.example.spl.helper.PreferenceManager;
import com.example.spl.model.ImpedanceDual;
import com.example.spl.model.ProgressItem;
import com.example.spl.programmer.singlechamber.AM8820.FragmentPara8820VVI;
import com.example.spl.programmer.singlechamber.FragmentParaVVI;
import com.example.spl.programmer.singlechamber.FragmentParaVVIR;
import com.example.spl.programmer.singlechamber.FragmentSingleStatisticsTabs;
import com.example.spl.programmer.singlechamber.FragmentThresholdTestSingle;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ActivityProgrammer extends AppCompatActivity {
    Button btnThresholdTest,btnDate, btnFile, btnResetPara, btnStatistics, btnEgmGain, btnScale, btnInterrogation, btnUrgent, btnLogs, btnCancel;
    ImageView mImgPatient, mImgPrint, mImgMail, mImgBack, mImgLanguage;
    RelativeLayout btSavePDF;
    public static Button btnProgram;
    public static ImageView mImgAlert;
    public static Spinner spinMode, spinModel;
    public static Spinner spinRate;
    public static LinkedHashMap<String, ImpedanceDual> mapImp = new LinkedHashMap<String, ImpedanceDual>();
    Spinner spinThrCycles, spinThrPolarity, spinThrRefractory, spinThrPw, spinThrAmp;
    String model;
    boolean bTuchSpinMode = false;
    boolean bTuchSpinRate = false;
    //TextView mTxtImpedanceValueCurr;
    public static String mode = "VVI";
    private ArrayAdapter<String> modelAdapter;
    public static ArrayAdapter<Integer> rateAdapter;
    Bundle bundle = new Bundle();
    FragmentManager fm = getSupportFragmentManager();
    public static FragmentManager fm5;
    EGMMarkerFragment egmMarkerFragment = new EGMMarkerFragment();
    private BluetoothAdapter bta;
    int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    BluetoothConnectivity bluetoothConnectivity;
    CountDownTimer waitTimer;
    RelativeLayout btnConnectBluetooth;
    private int[] teleData = new int[75];//[55];
    private boolean bStatSenCnt = false;
    boolean bMarkViewON = true;
    boolean bthrScreen = false, bReset = false;
    private static boolean bDataReadOK = true;
    static Handler handler;
    private static final int STAT_LISTENING = 1;
    private static final int STAT_CONNECTING = 2;
    private static final int STAT_CONNECTED = 3;
    private static final int STAT_CONNECTIONFAILED = 4;
    private static final int STAT_MSG_RECEIVED = 5;
    static DataTransferClass dataTransferClass;
    public static byte[] interrogateBT;
    private static byte[] bytesToSend;
    private static int n = 0;
    public static ArrayAdapter<String> modeSpinnerArrayAdapter;
    List<String> keys;
    int modePrevious;
    int current;
    public static TextView mTxtDeviceName, mTxtDeviceNumber, mTxtBatteryLife;
    Handler handlerBlink = new Handler();
    int bt_notes_blink = 0;
    BluetoothSocket clientSocket = null;
    ToggleButton idBtMark;
    LinearLayout mLayoutModelDetails;
    LinearLayout mLayoutBatteryTxt;
    RelativeLayout mLayoutModelDropdown;
    private CustomProgressBar seekbar;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;
    RelativeLayout mLayoutBatteryView;
    public static Activity activity;
    ImageView mImgImpedance;
    TextView mTxtImpToday;
    TextView mTxtImpLast;
    TextView mTxtImpLastValV;
    TextView mTxtValueBattery;
    public static TextView mTxtImpLastValA;
    public static TextView mTxtImpTodayValA;
    TextView mTxtImpTodayValV;
    public static TextView mTxtImpTitleV;
    public static TextView mTxtImpTitleA;
    ArrayList<ImpedanceDual> logArrayList = new ArrayList<>();
    ArrayList<String> stringList = new ArrayList<>();
    int mThrAtVeMode = 0;
    RadioGroup rgThrAmpPW;
    RadioButton radioThrAmplitude;
    RadioButton radioThrPulseWidth;
    RelativeLayout mLayoutPw;
    RelativeLayout mLayoutAmp;
    RelativeLayout mLayoutBtnAtrium;
    RelativeLayout mLayoutBtnVentricle;
    public static boolean isInterrogation = false;

    public static LinearLayout mLayoutImpedanceThreshold;
    public static LinearLayout mLayoutThreshold;
    public static LinearLayout mLayoutRateMode;
    public static LinearLayout mLayoutThrAtVt;
    public static LinearLayout mLayoutSideOptionButton;

    ArrayAdapter<Integer> mIntegerArrayAdapterCycles;
    ArrayAdapter<Integer> mIntegerArrayAdapterRefractory;
    ArrayAdapter<String> mStringArrayAdapterPolarity;
    ArrayAdapter<Double> mDoubleArrayAdapterPW;
    ArrayAdapter<Double> mDoubleArrayAdapterAMP;

    int languageIndex = 0;
    String[] languageStrArr = new String[]{"English", "Russian", "Spanish"};
    String[] languageCodeArr = new String[]{"en", "ru", "es"};


    //pdf
    private ProgressDialog mWait;
    private Button butSavePdf;
    private TextView pdfTxtTitle;
    private TextView pdfimplDate;
    private String strChamberPace;
    private String strChamberSens;
    private Paint pdfTitlePaint;
    private Paint pdfParaPaint;
    private Paint markPaint;
    private Paint txtPaint;
    private Paint rulerPaint;
    private Paint ecgPaint;
    private Paint gridPaint;
    private Paint pdfChartPaint; //To select color
    private EditText pdfPname;
    private EditText pdfpAge;
    private EditText pdfLdSrno;
    private EditText pdfDrName;
    private EditText pdfHName;
    private EditText pdfPthr;
    private EditText pdfSthr;
    private EditText pdfRemark,pdfImpd;

    private Button butImplDate;
    private boolean bFst51;
    private final PdfDocument pdfDoc = new PdfDocument();
    private PdfDocument.PageInfo pageInfo;
    private PdfDocument.Page page;
    private Canvas pdfCanvas;
    private int y;
    private int pageW;
    private int pageH;
    private int txtVgap;
    private int pageNo;
    private int ecgBaseLine;
    private int ecgX;
    private int oldecgData;
    private int ecgScale;
    private int mrkBaseLine; // ECG variables
    private int val35;
    private int prevVal35;
    private int val51;

    private int vMarkPos,vMarkPosPrev,aMarkPos,aMarkPosPrev;

    private int vMarkPrd;
    private int vMarkPrdDisp;
    int aMarkVal;
    private int vMarkVal;


    private int[] mrkData; //To hold marker/ECG Data
    private int thrType;
    private int paraIdx;
    private int chamberType;









    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String languageToLoad = PreferenceManager.getCurrentLanguage(ActivityProgrammer.this); // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_programmer);
        isInterrogation = false;
        fm5 = getSupportFragmentManager();
        activity = ActivityProgrammer.this;
        bta = BluetoothAdapter.getDefaultAdapter();
        bta.enable();

        btnConnectBluetooth = findViewById(R.id.btnConnectBluetooth);
        btnInterrogation = findViewById(R.id.btnInterrogation);
        btnProgram = findViewById(R.id.btnProgram);
        btnUrgent = findViewById(R.id.btnUrgent);
        btnCancel = findViewById(R.id.btnCancel);
        btnFile = findViewById(R.id.btnFile);
        //mTxtImpedanceValueCurr = findViewById(R.id.mTxtImpedanceValueCurr);
        mImgPatient = findViewById(R.id.mImgPatient);
        mImgPrint = findViewById(R.id.mImgPrint);
        mImgMail = findViewById(R.id.mImgMail);
        mImgAlert = findViewById(R.id.mImgAlert);
        btnResetPara = findViewById(R.id.btnResetPara);
        seekbar = findViewById(R.id.seekBar0);
        btnStatistics = findViewById(R.id.btnStatistics);
        mLayoutBatteryView = findViewById(R.id.mLayoutBatteryView);
        btnLogs = findViewById(R.id.btnLogs);
        spinMode = findViewById(R.id.spinMode);
        spinRate = findViewById(R.id.spinRate);
        spinModel = findViewById(R.id.spinModel);
        idBtMark = findViewById(R.id.idBtMark);
        btnThresholdTest = findViewById(R.id.btnTest);
        mImgBack = findViewById(R.id.mImgBack);
        mLayoutModelDetails = findViewById(R.id.mLayoutModelDetails);
        mTxtDeviceName = findViewById(R.id.mTxtDeviceName);
        mTxtDeviceNumber = findViewById(R.id.mTxtDeviceNumber);
        mLayoutModelDropdown = findViewById(R.id.mLayoutModelDropdown);
        mTxtBatteryLife = findViewById(R.id.mTxtBatteryLife);
        mImgLanguage = findViewById(R.id.mImgLanguage);
        mImgImpedance = findViewById(R.id.mImgImpedance);
        mTxtImpToday = findViewById(R.id.mTxtImpToday);
        mTxtImpLast = findViewById(R.id.mTxtImpLast);
        mTxtImpLastValV = findViewById(R.id.mTxtImpLastValV);
        mTxtImpLastValA = findViewById(R.id.mTxtImpLastValA);
        mTxtImpTodayValA = findViewById(R.id.mTxtImpTodayValA);
        mTxtImpTodayValV = findViewById(R.id.mTxtImpTodayValV);
        mTxtImpTitleV = findViewById(R.id.mTxtImpTitleV);
        mTxtImpTitleA = findViewById(R.id.mTxtImpTitleA);
        mTxtValueBattery = findViewById(R.id.mTxtValueBattery);
        mLayoutBatteryTxt = findViewById(R.id.mLayoutBatteryTxt);


        spinThrCycles = findViewById(R.id.spinThrCycles);
        spinThrPolarity = findViewById(R.id.spinThrPolarity);
        spinThrRefractory = findViewById(R.id.spinThrRefractory);
        spinThrPw = findViewById(R.id.spinThrPw);
        spinThrAmp = findViewById(R.id.spinThrAmp);

        rgThrAmpPW = findViewById(R.id.rgThrAmpPW);
        radioThrAmplitude = findViewById(R.id.radioThrAmplitude);
        radioThrPulseWidth = findViewById(R.id.radioThrPulseWidth);
        mLayoutPw = findViewById(R.id.mLayoutPw);
        mLayoutAmp = findViewById(R.id.mLayoutAmp);
        mLayoutBtnAtrium = findViewById(R.id.mLayoutBtnAtrium);
        mLayoutBtnVentricle = findViewById(R.id.mLayoutBtnVentricle);
        mLayoutImpedanceThreshold = findViewById(R.id.mLayoutImpedanceThreshold);
        mLayoutThreshold = findViewById(R.id.mLayoutThreshold);
        mLayoutRateMode = findViewById(R.id.mLayoutRateMode);
        mLayoutThrAtVt = findViewById(R.id.mLayoutThrAtVt);
        btnEgmGain = findViewById(R.id.btnEgmGain);
        btnScale = findViewById(R.id.btnScale);
        mLayoutSideOptionButton = findViewById(R.id.mLayoutSideOptionButton);

        IntentFilter btMonitor = new IntentFilter();
        btMonitor.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        btMonitor.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        btMonitor.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        btMonitor.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        this.registerReceiver(btStatReceiver, btMonitor);
        startRepeatingTask();
        //seekbar.getThumb().mutate().setAlpha(0);
        //initDataToSeekbar();


        rateAdapter = funSetArrayAdapterInteger(4, ActivityProgrammer.this, Arrays.asList(rateArray), iRate, (byte) 'R');
        spinRate.setAdapter(rateAdapter);
        spinRate.setSelection(iRate);


        keys = new ArrayList<>(map297_8820.keySet());
        modeSpinnerArrayAdapter = funSetArrayAdapterString(ActivityProgrammer.this, keys, 0, (byte) 0);
        spinMode.setAdapter(modeSpinnerArrayAdapter);

        mImgAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogErrorList();
            }
        });

        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityProgrammer.this, FolderListActivity.class);
                intent.putExtra("filepath", activity.getExternalFilesDir("").getAbsolutePath() + "/SPL_LOG/");
                startActivity(intent);
            }
        });

        mImgPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funPdfDailog();
            }
        });

        mImgLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRadioConfirmationDialog();
            }
        });


        idBtMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                markONOFF(isChecked);
            }
        });


        btnInterrogation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientSocket != null) {
                    CommonData.strAction = "Interrogation";
                    createRequest((byte) 1);
                } else {
                    mFunAlertAuto(ActivityProgrammer.this, "Alert", "Please connect to bluetooth");
                }

            }
        });

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (bta.isEnabled()) {
                        bta.disable();
                    }
                    CommonData.mStrBluetoothAddress = "";
                    if (clientSocket != null) {
                        DataTransferClass.inputStream.close();
                        DataTransferClass.outputStream.close();
                        dataTransferClass.interrupt();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LocalBroadcastManager.getInstance(ActivityProgrammer.this).unregisterReceiver(btStatReceiver);
                finish();
            }
        });

        btnThresholdTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapValidationMsg.size()>0){
                    mFunAlertAuto(ActivityProgrammer.this, "Alert", "invalid parameters");
                }else {
                    mLayoutImpedanceThreshold.setVisibility(View.GONE);
                    mLayoutThreshold.setVisibility(View.VISIBLE);
                    mLayoutRateMode.setVisibility(View.GONE);
                    mLayoutSideOptionButton.setVisibility(View.GONE);
                    mLayoutThrAtVt.setVisibility(View.VISIBLE);
                    funCallThreshold();
                }

            }
        });

        btnUrgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInterrogation) {
                    createRequest((byte) 0x0B);
                    CommonData.strAction = "Urgent Program";
                }else {
                    mFunAlertAuto(ActivityProgrammer.this, "Alert", "Device not interrogated");
                }
            }
        });

        btnLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.prog_frame, new ListLogFragment(), "ListLogFragment").commit();
                fm.beginTransaction().addToBackStack(null);
            }
        });


        mImgImpedance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInterrogation) {
                    if(mapValidationMsg.size()>0){
                        mFunAlertAuto(ActivityProgrammer.this, "Alert", "invalid parameters");
                    }else {
                        CommonData.strAction = "Impedance";
                        createRequest((byte) 0x64);
                    }
                } else {
                    mFunAlertAuto(ActivityProgrammer.this, "Alert", "Device not interrogated");
                }

            }
        });

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonData.strAction = "Statistics";
                if (iPacerSelect == 24 || iPacerSelect == 12) {
                    fm.beginTransaction().replace(R.id.prog_frame, new FragmentStatisticsTabs(), "StatisticsFragment").commit();
                    fm.beginTransaction().addToBackStack(null);
                } else {
                    fm.beginTransaction().replace(R.id.prog_frame, new FragmentSingleStatisticsTabs(), "StatisticsFragment").commit();
                    fm.beginTransaction().addToBackStack(null);
                }

            }
        });

        btnConnectBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bta != null) {
                    if (!bta.isEnabled()) {
                        if (ActivityCompat.checkSelfPermission(ActivityProgrammer.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
                        } else {
                            Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivity(btIntent);
                        }
                    } else
                        startActivity(new Intent(getApplicationContext(), BluetoothDevicesActivity.class));
                }
            }
        });


        btnProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("<><><>####Call prog11");
                if (isInterrogation) {
                    if(mapValidationMsg.size()>0){
                        System.out.println("<><><>####Call prog22");
                        mFunAlertAuto(ActivityProgrammer.this, "Alert", "invalid parameters");
                    }else {
                        System.out.println("<><><>####Call prog33");
                        CommonData.strAction = "Programming";
                        if (mode == "VOO" || mode == "OVO" || mode == "AOO" || mode == "OAO" || mode == "VVIR" || mode == "VOOR" || mode == "VVTR" || mode == "AAIR" || mode == "AOOR" || mode == "AATR") {

                            if (iPacerSelect == 25 || iPacerSelect == 27) {
                                CommonData.pacerDataPROG[9] = 0;
                                CommonData.pacerDataPROG[10] &= 0x1F; // search set false
                            } else if (iPacerSelect == 12 || iPacerSelect == 24) {
                                CommonData.pacerDataPROG[10] &= 0xF0;
                                CommonData.pacerDataPROG[10] |= CommonData.thystVAL;

                                if (CommonData.tMode <= 7) //if SSI mode then Hyst srch else AV Hyst Srch
                                {
                                    CommonData.tbHystSrchON = false;
                                    CommonData.pacerDataPROG[15] &= 0x7F;
                                } else {
                                    CommonData.tbAVHSrch = false;
                                    CommonData.pacerDataPROG[15] &= 0xBF;

                                }
                            }
                        }
                        createRequest((byte) 4);
                    }

                } else {
                    mFunAlertAuto(ActivityProgrammer.this, "Alert", "Device not interrogated");
                }
            }
        });


        mImgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Report";
                String message = "Please find the attach report ";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        fm.beginTransaction().replace(R.id.marker_frame, new EGMMarkerFragment()).commit();
        fm.beginTransaction().addToBackStack(null);


        btnEgmGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fm.beginTransaction().replace(R.id.prog_frame, new FragmentParaVVI(), "FragmentTopFramVvi").commit();
        fm.beginTransaction().addToBackStack(null);

        //threshold cross button

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapValidationMsg.clear();
                funCheckValidationList();
                System.arraycopy(CommonData.pacerData, 0, CommonData.pacerDataPROG, 0, CommonData.pacerData[1] + 5);
                CommonData.decodeBytes();
                updateViews(iPacerSelect);
                //resetScreen();
            }
        });
        btnResetPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("<><><>call  5555 " + mode);
                System.out.println("<><><>call  5556 " + iPacerSelect);
                resetScreen();
            }
        });


        spinMode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinMode = true;
                final boolean b = v.performClick();
                return false;
            }
        });

        spinMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                modePrevious = current;
                current = i;
                tMode = i;
                mode = spinMode.getSelectedItem().toString();
                System.out.println("<><><>call  5555 " + mode);
                System.out.println("<><><>call  5556 " + iPacerSelect);
                resetScreen();
                if (iPacerSelect == 24 || iPacerSelect == 12) {
                    System.out.println("<><><>######calllll " + tMode);
                    pacerDataPROG[6] = pacerDataPROG[6] & 0x03;
                    pacerDataPROG[6] += modeCtrlWD747[tMode];
                    System.out.println("<><><>######calllll$$$$ " + pacerDataPROG[6]);
                } else if (iPacerSelect == 25) {
                    List<Integer> valueMode = new ArrayList<>(map297_8820.values());
                    pacerDataPROG[6] = pacerDataPROG[6] & 0x07;
                    pacerDataPROG[6] += valueMode.get(tMode);
                } else {
                    List<Integer> valueMode = new ArrayList<>(map8820.values());
                    pacerDataPROG[6] = pacerDataPROG[6] & 0x07;
                    pacerDataPROG[6] += valueMode.get(tMode);
                }
                if (iPacerSelect == 24 || iPacerSelect == 12) {
                    rateAdapter = funSetArrayAdapterInteger(1, ActivityProgrammer.this, Arrays.asList(rateArray747), tRate, (byte) 'R');
                    spinRate.setAdapter(rateAdapter);
                    spinRate.setSelection(tRate);
                } else {
                    rateAdapter = funSetArrayAdapterInteger(2, ActivityProgrammer.this, Arrays.asList(rateArray), tRate, (byte) 'R');
                    spinRate.setAdapter(rateAdapter);
                    spinRate.setSelection(tRate);
                }
                //validateCommonData();
                blnkProgBut();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinRate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bTuchSpinRate = true;
                final boolean b = v.performClick();
                return false;
            }
        });
        spinRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (bTuchSpinRate) {
                    CommonData.bSpinClick = true;
                    bTuchSpinRate = false;
                    tRate = i;


                    if (iPacerSelect == 24 || iPacerSelect == 12) {
                        pacerDataPROG[15] = pacerDataPROG[15] & 0xC0;
                        pacerDataPROG[15] += tRate;
                    } else {
                        pacerDataPROG[12] = tRate;
                    }

                    if (iScreenFlag == 1) {
                        FragmentParaVVIR.validateParaMain(14);
                        //FragmentParaVVIR.validateParaOther(6);
                        //FragmentParaVVIR.validateParaRateResponse(5);
                    } else if (iScreenFlag == 2) {
                        FragmentParaVVI.validateParaMainSingle();
                        //FragmentParaVVI.validateParaOther();
                    } else if (iScreenFlag == 23) {
                        FragmentPara8820VVI.validateParaMain();
                        FragmentPara8820VVI.validateParaOther();
                    } else if (iScreenFlag == 12) {
                        FragmentDualVVIR.validateParaMain();
                        FragmentDualVVIR.validateParaRateResponse();
                        FragmentDualVVIR.validateParaOther();
                    } else if (iScreenFlag == 13) {
                        FragmentDualVVI.validateParaMain();
                        FragmentDualVVI.validateParaOther();
                    } else if (iScreenFlag == 16) {
                        FragmentParaDDD.validateParaAtrium();
                        FragmentParaDDD.validateParaOther();
                        FragmentParaDDD.validateParaVentricle();
                        FragmentParaDDD.validateParaATP();
                    } else if (iScreenFlag == 17) {
                        FragmentParaDDDR.validateParaAtrium();
                        FragmentParaDDDR.validateParaOther();
                        FragmentParaDDDR.validateParaVentricle();
                        FragmentParaDDDR.validateParaATP();
                        FragmentParaDDDR.validateParaRateResponse();
                    }
                    validateCommonData();

                    blnkProgBut();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what) {
                    case STAT_LISTENING:
                    case STAT_CONNECTING:

                        break;
                    case STAT_CONNECTED:
                        waitTimer.cancel();

                        break;
                    case STAT_CONNECTIONFAILED:
                        waitTimer.cancel();
                        hidePDialog();
                        mFunAlertAuto(ActivityProgrammer.this, "Alert", "Device Could not be Connected");
                        //Toast.makeText(getApplicationContext(), "Device Could not be Connected", Toast.LENGTH_SHORT).show();
                        break;
                    case STAT_MSG_RECEIVED:
                        teleData = (int[]) msg.obj;
                        if ((teleData[0] == 6 || teleData[0] == 7) && (teleData[1] == 34 || teleData[1] >= 2)) //ECG Data
                        {
                            //do nothing  Debug
                            String strBytes = new String();
                            strBytes = "";
                            strBytes = String.valueOf(msg.arg1).toString() + "-";
                            for (int p = 0; p < msg.arg1; p++) {
                                strBytes = strBytes + " " + String.valueOf(teleData[p]).toString();
                            }
                        } else {
                            String strBytes = new String();
                            strBytes = "";
                            strBytes = String.valueOf(msg.arg1).toString() + "-";
                            for (int p = 0; p < msg.arg1; p++) {
                                strBytes = strBytes + " " + String.valueOf(teleData[p]).toString();
                            }
                        }
                        //**********end Debug ********
                        if ((teleData[0] == 6 || teleData[0] == 7) && (teleData[1] == 34 || teleData[1] >= 2)) //ECG Data
                        {
                            getPacerData();
                        } else if (CommonData.VARIFYCRC(teleData)) {
                            waitTimer.cancel();
                            getPacerData();
                        } else {
                            waitTimer.cancel();
                            //mFunAlertAuto(ActivityProgrammer.this, "Alert", "Error (CRC CheckSum)");
                            //Toast.makeText(getApplicationContext(), "Error (CRC CheckSum)", Toast.LENGTH_SHORT).show();
                        }
                        bDataReadOK = true;
                        break;
                    default:
                        break;
                }
                return false;
            }

        });


        if (mThrAtVeMode == 0) {
            mLayoutBtnAtrium.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            mLayoutBtnVentricle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        //btConnectionCheck();
        init();
        validateCommonData();
    }

    public static void showLogFile() {
        new Handler().post(new Runnable() {
            public void run() {

            }
        });
    }

    private void resetScreen() {

        if (iPacerSelect == 24 || iPacerSelect == 12) {
            if (mode == "DDD" || mode == "VDD") {
                flagMainFragment = 1;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentParaDDD()).commit();
                fm.beginTransaction().addToBackStack(null);
            } else if (mode == "DDDR" || mode == "VDDR") {
                flagMainFragment = 1;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentParaDDDR()).commit();
                fm.beginTransaction().addToBackStack(null);
            } else if (mode == "ODO") {
                flagMainFragment = 1;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentParaDDDNoATP()).commit();
                fm.beginTransaction().addToBackStack(null);
            } else if (mode == "VVIR" || mode == "VOOR" || mode == "VVTR" || mode == "AAIR" || mode == "AOOR" || mode == "AATR") {
                flagMainFragment = 2;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentDualVVIR()).commit();
                fm.beginTransaction().addToBackStack(null);

            } else if (mode == "VVI" || mode == "VOO" || mode == "VVT" || mode == "OVO" || mode == "AAI" || mode == "AOO" || mode == "AAT" || mode == "OAO" || mode == "OOO") {
                flagMainFragment = 4;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentDualVVI(), "FragmentCommonDataVviTabs").commit();
                fm.beginTransaction().addToBackStack(null);
            }

        } else if (iPacerSelect == 2 || iPacerSelect == 27) {
            if (mode == "VVI" || mode == "VOO" || mode == "VVT" || mode == "OVO" || mode == "AAI" || mode == "AOO" || mode == "AAT" || mode == "OAO" || mode == "OOO") {
                flagMainFragment = 4;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentPara8820VVI(), "FragmentCommonDataVviTabs").commit();
                fm.beginTransaction().addToBackStack(null);
            }
        } else {
            if (mode == "VVI" || mode == "VOO" || mode == "VVT" || mode == "OVO" || mode == "AAI" || mode == "AOO" || mode == "AAT" || mode == "OAO" || mode == "OOO") {
                flagMainFragment = 4;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentParaVVI(), "FragmentCommonDataVviTabs").commit();
                fm.beginTransaction().addToBackStack(null);
            } else if (mode == "VVIR" || mode == "VOOR" || mode == "VVTR" || mode == "AAIR" || mode == "AOOR" || mode == "AATR") {
                flagMainFragment = 4;
                fm.beginTransaction().replace(R.id.prog_frame, new FragmentParaVVIR(), "FragmentCommonDataVviTabs").commit();
                fm.beginTransaction().addToBackStack(null);
            }

        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(tempFileOption == 0){
            if (CommonData.mStrBluetoothAddress == null || CommonData.mStrBluetoothAddress.isEmpty() || CommonData.mStrBluetoothAddress.trim().isEmpty()) {

            } else {
                //System.out.println("<><><><><> " + CommonData.mStrBluetoothAddress);
                connectProgrammer(CommonData.mStrBluetoothAddress);
            }
        }else {
            tempFileOption = 0;
            fm.beginTransaction().replace(R.id.prog_frame, new ListLogFragment(), "ListLogFragment").commit();
            fm.beginTransaction().addToBackStack(null);
        }


    }


    private void connectProgrammer(String address) {
        if (address != null) {
            show(ActivityProgrammer.this);
            System.out.println("<><><>###@@   Start");
            bluetoothConnectivity = new BluetoothConnectivity(bta.getRemoteDevice(address), ActivityProgrammer.this);
            bluetoothConnectivity.start();
            timeOut(30000);// wait for 30S
            waitTimer.start();
        } else
            Toast.makeText(getApplicationContext(), "No Device is Selected", Toast.LENGTH_SHORT).show();
    }

    public void timeOut(long timems) {
        waitTimer = new CountDownTimer(timems, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                hidePDialog();
                CommonData.logFileWrite(ActivityProgrammer.this, "Error in Communication", CommonData.pacerData, CommonData.currModel);
                mFunAlertAuto(ActivityProgrammer.this, "Alert", "Error in Communication");
                //Toast.makeText(getApplicationContext(), "Error in Communication", Toast.LENGTH_SHORT).show();
            }
        };
    }


    public String getMyData() {
        return mode;
    }

    class BluetoothConnectivity extends Thread {

        UUID spluuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        int MY_PERMISSIONS_REQUEST_LOCATION = 99;
        Activity context;

        BluetoothConnectivity(BluetoothDevice device, Activity context) {
            System.out.println("<><><>###@@   call fun");
            this.context = context;
            try {
                clientSocket = device.createInsecureRfcommSocketToServiceRecord(spluuid);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            super.run();
            boolean progConnected = false;
            try {
                if (clientSocket != null) {
                    if (!clientSocket.isConnected()) {
                        System.out.println("<><><>###@@   call");
                        clientSocket.connect();
                        System.out.println("<><><>###@@   End");
                        progConnected = true;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    if (clientSocket != null) {
                        clientSocket.close();
                        clientSocket = null;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (progConnected) {
                //btConnectionCheck();
                progConnected = false;
                hidePDialog();
                stopRepeatingTask();
                dataTransferClass = new DataTransferClass(clientSocket);
                dataTransferClass.start();
            }
        }

        void cancel() {
            try {

                if (clientSocket != null) {
                    clientSocket.close();
                    clientSocket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DataTransferClass extends Thread {
        BluetoothSocket dtSocket;
        static InputStream inputStream;
        static OutputStream outputStream;

        private DataTransferClass(BluetoothSocket socket) {


            dtSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = dtSocket.getInputStream();
                tmpOut = dtSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = tmpIn;
            outputStream = tmpOut;

        }

        public void run() {
            int[] dtRcvd = new int[55];
            int data;

            while (true) {

                try {

                    //String counts; //For statistics calculations
                    //Var for datatransfer class
                    int nAvailable = inputStream.available();
                    if (nAvailable > 0 && bDataReadOK) {
                        //data=0;
                        int nPacket;
                        for (nPacket = 0; nPacket < 55; nPacket++) {
                            dtRcvd[nPacket] = 0;
                        }
                        nPacket = 0;

                        data = inputStream.read();

                        //dtRcvd[nPacket]= (byte) data;
                        // First Byte
                        if (data == 3 || data == 4 || data == 5 || data == 6 || data == 7 || data == 8) {
                            dtRcvd[nPacket] = data;
                            nPacket++;
                            data = inputStream.read();
                            if (data < 45) { //(data < 40) {
                                dtRcvd[nPacket] = data;
                                nPacket++;
                                data = inputStream.read();
                                //Telemetry data confirmed
                                if (data == 1 || data == 2 || data == 4 || data == 6 || data == 18 ||
                                        data == 0x64 || data == 7 || data == 0x1B || data == 0x0B || data == 8) {
                                    dtRcvd[nPacket] = data;
                                    nPacket++;
                                    for (int t = 0; t < (dtRcvd[1] + 2); t++) {
                                        data = inputStream.read();
                                        if (data == 0x5C)
                                            data = inputStream.read();
                                        dtRcvd[nPacket] = data;
                                        nPacket++;
                                        //*****if (data == 0x5C && b5CFlg==false)
                                        //    b5CFlg=true; //to remove extra 0x5C
                                        //else {
                                        //   dtRcvd[nPacket] = data;
                                        //  nPacket++;
                                        // b5CFlg=false;
                                        //}******/
                                    }
                                    bDataReadOK = false;
                                    handler.obtainMessage(STAT_MSG_RECEIVED, dtRcvd[1] + 5, -1, dtRcvd).sendToTarget();

                                } else // ECG/Marker Data
                                {
                                    //if (bMarkViewON) {
                                    //Receive All ECG Data`
                                    dtRcvd[nPacket] = data;
                                    nPacket++;
                                    for (int t = 0; t < (dtRcvd[1] + 2); t++) {
                                        data = inputStream.read();
                                        if (data == 0x5C)
                                            data = inputStream.read();

                                        dtRcvd[nPacket] = data;
                                        nPacket++;
                                        //**************
                                        //if (data == 0x5C && dtRcvd[nPacket -1] != 0x5C)
                                        //    data=0; //Dummy operation to remove 0x5C
                                        //else{
                                        //   dtRcvd[nPacket] = data;
                                        //  nPacket++;
                                        //}
                                        //*********************
                                    }
                                    bDataReadOK = false;
                                    handler.obtainMessage(STAT_MSG_RECEIVED, dtRcvd[1] + 5, -1, dtRcvd).sendToTarget();

                                    //}

                                }

                            }

                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        void write(byte[] bytes) {

            try {
                outputStream.flush(); // Clear output buffer
                outputStream.write(bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void cancel() {
            try {
                inputStream.close();

                outputStream.close();

                dtSocket.close();
                dtSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    void getPacerData() {
        //System.out.println("<><><>call......111");
        //If marker window ON
        if ((teleData[0] == 6 || teleData[0] == 7) && (teleData[1] == 34 || teleData[1] >= 2)) //ECG / Marker Data
        {
            if (bMarkViewON) {
                if (teleData[0] == 6) {
                    System.arraycopy(teleData, 0, CommonData.markerData, 0, teleData[1] + 5);
                } else {
                    System.arraycopy(teleData, 0, CommonData.markerData, 39, teleData[1] + 5);
                }


                if (teleData[0] == 7) {

                    draw_marker();

                }
            } else
                waitTimer.cancel();
        } else if (teleData[0] == 8 && teleData[1] == 4 && (teleData[2] == 0x28))// Marker/Threshold Error
        {
            waitTimer.cancel();
            if (bMarkViewON) {
                butMarker.setChecked(false);
                System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);//Restore CommonData
                butThrNext.setEnabled(false);
                egmMarkerFragment.bFileWrite = false;
                egmMarkerFragment.bMarkON = false;
                CommonData.bThrStop = false;

            }
            Toast.makeText(getApplicationContext(), "Communication Lost", Toast.LENGTH_SHORT).show();
        } else if (teleData[0] == 8 && teleData[1] == 1 && (teleData[2] == 6 || teleData[2] == 0x0D))//wand Interrogate button pressed
        {
            //mprogressbar.setVisibility(View.VISIBLE);
            CommonData.actionCode = 1;
            createRequest((byte) 1);
            timeOut(5000);
            waitTimer.start();
        } else if (teleData[0] == 5 && (teleData[2] == 1 || teleData[2] == 4 || teleData[2] == 0x64 || teleData[2] == 7 || teleData[2] == 0x11 ||
                teleData[2] == 0x1B) && teleData[3] == 0x4F)// Different Pacer ID so send request to get pacer iD
        {
            if (CommonData.actionCode != 1) {
                CommonData.actionCode = 0;
                //mprogressbar.setVisibility(View.GONE);

                if (bMarkViewON) {
                    butMarker.setChecked(false);
                    System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);//Restore CommonData
                    butThrNext.setEnabled(false);
                    //bFileWrite=false;
                    // finish();
                    createRequest((byte) 2);
                }
                if (teleData[2] == 7) {
                    //statistics statClsInstance = statistics.getInstance();
                    // statClsInstance.waitCirBar.setVisibility(View.GONE);
                    // statClsInstance.finish();
                }
                //showAlertMessage(ActivityProgrammer.this,"Alert","Pacemaker is not Interrogated, First Interrogate",true,false);
            } else {
                createRequest((byte) 2);
            }
        } else if (teleData[0] == 5 && teleData[2] == 1 && teleData[3] == 0x64)// Reposition Wand
        {

            CommonData.actionCode = 0;
            // mprogressbar.setVisibility(View.GONE);
            mFunAlertAuto(ActivityProgrammer.this, "Alert", "Please Reposition Wand");

        } else if (teleData[0] == 4 && teleData[2] == 2 && teleData[1] < 15)// Store PacerID and Srno
        {

            iPacerSelect = teleData[3];
            CommonData.iPacerID = teleData[3];
            iSrnoH = teleData[4];
            iSrnoL = teleData[5];
            // CommonData.iSrno= (CommonData.iSrnoH * 256) + CommonData.iSrnoL;

            createRequest((byte) 6); //POR1
        } else if (teleData[0] == 4 && teleData[2] == 0xE4)// proggramer power on
        {
          // proggramer onnnnn popuup

        } else if (teleData[0] == 4 && teleData[2] == 6 && teleData[1] < 15)// POR1 response,  Send POR2, threshold next
        {
            if (CommonData.bThrNext) {
                CommonData.bThrNext = false;
                //  thresholdNext();

                //Toast.makeText(getApplicationContext(), "Threshold Next", Toast.LENGTH_SHORT).show();
                //markClsInstance.txtShowThrVal.setText(String.valueOf(CommonData.amparray297[CommonData.iAmp]).toString() + " V");
                //Start Timeout timer
                waitTimer.cancel();
                timeOut(3500);
                waitTimer.start();// To check marker is ON

            } else if (CommonData.bThrStop) {
                CommonData.bThrStop = false;
                Toast.makeText(getApplicationContext(), "Threshold End", Toast.LENGTH_SHORT).show();
            } else {
                createRequest((byte) 18); //POR 2

            }
        } else if (teleData[0] == 4 && teleData[2] == 18 && teleData[1] < 15)// Send new interrogate request
        {
            //fragInit=false;
            // Change Screen as per Model
           /* if (CommonData.iPacerID ==0x0E)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgPinn8820,"pin8820").commit();
            }
            if (CommonData.iPacerID ==0x19)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgPinn297,"pinr297").commit();
            }
            if (CommonData.iPacerID ==0x1B)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgPinn8820AM,"pinn8820AM").commit();
            }
            if (CommonData.iPacerID ==0x18)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgCharak747,"Charak747").commit();
            }
            if (CommonData.iPacerID ==0x0C)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgCharak747,"Charak747").commit();
            }
            if (CommonData.iPacerID ==0x10)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgPinn_P,"pinn_P").commit();
            }
            if (CommonData.iPacerID ==0x13)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgPinn_R,"pinn_R").commit();
            }
            if (CommonData.iPacerID ==0x1C)
            {
                manager.beginTransaction().replace(R.id.mainFrame,frgCharak_C,"charak_C").commit();
            }*/
            createRequest((byte) 1);
        } else if (teleData[0] == 4 && teleData[2] == 1 && teleData[1] < 40)// Receive Pacer Data Interrogation
        {
            //mprogressbar.setVisibility(View.GONE);
            System.arraycopy(teleData, 0, pacerData, 0, teleData[1] + 5);
            System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5); //copy data to prog array
            CommonData.decodeBytes();
            updateViews(iPacerSelect);
            isInterrogation = true;
            CommonData.logFileWrite(ActivityProgrammer.this, "Interrogation OK", CommonData.pacerData, CommonData.currModel); // Write Log File
            //Toast.makeText(getApplicationContext(), "Interrogation OK", Toast.LENGTH_SHORT).show();
            //Load file path and name
            System.out.println("<><>#####$$$$$ " + CommonData.currModel);
            CommonData.filePath = activity.getExternalFilesDir("").getAbsolutePath() + "/SPL_LOG/" + CommonData.currModel + "/";
            CommonData.fileName = iSrno + ".log";
            remLifeCalc();
            mapValidationMsg.clear();
            funCheckValidationList();
            blnkProgBut();
            mLayoutModelDropdown.setVisibility(View.GONE);
            mLayoutModelDetails.setVisibility(View.VISIBLE);
            mTxtBatteryLife.setText("Remaining Life\n(" + CommonData.remYear + " Yrs " + CommonData.remMonth + " Mon)");
            initDataToSeekbar(calculateProgress());
            setBatteryValuePos(calculateProgress());
            mLayoutBatteryView.setVisibility(View.VISIBLE);
            mFunAlertAuto(ActivityProgrammer.this, "Message", "Interrogation OK");


            //refreshScreen(CommonData.iPacerSelect);
            //updateViews(iPacerSelect);
            waitTimer.cancel();
            //Toast.makeText(getApplicationContext(),"Interrogation OK",Toast.LENGTH_SHORT).show();
            //setMenuItemState(true,true);

        } else if (teleData[0] == 4 && teleData[2] == 4 && teleData[1] < 40)// Receive Pacer Data Program
        {
            //mprogressbar.setVisibility(View.GONE);
            System.out.println("<><><><>###$$$$$$ " + pacerDataPROG[6]);
            System.arraycopy(pacerDataPROG, 6, pacerData, 6, pacerDataPROG[1] + 5);
            CommonData.logFileWrite(ActivityProgrammer.this, "Programming OK", CommonData.pacerData, CommonData.currModel); // Write Log File
            //CommonData.save_tCommonData();
            //refreshScreen(CommonData.iPacerSelect);
            // updateViews(CommonData.iPacerSelect);
            mapValidationMsg.clear();
            funCheckValidationList();
            blnkProgBut();
            save_tPara();
            //Toast.makeText(getApplicationContext(), "Programming OK", Toast.LENGTH_SHORT).show();
            //System.arraycopy(pacerDataPROG, 0, CommonData.pacerData, 0, pacerData[1] + 5);
            mFunAlertAuto(ActivityProgrammer.this, "Message", "Programming OK");
            updateViews(iPacerSelect);

        } else if (teleData[0] == 3 && teleData[1] == 1 && teleData[2] == 0x6) {
            //Do Nothing
        } else if (teleData[0] == 3 && teleData[1] == 1 && teleData[2] == 0x64) {
            //Do Nothing
        }
        //Impedance
        else if (teleData[0] == 4 && teleData[1] == 3 && teleData[2] == 0x64) {
            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x1B ||
                    iPacerSelect == 0x1D || iPacerSelect == 0x1E) //Receive Impedance
            {
                waitTimer.cancel();
                int idiv, TR2;
                if (teleData[3] >= 192)
                    idiv = 242;
                else if (teleData[3] >= 170)
                    idiv = 250;
                else
                    idiv = 255;

                if (teleData[3] <= 0) //To avoid invalid calculation (divide by zero)
                    TR2 = 50;
                else {
                    double tmp;

                    tmp = -log((double) teleData[3] / idiv);
                    TR2 = (int) (256 / tmp);

                }
                System.arraycopy(teleData, 0, pacerDataPROGImp, 0, teleData[1] + 5);
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal", pacerDataPROGImp, CommonData.currModel); // Write Log File
                mFunAlertAuto(ActivityProgrammer.this, "Message", "Impedance Ok");
                readImpedance(1);
                //updateViews(iPacerSelect);

            }
            if (iPacerSelect == 12 || iPacerSelect == 24) // Charak DDD (AM/AF)
            {
                waitTimer.cancel();
                System.arraycopy(teleData, 0, pacerDataPROGImp, 0, teleData[1] + 5);
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal", pacerDataPROGImp, CommonData.currModel); // Write Log File
                mFunAlertAuto(ActivityProgrammer.this, "Message", "Impedance Ok");
                readImpedance(3);
                //updateViews(iPacerSelect);

            }
            //For CPR Series Pacemaker
            if (iPacerSelect == 0x13 || iPacerSelect == 0x10 || iPacerSelect == 0x1C) {
                impd_CPR();
            }

        } else if (teleData[0] == 4 && teleData[1] == 2 && teleData[2] == 0x64) {
            //mprogressbar.setVisibility(View.GONE);
            System.arraycopy(teleData, 0, pacerDataPROGImp, 0, teleData[1] + 5);
            int TR2;
            waitTimer.cancel();

            //For CPR Series Pacemaker
            if (iPacerSelect == 0x13 || iPacerSelect == 0x10 || iPacerSelect == 0x1C) {
                impd_CPR();
            } else {
                if (teleData[3] >= 246)
                    teleData[3] = 240;
                TR2 = (teleData[3] - 6) * (teleData[3] - 6) / (246 - teleData[3]);
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal (Ohms) - " + setStrImpVal(TR2), pacerDataPROGImp, CommonData.currModel); // Write Log File


            }
            //refreshScreen(CommonData.iPacerSelect);
            mFunAlertAuto(ActivityProgrammer.this, "Message", "Impedance Ok");
            readImpedance(4);
            //updateViews(iPacerSelect);
        } else if (teleData[0] == 4 && teleData[1] == 2 && teleData[2] == 0x64) {
            //mprogressbar.setVisibility(View.GONE);
            System.arraycopy(teleData, 0, pacerDataPROGImp, 0, teleData[1] + 5);
            int TR2;
            waitTimer.cancel();
            TR2 = (teleData[3] - 6) * (teleData[3] - 6) / (246 - teleData[3]);

            if (TR2 > 1500) {
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal (Ohms) - Greater Than 1500", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                //showAlertMessage(ActivityProgrammer.this,"Impedance (Ohms)", "Greater Than 1500",true,false);
            } else if (TR2 < 250) {
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal (Ohms) - Less Than 250", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                //showAlertMessage(ActivityProgrammer.this,"Impedance (Ohms)", "Less Than 250",true,false);
            } else {
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal (Ohms) - " + TR2, CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                //showAlertMessage(ActivityProgrammer.this,"Impedance (Ohms)", String.valueOf(TR2),true,false);
            }
            mFunAlertAuto(ActivityProgrammer.this, "Message", "Impedance Ok");
            readImpedance(5);
            //updateViews(iPacerSelect);
        }
        //***************************************************
        //******************** Statistics Counts **************************
        // **** Pinnacle R 297 / Pinnacle AM 8820 Stat Counts ***
        else if (teleData[0] == 3 && teleData[1] == 0x12 && teleData[2] == 7) {

            if (!bStatSenCnt) {
                //Store data to decode/write in file
                pacerDataPROG[0] = teleData[1] - 1;
                pacerDataPROG[1] = teleData[1] - 1;
                System.arraycopy(teleData, 3, pacerDataPROG, 2, teleData[1] - 1);
                bStatSenCnt = true;
            } else {
                //Store data to decode/write in file
                pacerDataPROG[0] += teleData[1] - 1;
                pacerDataPROG[1] += teleData[1] - 1;
                System.arraycopy(teleData, 3, pacerDataPROG, 19, teleData[1] - 1);
                bStatSenCnt = false;
            }
        } else if (teleData[0] == 4 && teleData[1] == 0x13 && teleData[2] == 7) {
            //Store data to decode/write in file
            pacerDataPROG[0] += teleData[1] - 1;
            pacerDataPROG[1] += teleData[1] - 1;
            System.arraycopy(teleData, 3, pacerDataPROG, 36, teleData[1] - 1);

            CommonData.decode_stat_counts();
            bStatSenCnt = false;
            waitTimer.cancel();
            //statistics statClsInstance = statistics.getInstance();
            // statClsInstance.update_Counts();
            CommonData.logFileWrite(ActivityProgrammer.this, "Statistics Read OK", pacerDataPROG, CommonData.currModel);
            // Reload pacerdataProg
            System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
            Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
            // refreshScreen(CommonData.iPacerSelect);
            showlogpara();
            //updateViews(iPacerSelect);
            System.out.println("<><><>@@@&& 1");
            //Load index of Stat in Stat Array
            //statClsInstance.loadStatArray();
            // load curIndex with index showing stats
            //statClsInstance.curIndex=statClsInstance.statIndexArr.size()-1;

        }
        // **** Pinnacle 8820 Stat Counts ***
        else if (teleData[0] == 3 && teleData[1] == 0x0D && teleData[2] == 7) {
            if (!bStatSenCnt) {
                //Store data to decode/write in file
                pacerDataPROG[0] = teleData[1] - 1;
                pacerDataPROG[1] = teleData[1] - 1;
                System.arraycopy(teleData, 3, pacerDataPROG, 2, teleData[1] - 1);
                bStatSenCnt = true;
            } else {
                //Store data to decode/write in file
                pacerDataPROG[0] += teleData[1] - 1;
                pacerDataPROG[1] += teleData[1] - 1;
                System.arraycopy(teleData, 3, pacerDataPROG, 14, teleData[1] - 1);
                bStatSenCnt = false;
            }

        } else if (teleData[0] == 4 && teleData[1] == 0x0D && teleData[2] == 7) {
            //Store data to decode/write in file
            pacerDataPROG[0] += teleData[1] - 1;
            pacerDataPROG[1] += teleData[1] - 1;
            System.arraycopy(teleData, 3, pacerDataPROG, 26, teleData[1] - 1);
            CommonData.decode_stat_counts();
            bStatSenCnt = false;
            waitTimer.cancel();
            // statClsInstance = statistics.getInstance();
            //statClsInstance.update_Counts();
            CommonData.logFileWrite(ActivityProgrammer.this, "Statistics Read OK", pacerDataPROG, CommonData.currModel);
            Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
            System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
            //refreshScreen(CommonData.iPacerSelect);
            System.out.println("<><><>@@@&& 2");
            updateViews(iPacerSelect);
            //startActivity(new Intent(getApplicationContext(),statistics.class));
            //Load index of Stat in Stat Array
            //statClsInstance.loadStatArray();
            // load curIndex with index showing stats
            //statClsInstance.curIndex=statClsInstance.statIndexArr.size()-1;
        }
        //Charak DDDR Counts (Regular and AM/AF)
        else if (teleData[0] == 4 && teleData[1] == 0x1C && teleData[2] == 7) {
            //Store data to decode/write in file
            pacerDataPROG[0] += teleData[1] - 1;
            pacerDataPROG[1] += teleData[1] - 1;
            System.arraycopy(teleData, 3, pacerDataPROG, 2, teleData[1] - 1);

            CommonData.decode_stat_counts();
            bStatSenCnt = false;
            waitTimer.cancel();
            // statistics statClsInstance = statistics.getInstance();
            //statClsInstance.update_Counts_DDD();
            CommonData.logFileWrite(ActivityProgrammer.this, "Statistics Read OK", pacerDataPROG, CommonData.currModel);
            // Reload pacerdataProg
            System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);
            Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
            //  refreshScreen(CommonData.iPacerSelect);
            System.out.println("<><><>@@@&& 3");
            showlogparaDual();
            //updateViews(iPacerSelect);
            //Load index of Stat in Stat Array
            // statClsInstance.loadStatArray();
            // load curIndex with index showing stats
            // statClsInstance.curIndex=statClsInstance.statIndexArr.size()-1;
        }
        //New Models Counts (PRC)
        else if (teleData[0] == 4 && teleData[1] == 0x29 && teleData[2] == 7) {
            //Store data to decode/write in file
            pacerDataPROG[0] += teleData[1] - 1;
            pacerDataPROG[1] += teleData[1] - 1;
            System.arraycopy(teleData, 3, pacerDataPROG, 2, teleData[1] - 1);

            //CommonData.decode_stat_PScnts_N(CommonData.statChamber);
            if (CommonData.statChamber == 0) {
                CommonData.statChamber = 1;
                createRequest((byte) 7);
            } else {
                bStatSenCnt = false;
                ////**** waitTimer.cancel();
                //psCntFrag statClsInstancePS = psCntFrag.getInstance();
                //statClsInstancePS.updateCntsPS();
                ////  **** CommonData.logFileWrite("Statistics Read OK", CommonData.pacerDataPROG);
                // Reload pacerdataProg
                ////****System.arraycopy(CommonData.pacerData, 0, CommonData.pacerDataPROG, 0, CommonData.pacerData[1] + 5);
                Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
                ////***refreshScreen(CommonData.iPacerSelect);
                System.out.println("<><><>@@@&& 4");
                updateViews(iPacerSelect);
                //Load index of Stat in Stat Array
                ////***statClsInstance.loadStatArray();
                // load curIndex with index showing stats
                ////****statClsInstance.curIndex = statClsInstance.statIndexArr.size() - 1;
            }
        }
        // Ststistics Reset
        else if (teleData[0] == 4 && teleData[2] == 8) {
            CommonData.logFileWrite(ActivityProgrammer.this, "Statistics Reset OK", pacerDataPROG, CommonData.currModel);
            Toast.makeText(getApplicationContext(), "Statistics Reset OK", Toast.LENGTH_SHORT).show();
            //refreshScreen(CommonData.iPacerSelect);
            System.out.println("<><><>@@@&& 5");
            updateViews(iPacerSelect);
        }
        //**************************************************
        //**************************************
        //If marker OK
        else if (teleData[0] == 4 && teleData[2] == 0x11) {
            waitTimer.cancel();
            CommonData.logFileWrite(ActivityProgrammer.this, "Marker ON", CommonData.pacerData, CommonData.currModel); // Write Log File
            egmMarkerFragment.bFileWrite = true; // To write marker data in a file
            egmMarkerFragment.bMarkON = true;

            //refreshScreen(CommonData.iPacerSelect);
            updateViews(iPacerSelect);
            Toast.makeText(getApplicationContext(), "Marker ON ", Toast.LENGTH_SHORT).show();
        }
        //If marker OFF
        else if (teleData[0] == 4 && teleData[2] == 0x18) {
            waitTimer.cancel();
            CommonData.logFileWrite(ActivityProgrammer.this, "Marker OFF", CommonData.pacerData, CommonData.currModel); // Write Log File
            egmMarkerFragment.bFileWrite = false;
            egmMarkerFragment.bMarkON = false;

            //refreshScreen(CommonData.iPacerSelect);
            updateViews(iPacerSelect);
            Toast.makeText(getApplicationContext(), "Marker OFF ", Toast.LENGTH_SHORT).show();
        }
        //If Threshold start OK
        else if (teleData[0] == 4 && teleData[2] == 0x1B) {
            waitTimer.cancel();
            CommonData.logFileWrite(ActivityProgrammer.this, "Threshold ON", CommonData.pacerData, CommonData.currModel); // Write Log File
            egmMarkerFragment.bFileWrite = true; // To write threshold data in a file
            egmMarkerFragment.bMarkON = true;

            //refreshScreen(CommonData.iPacerSelect);
            updateViews(iPacerSelect);
            updateViews(iPacerSelect);
            Toast.makeText(getApplicationContext(), "Threshold ON ", Toast.LENGTH_SHORT).show();
            butThrNext.setEnabled(true);
        }
        //If 3  1  0x1B  then do nothing
        else if (teleData[0] == 3 && teleData[2] == 0x1B) {

            //Do nothing
        }
        //If Urgent Program OK
        else if (teleData[0] == 4 && teleData[2] == 0x0B) {
            CommonData.logFileWrite(ActivityProgrammer.this, "Urgent Programming OK", CommonData.pacerData, CommonData.currModel); // Write Log File
            Toast.makeText(getApplicationContext(), "Urgent Programming OK", Toast.LENGTH_SHORT).show();
            //mprogressbar.setVisibility(View.VISIBLE);
            CommonData.actionCode = 1;
            createRequest((byte) 1);
            timeOut(5000);
            waitTimer.start();
        }
        //To Confirm Reset
        else if (teleData[0] == 4 && teleData[2] == 0x0A) {
            bReset = true;
            showAlertMessage(ActivityProgrammer.this, "Pacemaker RESET", "Press OK to confirm pacemaker reset", true, true);

        }
        //To Reset
        else if (teleData[0] == 4 && teleData[2] == 0x09) {
            showAlertMessage(ActivityProgrammer.this, "Pacemaker RESET", "Pacemaker Reset OK", true, false);
            CommonData.logFileWrite(ActivityProgrammer.this, "Pacemaker Reset OK", CommonData.pacerData, CommonData.currModel); // Write Log File
            Toast.makeText(ActivityProgrammer.this, "Pacemaker Reset OK", Toast.LENGTH_SHORT).show();
            createRequest((byte) 0x01); //Interrogate pacemaker after Reset

        }
        // ECG Gain Up
        else if (teleData[0] == 4 && teleData[2] == 0x14) {
            CommonData.prevGainPos = teleData[3];
            CommonData.logFileWrite(ActivityProgrammer.this, "ECG Gain = " + CommonData.prevGainPos, CommonData.pacerData, CommonData.currModel); // Write Log File
            Toast.makeText(getApplicationContext(), "ECG Gain = " + CommonData.prevGainPos, Toast.LENGTH_SHORT).show();
            egmMarkerFragment.ecgSens = CommonData.prevGainPos * 30 + 20;
            egmMarkerFragment.ecgSens = CommonData.prevGainPos * 15 + 15;
        }
        //ECG Gain Down
        else if (teleData[0] == 4 && teleData[2] == 0x15) {
            CommonData.prevGainPos = teleData[3];
            CommonData.logFileWrite(ActivityProgrammer.this, "ECG Gain = " + CommonData.prevGainPos, CommonData.pacerData, CommonData.currModel); // Write Log File
            Toast.makeText(getApplicationContext(), "ECG Gain = " + CommonData.prevGainPos, Toast.LENGTH_SHORT).show();
            egmMarkerFragment.ecgSens = CommonData.prevGainPos * 30 + 20;
            egmMarkerFragment.ecgSens = CommonData.prevGainPos * 15 + 15;
        } else {

            mFunAlertAuto(ActivityProgrammer.this, "Alert", CommonData.strAction + " Error");
            //Toast.makeText(getApplicationContext(), CommonData.strAction + " Error", Toast.LENGTH_SHORT).show();
            bStatSenCnt = false;
            CommonData.bThrStop = false;
            //markClsInstance.bFileWrite=true;
            if (bMarkViewON) {
                //markClsInstance.bFileWrite=true;
                butMarker.setChecked(false);
                System.arraycopy(pacerData, 0, pacerDataPROG, 0, pacerData[1] + 5);//Restore CommonData
                butThrNext.setEnabled(false);
                egmMarkerFragment.bFileWrite = false;
                egmMarkerFragment.bMarkON = false;
                CommonData.decodeBytes();
            }
            if (CommonData.bStatiscticsON) {
                //statistics statClsInstance = statistics.getInstance();
                //statClsInstance.waitCirBar.setVisibility(View.INVISIBLE);
            }
            CommonData.logFileWrite(ActivityProgrammer.this, CommonData.strAction + " Error", CommonData.pacerData, CommonData.currModel); // Write Log File
            CommonData.strAction = "";

            //refreshScreen(CommonData.iPacerSelect);
            updateViews(iPacerSelect);
        }

        bDataReadOK = true;
    }

    private void impd_CPR() {
        int aImpVal = 0, vImpVal = 0;
        //'New Models
        if (teleData[1] == 3) {
            aImpVal = teleData[3];
            vImpVal = teleData[4];
            aImpVal = aImpVal * 2 - 150;
            vImpVal = vImpVal * 2 - 150;
        } else if (teleData[1] == 2) {
            vImpVal = teleData[3];
            vImpVal = vImpVal * 2 - 150;
        }

        if (aImpVal <= 0)
            aImpVal = 40;
        if (vImpVal <= 0)
            vImpVal = 40;

        if (aImpVal > 189)
            aImpVal = 189;
        if (vImpVal > 189)
            vImpVal = 189;

        if (aImpVal > 0)
            aImpVal = -(int) ((1 / log((double) aImpVal / 190)) * 180);
        if (vImpVal > 0)
            vImpVal = -(int) ((1 / log((double) vImpVal / 190)) * 180);

        System.arraycopy(teleData, 0, pacerDataPROGImp, 0, teleData[1] + 5);

//Show Message
        if (iPacerSelect == 0x10 || iPacerSelect == 0x13) //Pinn / PinnR
        {
            CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal = " + setStrImpVal(vImpVal) + " (Ohms)", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
            showAlertMessage(ActivityProgrammer.this, "Lead Impedance",
                    "Impedance = " + setStrImpVal(vImpVal) + " Ohms", true, false);
        } else { //Charak N
            if ((CommonData.iMode >= 4 && CommonData.iMode <= 7) || (CommonData.iMode >= 22 && CommonData.iMode <= 24)) //AAI Modes
            {
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal = " + setStrImpVal(vImpVal) + " (Ohms)", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                showAlertMessage(ActivityProgrammer.this, "Lead Impedance ",
                        "A Impedance = " + setStrImpVal(aImpVal) + " Ohms", true, false);
            } else if ((CommonData.iMode >= 0 && CommonData.iMode <= 3) || (CommonData.iMode >= 19 && CommonData.iMode <= 21)) //VVI Modes
            {
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal = " + setStrImpVal(vImpVal) + " (Ohms)", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                showAlertMessage(ActivityProgrammer.this, "Lead Impedance ",
                        "V Impedance = " + setStrImpVal(vImpVal) + " Ohms", true, false);
            } else if (CommonData.iMode == 9 || CommonData.iMode == 15) //VDD (R) Modes
            {
                CommonData.logFileWrite(ActivityProgrammer.this, "ImpedanceVal = " + setStrImpVal(vImpVal) + " (Ohms)", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                showAlertMessage(ActivityProgrammer.this, "Lead Impedance",
                        "V Impedance = " + setStrImpVal(vImpVal) + " Ohms", true, false);
            } else //DDD Modes
            {
                CommonData.logFileWrite(ActivityProgrammer.this, "A ImpedanceVal = " + setStrImpVal(aImpVal) + " (Ohms)", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                CommonData.logFileWrite(ActivityProgrammer.this, "V ImpedanceVal = " + setStrImpVal(vImpVal) + " (Ohms)", CommonData.pacerDataPROGImp, CommonData.currModel); // Write Log File
                showAlertMessage(ActivityProgrammer.this, "Lead Impedance ",
                        "A Impedance = " + setStrImpVal(aImpVal) + " Ohms" + '\n' +
                                "V Impedance = " + setStrImpVal(vImpVal) + " Ohms", true, false);
            }
        }
    }

    private String setStrImpVal(int val) {
        String strImp = "";
        if (val > 1500)
            strImp = ">> 1500";
        else if (val < 250)
            strImp = "<< 250";
        else
            strImp = String.valueOf(val);
        return strImp;
    }


    public static void createRequest(byte bt) {
        // int n;
        switch (bt) {
            case 1: //Interrogate
                interrogatePacer();
                break;
            case 2: //Request to send Pacer ID
                interrogateBT = new byte[]{2, 3, 39, 2, 3, 4, (byte) 155, (byte) 0xFF};
                break;
            case 6: //POR , Threshold Stop, Threshold Next
                if (CommonData.bThrStop) {
                    // CommonData.bThrStop=false;
                    interrogateBT = new byte[]{6, 3, 13, 16, 0, 0x77, 6, (byte) 0xFF}; // End of Threshold
                } else
                    interrogateBT = new byte[]{6, 3, 12, 0, 0, 52, 50, (byte) 0xFF};
                break;
            case 18: //POR2
                interrogateBT = new byte[]{18, 1, 11, (byte) 197, 99, (byte) 0xFF};
                break;
            case 4: //Program Request
                programPacer();
                break;
            case 0x64: //Impedance
                impedanceMeasure();
                break;
            case 0x07: // Statistics
                statRead();
                break;
            case 0x08: // Statistics Reset
                interrogateBT = new byte[]{0x08, 0x10, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0x55, 7, 0, 0x0A, 0, 0, 0};
                interrogateBT[7] = (byte) CommonData.iPacerID;
                interrogateBT[8] = (byte) iSrnoH;
                interrogateBT[9] = (byte) iSrnoL;
                break;
            case 0x11: //Marker
                markerStart();
                break;
            case 0x18://Stop Mareker
                interrogateBT = new byte[]{0x18, 3, 12, 0, 0, 0, 0, 0};
                break;
            case 0x1B: //Threshold Test Start
                if (!CommonData.bThrNext)
                    thrStart();
                break;
            case 0x0B: //Urgent Program
                interrogateBT = new byte[]{0x0B, 0x1E, 0x10, 0x4B, 1, 1, 1, (byte) (0xF4), 0x4C, 1, 1, 1, (byte) (0xF4), 0x36, 3, 1, 0x25, 0x43, 0x16, 5, 1, 6, 6,
                        0x1B, 0x1E, 1, 5, 1, 6, 0x13, 0, 0, 0, 0, 0};
                break;
            case 0x0A: //To Confirm Reset
                interrogateBT = new byte[]{0x0A, 0x0D, 0x27, 2, 3, 5, 1, (byte) CommonData.iPacerID, 1, 1, 1, (byte) (0xF4), 0x1C, 2, 0x0E, 0, 0, 0};

                break;
            case 0x09: //To Reset Pacemaker
                interrogateBT = new byte[]{0x09, 0x1C, 0x27, 3, 3, 5, 3, (byte) CommonData.iPacerID, (byte) iSrnoH, (byte) iSrnoL,
                        1, 1, 1, (byte) (0xF4), (byte) 0x97, 3, 1, 5, 1, (byte) 0x97, 1, 1, 1, (byte) (0xF4), (byte) 0x97, 3, 1, 5, 1, 6, 0, 0, 0};

                break;
            case 0x14: //ECG Gain UP
                interrogateBT = new byte[]{0x14, 0x02, 0x2C, (byte) 0xF1, 0, 0, 0};
                break;
            case 0x15: //ECG Gain Down
                interrogateBT = new byte[]{0x15, 0x02, 0x2C, (byte) 0xF2, 0, 0, 0};
                break;

            case (byte) 0xE4: //power on interface
                interrogateBT = new byte[]{(byte) 0xE4, 0x01, (byte) 0xE4, 0, 0, 0};
                break;
            default:
                break;
        }
        if (interrogateBT != null) {
            System.out.println("<><><>$$$$call   3");

            interrogateBT = CommonData.CRCCAL(interrogateBT);
            bytesToSend = new byte[interrogateBT[1] + 5];
            System.arraycopy(interrogateBT, 0, bytesToSend, 0, interrogateBT[1] + 5);
            //***** For Debug *****
            String strBytes = new String();
            strBytes = "";
            strBytes = String.valueOf(bytesToSend[1] + 5).toString() + "-";

            for (int p = 0; p < bytesToSend[1] + 5; p++) {
                strBytes = strBytes + " " + String.valueOf(0xFF & bytesToSend[p]).toString();
            }

            System.out.println("<><>@@@@ " + strBytes);

            System.out.println("<><><>$$$$call   4");
            dataTransferClass.write(bytesToSend);
            interrogateBT = null; // Clear Array
            bytesToSend = null; // Clear Array

        }

    }

    private static void interrogatePacer() {
        System.out.println("<>@@@ call");
        interrogateBT = new byte[]{1, 13, 39, 2, 3, 5, 1, 25, 1, 1, 1, (byte) (0xF4), (byte) 0x9C, 2, 14, 0, 0, 0, 0, 0, 0};
        interrogateBT[12] = (byte) 156; // Interrogate command
        //8820/TIRANGA
        if (CommonData.iPacerID == 0x0E)
            interrogateBT[14] = 9;  // no. of  bytes to receive
        // 'CHARAK  old BIVENT 'CHARAK35 AKASH AKASH35 HIRA
        if (CommonData.iPacerID == 6 || CommonData.iPacerID == 0x1A || CommonData.iPacerID == 7 || CommonData.iPacerID == 8
                || CommonData.iPacerID == 9)
            interrogateBT[14] = 12; // no. of  bytes to receive

        //' PINNAM
        if (CommonData.iPacerID == 0x2E)
            interrogateBT[14] = 14; // no. of  bytes to receive
        //CHARAK DR  CHARAK DR AM and AF
        if (CommonData.iPacerID == 12 || CommonData.iPacerID == 24) {
            interrogateBT[14] = 16; // no. of  bytes to receive
            interrogateBT[12] = 28; // Interrogate command
        }
        // Pinnacle-SSIR  Ventralite-R  Pinnacle-AM   Tiranga-AM  Hira-AM
        if (CommonData.iPacerID == 0x19 || CommonData.iPacerID == 0x0D || CommonData.iPacerID == 0x1B || CommonData.iPacerID == 0x1D || CommonData.iPacerID == 0x1E) {
            interrogateBT[14] = 14; // no. of  bytes to receive
            interrogateBT[12] = 28; // Interrogate command
        }

        //'*** NEW MODELS***
        //New Charak-DR-AM New Bivent-R AM  New SSIR  New SSI (C P R Series)
        if (CommonData.iPacerID == 0x1C || CommonData.iPacerID == 0x15 || CommonData.iPacerID == 0x13 || CommonData.iPacerID == 0x10) {
            interrogateBT[14] = 25; // no. of  bytes to receive
            interrogateBT[12] = 28; // Interrogate command
        }

        interrogateBT[7] = (byte) CommonData.iPacerID;

    }

    //Program Pacer
    private static void programPacer() {
        interrogateBT = new byte[]{4, 0x27, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0x63, 1, 7, 1, (byte) 0xF4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        interrogateBT[7] = (byte) CommonData.iPacerID;
        interrogateBT[8] = (byte) CommonData.iSrnoH;
        interrogateBT[9] = (byte) CommonData.iSrnoL;

        if (CommonData.iPacerSelect < 5) {
            interrogateBT[1] = 0x27;
            interrogateBT[16] = 7;
        }
        if (CommonData.iPacerSelect == 9) {
            interrogateBT[1] = 0x2C;
            interrogateBT[16] = 0x0C;
        }
        if (CommonData.iPacerSelect == 11) {
            interrogateBT[1] = 0x2A;
            interrogateBT[16] = 0x0A;
        }
        if (CommonData.iPacerSelect == 5 || CommonData.iPacerSelect == 6 || CommonData.iPacerSelect == 7 || CommonData.iPacerSelect == 8 || CommonData.iPacerSelect == 10) {
            interrogateBT[1] = 0x2B;
            interrogateBT[16] = 0x0B;
        }
        if (CommonData.iPacerSelect == 12) {
            interrogateBT[1] = 0x2F;
            interrogateBT[16] = 0x0F;
        }
        //'Charak DR AM and AF
        if (CommonData.iPacerSelect == 24) {
            interrogateBT[1] = 0x2F;
            interrogateBT[16] = 0x0F;
        }
        if (CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 0x0D || CommonData.iPacerSelect == 0x1B || CommonData.iPacerSelect == 0x1D || CommonData.iPacerSelect == 0x1E) {
            interrogateBT[1] = 0x2B;
            interrogateBT[16] = 0x0B;
        }
        //New Models
        if (CommonData.iPacerSelect == 0x1C || CommonData.iPacerSelect == 0x15 || CommonData.iPacerSelect == 0x13 || CommonData.iPacerSelect == 0x10) {
            interrogateBT[1] = 0x38;
            interrogateBT[16] = 0x18;
        }
        for (n = 19; n < (19 + interrogateBT[16]); n++) {
            interrogateBT[n] = (byte) (0xFF & CommonData.pacerDataPROG[n - 13]);
        }
        interrogateBT[n] = 3;
        interrogateBT[n + 1] = interrogateBT[16];
        interrogateBT[n + 2] = 4;
        interrogateBT[n + 3] = 0x11;
        interrogateBT[n + 4] = interrogateBT[16];
        interrogateBT[n + 5] = 1;
        interrogateBT[n + 6] = 1;
        interrogateBT[n + 7] = 1;
        interrogateBT[n + 8] = (byte) 0xF4;
        interrogateBT[n + 9] = 0x36;
        interrogateBT[n + 10] = 3;
        interrogateBT[n + 11] = 1;
        interrogateBT[n + 12] = 5;
        interrogateBT[n + 13] = 1;
        interrogateBT[n + 14] = 6;
    }

    // Impedance Measure
    private static void impedanceMeasure() {
        interrogateBT = new byte[]{0x64, 0, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, (byte) 0xAA, 3, 1, 5, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        interrogateBT[7] = (byte) CommonData.iPacerID;
        interrogateBT[8] = (byte) iSrnoH;
        interrogateBT[9] = (byte) iSrnoL;
        if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x1B || iPacerSelect == 0x1D
                || iPacerSelect == 0x1E) {
            interrogateBT[1] = 0x22;
            interrogateBT[20] = 1;
            interrogateBT[21] = 1;
            interrogateBT[22] = 1;
            interrogateBT[23] = (byte) 0xF4;
            interrogateBT[24] = 0x36;
            interrogateBT[25] = 0x0D;
            interrogateBT[26] = 0x10;
            interrogateBT[27] = (byte) 0x8A;
            interrogateBT[28] = 0x0D;
            interrogateBT[29] = 0x10;
            interrogateBT[30] = (byte) 0x8A;
            interrogateBT[31] = 0x0D;
            interrogateBT[32] = 0x10;
            interrogateBT[33] = 0x53;
            interrogateBT[34] = 2;
            interrogateBT[35] = 2;
        }
        //New Models Pinn/PinnR/Charak-DR/Bivent R (C P R Series)
        else if (iPacerSelect == 0x15 || iPacerSelect == 0x1C ||
                iPacerSelect == 0x13 || iPacerSelect == 0x10) {
            // **** To Do ****
            if ((CommonData.iMode >= 4 && CommonData.iMode <= 7) || (CommonData.iMode >= 22 && CommonData.iMode <= 24)) //AAI Mode
            {
                if (iPacerSelect == 0x13 || iPacerSelect == 0x10) //Pinn/PinnR
                {
                    interrogateBT[14] = (byte) 0x89;
                    interrogateBT[17] = (byte) 0x89;

                } else {
                    interrogateBT[14] = (byte) 0x8A;
                    interrogateBT[17] = (byte) 0x8A;
                }
                interrogateBT[15] = 0x0D;
                interrogateBT[16] = 0x10;
                interrogateBT[18] = 0x0D;
                interrogateBT[19] = 0x10;
                interrogateBT[20] = 0x53;
                interrogateBT[21] = 0x02;
                interrogateBT[22] = 0x01;
                interrogateBT[1] = 0x15;

            }
            //VVI VDD VDDR
            else if ((CommonData.iMode >= 0 && CommonData.iMode <= 3) || (CommonData.iMode >= 19 && CommonData.iMode <= 21) || CommonData.iMode == 9 || CommonData.iMode == 15) {
                interrogateBT[14] = (byte) 0x89;
                interrogateBT[15] = 0x0D;
                interrogateBT[16] = 0x10;
                interrogateBT[17] = (byte) 0x89;
                interrogateBT[18] = 0x0D;
                interrogateBT[19] = 0x10;
                interrogateBT[20] = (byte) 0x53;
                interrogateBT[21] = 0x02;
                interrogateBT[22] = 0x01;
                interrogateBT[1] = (byte) 0x15;

            } else {
                interrogateBT[14] = (byte) 0x8A;
                interrogateBT[15] = 0x0D;
                interrogateBT[16] = 0x10;
                interrogateBT[17] = (byte) 0x8A;
                interrogateBT[18] = 0x0D;
                interrogateBT[19] = 0x10;
                interrogateBT[20] = (byte) 0x89;
                interrogateBT[21] = 0x0D;
                interrogateBT[22] = 0x10;
                interrogateBT[23] = (byte) 0x89;
                interrogateBT[24] = (byte) 0x0D;
                interrogateBT[25] = 0x10;
                interrogateBT[26] = 0x53;
                interrogateBT[27] = 0x02;
                interrogateBT[28] = 0x02;
                interrogateBT[1] = (byte) 0x1B;
            }

        } else //if (CommonData.iPacerSelect <= 5 )
        {
            interrogateBT[1] = 0x1B;
            interrogateBT[20] = 0x10;
            interrogateBT[21] = 0x36;
            interrogateBT[22] = 9;
            interrogateBT[23] = 0;
            interrogateBT[24] = (byte) 0xA3;
            interrogateBT[25] = 0x10;
            interrogateBT[26] = 0x53;
            interrogateBT[27] = 2;
            interrogateBT[28] = 1;
            if (iPacerSelect == 12 || iPacerSelect == 24) //DDD (AM/AF)
                interrogateBT[28] = 2;
        }
    }

    // Statistics Read
    private static void statRead() {


        //If Charak (DDD)
        if (iPacerSelect == 12 || iPacerSelect == 24) {
            interrogateBT = new byte[]{0x07, 0, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0, 0, 0, 0, 0, 0};
            interrogateBT[7] = (byte) CommonData.iPacerID;
            interrogateBT[8] = (byte) iSrnoH;
            interrogateBT[9] = (byte) iSrnoL;
            interrogateBT[1] = 0x0F;
            interrogateBT[14] = (byte) 0xC9;
            interrogateBT[15] = 2;
            interrogateBT[16] = 0x1B;
        }

        //If Pinnacle (SSI)
        if (iPacerSelect < 5 || iPacerSelect == 9 || iPacerSelect == 11 || iPacerSelect == 25 || iPacerSelect == 13 ||
                iPacerSelect == 0x1B || iPacerSelect == 0x1D || iPacerSelect == 0x1E) {

            interrogateBT = new byte[]{0x07, 0, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0, 3, 1, 5, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            interrogateBT[7] = (byte) CommonData.iPacerID;
            interrogateBT[8] = (byte) iSrnoH;
            interrogateBT[9] = (byte) iSrnoL;

            interrogateBT[1] = 0x38;
            interrogateBT[11] = 4;
            interrogateBT[14] = 0x35;
            interrogateBT[15] = 0;

            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 11 || iPacerSelect == 0x1B ||
                    iPacerSelect == 0x1D || iPacerSelect == 0x1E) {
                interrogateBT[16] = 0x10;
                interrogateBT[17] = (byte) 0xF0;
            } else {
                interrogateBT[16] = 0x0B;
                interrogateBT[17] = (byte) 0xF5;
            }

            interrogateBT[18] = 2;

            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 11 || iPacerSelect == 0x1B ||
                    iPacerSelect == 0x1D || iPacerSelect == 0x1E) {
                interrogateBT[19] = 0x11;
            } else {
                interrogateBT[19] = 0x0C;
            }
            interrogateBT[20] = 0x0D;

            for (n = 2; n <= 14; n++) {
                interrogateBT[n + 19] = interrogateBT[n];
            }

            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 11 || iPacerSelect == 0x1B ||
                    iPacerSelect == 0x1D || iPacerSelect == 0x1E) {
                interrogateBT[34] = 0x11;
                interrogateBT[35] = 0x10;
                interrogateBT[36] = (byte) 0xDF;
            } else {
                interrogateBT[34] = 0x0C;
                interrogateBT[35] = 0x0B;
                interrogateBT[36] = (byte) 0xE9;
            }
            interrogateBT[37] = 2;

            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 11 || iPacerSelect == 0x1B ||
                    iPacerSelect == 0x1D || iPacerSelect == 0x1E) {
                interrogateBT[38] = 0x11;
            } else {
                interrogateBT[38] = 0x0C;
            }
            interrogateBT[39] = 0x0D;
            for (n = 2; n <= 14; n++) {
                interrogateBT[n + 38] = interrogateBT[n];
            }
            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 11 || iPacerSelect == 0x1B ||
                    iPacerSelect == 0x1D || iPacerSelect == 0x1E) {
                interrogateBT[53] = 0x22;
                interrogateBT[54] = 0x11;
                interrogateBT[55] = (byte) 0xCD;
            } else {
                interrogateBT[53] = 0x18;
                interrogateBT[54] = 0x0B;
                interrogateBT[55] = (byte) 0xDD;
            }

            interrogateBT[56] = 2;
            if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 11 || iPacerSelect == 0x1B ||
                    iPacerSelect == 0x1D || iPacerSelect == 0x1E) {
                interrogateBT[57] = 0x12;
            } else {
                interrogateBT[57] = 0x0C;
            }
        }
        //Statistics of New Models
        if (iPacerSelect == 0x10 || iPacerSelect == 0x13 || iPacerSelect == 0x1C) {
            interrogateBT = new byte[]{0x07, 0, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0, 0, 0, 0, 0, 0};
            if (CommonData.statTab == 0) {
                interrogateBT[0] = 0x7;
                interrogateBT[1] = 0x0F;
                interrogateBT[2] = 0x27;
                interrogateBT[3] = 0x03;
                interrogateBT[4] = 0x03;
                interrogateBT[5] = 0x05;
                interrogateBT[6] = 0x03;
                interrogateBT[7] = (byte) CommonData.iPacerID;
                interrogateBT[8] = (byte) iSrnoH;
                interrogateBT[9] = (byte) iSrnoL;
                interrogateBT[10] = 0x01;
                interrogateBT[11] = 0x01;
                interrogateBT[12] = 0x01;
                interrogateBT[13] = (byte) 0xF4;
                interrogateBT[15] = 0x02;
                interrogateBT[16] = 0x28;
                if (CommonData.statChamber == 0)
                    interrogateBT[14] = (byte) 0xC9; //For A Chamber
                if (CommonData.statChamber == 1)
                    interrogateBT[14] = (byte) 0xCA; //For V Chamber
            }
        }
    }

    //Marker Start
    private static void markerStart() {
        interrogateBT = new byte[]{0x11, 0, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0, 3, 1, 5, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        interrogateBT[1] = 0x0F;
        interrogateBT[7] = (byte) CommonData.iPacerID;
        interrogateBT[8] = (byte) iSrnoH;
        interrogateBT[9] = (byte) iSrnoL;

        if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x1B ||
                iPacerSelect == 0x1D || iPacerSelect == 0x1E) {
            interrogateBT[10] = 0x28;
            interrogateBT[11] = 0x10;
            interrogateBT[12] = 0x3C;
            interrogateBT[13] = 2;
            interrogateBT[14] = 1;
            interrogateBT[15] = 6;
            interrogateBT[16] = 9;
        } else {
            interrogateBT[1] = 0x18;
            interrogateBT[14] = 0x3C;
            interrogateBT[15] = 7;
            interrogateBT[16] = 0;
            interrogateBT[17] = 0x0A;
            interrogateBT[18] = 0x28;
            interrogateBT[19] = 8;

            //CharakDR, New charak ,bivent R models
            if (iPacerSelect == 12 || iPacerSelect == 24 || iPacerSelect == 0x15 ||
                    iPacerSelect == 0x1C || iPacerSelect == 0x13 || iPacerSelect == 0x10) {
                interrogateBT[20] = 4;
            } else {
                interrogateBT[20] = 3;
            }

            interrogateBT[21] = 9;
            interrogateBT[22] = 1;
            interrogateBT[23] = (byte) 0x89;
            interrogateBT[24] = 0x06;
            interrogateBT[25] = 0x11;
        }

    }

    //Threshold Start
    private static void thrStart() {
        interrogateBT = new byte[]{0x1B, 0, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0, 3, 1, 5, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        interrogateBT[0] = 0x1B;
        interrogateBT[2] = 0x27;
        interrogateBT[3] = 3;
        interrogateBT[4] = 3;
        interrogateBT[5] = 5;
        interrogateBT[6] = 3;
        interrogateBT[7] = (byte) CommonData.iPacerID;
        interrogateBT[8] = (byte) iSrnoH;
        interrogateBT[9] = (byte) iSrnoL;

        interrogateBT[10] = 1;
        interrogateBT[11] = 1;
        interrogateBT[12] = 1;
        interrogateBT[13] = (byte) (0xF4);
        interrogateBT[14] = 0x74;

        if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x1B || iPacerSelect == 0x1D ||
                iPacerSelect == 0x1E) {
            interrogateBT[14] = 0x5A;
        }

        interrogateBT[15] = 1;
        interrogateBT[17] = 1;
        interrogateBT[18] = (byte) 0xF4;

        if (iPacerSelect < 5) {
            interrogateBT[1] = 0x38;
            interrogateBT[16] = 7;
        } else if (iPacerSelect == 9) {
            interrogateBT[1] = 0x3D;
            interrogateBT[16] = 0x0C;
        } else if (iPacerSelect == 11) {
            interrogateBT[1] = 0x3B;
            interrogateBT[16] = 0x0A;
        } else if (iPacerSelect == 12 || iPacerSelect == 24) {
            interrogateBT[1] = 0x39;
            interrogateBT[16] = 0x0F;
        } else if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x1B || iPacerSelect == 0x1D ||
                iPacerSelect == 0x1E) {
            interrogateBT[1] = 0x36;
            interrogateBT[16] = 0x0B;
        } else if (iPacerSelect == 0x15 || iPacerSelect == 0x1C || iPacerSelect == 0x13 || iPacerSelect == 0x10)  //New Models
        {
            interrogateBT[1] = 0x39;
            interrogateBT[16] = 0x18;
        }

        for (n = 19; n < (19 + interrogateBT[16]); n++) {
            interrogateBT[n] = (byte) (0xFF & pacerDataPROG[n - 13]);

        }

        interrogateBT[n] = 3;
        interrogateBT[n + 1] = interrogateBT[16];
        interrogateBT[n + 2] = 4;
        interrogateBT[n + 3] = 0x11;
        interrogateBT[n + 4] = interrogateBT[16];
        interrogateBT[n + 5] = 1;
        interrogateBT[n + 6] = 1;
        interrogateBT[n + 7] = 1;
        interrogateBT[n + 8] = (byte) 0xF4;
        interrogateBT[n + 9] = 0x36;
        interrogateBT[n + 10] = 3;
        interrogateBT[n + 11] = 1;
        interrogateBT[n + 12] = 5;
        interrogateBT[n + 13] = 1;
        interrogateBT[n + 14] = 6;
        interrogateBT[n + 15] = 0x10;
        interrogateBT[n + 16] = (byte) 0x8B;

        if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x1B || iPacerSelect == 0x1D ||
                iPacerSelect == 0x1E) {
            interrogateBT[n + 16] = 0x5B;
            interrogateBT[n + 17] = 2;
            interrogateBT[n + 18] = 1;
            interrogateBT[n + 19] = 0x28;
            interrogateBT[n + 20] = 0x10;
            interrogateBT[n + 21] = 0x5B;
            interrogateBT[n + 22] = 2;
            interrogateBT[n + 23] = 1;
            interrogateBT[n + 24] = 6;
            interrogateBT[n + 25] = (byte) (n + 18);
        } else if (iPacerSelect == 12 || iPacerSelect == 24) {
            interrogateBT[n + 15] = 0x28;
            interrogateBT[n + 16] = 0x10;
            interrogateBT[n + 17] = (byte) 0x8B;
            interrogateBT[n + 18] = 3;
            interrogateBT[n + 19] = 1;
            interrogateBT[n + 20] = 5;
            interrogateBT[n + 21] = 1;
            interrogateBT[n + 22] = 6;
            interrogateBT[n + 23] = 6;
            interrogateBT[n + 24] = (byte) (n + 14);
            interrogateBT[1] = (byte) (n + 23);
        } else if (iPacerSelect == 0x15 || iPacerSelect == 0x1C || iPacerSelect == 0x13 || iPacerSelect == 0x10) {
            //New Models
            interrogateBT[n] = 3;
            interrogateBT[n + 1] = interrogateBT[16];
            interrogateBT[n + 2] = 4;
            interrogateBT[n + 3] = 0x11;
            interrogateBT[n + 4] = interrogateBT[16];
            interrogateBT[n + 5] = 0x10;
            interrogateBT[n + 7] = 3;
            interrogateBT[n + 8] = 1;
            interrogateBT[n + 9] = 5;
            interrogateBT[n + 10] = 1;
            interrogateBT[n + 11] = 6;
            interrogateBT[n + 12] = 0x28;
            interrogateBT[n + 13] = 0x10;
            interrogateBT[n + 14] = (byte) 0x8B;
            interrogateBT[n + 15] = 2;
            interrogateBT[n + 16] = 1;
            interrogateBT[n + 17] = 6;
            interrogateBT[n + 18] = (byte) (n + 11);
            interrogateBT[1] = (byte) (n + 17);

            //Check channel A or V
            //if (markClsInstance.bVPThr || markClsInstance.bVSThr)
            //   interrogateBT[n+6]=0x36;
            //else if (markClsInstance.bAPThr || markClsInstance.bASThr)
            //  interrogateBT[n+6]=0x35;
            //else
            //  interrogateBT[n+6]=0x36; //Default for V
            //Default For V
            interrogateBT[n + 6] = 0x36;
            if (bThrChamber == false)
                interrogateBT[n + 6] = 0x35;
        } else {
            interrogateBT[n + 17] = 3;
            interrogateBT[n + 18] = 1;
            interrogateBT[n + 19] = 5;
            interrogateBT[n + 20] = 1;
            interrogateBT[n + 21] = 6;
            interrogateBT[n + 22] = 0x28;
            interrogateBT[n + 23] = 0x10;
            interrogateBT[n + 24] = (byte) 0x8B;
            interrogateBT[n + 25] = 3;
            interrogateBT[n + 26] = 1;
            interrogateBT[n + 27] = 5;
            interrogateBT[n + 28] = 1;
            interrogateBT[n + 29] = 6;
            interrogateBT[n + 30] = 6;
            interrogateBT[n + 31] = (byte) (n + 21);
        }
    }

    //Threshold Next
    private void thresholdNext() {
        int temp2;
        interrogateBT = new byte[]{0x1B, 0, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0, 3, 1, 5, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        //***** if 8820 / Pinn R *****
        if (iPacerSelect < 5 || iPacerSelect == 9 || iPacerSelect == 11 || iPacerSelect == 25 ||
                iPacerSelect == 13 || iPacerSelect == 0x1B || iPacerSelect == 0x1D ||
                iPacerSelect == 0x1E) {
            if (bVPThr) {
                if (CommonData.iAmp > 0) {
                    CommonData.iAmp -= 1;
                    if (iPacerSelect == 25 || iPacerSelect == 0x1B) {
                        //'Hyst On/OFF + ARC + AMP
                        pacerDataPROG[8] &= 0xC0;
                        pacerDataPROG[8] += ampctrlWD297[CommonData.iAmp];
                    } else {
                        //'S POL + P POL + AMP
                        pacerDataPROG[8] &= 0xC0;
                        pacerDataPROG[8] += CommonData.ampctrlWD[CommonData.iAmp];

                    }
                    // debugmarkClsInstance.updateMarkAnnotation();
                } else {
                    //Toast.makeText(mainActInstance.getApplicationContext(), "End of CommonDatameter Limit", Toast.LENGTH_SHORT).show();
                    CommonData.bThrLimit = true;
                }
            }
            if (bVPWThr) {
                if (iPW > 0) {
                    iPW -= 1;
                    if (iPacerSelect == 25 || iPacerSelect == 0x1B) {
                        //P pol +S pol + PW
                        pacerDataPROG[7] &= 0xC0;
                        pacerDataPROG[7] += CommonData.pwCtrlWD297[iPW];
                    } else {
                        // PW
                        pacerDataPROG[7] = CommonData.pwCtrlWD[iPW];
                    }
                    // debug markClsInstance.updateMarkAnnotation();
                } else {
                    //Toast.makeText(mainActInstance.getApplicationContext(), "End of CommonDatameter Limit", Toast.LENGTH_SHORT).show();
                    CommonData.bThrLimit = true;
                }

            }
            if (bVSThr) {
                if (CommonData.iSen > 0) {
                    CommonData.iSen -= 1;
                    if (iPacerSelect == 25 || iPacerSelect == 0x1B) {
                        // Sensitivity AM MRI
                        pacerDataPROG[11] &= 0xC0;
                        pacerDataPROG[11] += CommonData.iSen;
                    } else {
                        // Sensitivity  REF
                        pacerDataPROG[10] &= 0x1F;
                        pacerDataPROG[10] += CommonData.senCtrlWD[CommonData.iSen];
                    }
                    // debug  markClsInstance.updateMarkAnnotation();
                } else {
                    //Toast.makeText(mainActInstance.getApplicationContext(), "End of CommonDatameter Limit", Toast.LENGTH_SHORT).show();
                    CommonData.bThrLimit = true;
                }
            }
        }
        // '********** if charak DR , New charak , New Bivent*************

        if (iPacerSelect == 12 || iPacerSelect == 24 || iPacerSelect == 0x15 || iPacerSelect == 0x1C ||
                iPacerSelect == 0x13 || iPacerSelect == 0x10) {

            if (bVSThr == true) // if Sens Thr
            {
                if (bThrChamber == true) // 'V SEN
                {

                    if (CommonData.iSen <= 0)
                        CommonData.bThrLimit = true;
                    else {
                        CommonData.iSen -= 1;
                        // debug   markClsInstance.updateMarkAnnotation();
                    }
                } else  // 'A SEN
                {
                    if (iASen <= 0)
                        CommonData.bThrLimit = true;
                    else {
                        iASen -= 1;
                        // debug  markClsInstance.updateMarkAnnotation();
                    }
                }
            } else if (bVPWThr == true) {
                if (bThrChamber == true) // 'VPW
                {
                    if (iPW <= 0)
                        CommonData.bThrLimit = true;
                    else {
                        iPW -= 1;
                        // debug markClsInstance.updateMarkAnnotation();
                    }
                } else // 'APW
                {
                    if (iAPW <= 0)
                        CommonData.bThrLimit = true;
                    else {
                        iAPW -= 1;
                        // debug  markClsInstance.updateMarkAnnotation();
                    }
                }
            } else if (bVPThr == true) {
                // 'A AMP
                if (bThrChamber == false) {
                    if (iAAmp <= 0)
                        CommonData.bThrLimit = true;
                    else {
                        iAAmp -= 1;
                        // debug  markClsInstance.updateMarkAnnotation();
                    }
                } else // 'V AMP
                {
                    if (CommonData.iAmp <= 0)
                        CommonData.bThrLimit = true;
                    else {
                        CommonData.iAmp -= 1;
                        // debug markClsInstance.updateMarkAnnotation();
                    }
                }
            }

        }
        //   '********* End of Charak DR and New Models**************


        //Create data to send
        interrogateBT[0] = 0x1B;
        interrogateBT[1] = 0x1B;
        interrogateBT[2] = 0x10;
        interrogateBT[3] = 0x2D;
        interrogateBT[4] = 1;
        interrogateBT[5] = 1;
        interrogateBT[6] = 1;
        interrogateBT[7] = (byte) 0xF4;
        interrogateBT[9] = 1;
        interrogateBT[10] = 1;
        interrogateBT[11] = 1;
        interrogateBT[12] = (byte) 0xF4;
        interrogateBT[14] = 3;
        interrogateBT[15] = 1;
        interrogateBT[16] = 5;
        interrogateBT[17] = 1;
        interrogateBT[18] = 6;
        interrogateBT[19] = 0x28;
        interrogateBT[20] = 0x10;
        interrogateBT[21] = (byte) 0x8B;
        interrogateBT[22] = 3;
        interrogateBT[23] = 1;
        interrogateBT[24] = 5;
        interrogateBT[25] = 1;
        interrogateBT[26] = 6;
        interrogateBT[27] = 6;
        interrogateBT[28] = 0x12;


        // For old 8820
        temp2 = pacerDataPROG[8] & 0x3F;// Fam.sa(bam).Tag And &H3F
        interrogateBT[8] = (byte) temp2;
        interrogateBT[13] = (byte) (0xD3 - temp2);
        if (bVPWThr) {
            interrogateBT[3] = 0x17;
            interrogateBT[8] = (byte) pacerDataPROG[7];
            interrogateBT[13] = (byte) (0xE9 - interrogateBT[8]);
        } else if (bVSThr) {
            interrogateBT[3] = 0x1A;
            interrogateBT[8] = (byte) (pacerDataPROG[10] / 32);
            interrogateBT[13] = (byte) (0xE6 - interrogateBT[8]);
        }

        //For 297 8820 AM
        if (iPacerSelect == 25 || iPacerSelect == 13 || iPacerSelect == 0x1B || iPacerSelect == 0x1D ||
                iPacerSelect == 0x1E) {
            interrogateBT[0] = 0x1B;
            interrogateBT[1] = 0x11;
            interrogateBT[2] = 0x0D;
            interrogateBT[3] = 0x10;
            interrogateBT[4] = 0x2D;
            interrogateBT[11] = (byte) (pacerDataPROG[8] & 0x3F);// wd(21) And & H3F


            if (bVPWThr) {

                interrogateBT[4] = 0x17;
                interrogateBT[11] = (byte) pacerDataPROG[7];

            }

            if (bVSThr) {
                interrogateBT[4] = 0x1A;
                interrogateBT[11] = (byte) pacerDataPROG[11];

            }

            interrogateBT[5] = 2;
            interrogateBT[6] = 1;
            interrogateBT[7] = 1;
            interrogateBT[8] = 1;
            interrogateBT[9] = 1;
            interrogateBT[10] = (byte) 0xF4;
            interrogateBT[12] = 0x28;
            interrogateBT[13] = 0x10;
            interrogateBT[14] = 0x5B;
            interrogateBT[15] = 2;
            interrogateBT[16] = 1;
            interrogateBT[17] = 6;
            interrogateBT[18] = 0x0B;
        }
        // For Charak
        if (iPacerSelect == 12 || iPacerSelect == 24) {

            if (CommonData.iAmp > 31)
                temp2 = (CommonData.iAmp - 31) * 3 + CommonData.iAmp;
            else
                temp2 = CommonData.iAmp;

            //22/04/23 temp2 = CommonData.ampctrlWD297[temp2] & 0x3F;
            temp2 = CommonData.ampctrlWD[temp2] & 0x3F; //22/04/23
            temp2 = temp2 | 0x80; // temp2,7 = 1 For V Amp

            if (bThrChamber == false) // For A Amp
            {
                if (iAAmp > 31)
                    temp2 = (iAAmp - 31) * 3 + iAAmp;
                else
                    temp2 = iAAmp;

                //22/04/23 temp2 = CommonData.ampctrlWD297[temp2] & 0x3F;
                temp2 = CommonData.ampctrlWD[temp2] & 0x3F;
            }
            interrogateBT[8] = (byte) temp2;
            interrogateBT[13] = (byte) (0xD3 - temp2);

            //if PW Selected
            if (bVPWThr == true) {

                if (bThrChamber == false) //if A chamber (APW)
                {
                    temp2 = iAPW;
                } else //if V chamber (VPW)
                {
                    temp2 = (iPW | 0x80); //7th bit=1 for V
                }
                interrogateBT[3] = 0x17;
                interrogateBT[8] = (byte) temp2;
                interrogateBT[13] = (byte) (0xE9 - temp2);
            }

            // if Sens Selected
            if (bVSThr == true) {

                if (bThrChamber == false) { //if A sens
                    temp2 = iASen;
                } else //V Sen
                {
                    temp2 = CommonData.iSen | 0x80; //7th bit =1 for V
                }
                interrogateBT[3] = 0x1A;
                interrogateBT[8] = (byte) temp2;
                interrogateBT[13] = (byte) (0xE6 - temp2);
            }
        }// End of Charak
        //*************** New Models ***************
        if (iPacerSelect == 0x10 || iPacerSelect == 0x13 || iPacerSelect == 0x1C) {
            if (bThrChamber == false) //A Amp
            {
                temp2 = iAAmp;
            } else //V Amp
            {
                temp2 = CommonData.iAmp;
                temp2 |= 0x80; //set bit 7 for V Amp
            }
            interrogateBT[8] = (byte) temp2;
            interrogateBT[13] = (byte) (0xD3 - temp2);

            if (bVPWThr) {
                interrogateBT[3] = 0x17;
                if (bThrChamber == false || CommonData.iMode == 4) // A PW
                {
                    if (iPacerSelect == 0x10 || iPacerSelect == 0x13) { //New Pinn PinnR
                        temp2 = iPW;
                        temp2 |= 0x80;
                    } else // New Charak
                    {
                        temp2 = iAPW;

                    }

                } else if (bThrChamber || CommonData.iMode == 0) // V PW
                {
                    temp2 = iPW;
                    temp2 |= 0x80; //Set bit 7 for V
                }
                interrogateBT[8] = (byte) temp2;
                interrogateBT[13] = (byte) (0xE9 - temp2);
            } else if (bVSThr) //Sens Thr
            {
                interrogateBT[3] = (byte) 0x1A;
                if (bThrChamber == false || CommonData.iMode == 4) //V
                {
                    temp2 = iASen;
                } else if (bThrChamber == true || CommonData.iMode == 0) //V
                {
                    temp2 = CommonData.iSen;
                    temp2 |= 0x80; //set bit 7 for V
                }
                interrogateBT[8] = (byte) temp2;
                interrogateBT[13] = (byte) (0xE6 - temp2);
            }
            interrogateBT[21] = (byte) 0x8B;
            interrogateBT[22] = 0x02;
            interrogateBT[23] = 0x01;
            interrogateBT[24] = 0x06;
            interrogateBT[25] = 0x12;
            interrogateBT[1] = 0x18;
        }

        //**************** End New Models *************************************
        if (CommonData.bThrLimit) {
            CommonData.bThrLimit = false;
            Toast.makeText(ActivityProgrammer.this, "End of CommonDatameter Limit", Toast.LENGTH_SHORT).show();
            stopMarker();
        } else {
            interrogateBT = CommonData.CRCCAL(interrogateBT);
            bytesToSend = new byte[interrogateBT[1] + 5];
            System.arraycopy(interrogateBT, 0, bytesToSend, 0, interrogateBT[1] + 5);
            dataTransferClass.write(bytesToSend);
            Toast.makeText(getApplicationContext(), "Threshold Next", Toast.LENGTH_SHORT).show();
        }
        interrogateBT = null; //Clear Array
        bytesToSend = null; //Clear Array

    }


    //Decode para of Pinnacle 8820 AM
    private static void decode8820AM() {
//1st BYTE
        //Decode Mode

        mTxtDeviceName.setText(" Pinnacle 8820AM ");
        mTxtDeviceNumber.setText("# " + iSrno);
        CommonData.currModel = "Pinnacle8820";
        CommonData.logFileWrite(activity, "Decode Pinnacle 8820AM", CommonData.pacerData, CommonData.currModel);
        mTxtImpLastValA.setVisibility(View.GONE);
        mTxtImpTodayValA.setVisibility(View.GONE);
        mTxtImpTitleA.setVisibility(View.GONE);
        mTxtImpTitleV.setVisibility(View.GONE);
        System.out.println("<><><>ipace   " + iPacerSelect);

     /*   for (int i=0;i<(modeCtrlWD297.length) ;i++)
        {
            if (modeCtrlWD297[i]==(pacerData[6] & 0xF8))
            {
                iMode=i;
                tMode=iMode;
                break;
                //i=modeCtrlWD297.length;
            }
        }*/

        List<Integer> modeList = new ArrayList<>(map8820.values());
        iMode = modeList.indexOf((pacerData[6] & 0xF8));
        tMode = iMode;

        //7th Byte
        //Decode Rate
        iRate = pacerData[12];
        tRate = iRate;

        List<String> modeKeyList = new ArrayList<>(map8820.keySet());
        modeSpinnerArrayAdapter = new ArrayAdapter<String>(activity, R.layout.dropdown_item, modeKeyList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (CommonData.iMode == position)
                    tv.setTextColor(Color.BLACK);
                else
                    tv.setTextColor(Color.BLUE);

                return view;
            }

            @NonNull
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if (position == CommonData.iMode)
                    tv.setTextColor(Color.BLACK);
                else
                    tv.setTextColor(Color.BLUE);


                return view;
            }
        };
        modeSpinnerArrayAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        spinMode.setAdapter(modeSpinnerArrayAdapter);
        spinMode.setSelection(iMode);

        rateAdapter = new ArrayAdapter<Integer>(activity, R.layout.dropdown_item, CommonData.rateArray) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (CommonData.iRate == position)
                    tv.setTextColor(Color.BLACK);
                else
                    tv.setTextColor(Color.BLUE);

                return view;
            }

            @NonNull
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                int iColor = Color.BLUE;
                //Check para selection is valid

                if (position == 44 && CommonData.tRef == 20) //Chk with Ref
                {
                    if (CommonData.tMode != 1 && CommonData.tMode != 5) //if not AOO VOO
                        iColor = Color.RED;
                }
                if ((CommonData.rateArray[position] - CommonData.hystArray297_int[CommonData.thystVAL] < 32) && CommonData.bHystON) {
                    if (CommonData.tMode == 0 || CommonData.tMode == 2 || CommonData.tMode == 4 || CommonData.tMode == 6) //VVI VVT AAI AAT
                        iColor = Color.RED;

                }
                if (CommonData.tMode == 2 || CommonData.tMode == 6) // SST mode then  chk with upr rate
                {
                    if (CommonData.rateArray[position] >= CommonData.uprArray297[CommonData.tTrigUprRate])
                        iColor = Color.RED;
                }
                if (position == CommonData.iRate)
                    iColor = Color.BLACK;

                // Set text Color
                tv.setTextColor(iColor);

                return view;
            }

        };
        rateAdapter.setDropDownViewResource(R.layout.dropdown_view_item);
        spinRate.setAdapter(rateAdapter);
        spinRate.setSelection(iRate);

        //Decode Up Time
        iuptime = pacerData[6] & 7;
        CommonData.tuptime = iuptime;
//2nd Byte
        //Decode PW
        for (int i = 0; i < (CommonData.pwCtrlWD.length); i++) {
            if (CommonData.pwCtrlWD[i] == (pacerData[7] & 0x3F)) {
                iPW = i;
                CommonData.tPW = iPW;
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

        CommonData.tbARC = bARC;
        //HYST ON/OFF
        bHystON = (pacerData[8] & 0x80) == 0x80;

        CommonData.tbHystON = bHystON;
//4th Byte
        //Hyst Value
        ihystVAL = pacerData[9] & 0x1F;
        CommonData.thystVAL = ihystVAL;
//5th Byte
        // Decode Refrectory
        iRef = pacerData[10] & 0x1F;
        CommonData.tRef = iRef;
        //Hyst Search ON/OFF
        bHystSrchON = (pacerData[10] & 0x20) == 0x20;

        tbHystSrchON = bHystSrchON;
//6th Byte
        //Decode Sensitivity
        iSen = pacerData[11] & 0x0F;
        CommonData.tSen = iSen;
        System.out.println("<><>isen   " + iSen);
        //AM and MRI
        bAutoMsr = (pacerData[11] & 0x80) == 0x80;

        CommonData.tbAutoMsr = bAutoMsr;
        bMRI = (pacerData[11] & 0x40) == 0x40;

        CommonData.tbMRI = bMRI;

//8th Byte
        //Decode Trigger Upper Rate (T modes)
        iTrigUprRate = pacerData[13] - 24;
        CommonData.tTrigUprRate = iTrigUprRate;
//9th Byte
        //Decode Target Upper Rate (R modes)
        iSensorRate = pacerData[14] - 24;
        CommonData.tSensorRate = iSensorRate;
//10th Byte
        //Decode Slope
        for (int i = 0; i < 16; i++) {
            if (pacerData[15] == CommonData.slpCtrlWD[i]) {
                iSlope = i;
                break;
            }
        }
        CommonData.tSlope = iSlope;
//11th Byte
        //Decode Down Time
        iDownTime = pacerData[16];
        CommonData.tDownTime = iDownTime;
//12th Byte
        //Show Battery
        iBat = pacerData[17];

//13th Byte
        //Decode Magnet, Noise
        CommonData.iNoise = pacerData[18] & 1;
        CommonData.iMagnet = pacerData[18] & 8;
    }

    private static void decode8820() {
        mTxtDeviceName.setText(" Pinnacle 8820AM ");
        mTxtDeviceNumber.setText("# " + iSrno);
        CommonData.currModel = "Pinnacle8820";
        CommonData.logFileWrite(activity, "Decode Pinnacle 8820AM", CommonData.pacerData, CommonData.currModel);
        mTxtImpLastValA.setVisibility(View.GONE);
        mTxtImpTodayValA.setVisibility(View.GONE);
        mTxtImpTitleA.setVisibility(View.GONE);
        mTxtImpTitleV.setVisibility(View.GONE);
        List<Integer> modeList = new ArrayList<>(map8820.values());
        iMode = modeList.indexOf((pacerData[6]));
        tMode = iMode;
        //Decode Up Time
        iuptime = pacerData[6] & 7; //uptime
        CommonData.tuptime = iuptime;

        iRate = (pacerData[11] / 2) - 1;
        tRate = iRate;

        modeSpinnerArrayAdapter = funSetArrayAdapterString(activity, new ArrayList<>(map8820.keySet()), iMode, (byte) 0);
        spinMode.setAdapter(modeSpinnerArrayAdapter);
        spinMode.setSelection(iMode);

        rateAdapter = funSetArrayAdapterInteger(3, activity, Arrays.asList(rateArrayP), iRate, (byte) 'R');
        spinRate.setAdapter(rateAdapter);
        spinRate.setSelection(iRate);


        List<Integer> valuesPW297 = new ArrayList<>(mapPW8820.values());
        iPW = valuesPW297.indexOf((pacerData[7]));

        CommonData.tPW = iPW;
        //Decode Polarity
        iPacePol = 0;
        iSenPol = 0;
        if ((pacerData[7] & 0x40) == 0x40)
            iPacePol = 1;
        if ((pacerData[7] & 0x80) == 0x80)
            iSenPol = 1;

        tPacePol = iPacePol;
        tSenPol = iSenPol;

        List<Integer> valuesAMP297 = new ArrayList<>(mapAMP297.values());
        iAmp = valuesAMP297.indexOf((pacerData[8] & 0x3F));
        tAmp = iAmp;
        // Decode ARC
        bARC = (pacerData[8] & 0x40) == 0x40;
        CommonData.tbARC = bARC;
        //HYST ON/OFF
        bHystON = (pacerData[8] & 0x80) == 0x80;
        CommonData.tbHystON = bHystON;

        // Decode Refrectory
        iRef = pacerData[10] & 0x1F;
        CommonData.tRef = iRef;
        //Hyst Search ON/OFF
        bHystSrchON = (pacerData[10] & 0x20) == 0x20;
        CommonData.tbHystSrchON = bHystSrchON;
//6th Byte
        //Decode Sensitivity
        CommonData.iSen = pacerData[11] & 0x0F;
        //AM and MRI
        bAutoMsr = (pacerData[11] & 0x80) == 0x80;
        System.out.println("<><><><>## " + bAutoMsr);
        CommonData.tbAutoMsr = bAutoMsr;

        bMRI = (pacerData[11] & 0x40) == 0x40;
        CommonData.tbMRI = bMRI;

        //8th Byte
        //Decode Trigger Upper Rate (T modes)
        iTrigUprRate = pacerData[13] - 24;
        CommonData.tTrigUprRate = iTrigUprRate;
//9th Byte
        //Decode Target Upper Rate (R modes)
        iSensorRate = pacerData[14] - 24;
        CommonData.tSensorRate = iSensorRate;


        //Hyst Value
        ihystVAL = pacerData[9] & 0x1F;
        CommonData.thystVAL = ihystVAL;

        //Hyst Search ON/OFF
        bHystSrchON = (pacerData[10] & 0x20) == 0x20;
        tbHystSrchON = bHystSrchON;

        // Decode ARC
        bARC = (pacerData[8] & 0x40) == 0x40;
        CommonData.tbARC = bARC;

        List<Integer> valuesSlope297 = new ArrayList<>(mapSlope297.values());
        iSlope = valuesSlope297.indexOf(pacerData[15]);

        //Decode Down Time
        iDownTime = pacerData[16];////down tme
        CommonData.tDownTime = iDownTime;

        //Decode Target Upper Rate (R modes)
        iSensorRate = pacerData[14] - 24;
        CommonData.tSensorRate = iSensorRate;

        iuptime = pacerData[6] & 7; //uptime
        CommonData.tuptime = iuptime;


        /*if (flagMainFragment == 4 && flagSubFragment == 1) {
            FragmentParaVVIR.spinPw.setSelection(iPW);
            FragmentParaVVIR.spinAmp.setSelection(iAmp);
            FragmentParaVVIR.spinSens.setSelection(iSen);
            FragmentParaVVIR.spinRef.setSelection(iRef);
        }*/

        if (CommonData.iNoise == 1) {
            mFunAlert(activity, "Message", "Noise Detected");
            CommonData.iNoise = 0;
            //Snackbar.make(getView(),"Noise Detected",Snackbar.LENGTH_SHORT).show();
        }
        if (CommonData.iMagnet == 8) {
            mFunAlert(activity, "Message", "Magnet Switch ON");
            CommonData.iMagnet = 0;
            //Snackbar.make(getView(),"Noise Detected",Snackbar.LENGTH_SHORT).show();
        }


    }

    public static void decode297() {
        mTxtDeviceName.setText(" Pinnacle R+ 297 ");
        mTxtDeviceNumber.setText("# " + iSrno);
        CommonData.currModel = "Pinnacle297";
        CommonData.logFileWrite(activity, "Decode Pinnacle 297", CommonData.pacerData, CommonData.currModel);
        mTxtImpLastValA.setVisibility(View.GONE);
        mTxtImpTodayValA.setVisibility(View.GONE);
        mTxtImpTitleA.setVisibility(View.GONE);
        mTxtImpTitleV.setVisibility(View.GONE);
        List<Integer> modeList = new ArrayList<>(map297_8820.values());
        iMode = modeList.indexOf((pacerData[6] & 0xF8));
        tMode = iMode;
        //Decode Up Time
        iuptime = pacerData[6] & 7; //uptime
        CommonData.tuptime = iuptime;

        iRate = pacerData[12];
        tRate = iRate;

        modeSpinnerArrayAdapter = funSetArrayAdapterString(activity, new ArrayList<>(map297_8820.keySet()), iMode, (byte) 0);
        spinMode.setAdapter(modeSpinnerArrayAdapter);
        spinMode.setSelection(iMode);

        rateAdapter = funSetArrayAdapterInteger(5, activity, Arrays.asList(rateArray), iRate, (byte) 'R');
        spinRate.setAdapter(rateAdapter);
        spinRate.setSelection(iRate);


        List<Integer> valuesPW297 = new ArrayList<>(mapPW297.values());
        iPW = valuesPW297.indexOf((pacerData[7] & 0x3F));

        CommonData.tPW = iPW;
        //Decode Polarity
        iPacePol = 0;
        iSenPol = 0;
        if ((pacerData[7] & 0x40) == 0x40)
            iPacePol = 1;
        if ((pacerData[7] & 0x80) == 0x80)
            iSenPol = 1;

        tPacePol = iPacePol;
        tSenPol = iSenPol;

        List<Integer> valuesAMP297 = new ArrayList<>(mapAMP297.values());
        iAmp = valuesAMP297.indexOf((pacerData[8] & 0x3F));
        tAmp = iAmp;
        // Decode ARC
        bARC = (pacerData[8] & 0x40) == 0x40;
        CommonData.tbARC = bARC;
        //HYST ON/OFF
        bHystON = (pacerData[8] & 0x80) == 0x80;
        CommonData.tbHystON = bHystON;

        // Decode Refrectory
        iRef = pacerData[10] & 0x1F;
        CommonData.tRef = iRef;
        //Hyst Search ON/OFF
        bHystSrchON = (pacerData[10] & 0x20) == 0x20;
        CommonData.tbHystSrchON = bHystSrchON;
//6th Byte
        //Decode Sensitivity
        CommonData.iSen = pacerData[11] & 0x0F;
        CommonData.tSen = iSen;
        //AM and MRI
        bAutoMsr = (pacerData[11] & 0x80) == 0x80;
        System.out.println("<><><><>## " + bAutoMsr);
        CommonData.tbAutoMsr = bAutoMsr;

        bMRI = (pacerData[11] & 0x40) == 0x40;
        CommonData.tbMRI = bMRI;

        //8th Byte
        //Decode Trigger Upper Rate (T modes)
        iTrigUprRate = pacerData[13] - 24;
        CommonData.tTrigUprRate = iTrigUprRate;
//9th Byte
        //Decode Target Upper Rate (R modes)
        iSensorRate = pacerData[14] - 24;
        CommonData.tSensorRate = iSensorRate;


        //Hyst Value
        ihystVAL = pacerData[9] & 0x1F;
        CommonData.thystVAL = ihystVAL;

        //Hyst Search ON/OFF
        bHystSrchON = (pacerData[10] & 0x20) == 0x20;
        tbHystSrchON = bHystSrchON;

        // Decode ARC
        bARC = (pacerData[8] & 0x40) == 0x40;
        CommonData.tbARC = bARC;

        List<Integer> valuesSlope297 = new ArrayList<>(mapSlope297.values());
        iSlope = valuesSlope297.indexOf(pacerData[15]);

        //Decode Down Time
        iDownTime = pacerData[16];////down tme
        CommonData.tDownTime = iDownTime;

        //Decode Target Upper Rate (R modes)
        iSensorRate = pacerData[14] - 24;
        CommonData.tSensorRate = iSensorRate;

        iuptime = pacerData[6] & 7; //uptime
        CommonData.tuptime = iuptime;


        /*if (flagMainFragment == 4 && flagSubFragment == 1) {
            FragmentParaVVIR.spinPw.setSelection(iPW);
            FragmentParaVVIR.spinAmp.setSelection(iAmp);
            FragmentParaVVIR.spinSens.setSelection(iSen);
            FragmentParaVVIR.spinRef.setSelection(iRef);
        }*/

        if (CommonData.iNoise == 1) {
            mFunAlert(activity, "Message", "Noise Detected");
            CommonData.iNoise = 0;
            //Snackbar.make(getView(),"Noise Detected",Snackbar.LENGTH_SHORT).show();
        }
        if (CommonData.iMagnet == 8) {
            mFunAlert(activity, "Message", "Magnet Switch ON");
            CommonData.iMagnet = 0;
            //Snackbar.make(getView(),"Noise Detected",Snackbar.LENGTH_SHORT).show();
        }
    }


    public static void decode747R() {
        mTxtDeviceName.setText(" Charak 747R ");
        mTxtDeviceNumber.setText("# " + iSrno);
        CommonData.currModel = "Charak747";
        CommonData.logFileWrite(activity, "Decode Charak 747", CommonData.pacerData, CommonData.currModel);
        mTxtImpLastValA.setVisibility(View.VISIBLE);
        mTxtImpTodayValA.setVisibility(View.VISIBLE);
        mTxtImpTitleA.setVisibility(View.VISIBLE);
        mTxtImpTitleV.setVisibility(View.VISIBLE);

        for (int i = 0; i < modeCtrlWD747.length; i++) {
            if (modeCtrlWD747[i] == (pacerData[6] & 0xFC)) {
                iMode = i;
                tMode = iMode;
                break;
            }
        }


        if (tMode < 0) {
            tMode = 1;
        }

        iRate = pacerData[15] & 0x3F;
        tRate = iRate;

        modeSpinnerArrayAdapter = funSetArrayAdapterString(activity, Arrays.asList(modeArray747), tMode, (byte) 0);
        spinMode.setAdapter(modeSpinnerArrayAdapter);
        spinMode.setSelection(tMode);

        rateAdapter = funSetArrayAdapterInteger(6, activity, Arrays.asList(rateArray747), tRate, (byte) 'R');
        spinRate.setAdapter(rateAdapter);
        spinRate.setSelection(tRate);


        //A NOISE
        // '(wd (19) nd &H2)/2
        //iANoise = (pacerData[19] & 2) / 2;
        CommonData.iANoise = (pacerData[6] & 2) / 2;
        //'V NOISE
        //'        wd (19) And &H1
        CommonData.iNoise = pacerData[6] & 1;


        //2nd byte
        //APW VPW
        //pwlm = (wd(20) And &HF0) / 16
        iAPW = (pacerData[7] & 0xF0) / 16;
        CommonData.tAPW = iAPW;
        //pwhm = wd(20) And &HF
        iPW = pacerData[7] & 0x0F;
        CommonData.tPW = iPW;

        //3rd byte
        //MAGNET
        //'(wd (21) And &H80)/128
        CommonData.iMagnet = pacerData[8] & 0x80;
        //AP POL
        //fapol = (wd(21) And &H40) / 64
        iAPacepol = (pacerData[8] & 0x40) / 64;
        CommonData.tAPacepol = iAPacepol;
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
            CommonData.iAAmp = (iAAmp + 93) / 4;

        CommonData.tAAmp = iAAmp;
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
        CommonData.tRef = iRef;
        //Hyst Val
        //rhy = wd(23) And &HF
        ihystVAL = pacerData[10] & 0x0F;
        CommonData.thystVAL = ihystVAL;
        //6th byte
        //PVARP
        // pvlm = (wd(24) And &HF0) / 16
        iPvarp = (pacerData[11] & 0xF0) / 16;
        CommonData.tPvarp = iPvarp;
        //A REF
        //rflm = (wd(24) And &HF)
        iARef = pacerData[11] & 0x0F;
        CommonData.tARef = iARef;
        //7th byte
        //AT ENTRY COUNT
        //ATent = (wd(25) And &HC0) / 64
        iATEnt = (pacerData[12] & 0xC0) / 64;
        CommonData.tATEnt = iATEnt;
        //UPPER RATE (SST and DDD Mode)
        // uplm = wd(25) And &H3F
        iTrigUprRate = pacerData[12] & 0x3F;
        CommonData.tTrigUprRate = iTrigUprRate;
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

        CommonData.tbARC = bARC;
        //AT ENABLE
        //fATena = (wd(26) And &H40) / 64
        if ((pacerData[13] & 0x40) == 0x40)
            bATEna = true;
        else
            bATEna = false;

        CommonData.tbATEna = bATEna;
        // TARGET (Sensor) UPPER RATE (RR)
        //tup = wd(26) And &H3F 'frc
        iSensorRate = pacerData[13] & 0x3F;
        CommonData.tSensorRate = iSensorRate;
        //9th byte
        //BLANKING
        //bklm = (wd(27) And &HC0) / 64
        iBlnk = (pacerData[14] & 0xC0) / 64;
        CommonData.tBlnk = iBlnk;
        //ASEN
        //snlm = (wd(27) And &H38) / 8
        iASen = (pacerData[14] & 0x38) / 8;
        CommonData.tASen = iASen;
        //VSEN
        //svlm = wd(27) And &H7
        iSen = pacerData[14] & 0x07;
        CommonData.tSen = iSen;
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

        CommonData.tbAVHSrch = bAVHSrch;
        //RATE
        //rtbm = wd(28) And &H3F

        //11th byte
        //AV Hyst Value
        //avhyst = (wd(29) And &HE0) / 32
        iAVHyst = (pacerData[16] & 0xE0) / 32;
        CommonData.tAVHyst = iAVHyst;
        //P AVD
        //avlm = wd(29) And &H1F
        iPAVI = pacerData[16] & 0x1F;
        CommonData.tPAVI = iPAVI;
        //12th byte
        //Up Time
        //uptm = (wd(30) And &HE0) / 32
        iuptime = (pacerData[17] & 0xE0) / 32;
        CommonData.tuptime = iuptime;
        //S AVD
        //d1lm = wd(30) And &H1F
        iSAVI = pacerData[17] & 0x1F;
        CommonData.tSAVI = iSAVI;
        //13th byte
        //SLOPE
        //slp = (wd(31) And &HF0) / 16
        iSlope = (pacerData[18] & 0xF0) / 16;
        CommonData.tSlope = iSlope;
        //Down Time
        //dntm = wd(31) And &HF
        iDownTime = pacerData[18] & 0x0F;
        CommonData.tDownTime = iDownTime;
        //14th byte
        //AT EXIT COUNT
        //AText = (wd(32) And &HC0) / 64
        iATExt = (pacerData[19] & 0xC0) / 64;
        CommonData.tATExt = iATExt;
        //AT TRIGGER RATE
        //ATrt = wd(32) And &H3F
        iATRate = pacerData[19] & 0x3F;
        CommonData.tATRate = iATRate;

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

        CommonData.tbAutoPol = bAutoPol;
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

        CommonData.tbMRI = bMRI;
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
            CommonData.tbAutoMsr = bAutoMsr;

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

            CommonData.tbATAF = bATAF;
        }
        //16th byte
        //BATTERY
        iBat = pacerData[21];

        if (CommonData.iNoise == 1) {

            mFunAlert(activity, "Message", "'V' Noise Detected");

            CommonData.iNoise = 0;
            //Snackbar.make(getView(),"Noise Detected",Snackbar.LENGTH_SHORT).show();
        }
        if (CommonData.iANoise == 1) {

            mFunAlert(activity, "Message", "'A' Noise Detected");
            CommonData.iANoise = 0;
            //Snackbar.make(getView(),"Noise Detected",Snackbar.LENGTH_SHORT).show();
        }
        if (CommonData.iMagnet == 8) {

            mFunAlert(activity, "Message", "Magnet Switch ON");
            CommonData.iMagnet = 0;

        }

    }

    static void updateViews(int pacerType) {

        iSrnoH = pacerData[4];
        iSrnoL = pacerData[5];
        iSrno = (256 * iSrnoH) + iSrnoL;

        switch (pacerType) {
            case 0x02: //Pinnacle 8820 old(single) 02
            case 0x1B: //Pinnacle 8820(single) 27
                decode8820AM();
                break;
            case 0x19: //Pinnacle 297(single) 25
                decode297();
                break;
            case 0x0C: //Charak 747(dual)  12
            case 0x18: //Charak AM 747(dual) 24
                decode747R();
                break;

            default:
                break;
        }
    }


    public Runnable blinkEffect = new Runnable() {

        @Override
        public void run() {
            int colorArray[] = {getResources().getColor(R.color.Green), getResources().getColor(R.color.CHERRY)};
            btnConnectBluetooth.setBackgroundColor(colorArray[bt_notes_blink]);
            bt_notes_blink++;
            if (bt_notes_blink == 2) {
                bt_notes_blink = 0;
            }
            handlerBlink.postDelayed(this, 500);
        }
    };

    void startRepeatingTask() {
        blinkEffect.run();
    }

    void stopRepeatingTask() {
        handlerBlink.removeCallbacks(blinkEffect);
        btnConnectBluetooth.setBackgroundColor(getResources().getColor(R.color.Green));
    }

    public static void validateCommonData() {
        int iColor;
        if (iPacerSelect == 12 || iPacerSelect == 24) // Charak DDD (AM/AF)
        {
            iColor = getColor747(activity, CommonData.tRate, (byte) 'R');
        } else {
            iColor = getColor297(CommonData.tRate, (byte) 'R', activity);
        }
        if (spinRate.getSelectedView() != null)
            ((TextView) spinRate.getSelectedView()).setTextColor(iColor);

    }

    private final BroadcastReceiver btStatReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            assert action != null;

            switch (action) {
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    //Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    startRepeatingTask();
                    //Toast.makeText(getApplicationContext(), "Connection Lost", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    final int btState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                    //Toast.makeText(getApplicationContext(), "Connecting......", Toast.LENGTH_SHORT).show();
                    if (btState == BluetoothAdapter.STATE_OFF) {
                        //startRepeatingTask();
                        //Toast.makeText(getApplicationContext(), "Connection Lost", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            CommonData.mStrBluetoothAddress = "";
            if (clientSocket != null) {
                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        unregisterReceiver(btStatReceiver);
        finish();
    }

    public void init() {
        modelAdapter = new ArrayAdapter<String>(ActivityProgrammer.this, R.layout.dropdown_item_long, demoModelArray) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.white));
                tv.setTextSize(15);

                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(getResources().getColor(R.color.Blue));
                textView.setTextSize(15);
                return textView;
            }
        };
        modelAdapter.setDropDownViewResource(R.layout.dropdown_item_long);
        spinModel.setAdapter(modelAdapter);

        spinModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (i == 0) {
                    CommonData.filePath = activity.getExternalFilesDir("").getAbsolutePath() + "/SPL_LOG/Pinnacle297";
                    CommonData.fileName = "65535.log";
                    POR_Pinr297();
                    //decode297();

                } else if (i == 1) {
                    CommonData.filePath = activity.getExternalFilesDir("").getAbsolutePath() + "/SPL_LOG/Charak747";
                    CommonData.fileName = "65535.log";
                    POR_DDDR_AM();
                    //decode747R();

                } else if (i == 2) {
                    CommonData.filePath = activity.getExternalFilesDir("").getAbsolutePath() + "/SPL_LOG/Pinnacle8820";
                    CommonData.fileName = "65535.log";
                    POR_Pinn8820AM();
                }

                updateViews(iPacerSelect);
                mLayoutImpedanceThreshold.setVisibility(View.VISIBLE);
                mLayoutThreshold.setVisibility(View.GONE);
                mLayoutRateMode.setVisibility(View.VISIBLE);
                mLayoutSideOptionButton.setVisibility(View.VISIBLE);
                mLayoutThrAtVt.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public int calculateProgress() {
        int intProgress = 0;
        double bat;
        if (iPacerSelect == 12 || iPacerSelect == 24) //Charak DR
            bat = 313.65 / (255 - iBat);
        else if (iPacerSelect == 2 || iPacerSelect == 27)// Old pinn 8820
            bat = 312.34 / (164 - iBat);
        else
            bat = (312.32 / (255 - iBat));


        System.out.println("<><><>$$$$&& " + bat);
        System.out.println("<><><>$$$$&& " + iBat);


        if (bat > 2.30 && bat <= 2.50) { // yellow area


            double aVal = (0.2 / 25);
            double bVal = (2.5 - bat);
            double cVal = (bVal / aVal);
            intProgress = (int) (40 - cVal);
            System.out.println("<><><>$$$$&&&& " + intProgress);

        } else if (bat > 2.50 && bat <= 2.8) { // blue area

            double aVal = (0.3 / 60);
            double bVal = (2.8 - bat);
            double cVal = (bVal / aVal);
            intProgress = (int) (100 - cVal);
            System.out.println("<><><>$$$$&&&& " + intProgress);
        } else if (bat > 2.80) { // blue area
            intProgress = 100;
            System.out.println("<><><>$$$$&&&& " + intProgress);
        } else { // red area

            double aVal = (0.3 / 15);
            double bVal = (2.3 - bat);
            double cVal = (bVal / 0.02);
            intProgress = (int) (15 - cVal);
            System.out.println("<><><>$$$$&&&& " + intProgress);
        }

        return intProgress;

    }

    private void initDataToSeekbar(int por) {
        progressItemList = new ArrayList<ProgressItem>();
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 15;
        //Log.i("Mainactivity", mProgressItem.progressItemPercentage + "");
        mProgressItem.color = R.color.CHERRY;
        progressItemList.add(mProgressItem);
        // blue span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 25;
        mProgressItem.color = R.color.Yellow;
        progressItemList.add(mProgressItem);
        // green span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 60;
        mProgressItem.color = R.color.Blue;
        progressItemList.add(mProgressItem);


        seekbar.initData(progressItemList);
        seekbar.setProgress(por);
        seekbar.invalidate();
    }


    public void readImpedance(int a) {
        System.out.println("<><><>#####call@@AAAA " + a);
        stringList.clear();
        logArrayList.clear();
        mapImp.clear();
        String strAction = "";
        // ***** Read Log Files *******
        //String path = activity.getExternalFilesDir("").getAbsolutePath() + "/SPLLOG/logfiles/";
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
                    if (strAction.contains("ImpedanceVal")) {
                        System.out.println("<><><>#####ommmmm   " + strAction);
                        CommonData.strDateImp = strAction.substring(strAction.indexOf("\n") + 1);
                        String[] separated = CommonData.strDateImp.split(" ");
                        stringList.add(separated[0]);

                        for (int q = 0; q < rdBytes[p + 1]; q++) {
                            pacerDataPROGImp[q] = 0xFF & rdBytes[q + p + 2];
                        }


                        if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24) {
                            decode_impedance747();
                            //Restore pacerDataPROG
                            //System.arraycopy(CommonData.pacerData, 0, pacerDataPROG, 0, CommonData.pacerData[1] + 5);
                            CommonData.bshowStat = true;
                        } else {
                            decode_impedance297();
                            //Restore pacerDataPROG
                            //System.arraycopy(CommonData.pacerData, 0, pacerDataPROG, 0, CommonData.pacerData[1] + 5);
                            CommonData.bshowStat = true;
                        }

                        //else
                        //update_Counts();
                    }


                    p += nBytes;
                }
            }
            //Collections.reverse(logArrayList);
            if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24) {
                List<String> keysImp = new ArrayList<>(mapImp.keySet());
                Collections.reverse(keysImp);
                if (keysImp.size() >= 1) {
                    ImpedanceDual impedanceDual = mapImp.get(keysImp.get(0));
                    mTxtImpToday.setText("Today\n" + impedanceDual.getmStrDate());
                    mTxtImpTodayValA.setText(setStrImpVal(impedanceDual.getmIntAvalue()));
                    mTxtImpTodayValV.setText(setStrImpVal(impedanceDual.getmIntVvalue()));
                }

                if (keysImp.size() >= 2) {
                    ImpedanceDual impedanceDual2 = mapImp.get(keysImp.get(1));
                    mTxtImpLast.setText("Last\n" + impedanceDual2.getmStrDate());
                    mTxtImpLastValA.setText(setStrImpVal(impedanceDual2.getmIntAvalue()));
                    mTxtImpLastValV.setText(setStrImpVal(impedanceDual2.getmIntVvalue()));
                }

            } else {
                List<String> keysImp = new ArrayList<>(mapImp.keySet());
                Collections.reverse(keysImp);
                if (keysImp.size() >= 1) {
                    ImpedanceDual impedanceDual = mapImp.get(keysImp.get(0));
                    ;
                    mTxtImpToday.setText("Today\n" + impedanceDual.getmStrDate());
                    mTxtImpTodayValV.setText(setStrImpVal(impedanceDual.getmIntAvalue()) + "");
                }

                if (keysImp.size() >= 2) {
                    ImpedanceDual impedanceDual = mapImp.get(keysImp.get(1));
                    mTxtImpLast.setText("Last\n" + impedanceDual.getmStrDate());
                    mTxtImpLastValV.setText(setStrImpVal(impedanceDual.getmIntAvalue()) + "");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //**************

    }

    private void decode_impedance297() {
        System.out.println("<><>&&&@@ " + CommonData.strDateImp);
        ImpedanceDual impedanceDual = new ImpedanceDual();
        //****  Impedance ******

        int idiv, TR2;
        if (pacerDataPROGImp[3] >= 192)
            idiv = 242;
        else if (pacerDataPROGImp[3] >= 170)
            idiv = 250;
        else
            idiv = 255;

        if (pacerDataPROGImp[3] <= 0) //To avoid invalid calculation (divide by zero)
            TR2 = 50;
        else {
            double tmp;

            tmp = -log((double) pacerDataPROGImp[3] / idiv);
            TR2 = (int) (256 / tmp);

        }


        impedanceDual.setmIntAvalue(TR2);
        System.out.println("<><>&&&@@!!!!!! " + TR2);

        String[] separated = CommonData.strDateImp.split(" ");
        impedanceDual.setmStrDate(separated[0]);
        System.out.println("<><>&&&@@&&&&&&******* " + stringList.indexOf(separated[0]));
        mapImp.put(separated[0], impedanceDual);
    }

    public void decode_impedance747() {
        System.out.println("<><><><>$$imp^^^ " + pacerDataPROGImp[3]);
        System.out.println("<><>&&&@@ " + CommonData.strDateImp);
        ImpedanceDual impedanceDual = new ImpedanceDual();
        //**** A Impedance ******
        int aImpedance = pacerDataPROGImp[3];
        if (aImpedance <= 0)
            aImpedance = 1; //'to avoide divide by zero
        aImpedance = -(int) ((1 / log((double) aImpedance / 256)) * 191);
        impedanceDual.setmIntAvalue(aImpedance);
        System.out.println("<><>&&&@@!!!!!! " + aImpedance);
        //**** V Impedance ******
        int vImpedance = pacerDataPROGImp[4];
        if (vImpedance <= 0)
            vImpedance = 1; //'to avoide divide by zero
        vImpedance = -(int) ((1 / log((double) vImpedance / 256)) * 202);
        impedanceDual.setmIntVvalue(vImpedance);
        System.out.println("<><>&&&@@!!!!! " + vImpedance);
        String[] separated = CommonData.strDateImp.split(" ");
        impedanceDual.setmStrDate(separated[0]);
        System.out.println("<><>&&&@@&&&&&&******* " + stringList.indexOf(separated[0]));
        mapImp.put(separated[0], impedanceDual);
        logArrayList.add(impedanceDual);
        /*if (CommonData.strLastDateTime.contains(separated[0])) {

        } else {
            CommonData.strLastDateTime = CommonData.strDateTime;
            System.out.println("<><>&&&@@&& " + separated[0]);
            logArrayList.add(impedanceDual);
        }*/

    }

    void funCallThreshold() {

        mIntegerArrayAdapterCycles = funSmallSetArrayAdapterInteger(ActivityProgrammer.this, Arrays.asList(CommonData.thrCyclesArr), 0, (byte) 0);
        spinThrCycles.setAdapter(mIntegerArrayAdapterCycles);

        mStringArrayAdapterPolarity = funSmallSetArrayAdapterString(ActivityProgrammer.this, Arrays.asList(CommonData.thrPolarityArray), 0, (byte) 0);
        spinThrPolarity.setAdapter(mStringArrayAdapterPolarity);

        if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24) {

            fm.beginTransaction().replace(R.id.prog_frame, new FragmentThresholdTestDual(), "FragmentThresholdTabs").commit();
            fm.beginTransaction().addToBackStack(null);
            mLayoutBtnAtrium.setVisibility(View.VISIBLE);
            mLayoutBtnVentricle.setVisibility(View.VISIBLE);

            if (mThrAtVeMode == 0) {
                mDoubleArrayAdapterPW = CommonData.funSmallSetArrayAdapterDouble(ActivityProgrammer.this, Arrays.asList(CommonData.pwArray747), iAPW, (byte) 'P');
                spinThrPw.setAdapter(mDoubleArrayAdapterPW);
                spinThrPw.setSelection(iAPW);

                mIntegerArrayAdapterRefractory = CommonData.funSmallSetArrayAdapterInteger(ActivityProgrammer.this, Arrays.asList(CommonData.refArray747), iARef, (byte) 'E');
                spinThrRefractory.setAdapter(mIntegerArrayAdapterRefractory);
                spinThrRefractory.setSelection(iARef);

                List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
                mDoubleArrayAdapterAMP = CommonData.funSmallSetArrayAdapterDouble(ActivityProgrammer.this, keysAMP, iAAmp, (byte) 'A');
                spinThrAmp.setAdapter(mDoubleArrayAdapterAMP);
                spinThrAmp.setSelection(iAAmp);
            } else {
                mDoubleArrayAdapterPW = CommonData.funSmallSetArrayAdapterDouble(ActivityProgrammer.this, Arrays.asList(CommonData.pwArray747), iPW, (byte) 'W');
                spinThrPw.setAdapter(mDoubleArrayAdapterPW);
                spinThrPw.setSelection(iPW);

                mIntegerArrayAdapterRefractory = CommonData.funSmallSetArrayAdapterInteger(ActivityProgrammer.this, Arrays.asList(CommonData.refArray747), iRef, (byte) 'F');
                spinThrRefractory.setAdapter(mIntegerArrayAdapterRefractory);
                spinThrRefractory.setSelection(iRef);

                List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
                mDoubleArrayAdapterAMP = CommonData.funSmallSetArrayAdapterDouble(ActivityProgrammer.this, keysAMP, iAmp, (byte) 'V');
                spinThrAmp.setAdapter(mDoubleArrayAdapterAMP);
                spinThrAmp.setSelection(iAmp);
            }

            rgThrAmpPW.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radioThrAmplitude) {
                        mLayoutAmp.setVisibility(View.GONE);
                        mLayoutPw.setVisibility(View.VISIBLE);
                        FragmentThresholdTestDual.mLayoutAmp.setVisibility(View.VISIBLE);
                        FragmentThresholdTestDual.mLayoutPw.setVisibility(View.GONE);
                    } else {
                        mLayoutAmp.setVisibility(View.VISIBLE);
                        mLayoutPw.setVisibility(View.GONE);
                        FragmentThresholdTestDual.mLayoutAmp.setVisibility(View.GONE);
                        FragmentThresholdTestDual.mLayoutPw.setVisibility(View.VISIBLE);
                    }

                }
            });

        } else {
            fm.beginTransaction().replace(R.id.prog_frame, new FragmentThresholdTestSingle(), "FragmentThresholdTabs").commit();
            fm.beginTransaction().addToBackStack(null);

            mLayoutBtnAtrium.setVisibility(View.GONE);
            mLayoutBtnVentricle.setVisibility(View.GONE);
            mIntegerArrayAdapterRefractory = CommonData.funSmallSetArrayAdapterInteger(ActivityProgrammer.this, Arrays.asList(CommonData.refArray297), iRef, (byte) 'F');
            spinThrRefractory.setAdapter(mIntegerArrayAdapterRefractory);
            spinThrRefractory.setSelection(iRef);

            List<Double> keysPW = new ArrayList<>(mapPW297.keySet());
            mDoubleArrayAdapterPW = CommonData.funSmallSetArrayAdapterDouble(ActivityProgrammer.this, keysPW, iPW, (byte) 'W');
            spinThrPw.setAdapter(mDoubleArrayAdapterPW);
            spinThrPw.setSelection(iPW);

            List<Double> keysAMP = new ArrayList<>(mapAMP297.keySet());
            mDoubleArrayAdapterAMP = CommonData.funSmallSetArrayAdapterDouble(ActivityProgrammer.this, keysAMP, iAmp, (byte) 'A');
            spinThrAmp.setAdapter(mDoubleArrayAdapterAMP);
            spinThrAmp.setSelection(iAmp);

            rgThrAmpPW.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radioThrAmplitude) {
                        mLayoutAmp.setVisibility(View.GONE);
                        mLayoutPw.setVisibility(View.VISIBLE);
                        FragmentThresholdTestSingle.mLayoutAmp.setVisibility(View.VISIBLE);
                        FragmentThresholdTestSingle.mLayoutPw.setVisibility(View.GONE);
                    } else {
                        mLayoutAmp.setVisibility(View.VISIBLE);
                        mLayoutPw.setVisibility(View.GONE);
                        FragmentThresholdTestSingle.mLayoutAmp.setVisibility(View.GONE);
                        FragmentThresholdTestSingle.mLayoutPw.setVisibility(View.VISIBLE);
                    }

                }
            });
        }


        mLayoutBtnAtrium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThrAtVeMode = 0;
                mLayoutBtnAtrium.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mLayoutBtnVentricle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentThresholdTestDual.funUpdateViews(mThrAtVeMode);
            }
        });

        mLayoutBtnVentricle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThrAtVeMode = 1;
                mLayoutBtnAtrium.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mLayoutBtnVentricle.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentThresholdTestDual.funUpdateViews(mThrAtVeMode);
            }
        });


        rgThrAmpPW.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24) {
                    if (checkedId == R.id.radioThrAmplitude) {
                        mLayoutAmp.setVisibility(View.GONE);
                        mLayoutPw.setVisibility(View.VISIBLE);
                        FragmentThresholdTestDual.mLayoutAmp.setVisibility(View.VISIBLE);
                        FragmentThresholdTestDual.mLayoutPw.setVisibility(View.GONE);
                    } else {
                        mLayoutAmp.setVisibility(View.VISIBLE);
                        mLayoutPw.setVisibility(View.GONE);
                        FragmentThresholdTestDual.mLayoutAmp.setVisibility(View.GONE);
                        FragmentThresholdTestDual.mLayoutPw.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (checkedId == R.id.radioThrAmplitude) {
                        mLayoutAmp.setVisibility(View.GONE);
                        mLayoutPw.setVisibility(View.VISIBLE);
                        FragmentThresholdTestSingle.mLayoutAmp.setVisibility(View.VISIBLE);
                        FragmentThresholdTestSingle.mLayoutPw.setVisibility(View.GONE);
                    } else {
                        mLayoutAmp.setVisibility(View.VISIBLE);
                        mLayoutPw.setVisibility(View.GONE);
                        FragmentThresholdTestSingle.mLayoutAmp.setVisibility(View.GONE);
                        FragmentThresholdTestSingle.mLayoutPw.setVisibility(View.VISIBLE);
                    }


                }

            }
        });
    }

    public static void closeThr() {
        updateViews(iPacerSelect);
        mLayoutImpedanceThreshold.setVisibility(View.VISIBLE);
        mLayoutThreshold.setVisibility(View.GONE);
        mLayoutRateMode.setVisibility(View.VISIBLE);
        mLayoutSideOptionButton.setVisibility(View.VISIBLE);
        mLayoutThrAtVt.setVisibility(View.GONE);
    }

    public void dialogErrorList() {
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>(mapValidationMsg.values()));
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ActivityProgrammer.this);
        builderSingle.setTitle("Error list:-");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(itemsAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = itemsAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(ActivityProgrammer.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }

    public void btConnectionCheck() {
        if (clientSocket == null) {
            btnInterrogation.setBackgroundColor(Color.parseColor("#dddddd"));
        } else {
            btnInterrogation.setBackgroundColor(getResources().getColor(R.color.gray));
        }

    }

    // fun for showing dialog with Radio Button for language
    private void showRadioConfirmationDialog() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ActivityProgrammer.this);
        builder.setTitle("List of language");
        builder.setSingleChoiceItems(languageStrArr, languageIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                languageIndex = i;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //System.out.println("<><><>######  "+i);

                PreferenceManager.setCurrentLanguage(ActivityProgrammer.this, languageCodeArr[languageIndex]);
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();

    }

    class makePDF extends AsyncTask<Void, Void, Void> {
        //private ProgressDialog mWait;
        final Context pdfContext;

        makePDF(Context context) {
            this.pdfContext = context;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("<><><>##$$$$$ callllllllll9090");
            mWait = ProgressDialog.show(ActivityProgrammer.this, "Creating PDF", "Please wait...");
        }


        @Override
        protected Void doInBackground(Void... voids) {
            saveData_In_PDF();
            //mWait.dismiss();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
             mWait.dismiss();
        }
    }

    void funPdfDailog() {
        Dialog dialog = new Dialog(ActivityProgrammer.this);
        dialog.setContentView(R.layout.save_pdf_layout);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenwd = dm.widthPixels;
        int screenht = dm.heightPixels;
        dialog.getWindow().setLayout((int) (screenwd * 0.6), (int) (screenht * 0.8)); //Adjust popup layout size
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        findViewID(dialog);

        if (CommonData.iPacerSelect == 25) {
            String strTitle = getString(R.string.pinnr297) + " /# " + iSrno;
            pdfTxtTitle.setText(strTitle);
            //pdfTxtTitle.setText("Pinnacle R+ 297 / #" + iSrno);
        } else if (CommonData.iPacerSelect == 0x1B) {
            String strTitle = getString(R.string.pinn8820am) + " /# " + iSrno;
            pdfTxtTitle.setText(strTitle);
            //   pdfTxtTitle.setText("Pinnacle 8820 AM / #" + iSrno);
        }
        else if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24) {
            String strTitle = getString(R.string.charak747) + " /# " + iSrno;
            pdfTxtTitle.setText(strTitle);
            //   pdfTxtTitle.setText("Charak AM/AF / #" + iSrno);
        }
        else {
            String strTitle = getString(R.string.pinn8820) + " /# " + iSrno;
            pdfTxtTitle.setText(strTitle);
            //pdfTxtTitle.setText("Pinnacle 8820 / #" + iSrno);
        }

        btSavePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                new makePDF(ActivityProgrammer.this).execute();
                //mWait.dismiss();
                Toast.makeText(ActivityProgrammer.this, "PDF File Saved", Toast.LENGTH_SHORT).show();
            }
        });

        butImplDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });

        dialog.show();
    }

    public static void funCheckValidationList() {
        if (mapValidationMsg.size() >= 1) {
            mImgAlert.setImageResource(R.drawable.ic_error_on);
        } else {
            mImgAlert.setImageResource(R.drawable.ic_error_off);
        }

    }


    void setBatteryValuePos(int por) {
        int p = 100 - por;
        System.out.println("<><>battery  " + por);
        int width = mLayoutBatteryTxt.getWidth();
        System.out.println("<><>batteryABC  " + width);
        int res = (int) ((width / 100.0f) * p);
        System.out.println("<><>battery  " + res);
        double bat;
        if (iPacerSelect == 12 || iPacerSelect == 24) //Charak DR
            bat = 313.65 / (255 - iBat);
        else if (iPacerSelect == 2)// Old pinn 8820
            bat = 312.34 / (164 - iBat);
        else
            bat = (312.32 / (255 - iBat));

        String strBat = new DecimalFormat("##.##").format(bat) + " v";//+getString(R.string.empty)+getString(R.string.eol);
        mTxtValueBattery.setText(strBat);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (res >= 30) {
            params.setMargins(0, 2, res - 30, 0);
        } else {
            params.setMargins(0, 2, res, 0);
        }
        mTxtValueBattery.setLayoutParams(params);
    }

    public static void blnkProgBut() {
        //Check if Prog BUTTON is enabled then check for Blink

        //check if para is changed then blink prog but
        if (isInterrogation) {
            for (int i = 3; i <= CommonData.pacerData[1] + 1; i++) {
                if (CommonData.pacerData[i] != CommonData.pacerDataPROG[i]) {
                    //to Blink prog button
                    System.out.println("<><>ValidateA " + pacerData[i]);
                    System.out.println("<><>ValidateB " + pacerDataPROG[i]);
                    Animation butAnim = AnimationUtils.loadAnimation(activity, R.anim.blink);
                    btnProgram.startAnimation(butAnim);

                    break;
                } else {

                    btnProgram.clearAnimation(); // Stop Blinking
                }
            }
        } else
            btnProgram.clearAnimation();
    }

    private void findViewID(Dialog dialog) {
        btSavePDF = dialog.findViewById(R.id.btSavePDF);
        pdfTxtTitle = dialog.findViewById(R.id.txtPdfTitle);
        pdfPname = dialog.findViewById(R.id.txtPatientName);
        pdfpAge = dialog.findViewById(R.id.txtAge);
        pdfLdSrno = dialog.findViewById(R.id.txtLeadSrNo);
        pdfimplDate = dialog.findViewById(R.id.txtImplDate);
        butImplDate = dialog.findViewById(R.id.btnDate);
        pdfDrName = dialog.findViewById(R.id.txtDrName);
        pdfHName = dialog.findViewById(R.id.txtHospName);
        pdfPthr = dialog.findViewById(R.id.txtPthr);
        pdfSthr = dialog.findViewById(R.id.txtSthr);
        pdfImpd = dialog.findViewById(R.id.txtImpd);
        pdfRemark = dialog.findViewById(R.id.txtRemark);
        btnDate = dialog.findViewById(R.id.btnDate);
    }

    public void CreatPdfOration(Dialog dialog){


    }

    private void saveData_In_PDF() {

        //get Screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        pageH = displayMetrics.heightPixels;// get screen height
        pageW = displayMetrics.widthPixels;// get screen Width
        //get Current Date and Time
        //SimpleDateFormat simpleDateFormat;
        String curDate = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault()).format(new Date());
        String curTime = new SimpleDateFormat("hh_mm_ss", Locale.getDefault()).format(new Date());
        //Set Text color and dimensions
        // For Title
        pdfTitlePaint = new Paint();
        pdfTitlePaint.setTextAlign(Paint.Align.CENTER);
        pdfTitlePaint.setTextSize(26);
        pdfTitlePaint.setColor(Color.BLACK);
        //For Other CommonData
        pdfParaPaint = new Paint();
        //pdfParaPaint.setTextAlign(Paint.Align.CENTER);
        pdfParaPaint.setTextSize(20);
        pdfParaPaint.setColor(Color.BLACK);
        //For Bar Chart Text
        pdfChartPaint = new Paint();
        pdfChartPaint.setTextSize(16);
        pdfChartPaint.setColor(Color.DKGRAY);

        y = (int) (((int) pdfTitlePaint.descent() - (int) pdfTitlePaint.ascent()) * 1.5);
        //Get Text Height
        Rect txtDim = new Rect();
        pdfTitlePaint.getTextBounds(String.valueOf(pdfTxtTitle.getText()), 0,
                String.valueOf(pdfTxtTitle.getText()).length(), txtDim);
        txtVgap = txtDim.height();
        //Set PDF page info

//***************** 1st PAGE ****************
        pageNo = 1;
        pageInfo = new PdfDocument.PageInfo.Builder(pageW, pageH, pageNo).create();
        page = pdfDoc.startPage(pageInfo);
        pdfCanvas = page.getCanvas();
        showPatientInfo(pdfCanvas, pageW, y + txtVgap-50); // Show Patient Information
        if (CommonData.iPacerSelect == 25) {
            showPara8820AM297(pdfCanvas, pageW, y + txtVgap + 350); // Show CommonData
        } else if (CommonData.iPacerSelect == 2) {
            showPara8820(pdfCanvas, pageW, y + txtVgap + 350); // Show CommonData
        } else if (CommonData.iPacerSelect == 0x1B) {
            showPara8820AM297(pdfCanvas, pageW, y + txtVgap + 350); // Show CommonData
        }
        else if (CommonData.iPacerSelect == 12 || CommonData.iPacerSelect == 24) {
            showParaCharak747(pdfCanvas, pageW, y + txtVgap + 250); // Show CommonData
        }

        pdfDoc.finishPage(page);
//***************** 1st PAGE END ****************
//***************** 2nd PAGE ****************
        // Show Statistics
        pageNo += 1;
        pageInfo = new PdfDocument.PageInfo.Builder(pageW, pageH, pageNo).create();
        page = pdfDoc.startPage(pageInfo);
        pdfCanvas = page.getCanvas();
        if (CommonData.iPacerSelect==12 || CommonData.iPacerSelect==24 )
            showStatCharak(pdfCanvas,pageW,y+txtVgap+50);
        else
            showStat8820AM297(pdfCanvas, pageW, y + txtVgap + 50);
        pdfDoc.finishPage(page);
//***************** 2nd PAGE END ****************
//***************** Next PAGE (Marker/ECG starts)****************
        pageNo += 1;
        //draw_markerECG(true);

//***************** Marker/ECG PAGE END ****************
//*****************  Next PAGE (Threshold/ECG starts)****************
        pageNo += 1;
        //draw_markerECG(false);

//***************** Marker/ECG PAGE END ****************

        //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SPLLOG/";

        File dir = new File(CommonData.filePath);
        if (!dir.exists()) {
            dir.mkdirs();
            //Toast.makeText(this, "Dir Created", Toast.LENGTH_SHORT).show();
        }
        //File file = new File(dir, String.valueOf(iSrno).toString() + "_" + curDate + "_" + curTime + ".pdf");
        //Take file name
        String fName = CommonData.fileName.substring(0,CommonData.fileName.lastIndexOf('.') ); //File name
        File file = new File(dir, fName + "_" + curDate + "_" + curTime + ".pdf");
        //File file = new File(dir, CommonData.fileName + "_" + curDate + "_" + curTime + ".pdf");
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            pdfDoc.writeTo(fOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfDoc.close();

    }

    private void showStatCharak(Canvas pdfCanvas, int x, int y) {
        float perAP = 0, perAS = 0, perVP = 0, perVS = 0;
        long tempTotal;
        //A Counts Total
        tempTotal=CommonData.cntL80+CommonData.cnt100;
        if (tempTotal > 0) {
            perAP = (float)((CommonData.cntL80 * 100) / (double) tempTotal);
            perAS = (float)((CommonData.cnt100 * 100) / (double) tempTotal);
        }
        //V Counts Total
        tempTotal= CommonData.cntPace + CommonData.cntSens;
        if (tempTotal>0) {
            perVP = (float) ((CommonData.cntPace * 100) / (double) tempTotal);
            perVS = (float)((CommonData.cntSens * 100) / (double) tempTotal);

        }

        pdfCanvas.drawText("Statistics Counts", (float) x / 2, y, pdfTitlePaint);
        y += 50;
        //Total A Pace
        pdfCanvas.drawText("A Pace Counts - " + CommonData.cntL80 + "( " + new DecimalFormat("##.##").format(perAP) + " %)", (float) x / 20, y, pdfParaPaint);
        y += 50;
        //Total A Sens
        pdfCanvas.drawText("A Sens Counts - " + CommonData.cnt100 + "( " + new DecimalFormat("##.##").format(perAS) + " %)", (float) x / 20, y, pdfParaPaint);

        y += 50;
        //Total V Pace
        pdfCanvas.drawText("V Pace Counts - " + CommonData.cntPace + "( " + new DecimalFormat("##.##").format(perVP) + " %)", (float) x / 20, y, pdfParaPaint);
        y += 50;
        //Total V Sens
        pdfCanvas.drawText("V Sens Counts - " + CommonData.cntSens + "( " + new DecimalFormat("##.##").format(perVS) + " %)", (float) x / 20, y, pdfParaPaint);

        y += 50;
        //Noise pace Counts
        pdfCanvas.drawText("Noise pacing - " + CommonData.cntNoisePace,  (float) x / 20, y, pdfParaPaint);
        //Noise Counts
        pdfCanvas.drawText("Noise Counts - " + CommonData.cntNoise, (float) 12 * x / 20, y, pdfParaPaint);

        y+=50;
        //AT Counts
        pdfCanvas.drawText("AT - " + CommonData.cnt120,  (float) x / 20, y, pdfParaPaint);
        //AF Counts
        if (CommonData.iPacerSelect==24)
            pdfCanvas.drawText("AF - " + CommonData.cnt140, (float) 12 * x / 20, y, pdfParaPaint);



        //Draw Chart of counts
        //X-Y lines
        int hPDF = pdfCanvas.getHeight();
        int wPDF = pdfCanvas.getWidth();
        int xPos = wPDF / 20, baseLine = hPDF - 50;

        pdfCanvas.drawLine(xPos + 20, baseLine, wPDF - 50, baseLine, pdfChartPaint); //X Line
        pdfCanvas.drawLine(xPos + 20, baseLine, xPos + 20, baseLine - 400, pdfChartPaint); //Y Line

        //Pace Sens Bar Graph
        //A Pacve
        xPos += 40;
        pdfCanvas.drawText("A Pace", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perAP), xPos, baseLine - (4 * perAP) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * perAP), xPos + 30, baseLine, pdfChartPaint); // Pace
        //A Sens
        xPos += 60;
        pdfCanvas.drawText("A Sens", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perAS), xPos, baseLine - (4 * perAS) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * perAS), xPos + 30, baseLine, pdfChartPaint); // Sens
        //V Pace
        xPos += 90;
        pdfCanvas.drawText("V Pace", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perVP), xPos, baseLine - (4 * perVP) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * perVP), xPos + 30, baseLine, pdfChartPaint); // Sens <80
        //V Sens
        xPos += 60;
        pdfCanvas.drawText("V Sens", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perVS), xPos, baseLine - (4 * perVS) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * perVS), xPos + 30, baseLine, pdfChartPaint); // Sens <100

    }

    private void showPatientInfo(Canvas canvas, int x, int y) {
        y = y + 20;
        //Write Model  and Sr. No.
        canvas.drawText(String.valueOf(pdfTxtTitle.getText()), (float) x / 2, y, pdfTitlePaint);
        y = y + 35;
        // Write Patient Details
        canvas.drawText("PATIENT / AGE -  " + pdfPname.getText()
                + " / " + pdfpAge.getText(), (float) x / 20, y, pdfParaPaint);
        y = y + 35;
        canvas.drawText("LEAD  - " + pdfLdSrno.getText()
                , (float) x / 20, y, pdfParaPaint);
        canvas.drawText("IMPLANT ON - " + pdfimplDate.getText()
                , (float) x / 2, y, pdfParaPaint);
        y = y + 35;
        canvas.drawText("DOCTOR - " + pdfDrName.getText()
                , (float) x / 20, y, pdfParaPaint);
        canvas.drawText("HOSPITAL - " + pdfHName.getText()
                , (float) x / 2, y, pdfParaPaint);
        y = y + 35;
        canvas.drawText("PACE THR(A/V) - " + pdfPthr.getText()
                , (float) x / 20, y, pdfParaPaint);
        canvas.drawText("SENS THR(A/V) - " + pdfSthr.getText()
                , (float) x / 2, y, pdfParaPaint);
        y = y + 35;
        canvas.drawText("Impedance (Ohms)  -  " + pdfImpd.getText()
                , (float) x / 20, y, pdfParaPaint);
        y = y + 35;
        canvas.drawText("REMARK  -  " + pdfRemark.getText()
                , (float) x / 20, y, pdfParaPaint);
    }

    private void showPara8820(Canvas canvas, int x, int y) {
        String temp;

        //Show Battery
        double bat = (312.34 / (164 - CommonData.iBat));
        //temp = new DecimalFormat("##.##").format(bat) + " V";
        //Check for battery level
        if (bat<2.31) {

            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.eol);
        }
        else if (bat <2.52){
            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.eri);

        }
        else
        {
            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.ok);

        }
        canvas.drawText( temp, x / 20, y, pdfParaPaint);
        //Mode
        temp = CommonData.modeArray[CommonData.iMode];
        canvas.drawText("Mode -  " + temp, (float) 7 * x / 20, y, pdfParaPaint);
        //Rate
        temp = String.valueOf(CommonData.rateArray[CommonData.iRate]);
        canvas.drawText("Rate - " + temp + " ppm", (float) 14 * x / 20, y, pdfParaPaint);

        y += 50;
        //Amplitude
        temp = String.valueOf(CommonData.amparray[CommonData.iAmp]);
        canvas.drawText("AMP - " + temp + " v", (float) x / 20, y, pdfParaPaint);

        //PW
        temp = String.valueOf(CommonData.pwArray[CommonData.iPW]);
        canvas.drawText("PW - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);

        y += 50;
        //Pace Polarity
        temp = String.valueOf(CommonData.polArray[CommonData.iPacePol]);
        canvas.drawText("P Pol - " + temp, (float) x / 20, y, pdfParaPaint);

        //Sens Polarity
        temp = String.valueOf(CommonData.polArray[CommonData.iSenPol]);
        canvas.drawText("S Pol - " + temp, (float) 7 * x / 20, y, pdfParaPaint);
        y += 50;
        //Sensing
        temp = String.valueOf(CommonData.senArray[CommonData.iSen]);
        canvas.drawText("Sens - " + temp + " mV", (float) x / 20, y, pdfParaPaint);

        //Refractory
        temp = String.valueOf(CommonData.refArray[CommonData.iRef]);
        canvas.drawText("Ref - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);
        //Hysteresis
        temp = String.valueOf(CommonData.HystArray[CommonData.ihystVAL]);
        canvas.drawText("Hyst - " + temp, (float) 14 * x / 20, y, pdfParaPaint);

        y += 50;
        //Upper rate (in VVT)
        if (CommonData.iMode == 2 || CommonData.iMode == 6 || CommonData.iMode == 10 || CommonData.iMode == 13) {
            temp = String.valueOf(CommonData.uprArray[CommonData.iUpRate]);
            canvas.drawText("Upper RT - " + temp + " ppm", (float) x / 20, y, pdfParaPaint);
        }

    }
    private void showPara8820AM297(Canvas canvas, int x, int y) {
        String temp;

        //Show Battery
        double bat = (312.32 / (255 - CommonData.iBat));
        //temp = new DecimalFormat("##.##").format(bat) + " V";
        //Check for battery level
        if (bat<2.31) {

            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.eol);
        }
        else if (bat <2.52){
            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.eri);

        }
        else
        {
            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.ok);

        }
        canvas.drawText( temp, x / 20, y, pdfParaPaint);
        //Show Remaining Life
        //showRemLife();
        remLifeCalc();
        canvas.drawText("Expected Life - " + CommonData.remYear +" Year "+CommonData.remMonth+" month" ,7*x/20,y,pdfParaPaint);

        y += +50;
        //Mode
        temp = CommonData.modeArray[CommonData.iMode];
        canvas.drawText("Mode -  " + temp,  x / 20, y, pdfParaPaint);
        //Rate
        temp = String.valueOf(CommonData.rateArray[CommonData.iRate]);
        canvas.drawText("Rate - " + temp + " ppm", (float) 7 * x / 20, y, pdfParaPaint);

        y += 50;
        //Amplitude
        temp = String.valueOf(CommonData.amparray297[CommonData.iAmp]);
        canvas.drawText("AMP - " + temp + " V", (float) x / 20, y, pdfParaPaint);

        //PW
        temp = String.valueOf(CommonData.pwArray[CommonData.iPW]);
        canvas.drawText("PW - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);

        y += 50;
        //Pace Polarity
        temp = String.valueOf(CommonData.polArray[CommonData.iPacePol]);
        canvas.drawText("P Pol - " + temp, (float) x / 20, y, pdfParaPaint);

        //Sens Polarity
        temp = String.valueOf(CommonData.polArray[CommonData.iSenPol]);
        canvas.drawText("S Pol - " + temp, (float) 7 * x / 20, y, pdfParaPaint);
        y += 50;
        //Sensing
        if (iSrno < 3390) // Old 297
            temp = String.valueOf(CommonData.senArray297old[CommonData.iSen]);
        else
            temp = String.valueOf(CommonData.senArray297[CommonData.iSen]);

        canvas.drawText("Sens - " + temp + " V", (float) x / 20, y, pdfParaPaint);

        //Refractory
        temp = String.valueOf(CommonData.refArray297[CommonData.iRef]);
        canvas.drawText("Ref - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);

        y += 50;
        //Hysteresis
        temp = String.valueOf(CommonData.hystArray297[CommonData.ihystVAL]);
        if (CommonData.bHystON)
            canvas.drawText("Hyst - " + temp, (float) x / 20, y, pdfParaPaint);
        else
            canvas.drawText("Hyst -  -----", (float) x / 20, y, pdfParaPaint);
        if (CommonData.bHystSrchON)
            canvas.drawText("Hyst Search - ON", (float) 7 * x / 20, y, pdfParaPaint);
        else
            canvas.drawText("Hyst Search - OFF", (float) 7 * x / 20, y, pdfParaPaint);
        y += 50;
        //AM
        if (CommonData.bAutoMsr) {
            canvas.drawText("AM - ON", (float) x / 20, y, pdfParaPaint);
        } else {
            canvas.drawText("AM - OFF", (float) x / 20, y, pdfParaPaint);
        }
        //MRI
        if (CommonData.bMRI) {
            canvas.drawText("MRI - ON", (float) 7 * x / 20, y, pdfParaPaint);
        } else {
            canvas.drawText("MRI - OFF", (float) 7 * x / 20, y, pdfParaPaint);
        }

        y += 50;
        //Upper rate (in VVT)
        if (CommonData.iMode == 2 || CommonData.iMode == 6 || CommonData.iMode == 10 || CommonData.iMode == 13) {
            temp = String.valueOf(CommonData.uprArray297[CommonData.iUpRate]);
            canvas.drawText("Upper RT - " + temp + " ppm", (float) x / 20, y, pdfParaPaint);
        }

        //If 297 then if R mode ON then show R para
        y += 50;
        if (CommonData.iPacerSelect == 0x19) {
            if (CommonData.iMode >= 8 && CommonData.iMode <= 13)//If R mode ON
            {
                //Target Rate
                temp = String.valueOf(CommonData.uprArray297[CommonData.iSensorRate]);
                canvas.drawText("Target Upper Rate - " + temp + " PPM", (x / (float) 20.0), y, pdfParaPaint);
                // ARC
                if (CommonData.bARC) {
                    canvas.drawText("ARC - ON", 14 * x / (float) 20.0, y, pdfParaPaint);
                } else {
                    canvas.drawText("ARC - OFF", 14 * x / (float) 20.0, y, pdfParaPaint);
                }
                y += 50;
                // Slope
                temp = String.valueOf(CommonData.slpArray[CommonData.iSlope]);
                canvas.drawText("Slope - " + temp, (x / (float) 20.0), y, pdfParaPaint);
                // Up Time
                temp = String.valueOf(CommonData.upTimeArray[CommonData.iuptime]);
                canvas.drawText("Up Time - " + temp + " S", 7 * x / (float) 20.0, y, pdfParaPaint);
                // Down Time
                temp = String.valueOf(CommonData.dnTimeArray[CommonData.iDownTime]);
                canvas.drawText("Down Time - " + temp + " m", 14 * x / (float) 20.0, y, pdfParaPaint);
            }

        }

    }
    //Show Charak Para
    private void showParaCharak747(Canvas canvas, int x, int y) {
        String temp;
        int lineGap=30;

        //Show Battery
        //double bat = (312.32 / (255 - CommonData.iBat));
        double bat = (313.65 / (255 - CommonData.iBat));
        //temp = new DecimalFormat("##.##").format(bat) + " V";
        //Check for battery level
        if (bat<2.31) {

            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.eol);
        }
        else if (bat <2.52){
            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.eri);

        }
        else
        {
            temp = getString(R.string.bat)+new DecimalFormat("##.##").format(bat)+ getString(R.string.empty)+getString(R.string.volt)+getString(R.string.empty)+getString(R.string.ok);

        }
//canvas.drawText("Bat -  " + temp, x / 20, y, pdfParaPaint);
        canvas.drawText(temp, x / 20, y, pdfParaPaint);

        //Show Remaining Life
        remLifeCalc();
        canvas.drawText("Expected Life - " + CommonData.remYear +" Year "+CommonData.remMonth+" month" ,7*x/20,y,pdfParaPaint);

        y += lineGap+10;
        //Mode
        temp = CommonData.modeArray747[CommonData.iMode];
        canvas.drawText("Mode -  " + temp, (float)  x / 20, y, pdfParaPaint);
        //Rate
        temp = String.valueOf(CommonData.rateArray747[CommonData.iRate]);
        canvas.drawText("Rate - " + temp + " ppm", (float) 7 * x / 20, y, pdfParaPaint);


        //Atrium CommonData
        y += lineGap+10;//50;
        if ((CommonData.iMode>=4 && CommonData.iMode<=18)||(CommonData.iMode>=22 && CommonData.iMode<=24)) //AAI (R) to DDD (R) Modes
        {
            if (CommonData.iMode != 7 && CommonData.iMode != 12 && CommonData.iMode != 9 && CommonData.iMode != 15) //if not OAO ,ODO, VDD(R)
            {
                //A Amplitude
                temp = String.valueOf(CommonData.amparray297[CommonData.iAAmp]);
                canvas.drawText("A AMP - " + temp + " V", (float) x / 20, y, pdfParaPaint);
                //A PW
                temp = String.valueOf(CommonData.pwArray747[CommonData.iAPW]);
                canvas.drawText("A PW - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);
                //A Pace Polarity
                temp = String.valueOf(CommonData.polArray[CommonData.iAPacepol]);
                canvas.drawText("A Pol - " + temp, (float) 14 * x / 20, y, pdfParaPaint);
                y += lineGap;//50;
            } //END of if not OAO ,ODO, VDD(R)
            //A Sensing
            if (CommonData.iMode != 5 && CommonData.iMode != 23 && CommonData.iMode != 10 && CommonData.iMode != 16 && CommonData.iMode != 13 && CommonData.iMode != 18) { //If not AOO(R),DOO(R),DVI(R)

                temp = String.valueOf(CommonData.aSenArray[CommonData.iASen]);
                canvas.drawText("A Sens - " + temp + " mV", (float) x / 20, y, pdfParaPaint);
                //S Polarity
                temp = String.valueOf(CommonData.polArray[CommonData.iSenPol]);
                canvas.drawText("S Pol - " + temp, (float) 7 * x / 20, y, pdfParaPaint);
                //AF
                if (CommonData.bATAF)
                    temp = "AF -  ON";
                else
                    temp = "AF -  OFF";
                canvas.drawText(temp, (float) 14 * x / 20, y, pdfParaPaint);
                y += lineGap;//50;
                //A Refractory
                temp = String.valueOf(CommonData.refArray747[CommonData.iARef]);
                canvas.drawText("A Ref - " + temp + " ms", (float) x / 20, y, pdfParaPaint);
                //PVARP
                temp = String.valueOf(CommonData.refArray747[CommonData.iPvarp]);
                canvas.drawText("PVARP - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);
            }//END If not AOO,DOO(R),DVI(R)
        } //END of AAI (R) to DDD (R) Modes
        //Ventricle CommonData
        if ((CommonData.iMode>=0 && CommonData.iMode<=3)||(CommonData.iMode>=8 && CommonData.iMode<=21)) //VVI (R) to DDD (R) Modes
        {
            y += lineGap;//50;
            y += lineGap;//50;

            if (CommonData.iMode != 3 && CommonData.iMode != 12 ) //if not OVO ,ODO
            {
                //V Amplitude
                temp = String.valueOf(CommonData.amparray297[CommonData.iAmp]);
                canvas.drawText("V Amp - " + temp + " V", (float) x / 20, y, pdfParaPaint);
                //V PW
                temp = String.valueOf(CommonData.pwArray747[CommonData.iPW]);
                canvas.drawText("VPW - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);
                //V Pace Polarity
                temp = String.valueOf(CommonData.polArray[CommonData.iPacePol]);
                canvas.drawText("V Pol - " + temp, (float) 14 * x / 20, y, pdfParaPaint);
                y += lineGap;//50;
            }//END of if not OVO ,ODO
            // V Sensing
            if (CommonData.iMode != 1 && CommonData.iMode != 10 && CommonData.iMode != 16 && CommonData.iMode != 19 ) { //If not VOO,DOO(R)
                temp = String.valueOf(CommonData.senArray297[CommonData.iSen]);
                canvas.drawText("V Sens - " + temp + " mV", (float) x / 20, y, pdfParaPaint);
                //Polarity
                temp = String.valueOf(CommonData.polArray[CommonData.iSenPol]);
                canvas.drawText("S Pol - " + temp, (float) 7 * x / 20, y, pdfParaPaint);
                //AM
                if (CommonData.bPin297AM)
                    temp = "AM -  ON";
                else
                    temp = "AM -  OFF";
                canvas.drawText(temp, (float) 14 * x / 20, y, pdfParaPaint);
                y += lineGap;//50;
                //V Refractory
                temp = String.valueOf(CommonData.refArray747[CommonData.iRef]);
                canvas.drawText("V Ref - " + temp + " ms", (float) x / 20, y, pdfParaPaint);
                //Blanking
                temp = String.valueOf(CommonData.blnkArray[CommonData.iBlnk]);
                canvas.drawText("Blanking - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);
            }// END of If not VOO,DOO(R)
        }//END of VVI (R) to DDD (R) Modes

        y+=lineGap;
        y+=lineGap;
        //AV Delay
        if (CommonData.iMode==8 || CommonData.iMode==10 ||CommonData.iMode==11||CommonData.iMode==13||
                CommonData.iMode==14 || CommonData.iMode==16 ||CommonData.iMode==17||CommonData.iMode==18) {//if DDD(R)DOO(R)DDI(R)DVI(R)
            temp = String.valueOf(CommonData.aviArray[CommonData.iPAVI]);
            canvas.drawText("P AVI - " + temp + " ms", (float) x / 20, y, pdfParaPaint);
        }
        if (CommonData.iMode==8 || CommonData.iMode==9 ||CommonData.iMode==14||CommonData.iMode==15) {//if DDD(R)VDD(R)
            temp = String.valueOf(CommonData.aviArray[CommonData.iSAVI]);
            canvas.drawText("S AVI - " + temp + " ms", (float) 7 * x / 20, y, pdfParaPaint);
        }
        y+=lineGap;
        //AV Hyst and Search
        if (CommonData.iMode==8 || CommonData.iMode==9 ||CommonData.iMode==11||CommonData.iMode==13||
                CommonData.iMode==14 || CommonData.iMode==15 ||CommonData.iMode==17||CommonData.iMode==18) {//if DDD(R)VDD(R)DDI(R)DVI(R)
            temp = String.valueOf(CommonData.avhArray[CommonData.iAVHyst]);
            canvas.drawText("AV Hyst - " + temp + " ms", (float) x / 20, y, pdfParaPaint);

            if (CommonData.bAVHSrch)
                temp = "Search - ON";
            else
                temp = "Search - OFF";
            canvas.drawText(temp, (float) 7 * x / 20, y, pdfParaPaint);
        }
        // Hyst and Search
        if (CommonData.iMode==0 || CommonData.iMode==2 ||CommonData.iMode==4||CommonData.iMode==6 ) {//if VVI VVT AAI AAT
            if (CommonData.bHystON) {
                temp = String.valueOf(CommonData.hystArray747[CommonData.ihystVAL]);
                canvas.drawText("Hyst - " + temp + " bpm", (float) x / 20, y, pdfParaPaint);

                if (CommonData.bHystSrchON)
                    temp = "Search - ON";
                else
                    temp = "Search - OFF";
                canvas.drawText(temp, (float) 7 * x / 20, y, pdfParaPaint);
            }
            else
                canvas.drawText("Hyst -  ---" , (float) x / 20, y, pdfParaPaint);
        }
        //Auto pol
        if (CommonData.bAutoPol)
            temp="Auto Pol - ON";
        else
            temp="Auto Pol - OFF";
        canvas.drawText(temp,(float) 14*x/20,y,pdfParaPaint);
        y+=lineGap;

        //Upper Rate
        if (CommonData.iMode==8 || CommonData.iMode==9 ||CommonData.iMode==14||CommonData.iMode==15) {//if DDD(R)VDD(R)
            temp = String.valueOf(CommonData.uprArray297[CommonData.iTrigUprRate]);
            canvas.drawText("Upper Rate - " + temp + " ppm" + " - Wenkebach", (float) x / 20, y, pdfParaPaint);
        }
        //Upper rate in SST mode
        if (CommonData.iMode==2 || CommonData.iMode==6 ||CommonData.iMode==21||CommonData.iMode==24) {//if VVT(R)AAT(R)
            temp = String.valueOf(CommonData.uprArray297[CommonData.iTrigUprRate]);
            canvas.drawText("Upper Rate - " + temp + " ppm" , (float) x / 20, y, pdfParaPaint);
        }
        //MRI
        if (CommonData.bMRI)
            temp="MRI Safe - ON";
        else
            temp="MRI Safe - OFF";
        canvas.drawText(temp,(float) 14*x/20,y,pdfParaPaint);

        //A Tachy Para
        y+=lineGap;
        y+=lineGap;

        if (CommonData.bATEna)
        {
            temp=String.valueOf(CommonData.rateArray747[CommonData.iATRate]);
            canvas.drawText("AT Rate - "+temp+" ppm",(float) x/20,y,pdfParaPaint);
            temp= CommonData.atEnExCounts[CommonData.iATEnt] +"/"+ CommonData.atEnExCounts[CommonData.iATExt];
            canvas.drawText("Entry/Exit  - "+temp,(float) 7*x/20,y,pdfParaPaint);
        }
        //else{
        //  canvas.drawText("AT Rate - --- ppm",x/20,y,pdfParaPaint);
        //canvas.drawText("Entry/Exit  - ---",7*x/20,y,pdfParaPaint);
        //}

        // RR Para
        y+=lineGap;
        y+=lineGap;
        if (CommonData.iMode>=14 && CommonData.iMode<=24)
        {
            temp=String.valueOf(CommonData.uprArray297[CommonData.iSensorRate]);
            canvas.drawText("Target Rate - "+temp+" ppm",(float) x/20,y,pdfParaPaint);
            temp=String.valueOf(CommonData.slpArray[CommonData.iSlope]);
            canvas.drawText("Slope  - "+temp,(float) 8*x/20,y,pdfParaPaint);
            if (CommonData.bARC)
                canvas.drawText("ARC  - ON",(float) 14*x/20,y,pdfParaPaint);
            else
                canvas.drawText("ARC  - OFF",(float) 14*x/20,y,pdfParaPaint);

            y+=lineGap;
            temp=String.valueOf(CommonData.upTimeArray[CommonData.iuptime]);
            canvas.drawText("Up Time - "+temp+" S",(float) x/20,y,pdfParaPaint);
            temp=String.valueOf(CommonData.dnTimeArray[CommonData.iDownTime]);
            canvas.drawText("Down Time  - "+temp+" min",(float) 7*x/20,y,pdfParaPaint);

        }

    }
    //End Charak Para
    //Show Statistics
    // For 8820 / 297
    private void showStat8820AM297(Canvas pdfCanvas, int x, int y) {
        double percPace = 0, percSens = 0, perS80 = 0, perS100 = 0, perS120 = 0, perS140 = 0, perSG140 = 0;
        long tempTotal;
        tempTotal = CommonData.cntPace + CommonData.cntSens;
        if (tempTotal > 0) {
            percPace = ((CommonData.cntPace * 100) / (double) tempTotal);
            percSens = ((CommonData.cntSens * 100) / (double) tempTotal);

            perS80 = ((CommonData.cntL80 * 100) / (double) tempTotal);
            perS100 = ((CommonData.cnt100 * 100) / (double) tempTotal);
            perS120 = ((CommonData.cnt120 * 100) / (double) tempTotal);
            perS140 = ((CommonData.cnt140 * 100) / (double) tempTotal);
            perSG140 = ((CommonData.cntG140 * 100) / (double) tempTotal);
        }
        pdfCanvas.drawText("Statistics Counts", (float) x / 2, y, pdfTitlePaint);
        y += 50;
        //Total Pace
        pdfCanvas.drawText("Pace Counts - " + CommonData.cntPace + "( " + new DecimalFormat("##.##").format(percPace) + " %)", (float) x / 20, y, pdfParaPaint);
        //Noise Counts
        pdfCanvas.drawText("Noise Counts - " + CommonData.cntNoise, (float) 12 * x / 20, y, pdfParaPaint);

        y += 50;
        //Total Sens
        pdfCanvas.drawText("Sens Counts - " + CommonData.cntSens + "( " + new DecimalFormat("##.##").format(percSens) + " %)", (float) x / 20, y, pdfParaPaint);
        //Noise pace Counts
        pdfCanvas.drawText("Noise pacing - " + CommonData.cntNoisePace, (float) 12 * x / 20, y, pdfParaPaint);

        y += 50;
        pdfCanvas.drawText("Sens < 80ppm - " + CommonData.cntL80 + "( " + new DecimalFormat("##.##").format(perS80) + " %)", (float) x / 20, y, pdfParaPaint);
        y += 50;
        pdfCanvas.drawText("Sens < 100ppm - " + CommonData.cnt100 + "( " + new DecimalFormat("##.##").format(perS100) + " %)", (float) x / 20, y, pdfParaPaint);
        //showS100.setText(String.valueOf(para.cnt100).toString()); //Sens less than 100ppm
        y += 50;
        pdfCanvas.drawText("Sens < 120ppm - " + CommonData.cnt120 + "( " + new DecimalFormat("##.##").format(perS120) + " %)", (float) x / 20, y, pdfParaPaint);
        //showS120.setText(String.valueOf(para.cnt120).toString()); //Sens less than 120ppm
        y += 50;
        pdfCanvas.drawText("Sens < 140ppm - " + CommonData.cnt140 + "( " + new DecimalFormat("##.##").format(perS140) + " %)", (float) x / 20, y, pdfParaPaint);
        //showS140.setText(String.valueOf(para.cnt140).toString()); //Sens less than 140ppm
        y += 50;
        pdfCanvas.drawText("Sens > 140ppm - " + CommonData.cntG140 + "( " + new DecimalFormat("##.##").format(perSG140) + " %)", (float) x / 20, y, pdfParaPaint);
        //showSG140.setText(String.valueOf(para.cntG140).toString()); //Sens more than 140ppm

        //Draw Chart of counts
        //X-Y lines
        int hPDF = pdfCanvas.getHeight();
        int wPDF = pdfCanvas.getWidth();
        int xPos = wPDF / 20, baseLine = hPDF - 50;

        pdfCanvas.drawLine(xPos + 20, baseLine, wPDF - 50, baseLine, pdfChartPaint); //X Line
        pdfCanvas.drawLine(xPos + 20, baseLine, xPos + 20, baseLine - 400, pdfChartPaint); //Y Line

        //Pace Sens Bar Graph
        xPos += 40;
        pdfCanvas.drawText("Pace", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(percPace), xPos, baseLine - (4 * (float) percPace) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * (float) percPace), xPos + 30, baseLine, pdfChartPaint); // Pace

        xPos += 50;
        pdfCanvas.drawText("Sens", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(percSens), xPos, baseLine - (4 * (float) percSens) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * (float) percSens), xPos + 30, baseLine, pdfChartPaint); // Sens

        xPos += 70;
        pdfCanvas.drawText("S<80", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perS80), xPos, baseLine - (4 * (float) perS80) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * (float) perS80), xPos + 30, baseLine, pdfChartPaint); // Sens <80

        xPos += 60;
        pdfCanvas.drawText("S<100", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perS100), xPos, baseLine - (4 * (float) perS100) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * (float) perS100), xPos + 30, baseLine, pdfChartPaint); // Sens <100

        xPos += 60;
        pdfCanvas.drawText("S<120", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perS120), xPos, baseLine - (4 * (float) perS120) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * (float) perS120), xPos + 30, baseLine, pdfChartPaint); // Sens <120

        xPos += 60;
        pdfCanvas.drawText("S<140", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perS140), xPos, baseLine - (4 * (float) perS140) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * (float) perS140), xPos + 30, baseLine, pdfChartPaint); // Sens <140

        xPos += 60;
        pdfCanvas.drawText("S>140", xPos, baseLine + 20, pdfChartPaint);
        pdfCanvas.drawText(new DecimalFormat("##.##").format(perSG140), xPos, baseLine - (4 * (float) perSG140) - 10, pdfChartPaint);
        pdfCanvas.drawRect(xPos, baseLine - (4 * (float) perSG140), xPos + 30, baseLine, pdfChartPaint); // Sens >140
    }

    private void selectDate() {

        int day, mon, year;
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        mon = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //String strDay,strMon,strYear;
                month += 1;
                String strTitle = dayOfMonth + "/" + month + "/" + year;
                pdfimplDate.setText(strTitle);
                //pdfimplDate.setText(dayOfMonth + "/" + month + "/" + year);

            }
        }, year, mon, day);

        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.DKGRAY);
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.DKGRAY);

    }

}


