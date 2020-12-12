package com.hackathon.internetradio.internetradiohmi.utilities;

/**
 * @brief  Implementation for Observer class.
 */
public abstract class Observer {
    /**
     * Abstract method to notify event to observers
     */
    public abstract void notifyEvent(int event, Object data);
}

