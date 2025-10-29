<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
       .hero-section {
           text-align: center;
           padding: 60px 20px;
           background: rgb(128,128,128);
           color: white;
           border-radius: 10px;
           margin-bottom: 30px;
       }
       .hero-section h1 {
           font-size: 48px;
           margin-bottom: 20px;
       }
       .hero-section p {
           font-size: 20px;
           margin-bottom: 30px;
       }
       .features {
           display: grid;
           grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
           gap: 20px;
           margin: 30px 0;
       }
       .feature-card {
           background: #f8f9fa;
           padding: 30px;
           border-radius: 10px;
           text-align: center;
       }
       .feature-card h3 {
           color: #667eea;
           margin-bottom: 15px;
           font-size: 24px;
       }
</style>
</head>
<body>
<div class="container">
<div class="hero-section">
<h1>🎓 Welcome to Online Quiz Portal</h1>
<p>Test your knowledge, compete with others, and climb the leaderboard!</p>
<a href="login.jsp" class="btn btn-primary" style="margin-right: 10px;">Login</a>
<a href="register.jsp" class="btn btn-success">Register</a>
</div>
<div class="features">
<div class="feature-card">
<h3>📝 Multiple Quizzes</h3>
<p>Access a wide variety of quizzes on different topics</p>
</div>
<div class="feature-card">
<h3>🏆 Leaderboard</h3>
<p>Compete with others and see your ranking</p>
</div>
<div class="feature-card">
<h3>✅ Instant Results</h3>
<p>Get immediate feedback on your answers</p>
</div>
<div class="feature-card">
<h3>📊 Track Progress</h3>
<p>View your quiz history and performance</p>
</div>
</div>
<div class="card" style="text-align: center;">
<h3>Ready to Start?</h3>
<p>Join thousands of users testing their knowledge!</p><br>
<a href="register.jsp" class="btn btn-primary">Get Started Now</a>
</div>
</div>
</body>
</html>