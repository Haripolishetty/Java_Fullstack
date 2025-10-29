<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.Admin" %>
<%@ page import="com.quiz.model.Quiz" %>
<%@ page import="com.quiz.dao.QuizDAO" %>
<%@ page import="java.util.List" %>
<%
   // Check if admin is logged in
   if(session.getAttribute("admin") == null) {
       response.sendRedirect("admin-login.jsp");
       return;
   }
   Admin admin = (Admin) session.getAttribute("admin");
   QuizDAO quizDAO = new QuizDAO();
   List<Quiz> quizzes = quizDAO.getAllQuizzes();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz List - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<div class="header">
<h1> All Quizzes</h1>
<div class="user-info">
<span>Welcome, <%= admin.getUsername() %>!</span>
<a href="${pageContext.request.contextPath}/AdminLogoutServlet" class="btn btn-secondary" style="background: #c82333;">Logout</a>
</div>
</div>
<div class="nav-menu">
<a href="admin-dashboard.jsp">Dashboard</a>
<a href="add-question.jsp">Add Question</a>
<a href="question-list.jsp">Question List</a>
<a href="create-quiz.jsp">Create Quiz</a>
<a href="quiz-list.jsp">Quiz List</a>
</div>
<div class="card">
<h3>Quiz List (<%= quizzes.size() %>)</h3>
<a href="create-quiz.jsp" class="btn btn-primary">Create New Quiz</a>
<% if(quizzes.isEmpty()) { %>
<p style="margin-top: 20px; text-align: center; color: #666;">No quizzes found. Create a quiz first!</p>
<% } else { %>
<table>
<thead>
<tr>
<th>Quiz ID</th>
<th>Quiz Name</th>
<th>Category</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<% for(Quiz quiz : quizzes) { %>
<tr>
<td><%= quiz.getQuizId() %></td>
<td><%= quiz.getQuizName() %></td>
<td><%= quiz.getCategory() %></td>
<td>
<a href="quiz-details.jsp?quizId=<%= quiz.getQuizId() %>" class="btn btn-primary" style="padding: 6px 12px; font-size: 14px;">View Details</a>
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