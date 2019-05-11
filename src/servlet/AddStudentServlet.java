package servlet;

import domain.Student;
import org.apache.commons.beanutils.BeanUtils;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        Map<String,String[]> map = request.getParameterMap();

        Student student = new Student();

        try {
            BeanUtils.populate(student,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        StudentService service = new StudentServiceImpl();
        service.addStudent(student);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
