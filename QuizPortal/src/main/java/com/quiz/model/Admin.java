package com.quiz.model;
public class Admin {
   private int adminId;
   private String username;
   private String password;
   private String email;
   // Default Constructor
   public Admin() {
   }
   // Constructor with parameters
   public Admin(int adminId, String username, String email) {
       this.adminId = adminId;
       this.username = username;
       this.email = email;
   }
   // Getters and Setters
   public int getAdminId() {
       return adminId;
   }
   public void setAdminId(int adminId) {
       this.adminId = adminId;
   }
   public String getUsername() {
       return username;
   }
   public void setUsername(String username) {
       this.username = username;
   }
   public String getPassword() {
       return password;
   }
   public void setPassword(String password) {
       this.password = password;
   }
   public String getEmail() {
       return email;
   }
   public void setEmail(String email) {
       this.email = email;
   }
}