/**
 * @file        HmiConstants.java
 * @brief       This file contains the constants used for hmi

 *
 * @author      Praveen
 */

package com.hackathon.internetradio.internetradiohmi.utilities;

/**
 * @brief  This class defines the constants used in UI.
 */
public final class HmiConstants {

    /**
     * Internet Radio package name.
     */
    public static final String TAG_INTERNET_RADIO_CLIENT_PACKAGE_NAME
            = "com.hackathon.internetradio.internetradioclient";
    /**
     * Internet Radio class name.
     */
    public static final String TAG_INTERNET_RADIO_CLIENT_CLASS_NAME
            = "com.hackathon.internetradio.internetradioclient.InternetRadioClient";

    /**
     * Events.
     */
    public @interface ServiceEvent {
        int AIDL_NOTIFY_PLAY_STATUS                                     = 0;
        int AIDL_NOTIFY_DEVICE_CONNECT                                  = 1;
        int AIDL_NOTIFY_FILE_ERROR                                      = 2;
    }
}