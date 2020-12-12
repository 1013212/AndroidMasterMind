/**
 * @file       HmiServiceInterface.java
 * @brief      This file contains the internet radio client interface method implementation
 *
 * @author     Praveen
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio;

import android.content.Context;
import android.os.Handler;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
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

    public void setContext(Context context) {

    }

    public int getConnectionStatus(int source) {
        return 0;
    }

    public int getCurrentPlayStatus() {
        return 0;
    }

    public void play() {
    }

    public void pause() {
    }

    public void skip(int direction, int skipCount) {
    }

    public String getCurrentAlbumArt() {
        return null;
    }

    public TrackInfo getCurrentTrackInfo() {
        return null;
    }

    public int getErrorStatus() {
        return 1;
    }
}
