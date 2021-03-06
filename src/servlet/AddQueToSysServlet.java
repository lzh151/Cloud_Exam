package servlet;

import domain.Question;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addQueToSysServlet")
public class AddQueToSysServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String[] questionIds = request.getParameterValues("questionId");
        //System.out.println(questionIds[0].charAt(0));

        ArrayList<Question> list = new ArrayList<>();
        for (String questionId : questionIds) {
            Question question = new Question();
            String[] split = questionId.split(",");
            question.setChapter(Integer.parseInt(split[0]));
            question.setQue_id(Integer.parseInt(split[1]));
            list.add(question);
        }

        HttpSession session = request.getSession();
        session.setAttribute("QueTable",list);
        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
