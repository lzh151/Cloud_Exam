package servlet;

import domain.Answer_sheet;
import domain.Question;
import domain.Student;
import service.Answer_sheetService;
import service.impl.Answer_sheetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Queue;

@WebServlet("/createExamServlet")
public class CreateExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String exam_name = request.getParameter("exam_name");
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        ArrayList<Question> questions = new ArrayList<>();
        String[] que_info = request.getParameterValues("que_info");
        for (int i = 0; i < que_info.length; i++) {
            String[] split = que_info[i].split(",");
            Question question = new Question();
            question.setChapter(Integer.parseInt(split[0]));
            question.setQue_id(Integer.parseInt(split[1]));
            questions.add(question);
        }

        ArrayList<Student> students = new ArrayList<>();
        String[] stu_info = request.getParameterValues("stu_info");
        for (int i = 0; i < stu_info.length; i++) {
            String[] split = stu_info[i].split(",");
            Student student = new Student();
            student.setId(Integer.parseInt(split[0]));
            student.setName(split[1]);
            students.add(student);
        }

//        System.out.println(questions.size());
//        System.out.println(students.size());

        Answer_sheetService service = new Answer_sheetServiceImpl();
        List<Answer_sheet> answers = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < questions.size(); j++) {
                Answer_sheet answer = new Answer_sheet();
                answer.setStu_id(students.get(i).getId());
                answer.setStu_name(students.get(i).getName());
                answer.setSel_chapter(questions.get(j).getChapter());
                answer.setSel_que_id(questions.get(j).getQue_id());
                answer.setTeacher_id(teacherId);
                answer.setExam_name(exam_name);
                service.AddAnswer(answer);
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("exam_name",null);
        session.setAttribute("StuTable",null);
        session.setAttribute("QueTable",null);
        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
