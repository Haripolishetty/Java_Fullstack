package com.quiz.servlets;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.quiz.dao.AdminDAO;
import com.quiz.model.Admin;
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       AdminDAO adminDAO = new AdminDAO();
       Admin admin = adminDAO.loginAdmin(username, password);
       if (admin != null) {
           // Login successful
           HttpSession session = request.getSession();
           session.setAttribute("admin", admin);
           session.setAttribute("adminId", admin.getAdminId());
           session.setAttribute("adminUsername", admin.getUsername());
           response.sendRedirect("admin/admin-dashboard.jsp");
       } else {
           // Login failed
           request.setAttribute("errorMessage", "Invalid username or password!");
           request.getRequestDispatcher("admin/admin-login.jsp").forward(request, response);
       }
   }
}