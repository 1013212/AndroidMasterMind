/**
 * @file        TrackInfo.java
 * @brief       Class for now play song track details

 *
 * @author      Resmi TM
 */

package com.hackathon.internetradio.lib.commoninterface;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @brief Class for holding now play song track details.
 */
public class TrackInfo implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *       field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<TrackInfo> CREATOR = new Creator<TrackInfo>() {

        public TrackInfo createFromParcel(Parcel in) {
            return new TrackInfo(in);
        }

        public TrackInfo[] newArray(int size) {
            return new TrackInfo[size];
        }
    };

    /**
     * Variable to store object of MetaData.
     */
    private MetaData mMetaData;

    /**
     * Integer variable to store current track number.
     */
    private int mCurrentTrack;

    /**
     * Integer variable to store total track count.
     */
    private int mTotalTrack;

    /**
     *  Variable to check progressbar visibility status.
     */
    private boolean mIsProgressbarVisible;

    /**
     * @brief Constructor for this class
     * @param metaData : metadata
     * @param currentTrack :  current track number
     * @param totalTrack :    total track number
     */
    public TrackInfo(MetaData metaData, int currentTrack, int totalTrack) {
        mMetaData = metaData;
        mCurrentTrack = currentTrack;
        mTotalTrack = totalTrack;
        mIsProgressbarVisible = true;
    }

    /**
     * @brief Constructor for this class
     * @param metaData : metadata
     * @param currentTrack :  current track number
     * @param totalTrack :    total track number
     * @param status : progressbar visibility status.
     */
    public TrackInfo(MetaData metaData, int currentTrack, int totalTrack, boolean status) {
        mMetaData = metaData;
        mCurrentTrack = currentTrack;
        mTotalTrack = totalTrack;
        mIsProgressbarVisible = status;
    }

    /**
     * @brief Constructor for parcel class
     * @param in : parcel object
     */
    public TrackInfo(Parcel in) {
        readFromParcel(in);
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
            dest.writeParcelable(mMetaData, flags);
            dest.writeInt(mCurrentTrack);
            dest.writeInt(mTotalTrack);
            dest.writeInt(mIsProgressbarVisible ? 1 : 0);
        }
    }

    /**
     * @brief Read from parcel class
     * @param in : parcel object
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mMetaData = in.readParcelable(MetaData.class.getClassLoader());
            mCurrentTrack = in.readInt();
            mTotalTrack = in.readInt();
            mIsProgressbarVisible = (in.readInt() == 0 ? false : true);
        }
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
     * @brief Method to get metadata.
     * @return MetaData : metadata
     */
    public MetaData getMetaData() {
        return mMetaData;
    }

    /**
     * @brief Method to set metadata.
     * @param metaData : metadata
     */
    public void setMetaData(MetaData metaData) {
        mMetaData = metaData;
    }

    /**
     * @brief Method to get current track number.
     * @return int : current track number
     */
    public int getCurrentTrack() {
        return mCurrentTrack;
    }

    /**
     * @brief Method to set current track number.
     * @param currentTrack : current track number
     */
    public void setCurrentTrack(int currentTrack) {
        mCurrentTrack = currentTrack;
    }

    /**
     * @brief Method to get total track number.
     * @return int : total track number
     */
    public int getTotalTrack() {
        return mTotalTrack;
    }

    /**
     * @brief Method to set current track number.
     * @param totalTrack : total track number
     */
    public void setTotalTrack(int totalTrack) {
        mTotalTrack = totalTrack;
    }

    /**
     * @brief Method to set ProgressbarVisibilityStatus.
     * @param status : progressbar visibility status
     */
    public void setIsProgressbarVisible(boolean status) {
        mIsProgressbarVisible = status;
    }

    /**
     * @brief Method to get ProgressbarVisibilityStatus
     * @return boolean : progressbar visibility status
     */
    public boolean getIsProgressbarVisible() {
        return mIsProgressbarVisible;
    }
}

