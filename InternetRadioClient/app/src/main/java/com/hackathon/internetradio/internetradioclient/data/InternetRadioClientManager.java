/**
 * @file        InternetRadioClientManager.java
 * @brief       Media Service manager is common gateway between all
 *              media sources and Media Service.
 * @copyright   COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *              ALL RIGHTS RESERVED
 * @author      Zubair KK
 */

package com.hackathon.internetradio.internetradioclient.data;

import android.content.Context;
import android.os.RemoteCallbackList;
import android.view.Gravity;
import android.widget.Toast;

import com.hackathon.internetradio.internetradioclient.data.utils.IServiceConnection;
import com.hackathon.internetradio.internetradioclient.data.utils.SourceNotifications;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseContext;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseFilter;
import com.hackathon.internetradio.lib.internetradiointerface.IClientListener;

/**
 * @brief InternetRadioClientManager class contains the implementations for the different media
 *        functionalities of various sources
 */
public class InternetRadioClientManager {

    /**
     * Member variable for keeping the instance of InternetRadioClientManager
     */
    private static final InternetRadioClientManager MEDIA_SERVICE_MANAGER = new InternetRadioClientManager();


    /**
     * Member variable for keeping the instance of ISourceBase
     */
    private ISourceBase mSourceManager;

    /**
     * Member variable for keeping the broadcast connection status
     */
    private boolean mBroadCastConnection;

    /**
     * Member variable for keeping the broadcast count
     */
    private int mBroadCastCount;

    /**
     * Member variable for keeping the the instance of SourceNotifications
     */
    private SourceNotifications mSourceNotifications;

    /**
     * Member variable for keeping the instance of Context
     */
    private Context mContext;
    /**
     * Member variable for keeping callback listeners
     */
    private final RemoteCallbackList<IClientListener> mCallbackList = new RemoteCallbackList<>();

    /**
     * Member variable to handle the service connected call back.
     */
    private IServiceConnection mServiceConnection = new IServiceConnection() {
        /**
         * @param serviceType : int
         * @brief Callback method to handle service initialization.
         */
        @Override
        public void onServiceInitialized(String serviceType) {
            synchronized (this) {

                if (mSourceNotifications != null) {
                    mSourceNotifications.notifyServiceReady(true);
                }

            }
        }
        /**
         * @param serviceType : int
         * @brief Callback method to handle service destroy.
         */
        @Override
        public void onServiceDeinitialized(String serviceType) {
        }
    };

    /**
     * @brief Private constructor to make class singleton
     */
    private InternetRadioClientManager() {
    }

    /**
     * @brief Method to return object for singleton class
     * @return InternetRadioClientManager : InternetRadioClientManager object
     */
    public static InternetRadioClientManager getInternetRadioClientManagerManager() {
        return MEDIA_SERVICE_MANAGER;
    }



    /**
     * @brief This method does initialisation logic for {@link InternetRadioClientManager}
     * @param context : application context
     */
    public void init(Context context) {
        mContext = context;
        mSourceNotifications = new SourceNotifications();

    }

    /**
     * @brief Register the client objects to notify events from player
     * @param hmiAidlObj : HMI AIDL object
     */
    public void registerAsyncCallBack(IClientListener hmiAidlObj) {
        mCallbackList.register(hmiAidlObj);
    }

    /**
     * @brief Unregister the registered client objects to notify events from player
     * @param hmiAidlObj : HMI AIDL object
     */
    public void unRegisterAsyncCallBack(IClientListener hmiAidlObj) {
        if (hmiAidlObj != null) {
            mCallbackList.unregister(hmiAidlObj);
        } else {
            mCallbackList.kill();
        }
    }


    /**
     * @brief This function is to get connection status of specific sourceType
     * @param sourceType : Device sourceType
     * @return int : connection status
     */
    public int getConnectionStatus(int sourceType) {
        int connectionStatus = 0;
        return connectionStatus;
    }

    /**
     * @brief Function to get current play status
     * @return boolean : Current play status
     */
    public boolean getPlayStatus() {
        return mSourceManager.getPlayStatus();
    }



    /**
     * @brief Function to skip current song to next or previous.
     * @param direction : skip direction
     * @param skipCount : skip count
     */
    public void skip(int direction, int skipCount) {
        mSourceManager.skip(direction, skipCount);
    }



    /**
     * @brief Function to get current playing track info.
     * @return trackInfo : current playing track info
     */
    public TrackInfo getCurrentTrackInfo() {
        TrackInfo trackInfo = mSourceManager.getCurrentTrackInfo();
        return trackInfo;
    }



    /**
     * @brief This function returns HMI callback list
     * @return RemoteCallbackList : callback list of AIDL subscribers
     */
    RemoteCallbackList<IClientListener> getCallbackList() {
        return mCallbackList;
    }

    /**
     * @brief Function to get album art path
     * @return String : Album art path
     */
    public String getCurrentAlbumArt() {
        return mSourceManager.getCurrentAlbumArt();
    }

    /**
     * @brief Function to get current error status
     * @return int : Current current error status
     */
    public int getErrorStatus() {
        return mSourceManager.getErrorStatus();
    }

    /**
     * @brief Function to get favorites from Personal Account
     * @return int[] : Array of source IDs of favorite sources
     */
    public int[] getFavoriteSources() {
        return null;
    }

    /**
     * @brief Function to set favorites to Personal Account
     * @param favorites :  Array of source IDs of favorite sources
     */
    public void setFavoriteSources(int[] favorites) {

    }

    /**
     * @brief Function to get browse list size
     * @param browseContext : Object containing detail of selected browse item
     * @return int : Size of browse list
     */
    public int getCategoryListSize(BrowseContext browseContext) {
        return 0;
    }



    /**
     * @brief Function to get current track list size
     * @return int : Size of current track list
     */
    public int getCurrentTrackListSize() {

        return 0;
    }

    /**
     * @brief This function is to get category list item
     * @param browseFilter : Filter for browse list
     */
    public void getCategoryListItems(BrowseFilter browseFilter) {

    }


    /**
     * @brief This function is to get current track list
     * @param startIndex : start index of  current track list
     * @param count : count of current track list
     */
    public void getCurrentTrackListItems(int startIndex, int count) {

    }

    /**
     * @brief This function is to play browse context song
     * @param itemContext : Object containing detail of selected browse item
     */
    public void playUsingContext(BrowseContext itemContext) {

    }


    /**
     * @brief This function is to play current sources
     */
    public void play() {
        Toast toast = Toast.makeText(mContext, "message", Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setText("Connected to service");
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        mSourceManager.play();
    }



    /**
     * @brief This function is to pause current source
     */
    public void pause() {
        mSourceManager.pause();
    }


    /**
     * @brief This function is to skip next if source is Projection
     * @param pressStatus status from HMI notifying press / release
     */
    public void next(int pressStatus) {
        mSourceManager.next(pressStatus);
    }

    /**
     * @brief This function is to skip previous if source is Projection
     * @param pressStatus status from HMI notifying press / release
     */
    public void previous(int pressStatus) {
        mSourceManager.previous(pressStatus);
    }

    /**
     * @brief Function to  be called on begin broadcast
     * @return int : Registered broad cast count
     */
    int beginBroadcast() {
        if (!mBroadCastConnection) {

            mBroadCastConnection = true;
            mBroadCastCount = mCallbackList.beginBroadcast();
        }
        return mBroadCastCount;
    }

    /**
     * @brief Function to  be called on finish broadcast.
     */
    void finishBroadcast() {
        if (mBroadCastConnection) {
            mCallbackList.finishBroadcast();
            mBroadCastConnection = false;
        }
    }

}
