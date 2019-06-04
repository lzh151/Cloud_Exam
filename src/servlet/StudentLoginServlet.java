package servlet;

import domain.*;
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
import java.util.ArrayList;
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
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        StudentService service = new StudentServiceImpl();
        Student loginStudent = service.login(student);

        if(loginStudent != null){
            StateService stateService = new StateServiceImpl();
            List<State> stateList = stateService.findStateByStudentId(loginStudent.getId());

            Answer_sheetService answer_sheetService = new Answer_sheetServiceImpl();
            List<Answer_sheet> studentMistakes = answer_sheetService.FindStudentMistake(loginStudent.getId());

            QuestionService questionService = new QuestionServiceImpl();
            List<Question> questions = new ArrayList<>();
            for (Answer_sheet answer_sheet : studentMistakes) {
                Question question = questionService.SearchAnswer(answer_sheet.getSel_chapter(), answer_sheet.getSel_que_id(), answer_sheet.getTeacher_id());
                questions.add(question);
            }

            List<Question_Answer> mistakes = new ArrayList<>();
            for (int i = 0; i < studentMistakes.size(); i++) {
                Question_Answer question_answer = new Question_Answer();

                question_answer.setExam_name(studentMistakes.get(i).getExam_name());
                question_answer.setChapter(studentMistakes.get(i).getSel_chapter());
                question_answer.setQue_id(studentMistakes.get(i).getSel_que_id());
                question_answer.setType(questions.get(i).getType());
                question_answer.setQue_describe(questions.get(i).getQue_describe());
                question_answer.setFile_path(questions.get(i).getFile_path());
                question_answer.setAnswer_A(questions.get(i).getAnswer_A());
                question_answer.setAnswer_B(questions.get(i).getAnswer_B());
                question_answer.setAnswer_C(questions.get(i).getAnswer_C());
                question_answer.setAnswer_D(questions.get(i).getAnswer_D());
                question_answer.setRemark(studentMistakes.get(i).getRemark());
                question_answer.setAnswer(studentMistakes.get(i).getAnswer());
                question_answer.setTeacher_id(studentMistakes.get(i).getTeacher_id());
                question_answer.setStu_id(studentMistakes.get(i).getStu_id());
                mistakes.add(question_answer);
            }

            for (Question_Answer mistake : mistakes) {
                if (mistake.getType().equals("选择题")) {
                    String que_describe = mistake.getQue_describe();
                    que_describe = que_describe + "</br>A." + mistake.getAnswer_A() + "</br>B." + mistake.getAnswer_B() + "</br>C." + mistake.getAnswer_C() + "</br>D." + mistake.getAnswer_D();
                    mistake.setQue_describe(que_describe);
                }
            }

            if(stateList != null){
                stateService.AddStudentState(loginStudent);

                List<Answer_sheet> answer_sheets = answer_sheetService.FindAllByStudentId(loginStudent.getId());
                session.setAttribute("name",loginStudent.getName());
                session.setAttribute("id",loginStudent.getId());
                session.setAttribute("examList",answer_sheets);
                session.setAttribute("mistakeList",mistakes);
                response.sendRedirect(request.getContextPath() + "/HTML/student_operation.jsp");
            }
            else{
                request.setAttribute("login_msg","请勿重复登录");
                request.getRequestDispatcher("/HTML/student_login.jsp").forward(request,response);
            }
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
