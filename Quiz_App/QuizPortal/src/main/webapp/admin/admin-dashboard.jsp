<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.Admin" %>
<%@ page import="com.quiz.dao.AdminDAO" %>
<%
   // Check if admin is logged in
   if(session.getAttribute("admin") == null) {
       response.sendRedirect("admin-login.jsp");
       return;
   }
   Admin admin = (Admin) session.getAttribute("admin");
   AdminDAO adminDAO = new AdminDAO();
   int totalQuizzes = adminDAO.getTotalQuizzes();
   int totalQuestions = adminDAO.getTotalQuestions();
   int totalUsers = adminDAO.getTotalUsers();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<div class="header">
<h1>📊 Admin Dashboard</h1>
<div class="user-info">
<span>Welcome, <%= admin.getUsername() %>!</span>
<a href="${pageContext.request.contextPath}/AdminLogoutServlet" class="btn btn-secondary">Logout</a>
</div>
</div>
<div class="nav-menu">
<a href="admin-dashboard.jsp">Dashboard</a>
<a href="add-question.jsp">Add Question</a>
<a href="question-list.jsp">Question List</a>
<a href="create-quiz.jsp">Create Quiz</a>
<a href="quiz-list.jsp">Quiz List</a>
</div>
<h2>Statistics</h2>
<div class="dashboard-cards">
<div class="stat-card">
<h2><%= totalQuizzes %></h2>
<p>Total Quizzes</p>
</div>
<div class="stat-card">
<h2><%= totalQuestions %></h2>
<p>Total Questions</p>
</div>
<div class="stat-card">
<h2><%= totalUsers %></h2>
<p>Total Users</p>
</div>
</div>
<div class="card">
<h3>Quick Actions</h3>
<a href="add-question.jsp" class="btn btn-primary">Add New Question</a>
<a href="create-quiz.jsp" class="btn btn-success">Create New Quiz</a>
<a href="question-list.jsp" class="btn btn-secondary">View All Questions</a>
</div>
</div>
</body>
</html>