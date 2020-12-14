package com.hackathon.internetradio.internetradioplayerservice.service.contentcatalogs;

import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MusicList {

    private static final TreeMap<String, MediaSessionCompat.QueueItem> mMusicList = new TreeMap<>();

    static {
        createQueueItem("http://216.55.141.189:8653/live", "Swadesh FM");

    }

    public static List<MediaSessionCompat.QueueItem> getMusicQueueList() {
        List<MediaSessionCompat.QueueItem> musicList = new ArrayList<>();
        for (MediaSessionCompat.QueueItem queueItem : mMusicList.values()) {
            musicList.add(queueItem);
        }
        return musicList;
    }

    public static MediaDescriptionCompat getMediaDescription(String mediaId) {
        MediaDescriptionCompat mediaDescriptionCompat = null;
        for (MediaSessionCompat.QueueItem queueItem : mMusicList.values()) {
            if (mMusicList.containsKey(mediaId)) {
                mediaDescriptionCompat = mMusicList.get(mediaId).getDescription();
            }
        }
        return mediaDescriptionCompat;
    }

    private static void createQueueItem(String mediaId, String title) {
        MediaDescriptionCompat mediaDescriptionCompat =
                new MediaDescriptionCompat.Builder()
                        .setMediaId(mediaId)
                        .setTitle(title)
                        .setSubtitle("")
                        .setDescription("")
                        .setExtras(null)
                        .build();
        mMusicList.put(mediaId,
                new MediaSessionCompat.QueueItem(mediaDescriptionCompat, 1));
    }
}
