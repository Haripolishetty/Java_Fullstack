package com.quiz.servlets;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.quiz.dao.QuestionDAO;
@WebServlet("/DeleteQuestionServlet")
public class DeleteQuestionServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       // Check if admin is logged in
       HttpSession session = request.getSession(false);
       if (session == null || session.getAttribute("admin") == null) {
           response.sendRedirect("admin/admin-login.jsp");
           return;
       }
       int questionId = Integer.parseInt(request.getParameter("id"));
       QuestionDAO questionDAO = new QuestionDAO();
       boolean deleted = questionDAO.deleteQuestion(questionId);
       if (deleted) {
           response.sendRedirect("admin/question-list.jsp?success=deleted");
       } else {
           response.sendRedirect("admin/question-list.jsp?error=delete_failed");
       }
   }
}