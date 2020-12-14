package com.hackathon.internetradio.internetradiohmi.Model;

import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiPresenter;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceEventManager;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio.HmiServiceInterface;
import com.hackathon.internetradio.internetradiohmi.utilities.HmiConstants;
import com.hackathon.internetradio.internetradiohmi.utilities.Observer;
import com.hackathon.internetradio.internetradiohmi.utilities.Subject;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

import java.util.ArrayList;

public class RadioNetModel extends Observer implements com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetModel {

    /**
     * Variable to store object of IMediaBasePresenter class
     */
    private IRadioNetHmiPresenter mMediaBasePresenter;
    /**
     * Array for storing events
     */
    private ArrayList<Integer> mEventList = new ArrayList<>();

    public RadioNetModel(IRadioNetHmiPresenter mediaBasePresenter){
        mMediaBasePresenter = mediaBasePresenter;
        observeEvent(HmiConstants.ServiceEvent.AIDL_NOTIFY_PLAY_STATUS);
        observeEvent(HmiConstants.ServiceEvent.AIDL_NOTIFY_DEVICE_CONNECT);
        observeEvent(HmiConstants.ServiceEvent.AIDL_NOTIFY_FILE_ERROR);
        observeEvent(HmiConstants.ServiceEvent.AIDL_NOTIFY_TRACK_CHANGE);
        observeEvent(HmiConstants.ServiceEvent.AIDL_NOTIFY_CATEGORY_LIST_ITEMS);

    }

    /**
     * @brief Observe event method
     * @param event : service event
     */
    public final void observeEvent(int event) {
        mEventList.add(event);
    }

    @Override
    public int getConnectionStatus(int source) {
        return getHmiServiceInterface().getConnectionStatus(source);
    }

    @Override
    public boolean getCurrentPlayStatus() {
        return getHmiServiceInterface().getCurrentPlayStatus();
    }

    @Override
    public void play() {
        getHmiServiceInterface().play();
    }

    @Override
    public void pause() {
        getHmiServiceInterface().pause();
    }

    @Override
    public void skip(int direction, int skipCount) {
        getHmiServiceInterface().skip(direction, skipCount);
    }

    @Override
    public String getCurrentAlbumArt() {
        return  getHmiServiceInterface().getCurrentAlbumArt();
    }

    @Override
    public TrackInfo getCurrentTrackInfo() {
        return getHmiServiceInterface().getCurrentTrackInfo();
    }

    @Override
    public int getErrorStatus() {
        return getHmiServiceInterface().getErrorStatus();
    }

    @Override
    public void connectToService() {
        getHmiServiceInterface().connectToService();
    }

    @Override
    public void getStationListItems(String stationType) {
        getHmiServiceInterface().getStationListItems(stationType);
    }

    @Override
    public void start() {
        if (mEventList != null) {
            for (Integer event : mEventList) {
                Subject observable = getEventObservable(event);
                if (observable != null) {
                    observable.register(this);
                }
            }
        }

    }

    @Override
    public void stop() {
        if (mEventList != null) {
            for (Integer event : mEventList) {
                Subject observable = getEventObservable(event);
                if (observable != null) {
                    observable.unregister(this);
                }
            }
        }
    }

    @Override
    public Object getEventData(int event) {
        return getServiceEventManager().getEventData(event);
    }

    @Override
    public void setEventData(int event, Object data) {
        getServiceEventManager().setEventData(event, data);

    }

    @Override
    public Subject getEventObservable(int event) {
        return getServiceEventManager().getObservable(event);
    }

    @Override
    public ServiceInterfaceManager getServiceInterfaceManager() {
        return ServiceInterfaceManager.getInstance();
    }

    @Override
    public ServiceEventManager getServiceEventManager() {
        return ServiceInterfaceManager.getInstance().getServiceEventManager();
    }

    @Override
    public HmiServiceInterface getHmiServiceInterface() {
        return ServiceInterfaceManager.getInstance().getHmiServiceInterface();
    }


    @Override
    public void notifyEvent(int event, Object data) {

        switch (event) {
            case HmiConstants.ServiceEvent.AIDL_NOTIFY_PLAY_STATUS:
                mMediaBasePresenter.onNotifyPlayStatus((int) data);
                break;

            case HmiConstants.ServiceEvent.AIDL_NOTIFY_DEVICE_CONNECT:
                mMediaBasePresenter.onNotifyConnectionStatus((boolean) data);
                break;

            case HmiConstants.ServiceEvent.AIDL_NOTIFY_FILE_ERROR:
                mMediaBasePresenter.onNotifyError((int) data);
                break;

            case HmiConstants.ServiceEvent.AIDL_NOTIFY_TRACK_CHANGE:
                mMediaBasePresenter.onNotifyTrackChange((TrackInfo) data);
                break;

            case HmiConstants.ServiceEvent.AIDL_NOTIFY_CATEGORY_LIST_ITEMS:
                mMediaBasePresenter.onNotifyStationListItems((BrowseList) data);
                break;
            default:
                break;

        }
    }

    @Override
    public void playFromMediaId(String mediaId) {
        getHmiServiceInterface().playFromMediaId(mediaId);
    }
}
