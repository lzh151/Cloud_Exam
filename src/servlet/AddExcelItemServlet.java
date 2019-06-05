package servlet;

import domain.Question;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.beanutils.BeanUtils;
import service.QuestionService;
import service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addExcelItemServlet")
@MultipartConfig
public class AddExcelItemServlet extends HttpServlet {

    private String contextPath;

    @Override
    public void init() {
        contextPath = getServletContext().getRealPath("/");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        Part filePart = null;
        try {
            filePart = request.getPart("excel_path");
        } catch (ServletException e) {
            e.printStackTrace();
        }
        String fileName = getFileName(filePart);
        writeTo(fileName,filePart);

        String name = contextPath + "File/excel.xls";
        File f=new File(name);
        Question question = new Question();
        String[] head = {"chapter","que_id","type","file_path","que_describe","answer_A","answer_B","answer_C","answer_D","correct_answer","teacher_id"};
        try {
            Workbook book=Workbook.getWorkbook(f);//
            Sheet sheet=book.getSheet(0); //获得第一个工作表对象
            for(int i=0;i<sheet.getRows();i++){
                Map<String, String> map = new HashMap<>();
                for(int j=0;j<sheet.getColumns();j++){
                    Cell cell=sheet.getCell(j, i); //获得单元格
                    map.put(head[j],cell.getContents());
//                    System.out.print(cell.getContents()+" ");
                }
                try {
                    BeanUtils.populate(question,map);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
//                    if(!question.getType().equals("选择题")){
//                        question.setAnswer_A(null);
//                        question.setAnswer_B(null);
//                        question.setAnswer_C(null);
//                        question.setAnswer_D(null);
//                    }
                try {
                    QuestionService service = new QuestionServiceImpl();
                    service.AddQuestion(question);
                }
                catch (Exception e){
                    request.setAttribute("add_msg","试题题号冲突,请重新添加");
                    request.getRequestDispatcher("/HTML/teacher_operation.jsp").forward(request,response);
                }
                System.out.println();
            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
        QuestionService questionService = new QuestionServiceImpl();

        List<Question> questions = questionService.FindAllQuestionByTeacherId(question.getTeacher_id());
        session.setAttribute("question",questions);

        response.sendRedirect(request.getContextPath() + "/HTML/teacher_operation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
//        System.out.println(header);
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
//        System.out.println(fileName);
        return fileName;
    }

    private void writeTo(String fileName, Part part) throws IOException {
        InputStream in = part.getInputStream();
        //System.out.println(contextPath + "/File/" + fileName);
        File file = new File(contextPath + "/File/excel.xls");
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
