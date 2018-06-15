package com.codeup.adlister.dao;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.codeup.adlister.Config;



public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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

//    @Override
//    public List<Ad> all() {
//        try {
//            String sql = "SELECT * FROM ads";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery("SELECT * FROM ads");
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving all ads.", e);
//        }
//    }
    @Override
    public List<Ad> search(String searchTerm){
        String input = "SELECT * FROM ads WHERE title LIKE ? OR description LIKE ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(input);
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving search results", e);
        }
    }

//    @Override
//    public List<Ad> listByUser (int id) {
//        try{
//            String sql = "SELECT * FROM ads WHERE userid = ?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            String userId = Integer.toString(id);
//            stmt.setString(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving ads by user id", e);
//        }
//    }
//    @Override
    public Long insert(Ad ad) {
        try {
            String sql = "INSERT INTO ads(userid, randId, title, description) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setLong(2, ad.getRandId());
            stmt.setString(3, ad.getTitle());
            stmt.setString(4, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

//    @Override
//    public void deleteQuery(String adId){
//        try {
//            String sql = "DELETE FROM ads WHERE id = ? Limit 1";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, adId);
//
//
//            stmt.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error deleting ad", e);
//        }
//    }

//    @Override
//    public Ad findById(int id) {
//        String query = "SELECT * FROM ads WHERE id = ? LIMIT 1";
//        try {
//            PreparedStatement stmt = connection.prepareStatement(query);
//            String adId = Integer.toString(id);
//            stmt.setString(1, adId);
//            return extractAdId(stmt.executeQuery());
//        } catch (SQLException e) {
//            throw new RuntimeException("Error finding ad by id", e);
//        }
//    }

//    @Override
//    public Ad findByRandId(long id) {
//        String query = "SELECT * FROM ads WHERE randId = ? LIMIT 1";
//        try {
//            PreparedStatement stmt = connection.prepareStatement(query);
//            String randId = Long.toString(id);
//            stmt.setString(1, randId);
//            return extractAdId(stmt.executeQuery());
//        } catch (SQLException e) {
//            throw new RuntimeException("Error finding ad by id", e);
//        }
//    }
//    @Override
    public List<Ad> listByCat(int catId) {
        String query = "SELECT * FROM ads a JOIN adCategory a2 ON a.id = a2.adID JOIN categories c ON a2.catID = c.id " +
                "WHERE a2.catID = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            String id = Integer.toString(catId);
            stmt.setString(1,id);
            return createAdsFromResultsCat(stmt.executeQuery());
        } catch (SQLException e){
            throw new RuntimeException("Error finding ad by category", e);
        }
    }





//    private Ad extractAdId(ResultSet rs) throws SQLException {
//        if (! rs.next()) {
//            return null;
//        }
//        return new Ad(
//                rs.getLong("id"),
//                rs.getLong("userid"),
//                rs.getLong("randId"),
//                rs.getString("title"),
//                rs.getString("description")
//        );
//    }


    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("userid"),
                rs.getLong("randId"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    private List<Ad> createAdsFromResultsCat(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    private Ad extractAdCat(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("userid"),
                rs.getLong("randId"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("catTitle")
        );
    }
    @Override
    public List<Ad> randomAds(List<Object> ads){
        int length = ads.size()-1;
        List<Ad> newAdList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            int random = (int) (Math.random() * (length - 1));
            Ad newAd = (Ad) ads.get(random);
            ads.remove(random);
            newAdList.add(newAd);
        }

        return newAdList;
    }
}

