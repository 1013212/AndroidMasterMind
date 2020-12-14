/**
 * @file        Constants.java
 */

package com.hackathon.internetradio.lib.commoninterface.constants;

/**
 * @brief This class is for saving constants common to Service and HMI apps accessing this service.
 */
public final class Constants {
    public static final String TAG = "InternetRadioClient";
    public static final int AIDL_NOTIFY_TRACK_CHANGE = 1;
    public static final int AIDL_NOTIFY_PLAY_STATUS = 2;
    public static final int AIDL_NOTIFY_CONNECTION_STATUS = 3;
    public static final int AIDL_NOTIFY_CATEGORY_LIST_ITEMS = 4;
    public static final int AIDL_NOTIFY_ERROR_STATUS = 5;
    public static final int AIDL_NOTIFY_CATEGORY_LIST_ITEMS1 = 6;
    public static final int AIDL_NOTIFY_CATEGORY_LIST_ITEMS2 = 7;

    /**
     * Constant for keeping boolean false
     */
    public static final boolean CONST_FALSE = false;

    public @interface ConnectionStatus {
        int CONNECTED = 1;
        int DISCONNECTED = 0;
        int NOT_SUPPORTED = -1;
        int NOT_AVAILABLE = -2;
    }


    /**
     * All play status type constants will be added here
     */
    public @interface PlayStatus {
        int PLAY = 1;
        int PAUSE = 0;
    }

    /**
     * All play status type constants will be added here
     */
    public @interface StationType {
        String ALL_STATIONS = "MUSIC";
        String FAVOURITE_STATIONS = "FAVORITES";
        String LIVE_STATIONS = "LIVE";
    }

    /**
     * All skip direction constants will be added here
     */
    public @interface SkipDirection {
        int NEXT = 1;
        int PREV = 2;
    }

    /**
     * All error type constants will be added here
     */
    public @interface ErrorStatus {
        int NO_ERROR = -1;
    }
}