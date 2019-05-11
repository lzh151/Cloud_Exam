package servlet;

import domain.Teacher;
import org.apache.commons.beanutils.BeanUtils;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addTeacherServlet")
public class AddTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        Map<String,String[]> map = request.getParameterMap();

        Teacher teacher = new Teacher();

        try {
            BeanUtils.populate(teacher,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        TeacherService service = new TeacherServiceImpl();
        service.addTeacher(teacher);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
