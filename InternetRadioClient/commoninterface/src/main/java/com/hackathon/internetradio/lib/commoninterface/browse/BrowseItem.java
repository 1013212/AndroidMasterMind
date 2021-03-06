/**
 * @file        BrowseItem.java
 */

package com.hackathon.internetradio.lib.commoninterface.browse;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @brief This class represents browse items
 */
public class BrowseItem implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *        field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<BrowseItem> CREATOR = new Creator<BrowseItem>() {
        @Override
        public BrowseItem createFromParcel(Parcel in) {
            return new BrowseItem(in);
        }

        @Override
        public BrowseItem[] newArray(int size) {
            return new BrowseItem[size];
        }
    };

    /**
     * Variable to store browse item id.
     */
    private String mId;

    /**
     * Variable to store browse item name.
     */
    private String mTitle;

    /**
     * @brief Constructor for this class
     * @param id : Value of item id
     * @param title : Value of item name
     */
    public BrowseItem(String id, String title) {
        mId = id;
        mTitle = title;
    }

    /**
     * @brief Constructor for parcel class
     * @param in : Object of parcel class
     */
    public BrowseItem(Parcel in) {
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
        dest.writeString(mId);
        dest.writeString(mTitle);
    }

    /**
     * @brief Method for read from parcel class.
     * @param in : Object of parcel class
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mId = in.readString();
            mTitle = in.readString();
        }
    }

    /**
     * @brief Method to get item id
     * @return String : Value of item id
     */
    public String getId() {
        return mId;
    }

    /**
     * @brief Method to set item id
     * @param id : Value of item id
     */
    public void setId(String id) {
        mId = id;
    }

    /**
     * @brief Method to get item name
     * @return String : Value of item name
     */
    public String getItemName() {
        return mTitle;
    }

    /**
     * @brief Method to set item name
     * @param title : Value of item name
     */
    public void setItemName(String title) {
        mTitle = title;
    }

    /**
     * @brief Method to return BrowseItem class values as string
     * @return String : BrowseItem values
     */
    @Override
    public String toString() {
        return "BrowseItem{"
                + "mId=" + mId
                + ", mTitle='" + mTitle + '\''
                + '}';
    }
}
