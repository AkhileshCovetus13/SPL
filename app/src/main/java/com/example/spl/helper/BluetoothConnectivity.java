package com.example.spl.helper;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.UUID;

public class BluetoothConnectivity extends Thread {
    UUID spluuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothSocket clientSocket = null;
    int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    Activity context;

    BluetoothConnectivity(BluetoothDevice device, Activity context) {
        this.context = context;
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                context.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                clientSocket = device.createInsecureRfcommSocketToServiceRecord(spluuid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BluetoothSocket getSocketConnection() {
        return clientSocket;
    }

    @Override
    public void run() {
        super.run();
        try {
            if (clientSocket != null) {
                if (!clientSocket.isConnected()) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        context.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
                    } else {
                        clientSocket.connect();
                    }
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
