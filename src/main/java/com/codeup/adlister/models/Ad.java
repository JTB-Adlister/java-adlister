package com.codeup.adlister.models;

public class Ad {
    private long id;
    private long userid;
    private long randId;

    public long getRandId() {
        return randId;
    }

    public void setRandId(long randId) {
        this.randId = randId;
    }

    private String title;
    private String description;

    public Ad(long id, long userId, long randId, String title, String description) {
        this.id = id;
        this.userid = userId;
        this.title = title;
        this.description = description;
        this.randId = randId;
    }

    public Ad(long userId, long randId, String title, String description) {
        this.userid = userId;
        this.randId = randId;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userid;
    }

    public void setUserId(long userId) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
