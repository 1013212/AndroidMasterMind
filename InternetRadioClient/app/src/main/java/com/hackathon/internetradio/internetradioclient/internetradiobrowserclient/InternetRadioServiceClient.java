/**
 * @file        InternetRadioServiceClient.java
 * @brief       This class is responsible for register and unregister required services.
 * @author      Praveen P G
 */

package com.hackathon.internetradio.internetradioclient.internetradiobrowserclient;



import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import com.hackathon.internetradio.internetradioclient.data.utils.SourceNotifications;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseItem;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 *@bref This class is responsible for register required services and start/stop them individually
 */

public class InternetRadioServiceClient {

    /**
     * Static variable for keeping the instance of InternetRadioServiceClient
     */
    private static final InternetRadioServiceClient INTERNET_RADIO_SERVICE_CLIENT = new InternetRadioServiceClient();

    /**
     * Member variable for keeping the instance of MediaBrowserCompat.
     */
    private MediaBrowserCompat mMediaBrowserCompat;

    /**
     * Variable to keep MediaSessionCompat token
     */
    private MediaSessionCompat.Token mToken;

    /**
     * Variable to keep MediaControllerCompat object
     */
    private MediaControllerCompat mMediaControllerCompat;

    /**
     * Member variable for storing context.
     */
    private Context mContext;

    /**
     * Variable to keep MediaControllerCompat.TransportControls object
     */
    private MediaControllerCompat.TransportControls mMediaTransportControls;


    /**
     * Member variable for keeping the instance of InternetRadioMediaControllerCallback.
     */
    private InternetRadioMediaControllerCallback mMediaControllerCompatCallback;

    /**
     * Member variable for keeping the instance of InternetRadioBrowseServiceCallback.
     */
    private InternetRadioBrowseServiceCallback mInternetRadioBrowseServiceCallback;


    /**
     * Member variable for storing bundle for subscribe calls.
     */
    private Bundle mSubscriptionBundle = new Bundle();



    private String mCurrentParentId = "MUSIC";

    /**
     * Constant to save app name name
     */
    public static final String INET_RADIO_BROWSER_SERVICE_PKG_NAME
            = "com.hackathon.internetradio.internetradioplayerservice";

    /**
     * Constant to save app name name
     */
    public static final String INET_RADIO_BROWSER_SERVICE_CLASS_NAME
            = "com.hackathon.internetradio.internetradioplayerservice.InternetRadioPlayerService";

    /**
     * Member variable to keep MediaMetaData
     */
    private MediaMetadataCompat mMediaMetadata;

    /**
     * TAG
     */
    private static final String TAG = InternetRadioServiceClient.class.getSimpleName();

    /**
     * @brief Member variable for keeping the instance of MediaBrowserCompat.ConnectionCallback.
     */
    private final MediaBrowserCompat.ConnectionCallback mConnectionCallbacks =
        new MediaBrowserCompat.ConnectionCallback() {
            @Override
            public void onConnected() {
                Log.d(TAG, ">>>>>>>Media Browser Service Connected");
                if (mMediaBrowserCompat == null) {
                    mMediaBrowserCompat = new MediaBrowserCompat(mContext,
                            new ComponentName(INET_RADIO_BROWSER_SERVICE_PKG_NAME, INET_RADIO_BROWSER_SERVICE_CLASS_NAME),
                            mConnectionCallbacks, null);
                }
                if (mMediaControllerCompatCallback == null) {
                    mMediaControllerCompatCallback = new InternetRadioMediaControllerCallback();
                }
                if (mInternetRadioBrowseServiceCallback == null) {
                    mInternetRadioBrowseServiceCallback = new InternetRadioBrowseServiceCallback();
                    mInternetRadioBrowseServiceCallback.initInternetRadioServiceNotification(mContext);

                }
                setupMediaController();
            }

            @Override
            public void onConnectionSuspended() {
                Log.d(TAG, ">>>>>>>Media Browser Service Connection failed");
            }

            @Override
            public void onConnectionFailed() {
                Log.d(TAG, ">>>>>>>Media Browser Service Connection suspended");
            }
        };


    /**
     * @brief Private constructor to make class singleton
     */
    private InternetRadioServiceClient() {
    }


    /**
     * @brief Method to return object for this singleton class.
     * @return InternetRadioServiceClient : single instance of InternetRadioServiceClient.
     */
    public static InternetRadioServiceClient getInstance() {
        return INTERNET_RADIO_SERVICE_CLIENT;
    }


    /**
     * @brief Method to register services needed from InternetRadio stack.
     */
    public void connectToInternetRadioMediaBrowserService(Context context) {
        Log.d(TAG, ">>>>>>>connectToInternetRadioMediaBrowserService");
        mContext = context;
        if (mMediaBrowserCompat == null) {
            mMediaBrowserCompat = new MediaBrowserCompat(context,
                    new ComponentName(INET_RADIO_BROWSER_SERVICE_PKG_NAME, INET_RADIO_BROWSER_SERVICE_CLASS_NAME),
                    mConnectionCallbacks, null);
            mMediaBrowserCompat.connect();
        } else {
            Log.d(TAG, ">>>>>>>connectToInternetRadioMediaBrowserService not called");
        }
    }



    /**
     * @brief Method to unregister services needed from InternetRadio stack.
     */
    public void unRegisterMediaService() {
        if (mMediaBrowserCompat != null) {
            mMediaBrowserCompat.disconnect();
        }
    }

    public void browse(String parentId) {
        mCurrentParentId = parentId;
        mMediaBrowserCompat.subscribe(parentId, mSubscriptionBundle, mInternetRadioBrowseServiceCallback);
    }

    /**
     * @brief Method to get MediaBrowserServiceConnectionCallbacks.
     */
    public MediaBrowserCompat.ConnectionCallback getMediaBrowserServiceConnectionCallbacks() {
        return mConnectionCallbacks;
    }

    /**
     * @brief Method set media controller for InternetRadio play back related activities.
     */
    public void setupMediaController() {
        if (mMediaBrowserCompat != null) {
            mToken = mMediaBrowserCompat.getSessionToken();
        }
        try {
            mMediaControllerCompat = new MediaControllerCompat(mContext, mToken);
            mMediaControllerCompat.registerCallback(mMediaControllerCompatCallback);
            mMediaTransportControls = mMediaControllerCompat.getTransportControls();

        } catch (RemoteException remoteException) {
        }
    }

    /**
     * @brief Method to play
     */
    public void play() {
        // Fix for CACDF-11913
        if (mMediaTransportControls != null) {
            mMediaTransportControls.play();
        }
    }


    /**
     * @brief Method to playFromMediaId
     * @param mediaId : Media id.
     */
    public void playFromMediaId(String mediaId) {
        if (mMediaTransportControls != null && mediaId != null && !mediaId.isEmpty()) {
            Bundle extras = new Bundle();
            mMediaTransportControls.playFromMediaId(mediaId, extras);
            Log.d("PPGG_Client", "playUsingId: " + mediaId);
        }
    }

    /**
     * @brief Method to pause
     */
    public void pause() {
        if (mMediaTransportControls != null) {
            mMediaTransportControls.pause();
        }
    }

    /**
     * @brief Method to skip
     * @param direction : Direction to skip
     */
    public void skip(int direction) {
        if (direction == Constants.SkipDirection.NEXT && mMediaTransportControls != null) {
            mMediaTransportControls.skipToNext();
        } else if (direction == Constants.SkipDirection.PREV && mMediaTransportControls != null) {
            mMediaTransportControls.skipToPrevious();
        } else {
        }
    }

    /**
     * @brief Method to get play status
     */
    public boolean getPlayStatus() {
        boolean playStatus = false;
        int playState = PlaybackStateCompat.STATE_NONE;
        if (mMediaControllerCompat != null) {
            playState = mMediaControllerCompat.getPlaybackState().getState();
        }
        if (playState == PlaybackStateCompat.STATE_PLAYING) {
            playStatus = true;
        }
        return playStatus;
    }


    /**
     * @breief Function to get MediaMetadata
     * @return MediaMetadataCompat : mMediaMetadata
     */
    public MediaMetadataCompat getMediaMetadata() {
        return mMediaMetadata;
    }

    /**
     * @brief Function to set MediaMetadata
     */
    public void setMediaMetadata(MediaMetadataCompat mediaMetadata) {
        mMediaMetadata = mediaMetadata;
    }

}
