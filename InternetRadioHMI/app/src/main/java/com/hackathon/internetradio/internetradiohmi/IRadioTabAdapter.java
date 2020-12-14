package com.hackathon.internetradio.internetradiohmi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class IRadioTabAdapter  extends FragmentPagerAdapter {

    private Context mContext;

    int totalTabs;

    public IRadioTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        mContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SubListFragment stationListFragment = new SubListFragment();
                return stationListFragment;
            case 1:
                NowplayFragment nowplayFragment = new NowplayFragment();
                return nowplayFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}