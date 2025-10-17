<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.User" %>
<%@ page import="com.quiz.model.QuizAttempt" %>
<%@ page import="com.quiz.dao.QuizDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%

    // Check if user is logged in

    if(session.getAttribute("user") == null) {

        response.sendRedirect("login.jsp");

        return;

    }

    User user = (User) session.getAttribute("user");

    int userId = (Integer) session.getAttribute("userId");

    QuizDAO quizDAO = new QuizDAO();

    List<QuizAttempt> attempts = quizDAO.getUserAttempts(userId);

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Attempts - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<div class="header">
<h1>📊 My Quiz Attempts</h1>
<div class="user-info">
<span>Welcome, <%= user.getUsername() %>!</span>
<a href="${pageContext.request.contextPath}/UserLogoutServlet" class="btn btn-secondary">Logout</a>
</div>
</div>
<div class="nav-menu">
<a href="quiz-list.jsp">Available Quizzes</a>
<a href="my-attempts.jsp">My Attempts</a>
</div>
<div class="card">
<h3>Your Quiz History</h3>
<% if(attempts.isEmpty()) { %>
<p style="text-align: center; color: #666; margin: 30px 0;">

                    You haven't attempted any quizzes yet. <a href="quiz-list.jsp">Start now!</a>
</p>
<% } else { %>
<table>
<thead>
<tr>
<th>Quiz Name</th>
<th>Score</th>
<th>Percentage</th>
<th>Date & Time</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<% for(QuizAttempt attempt : attempts) { %>
<tr>
<td><%= attempt.getQuizName() %></td>
<td><%= attempt.getScore() %> / <%= attempt.getTotalQuestions() %></td>
<td>
<% 

                                double percentage = attempt.getPercentage();

                                String color = percentage >= 70 ? "#28a745" : (percentage >= 40 ? "#ffc107" : "#dc3545");

                                %>
<span style="color: <%= color %>; font-weight: bold;">
<%= String.format("%.1f", percentage) %>%
</span>
</td>
<td><%= sdf.format(attempt.getAttemptedAt()) %></td>

<td>
<a href="quiz-review.jsp?attemptId=<%= attempt.getAttemptId() %>&quizId=<%= attempt.getQuizId() %>"
      class="btn btn-primary" style="padding: 6px 12px; font-size: 14px; margin-right: 5px;">📝 Review</a>
<a href="${pageContext.request.contextPath}/LeaderboardServlet?quizId=<%= attempt.getQuizId() %>"
      class="btn btn-secondary" style="padding: 6px 12px; font-size: 14px;">🏆 Leaderboard</a>
</td>

</tr>
<% } %>
</tbody>
</table>
<% } %>
</div>
</div>
</body>
</html>
 