package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.mysql.cj.api.mysqla.result.Resultset;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MySQLDao implements SQL {
    private Connection connection = null;

    public MySQLDao(Config config) {
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
    public List<Object> listAll(String table, String object) {
        String input = "SELECT * FROM ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(input);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
            return createObjectsFromResults(rs, object);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all " + table, e);
        }
    }

    @Override
    public Object findBySearch(String table, String column, String data){
        String input = "SELECT * FROM " + table + " WHERE " + column + " = ? Limit 1";
        try{
            PreparedStatement stmt = connection.prepareStatement(input);
            stmt.setString(1, data);
            System.out.println(stmt);

            if(table.equals("categories")) {
                return extractCat(stmt.executeQuery());
            }
            if(table.equals("users")){
                return extractUser(stmt.executeQuery());
            }
            if(table.equals("ads")){
                return extractAdSearch(stmt.executeQuery());
            }
            return null;
        } catch (SQLException e){
            throw new RuntimeException("Error searching for " + data + " in " + table, e);
        }
    }

    @Override
    public List<Object> listBySearch(String table, String column, String data){
        String input = "SELECT * FROM " + table + " WHERE " + column + " = ? Limit 1";
        try{
            PreparedStatement stmt = connection.prepareStatement(input);
            stmt.setString(1, data);
            System.out.println(stmt);
            if(table.equals("categories")) {
                return createObjectsFromResults(stmt.executeQuery(), "category");
            }
            if(table.equals("users")){
                return createObjectsForSearch(stmt.executeQuery(), "user");
            }
            if(table.equals("ads")){
                return createObjectsForSearch(stmt.executeQuery(), "ads");
            }
            return null;
        } catch (SQLException e){
            throw new RuntimeException("Error searching for " + data + " in " + table, e);
        }
    }


    @Override
    public void deleteQuery(String table, String column, String data){
        String sql = "DELETE FROM " + table + " WHERE " + column + " = ? Limit 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, data);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting " + column + " from " + table, e);
        }
    }




    private List<Object> createObjectsFromResults(ResultSet rs, String type) throws SQLException {
        List<Object> ads = new ArrayList<>();
        if(type.equals("ad")) {
            while (rs.next()) {
                ads.add(extractAd(rs));
            }
        }
        if(type.equals("user")) {
            while (rs.next()){
                ads.add(extractUserForAll(rs));
            }
        }
        if(type.equals("category")){
            while (rs.next()){
                ads.add(extractCat(rs));
            }
        }
        return ads;
    }


    //method for search object extraction
    private List<Object> createObjectsForSearch(ResultSet rs, String type) throws SQLException {
        List<Object> ads = new ArrayList<>();
        if(type.equals("ad")) {
            while (rs.next()) {
                ads.add(extractAdSearch(rs));
            }
        }
        if(type.equals("user")) {
            while (rs.next()){
                ads.add(extractUser(rs));
            }
        }

        return ads;
    }

    //defined extractions for each object type
    private Category extractCat(ResultSet rs) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getString("catTitle")
        );
    }
    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("userid"),
                rs.getLong("randId"),
                rs.getString("title"),
                rs.getString("description")
        );
    }
    private User extractUserForAll(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("userrole")
        );
    }

    //objection extraction for searches
    private Ad extractAdSearch(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new Ad(
                rs.getLong("id"),
                rs.getLong("userid"),
                rs.getLong("randId"),
                rs.getString("title"),
                rs.getString("description")
        );
    }
    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("userrole")
        );
    }
}
