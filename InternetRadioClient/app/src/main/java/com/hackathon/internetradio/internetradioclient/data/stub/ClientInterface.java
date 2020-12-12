/**
 * @file        internetradiointerface.java
 * @brief       Interface for HMI to Service API calls.
 * @copyright   COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *              ALL RIGHTS RESERVED
 * @author      Zubair KK
 */

package com.hackathon.internetradio.internetradioclient.data.stub;

import android.os.RemoteException;

import com.hackathon.internetradio.internetradioclient.data.InternetRadioClientManager;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseContext;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseFilter;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;
import com.hackathon.internetradio.lib.internetradiointerface.IClientInterface;

/**
 * @brief Interface for HMI to Service API calls.
 */
public class ClientInterface extends IClientInterface.Stub {

    /**
     * Member variable for keeping the instance of InternetRadioClientManager.
     */
    private final InternetRadioClientManager mInternetRadioClientManager =
            InternetRadioClientManager.getInternetRadioClientManagerManager();



    /**
     * @brief constructor to get context
     */
    ClientInterface() {
    }


    @Override
    public boolean isClientSideReady() throws RemoteException {
        return false;
    }

    /**
     * @brief API to play song from USB
     */
    @Override
    public void play() throws RemoteException {
        mInternetRadioClientManager.play();
    }

    /**
     * @brief API to pause current song
     */
    @Override
    public void pause() throws RemoteException {
        mInternetRadioClientManager.pause();
    }

    @Override
    public void skip(int direction, int skipCount) throws RemoteException {
        mInternetRadioClientManager.skip(direction, skipCount);
    }


    /**
     * @brief Function to get current play status
     * @return boolean : Current play status
     */
    @Override
    public boolean getCurrentPlayStatus() throws RemoteException {
        boolean playStatus = false;
        playStatus = mInternetRadioClientManager.getPlayStatus();
        return playStatus;
    }

    /**
     * @brief Function to get device connection status
     * @param sourceId : source ID
     * @return int : Current connection status of this device
     */
    @Override
    public int getConnectionStatus(int sourceId) throws RemoteException {
        int connectionStatus = Constants.ConnectionStatus.NOT_SUPPORTED;
        connectionStatus = mInternetRadioClientManager.getConnectionStatus(sourceId);
        return connectionStatus;
    }


    /**
     * @brief Function to get current playing track info.
     * @return TrackInfo : Current playing track info
     */
    @Override
    public TrackInfo getCurrentTrackInfo() throws RemoteException {
        TrackInfo trackInfo = null;
        trackInfo = mInternetRadioClientManager.getCurrentTrackInfo();
        return trackInfo;
    }

    /**
     * @brief Function is to get category list item
     * @param browseFilter : Filter for browse list
     * @throws RemoteException : remote exception
     */
    @Override
    public void getCategoryListItems(BrowseFilter browseFilter) throws RemoteException {
        mInternetRadioClientManager.getCategoryListItems(browseFilter);
    }

    /**
     * @brief Function is to play browse context song
     * @param browseContext : Object containing detail of selected browse item
     * @throws RemoteException : remote exception
     */
    @Override
    public void playUsingContext(BrowseContext browseContext) throws RemoteException {
        mInternetRadioClientManager.playUsingContext(browseContext);
    }


    /**
     * @return int : Current current error status
     * @brief Function to get current error status
     */
    @Override
    public int getErrorStatus() throws RemoteException {
        int errorStatus = 0;
        errorStatus = mInternetRadioClientManager.getErrorStatus();
        return errorStatus;
    }


    /**
     * @return String : Album art path
     * @brief Function to get album art path
     */
    @Override
    public String getCurrentAlbumArt() throws RemoteException {
        String albumArt = null;
        albumArt = mInternetRadioClientManager.getCurrentAlbumArt();
        return albumArt;
    }
}