<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.Admin" %>
<%@ page import="com.quiz.model.Question" %>
<%@ page import="com.quiz.dao.QuestionDAO" %>
<%@ page import="java.util.List" %>
<%
   // Check if admin is logged in
   if(session.getAttribute("admin") == null) {
       response.sendRedirect("admin-login.jsp");
       return;
   }
   Admin admin = (Admin) session.getAttribute("admin");
   QuestionDAO questionDAO = new QuestionDAO();
   List<Question> questions = questionDAO.getAllQuestions();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Quiz - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<div class="header">
<h1>🎯 Create New Quiz</h1>
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
<% if(request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-error">
<%= request.getAttribute("errorMessage") %>
</div>
<% } %>
<div class="card">
<% if(questions.isEmpty()) { %>
<div class="alert alert-info">
                   No questions available! Please <a href="add-question.jsp">add questions</a> first.
</div>
<% } else { %>
<form action="${pageContext.request.contextPath}/CreateQuizServlet" method="post">
<div class="form-group">
<label for="quizName">Quiz Title *</label>
<input type="text" id="quizName" name="quizName" required placeholder="Enter quiz title">
</div>
<div class="form-group">
<label for="category">Category *</label>
<input type="text" id="category" name="category" required placeholder="e.g., Java, Science, General Knowledge">
</div>
<div class="form-group">
<label>Select Questions * (Choose at least one)</label>
<div style="max-height: 400px; overflow-y: auto; border: 2px solid #e0e0e0; border-radius: 6px; padding: 15px;">
<% for(Question q : questions) { %>
<div class="question-checkbox">
<input type="checkbox" id="q<%= q.getQuestionId() %>" name="questions" value="<%= q.getQuestionId() %>">
<label for="q<%= q.getQuestionId() %>">
<strong>Q: </strong><%= q.getQuestionText() %>
</label>
</div>
<% } %>
</div>
</div>
<button type="submit" class="btn btn-success">Create Quiz</button>
<a href="quiz-list.jsp" class="btn btn-secondary">View All Quizzes</a>
</form>
<% } %>
</div>
</div>
</body>
</html>