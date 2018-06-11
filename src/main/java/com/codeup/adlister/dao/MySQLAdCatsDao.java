package com.codeup.adlister.dao;


import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.AdCategory;
import com.mysql.cj.core.util.StringUtils;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.codeup.adlister.Config;

public class MySQLAdCatsDao implements AdCategories {
    private Connection connection = null;

    public MySQLAdCatsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    @Override
    public void insert(AdCategory adid) {
        try {
            String sql = "INSERT INTO adCategory (adID, catID) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            String adId = Long.toString(adid.getAdId());
            String catId = Long.toString(adid.getCatId());
            stmt.setString(1,adId);
            stmt.setString(2,catId);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private String createInsertQuery(AdCategory adCat) {
        return "INSERT INTO adCategory(adID, catID) VALUES "
                + "(" +  adCat.getAdId() + "," + adCat.getCatId() + ")";
    }
}
