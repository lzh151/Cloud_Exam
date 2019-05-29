package servlet;

import domain.Answer_sheet;
import domain.Question;
import domain.Student;
import service.Answer_sheetService;
import service.QuestionService;
import service.impl.Answer_sheetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/studentIntelligentCreateServlet")
public class StudentIntelligentCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int number = Integer.parseInt(request.getParameter("number"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        String student_name = request.getParameter("student_name");
        Date date = new Date();
        String exam_name = String.valueOf(date);
        exam_name = exam_name.substring(0,exam_name.indexOf(":") + 6);
        exam_name =  exam_name.replaceAll(" ","_");
        exam_name = "试卷集(" + exam_name + ")";
        //System.out.println(exam_name);

        Answer_sheetService service = new Answer_sheetServiceImpl();
        List<Answer_sheet> answer_sheets = service.FindAllByStuId(student_id,number);
        if (number > answer_sheets.size()){
            number = answer_sheets.size();
        }
        for (int i = 0; i < number; i++){
            Answer_sheet answer = new Answer_sheet();
            answer.setStu_id(student_id);
            answer.setStu_name(student_name);
            answer.setSel_chapter(answer_sheets.get(i).getSel_chapter());
            answer.setSel_que_id(answer_sheets.get(i).getSel_que_id());
            answer.setTeacher_id(answer_sheets.get(i).getTeacher_id());
            answer.setExam_name(exam_name);
            service.AddAnswer(answer);
        }

        List<Answer_sheet> sheets = service.FindAllByStudentId(student_id);
        HttpSession session = request.getSession();
        session.setAttribute("examList",sheets);
        response.sendRedirect(request.getContextPath() + "/HTML/student_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
