package com.hackathon.internetradio.internetradioclient.internetradiobrowserclient;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.widget.Toast;


import com.hackathon.internetradio.internetradioclient.data.utils.SourceNotifications;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseItem;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

import java.util.ArrayList;
import java.util.List;

/**
 *@bref This class is responsible for handling browse related call backs
 */
public class InternetRadioBrowseServiceCallback extends MediaBrowserCompat.SubscriptionCallback {

    private Context mContext;

    /**
     * Variable to store list of browse item.
     */
    private List<BrowseItem> mBrowseItemList = new ArrayList<>();


    /**
     * Member variable for keeping the the instance of SourceNotifications
     */
    private SourceNotifications mSourceNotifications = new SourceNotifications();

    /**
     * @brief Public constructor.
     */
    public InternetRadioBrowseServiceCallback() {
    }

    /**
     * @brief Method to initialize serviceNotification
     */
    public void initInternetRadioServiceNotification(Context context) {
        mContext = context;
    }

    /**
     * @brief Method to get the browse results.
     * @param parentId : Parent id
     * @param children : Media item children
     */
    @Override
    public void onChildrenLoaded(@NonNull String parentId,
                                 @NonNull List<MediaBrowserCompat.MediaItem> children,
                                 Bundle options) {
        Log.d("BROWSE_RESULT", "onChildrenLoaded: " + children.toString());
        parseBrowseListItems(children);
    }

    /**
     * @brief Called when an error happens while subscribe() or the connected service doesn't support
     * @param parentId : parent ID
     */
    @Override
    public void onError(String parentId, Bundle options) {

    }

    /**
     * @brief To re-arrange the received browse list items to required format
     */
    public void parseBrowseListItems(List<MediaBrowserCompat.MediaItem> children) {
        String itemId;
        String itemName;

        if (mBrowseItemList != null) {
            mBrowseItemList.clear();
        }

        Log.d("PARSE_LIST", "parseBrowseListItems: entry" );
        if (children != null) {
            for (MediaBrowserCompat.MediaItem mediaItem : children) {
                itemId = mediaItem.getMediaId();
                itemName = mediaItem.getDescription().getTitle().toString();
                BrowseItem browseItem = new BrowseItem(itemId, itemName);
                mBrowseItemList.add(browseItem);
                Log.d("PARSE_LIST", "parseBrowseListItems: " + itemId + ":" + itemName);
            }
        }

        if (mSourceNotifications != null) {
            BrowseList browseList = new BrowseList(0,0 , mBrowseItemList);
            Log.d("PARSE_LIST", "parseBrowseListItems: " + browseList.toString());
            mSourceNotifications.notifyStationListItems(browseList);
        }
    }
}
