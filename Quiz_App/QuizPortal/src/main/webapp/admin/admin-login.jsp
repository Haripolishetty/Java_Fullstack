<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="auth-container">
<h2>🔐 Admin Login</h2>
<% if(request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-error">
<%= request.getAttribute("errorMessage") %>
</div>
<% } %>
<form action="${pageContext.request.contextPath}/AdminLoginServlet" method="post">
<div class="form-group">
<label for="username">Username</label>
<input type="text" id="username" name="username" required>
</div>
<div class="form-group">
<label for="password">Password</label>
<input type="password" id="password" name="password" required>
</div>
<button type="submit" class="btn btn-primary" style="width: 100%;">Login</button>
</form>
<div class="form-footer">
<p>User? <a href="${pageContext.request.contextPath}/user/login.jsp">Login here</a></p>
</div>
</div>
</body>
</html>