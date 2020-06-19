package com.example.banlge_aduio_story.model;

import android.graphics.drawable.Drawable;

public class Audioname {

    public Audioname(String audioname, int imageDrawable,int id) {
        this.audioname = audioname;
        this.imageDrawable= imageDrawable;
        this.id= id;
    }

    public void setAudioname(String audioname) {
        this.audioname = audioname;
    }

    public String getAudioname() {
        return audioname;
    }

    private String audioname;

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    private int imageDrawable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;


}
