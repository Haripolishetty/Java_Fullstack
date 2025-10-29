package com.quiz.servlets;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.quiz.dao.QuestionDAO;
import com.quiz.model.Question;
@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       // Check if admin is logged in
       HttpSession session = request.getSession(false);
       if (session == null || session.getAttribute("admin") == null) {
           response.sendRedirect("admin/admin-login.jsp");
           return;
       }
       String questionText = request.getParameter("questionText");
       String optionA = request.getParameter("optionA");
       String optionB = request.getParameter("optionB");
       String optionC = request.getParameter("optionC");
       String optionD = request.getParameter("optionD");
       String[] correctOptions = request.getParameterValues("correctOption");
       String correctOption = "";
       if (correctOptions != null && correctOptions.length > 0) {
          correctOption = String.join(",", correctOptions); // Store as "A,B,C"
       }
       Question question = new Question();
       question.setQuestionText(questionText);
       question.setOptionA(optionA);
       question.setOptionB(optionB);
       question.setOptionC(optionC);
       question.setOptionD(optionD);
       question.setCorrectOption(correctOption);
       QuestionDAO questionDAO = new QuestionDAO();
       boolean added = questionDAO.addQuestion(question);
       if (added) {
           response.sendRedirect("admin/question-list.jsp?,success=added");
       } else {
          response.sendRedirect("admin/add-question.jsp?error=failed");
       }
   }
}