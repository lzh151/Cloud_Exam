package servlet;

import domain.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteTeacherIdServlet")
public class DeleteTeacherIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));

        StudentService service = new StudentServiceImpl();
        service.deleteTeacherId(student_id,teacher_id);

        List<Student> students = service.findByTeacherId(teacher_id);

        HttpSession session = request.getSession();
        session.setAttribute("student",students);

        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
