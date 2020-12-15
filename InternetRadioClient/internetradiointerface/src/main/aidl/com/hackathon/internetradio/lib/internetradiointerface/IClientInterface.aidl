/**
 * @file IClientInterface.aidl
 */

package com.hackathon.internetradio.lib.internetradiointerface;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseItem;
/**
 * Interface for HMI to Service API calls.BrowseFilter
 * @author	PG
 * @version	1.0
 */
interface IClientInterface {

        /**
        * Interface for getting internet radio client ready status
        */
        boolean isClientSideReady();

        /**
        * Interface for start
        * and return void in AIDL.
        */
        void start();

        /**
         * Interface for play the song
         * and return void in AIDL.
         */
         void play();

         /**
         * Interface for pause the song
         * and return void in AIDL.
         */
         void pause();

        /**
         * Interface for pause the song
         * and return void in AIDL.
         */
         void connectToService();

         /**
         * Fetch current player status from Service and return source ID in AIDL.
         */
         boolean getCurrentPlayStatus();

         /**
         * Fetch current connection status from Service and return source ID in AIDL.
         */
         int getConnectionStatus(int sourceId);

         /**
         * Skip current song
         */
         void skip(int direction, int skipCount);

         /**
         * Get Album art
         */
         String getCurrentAlbumArt();

         /**
         * Fetch current track info from service and return metadata info in AIDL
         */
         TrackInfo getCurrentTrackInfo();

         /**
         * Fetch current error status
         */
         int getErrorStatus();

         /**
          * Fetch the category list item.
          */
         void getStationListItems(String stationType);

         /**
          * Inteface to play selected browse item
          */
         void playUsingId(String id);

         /**
          * Interface for setFavorite
          * and return void in AIDL.
          */
         void setFavorite(String item);

}
