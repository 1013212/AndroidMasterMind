/**
 * @file        DeviceConnectionInfo.java
 * @brief       Class for device connection info

 *
 * @author      Austin Renitez C
 */

package com.hackathon.internetradio.lib.commoninterface;

import android.os.Parcel;
import android.os.Parcelable;

import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

/**
 * @brief Class for holding  device connection info.
 */
public class DeviceConnectionInfo implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *        field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<DeviceConnectionInfo> CREATOR = new Creator<DeviceConnectionInfo>() {
        @Override
        public DeviceConnectionInfo createFromParcel(Parcel in) {
            return new DeviceConnectionInfo(in);
        }

        @Override
        public DeviceConnectionInfo[] newArray(int size) {
            return new DeviceConnectionInfo[size];
        }
    };

    /**
     * Variable to store source type.
     */
    private int mSourceType;

    /**
     * Variable to store service type.
     */
    private String mServiceType;

    /**
     * Variable to store connection status.
     */
    private int mConnectionStatus;

    /**
     * Variable to store device type.
     */
    private int mDeviceType;

    /**
     * Variable to store device name.
     */
    private String mDeviceName;

    /**
     * Variable to store device name.
     */
    private String mSerialNumber;

    /**
     * Variable to store port number.
     */
    private String mPortId;

    /**
     * @brief Constructor for this class.
     * @param sourceType : type of connected source
     * @param connectionStatus : status of connection
     * @param deviceType : type of connected device
     */
    public DeviceConnectionInfo(int sourceType, int connectionStatus, int deviceType) {
        mSourceType = sourceType;
        mConnectionStatus = connectionStatus;
        mDeviceType = deviceType;
        mDeviceName = Constants.DEFAULT_DEVICE_NAME;
        mSerialNumber = Constants.DEFAULT_SERIAL_NUMBER;
        mPortId = Constants.EMPTY_STRING;
        mServiceType = "";
    }

    /**
     * @brief Constructor for this class.
     * @param sourceType : type of connected source
     */
    public DeviceConnectionInfo(int sourceType) {
        mSourceType = sourceType;
        mConnectionStatus = Constants.ConnectionStatus.DISCONNECTED;
        mDeviceType = Constants.DeviceType.NONE;
        mDeviceName = Constants.DEFAULT_DEVICE_NAME;
        mSerialNumber = Constants.DEFAULT_SERIAL_NUMBER;
        mPortId = Constants.EMPTY_STRING;
        mServiceType = "";
    }

    /**
     * @brief Constructor for parcel class
     * @param in : parcel object
     */
    public DeviceConnectionInfo(Parcel in) {
        readFromParcel(in);
    }

    /**
     * @brief Describe the kinds of special objects contained in this Parcelable
     *         instance's marshaled representation.
     * @return int : A bitmask indicating the set of special object types marshaled
     *         by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @brief Method to flatten this object in to a Parcel.
     * @param dest : The Parcel in which the object should be written.
     * @param flags : Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSourceType);
        dest.writeInt(mConnectionStatus);
        dest.writeInt(mDeviceType);
        dest.writeString(mDeviceName);
        dest.writeString(mSerialNumber);
        dest.writeString(mPortId);
        dest.writeString(mServiceType);
    }

    /**
     * @brief Method for read from parcel class.
     * @param in : parcel object.
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mSourceType = in.readInt();
            mConnectionStatus = in.readInt();
            mDeviceType = in.readInt();
            mDeviceName = in.readString();
            mSerialNumber = in.readString();
            mPortId = in.readString();
            mServiceType = in.readString();
        }
    }

    /**
     * @brief Method to get source type
     * @return int : type of source
     */
    public int getSourceType() {
        return mSourceType;
    }

    /**
     * @brief Method to set source type
     * @param sourceType : type of source
     */
    public void setSourceType(int sourceType) {
        mSourceType = sourceType;
    }

    /**
     * @brief Method to get connection status
     * @return int : status of connection
     */
    public int getConnectionStatus() {
        return mConnectionStatus;
    }

    /**
     * @brief Method to set connection status
     * @param connectionStatus : connection status
     */
    public void setConnectionStatus(int connectionStatus) {
        mConnectionStatus = connectionStatus;
    }

    /**
     * @brief Method to get device type
     * @return int : type of device
     */
    public int getDeviceType() {
        return mDeviceType;
    }

    /**
     * @brief Method to set deviceType
     * @param deviceType : source id
     */
    public void setDeviceType(int deviceType) {
        mDeviceType = deviceType;
    }

    /**
     * @brief Method to get device name
     * @return String : name of device
     */
    public String getDeviceName() {
        return mDeviceName;
    }

    /**
     * @brief Method to set device name
     * @param deviceName : device name
     */
    public void setDeviceName(String deviceName) {
        mDeviceName = deviceName;
    }

    /**
     * @brief Method to get device serial number
     * @return String : serial number of device
     */
    public String getSerialNumber() {
        return mSerialNumber;
    }

    /**
     * @brief Method to set device serial number
     * @param serialNumber : device serial number
     */
    public void setSerialNumber(String serialNumber) {
        mSerialNumber = serialNumber;
    }

    /**
     * @brief Method to get device port number
     * @return String : port number
     */
    public String getPortId() {
        return mPortId;
    }

    /**
     * @brief Method to set device port number
     * @param portNumber : port number
     */
    public void setPortId(String portNumber) {
        mPortId = portNumber;
    }

    /**
     * @brief Method to get device service type
     * @return String : service type
     */
    public String getServiceType() {
        return mServiceType;
    }

    /**
     * @brief Method to set service type
     * @param serviceType : service type
     */
    public void setServiceType(String serviceType) {
        mServiceType = serviceType;
    }

    /**
     * @brief Method to return DeviceConnectionInfo valves as string
     * @return String : DeviceConnectionInfo values
     */
    @Override
    public String toString() {
        return "DeviceConnectionInfo{"
                + ", mSourceType=" + mSourceType
                + ", mConnectionStatus=" + mConnectionStatus
                + "mDeviceType=" + mDeviceType
                + "mDeviceName=" + mDeviceName
                + "mSerialNumber=" + mSerialNumber
                + "mPortId=" + mPortId
                + "mServiceType=" + mServiceType
                + '}';
    }
}
