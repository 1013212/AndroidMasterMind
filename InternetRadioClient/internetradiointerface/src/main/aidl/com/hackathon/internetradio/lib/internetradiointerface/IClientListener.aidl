/**
 * @file        IClientListener.aidl
 * @brief       Interface through which Service notify the bind HMI apps.

 *
 * @author	    Zubair KK
 */

package com.hackathon.internetradio.lib.internetradiointerface;

import com.hackathon.internetradio.lib.commoninterface.MetaData;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.DeviceConnectionInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseItem;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

/**
 * Interface through which Service notify the bind HMI apps.
 */
oneway interface IClientListener {


    /**
    * Notify the play status is changed
    */
    void notifyPlayStatus(int playStatus);

    /**
    * Notify the current track is changed
    */
    void notifyTrackChange(in TrackInfo trackInfo);

    /**
    * Notify the connection status update
    */
    void notifyConnectionStatus(in DeviceConnectionInfo deviceConnectInfo);

    /**
    * Notify the file error
    */
    void notifyError(int errorType);

    /**
    * Notify the category list items
    */
    void notifyCategoryListItems(in BrowseList browseList);

}