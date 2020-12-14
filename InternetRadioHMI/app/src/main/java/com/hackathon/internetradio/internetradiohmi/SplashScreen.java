package com.hackathon.internetradio.internetradiohmi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;

import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;

public class SplashScreen extends AppCompatActivity {

    int streamingStarted;

    private final ServiceInterfaceManager mServiceInterfaceManager =
            ServiceInterfaceManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mServiceInterfaceManager.bindService(getBaseContext().getApplicationContext());
        SharedPreferences sh
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);
        streamingStarted = sh.getInt("live", 0);
        System.out.println("Value : " + streamingStarted);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (streamingStarted == 1) {
                    Intent i = new Intent(SplashScreen.this,
                            MainTabActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashScreen.this,
                            StartActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, 2000);
    }
}
