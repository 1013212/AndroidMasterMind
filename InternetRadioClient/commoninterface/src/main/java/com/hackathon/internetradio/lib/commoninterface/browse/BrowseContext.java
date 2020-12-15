/**
 * @file        BrowseContext.java
 */

package com.hackathon.internetradio.lib.commoninterface.browse;

import android.os.Parcel;
import android.os.Parcelable;

import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

/**
 * @brief Class for holding browse context
 */
public class BrowseContext implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *       field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<BrowseContext> CREATOR = new Creator<BrowseContext>() {
        @Override
        public BrowseContext createFromParcel(Parcel in) {
            return new BrowseContext(in);
        }

        @Override
        public BrowseContext[] newArray(int size) {
            return new BrowseContext[size];
        }
    };

    /**
     * Variable to store category type.
     */
    private int mCategoryType;

    /**
     * Variable to store song id.
     */
    private String mItemId;

    /**
     * Variable to store browse list type.
     */
    private int mListType;

    /**
     * Variable to store is browse or currently playing context.
     */
    private boolean mIsTrackList;


    /**
     * Variable to store browsing type.
     */
    private int mBrowsingType;

    /**
     * @brief Default constructor for this class
     */
    public BrowseContext() {
        mCategoryType = 0;
        mItemId = "";
        mListType = 0;
        mBrowsingType = 0;
    }

    /**
     * @brief Constructor for this class
     * @param categoryType : browse category type
     * @param songId : browse song id
     * @param listType : Value of list type
     */
    public BrowseContext(int categoryType, String songId, int listType) {
        mCategoryType = categoryType;
        mItemId = songId;
        mListType = listType;
        mIsTrackList = Constants.CONST_FALSE;
        mBrowsingType = 0;
    }

    /**
     * @brief Constructor for parcel class
     * @param in : parcel object
     */
    public BrowseContext(Parcel in) {
        readFromParcel(in);
    }

    /**
     * @brief Method to flatten this object in to a Parcel.
     * @param dest : The Parcel in which the object should be written.
     * @param flags : Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeInt(mCategoryType);
            dest.writeString(mItemId);
            dest.writeInt(mListType);
            dest.writeByte((byte) (mIsTrackList ? 1 : 0));
            dest.writeInt(mBrowsingType);
        }
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
     * @brief Method for read from parcel class.
     * @param in : parcel object.
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mCategoryType = in.readInt();
            mItemId = in.readString();
            mListType = in.readInt();
            mIsTrackList = in.readByte() != 0;
            mBrowsingType = in.readInt();
        }
    }

    /**
     * @brief Method to get category type
     * @return int : value of category type
     */
    public int getCategoryType() {
        return mCategoryType;
    }

    /**
     * @brief Method to set category type
     * @param categoryType : parent category type
     */
    public void setCategoryType(int categoryType) {
        mCategoryType = categoryType;
    }

    /**
     * @brief Method to get item id
     * @return String : item id
     */
    public String getItemId() {
        return mItemId;
    }

    /**
     * @brief Method to set item id
     * @param itemId : item id
     */
    public void setItemId(String itemId) {
        mItemId = itemId;
    }

    /** @brief Method to get list type
     * @return int : Value of list type
     */
    public int getListType() {
        return mListType;
    }

    /**
     * @brief Method to set list type
     * @param listType : Value of list type
     */
    public void setListType(int listType) {
        mListType = listType;
    }

    /**
     * @brief Method to set list type
     * @return mIsTrackList :  Value of is Track list or not
     */
    public boolean getIsTrackList() {
        return mIsTrackList;
    }

    /**
     * @brief Method to set list type
     * @param trackList : Value of is Track list or not
     */
    public void setTrackList(boolean trackList) {
        mIsTrackList = trackList;
    }

    /**
     * @brief Method to get browsing type
     * @return int : browsing type
     */
    public int getBrowsingType() {
        return mBrowsingType;
    }

    /**
     * @brief Method to set browse type
     * @param mBrowsingType : browsing type
     */
    public void setBrowsingType(int mBrowsingType) {
        this.mBrowsingType = mBrowsingType;
    }

    /**
     * @brief Method to return BrowseContext class values as string
     * @return String : BrowseContext values
     */
    @Override
    public String toString() {
        return "BrowseContext{"
                + "mCategoryType=" + mCategoryType
                + ", mItemId=" + mItemId
                + ", mListType=" + mListType
                + ", mBrowsingType=" + mBrowsingType
                + '}';
    }

    /**
     * @brief Method to compare the objects
     * @param obj : object
     * @return boolean : status
     */
    @Override
    public boolean equals(Object obj) {
        boolean status = false;
        if (obj != null) {
            if (obj.getClass() == this.getClass()) {
                BrowseContext browseContext = (BrowseContext) obj;
                if (mCategoryType == browseContext.mCategoryType
                        && mItemId.equals(browseContext.mItemId)) {
                    status = true;
                }
            }
        }
        return status;
    }

    /**
     * @brief Method to get the hashcode.
     * @return int : hashcode.
     */
    @Override
    public int hashCode() {
        int result = 0;
        result = mCategoryType + mListType;
        return result;
    }
}