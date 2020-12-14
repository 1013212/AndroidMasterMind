package com.hackathon.internetradio.internetradiohmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainTabActivity extends AppCompatActivity implements IScrollViewManager{

    private TabLayout tabLayout;

    private ViewPager viewPager;

    Button home;

    int streamingStarted;

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.

    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main_layout);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        home = (Button) findViewById(R.id.home);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sh
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);
        streamingStarted = sh.getInt("live", 0);
        System.out.println("streamingStarted : " + streamingStarted);

        if (MainListActivity.screen == 2) {
            Bundle extras = getIntent().getExtras();
            String value = "1";
            if (extras != null) {
                value = extras.getString("SCREEN_STATIONS");
            }
            MainListActivity.screen = 0;
            viewPager.setCurrentItem(0);
        } else {
            if (MainListActivity.screen == 1 || streamingStarted == 1) {
                MainListActivity.screen = 0;
                viewPager.setCurrentItem(1);
            }
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainListActivity.class));
                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SubListFragment(), "STATION LIST");
        adapter.addFragment(new NowplayFragment(), "NOW PLAY");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void handleSelectedListItems() {
        viewPager.setCurrentItem(1);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            MainListActivity.screen = 0;
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show(); }
        mBackPressed = System.currentTimeMillis();
    }
}