package service.impl;

import dao.Answer_sheetDao;
import dao.impl.Answer_sheetDaoImpl;
import domain.Answer_sheet;
import service.Answer_sheetService;

import java.util.List;

public class Answer_sheetServiceImpl implements Answer_sheetService {
    Answer_sheetDao answer_sheetDao = new Answer_sheetDaoImpl();

    @Override
    public void AddAnswer(Answer_sheet answer_sheet) {
        answer_sheetDao.AddAnswer(answer_sheet);
    }

    @Override
    public List<Answer_sheet> FindAllByTeaId(int tea_id) {
        return answer_sheetDao.FindAllByTeaId(tea_id);
    }

    @Override
    public List<Answer_sheet> FindAllStudentExam(String exam_name, int stu_id, String stu_name) {
        return answer_sheetDao.FindAllStudentExam(exam_name,stu_id,stu_name);
    }

    @Override
    public void AddRemark(Answer_sheet answer_sheet) {
        answer_sheetDao.AddRemark(answer_sheet);
    }


}
