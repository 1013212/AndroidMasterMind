/**
 * @file        IServiceConnection.java
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
