/**
 *InternetRadioPlayerService.java
 */

package com.hackathon.internetradio.internetradioplayerservice;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;


import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.hackathon.internetradio.internetradioplayerservice.service.PlaybackInfoListener;
import com.hackathon.internetradio.internetradioplayerservice.service.contentcatalogs.FavoriteStations;
import com.hackathon.internetradio.internetradioplayerservice.service.contentcatalogs.LiveStationList;
import com.hackathon.internetradio.internetradioplayerservice.service.contentcatalogs.MusicLibrary;
import com.hackathon.internetradio.internetradioplayerservice.service.contentcatalogs.MusicList;
import com.hackathon.internetradio.internetradioplayerservice.service.notifications.MediaNotificationManager;
import com.hackathon.internetradio.internetradioplayerservice.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * @brief Media Service Base class. HMI apps can bind to
 *        this class with AIDL interfaces and make communication.
 */
public class InternetRadioPlayerService  extends MediaBrowserServiceCompat implements Player.EventListener, AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener{

    private static final String TAG = InternetRadioPlayerService.class.getSimpleName();

    private MediaSessionCompat mSession;
    private MediaNotificationManager mMediaNotificationManager;
    private InternetRadioPlayerService.MediaSessionCallback mCallback;
    private boolean mServiceInStartedState;

    /*******************************ExoPlayer Member Variables************************************/
    private String STATUS_NOCONNECTION;
    public static final String MODE_PREPARED = "PREPARED";
    private final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private SimpleExoPlayer mExoPlayer;
    private WifiManager.WifiLock wifiLock;
    private AudioManager mAudioManager;
    private String radioStatus;
    private String streamUrl;
    private ArrayList<String> mCurrentStationUrls;
    private ArrayList<String> mAllStations;
    private ArrayList<String> mFavoriteStations;
    private ArrayList<String> mLiveStations;
    private int mCurrentStationId = 0;
    private String[] mStationTypes;
    private static final String TYPE_AAC = "aac";
    private static final String TYPE_MP3 = "mp3";
    private boolean isPreparingStarted = false;
    private BroadcastReceiver becomingNoisyReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            pauseRadio();
        }
    };
/*******************************ExoPlayer Member Variables Ends************************************/

    /**
     * @brief Default constructor
     */
    public InternetRadioPlayerService() {
    }

    /**
     * @brief Called by the system when the service is first created.
     *        Do not call this method directly. Logger Utility instance is initialized in this
     *        method
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Create a new MediaSession.
        mSession = new MediaSessionCompat(this, InternetRadioPlayerService.class.getSimpleName());
        mSession.setActive(true);
        mCallback = new InternetRadioPlayerService.MediaSessionCallback();
        mSession.setCallback(mCallback);
        mSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        setSessionToken(mSession.getSessionToken());

        mMediaNotificationManager = new MediaNotificationManager(this);
        createPlayList();



        /*******************************ExoPlayer************************************/
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        wifiLock = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE))
                .createWifiLock(WifiManager.WIFI_MODE_FULL, "mcScPAmpLock");
        //transportControls = mediaSession.getController().getTransportControls();

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        AdaptiveTrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), trackSelector);
        mExoPlayer.addListener(this);

        registerReceiver(becomingNoisyReceiver, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
        mCurrentStationUrls = new ArrayList<>();
        mCurrentStationUrls.add("http://184.154.43.106:8145");
        radioStatus = Constants.PlaybackStatus.IDLE;
        mStationTypes = new String[]{"mp3"};
        updateStationList();
        mCurrentStationUrls = mAllStations;
        /*******************************ExoPlayer************************************/
    }


    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return new BrowserRoot(MusicLibrary.getRoot(), null);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        switch (parentId) {
            case Constants.BrowseCategories.MUSIC:
                result.sendResult(MusicLibrary.getMediaItems());
                mCurrentStationUrls = mAllStations;
                break;

            case Constants.BrowseCategories.LIVE:
                result.sendResult(LiveStationList.getMediaItems());
                mCurrentStationUrls = mLiveStations;
                break;

            case Constants.BrowseCategories.FAVORITES:
                result.sendResult(FavoriteStations.getMediaItems());
                mCurrentStationUrls = mFavoriteStations;
                break;
        }
    }

    /**
     * @brief Method Called by the system when the service is destroyed.
     *        Logger Utility instance is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaNotificationManager.onDestroy();
        stop();
        mSession.release();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }

    /**
     * Method to to create play list.
     */
    private void createPlayList() {
        List<MediaSessionCompat.QueueItem> musicList = MusicList.getMusicQueueList();
        if (mCallback != null && musicList != null) {
            for (MediaSessionCompat.QueueItem queueItem : musicList)
            mCallback.onAddQueueItem(queueItem.getDescription());
        }
    }

    /*******************************ExoPlayerMethods Start************************************/




    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        /* If radio is prepared then start playback */
        setRadioStatus("");
        sendBroadcast(new Intent(MODE_PREPARED));
        isPreparingStarted = false;
        playRadio(mCurrentStationUrls.get(0));
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {
            case Player.STATE_BUFFERING:
                radioStatus = Constants.PlaybackStatus.LOADING;
                break;
            case Player.STATE_ENDED:
                radioStatus = Constants.PlaybackStatus.STOPPED;
                break;
            case Player.STATE_IDLE:
                radioStatus = Constants.PlaybackStatus.IDLE;
                break;
            case Player.STATE_READY:
                radioStatus = playWhenReady ? Constants.PlaybackStatus.PLAYING : Constants.PlaybackStatus.PAUSED;
                break;
            default:
                radioStatus = Constants.PlaybackStatus.IDLE;
                break;
        }

        EventBus.getDefault().post(radioStatus);
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        EventBus.getDefault().post(Constants.PlaybackStatus.ERROR);
    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public void onAudioFocusChange(int focusChange) {

        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                mExoPlayer.setVolume(0.8f);
                resumeRadio();
                break;

            case AudioManager.AUDIOFOCUS_LOSS:
                stop();
                break;

            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                if (isPlaying()) pauseRadio();
                break;

            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                if (isPlaying())
                    mExoPlayer.setVolume(0.1f);
                break;
        }

    }

    public void playRadio(String streamUrl) {
        this.streamUrl = streamUrl;
        if (wifiLock != null && !wifiLock.isHeld()) {
            wifiLock.acquire();
        }

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, getUserAgent(), BANDWIDTH_METER);
        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .setExtractorsFactory(new DefaultExtractorsFactory())
                .createMediaSource(Uri.parse(streamUrl));
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    public void setRadioStatus(String radioStatus) {
        if (!hasConnectivity())
            this.radioStatus = STATUS_NOCONNECTION;
        else
            this.radioStatus = radioStatus;
    }

    public boolean hasConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public void resumeRadio() {
        if(streamUrl != null)
            playRadio(streamUrl);
    }

    public void pauseRadio() {
        mExoPlayer.setPlayWhenReady(false);
        mAudioManager.abandonAudioFocus(this);
        wifiLockRelease();
    }

    public void stop() {
        mExoPlayer.stop();
        mAudioManager.abandonAudioFocus(this);
        wifiLockRelease();
    }

    public void playOrPause(String url){
        int result = mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if(result != AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            stop();
        }

        if(streamUrl != null && streamUrl.equals(url)){
            if(!isPlaying()){
                playRadio(streamUrl);
            } else {
                pauseRadio();
            }
        } else {
            if(isPlaying()){
                pauseRadio();
            }
            playRadio(url);
        }
    }

    public String getRadioStatus(){
        return radioStatus;
    }

    public boolean isPlaying(){
        return this.radioStatus.equals(Constants.PlaybackStatus.PLAYING);
    }

    private void wifiLockRelease(){
        if (wifiLock != null && wifiLock.isHeld()) {
            wifiLock.release();
        }
    }

    private String getUserAgent(){
        return Util.getUserAgent(this, getClass().getSimpleName());
    }


    public String getCurrentStationType() {
        if (mStationTypes[mCurrentStationId].toLowerCase().trim()
                .equals("aac")
                || mStationTypes[mCurrentStationId].toLowerCase().trim()
                .equals("aac+"))
            return TYPE_AAC;
        else
            return TYPE_MP3;
    }

    public void setCurrentStationID(int id) {
        mCurrentStationId = id;
    }

    public void updateStationList() {
        mAllStations = new ArrayList<>();
        mFavoriteStations = new ArrayList<>();
        mLiveStations = new ArrayList<>();

        mAllStations.add("http://216.55.141.189:8653/live");
        mAllStations.add("http://205.164.62.22:8075");
        mAllStations.add("http://184.154.43.106:8145");
        mAllStations.add("http://stream.infowars.com:80");
        mAllStations.add("http://wnyc-iheart.streamguys.com/wnycfm-iheart.aac");
        mAllStations.add("http://titan.shoutca.st:8647/stream");
        mAllStations.add("http://stream.radio.co/s1383afdc9/listen?ver=828256");
        mAllStations.add("http://192.111.140.6:8546/stream");


        mLiveStations.add("http://216.55.141.189:8653/live");
        mLiveStations.add("http://184.154.43.106:8145");
        mLiveStations.add("http://stream.infowars.com:80");
        mLiveStations.add("http://stream.radio.co/s1383afdc9/listen?ver=828256");
        mLiveStations.add("http://144.217.195.24:8605/;stream.mp3");
        mLiveStations.add("http://198.245.60.88:8300/stream");
        mLiveStations.add("http://167.114.131.90:5412/stream3");
        mLiveStations.add("http://198.27.67.39:8000/pravasiradio.mp3");

        mFavoriteStations.add("http://viadj.viastreaming.net:7233/;listen.mp3");
        mFavoriteStations.add("http://prclive1.listenon.in:9908/;");
        mFavoriteStations.add("http://144.217.195.24:8605/;stream.mp3");
        mFavoriteStations.add("http://198.245.60.88:8300/stream");
        mFavoriteStations.add("http://167.114.131.90:5412/stream3");
        mFavoriteStations.add("http://198.27.67.39:8000/pravasiradio.mp3");

    }

    /*******************************ExoPlayerMethods End************************************/



    // MediaSession Callback: Transport Controls -> MediaPlayerAdapter
    public class MediaSessionCallback extends MediaSessionCompat.Callback {
        private final List<MediaSessionCompat.QueueItem> mPlaylist = new ArrayList<>();
        private int mQueueIndex = -1;
        private MediaMetadataCompat mPreparedMedia;

        @Override
        public void onAddQueueItem(MediaDescriptionCompat description) {
            mPlaylist.add(new MediaSessionCompat.QueueItem(description, description.hashCode()));
            mQueueIndex = (mQueueIndex == -1) ? 0 : mQueueIndex;
            mSession.setQueue(mPlaylist);
        }

        @Override
        public void onRemoveQueueItem(MediaDescriptionCompat description) {
            mPlaylist.remove(new MediaSessionCompat.QueueItem(description, description.hashCode()));
            mQueueIndex = (mPlaylist.isEmpty()) ? -1 : mQueueIndex;
            mSession.setQueue(mPlaylist);
        }

        @Override
        public void onPrepare() {
            Log.d(TAG, "onPrepare Entry");
            if (mQueueIndex < 0 && mPlaylist.isEmpty()) {
                // Nothing to play.
                return;
            }

            final String mediaId = mCurrentStationUrls.get(mCurrentStationId);
            Log.d(TAG, "onPrepare station id, media id: " + mCurrentStationId + "," + mediaId);
            mPreparedMedia = MusicLibrary.getMetadata(InternetRadioPlayerService.this, mediaId);
            Log.d(TAG, "onPrepare metadata: " + mPreparedMedia);
            mSession.setMetadata(mPreparedMedia);

            if (!mSession.isActive()) {
                mSession.setActive(true);
            }
        }

        @Override
        public void onPlayFromMediaId(String mediaId, Bundle extras) {
            Log.d(TAG, "onPlayFromMediaId media id: " + mediaId);
            if (mediaId != null && !TextUtils.isEmpty(mediaId)) {
                if (mediaId.equals("start_playback")) {
                    mediaId = mCurrentStationUrls.get(0);
                }
                playOrPause(mediaId);
                mSession.setPlaybackState(createNewPlaybackState(PlaybackStateCompat.STATE_PLAYING));
                onPrepare();
            }

        }

        @Override
        public void onPlay() {
            Log.d(TAG, "onPrepare station id, media id: " + mCurrentStationId + "," + mCurrentStationUrls.get(mCurrentStationId));
            playOrPause(mCurrentStationUrls.get(mCurrentStationId));
            onPrepare();
        }

        @Override
        public void onPause() {
            Log.d(TAG, "onPause>>>");
            pauseRadio();
            mSession.setPlaybackState(createNewPlaybackState(PlaybackStateCompat.STATE_PAUSED));
        }

        @Override
        public void onStop() {
            stop();
            mSession.setActive(false);
        }

        @Override
        public void onSkipToNext() {

            if (mCurrentStationId < mCurrentStationUrls.size()) {
                mCurrentStationId++;
                if (mCurrentStationId == mCurrentStationUrls.size()) {
                    mCurrentStationId = 0;
                }
            } else {
                mCurrentStationId = 0;
            }
            onPlay();
        }

        @Override
        public void onSkipToPrevious() {
            if (mCurrentStationId > 0) {
                mCurrentStationId--;
            } else {
                mCurrentStationId = mCurrentStationUrls.size() - 1;
            }
            onPlay();
        }

        @Override
        public void onSeekTo(long pos) {

        }

        private boolean isReadyToPlay() {
            return (!mPlaylist.isEmpty());
        }
    }

    // This is the main reducer for the player state machine.
    private PlaybackStateCompat createNewPlaybackState(@PlaybackStateCompat.State int newPlayerState) {
        Log.d(TAG, "createNewPlaybackState state: " + newPlayerState);
        long availableActions  = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID
                | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH
                | PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS;;

        switch (newPlayerState) {
            case PlaybackStateCompat.STATE_STOPPED:
                availableActions |= PlaybackStateCompat.ACTION_PLAY
                        | PlaybackStateCompat.ACTION_PAUSE;
                break;
            case PlaybackStateCompat.STATE_PLAYING:
                availableActions |= PlaybackStateCompat.ACTION_STOP
                        | PlaybackStateCompat.ACTION_PAUSE
                        | PlaybackStateCompat.ACTION_SEEK_TO;
                break;
            case PlaybackStateCompat.STATE_PAUSED:
                availableActions |= PlaybackStateCompat.ACTION_PLAY
                        | PlaybackStateCompat.ACTION_STOP;
                break;
            default:
                availableActions |= PlaybackStateCompat.ACTION_PLAY
                        | PlaybackStateCompat.ACTION_PLAY_PAUSE
                        | PlaybackStateCompat.ACTION_STOP
                        | PlaybackStateCompat.ACTION_PAUSE;
        }

        final PlaybackStateCompat.Builder stateBuilder = new PlaybackStateCompat.Builder();
        stateBuilder.setActions(availableActions);
        stateBuilder.setState(newPlayerState,
                0,
                1.0f,
                0);

        return stateBuilder.build();
    }

    /*********************************Start MediaSession Class**************************************/
    // MediaPlayerAdapter Callback: MediaPlayerAdapter state -> InternetRadioPlayerService.
    public class MediaPlayerListener extends PlaybackInfoListener {

        private final InternetRadioPlayerService.MediaPlayerListener.ServiceManager mServiceManager;

        MediaPlayerListener() {
            mServiceManager = new InternetRadioPlayerService.MediaPlayerListener.ServiceManager();
        }

        @Override
        public void onPlaybackStateChange(PlaybackStateCompat state) {
            // Report the state to the MediaSession.
            mSession.setPlaybackState(state);

            // Manage the started state of this service.
            switch (state.getState()) {
                case PlaybackStateCompat.STATE_PLAYING:
                    mServiceManager.moveServiceToStartedState(state);
                    break;
                case PlaybackStateCompat.STATE_PAUSED:
                    mServiceManager.updateNotificationForPause(state);
                    break;
                case PlaybackStateCompat.STATE_STOPPED:
                    mServiceManager.moveServiceOutOfStartedState(state);
                    break;
            }
        }

        class ServiceManager {

            private void moveServiceToStartedState(PlaybackStateCompat state) {
                Notification notification = null;
                        /*mMediaNotificationManager.getNotification(
                                mPlayback.getCurrentMedia(), state, getSessionToken());*/

                if (!mServiceInStartedState) {
                    ContextCompat.startForegroundService(
                            InternetRadioPlayerService.this,
                            new Intent(InternetRadioPlayerService.this, InternetRadioPlayerService.class));
                    mServiceInStartedState = true;
                }

                startForeground(MediaNotificationManager.NOTIFICATION_ID, notification);
            }

            private void updateNotificationForPause(PlaybackStateCompat state) {
                stopForeground(false);
                Notification notification = null;
                        /*mMediaNotificationManager.getNotification(
                                mPlayback.getCurrentMedia(), state, getSessionToken());*/
                mMediaNotificationManager.getNotificationManager()
                        .notify(MediaNotificationManager.NOTIFICATION_ID, notification);
            }

            private void moveServiceOutOfStartedState(PlaybackStateCompat state) {
                stopForeground(true);
                stopSelf();
                mServiceInStartedState = false;
            }
        }

    }
    /*********************************Start MediaSession Class**************************************/
}
