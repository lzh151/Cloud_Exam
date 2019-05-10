package servlet;

import domain.Answer_sheet;
import domain.Question;
import service.Answer_sheetService;
import service.QuestionService;
import service.impl.Answer_sheetServiceImpl;
import service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacherSelectScheduleServlet")
public class TeacherSelectScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String exam_name = request.getParameter("exam_name");
        int stu_id = Integer.parseInt(request.getParameter("stu_id"));
        String stu_name = request.getParameter("stu_name");
        String[] split = stu_name.split(" ");
        stu_name = split[1];

        Answer_sheetService service = new Answer_sheetServiceImpl();

        List<Answer_sheet> answer_sheets = service.FindAllStudentExam(exam_name, stu_id, stu_name);

        QuestionService questionService = new QuestionServiceImpl();
        for (int i = 0; i < answer_sheets.size(); i++) {
            String correctAnswer = questionService.SearchCorrectAnswer(answer_sheets.get(i).getSel_chapter(), answer_sheets.get(i).getSel_chapter(), answer_sheets.get(i).getTeacher_id()).getCorrect_answer();
            answer_sheets.get(i).setAnswer_correct(correctAnswer);
        }

        HttpSession session = request.getSession();
        session.setAttribute("stu_id",stu_id);
        session.setAttribute("stu_name",stu_name);
        session.setAttribute("ScheduleList",answer_sheets);
        response.sendRedirect("/HTML/teacher_schedule.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
