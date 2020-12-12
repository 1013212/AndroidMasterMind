/**
 * @file        Subject.java
 * @brief       This file contains implementation of Subject

 *
 * @author      Praveen
 */

package com.hackathon.internetradio.internetradiohmi.utilities;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @brief  Implementation for Subject class.
 */
public class Subject {
    /**
     * Variable to store observers list
     */
    private CopyOnWriteArrayList<Observer> mObservers = new CopyOnWriteArrayList<>();
    /**
     * Variable to keep service event data
     */
    private Object mData;
    /**
     * Variable to keep service event
     */
    private int mEvent;

    /**
     * @brief Subject constructor
     * @param event : service event Id
     */
    public Subject(int event) {
        mEvent = event;
    }

    /**
     * @brief This method register the observers of service event.
     * @param observer : Observer object
     */
    public void register(Observer observer) {
        if (observer != null) {
            mObservers.add(observer);
        }
    }

    /**
     * @brief This method unregister the observers of service event.
     * @param observer : Observer object
     */
    public void unregister(Observer observer) {
        if (observer != null) {
            mObservers.remove(observer);
        }
    }

    /**
     * @brief This function returns the service event Id.
     * @return int : service event id
     */
    public int getEvent() {
        return mEvent;
    }

    /**
     * @brief This function return the service event data
     * @return  Object : Service event data
     */
    public Object getData() {
        return mData;
    }

    /**
     * @brief This function set the service event data
     * @param data : Service event data
     */
    public void setData(Object data) {
        mData = data;
    }

    /**
     * @brief This function notifies the service event to its registered observers
     * @param event : Service event Id
     * @param data : Service event data
     */
    public void notifyEvent(int event, Object data) {
        notifyObservers(event, data);
    }

    /**
     * @brief This function notifies the service event to its registered observers
     * @param event : Service event Id
     * @param data : Service event data
     */
    private void notifyObservers(int event, Object data) {
        for (Observer observer : mObservers) {
            observer.notifyEvent(event, data);
        }
    }
}
