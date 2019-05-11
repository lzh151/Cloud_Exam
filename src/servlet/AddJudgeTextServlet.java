package servlet;

import domain.Answer_sheet;
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

@WebServlet("/addJudgeTextServlet")
public class AddJudgeTextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        Answer_sheet answer_sheet = new Answer_sheet();
        String exam_name = request.getParameter("exam_name");
        String[] split = exam_name.split(" ");
        exam_name = split[1];
        answer_sheet.setExam_name(exam_name);
        answer_sheet.setStu_id(Integer.parseInt(request.getParameter("stu_id")));
        answer_sheet.setSel_chapter(Integer.parseInt(request.getParameter("chapter")));
        answer_sheet.setSel_que_id(Integer.parseInt(request.getParameter("que_id")));
        answer_sheet.setTeacher_id(Integer.parseInt(request.getParameter("teacher_id")));
        answer_sheet.setRemark(request.getParameter("text"));

        Answer_sheetService service = new Answer_sheetServiceImpl();
        service.AddRemark(answer_sheet);
        List<Answer_sheet> answer_sheets = service.FindAllStudentExam(exam_name,Integer.parseInt(request.getParameter("stu_id")),request.getParameter("stu_name"));
        QuestionService questionService = new QuestionServiceImpl();

        for (Answer_sheet answerSheet : answer_sheets) {
            String correctAnswer = questionService.SearchCorrectAnswer(answerSheet.getSel_chapter(), answerSheet.getSel_chapter(), answerSheet.getTeacher_id()).getCorrect_answer();
            answerSheet.setAnswer_correct(correctAnswer);
        }

        HttpSession session = request.getSession();
        session.setAttribute("ScheduleList",answer_sheets);
        response.sendRedirect(request.getContextPath() + "/HTML/teacher_schedule.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
