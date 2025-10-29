<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.Admin" %>
<%
   // Check if admin is logged in
   if(session.getAttribute("admin") == null) {
       response.sendRedirect("admin-login.jsp");
       return;
   }
   Admin admin = (Admin) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Question - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<div class="header">
<h1> Add New Question</h1>
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
<% if(request.getAttribute("successMessage") != null) { %>
<div class="alert alert-success">
<%= request.getAttribute("successMessage") %>
</div>
<% } %>
<% if(request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-error">
<%= request.getAttribute("errorMessage") %>
</div>
<% } %>
<div class="card">
<form action="${pageContext.request.contextPath}/AddQuestionServlet" method="post">
<div class="form-group">
<label for="questionText">Question *</label>
<textarea id="questionText" name="questionText" rows="3" required></textarea>
</div>
<div class="form-group">
<label for="optionA">Option A *</label>
<input type="text" id="optionA" name="optionA" required>
</div>
<div class="form-group">
<label for="optionB">Option B *</label>
<input type="text" id="optionB" name="optionB" required>
</div>
<div class="form-group">
<label for="optionC">Option C *</label>
<input type="text" id="optionC" name="optionC" required>
</div>
<div class="form-group">
<label for="optionD">Option D *</label>
<input type="text" id="optionD" name="optionD" required>
</div>
<div class="form-group">
<label>Correct Answer(s) * (Select one or more)</label>
<div style="background: #f8f9fa; padding: 15px; border-radius: 6px;">
<label style="display: block; margin-bottom: 10px;">
<input type="checkbox" name="correctOption" value="A"> Option A
</label>
<label style="display: block; margin-bottom: 10px;">
<input type="checkbox" name="correctOption" value="B"> Option B
</label>
<label style="display: block; margin-bottom: 10px;">
<input type="checkbox" name="correctOption" value="C"> Option C
</label>
<label style="display: block; margin-bottom: 10px;">
<input type="checkbox" name="correctOption" value="D"> Option D
</label>
</div>
<small style="color: #666;">Select all correct options</small>
</div>
<button type="submit" class="btn btn-primary">Add Question</button>
<a href="question-list.jsp" class="btn btn-secondary">View All Questions</a>
</form>
</div>
</div>
</body>
</html>