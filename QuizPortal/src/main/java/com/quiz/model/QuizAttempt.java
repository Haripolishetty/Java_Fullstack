package com.quiz.model;
import java.sql.Timestamp;
public class QuizAttempt {
   private int attemptId;
   private int userId;
   private int quizId;
   private int score;
   private int totalQuestions;
   private Timestamp attemptedAt;
   private String username;
   private String quizName;
   // Default Constructor
   public QuizAttempt() {
   }
   // Constructor with parameters
   public QuizAttempt(int attemptId, int userId, int quizId, int score, int totalQuestions) {
       this.attemptId = attemptId;
       this.userId = userId;
       this.quizId = quizId;
       this.score = score;
       this.totalQuestions = totalQuestions;
   }
   // Getters and Setters
   public int getAttemptId() {
       return attemptId;
   }
   public void setAttemptId(int attemptId) {
       this.attemptId = attemptId;
   }
   public int getUserId() {
       return userId;
   }
   public void setUserId(int userId) {
       this.userId = userId;
   }
   public int getQuizId() {
       return quizId;
   }
   public void setQuizId(int quizId) {
       this.quizId = quizId;
   }
   public int getScore() {
       return score;
   }
   public void setScore(int score) {
       this.score = score;
   }
   public int getTotalQuestions() {
       return totalQuestions;
   }
   public void setTotalQuestions(int totalQuestions) {
       this.totalQuestions = totalQuestions;
   }
   public Timestamp getAttemptedAt() {
       return attemptedAt;
   }
   public void setAttemptedAt(Timestamp attemptedAt) {
       this.attemptedAt = attemptedAt;
   }
   public String getUsername() {
       return username;
   }
   public void setUsername(String username) {
       this.username = username;
   }
   public String getQuizName() {
       return quizName;
   }
   public void setQuizName(String quizName) {
       this.quizName = quizName;
   }
   // Calculate percentage
   public double getPercentage() {
       if (totalQuestions == 0) return 0;
       return (double) score / totalQuestions * 100;
   }
}