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
<title>Question List - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
       function confirmDelete(questionId) {
           if(confirm("Are you sure you want to delete this question?")) {
               window.location.href = "${pageContext.request.contextPath}/DeleteQuestionServlet?id=" + questionId;
           }
       }
</script>
</head>
<body>
<div class="container">
<div class="header">
<h1>📋 Question List</h1>
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
<% String success = request.getParameter("success");
if(success != null) {
   if(success.equals("deleted")) {
%>
<div class="alert alert-success">
       Question deleted successfully!
</div>
<%
   } else if(success.equals("added")) {
%>
<div class="alert alert-success">
       Question added successfully!
</div>
<%
   }
}
%>
<div class="card">
<h3>All Questions (<%= questions.size() %>)</h3>
<a href="add-question.jsp" class="btn btn-primary">Add New Question</a>
<% if(questions.isEmpty()) { %>
<p style="margin-top: 20px; text-align: center; color: #666;">No questions found. Add some questions first!</p>
<% } else { %>
<table>
<thead>
<tr>
<th style="width: 60px;">Sr No.</th>
<th>Question</th>
<th style="width: 100px;">Option A</th>
<th style="width: 100px;">Option B</th>
<th style="width: 100px;">Option C</th>
<th style="width: 100px;">Option D</th>
<th style="width: 80px;">Answer</th>
<th style="width: 100px;">Action</th>
</tr>
</thead>
<tbody>
<%
                       int srNo = 1;
                       for(Question q : questions) {
                       %>
<tr>
<td><%= srNo++ %></td>
<td><%= q.getQuestionText() %></td>
<td><%= q.getOptionA() %></td>
<td><%= q.getOptionB() %></td>
<td><%= q.getOptionC() %></td>
<td><%= q.getOptionD() %></td>
<td><strong><%= q.getCorrectOption() %></strong></td>
<td>
<button onclick="confirmDelete(<%= q.getQuestionId() %>)" class="btn btn-danger" style="padding: 6px 12px; font-size: 14px;">Delete</button>
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