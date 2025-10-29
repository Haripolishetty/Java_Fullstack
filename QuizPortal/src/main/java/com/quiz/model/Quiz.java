package com.quiz.model;
import java.util.ArrayList;
import java.util.List;
public class Quiz {
   private int quizId;
   private String quizName;
   private String category;
   private int createdBy;
   private List<Question> questions;
   // Default Constructor
   public Quiz() {
       this.questions = new ArrayList<>();
   }
   // Constructor with parameters
   public Quiz(int quizId, String quizName, String category) {
       this.quizId = quizId;
       this.quizName = quizName;
       this.category = category;
       this.questions = new ArrayList<>();
   }
   // Getters and Setters
   public int getQuizId() {
       return quizId;
   }
   public void setQuizId(int quizId) {
       this.quizId = quizId;
   }
   public String getQuizName() {
       return quizName;
   }
   public void setQuizName(String quizName) {
       this.quizName = quizName;
   }
   public String getCategory() {
       return category;
   }
   public void setCategory(String category) {
       this.category = category;
   }
   public int getCreatedBy() {
       return createdBy;
   }
   public void setCreatedBy(int createdBy) {
       this.createdBy = createdBy;
   }
   public List<Question> getQuestions() {
       return questions;
   }
   public void setQuestions(List<Question> questions) {
       this.questions = questions;
   }
   public void addQuestion(Question question) {
       this.questions.add(question);
   }
}