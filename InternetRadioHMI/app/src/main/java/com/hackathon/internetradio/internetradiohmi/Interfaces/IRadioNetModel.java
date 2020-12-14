package com.hackathon.internetradio.internetradiohmi.Interfaces;

import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceEventManager;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio.HmiServiceInterface;
import com.hackathon.internetradio.internetradiohmi.utilities.Subject;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;

public interface IRadioNetModel {

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
     * Method to start model
     */
    void start();

    /**
     * Method to stop model
     */
    void stop();

    /**
     * Method to get service event data
     */
    Object getEventData(int event);

    /**
     * Method to set service event data
     */
    void setEventData(int event, Object data);

    /**
     * Method to get event observable
     */
    Subject getEventObservable(int observableType);

    /**
     * Method to get service interface manager object
     */
    ServiceInterfaceManager getServiceInterfaceManager();

    /**
     * Method to get service event manager object
     */
    ServiceEventManager getServiceEventManager();

    /**
     * Method to get hmi service interface object
     */
    HmiServiceInterface getHmiServiceInterface();
}
