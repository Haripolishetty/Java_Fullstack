package com.quiz.servlets;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.quiz.dao.UserDAO;
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String username = request.getParameter("username");
       String email = request.getParameter("email");
       String password = request.getParameter("password");
       String confirmPassword = request.getParameter("confirmPassword");
       UserDAO userDAO = new UserDAO();
       // Validation
       if (!password.equals(confirmPassword)) {
           request.setAttribute("errorMessage", "Passwords do not match!");
           request.getRequestDispatcher("user/register.jsp").forward(request, response);
           return;
       }
       if (userDAO.emailExists(email)) {
           request.setAttribute("errorMessage", "Email already exists!");
           request.getRequestDispatcher("user/register.jsp").forward(request, response);
           return;
       }
       if (userDAO.usernameExists(username)) {
           request.setAttribute("errorMessage", "Username already exists!");
           request.getRequestDispatcher("user/register.jsp").forward(request, response);
           return;
       }
       // Register user
       boolean registered = userDAO.registerUser(username, email, password);
       if (registered) {
           request.setAttribute("successMessage", "Registration successful! Please login.");
           request.getRequestDispatcher("user/login.jsp").forward(request, response);
       } else {
           request.setAttribute("errorMessage", "Registration failed! Please try again.");
           request.getRequestDispatcher("user/register.jsp").forward(request, response);
       }
   }
}