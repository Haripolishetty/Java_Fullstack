<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.User" %>
<%@ page import="com.quiz.model.Quiz" %>
<%@ page import="com.quiz.dao.QuizDAO" %>
<%@ page import="java.util.List" %>
<%
   // Check if user is logged in
   if(session.getAttribute("user") == null) {
       response.sendRedirect("login.jsp");
       return;
   }
   User user = (User) session.getAttribute("user");
   QuizDAO quizDAO = new QuizDAO();
   List<Quiz> quizzes = quizDAO.getAllQuizzes();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Quizzes - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<div class="header">
<h1>📚 Available Quizzes</h1>
<div class="user-info">
<span>Welcome, <%= user.getUsername() %>!</span>
<a href="${pageContext.request.contextPath}/UserLogoutServlet" class="btn btn-secondary">Logout</a>
</div>
</div>
<div class="nav-menu">
<a href="quiz-list.jsp">Available Quizzes</a>
<a href="my-attempts.jsp">My Attempts</a>
</div>
<% if(quizzes.isEmpty()) { %>
<div class="alert alert-info">
               No quizzes available at the moment. Please check back later!
</div>
<% } else { %>
<div class="dashboard-cards">
<% for(Quiz quiz : quizzes) {
                   Quiz fullQuiz = quizDAO.getQuizById(quiz.getQuizId());
               %>
<div class="card">
<h3><%= quiz.getQuizName() %></h3>
<p><strong>Category:</strong> <%= quiz.getCategory() %></p>
<p><strong>Questions:</strong> <%= fullQuiz.getQuestions().size() %></p><br>
<a href="take-quiz.jsp?quizId=<%= quiz.getQuizId() %>" class="btn btn-primary">Take Quiz</a><br><br>
<a href="${pageContext.request.contextPath}/LeaderboardServlet?quizId=<%= quiz.getQuizId() %>" class="btn btn-secondary">View Leaderboard</a>
</div>
<% } %>
</div>
<% } %>
</div>
</body>
</html>