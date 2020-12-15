/**
 * @file        ServiceEventHandler.java
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata;

import android.os.Handler;
import android.os.Message;


import java.lang.ref.WeakReference;

/**
 * @brief   Implementation for ServiceEventHandler class.
 *          ServiceEventHandler contains the implementations for event handlers to communicate
 *          with Service.
 */
public class ServiceEventHandler extends Handler {

    /**
     * Variable to store week reference of ServiceEventManager object
     */
    private WeakReference<ServiceEventManager> mServiceEventManager;

    /**
     * @brief Constructor of class ServiceEventHandler
     * @param serviceEventManager : object of ServiceEventManager
     */
    ServiceEventHandler(ServiceEventManager serviceEventManager) {
        mServiceEventManager = new WeakReference<>(serviceEventManager);
    }

    /**
     * @brief Message handler method
     * @param msg : object of Message
     */
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        ServiceEventManager instance = mServiceEventManager.get();
        if (instance != null) {
            instance.notifyEvent(msg.what, msg.obj);
        } else {
        }
    }
}
