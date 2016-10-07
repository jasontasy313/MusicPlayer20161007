package com.example.kemie.musicplayer20161007;

/**
 * Created by user on 2016/10/7.
 */
public class Song {
    private long id;
    private String title;
    private String album;

    public Song(long id, String title, String album) {
        this.id = id;
        this.title = title;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
