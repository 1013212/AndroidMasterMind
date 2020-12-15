/**
 * @file        BrowseFilter.java
 */

package com.hackathon.internetradio.lib.commoninterface.browse;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @brief Class to hold browse filter details
 */
public class BrowseFilter implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *        field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<BrowseFilter> CREATOR = new Creator<BrowseFilter>() {
        @Override
        public BrowseFilter createFromParcel(Parcel in) {
            return new BrowseFilter(in);
        }

        @Override
        public BrowseFilter[] newArray(int size) {
            return new BrowseFilter[size];
        }
    };

    /**
     * Variable to store browse context.
     */
    private BrowseContext mBrowseContext;

    /**
     * Variable to store start index.
     */
    private int mStartIndex;

    /**
     * Variable to store item count.
     */
    private int mItemCount;

    /**
     * @brief Constructor for this class
     * @param browseContext : Value of browse context
     * @param startIndex : Value of start index
     * @param itemCount : Value of item count
     */
    public BrowseFilter(BrowseContext browseContext, int startIndex, int itemCount) {
        this.mBrowseContext = browseContext;
        this.mStartIndex = startIndex;
        this.mItemCount = itemCount;
    }

    /**
     * @brief Constructor for parcel class
     * @param in : Object of parcel class
     */
    public BrowseFilter(Parcel in) {
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
        dest.writeParcelable(mBrowseContext, flags);
        dest.writeInt(mStartIndex);
        dest.writeInt(mItemCount);
    }

    /**
     * @brief Method for read from parcel class.
     * @param in : Object of parcel class.
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mBrowseContext = in.readParcelable(BrowseContext.class.getClassLoader());
            mStartIndex = in.readInt();
            mItemCount = in.readInt();
        }
    }

    /**
     * @brief Method to get browse context
     * @return BrowseContext : Object of BrowseContext class
     */
    public BrowseContext getBrowseContext() {
        return mBrowseContext;
    }

    /**
     * @brief Method to set browse context
     * @param browseContext : Object of BrowseContext class
     */
    public void setBrowseContext(BrowseContext browseContext) {
        this.mBrowseContext = browseContext;
    }

    /**
     * @brief Method to get start index
     * @return int : Value of start index
     */
    public int getStartIndex() {
        return mStartIndex;
    }

    /**
     * @brief Method to set start index
     * @param startIndex : Value of start index
     */
    public void setStartIndex(int startIndex) {
        this.mStartIndex = startIndex;
    }

    /**
     * @brief Method to get item count
     * @return int : Value of item count
     */
    public int getItemCount() {
        return mItemCount;
    }

    /**
     * @brief Method to set item count
     * @param itemCount : Value of item count
     */
    public void setItemCount(int itemCount) {
        this.mItemCount = itemCount;
    }
}
