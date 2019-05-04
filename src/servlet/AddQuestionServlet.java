package servlet;

import domain.Question;
import org.apache.commons.beanutils.BeanUtils;
import service.QuestionService;
import service.impl.QuestionServiceImpl;
import sun.tools.jconsole.inspector.Utils;

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

@WebServlet("/addQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap();

        Question question = new Question();

        HttpSession session = request.getSession();

        try {
            BeanUtils.populate(question,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(!question.getType().equals("选择题")){
            question.setAnswer_A(null);
            question.setAnswer_B(null);
            question.setAnswer_C(null);
            question.setAnswer_D(null);
        }

        QuestionService service = new QuestionServiceImpl();
        service.AddQuestion(question);

        QuestionService questionService = new QuestionServiceImpl();
        List<Question> questions = questionService.FindAllQuestionByTeacherId(question.getTeacher_id());
        session.setAttribute("question",questions);

        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
