/**
 * @file        MetaData.java
 */

package com.hackathon.internetradio.lib.commoninterface;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @brief Class for holding  now play song details
 */
public class MetaData implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *        field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<MetaData> CREATOR =
            new Creator<MetaData>() {

        public MetaData createFromParcel(Parcel in) {
            return new MetaData(in);
        }

        public MetaData[] newArray(int size) {

            return new MetaData[size];
        }
    };
    /**
     * Member variable to store Id.
     */
    private String mId;

    /**
     * Member variable to store Album name.
     */
    private String mAlbumName;

    /**
     * Member variable to store AlbumId.
     */
    private int mSongId;

    /**
     * Member variable to store AlbumSongName.
     */
    private String mAlbumSongName;

    /**
     * Member variable to store SongUrl.
     */
    private String mSongUrl;

    /**
     * Member variable to store AlbumSongDuration.
     */
    private String mAlbumSongDuration;

    /**
     * Member variable to store Artist.
     */
    private String mArtist;

    /**
     * Member variable to store Year.
     */
    private String mYear;

    /**
     * Member variable to store Composer.
     */
    private String mComposer;
    /**
     * Member variable to store Genre.
     */
    private String mGenre;

    /**
     * @brief Constructor for this class
     *
     * @param id :                song ID
     * @param albumName :         album name
     * @param albumSongId :       song ID
     * @param albumSongName :     song name
     * @param songUrl :           song URL
     * @param albumSongDuration : song URL
     * @param artist :            artist
     * @param year :              year
     * @param composer :          composer
     * @param genre :             genre
     */
    public MetaData(String id, String albumName, int albumSongId, String albumSongName,
                    String songUrl, String albumSongDuration, String artist, String year,
                    String composer, String genre) {
        mId = id;
        mAlbumName = albumName;
        mSongId = albumSongId;
        mAlbumSongName = albumSongName;
        mSongUrl = songUrl;
        mAlbumSongDuration = albumSongDuration;
        mArtist = artist;
        mYear = year;
        mComposer = composer;
        mGenre = genre;
    }

    /**
     * @brief Constructor for MetaData
     */
    public MetaData(){

    }

    /**
     * @brief Constructor for parcel class
     * @param in : parcel object
     */
    public MetaData(Parcel in) {
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
            dest.writeString(mId);
            dest.writeInt(mSongId);
            dest.writeString(mAlbumName);
            dest.writeString(mAlbumSongName);
            dest.writeString(mSongUrl);
            dest.writeString(mAlbumSongDuration);
            dest.writeString(mArtist);
            dest.writeString(mYear);
            dest.writeString(mComposer);
            dest.writeString(mGenre);
        }
    }

    /**
     * @brief Method for read from parcel class.
     * @param in : parcel object.
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mId = in.readString();
            mSongId = in.readInt();
            mAlbumName = in.readString();
            mAlbumSongName = in.readString();
            mSongUrl = in.readString();
            mAlbumSongDuration = in.readString();
            mArtist = in.readString();
            mYear = in.readString();
            mComposer = in.readString();
            mGenre = in.readString();
        }
    }

    /**
     * @brief Describe the kinds of special objects contained in this Parcelable
     *         instance's marshaled representation.
     * @return int : a bitmask indicating the set of special object types marshaled
     *         by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @brief Method to get song ID
     * @return String : song ID
     */
    public String getId() {
        return mId;
    }

    /**
     * @brief Method to set song ID
     * @param id : song ID
     */
    public void setId(String id) {
        mId = id;
    }

    /**
     * @brief Method to get album name
     * @return String : album name
     */
    public String getAlbumName() {
        return mAlbumName;
    }

    /**
     * @brief Method to set album name
     * @param albumName : album name of song
     */
    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }

    /**
     * @brief Method to get album ID
     * @return String : album ID
     */
    public int getAlbumId() {
        return mSongId;
    }

    /**
     * @brief Method to set  album ID
     * @param id : Album ID
     */
    public void setAlbumId(int id) {
        mSongId = id;
    }

    /**
     * @brief Method to get song name
     * @return String : song name
     */
    public String getAlbumSongName() {
        return mAlbumSongName;
    }

    /**
     * @brief Method to set album SongName
     * @param albumSongName : album SongName of song
     */
    public void setAlbumSongName(String albumSongName) {
        mAlbumSongName = albumSongName;
    }

    /**
     * @brief Method to get song URL
     * @return String : song URL
     */
    public String getSongUrl() {
        return mSongUrl;
    }

    /**
     * @brief Method to set songUrl name
     * @param songUrl : songUrl of song
     */
    public void setSongUrl(String songUrl) {
        mSongUrl = songUrl;
    }

    /**
     * @brief Method to get song duration
     * @return String : song duration
     */
    public String getAlbumSongDuration() {
        return mAlbumSongDuration;
    }

    /**
     * @brief Method to set albumSong Duration
     * @param albumSongDuration : albumSong Duration of song
     */
    public void setAlbumSongDuration(String albumSongDuration) {
        mAlbumSongDuration = albumSongDuration;
    }

    /**
     * @brief Method to get artist name
     * @return String : artist name
     */
    public String getArtist() {
        return mArtist;
    }

    /**
     * @brief Method to set artist name
     * @param artist : artist name of song
     */
    public void setArtist(String artist) {
        mArtist = artist;
    }

    /**
     * @brief Method to get year tag from song
     * @return String : year
     */
    public String getYear() {
        return mYear;
    }

    /**
     * @brief Method to set year
     * @param year : year of song
     */
    public void setYear(String year) {
        mYear = year;
    }

    /**
     * @brief Method to get composer name
     * @return String : composer name
     */
    public String getComposer() {
        return mComposer;
    }

    /**
     * @brief Method to set composer name
     * @param composer : composer name
     */
    public void setComposer(String composer) {
        mComposer = composer;
    }

    /**
     * @brief Method to get genre name
     * @return String : genre name
     */
    public String getGenre() {
        return mGenre;
    }

    /**
     * @brief Method to set genre name
     * @param genre : genre name
     */
    public void setGenre(String genre) {
        mGenre = genre;
    }

    /**
     * @brief Method to return MetaData valves as string
     * @return String : MetaData values
     */
    @Override
    public String toString() {
        return "MetaData{"
                + ", mId=" + mId
                + ", mAlbumName=" + mAlbumName
                + "mSongId=" + mSongId
                + "mAlbumSongName=" + mAlbumSongName
                + "mSongUrl=" + mSongUrl
                + "mAlbumSongDuration=" + mAlbumSongDuration
                + "mArtist=" + mArtist
                + "mYear=" + mYear
                + "mComposer=" + mComposer
                + "mGenre=" + mGenre
                + '}';
    }
}