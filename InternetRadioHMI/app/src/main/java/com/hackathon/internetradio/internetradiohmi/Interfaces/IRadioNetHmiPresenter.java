package com.hackathon.internetradio.internetradiohmi.Interfaces;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

public interface IRadioNetHmiPresenter {


    /**
     * @brief Method to get ConnectionStatus.
     */
    int getConnectionStatus(int source);

    /**
     * @brief Method to get CurrentPlayStatus.
     */
    boolean getCurrentPlayStatus();

    /**
     * @brief Method for play.
     */
    void play();

    /**
     * @brief Method for play.
     */
    void playFromMediaId(String id);

    /**
     * @brief Method for pause.
     */
    void pause();

    /**
     * Method to start model
     */
    void start();

    /**
     * Method to stop model
     */
    void stop();

    /**
     * @brief Method for skip.
     */
    void skip(int direction, int skipCount);

    /**
     * @brief Method to get CurrentAlbumArt.
     */
    String getCurrentAlbumArt();

    /**
     * @brief Method fto get currentTrackInfo.
     */
    TrackInfo getCurrentTrackInfo();

    /**
     * @brief Method to get ErrorStatus.
     */
    int getErrorStatus();

    /**
     * @brief Method for connectToService.
     */
    void connectToService();

    /**
     * @brief Method to get station ListItems.
     */
    void getStationListItems(String stationType);

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
