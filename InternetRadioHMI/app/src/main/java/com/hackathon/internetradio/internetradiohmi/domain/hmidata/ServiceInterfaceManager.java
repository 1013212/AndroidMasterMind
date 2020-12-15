/**
 * @file        ServiceInterfaceManager.java
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Gravity;
import android.widget.Toast;


import com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio.HmiServiceInterface;
import com.hackathon.internetradio.internetradiohmi.utilities.HmiConstants;
import com.hackathon.internetradio.lib.internetradiointerface.IClientCallback;
import com.hackathon.internetradio.lib.internetradiointerface.IClientListener;


/**
 * @brief Implementation for ServiceInterfaceManager class.
 *          ServiceInterfaceManager contains the implementations required for interfacing the
 *          communication with service.
 */
public class ServiceInterfaceManager {

    /**
     * Variable to store object of ServiceInterfaceManager.
     */
    private static ServiceInterfaceManager sServiceInterfaceManager = null;

    /**
     * Mutex object to synchronize the singleton.
     */
    private static final Object MUTEX = new Object();

    /**
     * Variable to store object of IClientCallback.
     */
    private IClientCallback mClientCallback;

    /**
     * Variable to store object of IClientListener.
     */
    private IClientListener mClientListener = null;

    /**
     * Variable to store object of HmiServiceInterface.
     */
    private HmiServiceInterface mHmiServiceInterface = null;

    /**
     * Variable to store object of ServiceEventManager.
     */
    private ServiceEventManager mServiceEventManager = null;

    /**
     * Variable to store object of ServiceEventListener.
     */
    private ServiceEventListener mServiceEventListener = null;

    /**
     * Variable to store object of ServiceEventHandler.
     */
    private ServiceEventHandler mServiceEventHandler = null;

    /**
     * Application Context.
     */
    private Context mContext;

    /**
     * Variable to store the object of LogUtility.
     */

    /**
     * @brief Service connection object for Internet Client.
     */
    private ServiceConnection mHmiSyncConnection = new ServiceConnection() {
        /**
         * @brief Internet Radio Client connected callback for HMI.
         * @param name : name of the component.
         * @param service : service object.
         * @exception RemoteException : thrown when binder remote-invocation fails.
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mClientCallback = IClientCallback.Stub.asInterface(service);
            try {
                mClientListener = mServiceEventListener.getClientListener();
                mClientCallback.registerAsyncConnection(mClientListener);
                mHmiServiceInterface.initialize(mClientCallback.getSyncConnection());
                mHmiServiceInterface.playFromMediaId("start_playback");

            } catch (RemoteException e) {
            }
        }

        /**
         * @brief Service disconnected callback for Internet Radio Client.
         * @param name : name of the component.
         * @exception RemoteException : thrown when binder remote-invocation fails.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast toast = Toast.makeText(mContext, "message", Toast.LENGTH_SHORT);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(">>>>Disconnected from service");
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }
    };

    /**
     * Member variable for keeping the instance of BroadcastReceiver.
     */
    private BroadcastReceiver mEsmBroadcastReceiver;

    /**
     * @brief Constructor of ServiceInterfaceManager.
     */
    private ServiceInterfaceManager() {
        mHmiServiceInterface = new HmiServiceInterface();
        mServiceEventManager = new ServiceEventManager();
        mServiceEventHandler = new ServiceEventHandler(mServiceEventManager);
        mServiceEventListener = new ServiceEventListener(mServiceEventHandler);
    }

    /**
     * @brief  Method to get the singleton instance of service interface class.
     * @return ServiceInterfaceManager : Service interface manager singleton instance.
     */
    public static ServiceInterfaceManager getInstance() {

        ServiceInterfaceManager result = sServiceInterfaceManager;
        if (result == null) {
            synchronized (MUTEX) {
                result = sServiceInterfaceManager;
                if (result == null) {
                    sServiceInterfaceManager = result = new ServiceInterfaceManager();
                }
            }
        }
        return result;
    }

    /**
     * @brief  Method for getServiceEventManager.
     * @return ServiceEventManager : Service event manager instance.
     */
    public ServiceEventManager getServiceEventManager() {
        return mServiceEventManager;
    }

    /**
     * @brief  Method return the internet client interface.
     * @return HmiServiceInterface : hmi service interface.
     */
    public HmiServiceInterface getHmiServiceInterface() {
        return mHmiServiceInterface;
    }

    /**
     * @brief Method to bind internet radio client.
     * @param context : context object.
     */
    public void bindService(Context context) {
        mContext = context;
        if (mHmiServiceInterface != null) {
            mHmiServiceInterface.setContext(mContext);
        }
        bindToInternetRadioClient();
    }

    /**
     * @brief Method to unbind internet radio client.
     */
    public void unbindService() {
        if (mContext != null) {
            mContext.unbindService(mHmiSyncConnection);
        }
    }

    /**
     * @brief Method to bind internet radio client.
     */
    public void bindToInternetRadioClient() {
        if (mContext != null) {
            Intent intentToClient = new Intent(HmiConstants.TAG_INTERNET_RADIO_CLIENT_PACKAGE_NAME);
            intentToClient.setClassName(HmiConstants.TAG_INTERNET_RADIO_CLIENT_PACKAGE_NAME,
                    HmiConstants.TAG_INTERNET_RADIO_CLIENT_CLASS_NAME);
            try {
                //mContext.startForegroundService(intentToClient);
                mContext.bindService(intentToClient, mHmiSyncConnection, Context.BIND_AUTO_CREATE);
            } catch (SecurityException e) {
                mContext.startForegroundService(intentToClient);
            }
        }
    }

    /**
     * @brief  Method to get service handler.
     * @return ServiceEventHandler : Return service handler
     */
    public ServiceEventHandler getServiceHandler() {
        return mServiceEventHandler;
    }
}