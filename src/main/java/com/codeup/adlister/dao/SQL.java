package com.codeup.adlister.dao;


import com.codeup.adlister.models.Ad;

import java.util.List;

public interface SQL {

    List<Object> listAll(String table, String object);
    List<Object> listBySearch(String table, String column, String data);
    Object findBySearch(String table, String column, String data);
    void deleteQuery(String table, String column, String data);

//    Long insert(Object object);
//    void deleteQuery(String table, String column, String data);
//    Object search(String table, String column, String data);

}
