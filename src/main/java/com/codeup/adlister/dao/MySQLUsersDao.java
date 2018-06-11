package com.codeup.adlister.dao;
import com.codeup.adlister.Config;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
    private Connection connection = null;

    public MySQLUsersDao(Config config) {
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
    public List<User> listAll(){

        try{
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            return createUserList(rs);
        } catch (SQLException e){
            throw new RuntimeException("Error listing all users", e);
        }
    }
    private List<User> createUserList(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(extractUser(rs));
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public Long insert(User user) {
        try {

            String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new user.", e);
        }

    }

    @Override
    public User findByUserId(String id){
        String sql = "SELECT * FROM users WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by userid", e);
        }
    }

    private List<User> createUsersFromResults(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(extractUser(rs));
        }
        return users;
    }

    @Override
    public boolean userExists(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return scanDb(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public void deleteQuery(String userId){
        try {
            String sql = "DELETE FROM users WHERE id = ? Limit 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    private boolean scanDb(ResultSet rs) throws SQLException {
        return rs.next();
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