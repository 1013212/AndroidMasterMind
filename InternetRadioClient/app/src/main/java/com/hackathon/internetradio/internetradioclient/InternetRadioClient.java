/**
 * @file        InternetRadioClient.java
 * @brief       Media Service Base class. HMI apps can bind to
 *              this class with AIDL interfaces and make communication.


 * @author      Zubair KK
 */

package com.hackathon.internetradio.internetradioclient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.hackathon.internetradio.internetradioclient.data.InternetRadioClientManager;
import com.hackathon.internetradio.internetradioclient.data.stub.ClientCallback;
import com.hackathon.internetradio.internetradioclient.internetradiobrowserclient.InternetRadioServiceClient;

/**
 * @brief Media Service Base cl`ass. HMI apps can bind to
 *        this class with AIDL interfaces and make communication.
 */
public class InternetRadioClient extends Service {

    private final InternetRadioClientManager mInternetRadioClientManager =
            InternetRadioClientManager.getInternetRadioClientManager();

    /**
     * @brief Default constructor
     */
    public InternetRadioClient() {
    }

    /**
     * @brief Called by the system when the service is first created.
     *        Do not call this method directly. Logger Utility instance is initialized in this
     *        method
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mInternetRadioClientManager.init();
        InternetRadioServiceClient.getInstance().
                connectToInternetRadioMediaBrowserService(getApplication().getApplicationContext());
    }

    /**
     * @brief This function is called when service starts.
     * @param intent : The Intent supplied to startService(Intent)
     * @param flags : Additional data about this start request
     * @param startId : A unique integer representing this specific request to start
     * @return int : The return value indicates what semantics the system should use for the
     *         service's
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Service is restarted if it gets terminated.
        return START_NOT_STICKY;
    }

    /**
     * @brief onBind will bind the server with client and returns the binder object
     * @param intent : The Intent supplied to obBind
     * @return IBinder : IBinder object with AIDL object.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new ClientCallback();
    }

    /**
     * @brief Method Called by the system when the service is destroyed.
     *        Logger Utility instance is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
