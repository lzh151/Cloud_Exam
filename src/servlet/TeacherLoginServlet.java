package servlet;

import domain.Question;
import domain.Teacher;
import org.apache.commons.beanutils.BeanUtils;
import service.QuestionService;
import service.StateService;
import service.TeacherService;
import service.impl.QuestionServiceImpl;
import service.impl.StateServiceImpl;
import service.impl.TeacherServiceImpl;

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

@WebServlet("/teacherLoginServlet")
public class TeacherLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap();

        Teacher teacher = new Teacher();

        HttpSession session = request.getSession();

        try {
            BeanUtils.populate(teacher,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        TeacherService service = new TeacherServiceImpl();
        Teacher loginTeacher = service.login(teacher);

        QuestionService questionService = new QuestionServiceImpl();
        List<Question> questions = questionService.FindAllQuestionByTeacherId(loginTeacher.getId());

        if(loginTeacher != null){
            StateService service1 = new StateServiceImpl();
            service1.AddTeacherState(loginTeacher);

            session.setAttribute("teacher",loginTeacher);
            session.setAttribute("question",questions);
            response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
        }
        else{
            request.setAttribute("login_msg","用户名密码错误");
            request.getRequestDispatcher("/HTML/teacher_login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
