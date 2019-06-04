package servlet;

import domain.Question;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import service.QuestionService;
import service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/addQuestionServlet")
@MultipartConfig
public class AddQuestionServlet extends HttpServlet {

    private String contextPath;

    @Override
    public void init() {
        contextPath = getServletContext().getRealPath("/");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        Map<String, String[]> map = request.getParameterMap();
        Question question = new Question();

        String fileCheckBox = request.getParameter("fileCheckBox");

        if(fileCheckBox.equals("checked")){
            Part filePart = null;
            try {
                filePart = request.getPart("file_path");
            } catch (ServletException e) {
                e.printStackTrace();
            }
            String fileName = getFileName(filePart);
            writeTo(fileName,filePart);
            question.setFile_path("/File/" + fileName);
        }
        try {
            BeanUtils.populate(question,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if(!question.getType().equals("选择题")){
            question.setAnswer_A(null);
            question.setAnswer_B(null);
            question.setAnswer_C(null);
            question.setAnswer_D(null);
        }

        try {
            QuestionService service = new QuestionServiceImpl();
            service.AddQuestion(question);
        }
        catch (Exception e){
            request.setAttribute("add_msg","试题题号冲突,请重新添加");
            request.getRequestDispatcher("/HTML/teacher_operation.jsp").forward(request,response);
        }

        QuestionService questionService = new QuestionServiceImpl();

        List<Question> questions = questionService.FindAllQuestionByTeacherId(question.getTeacher_id());
        session.setAttribute("question",questions);

        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
        return fileName;
    }

    private void writeTo(String fileName, Part part) throws IOException {
        InputStream in = part.getInputStream();
        //System.out.println(contextPath + "/File/" + fileName);
        File file = new File(contextPath + "/File/" + fileName);
        OutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }
}
