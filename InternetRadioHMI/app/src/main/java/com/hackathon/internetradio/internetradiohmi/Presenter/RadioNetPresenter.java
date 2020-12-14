package com.hackathon.internetradio.internetradiohmi.Presenter;

import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiPresenter;
import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiView;
import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetModel;
import com.hackathon.internetradio.internetradiohmi.Model.RadioNetModel;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;

public class RadioNetPresenter implements IRadioNetHmiPresenter {

    /**
     * variable to store object of IMediaBaseView
     */
    private IRadioNetHmiView mMediaBaseView;
    /**
     * variable to store object of IMediaBaseModel
     */
    private IRadioNetModel mMediaBaseModel;

    public RadioNetPresenter(IRadioNetHmiView mediaBaseView) {
        mMediaBaseView = mediaBaseView;
        mMediaBaseModel = new RadioNetModel(this);
    }

    @Override
    public int getConnectionStatus(int source) {
        return mMediaBaseModel.getConnectionStatus(source);
    }

    @Override
    public boolean getCurrentPlayStatus() {
        return mMediaBaseModel.getCurrentPlayStatus();
    }

    @Override
    public void play() {
        mMediaBaseModel.play();
    }

    @Override
    public void pause() {
        mMediaBaseModel.pause();
    }

    @Override
    public void start() {
        mMediaBaseModel.start();
    }

    @Override
    public void stop() {

        mMediaBaseModel.stop();
    }

    @Override
    public void skip(int direction, int skipCount) {
        mMediaBaseModel.skip(direction, skipCount);
    }

    @Override
    public String getCurrentAlbumArt() {
        return mMediaBaseModel.getCurrentAlbumArt();
    }

    @Override
    public TrackInfo getCurrentTrackInfo() {
        return mMediaBaseModel.getCurrentTrackInfo();
    }

    @Override
    public int getErrorStatus() {
        return mMediaBaseModel.getErrorStatus();
    }

    @Override
    public void connectToService() {

        mMediaBaseModel.connectToService();
    }

    @Override
    public void getStationListItems(String stationType) {

        mMediaBaseModel.getStationListItems(stationType);
    }

    @Override
    public void onNotifyStationListItems(BrowseList browseList) {

        mMediaBaseView.onNotifyStationListItems(browseList);
    }

    @Override
    public void onNotifyPlayStatus(int playStatus) {

        mMediaBaseView.onNotifyPlayStatus(playStatus);
    }

    @Override
    public void onNotifyTrackChange(TrackInfo trackInfo) {

        mMediaBaseView.onNotifyTrackChange(trackInfo);
    }

    @Override
    public void onNotifyConnectionStatus(boolean status) {
        mMediaBaseView.onNotifyConnectionStatus(status);

    }

    @Override
    public void onNotifyError(int errorType) {

        mMediaBaseView.onNotifyError(errorType);
    }

    @Override
    public void playFromMediaId(String mediaId) {
        mMediaBaseModel.playFromMediaId(mediaId);
    }
}
