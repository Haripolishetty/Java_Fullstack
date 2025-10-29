<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="auth-container">
<div style="background: white; padding: 40px; border-radius: 10px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); max-width: 800px; width: 100%; dispay: flex; align-items: center; gap: 40px;">

<h2 style = "color:black;"> Create Account</h2>
<% if(request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-error">
<%= request.getAttribute("errorMessage") %>
</div>
<% } %>
<form action="${pageContext.request.contextPath}/UserRegisterServlet" method="post">
<div class="form-group">
<label for="username">Username *</label>
<input type="text" id="username" name="username" required>
</div>
<div class="form-group">
<label for="email">Email *</label>
<input type="email" id="email" name="email" required>
</div>
<div class="form-group">
<label for="password">Password *</label>
<input type="password" id="password" name="password" required>
</div>
<div class="form-group">
<label for="confirmPassword">Confirm Password *</label>
<input type="password" id="confirmPassword" name="confirmPassword" required>
</div>
<button type="submit" class="btn btn-success" style="width: 100%;">Register</button>
</form>
<div class="form-footer">
<p style = "color:black;">Already have an account? &nbsp; <a style = "color:blue;" href="login.jsp">Login here</a></p>
<p><a style = "color:blue;" href="index.jsp">Back to Home</a></p>
</div>
</div>
</div>
</body>
</html>