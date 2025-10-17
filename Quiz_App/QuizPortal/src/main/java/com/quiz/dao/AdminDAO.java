package com.quiz.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.quiz.model.Admin;
public class AdminDAO {
   // Admin Login
   public Admin loginAdmin(String username, String password) {
       Admin admin = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
           ps = conn.prepareStatement(query);
           ps.setString(1, username);
           ps.setString(2, password);
           rs = ps.executeQuery();
           if (rs.next()) {
               admin = new Admin();
               admin.setAdminId(rs.getInt("admin_id"));
               admin.setUsername(rs.getString("username"));
               admin.setEmail(rs.getString("email"));
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
       return admin;
   }
   // Update Admin Profile
   public boolean updateAdminProfile(int adminId, String email, String newPassword) {
       Connection conn = null;
       PreparedStatement ps = null;
       boolean updated = false;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "UPDATE admin SET email = ?, password = ? WHERE admin_id = ?";
           ps = conn.prepareStatement(query);
           ps.setString(1, email);
           ps.setString(2, newPassword);
           ps.setInt(3, adminId);
           int rows = ps.executeUpdate();
           updated = (rows > 0);
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
       return updated;
   }
   // Get Admin by ID
   public Admin getAdminById(int adminId) {
       Admin admin = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM admin WHERE admin_id = ?";
           ps = conn.prepareStatement(query);
           ps.setInt(1, adminId);
           rs = ps.executeQuery();
           if (rs.next()) {
               admin = new Admin();
               admin.setAdminId(rs.getInt("admin_id"));
               admin.setUsername(rs.getString("username"));
               admin.setEmail(rs.getString("email"));
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
       return admin;
   }
   // Get Dashboard Statistics
   public int getTotalQuizzes() {
       int count = 0;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT COUNT(*) as total FROM quiz";
           ps = conn.prepareStatement(query);
           rs = ps.executeQuery();
           if (rs.next()) {
               count = rs.getInt("total");
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
       return count;
   }
   public int getTotalQuestions() {
       int count = 0;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT COUNT(*) as total FROM questions";
           ps = conn.prepareStatement(query);
           rs = ps.executeQuery();
           if (rs.next()) {
               count = rs.getInt("total");
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
       return count;
   }
   public int getTotalUsers() {
       int count = 0;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT COUNT(*) as total FROM users";
           ps = conn.prepareStatement(query);
           rs = ps.executeQuery();
           if (rs.next()) {
               count = rs.getInt("total");
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
       return count;
   }
}