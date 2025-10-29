package com.quiz.servlets;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.quiz.dao.QuizDAO;
import com.quiz.model.QuizAttempt;
@WebServlet("/LeaderboardServlet")
public class LeaderboardServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       int quizId = Integer.parseInt(request.getParameter("quizId"));
       QuizDAO quizDAO = new QuizDAO();
       List<QuizAttempt> leaderboard = quizDAO.getLeaderboard(quizId);
       request.setAttribute("leaderboard", leaderboard);
       request.setAttribute("quizId", quizId);
       request.getRequestDispatcher("user/leaderboard.jsp").forward(request, response);
   }
}