package servlet;

import domain.State;
import service.StateService;
import service.impl.StateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/studentQuitServlet")
public class StudentQuitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        int student_id = Integer.parseInt(request.getParameter("student_id"));
        State state = new State();
        state.setType("student");
        state.setState(false);
        state.setStu_id(student_id);
        StateService stateService = new StateServiceImpl();

        stateService.DeleteStudentState(state);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
