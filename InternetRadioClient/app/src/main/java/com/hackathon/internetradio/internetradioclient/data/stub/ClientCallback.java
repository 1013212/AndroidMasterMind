/**
 * @file        ClientCallback.java
 * @brief       This class is a stub implementation for
 *              {@link com.hackathon.internetradio.internetradioclient.InternetRadioClient}. bind AIDL.
 * @copyright   COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *              ALL RIGHTS RESERVED
 * @author      Zubair KK
 */

package com.hackathon.internetradio.internetradioclient.data.stub;

import android.os.IBinder;
import android.os.RemoteException;

import com.hackathon.internetradio.internetradioclient.data.InternetRadioClientManager;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseContext;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseFilter;
import com.hackathon.internetradio.lib.internetradiointerface.IClientListener;
import com.hackathon.internetradio.lib.internetradiointerface.IClientCallback;
import com.hackathon.internetradio.lib.internetradiointerface.IClientInterface;

/**
 * @brief This class is a stub implementation for
 *       {@link com.hackathon.internetradio.internetradioclient.InternetRadioClient}.
 *       bind AIDL.
 */
public class ClientCallback extends IClientCallback.Stub {

    /**
     * Member variable for keeping the instance of InternetRadioClientManager.
     */
    private final InternetRadioClientManager mInternetRadioClientManager =
            InternetRadioClientManager.getInternetRadioClientManagerManager();



    /**
     * @brief Default constructor
     */
    public ClientCallback() {
        /**
         * Defaul constructor
         */
    }

    /**
     * @brief API to get sync connection object.
     * @return Iinternetradiointerface Provides an AIDL interface which can be used to execute a
     *         task in service.
     */
    @Override
    public IClientInterface getSyncConnection() throws RemoteException {

        return new IClientInterface() {
            @Override
            public boolean isClientSideReady() throws RemoteException {
                return false;
            }

            @Override
            public void play() throws RemoteException {

            }

            @Override
            public void pause() throws RemoteException {

            }

            @Override
            public boolean getCurrentPlayStatus() throws RemoteException {
                return false;
            }

            @Override
            public int getConnectionStatus(int sourceId) throws RemoteException {
                return 0;
            }

            @Override
            public void skip(int direction, int skipCount) throws RemoteException {

            }

            @Override
            public String getCurrentAlbumArt() throws RemoteException {
                return null;
            }

            @Override
            public TrackInfo getCurrentTrackInfo() throws RemoteException {
                return null;
            }

            @Override
            public int getErrorStatus() throws RemoteException {
                return 0;
            }

            @Override
            public void getCategoryListItems(BrowseFilter browseFilter) throws RemoteException {

            }

            @Override
            public void playUsingContext(BrowseContext browseContext) throws RemoteException {

            }

            @Override
            public IBinder asBinder() {
                return null;
            }
        };
    }

    /**
     * @brief API to register a async connection.
     * @param mediaListener : AIDL interface through which service communicate with HMI.
     */
    @Override
    public void registerAsyncConnection(IClientListener mediaListener) throws RemoteException {

        mInternetRadioClientManager.registerAsyncCallBack(mediaListener);
    }

    /**
     * @brief API to unregister a async connection.
     * @param mediaListener : AIDL interface through which service communicate with HMI.
     */
    @Override
    public void unRegisterAsyncConnection(IClientListener mediaListener) throws RemoteException {
        mInternetRadioClientManager.unRegisterAsyncCallBack(mediaListener);
    }
}
