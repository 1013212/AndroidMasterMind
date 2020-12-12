/**
 * @file      ServiceEventListener.java
 * @brief     This file contains the internet radio client event listener implementation

 *
 * @author    Praveen
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata;



import android.os.Message;
import android.os.RemoteException;


import com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio.HmiServiceInterface;
import com.hackathon.internetradio.internetradiohmi.utilities.HmiConstants;
import com.hackathon.internetradio.lib.commoninterface.DeviceConnectionInfo;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
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
            if (isServiceReady()) {
                Message msg = mServiceEventHandler.obtainMessage(
                        HmiConstants.ServiceEvent.AIDL_NOTIFY_PLAY_STATUS, playStatus);
                mServiceEventHandler.removeMessages(HmiConstants.ServiceEvent.AIDL_NOTIFY_PLAY_STATUS);
                mServiceEventHandler.sendMessage(msg);
            }
        }

        @Override
        public void notifyTrackChange(TrackInfo trackInfo) throws RemoteException {

        }


        /**
         * @brief Service callback method to notify connection status.
         * @param deviceConnectInfo : device connect information.
         * @exception RemoteException : remote exception
         */
        @Override
        public void notifyConnectionStatus(DeviceConnectionInfo
                        deviceConnectInfo) throws RemoteException {
            if (isServiceReady()) {
                Message msg = mServiceEventHandler.obtainMessage(
                        HmiConstants.ServiceEvent.AIDL_NOTIFY_DEVICE_CONNECT, deviceConnectInfo);
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
        public void notifyCategoryListItems(BrowseList browseList) throws RemoteException {

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

    /**
     * @brief  Method return the internet radio client interface.
     * @return HmiServiceInterface : hmi client interface.
     */
    private HmiServiceInterface getHmiServiceInterface() {
        return ServiceInterfaceManager.getInstance().getHmiServiceInterface();
    }
}
