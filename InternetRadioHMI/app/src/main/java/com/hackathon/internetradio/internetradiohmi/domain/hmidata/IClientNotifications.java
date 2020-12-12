/**
 * @file        IClientNotifications.java
 * @brief       Interface for defining notification to HMI from internet radio client.
 * @author      Praveen
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata;


import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
import com.hackathon.internetradio.lib.commoninterface.DeviceConnectionInfo;

/**
 * @brief  Interface for defining notification to HMI from Internet Radio Client.
 */
public interface IClientNotifications {

    /**
     * @brief Default function to notify playStatus
     * @param playStatus : Play status.
     */
    default void onNotifyPlayStatus(int playStatus) {
    }

    /**
     * @brief Default function to notify track change.
     * @param trackInfo : Track info
     */
    default void onNotifyTrackChange(TrackInfo trackInfo) {
    }

    /**
     * @brief Default function to notify device connection.
     * @param deviceConnectInfo : Device connection info.
     */
    default void onNotifyDeviceConnection(DeviceConnectionInfo deviceConnectInfo) {
    }


    /**
     * @brief Default function to notify Error.
     * @param errorType : Type of error.
     */
    default void onNotifyFileError(int errorType) {
    }

    /**
     * @brief Method handles the browse list items notification
     * @param browseItems : Browse items list.
     */
    default void onNotifyCategoryListItems(BrowseList browseItems) {
    }

    /**
     * @brief Method handles the cover art notification
     * @param coverArtPath : cover art path.
     */
    default void onNotifyCoverArtPath(String coverArtPath) {
    }


}