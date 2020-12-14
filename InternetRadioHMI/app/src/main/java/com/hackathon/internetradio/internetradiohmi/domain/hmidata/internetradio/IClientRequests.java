/**
 * @file IClientRequests.java
 * @brief Interface for defining HMI requests to Internet Radio Client.
 * @author Praveen
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio;

import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;

/**
 * @brief Interface for defining HMI requests to Internet Radio Client.
 */
public interface IClientRequests {

    /**
     * @brief Default method to get connection status from service
     * @param source : Source type
     * @return int : Connection status
     */
    default int getConnectionStatus(int source) {
        return ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .getConnectionStatus(source);
    }

    /**
     * @brief Default method to get play status from service
     * @return int : Play status.
     */
    default boolean getCurrentPlayStatus() {
        return ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .getCurrentPlayStatus();
    }

    /**
     * @brief Default method to request play
     */
    default void play() {
        ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .play();
    }

    /**
     * @brief Default method to request pause
     */
    default void pause() {
        ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .pause();
    }

    /**
     * @brief Default method to request skip
     * @param direction : skip direction
     * @param skipCount : skip count
     */
    default void skip(int direction, int skipCount) {
        ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .skip(direction, skipCount);
    }

    /**
     * @brief Default method to get the Album art path.
     * @return String : Current album art path
     */
    default String getCurrentAlbumArtPath() {
        return ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .getCurrentAlbumArt();
    }

    /**
     * @brief Default method to get track info from service
     * @return TrackInfo : Track info
     */
    default TrackInfo getCurrentTrackInfo() {
        return ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .getCurrentTrackInfo();
    }

    /**
     * @brief Default method to get error status from service
     * @return int : errorStatus
     */
    default int getErrorStatus() {
        return ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .getErrorStatus();
    }

    /**
     * @brief Default method to get station list items.
     * @return int : errorStatus
     */
    default void getStationListItems(String type) {
        ServiceInterfaceManager.getInstance().getHmiServiceInterface()
                .getStationListItems(type);
    }
}
