package com.hackathon.internetradio.internetradioplayerservice.utils;

public class Constants {
    /**
     * Constant to save app name name
     */
    public static final String APP_NAME = "InternetRadioPlayerService";

    /**
     * Constant to save app name name
     */
    public static final String LIVE_BROADCAST = "You are Listening Internet Radio";

    public @interface PlaybackStatus {
        String IDLE = "PlaybackStatus_IDLE";
        String LOADING = "PlaybackStatus_LOADING";
        String PLAYING = "PlaybackStatus_PLAYING";
        String PAUSED = "PlaybackStatus_PAUSED";
        String STOPPED = "PlaybackStatus_STOPPED";
        String ERROR = "PlaybackStatus_ERROR";
    }

    public @interface BrowseCategories {
        String MUSIC = "MUSIC";
        String LIVE = "LIVE";
        String FAVORITES = "FAVORITES";
    }
}
