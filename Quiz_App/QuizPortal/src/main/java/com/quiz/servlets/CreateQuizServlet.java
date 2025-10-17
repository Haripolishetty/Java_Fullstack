package com.quiz.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.quiz.dao.QuizDAO;
@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       // Check if admin is logged in
       HttpSession session = request.getSession(false);
       if (session == null || session.getAttribute("admin") == null) {
           response.sendRedirect("admin/admin-login.jsp");
           return;
       }
       String quizName = request.getParameter("quizName");
       String category = request.getParameter("category");
       String[] selectedQuestions = request.getParameterValues("questions");
       if (selectedQuestions == null || selectedQuestions.length == 0) {
           request.setAttribute("errorMessage", "Please select at least one question!");
           request.getRequestDispatcher("admin/create-quiz.jsp").forward(request, response);
           return;
       }
       // Convert String array to Integer List
       List<Integer> questionIds = new ArrayList<>();
       for (String qId : selectedQuestions) {
           questionIds.add(Integer.parseInt(qId));
       }
       int adminId = (Integer) session.getAttribute("adminId");
       QuizDAO quizDAO = new QuizDAO();
       int quizId = quizDAO.createQuiz(quizName, category, adminId, questionIds);
       if (quizId > 0) {
           response.sendRedirect("admin/quiz-details.jsp?quizId=" + quizId + "&success=created");
       } else {
           request.setAttribute("errorMessage", "Failed to create quiz!");
           request.getRequestDispatcher("admin/create-quiz.jsp").forward(request, response);
       }
   }
}