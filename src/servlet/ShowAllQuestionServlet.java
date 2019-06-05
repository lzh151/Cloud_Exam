package servlet;

import domain.Question;
import service.QuestionService;
import service.impl.QuestionServiceImpl;

import javax.management.remote.rmi.RMIConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAllQuestionServlet")
public class ShowAllQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        Cookie[] cookies = request.getCookies();
//        System.out.println(cookies[0].getValue());
        //cookies[0].setValue("1");
        QuestionService service = new QuestionServiceImpl();
        List<Question> questions = service.FindAllQuestion();

        for (Question question : questions) {
            if (question.getType().equals("选择题")) {
                String que_describe = question.getQue_describe();
                que_describe = que_describe + "</br>A." + question.getAnswer_A() + "</br>B." + question.getAnswer_B() + "</br>C." + question.getAnswer_C() + "</br>D." + question.getAnswer_D();
                question.setQue_describe(que_describe);
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("questionList",questions);
        response.sendRedirect(request.getContextPath() + "index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
