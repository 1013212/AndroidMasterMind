package com.hackathon.internetradio.internetradioplayerservice.service.contentcatalogs;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;


public class MusicLibrary {

    private static final TreeMap<String, MediaMetadataCompat> music = new TreeMap<>();
    private static final HashMap<String, Integer> albumRes = new HashMap<>();
    private static final HashMap<String, String> musicFileName = new HashMap<>();

    static {
        createMediaMetadataCompat(
                "http://216.55.141.189:8653/live",
                "Back To Basics",
                "Rick Astley",
                "Sound Of Summer",
                "Jazz",
                103,
                TimeUnit.SECONDS,
                "bagel.mp3",
                0,
                "album_art_1");
        createMediaMetadataCompat(
                "http://205.164.62.22:8075",
                "American Roots",
                "The 126ers",
                "Boot Liquor",
                "Rock",
                160,
                TimeUnit.SECONDS,
                "american_roots.mp3",
                0,
                "album_art_2");
        createMediaMetadataCompat(
                "http://184.154.43.106:8145",
                "Celtic",
                "Beach Boys",
                "Bad Blood",
                "Rock",
                160,
                TimeUnit.SECONDS,
                "celtic.mp3",
                0,
                "album_art_3");
        createMediaMetadataCompat(
                "http://stream.infowars.com:80",
                "Chillout",
                "B.o.B",
                "Groove Salad",
                "Pop",
                120,
                TimeUnit.SECONDS,
                "chillout.mp3",
                0,
                "album_art_4");
        createMediaMetadataCompat(
                "http://wnyc-iheart.streamguys.com/wnycfm-iheart.aac",
                "Blue Ruby",
                "Commodore Clause",
                "Commodore 64 Remixes",
                "Blues",
                103,
                TimeUnit.SECONDS,
                "blue_ruby.mp3",
                0,
                "album_art_5");
        createMediaMetadataCompat(
                "http://titan.shoutca.st:8647/stream",
                "Endlessly",
                "Muse",
                "Absolution",
                "Rock",
                136,
                TimeUnit.SECONDS,
                "endlessly.mp3",
                0,
                "album_art_5");
        createMediaMetadataCompat(
                "http://stream.radio.co/s1383afdc9/listen?ver=828256",
                "Hymn for the Weekend",
                "Coldplay",
                "A Head Full of Dreams",
                "Reggae",
                150,
                TimeUnit.SECONDS,
                "hymn_for_the_weekend.mp3",
                0,
                "album_art_6");
        createMediaMetadataCompat(
                "http://192.111.140.6:8546/stream",
                "Dub Step Beyond",
                "The BB Rockers",
                "DS raw",
                "Dubstep",
                141,
                TimeUnit.SECONDS,
                "dub_step_beyond.mp3",
                0,
                "album_art_7");
    }

    public static String getRoot() {
        return "root";
    }

    private static String getAlbumArtUri(String albumArtResName) {
        return null;
    }

    public static String getMusicFilename(String mediaId) {
        return musicFileName.containsKey(mediaId) ? musicFileName.get(mediaId) : null;
    }

    private static int getAlbumRes(String mediaId) {
        return albumRes.containsKey(mediaId) ? albumRes.get(mediaId) : 0;
    }

    public static Bitmap getAlbumBitmap(Context context, String mediaId) {
        return BitmapFactory.decodeResource(context.getResources(),
                MusicLibrary.getAlbumRes(mediaId));
    }

    public static List<MediaBrowserCompat.MediaItem> getMediaItems() {
        List<MediaBrowserCompat.MediaItem> result = new ArrayList<>();
        for (MediaMetadataCompat metadata : music.values()) {
            result.add(
                    new MediaBrowserCompat.MediaItem(
                            metadata.getDescription(), MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));
        }
        return result;
    }

    public static MediaMetadataCompat getMetadata(Context context, String mediaId) {
        MediaMetadataCompat metadataWithoutBitmap = music.get(mediaId);
        Bitmap albumArt = getAlbumBitmap(context, mediaId);

        // Since MediaMetadataCompat is immutable, we need to create a copy to set the album art.
        // We don't set it initially on all items so that they don't take unnecessary memory.
        MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
        for (String key :
                new String[]{
                        MediaMetadataCompat.METADATA_KEY_MEDIA_ID,
                        MediaMetadataCompat.METADATA_KEY_ALBUM,
                        MediaMetadataCompat.METADATA_KEY_ARTIST,
                        MediaMetadataCompat.METADATA_KEY_GENRE,
                        MediaMetadataCompat.METADATA_KEY_TITLE
                }) {
            builder.putString(key, metadataWithoutBitmap.getString(key));
        }
        builder.putLong(
                MediaMetadataCompat.METADATA_KEY_DURATION,
                metadataWithoutBitmap.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
        builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, albumArt);
        return builder.build();
    }

    private static void createMediaMetadataCompat(
            String mediaId,
            String title,
            String artist,
            String album,
            String genre,
            long duration,
            TimeUnit durationUnit,
            String musicFilename,
            int albumArtResId,
            String albumArtResName) {
        music.put(
                mediaId,
                new MediaMetadataCompat.Builder()
                        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
                        .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
                        .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                        .putLong(MediaMetadataCompat.METADATA_KEY_DURATION,
                                TimeUnit.MILLISECONDS.convert(duration, durationUnit))
                        .putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre)
                        .putString(
                                MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI,
                                getAlbumArtUri(albumArtResName))
                        .putString(
                                MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
                                getAlbumArtUri(albumArtResName))
                        .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                        .build());
        albumRes.put(mediaId, albumArtResId);
        musicFileName.put(mediaId, musicFilename);
    }
}
