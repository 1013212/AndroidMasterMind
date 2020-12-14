package com.hackathon.internetradio.internetradiohmi.Interfaces;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

public interface IRadioNetHmiView {

    /**
     * @brief Method to notify playlist change.
     */
    default void onNotifyStationListItems(BrowseList browseList) {

    }

    /**
     * @brief Method to notify playlist change.
     */
    default void onNotifyPlayStatus(int playStatus) {

    }

    /**
     * @brief Method to notify playlist change.
     */
    default void onNotifyTrackChange(TrackInfo trackInfo) {

    }

    /**
     * @brief Method to notify playlist change.
     */
    default void onNotifyConnectionStatus(boolean status) {

    }

    /**
     * @brief Method to notify playlist change.
     */
    default void onNotifyError(int errorType) {

    }
}
