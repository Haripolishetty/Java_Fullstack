<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="auth-container">
<h2>🔐 User Login</h2>
<% if(request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-error">
<%= request.getAttribute("errorMessage") %>
</div>
<% } %>
<% if(request.getAttribute("successMessage") != null) { %>
<div class="alert alert-success">
<%= request.getAttribute("successMessage") %>
</div>
<% } %>
<form action="${pageContext.request.contextPath}/UserLoginServlet" method="post">
<div class="form-group">
<label for="email">Email</label>
<input type="email" id="email" name="email" required>
</div>
<div class="form-group">
<label for="password">Password</label>
<input type="password" id="password" name="password" required>
</div>
<button type="submit" class="btn btn-primary" style="width: 100%;">Login</button>
</form>
<div class="form-footer">
<p>Don't have an account? <a href="register.jsp">Register here</a></p>
<p><a href="index.jsp">Back to Home</a></p>
</div>
</div>
</body>
</html>