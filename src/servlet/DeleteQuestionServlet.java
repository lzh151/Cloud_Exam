package servlet;

import domain.Question;
import service.QuestionService;
import service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteQuestionServlet")
public class DeleteQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        int chapter = Integer.parseInt(request.getParameter("chapter"));
        int que_id = Integer.parseInt(request.getParameter("que_id"));
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));

        Question question = new Question();
        question.setChapter(chapter);
        question.setQue_id(que_id);
        question.setTeacher_id(teacher_id);

        QuestionService service = new QuestionServiceImpl();
        service.DeleteQuestion(question);

        List<Question> questions = service.FindAllQuestionByTeacherId(teacher_id);

        session.setAttribute("question",questions);
        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
