/**
 * @file        InternetRadioClientManager.java
 * @brief       InternetRadioClientManager is common gateway.
 * @author      PG
 */

package com.hackathon.internetradio.internetradioclient.data;

import android.content.Context;
import android.os.RemoteCallbackList;
import android.util.Log;

import com.hackathon.internetradio.internetradioclient.data.utils.SourceNotifications;
import com.hackathon.internetradio.internetradioclient.internetradiobrowserclient.InternetRadioServiceClient;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseItem;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;
import com.hackathon.internetradio.lib.internetradiointerface.IClientListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief InternetRadioClientManager class contains the implementations for the different media
 *        functionalities of various sources
 */
public class InternetRadioClientManager {

    /**
     * TAG
     */
    private static final String TAG = InternetRadioClientManager.class.getSimpleName();

    /**
     * Member variable for keeping the instance of InternetRadioClientManager
     */
    private static final InternetRadioClientManager RADIO_CLIENT_MANAGER = new InternetRadioClientManager();

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
     * Variable to store list of browse item.
     */
    private List<BrowseItem> mBrowseItemList = new ArrayList<>();

    /**
     * Variable to store list of browse item.
     */
    private List<BrowseItem> mFavItemList = new ArrayList<>();

    /**
     * Variable to store list of browse item.
     */
    private List<TrackInfo> mTrackInfoListList = new ArrayList<>();

    /**
     * Variable to store list of browse item.
     */
    private TrackInfo mTrackInfo = null;


    /**
     * Member variable for keeping the instance of InternetRadioServiceClient.
     */
    private InternetRadioServiceClient mInternetRadioServiceClient = InternetRadioServiceClient.getInstance();


    /**
     * Member variable for keeping callback listeners
     */
    private final RemoteCallbackList<IClientListener> mCallbackList = new RemoteCallbackList<>();

    /**
     * @brief Private constructor to make class singleton
     */
    private InternetRadioClientManager() {

    }

    /**
     * @brief Method to return object for singleton class
     * @return InternetRadioClientManager : InternetRadioClientManager object
     */
    public static InternetRadioClientManager getInternetRadioClientManager() {
        return RADIO_CLIENT_MANAGER;
    }



    /**
     * @brief This method does initialisation logic for {@link InternetRadioClientManager}
     */
    public void init() {
        //mContext = context;
        mSourceNotifications = new SourceNotifications();
        mTrackInfo = null;
        //createDummyStationData();
        //createDummyMetadataData();

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
        int connectionStatus = Constants.ConnectionStatus.CONNECTED;
        return connectionStatus;
    }

    /**
     * @brief Function to get current play status
     * @return boolean : Current play status
     */
    public boolean getPlayStatus() {
        return true;
    }

    /**
     * @brief Function to skip current song to next or previous.
     * @param direction : skip direction
     * @param skipCount : skip count
     */
    public void skip(int direction, int skipCount) {
        if (direction == Constants.SkipDirection.NEXT) {
            mInternetRadioServiceClient.skip(Constants.SkipDirection.NEXT);
        } else {
            mInternetRadioServiceClient.skip(Constants.SkipDirection.PREV);
        }

    }

    /**
     * @brief Function to get current playing track info.
     * @return trackInfo : current playing track info
     */
    public TrackInfo getCurrentTrackInfo() {
        return mTrackInfo;
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
        String coverArt = null;
        if (mTrackInfo!= null) {
            coverArt = mTrackInfo.getCoverArt();
        }
        return coverArt;
    }

    /**
     * @brief Function to get current error status
     * @return int : Current current error status
     */
    public int getErrorStatus() {
        return Constants.ErrorStatus.NO_ERROR;
    }

    /**
     * @brief This function is to get category list item
     */
    public void getStationListItems(String stationType) {
        mInternetRadioServiceClient.browse(stationType);
    }

    /**
     * @brief This function is to play browse context song
     * @param id : Object containing detail of selected browse item
     */
    public void playUsingId(String id) {
        Log.d("PPGG_Client", "playUsingId: " + id);
        mInternetRadioServiceClient.playFromMediaId(id);
    }

    /**
     * @brief This function is to pause current source
     */
    public void start() {
    }

    /**
     * @brief This function is to play current sources
     */
    public void play() {
        mInternetRadioServiceClient.play();
    }

    /**
     * @brief This function is to pause current source
     */
    public void pause() {
        mInternetRadioServiceClient.pause();;
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

    /**
     * @brief Function to  be called on finish broadcast.
     * @param item : favourite item
     */
    public void setFavorite(String item) {
        for (int i=0; i<mBrowseItemList.size(); i++) {
            if (mBrowseItemList.get(i).getId().equals(item)) {
                mFavItemList.add(mBrowseItemList.get(i));
                break;
            }
        }
    }

    /**
     * @brief Function to  connect to service.
     */
    public void connectToService() {
        Log.d(TAG, ">>>>>>>connectToService entry");
        //mInternetRadioServiceClient.connectToInternetRadioMediaBrowserService(mContext);
        Log.d(TAG, ">>>>>>>connectToService exit");
    }

    private void createDummyStationData() {
        BrowseItem station1 = new BrowseItem("http://somafm.com/bagel.pls", "Back To Basics");
        BrowseItem station2 = new BrowseItem("http://somafm.com/bootliquor.pls", "American Roots");
        BrowseItem station3 = new BrowseItem("http://somafm.com/thistle.pls", "Celtic");
        BrowseItem station4 = new BrowseItem("http://somafm.com/startstream=groovesalad.pls", "Chillout");
        BrowseItem station5 = new BrowseItem("http://www.slayradio.org/tune_in.php/128kbps/listen.m3u", "Blue Ruby");
        mBrowseItemList.add(station1);
        mBrowseItemList.add(station2);
        mBrowseItemList.add(station3);
        mBrowseItemList.add(station4);
        mBrowseItemList.add(station5);
    }

    private void createDummyMetadataData() {
        TrackInfo metadata1 = new TrackInfo("http://somafm.com/bagel.pls",
                "Back To Basics",
                "Rick Astley",
                "Sound Of Summer",
                "Jazz",
                "bagel.mp3",
                "album_art_1");
        TrackInfo metadata2 = new TrackInfo("http://somafm.com/bootliquor.pls",
                "American Roots",
                "The 126ers",
                "Boot Liquor",
                "Rock",
                "american_roots.mp3",
                "album_art_2");
        TrackInfo metadata3 = new TrackInfo("http://somafm.com/thistle.pls",
                "Celtic",
                "Beach Boys",
                "Bad Blood",
                "Rock",
                "celtic.mp3",
                "album_art_3");
        TrackInfo metadata4 = new TrackInfo("http://somafm.com/startstream=groovesalad.pls",
                "Chillout",
                "B.o.B",
                "Groove Salad",
                "Pop",
                "chillout.mp3",
                "album_art_4");
        TrackInfo metadata5 = new TrackInfo("http://www.slayradio.org/tune_in.php/128kbps/listen.m3u",
                "Blue Ruby",
                "Commodore Clause",
                "Commodore 64 Remixes",
                "Blues",
                "blue_ruby.mp3",
                "album_art_5");
        mTrackInfoListList.add(metadata1);
        mTrackInfoListList.add(metadata2);
        mTrackInfoListList.add(metadata3);
        mTrackInfoListList.add(metadata4);
        mTrackInfoListList.add(metadata5);
    }
}
