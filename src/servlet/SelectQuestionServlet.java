package servlet;

import domain.Answer_sheet;
import domain.Question;
import domain.Student;
import service.Answer_sheetService;
import service.QuestionService;
import service.StudentService;
import service.impl.Answer_sheetServiceImpl;
import service.impl.QuestionServiceImpl;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectQuestionServlet")
public class SelectQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int chapter = Integer.parseInt(request.getParameter("chapter"));
        String type = request.getParameter("type");
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
        HttpSession session = request.getSession();

        QuestionService service  = new QuestionServiceImpl();
        List<Question> questions = service.SelectQuestion(chapter, type, teacher_id);

        session.setAttribute("question",questions);

        StudentService studentService = new StudentServiceImpl();
        List<Student> students = studentService.findByTeacherId(teacher_id);

        Answer_sheetService answer_sheetService = new Answer_sheetServiceImpl();
        List<Answer_sheet> answer_sheets = answer_sheetService.FindAllByTeaId(teacher_id);

        session.setAttribute("student",students);
        session.setAttribute("Schedule",answer_sheets);
        response.sendRedirect("/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
