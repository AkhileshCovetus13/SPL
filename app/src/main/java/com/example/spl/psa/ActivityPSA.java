package com.example.spl.psa;

import static com.example.spl.egm.EGMMarkerFragmentPsa.draw_marker;
import static com.example.spl.helper.CommonData.iSrnoH;
import static com.example.spl.helper.CommonData.iSrnoL;

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
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.spl.BluetoothDevicesActivity;
import com.example.spl.R;
import com.example.spl.egm.EGMMarkerFragmentPsa;
import com.example.spl.helper.CommonData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ActivityPSA extends AppCompatActivity {
    public static byte[] interrogateBT;
    private static byte[] bytesToSend;
    Button egm, scale;
    RelativeLayout btnConnectBluetooth;
    private BluetoothAdapter bta;
    int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    BluetoothConnectivity bluetoothConnectivity;
    static BluetoothSocket clientSocket = null;
    CountDownTimer waitTimer;
    int bt_notes_blink = 0;
    Handler handlerBlink = new Handler();
    private static final int STAT_MSG_RECEIVED = 5;
    private static boolean bDataReadOK = true;
    static Handler handler;
    ImageView mImgBack;
    static DataTransferClass dataTransferClass;
    private static final int STAT_LISTENING = 1;
    private static final int STAT_CONNECTING = 2;
    private static final int STAT_CONNECTED = 3;
    private static final int STAT_CONNECTIONFAILED = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psa);

        bta = BluetoothAdapter.getDefaultAdapter();
        bta.enable();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame, new FragmentPsaVvi()).commit();

        egm = findViewById(R.id.egm);
        btnConnectBluetooth = findViewById(R.id.btnConnectBluetooth);
        mImgBack = findViewById(R.id.mImgBack);

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
                LocalBroadcastManager.getInstance(ActivityPSA.this).unregisterReceiver(btStatReceiver);
                //unregisterReceiver(btStatReceiver);
                finish();
            }
        });

        egm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnConnectBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bta != null) {
                    if (!bta.isEnabled()) {
                        if (ActivityCompat.checkSelfPermission(ActivityPSA.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
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

        scale = findViewById(R.id.scale);
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        fm.beginTransaction().replace(R.id.marker_frame, new EGMMarkerFragmentPsa()).commit();
        fm.beginTransaction().addToBackStack(null);
        IntentFilter btMonitor = new IntentFilter();
        btMonitor.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        btMonitor.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        btMonitor.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        btMonitor.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        this.registerReceiver(btStatReceiver, btMonitor);
        startRepeatingTask();

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
                        Toast.makeText(getApplicationContext(), "Device Could not be Connected", Toast.LENGTH_SHORT).show();
                        break;
                    case STAT_MSG_RECEIVED:

                        draw_marker();
                       /* teleData = (int[]) msg.obj;
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
                            ///getPacerData();
                        } else if (CommonData.VARIFYCRC(teleData)) {
                            waitTimer.cancel();
                            //getPacerData();
                        } else {
                            waitTimer.cancel();
                            mFunAlertAuto(ActivityPSA.this, "Alert", "Error (CRC CheckSum)");
                            //Toast.makeText(getApplicationContext(), "Error (CRC CheckSum)", Toast.LENGTH_SHORT).show();
                        }*/
                        bDataReadOK = true;
                        break;
                    default:
                        break;
                }
                return false;
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CommonData.mStrBluetoothAddress == null || CommonData.mStrBluetoothAddress.isEmpty() || CommonData.mStrBluetoothAddress.trim().isEmpty()) {

        } else {
            //System.out.println("<><><><><> " + CommonData.mStrBluetoothAddress);
            connectProgrammer(CommonData.mStrBluetoothAddress);
        }
    }

    private void connectProgrammer(String address) {
        if (address != null) {
            System.out.println("<><><>###@@   Start");
            bluetoothConnectivity = new BluetoothConnectivity(bta.getRemoteDevice(address), ActivityPSA.this);
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
               // bluetoothConnectivity.stop();
                //CommonData.logFileWrite(ActivityPSA.this, "Error in Communication", CommonData.pacerData, CommonData.currModel);
                //mFunAlertAuto(ActivityPSA.this, "Alert", "Error in Communication");
                //Toast.makeText(getApplicationContext(), "Error in Communication", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public static void createRequest(byte bt) {
        // int n;
        switch (bt) {
            case 1: //Interrogate
                //interrogatePacer();
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
                //programPacer();
                break;
            case 0x64: //Impedance
                //impedanceMeasure();
                break;
            case 0x07: // Statistics
                //statRead();
                break;
            case 0x08: // Statistics Reset
                interrogateBT = new byte[]{0x08, 0x10, 0x27, 3, 3, 5, 3, 0, 0, 0, 1, 1, 1, (byte) 0xF4, 0x55, 7, 0, 0x0A, 0, 0, 0};
                interrogateBT[7] = (byte) CommonData.iPacerID;
                interrogateBT[8] = (byte) iSrnoH;
                interrogateBT[9] = (byte) iSrnoL;
                break;
            case 0x11: //Marker
                //markerStart();
                break;
            case 0x18://Stop Mareker
                interrogateBT = new byte[]{0x18, 3, 12, 0, 0, 0, 0, 0};
                break;
            case 0x1B: //Threshold Test Start
                if (!CommonData.bThrNext)
                    //thrStart();
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

            case (byte) 0xE3: //power on  PSA
                interrogateBT = new byte[]{(byte) 0xE3, 0x01, (byte) 0xE3, 0, 0, 0};
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

    void startRepeatingTask() {
        blinkEffect.run();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
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
        unregisterReceiver(btStatReceiver);
        finish();
    }

    void stopRepeatingTask() {
        handlerBlink.removeCallbacks(blinkEffect);
        btnConnectBluetooth.setBackgroundColor(getResources().getColor(R.color.Green));
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

    public static class DataTransferClass extends Thread {
        BluetoothSocket dtSocket;
         public static InputStream inputStream;
        public static OutputStream outputStream;

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
                    if(dtSocket.isConnected()){
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
}