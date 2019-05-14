package service;

import domain.Answer_sheet;

import java.util.List;

public interface Answer_sheetService {

    void AddAnswer(Answer_sheet answer_sheet);

    List<Answer_sheet> FindAllByTeaId(int tea_id);

    List<Answer_sheet> FindAllStudentExam(String exam_name,int stu_id, String stu_name);

    void AddRemark(Answer_sheet answer_sheet);

    List<Answer_sheet> FindAllByStudentId(int stu_id);

    List<Answer_sheet> FindAllByStudentIdAndExamName(int stu_id,String exam_name);

    void AddStudentAnswer(Answer_sheet answer_sheet);

    void DeleteAllStudentExam(String exam_name,int stu_id, String stu_name);

    void AddBoolMark(Boolean mark);
}
