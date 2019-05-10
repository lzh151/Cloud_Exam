package servlet;

import domain.Answer_sheet;
import domain.Question;
import domain.Student;
import domain.Teacher;
import org.apache.commons.beanutils.BeanUtils;
import service.*;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/studentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap();

        Student student = new Student();

        HttpSession session = request.getSession();

        try {
            BeanUtils.populate(student,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        StudentService service = new StudentServiceImpl();
        Student loginStudent = service.login(student);
        System.out.println(loginStudent.getId());

        Answer_sheetService answer_sheetService = new Answer_sheetServiceImpl();
        List<Answer_sheet> answer_sheets = answer_sheetService.FindAllByStudentId(loginStudent.getId());

        if(loginStudent != null){
            StateService stateService = new StateServiceImpl();
            stateService.AddStudentState(loginStudent);

            session.setAttribute("name",loginStudent.getName());
            session.setAttribute("id",loginStudent.getId());
            session.setAttribute("examList",answer_sheets);
            response.sendRedirect(request.getContextPath() + "/HTML/student_operation.jsp");
        }
        else{
            request.setAttribute("login_msg","用户名密码错误");
            request.getRequestDispatcher("/HTML/student_login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
