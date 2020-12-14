/**
 * @file        ClientCallback.java
 * @brief       This class is a stub implementation for
 *              {@link com.hackathon.internetradio.internetradioclient.InternetRadioClient}. bind AIDL.


 * @author      PG
 */

package com.hackathon.internetradio.internetradioclient.data.stub;

import android.os.RemoteException;

import com.hackathon.internetradio.internetradioclient.data.InternetRadioClientManager;
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
            InternetRadioClientManager.getInternetRadioClientManager();

    /**
     * @brief Default constructor
     */
    public ClientCallback() {
    }

    /**
     * @brief API to get sync connection object.
     * @return IClientInterface Provides an AIDL interface which can be used to execute a
     *         task in service.
     */
    @Override
    public IClientInterface getSyncConnection() throws RemoteException {
        return new ClientInterface();
    }

    /**
     * @brief API to register a async connection.
     * @param clientListener : AIDL interface through which service communicate with HMI.
     */
    @Override
    public void registerAsyncConnection(IClientListener clientListener) throws RemoteException {

        mInternetRadioClientManager.registerAsyncCallBack(clientListener);
    }

    /**
     * @brief API to unregister a async connection.
     * @param clientListener : AIDL interface through which service communicate with HMI.
     */
    @Override
    public void unRegisterAsyncConnection(IClientListener clientListener) throws RemoteException {
        mInternetRadioClientManager.unRegisterAsyncCallBack(clientListener);
    }
}
