<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.QuizAttempt" %>
<%@ page import="java.util.List" %>
<%
   List<QuizAttempt> leaderboard = (List<QuizAttempt>) request.getAttribute("leaderboard");
   Integer quizId = (Integer) request.getAttribute("quizId");
   if(leaderboard == null) {
       response.sendRedirect("quiz-list.jsp");
       return;
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaderboard - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
       .leaderboard-header {
           background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
           color: white;
           padding: 30px;
           border-radius: 10px;
           text-align: center;
           margin-bottom: 30px;
       }
       .rank-badge {
           display: inline-block;
           width: 40px;
           height: 40px;
           line-height: 40px;
           text-align: center;
           border-radius: 50%;
           font-weight: bold;
           margin-right: 15px;
       }
       .rank-1 { background: #FFD700; color: #000; }
       .rank-2 { background: #C0C0C0; color: #000; }
       .rank-3 { background: #CD7F32; color: #fff; }
       .rank-other { background: #e0e0e0; color: #333; }
</style>
</head>
<body>
<div class="container">
<div class="leaderboard-header">
<h1>🏆 Leaderboard</h1>
<% if(!leaderboard.isEmpty()) { %>
<p style="font-size: 20px; margin-top: 10px;"><%= leaderboard.get(0).getQuizName() %></p>
<% } %>
</div>
<% if(leaderboard.isEmpty()) { %>
<div class="alert alert-info">
               No one has attempted this quiz yet. Be the first!
</div>
<% } else { %>
<div class="card">
<h3>Top Performers</h3>
<%
               int rank = 1;
               for(QuizAttempt attempt : leaderboard) {
                   String rankClass = rank <= 3 ? "rank-" + rank : "rank-other";
               %>
<div class="leaderboard-item">
<div style="display: flex; align-items: center;">
<span class="rank-badge <%= rankClass %>"><%= rank %></span>
<div style="flex: 1;">
<strong><%= attempt.getUsername() %></strong>
<p style="color: #666; margin: 5px 0 0 0; font-size: 14px;">
                                   Score: <%= attempt.getScore() %>/<%= attempt.getTotalQuestions() %>
                                   (<%= String.format("%.1f", attempt.getPercentage()) %>%)
</p>
</div>
</div>
</div>
<%
                   rank++;
               }
               %>
</div>
<% } %>
<div style="text-align: center; margin-top: 20px;">
<a href="${pageContext.request.contextPath}/user/quiz-list.jsp" class="btn btn-primary">Back to Quizzes</a>
<% if(quizId != null) { %>
<a href="${pageContext.request.contextPath}/user/quiz-list.jsp?quizId=<%= quizId %>" class="btn btn-success">Take This Quiz</a>
<% } %>
</div>
</div>
</body>
</html>