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
<h2>📝 Create Account</h2>
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
<p>Already have an account? <a href="login.jsp">Login here</a></p>
<p><a href="index.jsp">Back to Home</a></p>
</div>
</div>
</body>
</html>