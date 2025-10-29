<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login - Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="auth-container"  style="display: flex !important;
	justify-content: center !important;
	align-items: center !important;
	min-height: 10vh !important;
	padding: 20px">
	
	
  <div style="background: white; padding: 40px; border-radius: 10px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); max-width: 800px; width: 100%; dispay: flex; align-items: center; gap: 40px; margin-top: 10%;">
   
   
     
<!-- Right Side - Form -->
     <div style="flex: 1;">
     
          <h2 style="text-align: center; color: #333; margin-bottom: 30px; font-size: 24px;"> Admin Login</h2>
<% if(request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-error">
<%= request.getAttribute("errorMessage") %>
</div>
<% } %>
<div style="display:flex;">
<img class="dog-img" alt="dog" src="${pageContext.request.contextPath}/admin/dog.jpg"/>
<form style="width:50%;" action="${pageContext.request.contextPath}/AdminLoginServlet" method="post">
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
</div>


<div class="form-footer">
<p style="color:black;">User? <a style="color:blue;" href="${pageContext.request.contextPath}/user/login.jsp">Login here</a></p>
</div>
</div>
</div>
</div>
</body>
</html>