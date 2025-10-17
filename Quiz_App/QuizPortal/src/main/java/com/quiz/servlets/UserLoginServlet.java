package com.quiz.servlets;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.quiz.dao.UserDAO;
import com.quiz.model.User;
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String email = request.getParameter("email");
       String password = request.getParameter("password");
       UserDAO userDAO = new UserDAO();
       User user = userDAO.loginUser(email, password);
       if (user != null) {
           // Login successful
           HttpSession session = request.getSession();
           session.setAttribute("user", user);
           session.setAttribute("userId", user.getUserId());
           session.setAttribute("username", user.getUsername());
           response.sendRedirect("user/quiz-list.jsp");
       } else {
           // Login failed
           request.setAttribute("errorMessage", "Invalid email or password!");
           request.getRequestDispatcher("user/login.jsp").forward(request, response);
       }
   }
}