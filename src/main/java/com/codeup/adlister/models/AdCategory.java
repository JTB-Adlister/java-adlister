package com.codeup.adlister.models;

public class AdCategory {
    private long catId;
    private long adId;

    public AdCategory(long adId, long catId){
        this.adId = adId;
        this.catId = catId;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }
}
