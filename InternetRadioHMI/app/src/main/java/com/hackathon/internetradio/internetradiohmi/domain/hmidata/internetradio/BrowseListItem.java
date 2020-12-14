package com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio;

/**
 * @brief This class contains the Browse list item properties
 */
public class BrowseListItem {

    /**
     * Variable to keep list item Id
     */
    private String mListItemId;

    /**
     * Variable to keep list item name
     */
    private String mListItemName;

    /**
     * Variable to keep list item Next level icon
     */
    private boolean  mListItemNextLevelIcon;

    /**
     * Variable to store list item type.
     */
    private int mItemType;

    /**
     * Variable to store browse item status.
     */
    private int mItemState;

    /**
     * @brief Empty constructor for initialization process.
     */
    public BrowseListItem() {
        // Empty Constructor
    }

    /**
     * @brief Constructor for BrowseListItem
     * @param listItemId : list item Id
     */
    public BrowseListItem(String listItemId) {
        mListItemId = listItemId;
    }

    /**
     * @brief Method to get list item id
     * @return int : list item id
     */
    public String getListItemId() {
        return mListItemId;
    }

    /**
     * @brief Method to set list item id
     * @param listItemId : list item id
     */
    public void setListItemId(String listItemId) {
        mListItemId = listItemId;
    }

    /**
     * @brief Method to get list item name
     * @return String : list item name
     */
    public String getListItemName() {
        return mListItemName;
    }

    /**
     * @brief Method to set list item name
     * @param listItemName : list item name
     */
    public void setListItemName(String listItemName) {
        mListItemName = listItemName;
    }

    /**
     * @brief Method to get list item next level icon
     * @return boolean : list item next level icon
     */
    public boolean getListItemNextLevelIcon() {
        return mListItemNextLevelIcon;
    }

    /**
     * @brief Method to set list item right icon name
     * @param listItemNextLevelIcon : list item next level icon name
     */
    public void setListItemNextLevelIcon(boolean listItemNextLevelIcon) {
        mListItemNextLevelIcon = listItemNextLevelIcon;
    }

    /**
     * @brief Method to get list item type
     * @return int : Value of list item type
     */
    public int getItemType() {
        return mItemType;
    }

    /**
     * @brief Method to set list item type
     * @param itemType : Value of list item type
     */
    public void setItemType(int itemType) {
        mItemType = itemType;
    }

    /**
     * @brief Method to get item state
     * @return int : Item state
     */
    public int getItemState() {
        return mItemState;
    }

    /**
     * @brief Method to set item state.
     * @param itemState : item state
     */
    public void setItemState(int itemState) {
        mItemState = itemState;
    }
}

