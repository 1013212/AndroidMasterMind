/**
 * @file        SourceStateInfo.java
 * @brief       Class for storing source state details

 *
 * @author      Zubair KK
 */

package com.hackathon.internetradio.lib.internetradiointerface;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @brief Class for storing source state details.
 */
public class SourceStateInfo implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *       field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<SourceStateInfo> CREATOR = new Creator<SourceStateInfo>() {

        public SourceStateInfo createFromParcel(Parcel in) {
            return new SourceStateInfo(in);
        }

        public SourceStateInfo[] newArray(int size) {
            return new SourceStateInfo[size];
        }
    };

    /**
     * Integer variable to store source type
     */
    private int mSourceType;

    /**
     * Integer variable to store error status
     */
    private int mSourceStatus;

    /**
     * @brief Constructor for this class
     * @param sourceType : source type
     */
    public SourceStateInfo(int sourceType) {
        mSourceType = sourceType;
    }

    /**
     * @brief Constructor for parcel class
     * @param in : parcel object
     */
    public SourceStateInfo(Parcel in) {
        readFromParcel(in);
    }

    /**
     * @brief Describe the kinds of special objects contained in this Parcelable
     *       instance's marshaled representation. For example, if the object will
     *       include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     *       the return value of this method must include the
     *       @link #CONTENTS_FILE_DESCRIPTOR} bit.
     * @return a bitmask indicating the set of special object types marshaled
     *        by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @brief Flatten this object in to a Parcel.
     * @param dest :  The Parcel in which the object should be written.
     * @param flags : Additional flags about how the object should be written.
     *               May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeInt(mSourceType);
            dest.writeInt(mSourceStatus);
        }
    }

    /**
     * @brief Read from parcel class
     * @param in : parcel object
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mSourceType = in.readInt();
            mSourceStatus = in.readInt();
        }
    }

    /**
     * @brief Method to set source type
     * @return int : sourceType: source type
     */
    public int getSourceType() {
        return mSourceType;
    }

    /**
     * @brief Method to set source type
     * @param sourceType : source type
     */
    public void setSourceType(int sourceType) {
        mSourceType = sourceType;
    }

    /**
     * @brief Method to get error status
     * @return int : error Status
     */
    public int getSourceStatus() {
        return mSourceStatus;
    }

    /**
     * @brief Method to set error status
     * @param sourceStatus : error status
     */
    public void setSourceStatus(int sourceStatus) {
        mSourceStatus = sourceStatus;
    }
}