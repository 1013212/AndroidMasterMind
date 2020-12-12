/**
 * @file        IServiceConnection.java
 * @brief       Interface for bind (mtp/ipod)service connection callback methods.
 * @copyright   COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *              ALL RIGHTS RESERVED
 * @author      Praveen P G
 */

package com.hackathon.internetradio.internetradioclient.data.utils;

/**
 * @brief Interface for service init callback methods.
 */
public interface IServiceConnection {

    /**
     * @brief Callback method for service init success.
     */
    void onServiceInitialized(String serviceType);

    /**
     * @brief Callback method for service destroyed.
     */
    void onServiceDeinitialized(String serviceType);
}
