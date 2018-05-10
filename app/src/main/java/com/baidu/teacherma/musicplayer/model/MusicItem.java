package com.baidu.teacherma.musicplayer.model;

import android.net.Uri;

/**
 * Created by teacherMa on 2018/5/8
 * <p>
 * Storage music info that from scan local memory
 */

public class MusicItem {
    private String name;
    private String songUri;
    private String albumUri;
    private String artist;
    private long duration;

    public MusicItem(Uri songUri, Uri albumUri, String strName, long duration, String artist) {
        this.name = strName;
        this.songUri = songUri.toString();
        this.duration = duration;
        this.albumUri = albumUri.toString();
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getSongUri() {
        return Uri.parse(songUri);
    }

    public void setSongUri(Uri songUri) {
        this.songUri = songUri.toString();
    }

    public Uri getAlbumUri() {
        return Uri.parse(albumUri);
    }

    public void setAlbumUri(Uri albumUri) {
        this.albumUri = albumUri.toString();
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setSongUri(String songUri) {
        this.songUri = songUri;
    }

    public void setAlbumUri(String albumUri) {
        this.albumUri = albumUri;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
