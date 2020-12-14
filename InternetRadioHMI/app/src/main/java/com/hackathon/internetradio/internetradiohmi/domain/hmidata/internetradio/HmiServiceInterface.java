/**
 * @file       HmiServiceInterface.java
 * @brief      This file contains the internet radio client interface method implementation
 *
 * @author     Praveen
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;
import com.hackathon.internetradio.lib.internetradiointerface.IClientInterface;

/**
 * @brief Implementation for HmiServiceInterface class.
 *          HmiServiceInterface contains the implementations required for the communication
 *          with service.
 */
public class HmiServiceInterface {

    private IClientInterface mClientInterface;

    /**
     * Async task handler to process hmi async requests to service
     */
    private Handler mAsyncTaskHandler;

    /**
     * Async task handler to process hmi async requests to service
     */
    private Context mContext;

    /**
     * Boolean  is Handler thread Started or not
     */
    private boolean mHmiHandlerThreadStarted = false;

    /**
     * @brief HmiServiceInterface constructor
     */
    public HmiServiceInterface() {
    }

    /**
     * @brief Method for initialize HmiSideInterface Object.
     * @param clientInterface   : Object of IClientInterface
     */
    public void initialize(IClientInterface clientInterface) {
        mClientInterface = clientInterface;
        if (!mHmiHandlerThreadStarted) {
        } else {
        }
    }

    /**
     * @brief Method to set context.
     * @param context : Object of context.
     */
    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * @brief Method to get ConnectionStatus.
     */
    public int getConnectionStatus(int source) {
        int connectionStatus = Constants.ConnectionStatus.DISCONNECTED;
        try {
            connectionStatus = mClientInterface.getConnectionStatus(source);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return connectionStatus;
    }

    /**
     * @brief Method to get CurrentPlayStatus.
     */
    public boolean getCurrentPlayStatus() {
        boolean playStatus = false;
        try {
            playStatus = mClientInterface.getCurrentPlayStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return playStatus;
    }

    /**
     * @brief Method for play.
     */
    public void play() {
        try {
            mClientInterface.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Method for pause.
     */
    public void pause() {
        try {
            mClientInterface.pause();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Method for skip.
     */
    public void skip(int direction, int skipCount) {
        try {
            mClientInterface.skip(direction, skipCount);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Method to get CurrentAlbumArt.
     */
    public String getCurrentAlbumArt() {
        String albumArt = null;
        try {
            albumArt = mClientInterface.getCurrentAlbumArt();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return albumArt;
    }

    /**
     * @brief Method fto get currentTrackInfo.
     */
    public TrackInfo getCurrentTrackInfo() {
        TrackInfo trackInfo = null;
        try {
            trackInfo = mClientInterface.getCurrentTrackInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return trackInfo;
    }

    /**
     * @brief Method to get ErrorStatus.
     */
    public int getErrorStatus() {
        int errorStatus = Constants.ErrorStatus.NO_ERROR;
        try {
            errorStatus = mClientInterface.getErrorStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return errorStatus;
    }

    /**
     * @brief Method for connectToService.
     */
    public void connectToService() {
        try {
            mClientInterface.connectToService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Method to get station ListItems.
     */
    public void getStationListItems(String stationType) {
        try {
            Log.d("PPGG", "getStationListItems: " + stationType);
            mClientInterface.getStationListItems(stationType);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play using media id
     * @param mediaId
     */
    public void playFromMediaId(String mediaId) {
        try {
            Log.d("PPGG", "playFromMediaId: " + mediaId);
            mClientInterface.playUsingId(mediaId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
