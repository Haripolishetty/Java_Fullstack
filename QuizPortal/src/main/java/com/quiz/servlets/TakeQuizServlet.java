package com.quiz.servlets;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.quiz.dao.QuizDAO;
import com.quiz.model.Quiz;
import com.quiz.model.Question;
@WebServlet("/TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       // Check if user is logged in
       HttpSession session = request.getSession(false);
       if (session == null || session.getAttribute("user") == null) {
           response.sendRedirect("user/login.jsp");
           return;
       }
       int quizId = Integer.parseInt(request.getParameter("quizId"));
       int userId = (Integer) session.getAttribute("userId");
       QuizDAO quizDAO = new QuizDAO();
       Quiz quiz = quizDAO.getQuizById(quizId);
       if (quiz == null) {
           response.sendRedirect("user/quiz-list.jsp?error=quiz_not_found");
           return;
       }
       List<Question> questions = quiz.getQuestions();
       int score = 0;
       int totalQuestions = questions.size();
       // Calculate score
       for (Question question : questions) {
           String[] userAnswers = request.getParameterValues("question_" + question.getQuestionId());
           String userAnswer = "";
           if (userAnswers != null && userAnswers.length > 0) {
               java.util.Arrays.sort(userAnswers);
               userAnswer = String.join(",", userAnswers); // "A,B,C"
           }
           String correctAnswer = question.getCorrectOption();
           if (userAnswer.equals(correctAnswer)) {
               score++;
           }
       }
       // Save attempt to database
       int attemptId = quizDAO.saveQuizAttempt(userId, quizId, score, totalQuestions);
       // IMPORTANT: Save individual answers
       if (attemptId > 0) {
           for (Question question : questions) {
               String[] userAnswers = request.getParameterValues("question_" + question.getQuestionId());
               String userAnswer = "";
               if (userAnswers != null && userAnswers.length > 0) {
                   java.util.Arrays.sort(userAnswers);
                   userAnswer = String.join(",", userAnswers);
               }
               boolean isCorrect = userAnswer.equals(question.getCorrectOption());
               // Save to database
               quizDAO.saveUserAnswer(attemptId, question.getQuestionId(), userAnswer, isCorrect);
           }
       }
       // Store results in session
       session.setAttribute("quizResult_score", score);
       session.setAttribute("quizResult_total", totalQuestions);
       session.setAttribute("quizResult_quizName", quiz.getQuizName());
       session.setAttribute("quizResult_quizId", quizId);
       session.setAttribute("quizResult_attemptId", attemptId);
       response.sendRedirect("user/quiz-result.jsp");
   }
}