package com.hackathon.internetradio.internetradiohmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiPresenter;
import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiView;
import com.hackathon.internetradio.internetradiohmi.Presenter.RadioNetPresenter;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

import java.util.ArrayList;

public class MainListActivity extends Activity implements IRadioNetHmiView {

    Button allSongs,myFav,live;

    public static int screen = 0;

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.

    private long mBackPressed;

    protected IRadioNetHmiPresenter mRadioNetHmiPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        allSongs=findViewById(R.id.all_songs);
        myFav=findViewById(R.id.my_fav);
        live=findViewById(R.id.live);
        mRadioNetHmiPresenter = new RadioNetPresenter(this);
        mRadioNetHmiPresenter.start();
        // Inflate the layout for this fragment
        allSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioNetHmiPresenter.getStationListItems("MUSIC");
                screen = 2;
                Intent activityIntent = new Intent(getApplicationContext(),
                        MainTabActivity.class);
                activityIntent.putExtra("SCREEN_STATIONS", "1");
                startActivity(activityIntent);
                finish();
            }
        });
        myFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioNetHmiPresenter.getStationListItems("FAVORITES");
                screen = 2;
                Intent activityIntent = new Intent(getApplicationContext(),
                        MainTabActivity.class);
                activityIntent.putExtra("SCREEN_STATIONS", "2");
                startActivity(activityIntent);
                finish();
            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioNetHmiPresenter.getStationListItems("LIVE");
                screen = 1;
                Intent activityIntent = new Intent(getApplicationContext(),
                        MainTabActivity.class);
                activityIntent.putExtra("SCREEN_LIVE", "3");
                startActivity(activityIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            screen = 0;
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show(); }
        mBackPressed = System.currentTimeMillis();
    }

}