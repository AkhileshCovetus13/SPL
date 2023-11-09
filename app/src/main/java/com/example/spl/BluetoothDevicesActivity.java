package com.example.spl;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.spl.helper.CommonData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class BluetoothDevicesActivity extends Activity {

    // private final static String APP_NAME="SPL_APP";
    private static final int STAT_LISTENING = 1;
    private static final int STAT_CONNECTING = 2;
    private static final int STAT_CONNECTED = 3;
    private static final int STAT_CONNECTIONFAILED = 4;
    private static final int STAT_MSG_RECEIVED = 5;
    private final static UUID spluuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private int[] teleData = new int[75];//[55];
    private BluetoothAdapter btAdapter;
    private boolean bStatSenCnt = false;
    private ProgressBar mProgress;
    private final ArrayList<BluetoothDevice> btProgrammer = new ArrayList<>();
    private final ArrayList<String> btArrayList = new ArrayList<>();
    private ArrayAdapter<String> btArrayAdapter;
    private int nPairedDevices = 0;
    private ClientClassBT clientBT;
    public CountDownTimer waitTimer;
    private static final int BT_ENA = 1;
    TextView mTvRunAsDemo;
    private boolean bDataReadOK=true,bShowVRR=false;
    public boolean bBTConnection = false, b5CFlg = false;
    public boolean bthrScreen=false;
    boolean bReset=false;
    private static BluetoothDevicesActivity mainActInstance;
    private byte[] interrogateBT;
    private byte[] bytesToSend ;
    private DataTransferClass dataTransfer;
    int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    public static BluetoothDevicesActivity getInstance()
    {
        return mainActInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btdevice_popup);

        ListView btListView = findViewById(R.id.btdevice_list);
        mTvRunAsDemo = findViewById(R.id.mTvRunAsDemo);
        mProgress = findViewById(R.id.progressBar2);
        mProgress.getIndeterminateDrawable().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenwd = dm.widthPixels;
        int screenht = dm.heightPixels;
        getWindow().setLayout((int) (screenwd * 0.6), (int) (screenht * 0.5)); //Adjust popup layout size

        btArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, btArrayList) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View lView = super.getView(position, convertView, parent);
                //return super.getView(position, convertView, parent);
                if (position < nPairedDevices)
                    lView.setBackgroundColor(Color.CYAN);
                else
                    lView.setBackgroundColor(Color.rgb(0x50, 0xA0, 0xF0));// light blue

                return lView;
            }
        };
        btListView.setAdapter(btArrayAdapter);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth not Available", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (!btAdapter.isEnabled()) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(btIntent);
                }
                Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(btIntent, BT_ENA);

            } else {
                ShowPairedDevices();
            }
        }
        btListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommonData.mStrBluetoothAddress = btProgrammer.get(position).getAddress();
                finish();
            }
        });

        //Start Discovery of unpaired devices
        if (btAdapter != null) {
            if (btAdapter.isDiscovering())
                btAdapter.cancelDiscovery();

            chkBTPermission();

            btAdapter.startDiscovery();
        }
        //Register receiver for BT devices
        IntentFilter btDevicesToShow = new IntentFilter();
        btDevicesToShow.addAction(BluetoothDevice.ACTION_FOUND);
        btDevicesToShow.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        btDevicesToShow.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        this.registerReceiver(btDeviceReceiver, btDevicesToShow);

        mTvRunAsDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void chkBTPermission() {
        int btPermission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btPermission = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btPermission += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
        }
        if (btPermission != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }

    }

    private final BroadcastReceiver btDeviceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // boolean bDeviceInList = false;
            String action = intent.getAction();
            if (Objects.equals(action, BluetoothDevice.ACTION_FOUND)) {
                //   Toast.makeText(btdevice.this, "FOUND", Toast.LENGTH_SHORT).show();
                BluetoothDevice btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (!btProgrammer.contains(btDevice)) {
                    btArrayList.add(btDevice.getName() + " - " + "(New Device)");
                    btProgrammer.add(btDevice);
                    btArrayAdapter.notifyDataSetChanged();
                }
            } else {
                assert action != null;
                if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                    mProgress.setVisibility(View.VISIBLE);
                    Toast.makeText(BluetoothDevicesActivity.this, "Searching Device", Toast.LENGTH_SHORT).show();
                } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                    mProgress.setVisibility(View.INVISIBLE);
                    Toast.makeText(BluetoothDevicesActivity.this, "Device Searching Finished", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BT_ENA) {
            ShowPairedDevices();
        }
    }

    private void ShowPairedDevices() {
        if (btAdapter != null) {
            Set<BluetoothDevice> device = btAdapter.getBondedDevices();
            if (device.size() > 0) {
                btArrayList.clear();
                btProgrammer.clear();

                for (BluetoothDevice bt : device) {
                    //btArrayList.add(bt.getName()+"-"+bt.getAddress()+" (Paired)");
                    btArrayList.add(bt.getName() + " - " + "{Paired}");
                    btProgrammer.add(bt);
                    nPairedDevices += 1;
                }
                btArrayAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(btDeviceReceiver);
    }

    private void connectProgrammer(String address) {
        //mprogressbar.setVisibility(View.VISIBLE);
        //String strDev="00:19:09:10:02:4A"; // For HC05
        // strDev="30:14:08:26:17:76"; // For HC06

        //startActivity(new Intent(getApplicationContext(),btdevice.class));
        if (address != null) {
            // mprogressbar.setVisibility(View.VISIBLE);
            clientBT = new ClientClassBT(btAdapter.getRemoteDevice(address));
            clientBT.start();
            timeOut(30000);// wait for 30S
            waitTimer.start();
        } else
            Toast.makeText(getApplicationContext(), "No Device is Selected", Toast.LENGTH_SHORT).show();


    }

    private class ClientClassBT extends Thread {
        private BluetoothSocket clientSocket = null;

        ClientClassBT(BluetoothDevice device) {

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
            btAdapter.cancelDiscovery();
            try {
                if (clientSocket != null) {
                    if (!clientSocket.isConnected()) {
                        clientSocket.connect();
                        progConnected = true;
                        // Message message=Message.obtain();
                        // message.what=STAT_CONNECTED;
                        //handler.sendMessage(message);
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();
                Message message = Message.obtain();
                message.what = STAT_CONNECTIONFAILED;
                //handler.sendMessage(message);

                try {
                    if (clientSocket != null) {
                        clientSocket.close();
                        clientSocket = null;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            }
            if (progConnected)
            {
                progConnected=false;

                dataTransfer = new DataTransferClass(clientSocket);
                dataTransfer.start();

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

    public void timeOut(long timems) {
        waitTimer = new CountDownTimer(timems, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //  mprogressbar.setVisibility(View.GONE);
                bStatSenCnt = false;
//                para.logFileWrite("Error in Communication", Parameters.pacerData); // Write Log File
                Toast.makeText(getApplicationContext(), "Error in Communication", Toast.LENGTH_SHORT).show();
            }
        };
    }

    class DataTransferClass extends Thread {
        BluetoothSocket dtSocket;
        final InputStream inputStream;
        final OutputStream outputStream;

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

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what)
            {
                case STAT_LISTENING:
                case STAT_CONNECTING:

                    break;
                case STAT_CONNECTED:
                    waitTimer.cancel();

                    break;
                case STAT_CONNECTIONFAILED:

                    waitTimer.cancel();
                    //mprogressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Device Could not be Connected", Toast.LENGTH_SHORT).show();
                    break;
                case STAT_MSG_RECEIVED:

                    teleData=(int[]) msg.obj;
                    //System.arraycopy(teleData,0,para.pacerData,0,54);
                    //if (para.VARIFYCRC(teleData)==true) {
                    //***** For Debug *****
                    if((teleData[0]==6 || teleData[0]==7) && (teleData[1]==34 || teleData[1]>=2)) //ECG Data
                    {
                        //do nothing  Debug
                        String strBytes = new String();
                        strBytes = "";
                        strBytes = String.valueOf(msg.arg1).toString() + "-";

                        for (int p = 0; p < msg.arg1; p++) {
                            strBytes = strBytes + " " + String.valueOf(teleData[p]).toString();
                        }

                        System.out.println("<><>DataIF "+strBytes);
                    }
                    else {
                        String strBytes = new String();
                        strBytes = "";
                        strBytes = String.valueOf(msg.arg1).toString() + "-";

                        for (int p = 0; p < msg.arg1; p++) {
                            strBytes = strBytes + " " + String.valueOf(teleData[p]).toString();
                        }

                        System.out.println("<><>DataElse "+strBytes);
                    }
                    //**********end Debug ********
                    if((teleData[0]==6 || teleData[0]==7) && (teleData[1]==34 || teleData[1]>=2)) //ECG Data
                    {
                        //getPacerData();
                    }
                    else if (CommonData.VARIFYCRC(teleData))
                    {
                        waitTimer.cancel();
                        //getPacerData();
                    }
                    else
                    {
                        waitTimer.cancel();
                        //mprogressbar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error (CRC CheckSum)", Toast.LENGTH_SHORT).show();

                    }
                    bDataReadOK = true;
                    break;
                default:
                    //mprogressbar.setVisibility(View.GONE);
                    break;
            }
            return false;
        }

    });

    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        if (btAdapter.isEnabled() && !bBTConnection) {
            //connectProgrammer();
            //startActivity(new Intent(getApplicationContext(), btdevice.class));
        }

    }

    /*  void getPacerData()
     {
         //If marker window ON
         if((teleData[0]==6 || teleData[0]==7) && (teleData[1]==34 || teleData[1]>=2)) //ECG / Marker Data
         {
             if(bMarkViewON) {
                 if(teleData[0]==6) {
                     System.arraycopy(teleData, 0, CommonData.markerData, 0, teleData[1] + 5);
                 }
                 else{
                     System.arraycopy(teleData, 0, CommonData.markerData, 39, teleData[1] +  5);
                 }

                 if(!bMarkInstCreated){
                     bMarkInstCreated=true;
                     markClsInstance = marker.getInstance();
                 }
                 if(teleData[0]==7) {

                     markClsInstance.draw_marker();
                 }
             }
             else
                 waitTimer.cancel();
         }
         else if(teleData[0]==8 && teleData[1]==4 && (teleData[2]==0x28 ))// Marker/Threshold Error
         {
             waitTimer.cancel();
             if (bMarkViewON) {
                 markClsInstance.butMarker.setChecked(false);
                 System.arraycopy(CommonData.pacerData, 0, CommonData.pacerDataPROG, 0, CommonData.pacerData[1] + 5);//Restore Para
                 markClsInstance.butThrNext.setEnabled(false);
                 markClsInstance.bFileWrite=false;
                 markClsInstance.bMarkON=false;
                 CommonData.bThrStop =false;

             }
             Toast.makeText(getApplicationContext(), "Communication Lost", Toast.LENGTH_SHORT).show();
         }
         else if(teleData[0]==8 && teleData[1]==1 && (teleData[2]==6 || teleData[2]==0x0D))//wand Interrogate button pressed
         {
             //mprogressbar.setVisibility(View.VISIBLE);
             CommonData.actionCode =1;
             createRequest((byte) 1);
             timeOut(5000);
             waitTimer.start();
         }
         else if(teleData[0]==5 && (teleData[2]==1 || teleData[2]==4 || teleData[2]==0x64 || teleData[2]==7|| teleData[2]==0x11 ||
                 teleData[2]==0x1B) && teleData[3]==0x4F)// Different Pacer ID so send request to get pacer iD
         {
             //if (para.actionCode==4 || para.actionCode==0x64 ||para.actionCode==0x11 )//prog,Impd,Marker
             if (CommonData.actionCode != 1)
             {
                 CommonData.actionCode =0;
                 //mprogressbar.setVisibility(View.GONE);

                 if (bMarkViewON) {
                     markClsInstance.butMarker.setChecked(false);
                     System.arraycopy(CommonData.pacerData, 0, CommonData.pacerDataPROG, 0, CommonData.pacerData[1] + 5);//Restore Para
                     markClsInstance.butThrNext.setEnabled(false);
                     markClsInstance.bFileWrite=false;
                     markClsInstance.finish();
                 }
                 if (teleData[2] == 7)
                 {
                     *//*statistics statClsInstance = statistics.getInstance();
                    statClsInstance.waitCirBar.setVisibility(View.GONE);
                    statClsInstance.finish();*//*
                }
                //showAlertMessage(MainActivity.this,"Alert","Pacemaker is not Interrogated, First Interrogate",true,false);
            }
            else {
                createRequest((byte) 2);
            }
        }
        else if(teleData[0]==5 && teleData[2]==1 && teleData[3]==0x64)// Reposition Wand
        {

            CommonData.actionCode =0;
            mprogressbar.setVisibility(View.GONE);
            //howAlertMessage(MainActivity.this,"Alert","Please Reposition Wand",true,false);

        }
        else if(teleData[0]==4 && teleData[2]==2 && teleData[1]<15  )// Store PacerID and Srno
        {

            CommonData.iPacerSelect =teleData[3];
            CommonData.iPacerID =teleData[3];
            CommonData.iSrnoH =teleData[4];
            CommonData.iSrnoL =teleData[5];
            // para.iSrno= (para.iSrnoH * 256) + para.iSrnoL;

            createRequest((byte)6); //POR1
        }
        else if(teleData[0]==4 && teleData[2]==6 && teleData[1]<15 )// POR1 response,  Send POR2, threshold next
        {
            if (CommonData.bThrNext) {
                CommonData.bThrNext = false;
                thresholdNext();

                //Toast.makeText(getApplicationContext(), "Threshold Next", Toast.LENGTH_SHORT).show();
                //markClsInstance.txtShowThrVal.setText(String.valueOf(para.amparray297[para.iAmp]).toString() + " V");
                //Start Timeout timer
                waitTimer.cancel();
                timeOut(3500);
                waitTimer.start();// To check marker is ON

            }
            else if(CommonData.bThrStop)
            {
                CommonData.bThrStop =false;
                Toast.makeText(getApplicationContext(), "Threshold End", Toast.LENGTH_SHORT).show();
            }
            else{
                createRequest((byte)18); //POR 2

            }
        }
        else if(teleData[0]==4 && teleData[2]==18 && teleData[1]<15 )// Send new interrogate request
        {
            //fragInit=false;
            // Change Screen as per Model
            if (CommonData.iPacerID ==0x0E)
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
            }
            createRequest((byte) 1);
        }
        else if(teleData[0]==4 && teleData[2]==1 && teleData[1]<40 )// Receive Pacer Data Interrogation
        {
            mprogressbar.setVisibility(View.GONE);
            System.arraycopy(teleData,0, CommonData.pacerData,0,teleData[1]+5);
            CommonData.decodeBytes();

            System.arraycopy(CommonData.pacerData,0, CommonData.pacerDataPROG,0, CommonData.pacerData[1]+5); //copy data to prog array
            //CommonData.logFileWrite("Interrogation OK", CommonData.pacerData); // Write Log File
            Toast.makeText(getApplicationContext(),"Interrogation OK",Toast.LENGTH_SHORT).show();
            //Load file path and name
            CommonData.filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SPLLOG/logfiles/";
            CommonData.fileName = CommonData.iSrno + ".log";
            refreshScreen(CommonData.iPacerSelect);
            waitTimer.cancel();
            //Toast.makeText(getApplicationContext(),"Interrogation OK",Toast.LENGTH_SHORT).show();
            setMenuItemState(true,true);

        }

        else if(teleData[0]==4 && teleData[2]==4 && teleData[1]<40 )// Receive Pacer Data Program
        {
            mprogressbar.setVisibility(View.GONE);
            System.arraycopy(CommonData.pacerDataPROG,6, CommonData.pacerData,6, CommonData.pacerDataPROG[1]+5);
            //CommonData.logFileWrite("Programming OK", CommonData.pacerData); // Write Log File
            CommonData.save_tPara();
            refreshScreen(CommonData.iPacerSelect);
            Toast.makeText(getApplicationContext(),"Programming OK",Toast.LENGTH_SHORT).show();

        }
        else if(teleData[0] == 3 && teleData[1] == 1 && teleData[2] == 0x6) {
            //Do Nothing
        }
        else if(teleData[0] == 3 && teleData[1] == 1 && teleData[2] == 0x64) {
            //Do Nothing
        }
        //Impedance
        else if(teleData[0] == 4 && teleData[1] == 3 && teleData[2] == 0x64) {
            if (CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 13 || CommonData.iPacerSelect == 0x1B ||
                    CommonData.iPacerSelect == 0x1D || CommonData.iPacerSelect == 0x1E) //Receive Impedance
            {
                waitTimer.cancel();
                int idiv,TR2;
                if(teleData[3] >= 192)
                    idiv = 242;
                else if(teleData[3] >=170)
                    idiv = 250;
                else
                    idiv = 255;

                if(teleData[3] <=0) //To avoid invalid calculation (divide by zero)
                    TR2 = 50;
                else {
                    double tmp;

                    tmp= -log( (double) teleData[3]/idiv);
                    TR2 = (int) (256 / tmp);

                }
                *//*mprogressbar.setVisibility(View.GONE);
                CommonData.logFileWrite("Impedance - "+setStrImpVal(TR2)+" Ohms", CommonData.pacerData); // Write Log File
                showAlertMessage(MainActivity.this,"Lead Impedance (Ohms)",
                        "Impedance - "+setStrImpVal(TR2)+" Ohms",true,false);*//*

     *//* if (TR2>1500) {
                        para.logFileWrite("Impedance (Ohms) - Greater Than 1500", Parameters.pacerData); // Write Log File
                        showAlertMessage(MainActivity.this,"Impedance (Ohms)", "Greater Than 1500",true,false);
                    }
                    else if (TR2<250) {
                        para.logFileWrite("Impedance (Ohms) - Less Than 250", Parameters.pacerData); // Write Log File
                        showAlertMessage(MainActivity.this,"Impedance (Ohms)", "Less Than 250",true,false);
                    }
                    else {
                        para.logFileWrite("Impedance (Ohms) - " + TR2, Parameters.pacerData); // Write Log File
                        showAlertMessage(MainActivity.this,"Impedance (Ohms)", String.valueOf(TR2),true,false);
                    }*//*
                refreshScreen(CommonData.iPacerSelect);

            }
            if (CommonData.iPacerSelect==12 || CommonData.iPacerSelect==24) // Charak DDD (AM/AF)
            {
                waitTimer.cancel();
                int impdData,impdVal;
                String strImpd="";
                //**** A Impedance ******
                impdData=teleData[3];
                if (impdData<=0)
                    impdData=1 ; //'to avoide divide by zero
                impdVal=-(int)((1/log((double)impdData/256))*191);

               // mprogressbar.setVisibility(View.GONE);
                if ((CommonData.iMode >=4 && CommonData.iMode<=18) || (CommonData.iMode >=22 && CommonData.iMode<=24) ) {
                    //CommonData.logFileWrite("A Impedance "+ setStrImpVal(impdVal)+" Ohms", CommonData.pacerData); // Write Log File

                    strImpd += "A Impedance "+setStrImpVal(impdVal)+" Ohms"+'\n';
                       *//* if (impdVal > 1500) {
                            para.logFileWrite("A Impedance >>> 1500 (Ohms)", Parameters.pacerData); // Write Log File
                            //showAlertMessage(MainActivity.this, "A Impedance (Ohms)", "Greater Than 1500", true, false);
                            strImpd += "A Impedance  >>> 1500 (Ohms)"+'\n';
                        } else if (impdVal < 250) {
                            para.logFileWrite("A Impedance <<< 250 (Ohms)", Parameters.pacerData); // Write Log File
                            //showAlertMessage(MainActivity.this, "A Impedance (Ohms)", "Less Than 250", true, false);
                            strImpd += "A Impedance  <<< 250 (Ohms)"+'\n';
                        } else {
                            para.logFileWrite("A Impedance = " + impdVal + " (Ohms)", Parameters.pacerData); // Write Log File
                           // showAlertMessage(MainActivity.this, "A Impedance (Ohms)", String.valueOf(impdVal), true, false);
                            strImpd += "A Impedance = " + impdVal+" (Ohms)"+'\n';
                        }*//*
                }

                //**** V Impedance ******
                impdData=teleData[4];
                if (impdData<=0)
                    impdData=1 ; //'to avoide divide by zero
                impdVal=-(int)((1/log((double) impdData/256))*202);

                if ((CommonData.iMode >=0 && CommonData.iMode<=2) || (CommonData.iMode >=8 && CommonData.iMode<=21) ) {
                    //CommonData.logFileWrite("V Impedance" +setStrImpVal (impdVal)+" Ohms", CommonData.pacerData); // Write Log File

                    strImpd += "V Impedance " +setStrImpVal (impdVal)+" Ohms";

                }
                showAlertMessage(MainActivity.this, "Lead Impedance (Ohms)", strImpd, true, false);
                refreshScreen(CommonData.iPacerSelect);

            }
            //For CPR Series Pacemaker
            if (CommonData.iPacerSelect==0x13 || CommonData.iPacerSelect==0x10 || CommonData.iPacerSelect==0x1C)
            {
                impd_CPR();
            }

        }
        else if(teleData[0] == 4 && teleData[1] == 2 && teleData[2] == 0x64) {
            mprogressbar.setVisibility(View.GONE);
            int TR2;
            waitTimer.cancel();
            //For CPR Series Pacemaker
            if (CommonData.iPacerSelect==0x13 || CommonData.iPacerSelect==0x10 || CommonData.iPacerSelect==0x1C)
            {
                impd_CPR();
            }
            else {
                if (teleData[3]>=246)
                    teleData[3]=240;
                TR2 = (teleData[3] - 6) * (teleData[3] - 6) / (246 - teleData[3]);
               // CommonData.logFileWrite("Impedance (Ohms) - "+setStrImpVal(TR2), CommonData.pacerData); // Write Log File
               *//* showAlertMessage(MainActivity.this, "Lead Impedance (Ohms)",
                        "Impedance - "+setStrImpVal(TR2)+" Ohms", true, false);*//*

            }
            refreshScreen(CommonData.iPacerSelect);
        }
        //***************************************************
        //******************** Statistics Counts **************************
        // **** Pinnacle R 297 / Pinnacle AM 8820 Stat Counts ***
        else if (teleData[0]== 3 && teleData[1] == 0x12 && teleData[2] == 7) {

            if (!bStatSenCnt) {
                //Store data to decode/write in file
                CommonData.pacerDataPROG[0]=teleData[1]-1;
                CommonData.pacerDataPROG[1]=teleData[1]-1;
                System.arraycopy(teleData,3, CommonData.pacerDataPROG,2,teleData[1]-1);
                bStatSenCnt = true;
            }
            else {
                //Store data to decode/write in file
                CommonData.pacerDataPROG[0]+=teleData[1]-1;
                CommonData.pacerDataPROG[1]+=teleData[1]-1;
                System.arraycopy(teleData,3, CommonData.pacerDataPROG,19,teleData[1]-1);
                bStatSenCnt=false;
            }
        }
        else if (teleData[0]== 4 && teleData[1] == 0x13 && teleData[2] == 7)
        {
            //Store data to decode/write in file
            CommonData.pacerDataPROG[0]+=teleData[1]-1;
            CommonData.pacerDataPROG[1]+=teleData[1]-1;
            System.arraycopy(teleData,3, CommonData.pacerDataPROG,36,teleData[1]-1);

            CommonData.decode_stat_counts();
            bStatSenCnt=false;
            waitTimer.cancel();
            statistics statClsInstance = statistics.getInstance();
            statClsInstance.update_Counts();
            para.logFileWrite("Statistics Read OK", CommonData.pacerDataPROG);
            // Reload pacerdataProg
            System.arraycopy(CommonData.pacerData,0, CommonData.pacerDataPROG,0, CommonData.pacerData[1]+5);
            Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
            refreshScreen(CommonData.iPacerSelect);
            //Load index of Stat in Stat Array
            statClsInstance.loadStatArray();
            // load curIndex with index showing stats
            statClsInstance.curIndex=statClsInstance.statIndexArr.size()-1;

        }
        // **** Pinnacle 8820 Stat Counts ***
        else if (teleData[0]== 3 && teleData[1] == 0x0D && teleData[2] == 7) {
            if (!bStatSenCnt) {
                //Store data to decode/write in file
                CommonData.pacerDataPROG[0]=teleData[1]-1;
                CommonData.pacerDataPROG[1]=teleData[1]-1;
                System.arraycopy(teleData,3, CommonData.pacerDataPROG,2,teleData[1]-1);
                bStatSenCnt = true;
            }
            else {
                //Store data to decode/write in file
                CommonData.pacerDataPROG[0]+=teleData[1]-1;
                CommonData.pacerDataPROG[1]+=teleData[1]-1;
                System.arraycopy(teleData,3, CommonData.pacerDataPROG,14,teleData[1]-1);
                bStatSenCnt=false;
            }

        }
        else if (teleData[0]== 4 && teleData[1] == 0x0D && teleData[2] == 7)
        {
            //Store data to decode/write in file
            CommonData.pacerDataPROG[0]+=teleData[1]-1;
            CommonData.pacerDataPROG[1]+=teleData[1]-1;
            System.arraycopy(teleData,3, CommonData.pacerDataPROG,26,teleData[1]-1);
            CommonData.decode_stat_counts();
            bStatSenCnt=false;
            waitTimer.cancel();
            statistics statClsInstance = statistics.getInstance();
            statClsInstance.update_Counts();
            //CommonData.logFileWrite("Statistics Read OK", CommonData.pacerDataPROG);
            Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
            System.arraycopy(CommonData.pacerData,0, CommonData.pacerDataPROG,0, CommonData.pacerData[1]+5);
            refreshScreen(CommonData.iPacerSelect);
            //startActivity(new Intent(getApplicationContext(),statistics.class));
            //Load index of Stat in Stat Array
            statClsInstance.loadStatArray();
            // load curIndex with index showing stats
            statClsInstance.curIndex=statClsInstance.statIndexArr.size()-1;
        }
        //Charak DDDR Counts (Regular and AM/AF)
        else if (teleData[0]==4 && teleData[1]==0x1C && teleData[2]==7)
        {
            //Store data to decode/write in file
            CommonData.pacerDataPROG[0]+=teleData[1]-1;
            CommonData.pacerDataPROG[1]+=teleData[1]-1;
            System.arraycopy(teleData,3, CommonData.pacerDataPROG,2,teleData[1]-1);

            CommonData.decode_stat_counts();
            bStatSenCnt=false;
            waitTimer.cancel();
            statistics statClsInstance = statistics.getInstance();
            statClsInstance.update_Counts_DDD();
            para.logFileWrite("Statistics Read OK", CommonData.pacerDataPROG);
            // Reload pacerdataProg
            System.arraycopy(CommonData.pacerData,0, CommonData.pacerDataPROG,0, CommonData.pacerData[1]+5);
            Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
            refreshScreen(CommonData.iPacerSelect);
            //Load index of Stat in Stat Array
            statClsInstance.loadStatArray();
            // load curIndex with index showing stats
            statClsInstance.curIndex=statClsInstance.statIndexArr.size()-1;
        }
        //New Models Counts (PRC)
        else if (teleData[0]==4 && teleData[1]==0x29 && teleData[2]==7)
        {
            //Store data to decode/write in file
            CommonData.pacerDataPROG[0]+=teleData[1]-1;
            CommonData.pacerDataPROG[1]+=teleData[1]-1;
            System.arraycopy(teleData,3, CommonData.pacerDataPROG,2,teleData[1]-1);

            CommonData.decode_stat_PScnts_N(CommonData.statChamber);
            if (CommonData.statChamber==0)
            {
                CommonData.statChamber=1;
                createRequest((byte)7);
            }
            else {
                bStatSenCnt = false;
                ////**** waitTimer.cancel();
                //psCntFrag statClsInstancePS = psCntFrag.getInstance();
                //statClsInstancePS.updateCntsPS();
                ////  **** para.logFileWrite("Statistics Read OK", Parameters.pacerDataPROG);
                // Reload pacerdataProg
                ////****System.arraycopy(Parameters.pacerData, 0, Parameters.pacerDataPROG, 0, Parameters.pacerData[1] + 5);
                Toast.makeText(getApplicationContext(), "Statistics Read OK", Toast.LENGTH_SHORT).show();
                ////***refreshScreen(Parameters.iPacerSelect);
                //Load index of Stat in Stat Array
                ////***statClsInstance.loadStatArray();
                // load curIndex with index showing stats
                ////****statClsInstance.curIndex = statClsInstance.statIndexArr.size() - 1;
            }
        }
        // Ststistics Reset
        else if (teleData[0]== 4 && teleData[2] == 8)
        {
           // CommonData.logFileWrite("Statistics Reset OK", CommonData.pacerDataPROG);
            Toast.makeText(getApplicationContext(), "Statistics Reset OK", Toast.LENGTH_SHORT).show();
            refreshScreen(CommonData.iPacerSelect);
        }
        //**************************************************
        //**************************************
        //If marker OK
        else if (teleData[0]== 4 &&  teleData[2] == 0x11)
        {
            waitTimer.cancel();
            //CommonData.logFileWrite("Marker ON", CommonData.pacerData); // Write Log File
            markClsInstance.bFileWrite=true; // To write marker data in a file
            markClsInstance.bMarkON=true;

            refreshScreen(CommonData.iPacerSelect);
            Toast.makeText(getApplicationContext(),"Marker ON ",Toast.LENGTH_SHORT).show();
        }
        //If marker OFF
        else if (teleData[0]== 4 &&  teleData[2] == 0x18)
        {
            waitTimer.cancel();
           // CommonData.logFileWrite("Marker OFF", CommonData.pacerData); // Write Log File
            markClsInstance.bFileWrite=false;
            markClsInstance.bMarkON=false;

            refreshScreen(CommonData.iPacerSelect);
            Toast.makeText(getApplicationContext(),"Marker OFF ",Toast.LENGTH_SHORT).show();
        }
        //If Threshold start OK
        else if (teleData[0]== 4 &&  teleData[2] == 0x1B)
        {
            waitTimer.cancel();
            //CommonData.logFileWrite("Threshold ON", CommonData.pacerData); // Write Log File
            markClsInstance.bFileWrite=true; // To write threshold data in a file
            markClsInstance.bMarkON=true;

            refreshScreen(CommonData.iPacerSelect);
            Toast.makeText(getApplicationContext(),"Threshold ON ",Toast.LENGTH_SHORT).show();
            markClsInstance.butThrNext.setEnabled(true);
        }
        //If 3  1  0x1B  then do nothing
        else if (teleData[0]== 3 && teleData[2] == 0x1B)
        {

            //Do nothing
        }
        //If Urgent Program OK
        else if (teleData[0]== 4 &&  teleData[2] == 0x0B)
        {
            //para.logFileWrite("Urgent Programming OK", CommonData.pacerData); // Write Log File
            Toast.makeText(getApplicationContext(), "Urgent Programming OK", Toast.LENGTH_SHORT).show();
            //mprogressbar.setVisibility(View.VISIBLE);
            CommonData.actionCode =1;
            createRequest((byte) 1);
            timeOut(5000);
            waitTimer.start();
        }
        //To Confirm Reset
        else if(teleData[0]== 4 &&  teleData[2] == 0x0A)
        {
            bReset=true;
            //showAlertMessage(MainActivity.this,"Pacemaker RESET","Press OK to confirm pacemaker reset",true,true);

        }
        //To Reset
        else if (teleData[0]== 4 &&  teleData[2] == 0x09)
        {
            //showAlertMessage(MainActivity.this,"Pacemaker RESET","Pacemaker Reset OK",true,false);
            //para.logFileWrite("Pacemaker Reset OK", Parameters.pacerData); // Write Log File
            Toast.makeText(mainActInstance, "Pacemaker Reset OK", Toast.LENGTH_SHORT).show();
            createRequest((byte)0x01); //Interrogate pacemaker after Reset

        }
        // ECG Gain Up
        else if (teleData[0]==4 && teleData[2] == 0x14)
        {
            CommonData.prevGainPos =teleData[3];
            //para.logFileWrite("ECG Gain = " + CommonData.prevGainPos, CommonData.pacerData); // Write Log File
            Toast.makeText(getApplicationContext(), "ECG Gain = " + CommonData.prevGainPos, Toast.LENGTH_SHORT).show();
            //markClsInstance.ecgSens=para.prevGainPos*30 +20;
            //markClsInstance.ecgSens= Parameters.prevGainPos *15 +15;
        }
        //ECG Gain Down
        else if (teleData[0]==4 && teleData[2] == 0x15)
        {
            CommonData.prevGainPos =teleData[3];
            //CommonData.logFileWrite("ECG Gain = " + CommonData.prevGainPos, CommonData.pacerData); // Write Log File
            Toast.makeText(getApplicationContext(), "ECG Gain = " + CommonData.prevGainPos, Toast.LENGTH_SHORT).show();
            //markClsInstance.ecgSens=para.prevGainPos*30 +20;
            markClsInstance.ecgSens= CommonData.prevGainPos *15 +15;
        }
        else {
            mprogressbar.setVisibility(View.GONE);

            Toast.makeText(getApplicationContext(), CommonData.strAction +" Error",Toast.LENGTH_SHORT).show();
            bStatSenCnt=false;
            CommonData.bThrStop =false;
            //markClsInstance.bFileWrite=true;
            if (bMarkViewON) {
                //markClsInstance.bFileWrite=true;
                markClsInstance.butMarker.setChecked(false);
                System.arraycopy(CommonData.pacerData, 0, CommonData.pacerDataPROG, 0, CommonData.pacerData[1] + 5);//Restore Para
                markClsInstance.butThrNext.setEnabled(false);
                markClsInstance.bFileWrite=false;
                markClsInstance.bMarkON=false;
                CommonData.decodeBytes();
            }
            if (CommonData.bStatiscticsON) {
                //statistics statClsInstance = statistics.getInstance();
                //statClsInstance.waitCirBar.setVisibility(View.INVISIBLE);
            }
            //CommonData.logFileWrite(CommonData.strAction +" Error", CommonData.pacerData); // Write Log File
            CommonData.strAction ="";

            refreshScreen(CommonData.iPacerSelect);
        }

        bDataReadOK=true;
    }
*/
   /* public void createRequest(byte bt)
    {
        // int n;
        switch (bt)
        {
            case 1: //Interrogate
                interrogatePacer();
                break;
            case 2: //Request to send Pacer ID
                interrogateBT = new byte[]{2,3,39,2,3,4,(byte)155,(byte)0xFF};
                break;
            case 6: //POR , Threshold Stop, Threshold Next
                if (CommonData.bThrStop) {
                    // para.bThrStop=false;
                    interrogateBT = new byte[]{6, 3, 13, 16, 0, 0x77, 6, (byte) 0xFF}; // End of Threshold
                }
                else
                    interrogateBT = new byte[]{6,3,12,0,0,52,50,(byte)0xFF};
                break;
            case 18: //POR2
                interrogateBT = new byte[]{18,1,11,(byte)197,99,(byte)0xFF};
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
                interrogateBT= new byte[]{0x08,0x10,0x27,3,3,5,3,0,0,0,1,1,1,(byte) 0xF4,0x55,7,0,0x0A,0,0,0};
                interrogateBT[7]= (byte) CommonData.iPacerID;interrogateBT[8]=(byte) CommonData.iSrnoH;interrogateBT[9]=(byte) CommonData.iSrnoL;
                break;
            case 0x11: //Marker
                markerStart();
                break;
            case 0x18://Stop Mareker
                interrogateBT= new byte[]{0x18,3,12,0,0,0,0,0};
                break;
            case 0x1B: //Threshold Test Start
                if (!CommonData.bThrNext)
                    thrStart();
                break;
            case 0x0B: //Urgent Program
                interrogateBT = new byte[] {0x0B,0x1E,0x10,0x4B,1,1,1,(byte)(0xF4),0x4C,1,1,1,(byte)(0xF4),0x36,3,1,0x25,0x43,0x16,5,1,6,6,
                        0x1B,0x1E,1,5,1,6,0x13,0,0,0,0,0};
                break;
            case 0x0A: //To Confirm Reset
                interrogateBT = new byte[] {0x0A,0x0D,0x27,2,3,5,1,(byte)CommonData.iPacerID,1,1,1,(byte)(0xF4),0x1C,2,0x0E,0,0,0};

                break;
            case 0x09: //To Reset Pacemaker
                interrogateBT = new byte[] {0x09,0x1C,0x27,3,3,5,3,(byte)CommonData.iPacerID,(byte)CommonData.iSrnoH,(byte)CommonData.iSrnoL,
                        1,1,1,(byte)(0xF4),(byte) 0x97,3,1,5,1,(byte) 0x97,1,1,1,(byte)(0xF4),(byte) 0x97,3,1,5,1,6,0,0,0};

                break;
            case 0x14: //ECG Gain UP
                interrogateBT = new byte[] {0x14,0x02,0x2C,(byte) 0xF1,0,0,0};
                break;
            case 0x15: //ECG Gain Down
                interrogateBT = new byte[] {0x15,0x02,0x2C,(byte) 0xF2,0,0,0};
                break;
            default:
                break;
        }
        if (interrogateBT != null)
        {

            interrogateBT = CommonData.CRCCAL(interrogateBT);
            bytesToSend = new byte[interrogateBT[1] + 5];
            System.arraycopy(interrogateBT, 0, bytesToSend, 0, interrogateBT[1] + 5);
            //***** For Debug *****
           *//* String strBytes = new String();
            strBytes = "";
            strBytes = String.valueOf(bytesToSend[1]+5).toString() + "-";

            for (int p = 0; p < bytesToSend[1]+5; p++) {
                strBytes = strBytes + " " + String.valueOf(0xFF & bytesToSend[p]).toString();
            }

            arraylist.add(strBytes);
            arrayAdapter.notifyDataSetChanged();
            if(arraylist.size()>100){

                arraylist.clear();
            }*//*

            dataTransfer.write(bytesToSend);
            interrogateBT = null; // Clear Array
            bytesToSend = null; // Clear Array

        }

    }

    private void thresholdNext()
    {
        int temp2;
        interrogateBT= new byte[]{0x1B,0,0x27,3,3,5,3,0,0,0,1,1,1,(byte)0xF4,0,3,1,5,1,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        //***** if 8820 / Pinn R *****
        if( CommonData.iPacerSelect < 5 || CommonData.iPacerSelect == 9 || CommonData.iPacerSelect == 11 || CommonData.iPacerSelect == 25 ||
                CommonData.iPacerSelect == 13 || CommonData.iPacerSelect == 0x1B || CommonData.iPacerSelect == 0x1D ||
                CommonData.iPacerSelect == 0x1E)
        {
            if(markClsInstance.bVPThr) {
                if(CommonData.iAmp >0 ) {
                    CommonData.iAmp -= 1;
                    if (CommonData.iPacerSelect ==25 || CommonData.iPacerSelect ==0x1B) {
                        //'Hyst On/OFF + ARC + AMP
                        CommonData.pacerDataPROG[8] &= 0xC0;
                        CommonData.pacerDataPROG[8] += CommonData.ampctrlWD297[CommonData.iAmp];
                    }
                    else{
                        //'S POL + P POL + AMP
                        CommonData.pacerDataPROG[8] &= 0xC0;
                        CommonData.pacerDataPROG[8] += para.ampctrlWD[CommonData.iAmp];

                    }
                    // debugmarkClsInstance.updateMarkAnnotation();
                }
                else{
                    //Toast.makeText(mainActInstance.getApplicationContext(), "End of Parameter Limit", Toast.LENGTH_SHORT).show();
                    CommonData.bThrLimit =true;
                }
            }
            if (markClsInstance.bVPWThr) {
                if (CommonData.iPW >0) {
                    CommonData.iPW -= 1;
                    if (CommonData.iPacerSelect ==25 || CommonData.iPacerSelect ==0x1B) {
                        //P pol +S pol + PW
                        CommonData.pacerDataPROG[7] &= 0xC0;
                        CommonData.pacerDataPROG[7] += para.pwCtrlWD297[CommonData.iPW];
                    }
                    else
                    {
                        // PW
                        CommonData.pacerDataPROG[7] = para.pwCtrlWD[CommonData.iPW];
                    }
                    // debug markClsInstance.updateMarkAnnotation();
                }
                else{
                    //Toast.makeText(mainActInstance.getApplicationContext(), "End of Parameter Limit", Toast.LENGTH_SHORT).show();
                    CommonData.bThrLimit =true;
                }

            }
            if(markClsInstance.bVSThr)
            {
                if (CommonData.iSen > 0) {
                    CommonData.iSen -= 1;
                    if (CommonData.iPacerSelect ==25 || CommonData.iPacerSelect ==0x1B) {
                        // Sensitivity AM MRI
                        CommonData.pacerDataPROG[11] &= 0xC0;
                        CommonData.pacerDataPROG[11] += CommonData.iSen;
                    }
                    else
                    {
                        // Sensitivity  REF
                        CommonData.pacerDataPROG[10] &= 0x1F;
                        CommonData.pacerDataPROG[10] += CommonData.senCtrlWD[CommonData.iSen];
                    }
                    // debug  markClsInstance.updateMarkAnnotation();
                }
                else{
                    //Toast.makeText(mainActInstance.getApplicationContext(), "End of Parameter Limit", Toast.LENGTH_SHORT).show();
                    CommonData.bThrLimit =true;
                }
            }
        }
        // '********** if charak DR , New charak , New Bivent*************

        if(CommonData.iPacerSelect==12 ||CommonData.iPacerSelect==24|| CommonData.iPacerSelect==0x15 ||CommonData.iPacerSelect==0x1C ||
                CommonData.iPacerSelect==0x13 || CommonData.iPacerSelect==0x10) {

            if(markClsInstance.bVSThr==true) // if Sens Thr
            {
                if(markClsInstance.bThrChamber==true) // 'V SEN
                {

                    if(CommonData.iSen<=0)
                        CommonData.bThrLimit =true;
                    else {
                        CommonData.iSen -= 1;
                        // debug   markClsInstance.updateMarkAnnotation();
                    }
                }
                else  // 'A SEN
                {
                    if(CommonData.iASen<=0)
                        CommonData.bThrLimit =true;
                    else {
                        CommonData.iASen -= 1;
                        // debug  markClsInstance.updateMarkAnnotation();
                    }
                }
            }
            else if(markClsInstance.bVPWThr==true) {
                if(markClsInstance.bThrChamber==true) // 'VPW
                {
                    if(CommonData.iPW<=0)
                        CommonData.bThrLimit =true;
                    else {
                        CommonData.iPW -= 1;
                        // debug markClsInstance.updateMarkAnnotation();
                    }
                }
                else // 'APW
                {
                    if(CommonData.iAPW<=0)
                        CommonData.bThrLimit =true;
                    else {
                        CommonData.iAPW -= 1;
                        // debug  markClsInstance.updateMarkAnnotation();
                    }
                }
            }
            else if (markClsInstance.bVPThr==true) {
                // 'A AMP
                if(markClsInstance.bThrChamber==false)
                {
                    if (CommonData.iAAmp<=0)
                        CommonData.bThrLimit =true;
                    else {
                        CommonData.iAAmp -= 1;
                        // debug  markClsInstance.updateMarkAnnotation();
                    }
                }

                else // 'V AMP
                {
                    if (CommonData.iAmp<=0)
                        CommonData.bThrLimit =true;
                    else {
                        CommonData.iAmp -= 1;
                        // debug markClsInstance.updateMarkAnnotation();
                    }
                }
            }

        }
        //   '********* End of Charak DR and New Models**************



        //Create data to send
        interrogateBT[0] = 0x1B; interrogateBT[1] = 0x1B; interrogateBT[2] = 0x10; interrogateBT[3] = 0x2D;interrogateBT[4] = 1;
        interrogateBT[5] = 1; interrogateBT[6] = 1; interrogateBT[7] = (byte) 0xF4;interrogateBT[9] = 1; interrogateBT[10] = 1;
        interrogateBT[11] = 1; interrogateBT[12] = (byte) 0xF4;interrogateBT[14] = 3; interrogateBT[15] = 1; interrogateBT[16] = 5;
        interrogateBT[17] = 1;interrogateBT[18] = 6;interrogateBT[19] = 0x28; interrogateBT[20] = 0x10; interrogateBT[21] = (byte)0x8B;
        interrogateBT[22] = 3;interrogateBT[23] = 1;interrogateBT[24] = 5; interrogateBT[25] = 1;interrogateBT[26] = 6; interrogateBT[27] = 6;
        interrogateBT[28] = 0x12;


        // For old 8820
        temp2 = CommonData.pacerDataPROG[8] & 0x3F;// Fam.sa(bam).Tag And &H3F
        interrogateBT[8]= (byte) temp2;
        interrogateBT[13] = (byte)(0xD3 - temp2);
        if (markClsInstance.bVPWThr)
        {
            interrogateBT[3] = 0x17;
            interrogateBT[8] = (byte) CommonData.pacerDataPROG[7];
            interrogateBT[13] = (byte)(0xE9 - interrogateBT[8]);
        }
        else if (markClsInstance.bVSThr)
        {
            interrogateBT[3] = 0x1A;
            interrogateBT[8] = (byte) (CommonData.pacerDataPROG[10]/32);
            interrogateBT[13] = (byte)(0xE6 - interrogateBT[8]);
        }

        //For 297 8820 AM
        if( CommonData.iPacerSelect == 25 || CommonData.iPacerSelect == 13 || CommonData.iPacerSelect == 0x1B || CommonData.iPacerSelect == 0x1D ||
                CommonData.iPacerSelect == 0x1E)
        {
            interrogateBT[0]=0x1B;interrogateBT[1] = 0x11;interrogateBT[2] = 0x0D;interrogateBT[3] = 0x10;interrogateBT[4] = 0x2D;
            interrogateBT[11] = (byte)(CommonData.pacerDataPROG[8] & 0x3F);// wd(21) And & H3F


            if(markClsInstance.bVPWThr) {

                interrogateBT[4] = 0x17;
                interrogateBT[11] = (byte) CommonData.pacerDataPROG[7];

            }

            if (markClsInstance.bVSThr) {
                interrogateBT[4] = 0x1A;
                interrogateBT[11] = (byte) CommonData.pacerDataPROG[11];

            }

            interrogateBT[5] = 2;interrogateBT[6] = 1;interrogateBT[7] = 1;interrogateBT[8] = 1;interrogateBT[9] = 1;interrogateBT[10] = (byte) 0xF4;
            interrogateBT[12] = 0x28;interrogateBT[13] = 0x10;interrogateBT[14] = 0x5B;interrogateBT[15] = 2;interrogateBT[16] = 1;
            interrogateBT[17] = 6;interrogateBT[18] = 0x0B;
        }
        // For Charak
        if (CommonData.iPacerSelect==12 || CommonData.iPacerSelect==24) {

            if(CommonData.iAmp > 31)
                temp2 = (CommonData.iAmp - 31) * 3 + CommonData.iAmp;
            else
                temp2=CommonData.iAmp;

            //22/04/23 temp2 = para.ampctrlWD297[temp2] & 0x3F;
            temp2 = CommonData.ampctrlWD[temp2] & 0x3F; //22/04/23
            temp2 = temp2 | 0x80 ; // temp2,7 = 1 For V Amp

            if (markClsInstance.bThrChamber==false) // For A Amp
            {
                if(CommonData.iAAmp > 31)
                    temp2 = (CommonData.iAAmp - 31) * 3 + CommonData.iAAmp;
                else
                    temp2=CommonData.iAAmp;

                //22/04/23 temp2 = para.ampctrlWD297[temp2] & 0x3F;
                temp2 = CommonData.ampctrlWD[temp2] & 0x3F;
            }
            interrogateBT[8] = (byte)temp2;
            interrogateBT[13] = (byte)(0xD3 - temp2);

            //if PW Selected
            if(markClsInstance.bVPWThr==true)
            {

                if (markClsInstance.bThrChamber==false) //if A chamber (APW)
                {
                    temp2=CommonData.iAPW;
                }
                else //if V chamber (VPW)
                {
                    temp2 = ( CommonData.iPW | 0x80); //7th bit=1 for V
                }
                interrogateBT[3]=0x17;
                interrogateBT[8]=(byte)temp2;
                interrogateBT[13]=(byte)(0xE9-temp2);
            }

            // if Sens Selected
            if(markClsInstance.bVSThr==true) {

                if (markClsInstance.bThrChamber==false) { //if A sens
                    temp2= CommonData.iASen;
                }
                else //V Sen
                {
                    temp2=CommonData.iSen | 0x80 ; //7th bit =1 for V
                }
                interrogateBT[3]=0x1A;
                interrogateBT[8]=(byte) temp2;
                interrogateBT[13]=(byte)(0xE6-temp2);
            }
        }// End of Charak
        //*************** New Models ***************
        if (CommonData.iPacerSelect==0x10 || CommonData.iPacerSelect==0x13 || CommonData.iPacerSelect==0x1C)
        {
            if ( markClsInstance.bThrChamber==false) //A Amp
            {
                temp2 = CommonData.iAAmp;
            }
            else //V Amp
            {
                temp2=CommonData.iAmp;
                temp2 |= 0x80; //set bit 7 for V Amp
            }
            interrogateBT[8]= (byte) temp2 ; interrogateBT[13]= (byte)(0xD3 - temp2);

            if (markClsInstance.bVPWThr)
            {
                interrogateBT[3] = 0x17;
                if (markClsInstance.bThrChamber == false || CommonData.iMode == 4) // A PW
                {
                    if (CommonData.iPacerSelect==0x10 || CommonData.iPacerSelect==0x13) { //New Pinn PinnR
                        temp2 = CommonData.iPW;
                        temp2 |= 0x80;
                    }
                    else // New Charak
                    {
                        temp2=CommonData.iAPW;

                    }

                }
                else if (markClsInstance.bThrChamber || CommonData.iMode==0) // V PW
                {
                    temp2=CommonData.iPW;
                    temp2 |= 0x80; //Set bit 7 for V
                }
                interrogateBT[8]= (byte) temp2 ; interrogateBT[13]= (byte)(0xE9 - temp2);
            }
            else if (markClsInstance.bVSThr) //Sens Thr
            {
                interrogateBT[3]= (byte)0x1A;
                if (markClsInstance.bThrChamber==false || CommonData.iMode==4) //V
                {
                    temp2 = CommonData.iASen;
                }
                else if (markClsInstance.bThrChamber==true || CommonData.iMode==0) //V
                {
                    temp2=CommonData.iSen;
                    temp2 |= 0x80; //set bit 7 for V
                }
                interrogateBT[8]= (byte) temp2 ; interrogateBT[13]= (byte)(0xE6 - temp2);
            }
            interrogateBT[21]=(byte)0x8B; interrogateBT[22] = 0x02; interrogateBT[23]=0x01; interrogateBT[24] = 0x06;
            interrogateBT[25]=0x12;interrogateBT[1]=0x18;
        }

        //**************** End New Models *************************************
        if (CommonData.bThrLimit)
        {
            CommonData.bThrLimit =false;
            Toast.makeText(mainActInstance.getApplicationContext(), "End of Parameter Limit", Toast.LENGTH_SHORT).show();
            markClsInstance.stopMarker();
        }
        else {
            interrogateBT = CommonData.CRCCAL(interrogateBT);
            bytesToSend = new byte[interrogateBT[1] + 5];
            System.arraycopy(interrogateBT, 0, bytesToSend, 0, interrogateBT[1] + 5);
            dataTransfer.write(bytesToSend);
            Toast.makeText(getApplicationContext(), "Threshold Next", Toast.LENGTH_SHORT).show();
        }
        interrogateBT = null; //Clear Array
        bytesToSend = null; //Clear Array

    }
*/
    private String setStrImpVal(int val)
    {
        String strImp="";
        if (val > 1500)
            strImp="(>> 1500)";
        else if (val <250)
            strImp="(<< 250)";
        else
            strImp = String.valueOf(val);
        return strImp;
    }
}
