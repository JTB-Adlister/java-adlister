package com.codeup.adlister.dao;


import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Category;
import com.mysql.cj.core.util.StringUtils;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.codeup.adlister.Config;

public class MySQLCatsDao implements Categories {
    private Connection connection = null;

    public MySQLCatsDao(Config config) {
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
    public List<Category> listById (int id) {
        try{
            String sql = "SELECT * FROM categories WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            String userId = Integer.toString(id);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            return createCategoryFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads by user id", e);
        }
    }
    @Override
    public List<Category> listAll() {
        try{
            String sql = "SELECT * FROM categories";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            return createCategoryFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving categories", e);
        }
    }

    private List<Category> createCategoryFromResults(ResultSet rs) throws SQLException {
        List<Category> cats = new ArrayList<>();
        while (rs.next()) {
            cats.add(extractCat(rs));
        }
        return cats;
    }
    private Category extractCat(ResultSet rs) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getString("catTitle")
        );
    }
}
