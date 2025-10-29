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
@WebServlet("/UpdateAdminProfileServlet")
public class UpdateAdminProfileServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       // Check if admin is logged in
       HttpSession session = request.getSession(false);
       if (session == null || session.getAttribute("admin") == null) {
           response.sendRedirect("admin/admin-login.jsp");
           return;
       }
       int adminId = (Integer) session.getAttribute("adminId");
       String email = request.getParameter("email");
       String newPassword = request.getParameter("newPassword");
       String confirmPassword = request.getParameter("confirmPassword");
       if (!newPassword.equals(confirmPassword)) {
           request.setAttribute("errorMessage", "Passwords do not match!");
           request.getRequestDispatcher("admin/admin-profile.jsp").forward(request, response);
           return;
       }
       AdminDAO adminDAO = new AdminDAO();
       boolean updated = adminDAO.updateAdminProfile(adminId, email, newPassword);
       if (updated) {
           // Update session
           Admin admin = adminDAO.getAdminById(adminId);
           session.setAttribute("admin", admin);
           request.setAttribute("successMessage", "Profile updated successfully!");
       } else {
           request.setAttribute("errorMessage", "Failed to update profile!");
       }
       request.getRequestDispatcher("admin/admin-profile.jsp").forward(request, response);
   }
}