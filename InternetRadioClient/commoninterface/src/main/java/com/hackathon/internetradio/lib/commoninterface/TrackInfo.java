/**
 * @file        TrackInfo.java
 */

package com.hackathon.internetradio.lib.commoninterface;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @brief Class for holding  now play song details
 */
public class TrackInfo implements Parcelable {

    /**
     * @brief Interface that must be implemented and provided as a public CREATOR
     *        field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<TrackInfo> CREATOR =
            new Creator<TrackInfo>() {

        public TrackInfo createFromParcel(Parcel in) {
            return new TrackInfo(in);
        }

        public TrackInfo[] newArray(int size) {

            return new TrackInfo[size];
        }
    };
    /**
     * Member variable to store url.
     */
    private String mMediaId;

    /**
     * Member variable to store title.
     */
    private String mTitle;

    /**
     * Member variable to store artist.
     */
    private String mArtist;

    /**
     * Member variable to store album.
     */
    private String mAlbum;

    /**
     * Member variable to store genre.
     */
    private String mGenre;

    /**
     * Member variable to store musicFilename.
     */
    private String mMusicFilename;

    /**
     * Member variable to store albumArtResName.
     */
    private String mAlbumArtResName;


    /**
     * @brief Constructor for this class
     *
     * @param id :                song url
     * @param title :             title
     * @param artist :            artist
     * @param album :             album
     * @param genre :             genre
     * @param fileName :          fileName
     * @param coverArt :          coverArt
     */
    public TrackInfo(String id, String title, String artist, String album,
                     String genre, String fileName, String coverArt) {
        mMediaId = id;
        mTitle = title;
        mArtist = artist;
        mAlbum = album;
        mGenre = genre;
        mMusicFilename = fileName;
        mAlbumArtResName = coverArt;
    }

    /**
     * @brief Constructor for TrackInfo
     */
    public TrackInfo(){

    }

    /**
     * @brief Constructor for parcel class
     * @param in : parcel object
     */
    public TrackInfo(Parcel in) {
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
            dest.writeString(mMediaId);
            dest.writeString(mTitle);
            dest.writeString(mArtist);
            dest.writeString(mAlbum);
            dest.writeString(mGenre);
            dest.writeString(mMusicFilename);
            dest.writeString(mAlbumArtResName);

        }
    }

    /**
     * @brief Method for read from parcel class.
     * @param in : parcel object.
     */
    public void readFromParcel(Parcel in) {
        if (in != null) {
            mMediaId = in.readString();
            mTitle = in.readString();
            mArtist = in.readString();
            mAlbum = in.readString();
            mGenre = in.readString();
            mMusicFilename = in.readString();
            mAlbumArtResName = in.readString();

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
        return mMediaId;
    }

    /**
     * @brief Method to set song ID
     * @param id : song ID
     */
    public void setId(String id) {
        mMediaId = id;
    }

    /**
     * @brief Method to get album name
     * @return String : album name
     */
    public String getAlbumName() {
        return mAlbum;
    }

    /**
     * @brief Method to set album name
     * @param albumName : album name of song
     */
    public void setAlbumName(String albumName) {
        mAlbum = albumName;
    }

    /**
     * @brief Method to get Title
     * @return String : Title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * @brief Method to set  Title
     * @param Title : Title
     */
    public void setTitle(String Title) {
        mTitle = Title;
    }

    /**
     * @brief Method to get song name
     * @return String : song name
     */
    public String getFileName() {
        return mMusicFilename;
    }

    /**
     * @brief Method to set song name
     * @param fileName : song name
     */
    public void setAlbumSongName(String fileName) {
        mMusicFilename = fileName;
    }

    /**
     * @brief Method to get CoverArt
     * @return String : CoverArt
     */
    public String getCoverArt() {
        return mAlbumArtResName;
    }

    /**
     * @brief Method to set coverArt
     * @param coverArt : coverArt
     */
    public void setSongUrl(String coverArt) {
        mAlbumArtResName = coverArt;
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
     * @brief Method to return TrackInfo valves as string
     * @return String : TrackInfo values
     */
    @Override
    public String toString() {
        return "TrackInfo{"
                + ", mMediaId=" + mMediaId
                + ", mTitle=" + mTitle
                + "mArtist=" + mArtist
                + "mAlbum=" + mAlbum
                + "mGenre=" + mGenre
                + "mMusicFilename=" + mMusicFilename
                + "mAlbumArtResName=" + mAlbumArtResName
                + '}';
    }
}