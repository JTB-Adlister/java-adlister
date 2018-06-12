package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
//    User findByUsername(String username);

    Long insert(User user);

    boolean userExists(String username);

    User findByUserId(String id);

    List<User> listAll();

    void deleteQuery(String userId);

    void updateUser(User user, String username, String email, String passNew);
}
