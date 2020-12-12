/**
 * @file        Constants.java
 * @brief       This class is for saving constants common to Service and HMI apps accessing this
 *              service.

 *
 * @author      Zubair KK
 */

package com.hackathon.internetradio.lib.commoninterface.constants;

import android.os.Environment;

/**
 * @brief This class is for saving constants common to Service and HMI apps accessing this service.
 */
public final class Constants {
    public static final String TAG = "InternetRadioClient";
    public static final int AIDL_NOTIFY_TRACK_CHANGE = 1;

    public static final int INVALID_VALUE = -1;
    public static final String DEFAULT_INVALID_VALUE = "-1";

    public static final String DEFAULT_DEVICE_NAME = "unknown";

    public static final String DEFAULT_SERIAL_NUMBER = "";
    public static final String EMPTY_STRING = "";
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
    public @interface DeviceType {
        int NONE = 0;
    }

    public @interface BrowseCategory {
        int BROWSE_DEFAULT = -1;
        int BROWSE_TRACKS = 0;
        int BROWSE_ARTIST = 1;
        int BROWSE_ALBUM = 2;
        int BROWSE_BTA_FOLDER = 3;
        int BROWSE_GENRE = 4;
        int BROWSE_PLAYLIST = 5;
        int BROWSE_SONGS = 6;
        int BROWSE_PODCAST = 7;
        int BROWSE_AUDIOBOOK = 8;
        int BROWSE_FOLDER = 9;
        int BROWSE_FOLDER_SONG = 10;
        int BROWSE_COMPOSER = 11;
        int BROWSE_MUSICRADIO = 12;
        int BROWSE_BTA_LEVEL = 13;
        int BROWSE_MTP_CHAR_SEARCH = 14;
    }

    public @interface BrowseListType {
        int BROWSE_ORDER_SORTED = 0;
        int BROWSE_ORDER_NOT_SORTED = 1;
        int BROWSE_SEARCH = 2;
    }
}