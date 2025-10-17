<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quiz.model.User" %>
<%@ page import="com.quiz.model.Quiz" %>
<%@ page import="com.quiz.model.Question" %>
<%@ page import="com.quiz.dao.QuizDAO" %>
<%
   // Check if user is logged in
   if(session.getAttribute("user") == null) {
       response.sendRedirect("login.jsp");
       return;
   }
   User user = (User) session.getAttribute("user");
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
<title><%= quiz.getQuizName() %> - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
       function validateForm() {
           return confirm("Are you sure you want to submit the quiz?");
       }
</script>
</head>
<body>
<div class="container">
<div class="header">
<h1>📝 <%= quiz.getQuizName() %></h1>
<div class="user-info">
<span><%= user.getUsername() %></span>
</div>
</div>
<div class="alert alert-info">
<strong>Instructions:</strong>
<ul style="margin-left: 20px; margin-top: 10px;">
<li>Total Questions: <%= quiz.getQuestions().size() %></li>
<li>Answer all questions</li>
<li>Click Submit when you're done</li>
<li>You will see your score immediately after submission</li>
</ul>
</div>
<form action="${pageContext.request.contextPath}/TakeQuizServlet" method="post" onsubmit="return validateForm()">
<input type="hidden" name="quizId" value="<%= quizId %>">
<%
           int qNo = 1;
           for(Question q : quiz.getQuestions()) {
           %>
<div class="question-card">
<h4>Question <%= qNo++ %>: <%= q.getQuestionText() %></h4>
<div class="options">
<div style="margin-bottom: 10px;">
<label style="display: flex; align-items: center; padding: 12px; background: white; border-radius: 6px; cursor: pointer; border: 2px solid #e0e0e0;">
<input type="checkbox" name="question_<%= q.getQuestionId() %>" value="A" style="margin-right: 12px; width: 18px; height: 18px; cursor: pointer;">
<span style="flex: 1;">A) <%= q.getOptionA() %></span>
</label>
</div>
<div style="margin-bottom: 10px;">
<label style="display: flex; align-items: center; padding: 12px; background: white; border-radius: 6px; cursor: pointer; border: 2px solid #e0e0e0;">
<input type="checkbox" name="question_<%= q.getQuestionId() %>" value="B" style="margin-right: 12px; width: 18px; height: 18px; cursor: pointer;">
<span style="flex: 1;">B) <%= q.getOptionB() %></span>
</label>
</div>
<div style="margin-bottom: 10px;">
<label style="display: flex; align-items: center; padding: 12px; background: white; border-radius: 6px; cursor: pointer; border: 2px solid #e0e0e0;">
<input type="checkbox" name="question_<%= q.getQuestionId() %>" value="C" style="margin-right: 12px; width: 18px; height: 18px; cursor: pointer;">
<span style="flex: 1;">C) <%= q.getOptionC() %></span>
</label>
</div>
<div style="margin-bottom: 10px;">
<label style="display: flex; align-items: center; padding: 12px; background: white; border-radius: 6px; cursor: pointer; border: 2px solid #e0e0e0;">
<input type="checkbox" name="question_<%= q.getQuestionId() %>" value="D" style="margin-right: 12px; width: 18px; height: 18px; cursor: pointer;">
<span style="flex: 1;">D) <%= q.getOptionD() %></span>
</label>
</div>
</div>
</div>
<% } %>
<button type="submit" class="btn btn-success">Submit Quiz</button>
<a href="quiz-list.jsp" class="btn btn-secondary">Cancel</a>
</form>
</div>
</body>
</html>