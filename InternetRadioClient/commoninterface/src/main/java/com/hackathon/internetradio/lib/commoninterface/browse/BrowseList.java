/**
 * @file        BrowseList.java
 * @brief       Class for browse list

 *
 * @author      Austin Renitz C
 */

package com.hackathon.internetradio.lib.commoninterface.browse;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief This class represents browse list
 */
public class BrowseList implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *        field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<BrowseList> CREATOR = new Creator<BrowseList>() {
        @Override
        public BrowseList createFromParcel(Parcel in) {
            return new BrowseList(in);
        }

        @Override
        public BrowseList[] newArray(int size) {
            return new BrowseList[size];
        }
    };

    /**
     * Variable to store browse list type.
     */
    private int mListType;

    /**
     * Variable to store browse category type.
     */
    private int mCategoryType;

    /**
     * Variable to store list of browse item.
     */
    private List<BrowseItem> mBrowseItemList;

    /**
     * @brief Constructor for parcel class
     * @param in : Object of parcel class
     */
    public BrowseList(Parcel in) {
        readFromParcel(in);
    }

    /**
     * @brief Constructor for BrowseList class
     * @param listType : Type of list
     * @param categoryType : Type of category
     * @param browseItemList : List of browse item
     */
    public BrowseList(int listType, int categoryType, List<BrowseItem> browseItemList) {
        mListType = listType;
        mCategoryType = categoryType;
        mBrowseItemList =  new ArrayList<>(browseItemList);
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
        dest.writeInt(mListType);
        dest.writeInt(mCategoryType);
        dest.writeTypedList(mBrowseItemList);
    }

    /**
     * @brief Method for read from parcel class.
     * @param in : Object of parcel class
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mListType = in.readInt();
            mCategoryType = in.readInt();
            mBrowseItemList = in.createTypedArrayList(BrowseItem.CREATOR);
        } else {
        }
    }

    /**
     * @brief Method to get list type
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
     * @brief Method to get category type
     * @return int : Type of category
     */
    public int getCategoryType() {
        return mCategoryType;
    }

    /**
     * @brief Method to set category type
     * @param categoryType : Type of category
     */
    public void setCategoryType(int categoryType) {
        mCategoryType = categoryType;
    }

    /**
     * @brief Method to get browse item list
     * @return List : Object of BrowseItem list
     */
    public List<BrowseItem> getBrowseItemList() {
        return  new ArrayList<>(mBrowseItemList);
    }

    /**
     * @brief Method to set browse item list
     * @param browseItemList : Object of BrowseItem list
     */
    public void setBrowseItemList(List<BrowseItem> browseItemList) {
        mBrowseItemList =  new ArrayList<>(browseItemList);
    }

    /**
     * @brief Method to return BrowseList class values as string
     * @return String : BrowseList values
     */
    @Override
    public String toString() {
        return "BrowseList{"
                + "mListType=" + mListType
                + ", mCategoryType=" + mCategoryType
                + ", mBrowseItemList=" + mBrowseItemList
                + '}';
    }
}
