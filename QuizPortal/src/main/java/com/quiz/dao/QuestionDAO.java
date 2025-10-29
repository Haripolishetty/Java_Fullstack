package com.quiz.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.quiz.model.Question;
public class QuestionDAO {
   // Add a new question
   public boolean addQuestion(Question question) {
       Connection conn = null;
       PreparedStatement ps = null;
       boolean added = false;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
           ps = conn.prepareStatement(query);
           ps.setString(1, question.getQuestionText());
           ps.setString(2, question.getOptionA());
           ps.setString(3, question.getOptionB());
           ps.setString(4, question.getOptionC());
           ps.setString(5, question.getOptionD());
           ps.setString(6, question.getCorrectOption());
           int rows = ps.executeUpdate();
           added = (rows > 0);
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
       return added;
   }
   // Get all questions
   public List<Question> getAllQuestions() {
       List<Question> questions = new ArrayList<>();
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM questions ORDER BY question_id DESC";
           ps = conn.prepareStatement(query);
           rs = ps.executeQuery();
           while (rs.next()) {
               Question question = new Question();
               question.setQuestionId(rs.getInt("question_id"));
               question.setQuestionText(rs.getString("question_text"));
               question.setOptionA(rs.getString("option_a"));
               question.setOptionB(rs.getString("option_b"));
               question.setOptionC(rs.getString("option_c"));
               question.setOptionD(rs.getString("option_d"));
               question.setCorrectOption(rs.getString("correct_option"));
               questions.add(question);
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
       return questions;
   }
   // Get question by ID
   public Question getQuestionById(int questionId) {
       Question question = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM questions WHERE question_id = ?";
           ps = conn.prepareStatement(query);
           ps.setInt(1, questionId);
           rs = ps.executeQuery();
           if (rs.next()) {
               question = new Question();
               question.setQuestionId(rs.getInt("question_id"));
               question.setQuestionText(rs.getString("question_text"));
               question.setOptionA(rs.getString("option_a"));
               question.setOptionB(rs.getString("option_b"));
               question.setOptionC(rs.getString("option_c"));
               question.setOptionD(rs.getString("option_d"));
               question.setCorrectOption(rs.getString("correct_option"));
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
       return question;
   }
   // Delete question
   public boolean deleteQuestion(int questionId) {
       Connection conn = null;
       PreparedStatement ps = null;
       boolean deleted = false;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "DELETE FROM questions WHERE question_id = ?";
           ps = conn.prepareStatement(query);
           ps.setInt(1, questionId);
           int rows = ps.executeUpdate();
           deleted = (rows > 0);
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
       return deleted;
   }
   // Update question
   public boolean updateQuestion(Question question) {
       Connection conn = null;
       PreparedStatement ps = null;
       boolean updated = false;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "UPDATE questions SET question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_option = ? WHERE question_id = ?";
           ps = conn.prepareStatement(query);
           ps.setString(1, question.getQuestionText());
           ps.setString(2, question.getOptionA());
           ps.setString(3, question.getOptionB());
           ps.setString(4, question.getOptionC());
           ps.setString(5, question.getOptionD());
           ps.setString(6, question.getCorrectOption());
           ps.setInt(7, question.getQuestionId());
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
}