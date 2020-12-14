/**
 * @file        InternetRadioClientHandler.java
 * @brief       Handler class for passing AIDL broadcast notification.
 *              This will avoid concurrent notification.
 * @author      Zubair KK
 */

package com.hackathon.internetradio.internetradioclient.data;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

import java.lang.ref.WeakReference;

/**
 * @brief Handler class for passing AIDL broadcast notification.
 *        This will avoid concurrent notification.
 */
public class InternetRadioClientHandler extends Handler {

    /**
     * Member variable for keeping week reference of InternetRadioClientManager;
     */
    private WeakReference<InternetRadioClientManager> mInternetRadioClientInstance;


    /**
     * @brief Constructor for initialization.
     * @param musicManager : Static instance of media service manager
     */
    public InternetRadioClientHandler(InternetRadioClientManager musicManager) {
        mInternetRadioClientInstance = new WeakReference<>(musicManager);
    }

    /**
     * @brief Function to be called on handle message
     * @params message :  Message message
     */
    @Override
    public void handleMessage(Message message) {
        InternetRadioClientManager internetRadioClientManager = mInternetRadioClientInstance.get();
        if (internetRadioClientManager != null && message != null) {
            int broadcastCount = internetRadioClientManager.beginBroadcast();
            try {
                /**
                 * This for loop iterates through number of application bind to service
                 */
                for (int i = 0; i < broadcastCount; i++) {

                    switch (message.what) {
                        case Constants.AIDL_NOTIFY_TRACK_CHANGE:
                            Log.d("NOTIFY_track_handler", "notifyTrackChange: ");
                            internetRadioClientManager.getCallbackList().getBroadcastItem(
                                    i).notifyTrackChange((TrackInfo) message.obj);
                            break;

                        case Constants.AIDL_NOTIFY_PLAY_STATUS:
                            internetRadioClientManager.getCallbackList().getBroadcastItem(
                                    i).notifyPlayStatus(
                                    (int) message.obj);
                            break;
                        case Constants.AIDL_NOTIFY_CONNECTION_STATUS:
                            internetRadioClientManager.getCallbackList().getBroadcastItem(
                                    i).notifyConnectionStatus((boolean) message.obj);
                            break;

                        case Constants.AIDL_NOTIFY_ERROR_STATUS:
                            internetRadioClientManager.getCallbackList().getBroadcastItem(
                                    i).notifyError((int) message.obj);
                            break;

                        case Constants.AIDL_NOTIFY_CATEGORY_LIST_ITEMS:
                            Log.d("NOTIFY_Category_handler", "notifyStationListItems: ");
                            BrowseList browseList =  (BrowseList) message.obj;
                            Log.d("NOTIFY_Category_handler", "notifyStationListItems: " + browseList.toString());
                            internetRadioClientManager.getCallbackList().getBroadcastItem(
                                    i).notifyStationListItems(browseList);
                            break;

                        default:
                            break;
                    }
                }
            } catch (RemoteException e) {
            } catch (IllegalStateException e) {
            } finally {
                Log.d(">>>>>>", "Exception");
                internetRadioClientManager.finishBroadcast();
            }
        } else {
            if (internetRadioClientManager == null) {
                Log.d(">>>>>>", "internetRadioClientManager is null");
            } else {
                Log.d(">>>>>>", "message is null");
            }
        }
    }
}