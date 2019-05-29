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
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies[0].getValue());
        //cookies[0].setValue("1");
        QuestionService service = new QuestionServiceImpl();
        List<Question> questions = service.FindAllQuestion();

        HttpSession session = request.getSession();
        session.setAttribute("questionList",questions);
        response.sendRedirect(request.getContextPath() + "index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
