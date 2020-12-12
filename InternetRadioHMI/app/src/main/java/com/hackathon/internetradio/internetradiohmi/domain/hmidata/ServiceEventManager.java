/**
 * @file        ServiceEventManager.java
 * @brief       This class keep the data received from Internet Radio Client and provides the observables
 *              to the listeners of the service data.
 * @author      Praveen
 */

package com.hackathon.internetradio.internetradiohmi.domain.hmidata;

import android.util.SparseArray;

import com.hackathon.internetradio.internetradiohmi.utilities.Subject;

/**
 * @brief   Implementation for ServiceEventManager class.
 *          ServiceEventManager contains the implementations for managing the communication
 *          with Service.
 */
public class ServiceEventManager {

    /**
     * Array list to store Subject objects
     */
    private SparseArray<Subject> mSubjectList;

    /**
     * @brief ServiceEventManager constructor
     */
    ServiceEventManager() {
        mSubjectList = new SparseArray();
    }

    /**
     * @brief Function to set and notify the event and its data to active observers
     * @param event : service event
     * @param data : service event data
     */

    public void notifyEvent(int event, Object data) {
        Subject subject = getObservable(event);
        subject.setData(data);
        subject.notifyEvent(event, data);
    }

    /**
     * @brief Function to get the last notified event data
     * @param event : service event
     * @return Object : service event data
     */
    public Object getEventData(int event) {
        return getObservable(event).getData();
    }

    /**
     * @brief Function to set the last notified event data
     * @param event : service event
     * @param data : service event data
     */
    public void setEventData(int event, Object data) {
        getObservable(event).setData(data);
    }

    /**
     * @brief Function to get the observable  of specified event
     * @param event : service event
     * @return Subject : event observable
     */
    public Subject getObservable(int event) {
        Subject subject = mSubjectList.get(event, null);
        if (subject == null) {
            subject = new Subject(event);
            mSubjectList.put(event, subject);
        }
        return subject;
    }
}
