package servlet;

import domain.Answer_sheet;
import service.Answer_sheetService;
import service.impl.Answer_sheetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteScheduleServlet")
public class DeleteScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String exam_name = request.getParameter("exam_name");
        int stu_id = Integer.parseInt(request.getParameter("stu_id"));
        String stu_name = request.getParameter("stu_name");
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
        String[] split = stu_name.split(" ");
        stu_name = split[1];

        Answer_sheetService service = new Answer_sheetServiceImpl();

        service.DeleteAllStudentExam(exam_name, stu_id, stu_name);

        Answer_sheetService answer_sheetService = new Answer_sheetServiceImpl();
        List<Answer_sheet> answer_sheets = answer_sheetService.FindAllByTeaId(teacher_id);

        HttpSession session = request.getSession();
        session.setAttribute("stu_id",stu_id);
        session.setAttribute("stu_name",stu_name);
        session.setAttribute("Schedule",answer_sheets);
        response.sendRedirect("/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
