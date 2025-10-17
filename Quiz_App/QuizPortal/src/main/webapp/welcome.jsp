<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome - Online Quiz Portal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
       body {
           display: flex;
           flex-direction: column;
           min-height: 100vh;
           margin: 0;
       }
       .welcome-container {
           max-width: 900px;
           margin: 50px auto;
           text-align: center;
           flex: 1;
           padding: 20px;
       }
       .welcome-header {
           background: linear-gradient(135deg, #1e3c72 0%, #2a5298 50%,#7e22ce 100%);
           color: white;
           padding: 60px 40px;
           border-radius: 15px;
           margin-bottom: 50px;
           box-shadow: 0 10px 40px rgba(0, 0, 0, 0.3);
           border: 3px solid rgba(255, 255, 255, 0.2);
       }
       .welcome-header h1 {
           font-size: 56px;
           margin-bottom: 15px;
           text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
           color: white; /* Explicit white color */
       }
       .welcome-header .subtitle {
           font-size: 22px;
           opacity: 0.95;
           color: #f0f0f0; /* Light color for subtitle */
           font-weight: 400;
       }
       .role-selection-title {
           font-size: 32px;
           color: #333;
           margin-bottom: 30px;
           font-weight: 600;
       }
       .role-selection {
           display: grid;
           grid-template-columns: repeat(2, 1fr);
           gap: 30px;
           margin-top: 40px;
       }
       .role-card {
           background: white;
           padding: 50px 30px;
           border-radius: 15px;
           box-shadow: 0 5px 25px rgba(0,0,0,0.1);
           transition: all 0.3s ease;
           cursor: pointer;
           text-decoration: none;
           color: #333;
           display: block;
       }
       .role-card:hover {
           transform: translateY(-10px);
           box-shadow: 0 15px 40px rgba(0,0,0,0.2);
       }
       .role-card.admin-card {
           border-top: 5px solid #dc3545;
       }
       .role-card.user-card {
           border-top: 5px solid #28a745;
       }
       .role-icon {
           font-size: 80px;
           margin-bottom: 20px;
       }
       .role-card h2 {
           font-size: 32px;
           margin-bottom: 15px;
           color: #333;
       }
       .role-card p {
           font-size: 16px;
           color: #666;
           margin-bottom: 25px;
           line-height: 1.6;
       }
       .role-btn {
           display: inline-block;
           padding: 15px 40px;
           border-radius: 8px;
           font-size: 18px;
           font-weight: 600;
           transition: all 0.3s;
           text-decoration: none;
           color: white;
       }
       .admin-btn {
           background: #dc3545;
       }
       .admin-btn:hover {
           background: #c82333;
           box-shadow: 0 5px 15px rgba(220, 53, 69, 0.4);
       }
       .user-btn {
           background: #28a745;
       }
       .user-btn:hover {
           background: #218838;
           box-shadow: 0 5px 15px rgba(40, 167, 69, 0.4);
       }
       /* Footer Styles */
       .footer {
           background: #2c3e50;
           color: white;
           text-align: center;
           padding: 20px;
           margin-top: auto;
           width: 100%;
       }
       .footer p {
           margin: 0;
           font-size: 16px;
       }
       .footer .copyright {
           font-weight: 600;
           color: #3498db;
       }
       /* Animations */
       @keyframes fadeInUp {
           from {
               opacity: 0;
               transform: translateY(30px);
           }
           to {
               opacity: 1;
               transform: translateY(0);
           }
       }
       .role-card {
           animation: fadeInUp 0.6s ease-out;
       }
       .role-card:nth-child(1) {
           animation-delay: 0.1s;
       }
       .role-card:nth-child(2) {
           animation-delay: 0.2s;
       }
       .welcome-header {
           animation: fadeInUp 0.8s ease-out;
       }
       @media (max-width: 768px) {
           .role-selection {
               grid-template-columns: 1fr;
           }
           .welcome-header h1 {
               font-size: 36px;
           }
           .welcome-header .subtitle {
               font-size: 18px;
           }
           .role-selection-title {
               font-size: 24px;
           }
       }
</style>
</head>
<body>
<div class="welcome-container">
<div class="welcome-header">
<h1>🎓 Online Quiz Portal</h1>
<p class="subtitle">Test Your Knowledge, Challenge Yourself, Achieve Excellence</p>
</div>
<h2 class="role-selection-title">Choose Your Role</h2>
<div class="role-selection">
<!-- Admin Card -->
<a href="admin/admin-login.jsp" class="role-card admin-card">
<div class="role-icon">🔐</div>
<h2>Administrator</h2>
<p>
                   Manage quizzes, create questions, monitor user activities,
                   and access comprehensive analytics dashboard.
</p>
<span class="role-btn admin-btn">Login as Admin</span>
</a>
<!-- User Card -->
<a href="user/index.jsp" class="role-card user-card">
<div class="role-icon">👤</div>
<h2>Student / User</h2>
<p>
                   Take quizzes, test your knowledge, compete on leaderboards,
                   and track your progress over time.
</p>
<span class="role-btn user-btn">Continue as User</span>
</a>
</div>
</div>
<!-- Footer -->
<div class="footer">
<p>&copy; 2025 <span class="copyright">@harisaipolishetty</span> | All Rights Reserved</p>
</div>
</body>
</html>