/**
 * @file        ISourceBase.java
 * @brief       Interface for passing source notification to InternetRadioClientManager.
 * @copyright   COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION
 *              ALL RIGHTS RESERVED
 * @author      Zubair KK
 */

package com.hackathon.internetradio.internetradioclient.data;

import android.content.Context;

import com.hackathon.internetradio.internetradioclient.data.utils.IServiceConnection;
import com.hackathon.internetradio.internetradioclient.data.utils.ISourceNotifications;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseContext;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseFilter;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;
import com.hackathon.internetradio.lib.internetradiointerface.SourceStateInfo;

/**
 * @brief Interface for passing source notification to InternetRadioClientManager.
 */
public interface ISourceBase {

    /**
     * @brief This function is called when a USB is attached
     * @param serialNumber : Serial number of device
     */
    default void deviceAttached(String serialNumber) {

    }

    /**
     * @brief This function is called when a device is detached
     * @param serialNumber : Serial number of device
     */
    default void deviceDetached(String serialNumber) {

    }

    /**
     * @brief API to play song from USB
     */
    default void play() {

    }

    /**
     * @brief Method to play for idevice and projection .
     */
    default void play(int pressStatus) {

    }

    /**
     * @brief API to pause current song
     */
    default void pause() {

    }

    /**
     * @brief Method to pause for idevice mirroring screen.
     */
    default void pause(int pressStatus) {

    }

    /**
     * @brief Method to nextCarPlay for device mirroring screen.
     */
    default void next(int pressStatus) {

    }

    /**
     * @brief Method to previousCarPlay for device mirroring screen.
     */
    default void previous(int pressStatus) {

    }

    /**
     * @brief API to skip  current song to next or previous.
     * @param direction : skip direction
     * @param skipCount : skip count
     */
    default void skip(int direction, int skipCount) {

    }

    /**
     * @brief Function to jump current position to required position.
     * @param seekTime : Required seek time
     */
    default void jumpToPosition(int seekTime) {

    }

    /**
     * @brief API to get current play status
     * @return current play status
     */
    default boolean getPlayStatus() {
        return false;
    }

    /**
     * @brief API to get current playback stop status
     * @return current playback stop status
     */
    default boolean getPlaybackStopStatus() {
        return false;
    }

    /**
     * @brief API to get bt browse list completion status
     * @return current bt browse list completion status
     */
    default boolean getBtBrowseListCompletionStatus() {
        return false;
    }

    /**
     * @brief API to get current source,
     *        like RADIO_FM, USB, BTA, AUX
     * @return current source ID
     */
    default int getCurrentSource() {
        return 0;
    }

    /**
     * @brief This function will be called when service destroys
     */
    default void destroy() {

    }

    /**
     * @brief This function will be called when services need to stop forcefully.
     */
    default void forceStopService() {

    }

    /**
     * @brief Method to show function not available during emergency call popup.
     * @param moduleType : moduleType.
     */
    default void showEmergencyCallActivePopup(int moduleType) {

    }

    /**
     * @brief Class initialisation logic will be handled here
     * @param context : Context of application
     *        {@link InternetRadioClientManager}
     *               class
     * @param serviceConnection : IServiceConnection
     */
    default void init(Context context, IServiceConnection serviceConnection) {

    }

    /**
     * @brief function to start a source. This can be used while source switching.
     * @param sourceNotifications : interface to notify HMI about service updates.
     */
    default void start(ISourceNotifications sourceNotifications) {

    }

    /**
     * @brief This function will be called for stopping service
     */
    default void stop() {

    }

    /**
     * @brief API to get availability status,
     *        like RADIO_FM, USB, BTA, , AUX
     * @return int : availability status
     */
    default int getAvailabilityStatus() {
        return 0;
    }

    /**
     * @brief API to get the device connection status
     *        like RADIO_FM, USB, BTA, , AUX
     * @return int : current connection status
     */
    default int getConnectionStatus(Context context) {
        return Constants.ConnectionStatus.NOT_SUPPORTED;
    }

    /**
     * @brief Function to get album art path
     * @return String : Album art path
     */
    default String getCurrentAlbumArt() {
        return null;
    }

    /**
     * @brief API to get current metadata
     * @return TrackInfo : current track info
     */
    default  TrackInfo getCurrentTrackInfo() {
        return null;
    }

    /**
     * @brief API to get current source info
     * @return SourceStateInfo : source state info
     */
    default SourceStateInfo getSourceStateInfo() {
        return null;
    }



    /**
     * @brief Method to get emergency call status.
     */
    default int getEmergencyCallStatus() {
        return 0;
    }

    /**
     * @brief Method to get emergency call instant status.
     */
    default int getEmergencyCallInstantStatus() {
        return 0;
    }

    /**
     * @brief Function to get current error status
     * @return int : Current current error status
     */
    default int getErrorStatus() {
        return 0;
    }

    /**
     * @brief Function to get shuffle status
     * @return int : shuffle status
     */
    default int getShuffleStatus() {
        return 0;
    }

    /**
     * @brief Function to set shuffle status
     * @param shuffleStatus : Shuffle status
     */
    default void setShuffleStatus(int shuffleStatus) {
    }

    /**
     * @brief Method to changeShuffleCarPlay for device mirroring screen.
     */
    default void setShuffleStatus(int shuffleStatus, int pressStatus){

    }

    /**
     * @brief Function to get repeat status
     * @return int : Repeat status
     */
    default int getRepeatStatus() {
        return 0;
    }

    /**
     * @brief Function to get deviceName.
     * @return String : device name.
     */
    default String getDeviceName() {
        return null;
    }

    /**
     * @brief Function to set repeat status
     * @param repeatStatus : Repeat status
     */
    default void setRepeatStatus(int repeatStatus) {

    }

    /**
     * @brief Method to changeRepeatCarPlay for device mirroring screen.
     */
    default void setRepeatStatus(int repeatStatus, int pressStatus) {

    }


    /**
     * @brief This function is to get port id of usb type sources
     * @return char array : usb port id
     */
    default String getUsbPortId() {
        return Constants.EMPTY_STRING;
    }



    /**
     * @brief Method which is used to update the elapsed time to DB
     */
    default float updateElapsedTimeToDb() {
        return Constants.INVALID_VALUE;
    }

    /**
     * @brief Method which is used to notify A2dconnecton status
     * @param port : A2dpconnected USB port
     * @param status : A2dpconnection status
     */
    default void notifyA2dpConnectionStatus(String port, int status) {
    }

    /**
     * @brief This function will be called for resuming service
     */
    default void resume() {

    }

    /**
     * @brief Method to get the device type
     * @return int : device type
     */
    default int getDeviceType() {
        return Constants.DeviceType.NONE;
    }

    interface ISourceBrowse {

        /**
         * @brief Function to start or stop search sequence
         * @param categoryType : category Type
         * @param searchStatus : search status
         * @param searchData : search data
         */
        default void startOrStopSearch(int categoryType, int searchStatus, String searchData) {
        }

        /**
         * @brief Function to get browse list size
         * @param browseContext : Object containing detail of selected browse item
         * @return int : Size of the browse list
         */
        int getCategoryListSize(BrowseContext browseContext);

        /**
         * @brief Function to get folder list size
         * @param browseContext : Object containing detail of selected browse item
         * @return int : Size of folder list
         */
        int getFolderListSize(BrowseContext browseContext);

        /**
         * @brief Function to get current track list size
         * @return int : Size of current track list
         */
        int getCurrentTrackListSize();

        /**
         * @brief Function is to get category list item
         * @param browseFilter : Filter for browse list
         */
        void getCategoryListItems(BrowseFilter browseFilter);

        /**
         * @brief Function to get folder list
         * @param browseFilter : Filter for browse list
         */
        void getFolderItems(BrowseFilter browseFilter);

        /**
         * @brief This function is to get current track list
         * @param startIndex : start index of  current track list
         * @param count : count of current track list
         */
        void getCurrentTrackListItems(int startIndex, int count);

        /**
         * @brief Function is to play browse context song
         * @param itemContext : Object containing detail of selected browse item
         */
        void playUsingContext(BrowseContext itemContext);

        /**
         * @brief This function is to get search items
         * @param searchString : keyword for search
         * @param categoryType : category type value
         * @return int : Number elements in search result list
         */
        int getSearchItemSize(String searchString, int categoryType);

        /**
         * @brief This function is to get search character candidates
         * @return char array : character array of search character candidates
         */
        String getSearchCharacterCandidates();

    }
}