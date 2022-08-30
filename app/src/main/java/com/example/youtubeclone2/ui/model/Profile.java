package com.example.youtubeclone2.ui.model;

import java.io.Serializable;

public class Profile implements Serializable {

    private String channelName,ContentName,views,time,profileImageUrl,imageUrl,type;
    private int like,dislike,subscriber;


    public Profile(String channelName, String contentName, String views, String time, String profileImageUrl, String imageUrl,String type) {
        this.channelName = channelName;
        this.ContentName = contentName;
        this.views = views;
        this.time = time;
        this.profileImageUrl = profileImageUrl;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getContentName() {
        return ContentName;
    }

    public void setContentName(String contentName) {
        ContentName = contentName;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getType() {
        return type;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(int subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "channelName='" + channelName + '\'' +
                ", ContentName='" + ContentName + '\'' +
                ", views='" + views + '\'' +
                ", time='" + time + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
