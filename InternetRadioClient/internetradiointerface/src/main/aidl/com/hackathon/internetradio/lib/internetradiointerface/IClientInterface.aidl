/**
 * @file IClientInterface.aidl
 * @brief Interface for HMI to Service API calls.
 * @copyright Copyright (C) 2018 MEAA. .
 * Reproduction or transmission in whole or in part, in any form
 * or by any means, electronic, mechanical or otherwise, is
 * prohibited without the prior written consent of the copyright owner.
 * @author	Zubair KK
 * @version 	1.0	18.Mar.2018	Zubair KK	Initial version
 */

package com.hackathon.internetradio.lib.internetradiointerface;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseContext;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseItem;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseFilter;

/**
 * Interface for HMI to Service API calls.BrowseFilter
 * @author	Zubair KK
 * @version	1.0
 */
interface IClientInterface {

        /**
        * Interface for getting internet radio client ready status
        */
        boolean isClientSideReady();

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
         void getCategoryListItems(in BrowseFilter browseFilter);

         /**
          * Inteface to play selected browse item
          */
         void playUsingContext(in BrowseContext browseContext);

}
