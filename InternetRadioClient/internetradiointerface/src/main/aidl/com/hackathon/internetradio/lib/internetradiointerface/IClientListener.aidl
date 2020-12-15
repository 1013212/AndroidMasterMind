/**
 * @file        IClientListener.aidl
 */

package com.hackathon.internetradio.lib.internetradiointerface;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

/**
 * Interface through which Service notify the bind HMI apps.
 */
oneway interface IClientListener {

    /**
    * Notify the current track is changed
    */
    void notifyTrackChange(in TrackInfo trackInfo);

    /**
    * Notify the connection status update
    */
    void notifyConnectionStatus(boolean status);

    /**
    * Notify the category list items
    */
    void notifyStationListItems(in BrowseList browseList);

    /**
    * Notify the file error
    */
    void notifyError(int errorType);

    /**
    * Notify the play status is changed
    */
    void notifyPlayStatus(int playStatus);

}