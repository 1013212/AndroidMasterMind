/**
 * @file        InternetRadioMainActivity.java
 * @brief       InternetRadioMainActivity class act as the base activity
 * @author      Praveen
 */

package com.hackathon.internetradio.internetradiohmi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;

/**
 * @brief Implementation for InternetRadioMainActivity class.
 *          InternetRadioMainActivity is the entry point to the application which extends AppCompatActivity.
 *          InternetRadioMainActivity is responsible for initial loading different fragments based on
 *          conditions.
 */
public class InternetRadioMainActivity extends AppCompatActivity {

    Button mPlayButton;

    Button mPauseButton;

    /**
     * Member variable for keeping the instance of RadioNetServiceInterfaceManager.
     */
    private final ServiceInterfaceManager mServiceInterfaceManager =
            ServiceInterfaceManager.getInstance();

    /**
     * @brief Android lfe cycle function.
     * @param savedInstanceState : object of Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*mPlayButton =  findViewById(R.id.btn_radio_play);
        mPauseButton = findViewById(R.id.btn_radio_pause);*/
        mPlayButton.setEnabled(true);
        mPauseButton.setEnabled(true);
        //mServiceInterfaceManager.bindService(getBaseContext().getApplicationContext());

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mServiceInterfaceManager.getHmiServiceInterface().play();
            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mServiceInterfaceManager.getHmiServiceInterface().pause();
            }
        });
    }

}