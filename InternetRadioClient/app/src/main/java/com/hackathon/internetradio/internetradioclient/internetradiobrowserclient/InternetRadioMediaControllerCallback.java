/**
 * @file        InternetRadioMediaControllerCallback.java
 * @brief       This class is responsible for media play control related callbacks.


 * @author      Praveen P G
 */

package com.hackathon.internetradio.internetradioclient.internetradiobrowserclient;

import android.media.MediaMetadata;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;

import com.hackathon.internetradio.internetradioclient.data.utils.SourceNotifications;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

import org.w3c.dom.Text;

import java.util.Random;

/**
 *@bref This class is responsible for handling play control related call backs.
 */
public class InternetRadioMediaControllerCallback extends MediaControllerCompat.Callback {

    /**
     * Variable to keep InternetRadioServiceClient object
     */
    private InternetRadioServiceClient mInternetRadioServiceClient = InternetRadioServiceClient.getInstance();

    /**
     * Member variable for keeping the the instance of SourceNotifications
     */
    private SourceNotifications mSourceNotifications = new SourceNotifications();

    /**
     * TAG
     */
    private static final String TAG = InternetRadioMediaControllerCallback.class.getSimpleName();

    /**
     * @brief InternetRadioMediaControllerCallback constructor
     */
    public InternetRadioMediaControllerCallback() {

    }

    /**
     * @brief Method to capture playback state changes
     * @param playbackState : Playback state
     */
    @Override
    public void onPlaybackStateChanged(PlaybackStateCompat playbackState) {
        if (playbackState != null) {
            int currentPlayStatus = playbackState.getState();
            Log.d(TAG, "onPlaybackStateChanged currentPlayStatus: " + currentPlayStatus);
            switch (currentPlayStatus) {
                case PlaybackStateCompat.STATE_STOPPED:
                case PlaybackStateCompat.STATE_ERROR:
                case PlaybackStateCompat.STATE_PAUSED:
                    currentPlayStatus = Constants.PlayStatus.PAUSE;
                    break;
                case PlaybackStateCompat.STATE_PLAYING:
                    currentPlayStatus = Constants.PlayStatus.PLAY;
                    break;
                default:
                    currentPlayStatus = Constants.PlayStatus.PAUSE;
                    break;
            }
            if (mSourceNotifications != null) {
                mSourceNotifications.notifyPlayStatus(currentPlayStatus);
            }
        }
    }

    /**
     * @brief Method to capture metadata related changes
     * @param metadata : Metatdata
     */
    @Override
    public void onMetadataChanged(MediaMetadataCompat metadata) {
        if (metadata != null) {
            if (isMetaDataChanged(metadata)) {
                TrackInfo trackInfo = createTrackInfo(metadata);
                if (mSourceNotifications != null) {
                    mSourceNotifications.notifyTrackChange(trackInfo);
                }
            }
        }
    }

    private boolean isMetaDataChanged(MediaMetadataCompat currentMetaData) {
        boolean metaDataChanged = false;

        MediaMetadataCompat previousMetaData = mInternetRadioServiceClient.getMediaMetadata();

        if (previousMetaData == null && currentMetaData != null) {
            mInternetRadioServiceClient.setMediaMetadata(currentMetaData);
            Log.d(TAG, "isMetaDataChanged: " + true);
            return true;
        }

        if (previousMetaData != null && currentMetaData != null) {
            String CurrentMediaId = currentMetaData.getString(MediaMetadata.METADATA_KEY_MEDIA_ID);
            String lastMediaId = previousMetaData.getString(MediaMetadata.METADATA_KEY_MEDIA_ID);
            if (CurrentMediaId != null && !TextUtils.isEmpty(CurrentMediaId)
                    && lastMediaId != null && !TextUtils.isEmpty(lastMediaId)) {
                if (!CurrentMediaId.equals(lastMediaId)) {
                    metaDataChanged = true;
                    mInternetRadioServiceClient.setMediaMetadata(currentMetaData);
                }
            }
        }
        Log.d(TAG, "isMetaDataChanged: " + metaDataChanged);
        return metaDataChanged;

    }

    private TrackInfo createTrackInfo(MediaMetadataCompat currentMetaData) {
        TrackInfo trackInfo = new TrackInfo();

        if (currentMetaData != null) {
            Log.d(TAG, "createTrackInfo mediaId: " + currentMetaData.getString(MediaMetadata.METADATA_KEY_MEDIA_ID));
            Log.d(TAG, "createTrackInfo title: " + currentMetaData.getString(MediaMetadata.METADATA_KEY_TITLE));

            String coverArt = "album_art_" + generateRandom();
            trackInfo.setId(currentMetaData.getString(MediaMetadata.METADATA_KEY_MEDIA_ID));
            trackInfo.setTitle(currentMetaData.getString(MediaMetadata.METADATA_KEY_TITLE));
            trackInfo.setSongUrl(coverArt);
            trackInfo.setAlbumName(currentMetaData.getString(MediaMetadata.METADATA_KEY_ALBUM));
            trackInfo.setArtist(currentMetaData.getString(MediaMetadata.METADATA_KEY_ARTIST));
        } else {
            Log.d(TAG, "createTrackInfo currentMetaData is null");
        }
        return trackInfo;
    }

    private int generateRandom() {
        Random r = new Random();
        int low = 1;
        int high = 15;
        return  r.nextInt(high-low) + low;
    }

    /**
     * @brief Method to capture session related changes
     */
    @Override
    public void onSessionDestroyed() {
        InternetRadioServiceClient.getInstance().unRegisterMediaService();
    }



    /**
     * @brief Method for getting metadata details and passing the values to MediaMetadata
     *        constructor.
     * @param metadata : metadata details of a song from MediaController.callback.
     * @return MediaMetaData : passing metadata to trigger notification to HMI.
     */
    public void createMetadata(MediaMetadataCompat metadata) {
        return;
    }


}