package com.quiz.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.quiz.model.Quiz;
import com.quiz.model.Question;
import com.quiz.model.QuizAttempt;
public class QuizDAO {
   // Create a new quiz
   public int createQuiz(String quizName, String category, int adminId, List<Integer> questionIds) {
       Connection conn = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       ResultSet rs = null;
       int quizId = -1;
       try {
           conn = DatabaseConnection.getConnection();
           conn.setAutoCommit(false); // Start transaction
           // Insert quiz
           String query1 = "INSERT INTO quiz (quiz_name, category, created_by) VALUES (?, ?, ?)";
           ps1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
           ps1.setString(1, quizName);
           ps1.setString(2, category);
           ps1.setInt(3, adminId);
           ps1.executeUpdate();
           rs = ps1.getGeneratedKeys();
           if (rs.next()) {
               quizId = rs.getInt(1);
           }
           // Insert quiz questions mapping
           String query2 = "INSERT INTO quiz_questions (quiz_id, question_id) VALUES (?, ?)";
           ps2 = conn.prepareStatement(query2);
           for (Integer questionId : questionIds) {
               ps2.setInt(1, quizId);
               ps2.setInt(2, questionId);
               ps2.addBatch();
           }
           ps2.executeBatch();
           conn.commit(); // Commit transaction
       } catch (SQLException e) {
           if (conn != null) {
               try {
                   conn.rollback();
               } catch (SQLException ex) {
                   ex.printStackTrace();
               }
           }
           e.printStackTrace();
       } finally {
           try {
               if (rs != null) rs.close();
               if (ps1 != null) ps1.close();
               if (ps2 != null) ps2.close();
               if (conn != null) {
                   conn.setAutoCommit(true);
                   conn.close();
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return quizId;
   }
   // Get all quizzes
   public List<Quiz> getAllQuizzes() {
       List<Quiz> quizzes = new ArrayList<>();
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT * FROM quiz ORDER BY quiz_id DESC";
           ps = conn.prepareStatement(query);
           rs = ps.executeQuery();
           while (rs.next()) {
               Quiz quiz = new Quiz();
               quiz.setQuizId(rs.getInt("quiz_id"));
               quiz.setQuizName(rs.getString("quiz_name"));
               quiz.setCategory(rs.getString("category"));
               quiz.setCreatedBy(rs.getInt("created_by"));
               quizzes.add(quiz);
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
       return quizzes;
   }
   // Get quiz by ID with questions
   public Quiz getQuizById(int quizId) {
       Quiz quiz = null;
       Connection conn = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       ResultSet rs1 = null;
       ResultSet rs2 = null;
       try {
           conn = DatabaseConnection.getConnection();
           // Get quiz details
           String query1 = "SELECT * FROM quiz WHERE quiz_id = ?";
           ps1 = conn.prepareStatement(query1);
           ps1.setInt(1, quizId);
           rs1 = ps1.executeQuery();
           if (rs1.next()) {
               quiz = new Quiz();
               quiz.setQuizId(rs1.getInt("quiz_id"));
               quiz.setQuizName(rs1.getString("quiz_name"));
               quiz.setCategory(rs1.getString("category"));
               quiz.setCreatedBy(rs1.getInt("created_by"));
               // Get quiz questions
               String query2 = "SELECT q.* FROM questions q " +
                              "INNER JOIN quiz_questions qq ON q.question_id = qq.question_id " +
                              "WHERE qq.quiz_id = ?";
               ps2 = conn.prepareStatement(query2);
               ps2.setInt(1, quizId);
               rs2 = ps2.executeQuery();
               while (rs2.next()) {
                   Question question = new Question();
                   question.setQuestionId(rs2.getInt("question_id"));
                   question.setQuestionText(rs2.getString("question_text"));
                   question.setOptionA(rs2.getString("option_a"));
                   question.setOptionB(rs2.getString("option_b"));
                   question.setOptionC(rs2.getString("option_c"));
                   question.setOptionD(rs2.getString("option_d"));
                   question.setCorrectOption(rs2.getString("correct_option"));
                   quiz.addQuestion(question);
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
               if (rs1 != null) rs1.close();
               if (rs2 != null) rs2.close();
               if (ps1 != null) ps1.close();
               if (ps2 != null) ps2.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return quiz;
   }
   // Save user quiz attempt
   public int saveQuizAttempt(int userId, int quizId, int score, int totalQuestions) {
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       int attemptId = -1;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "INSERT INTO user_quiz_attempts (user_id, quiz_id, score, total_questions) VALUES (?, ?, ?, ?)";
           ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           ps.setInt(1, userId);
           ps.setInt(2, quizId);
           ps.setInt(3, score);
           ps.setInt(4, totalQuestions);
           ps.executeUpdate();
           rs = ps.getGeneratedKeys();
           if (rs.next()) {
               attemptId = rs.getInt(1);
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
       return attemptId;
   }
   // Save user answers
   public void saveUserAnswer(int attemptId, int questionId, String selectedOption, boolean isCorrect) {
       Connection conn = null;
       PreparedStatement ps = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "INSERT INTO user_answers (attempt_id, question_id, selected_option, is_correct) VALUES (?, ?, ?, ?)";
           ps = conn.prepareStatement(query);
           ps.setInt(1, attemptId);
           ps.setInt(2, questionId);
           ps.setString(3, selectedOption);
           ps.setBoolean(4, isCorrect);
           ps.executeUpdate();
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
   }
   // Get leaderboard for a quiz
   public List<QuizAttempt> getLeaderboard(int quizId) {
       List<QuizAttempt> leaderboard = new ArrayList<>();
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT uqa.*, u.username, q.quiz_name " +
                         "FROM user_quiz_attempts uqa " +
                         "INNER JOIN users u ON uqa.user_id = u.user_id " +
                         "INNER JOIN quiz q ON uqa.quiz_id = q.quiz_id " +
                         "WHERE uqa.quiz_id = ? " +
                         "ORDER BY uqa.score DESC, uqa.attempted_at ASC";
           ps = conn.prepareStatement(query);
           ps.setInt(1, quizId);
           rs = ps.executeQuery();
           while (rs.next()) {
               QuizAttempt attempt = new QuizAttempt();
               attempt.setAttemptId(rs.getInt("attempt_id"));
               attempt.setUserId(rs.getInt("user_id"));
               attempt.setQuizId(rs.getInt("quiz_id"));
               attempt.setScore(rs.getInt("score"));
               attempt.setTotalQuestions(rs.getInt("total_questions"));
               attempt.setAttemptedAt(rs.getTimestamp("attempted_at"));
               attempt.setUsername(rs.getString("username"));
               attempt.setQuizName(rs.getString("quiz_name"));
               leaderboard.add(attempt);
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
       return leaderboard;
   }
   // Get user's quiz attempts
   public List<QuizAttempt> getUserAttempts(int userId) {
       List<QuizAttempt> attempts = new ArrayList<>();
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
           conn = DatabaseConnection.getConnection();
           String query = "SELECT uqa.*, q.quiz_name " +
                         "FROM user_quiz_attempts uqa " +
                         "INNER JOIN quiz q ON uqa.quiz_id = q.quiz_id " +
                         "WHERE uqa.user_id = ? " +
                         "ORDER BY uqa.attempted_at DESC";
           ps = conn.prepareStatement(query);
           ps.setInt(1, userId);
           rs = ps.executeQuery();
           while (rs.next()) {
               QuizAttempt attempt = new QuizAttempt();
               attempt.setAttemptId(rs.getInt("attempt_id"));
               attempt.setUserId(rs.getInt("user_id"));
               attempt.setQuizId(rs.getInt("quiz_id"));
               attempt.setScore(rs.getInt("score"));
               attempt.setTotalQuestions(rs.getInt("total_questions"));
               attempt.setAttemptedAt(rs.getTimestamp("attempted_at"));
               attempt.setQuizName(rs.getString("quiz_name"));
               attempts.add(attempt);
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
       return attempts;
   }
}