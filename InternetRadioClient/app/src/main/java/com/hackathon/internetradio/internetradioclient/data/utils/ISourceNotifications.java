/**
 * @file        ISourceNotifications.java
 */

package com.hackathon.internetradio.internetradioclient.data.utils;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

/**
 * @brief Interface for passing notification to Media Service Manager class.
 */
public interface ISourceNotifications {

    /**
     * @brief Function to notify play status with HMI apps
     * @param playStatus : play status
     */
    void notifyPlayStatus(int playStatus);

    /**
     * @brief Function to notify track change with HMI apps
     * @param trackInfo : track info
     */
    void notifyTrackChange(TrackInfo trackInfo);

    /**
     * @brief Function to notify device connection with HMI apps
     * @param status : device connection info
     */
    void notifyDeviceConnection(boolean status);

    /**
     * @brief Function to notify error with HMI apps
     * @param errorType : type of the error
     */
    void notifyError(int errorType);

    /**
     * @brief Function notifies category lists items
     * @param browseList : Browse list items
     */
    void notifyStationListItems(BrowseList browseList);



    /**
     * @brief Function notifies track list items
     * @param browseList : Browse list items
     */
    void notifyCurrentTrackListItems(BrowseList browseList);



    /**
     * @brief Function to notify service ready
     * @param isReady : service ready status
     */
    void notifyServiceReady(boolean isReady);




}