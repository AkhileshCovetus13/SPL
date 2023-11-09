package com.example.spl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySplash extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return;
        }else{
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ActivitySplash.this,ActivityChooseOperation.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }

    }

    @Override public void onRequestPermissionsResult(int requestCode,
                                                     String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "GRANTED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivitySplash.this,ActivityChooseOperation.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "NOT GRANTED", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

   /* public void changeResolution(int x, int y){
        try { Class c = Class.forName("android.os.ServiceManager");
            try { Method method = c.getDeclaredMethod("checkService", String.class);
                try {
                    IWindowManager mWindowManager = IWindowManager.Stub.asInterface((IBinder) method.invoke(null, Context.WINDOW_SERVICE));
                    mWindowManager.setForcedDisplaySize(Display.DEFAULT_DISPLAY,x,y);
                }  catch (IllegalAccessException e) {e.printStackTrace();}
                catch (InvocationTargetException e) {e.printStackTrace();}
            } catch (NoSuchMethodException e) {e.printStackTrace();}
        } catch (ClassNotFoundException e) {e.printStackTrace();}
    }*/
}