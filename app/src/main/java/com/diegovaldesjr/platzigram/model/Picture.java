package com.diegovaldesjr.platzigram.model;

import java.io.Serializable;

/**
 * Created by diego on 11/10/2017.
 */

public class Picture implements Serializable{

    private String picture;
    private String username;
    private String time;
    private String like_number = "0 dias";

    public Picture(String picture, String username, String time, String like_number) {
        this.picture = picture;
        this.username = username;
        this.time = time;
        this.like_number = like_number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike_number() {
        return like_number;
    }

    public void setLike_number(String like_number) {
        this.like_number = like_number;
    }
}
