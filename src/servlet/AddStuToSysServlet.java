package servlet;

import domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addStuToSysServlet")
public class AddStuToSysServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String[] totalStudents = request.getParameterValues("totalStudent");
        //System.out.println(questionIds[0].charAt(0));

        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < totalStudents.length; i++) {
            Student student = new Student();
            String[] split = totalStudents[i].split(",");
            student.setId(Integer.parseInt(split[0]));
            student.setName(split[1]);
            list.add(student);
        }

        HttpSession session = request.getSession();
        session.setAttribute("StuTable",list);
        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
