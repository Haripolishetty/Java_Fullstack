package com.quiz.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.quiz.model.User;
public class UserDAO {
   // User Registration
   public boolean registerUser(String username, String email, String password) {
       Connection conn = null;
       PreparedStatement ps = null;
       boolean registered = false;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
           ps = conn.prepareStatement(query);
           ps.setString(1, username);
           ps.setString(2, email);
           ps.setString(3, password);
           int rows = ps.executeUpdate();
           registered = (rows > 0);
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
               if (ps != null) ps.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return registered;
   }
   // User Login
   public User loginUser(String email, String password) {
       User user = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM users WHERE email = ? AND password = ?";
           ps = conn.prepareStatement(query);
           ps.setString(1, email);
           ps.setString(2, password);
           rs = ps.executeQuery();
           if (rs.next()) {
               user = new User();
               user.setUserId(rs.getInt("user_id"));
               user.setUsername(rs.getString("username"));
               user.setEmail(rs.getString("email"));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return user;
   }
   // Check if email already exists
   public boolean emailExists(String email) {
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       boolean exists = false;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM users WHERE email = ?";
           ps = conn.prepareStatement(query);
           ps.setString(1, email);
           rs = ps.executeQuery();
           exists = rs.next();
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return exists;
   }
   // Check if username already exists
   public boolean usernameExists(String username) {
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       boolean exists = false;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM users WHERE username = ?";
           ps = conn.prepareStatement(query);
           ps.setString(1, username);
           rs = ps.executeQuery();
           exists = rs.next();
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return exists;
   }
   // Get User by ID
   public User getUserById(int userId) {
       User user = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM users WHERE user_id = ?";
           ps = conn.prepareStatement(query);
           ps.setInt(1, userId);
           rs = ps.executeQuery();
           if (rs.next()) {
               user = new User();
               user.setUserId(rs.getInt("user_id"));
               user.setUsername(rs.getString("username"));
               user.setEmail(rs.getString("email"));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return user;
   }
}