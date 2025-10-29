<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.Admin" %>
<%@ page import="com.quiz.model.Quiz" %>
<%@ page import="com.quiz.model.Question" %>
<%@ page import="com.quiz.dao.QuizDAO" %>
<%
   // Check if admin is logged in
   if(session.getAttribute("admin") == null) {
       response.sendRedirect("admin-login.jsp");
       return;
   }
   Admin admin = (Admin) session.getAttribute("admin");
   int quizId = Integer.parseInt(request.getParameter("quizId"));
   QuizDAO quizDAO = new QuizDAO();
   Quiz quiz = quizDAO.getQuizById(quizId);
   if(quiz == null) {
       response.sendRedirect("quiz-list.jsp");
       return;
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Details - <%= quiz.getQuizName() %></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<div class="header">
<h1> Quiz Details</h1>
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
<% if(request.getParameter("success") != null) { %>
<div class="alert alert-success">
               Quiz created successfully!
</div>
<% } %>
<div class="card">
<h2><%= quiz.getQuizName() %></h2>
<p><strong>Category:</strong> <%= quiz.getCategory() %></p>
<p><strong>Total Questions:</strong> <%= quiz.getQuestions().size() %></p>
<hr>
<h3>Question List</h3>
<%
           int qNo = 1;
           for(Question q : quiz.getQuestions()) {
           %>
<div class="question-card">
<h4>Question <%= qNo++ %>: <%= q.getQuestionText() %></h4>
<div style="margin-left: 20px; margin-top: 10px;">
<p><strong>A)</strong> <%= q.getOptionA() %></p>
<p><strong>B)</strong> <%= q.getOptionB() %></p>
<p><strong>C)</strong> <%= q.getOptionC() %></p>
<p><strong>D)</strong> <%= q.getOptionD() %></p>
<p style="color: green;"><strong>Correct Answer: <%= q.getCorrectOption() %></strong></p>
</div>
</div>
<% } %>
<a href="quiz-list.jsp" class="btn btn-secondary">Back to Quiz List</a>
</div>
</div>
</body>
</html>