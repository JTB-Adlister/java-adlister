package com.codeup.adlister.models;

public class Category {
    private long id;
    private String catTitle;

    public Category(long id, String catTitle){
        this.id = id;
        this.catTitle = catTitle;
    }

    public Category(String title){
        this.catTitle = catTitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }
}
