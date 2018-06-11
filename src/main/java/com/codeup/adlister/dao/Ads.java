package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    List<Ad> search(String searchTerm);

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    void deleteQuery(String adId);

    Ad findById(int id);

    Ad findByRandId(long id);

    List<Ad> listByUser(int id);

    List<Ad> listByCat(int catId);



}