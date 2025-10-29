<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.User" %>
<%@ page import="com.quiz.model.Quiz" %>
<%@ page import="com.quiz.model.Question" %>
<%@ page import="com.quiz.dao.QuizDAO" %>
<%@ page import="java.sql.*" %>
<%
   if(session.getAttribute("user") == null) {
       response.sendRedirect("login.jsp");
       return;
   }
   User user = (User) session.getAttribute("user");
   int attemptId = Integer.parseInt(request.getParameter("attemptId"));
   int quizId = Integer.parseInt(request.getParameter("quizId"));
   QuizDAO quizDAO = new QuizDAO();
   Quiz quiz = quizDAO.getQuizById(quizId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Answers - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
       .wrong-answer {
           background: #f8d7da !important;
           border-left: 5px solid #dc3545 !important;
           margin-bottom: 25px;
       }
       .answer-badge {
           display: inline-block;
           padding: 6px 16px;
           border-radius: 20px;
           font-weight: 600;
           font-size: 14px;
           background: #dc3545;
           color: white;
       }
       .answer-detail {
           margin-top: 15px;
           padding: 15px;
           background: rgba(255,255,255,0.7);
           border-radius: 8px;
           border-top: 2px solid #e0e0e0;
       }
       .answer-detail p {
           margin: 8px 0;
       }
       .user-answer {
           color: #dc3545;
           font-weight: 600;
           font-size: 16px;
       }
       .correct-answer-text {
           color: #28a745;
           font-weight: 600;
           font-size: 16px;
       }
       .no-mistakes {
           background: #d4edda;
           padding: 40px;
           border-radius: 15px;
           text-align: center;
           border: 3px solid #28a745;
           margin: 30px 0;
       }
       .no-mistakes h2 {
           color: #28a745;
           font-size: 36px;
           margin-bottom: 15px;
       }
       .no-mistakes p {
           font-size: 18px;
           color: #155724;
       }
</style>
</head>
<body>
<div class="container">
<div class="header">
<h1>📋 Review Incorrect Answers</h1>
<div class="user-info">
<span><%= user.getUsername() %></span>
</div>
</div>
<h2 style="text-align: center; color: #667eea; margin-bottom: 30px;"><%= quiz.getQuizName() %></h2>
<%
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       int wrongCount = 0;
       try {
           conn = com.quiz.dao.DatabaseConnection.getConnection();
           // Get ONLY wrong answers
           String query = "SELECT ua.*, q.* FROM user_answers ua " +
                         "INNER JOIN questions q ON ua.question_id = q.question_id " +
                         "WHERE ua.attempt_id = ? AND ua.is_correct = FALSE " +
                         "ORDER BY ua.answer_id";
           ps = conn.prepareStatement(query);
           ps.setInt(1, attemptId);
           rs = ps.executeQuery();
           int qNo = 1;
           while(rs.next()) {
               wrongCount++;
               String userAnswer = rs.getString("selected_option");
               String correctAnswer = rs.getString("correct_option");
       %>
<div class="question-card wrong-answer">
<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
<h4 style="margin: 0; flex: 1;">Question <%= qNo++ %>: <%= rs.getString("question_text") %></h4>
<span class="answer-badge">✗ Wrong</span>
</div>
<div style="margin-left: 20px; margin-top: 15px;">
<p style="margin: 8px 0;"><strong>A)</strong> <%= rs.getString("option_a") %></p>
<p style="margin: 8px 0;"><strong>B)</strong> <%= rs.getString("option_b") %></p>
<p style="margin: 8px 0;"><strong>C)</strong> <%= rs.getString("option_c") %></p>
<p style="margin: 8px 0;"><strong>D)</strong> <%= rs.getString("option_d") %></p>
</div>
<div class="answer-detail">
<p><strong>Your Answer:</strong>
<span class="user-answer">
<%= (userAnswer != null && !userAnswer.isEmpty()) ? userAnswer : "Not Answered" %>
</span>
</p>
<p><strong>Correct Answer:</strong>
<span class="correct-answer-text"><%= correctAnswer %></span>
</p>
</div>
</div>
<%
           }
           // If no wrong answers, show congratulations message
           if(wrongCount == 0) {
       %>
<div class="no-mistakes">
<h2>🎉 Perfect Score!</h2>
<p style="font-size: 20px; margin: 20px 0;">Congratulations! You answered all questions correctly!</p>
<p>There are no incorrect answers to review.</p>
</div>
<%
           } else {
       %>
<div style="background: #fff3cd; padding: 15px; border-radius: 8px; text-align: center; margin-top: 20px;">
<p style="margin: 0; font-size: 16px; color: #856404;">
<strong>You got <%= wrongCount %> question(s) wrong.</strong> Review them carefully!
</p>
</div>
<%
           }
       } catch(Exception e) {
       %>
<div class="alert alert-error">
<p><strong>Error loading answers:</strong> <%= e.getMessage() %></p>
</div>
<%
           e.printStackTrace();
       } finally {
           if(rs != null) try { rs.close(); } catch(Exception e) {}
           if(ps != null) try { ps.close(); } catch(Exception e) {}
           if(conn != null) try { conn.close(); } catch(Exception e) {}
       }
       %>
<div style="text-align: center; margin-top: 30px; padding-top: 30px; border-top: 2px solid #e0e0e0;">
<a href="my-attempts.jsp" class="btn btn-secondary">← Back to My Attempts</a>
<a href="quiz-list.jsp" class="btn btn-success">📝 Take Another Quiz</a>
<a href="${pageContext.request.contextPath}/LeaderboardServlet?quizId=<%= quizId %>" class="btn btn-primary">🏆 View Leaderboard</a>
</div>
</div>
</body>
</html>