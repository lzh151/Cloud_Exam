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

@WebServlet("/teacherQuitServlet")
public class TeacherQuitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        State state = new State();
        state.setType("teacher");
        state.setState(false);
        StateService stateService = new StateServiceImpl();

        stateService.DeleteTeacherState(state);
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
