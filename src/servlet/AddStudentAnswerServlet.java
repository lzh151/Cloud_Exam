package servlet;

import domain.Answer_sheet;
import domain.Question;
import domain.Question_Answer;
import service.Answer_sheetService;
import service.QuestionService;
import service.impl.Answer_sheetServiceImpl;
import service.impl.QuestionServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addStudentAnswerServlet")
public class AddStudentAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        Answer_sheet answer_sheet = new Answer_sheet();
        String exam_name = request.getParameter("exam_name");
        String[] split = exam_name.split(" ");
        exam_name = split[1];
        answer_sheet.setExam_name(exam_name);

        answer_sheet.setStu_id(Integer.parseInt(request.getParameter("stu_id")));
        answer_sheet.setSel_chapter(Integer.parseInt(request.getParameter("chapter")));
        answer_sheet.setSel_que_id(Integer.parseInt(request.getParameter("que_id")));
        answer_sheet.setAnswer(request.getParameter("text"));

        Answer_sheetService service = new Answer_sheetServiceImpl();
        service.AddStudentAnswer(answer_sheet);

        List<Answer_sheet> answer_sheets = service.FindAllByStudentIdAndExamName(Integer.parseInt(request.getParameter("stu_id")), exam_name);

        QuestionService questionService = new QuestionServiceImpl();
        List<Question> questions = new ArrayList<>();
        for (Answer_sheet answerSheet : answer_sheets) {
            Question question = questionService.SearchAnswer(answerSheet.getSel_chapter(), answerSheet.getSel_que_id(), answerSheet.getTeacher_id());
            questions.add(question);
        }

        List<Question_Answer> question_answers = new ArrayList<>();
        for (int i = 0; i < answer_sheets.size(); i++) {
            Question_Answer question_answer = new Question_Answer();

            question_answer.setExam_name(answer_sheets.get(i).getExam_name());
            question_answer.setChapter(answer_sheets.get(i).getSel_chapter());
            question_answer.setQue_id(answer_sheets.get(i).getSel_que_id());
            question_answer.setType(questions.get(i).getType());
            question_answer.setQue_describe(questions.get(i).getQue_describe());
            question_answer.setFile_path(questions.get(i).getFile_path());
            question_answer.setAnswer_A(questions.get(i).getAnswer_A());
            question_answer.setAnswer_B(questions.get(i).getAnswer_B());
            question_answer.setAnswer_C(questions.get(i).getAnswer_C());
            question_answer.setAnswer_D(questions.get(i).getAnswer_D());
            question_answer.setRemark(answer_sheets.get(i).getRemark());
            question_answer.setAnswer(answer_sheets.get(i).getAnswer());
            question_answer.setTeacher_id(answer_sheets.get(i).getTeacher_id());
            question_answer.setStu_id(answer_sheets.get(i).getStu_id());
            question_answers.add(question_answer);
        }

        HttpSession session = request.getSession();
        session.setAttribute("questionsList",question_answers);
        response.sendRedirect(request.getContextPath() + "/HTML/student_paper.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
