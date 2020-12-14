/**
 * @file      ServiceEventListener.java
 * @brief     This file contains the internet radio client event listener implementation

 *
 * @author    Praveen
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio.HmiServiceInterface;
import com.hackathon.internetradio.internetradiohmi.utilities.HmiConstants;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;
import com.hackathon.internetradio.lib.internetradiointerface.IClientListener;

/**
 * @brief Implementation for ServiceEventListener class.
 *          ServiceEventListener contains the implementations for listening the events from Service.
 */
public class ServiceEventListener {

    /**
     * Variable to store object of ServiceEventHandler
     */
    private ServiceEventHandler mServiceEventHandler;

    /**
     * Variable to store object of IClientListener stub
     */
    private final IClientListener mClientListener = new IClientListener.Stub() {

        /**
         * @brief Service callback method to notify play status.
         * @param playStatus : play status.
         * @exception RemoteException : remote exception
         */
        @Override
        public void notifyPlayStatus(int playStatus) throws RemoteException {
            Message msg = mServiceEventHandler.obtainMessage(
                    HmiConstants.ServiceEvent.AIDL_NOTIFY_PLAY_STATUS, playStatus);
            mServiceEventHandler.removeMessages(HmiConstants.ServiceEvent.AIDL_NOTIFY_PLAY_STATUS);
            mServiceEventHandler.sendMessage(msg);

            if (playStatus == Constants.PlayStatus.PLAY) {
                System.out.println(" PlayStatus.PLAY ");
            } else {
                System.out.println("PlayStatus.PAUSE ");
            }
        }

        @Override
        public void notifyTrackChange(TrackInfo trackInfo) throws RemoteException {
            Message msg = mServiceEventHandler.obtainMessage(
                    HmiConstants.ServiceEvent.AIDL_NOTIFY_TRACK_CHANGE, trackInfo);
            mServiceEventHandler.removeMessages(HmiConstants.ServiceEvent.AIDL_NOTIFY_TRACK_CHANGE);
            mServiceEventHandler.sendMessage(msg);

        }

        /**
         * @brief Service callback method to notify connection status.
         * @param status : device connect status.
         * @exception RemoteException : remote exception
         */
        @Override
        public void notifyConnectionStatus(boolean status) throws RemoteException {
            if (isServiceReady()) {
                Message msg = mServiceEventHandler.obtainMessage(
                        HmiConstants.ServiceEvent.AIDL_NOTIFY_DEVICE_CONNECT, status);
                mServiceEventHandler.sendMessage(msg);
            }
        }

        /**
         * @brief Service callback method to notify error status of a source.
         * @param errorType : Error type.
         * @exception RemoteException : remote exception
         */
        @Override
        public void notifyError(int errorType) throws RemoteException {
            if (isServiceReady()) {
                Message msg = mServiceEventHandler.obtainMessage(
                        HmiConstants.ServiceEvent.AIDL_NOTIFY_FILE_ERROR, errorType);
                mServiceEventHandler.sendMessage(msg);
            }
        }

        @Override
        public void notifyStationListItems(BrowseList browseList) throws RemoteException {
            Log.d("HMI_NOTIFY_Category", "notifyStationListItems: " + browseList.toString());
            Message msg = mServiceEventHandler.obtainMessage(
                    HmiConstants.ServiceEvent.AIDL_NOTIFY_CATEGORY_LIST_ITEMS, browseList);
            mServiceEventHandler.sendMessage(msg);
        }

        /**
         * Method to check if service is ready
         */
        private boolean isServiceReady() {
            return true;
        }
    };

    /**
     * @brief ServiceEventListener constructor.
     * @param serviceEventHandler : object of ServiceEventHandler.
     */
    public ServiceEventListener(ServiceEventHandler serviceEventHandler) {
        mServiceEventHandler = serviceEventHandler;
    }

    /**
     * @brief Function to get the listener object.
     * @return IClientListener : internet radio client listener.
     */
    IClientListener getClientListener() {

        return mClientListener;
    }


}
