/**
 * @file        SourceNotifications.java
 * @brief       Class for passing notification to Handler.


 * @author      Zubair KK
 */

package com.hackathon.internetradio.internetradioclient.data.utils;

import android.os.Handler;
import android.util.Log;

import com.hackathon.internetradio.internetradioclient.data.InternetRadioClientHandler;
import com.hackathon.internetradio.internetradioclient.data.InternetRadioClientManager;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

/**
 * @brief Class for passing notification to Handler.
 */
public class SourceNotifications implements ISourceNotifications {

    /**
     * Member variable for keeping the instance of Handler.
     */
    private Handler mClientHandler;

    /**
     * @brief constructor to get media handler
     */
    public SourceNotifications() {
        InternetRadioClientManager internetRadioClientManager = InternetRadioClientManager.getInternetRadioClientManager();
        mClientHandler = new InternetRadioClientHandler(internetRadioClientManager);
    }

    /**
     * @brief Function to notify play status with HMI apps
     * @param playStatus : play status
     */
    @Override
    public void notifyPlayStatus(int playStatus) {
        mClientHandler.removeMessages(Constants.AIDL_NOTIFY_PLAY_STATUS);
        mClientHandler.sendMessage(
                mClientHandler.obtainMessage(Constants.AIDL_NOTIFY_PLAY_STATUS, playStatus));
    }

    /**
     * @brief Function to notify track change with HMI apps
     * @param trackInfo : track info.
     */
    @Override
    public void notifyTrackChange(TrackInfo trackInfo) {
        mClientHandler.removeMessages(Constants.AIDL_NOTIFY_TRACK_CHANGE);
        mClientHandler.sendMessage(
                mClientHandler.obtainMessage(Constants.AIDL_NOTIFY_TRACK_CHANGE, trackInfo));
    }

    @Override
    public void notifyDeviceConnection(boolean status) {

    }

    @Override
    public void notifyError(int errorType) {

    }


    /**
     * @brief Function notifies category list items
     * @param browseList : Browse list items
     */
    @Override
    public void notifyStationListItems(BrowseList browseList) {
        Log.d("NOTIFY_Category_entry", "notifyStationListItems: " + browseList.toString());
        mClientHandler.removeMessages(Constants.AIDL_NOTIFY_CATEGORY_LIST_ITEMS);
        mClientHandler.sendMessage(
                mClientHandler.obtainMessage(Constants.AIDL_NOTIFY_CATEGORY_LIST_ITEMS, browseList));
        Log.d("NOTIFY_Category_exit", "notifyStationListItems: " + browseList.toString());
    }

    /**
     * @brief Function notifies track list items
     * @param browseList : Browse list items
     */
    @Override
    public void notifyCurrentTrackListItems(BrowseList browseList) {
        mClientHandler.removeMessages(Constants.AIDL_NOTIFY_CATEGORY_LIST_ITEMS);
        mClientHandler.sendMessage(
                mClientHandler.obtainMessage(Constants.AIDL_NOTIFY_CATEGORY_LIST_ITEMS, browseList));
    }

    /**
     * @brief Function to notify service ready
     * @param isReady : service ready status
     */
    @Override
    public void notifyServiceReady(boolean isReady) {
        mClientHandler.sendMessage(mClientHandler.obtainMessage(
                0, isReady));
    }
}