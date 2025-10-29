<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.User" %>
<%
   // Check if user is logged in
   if(session.getAttribute("user") == null) {
       response.sendRedirect("login.jsp");
       return;
   }
   User user = (User) session.getAttribute("user");
   // Get result from session
   Integer score = (Integer) session.getAttribute("quizResult_score");
   Integer total = (Integer) session.getAttribute("quizResult_total");
   String quizName = (String) session.getAttribute("quizResult_quizName");
   Integer quizId = (Integer) session.getAttribute("quizResult_quizId");
   Integer attemptId = (Integer) session.getAttribute("quizResult_attemptId");

   if(score == null || total == null) {
       response.sendRedirect("quiz-list.jsp");
       return;
   }
   double percentage = (double)score / total * 100;
   // Clear result from session
   session.removeAttribute("quizResult_score");
   session.removeAttribute("quizResult_total");
   session.removeAttribute("quizResult_quizName");
   session.removeAttribute("quizResult_quizId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Result - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
       .result-container {
           text-align: center;
           padding: 40px;
       }
       .result-score {
           font-size: 72px;
           font-weight: bold;
           color: #667eea;
           margin: 20px 0;
       }
       .result-message {
           font-size: 24px;
           margin: 20px 0;
       }
       .result-details {
           background: #f8f9fa;
           padding: 30px;
           border-radius: 10px;
           margin: 30px 0;
       }
       .result-details h3 {
           color: #333;
           margin-bottom: 20px;
       }
       .result-stat {
           display: flex;
           justify-content: space-between;
           padding: 15px;
           background: white;
           border-radius: 6px;
           margin-bottom: 10px;
       }
</style>
</head>
<body>
<div class="container">
<div class="header">
<h1>🎉 Quiz Completed!</h1>
<div class="user-info">
<span><%= user.getUsername() %></span>
</div>
</div>
<div class="result-container">
<% if(percentage >= 70) { %>
<h2 style="color: #28a745;">🎊 Congratulations!</h2>
<% } else if(percentage >= 40) { %>
<h2 style="color: #ffc107;">👍 Good Effort!</h2>
<% } else { %>
<h2 style="color: #dc3545;">💪 Keep Practicing!</h2>
<% } %>
<div class="result-score">
<%= String.format("%.1f", percentage) %>%
</div>
<div class="result-details">
<h3>Quiz: <%= quizName %></h3>
<div class="result-stat">
<span><strong>Score:</strong></span>
<span><%= score %> / <%= total %></span>
</div>
<div class="result-stat">
<span><strong>Correct Answers:</strong></span>
<span style="color: #28a745;"><%= score %></span>
</div>
<div class="result-stat">
<span><strong>Wrong Answers:</strong></span>
<span style="color: #dc3545;"><%= (total - score) %></span>
</div>
<div class="result-stat">
<span><strong>Percentage:</strong></span>
<span><%= String.format("%.1f", percentage) %>%</span>
</div>
</div>
<a href="quiz-review.jsp?attemptId=<%= attemptId %>&quizId=<%= quizId %>" class="btn btn-primary">📝 Review Answers</a>
<a href="${pageContext.request.contextPath}/LeaderboardServlet?quizId=<%= quizId %>" class="btn btn-secondary">View Leaderboard</a>
<a href="${pageContext.request.contextPath}/user/quiz-list.jsp" class="btn btn-success">Take Another Quiz</a>
</div>
</div>
</body>
</html>