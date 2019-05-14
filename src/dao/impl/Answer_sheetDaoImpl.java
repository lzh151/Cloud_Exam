package dao.impl;

import dao.Answer_sheetDao;
import domain.Answer_sheet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class Answer_sheetDaoImpl implements Answer_sheetDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void AddAnswer(Answer_sheet answer_sheet) {
        String sql = "insert into answer_sheet values(?,?,?,?,null,?,?,null,0,0)";
        template.update(sql,answer_sheet.getStu_id(),answer_sheet.getStu_name(),answer_sheet.getSel_chapter(),answer_sheet.getSel_que_id(),answer_sheet.getTeacher_id(),answer_sheet.getExam_name());
    }

    @Override
    public List<Answer_sheet> FindAllByTeaId(int tea_id) {
        try {
            String sql = "select * from answer_sheet where teacher_id = ? group by stu_id,exam_name";
            return template.query(sql, new BeanPropertyRowMapper<Answer_sheet>(Answer_sheet.class), tea_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Answer_sheet> FindAllStudentExam(String exam_name, int stu_id, String stu_name) {
        try {
            String sql = "select * from answer_sheet where stu_id = ? and stu_name = ? and exam_name = ?";
            return template.query(sql, new BeanPropertyRowMapper<Answer_sheet>(Answer_sheet.class), stu_id, stu_name, exam_name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void AddRemark(Answer_sheet answer_sheet) {
        String sql = "update answer_sheet set remark = ? where exam_name = ? and stu_id = ? and sel_chapter = ? and sel_que_id = ? and teacher_id = ?";
        template.update(sql,answer_sheet.getRemark(),answer_sheet.getExam_name(),answer_sheet.getStu_id(),answer_sheet.getSel_chapter(),answer_sheet.getSel_que_id(),answer_sheet.getTeacher_id());
    }

    @Override
    public List<Answer_sheet> FindAllByStudentId(int stu_id) {
        try {
            String sql = "select * from answer_sheet where stu_id = ? group by exam_name";
            return template.query(sql, new BeanPropertyRowMapper<Answer_sheet>(Answer_sheet.class), stu_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Answer_sheet> FindAllByStudentIdAndExamName(int stu_id, String exam_name) {
        try {
            String sql = "select * from answer_sheet where stu_id = ? and exam_name = ?";
            return template.query(sql, new BeanPropertyRowMapper<Answer_sheet>(Answer_sheet.class), stu_id, exam_name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void AddStudentAnswer(Answer_sheet answer_sheet) {
        String sql = "update answer_sheet set answer = ? where exam_name = ? and stu_id = ? and sel_chapter = ? and sel_que_id = ?";
        template.update(sql,answer_sheet.getAnswer(),answer_sheet.getExam_name(),answer_sheet.getStu_id(),answer_sheet.getSel_chapter(),answer_sheet.getSel_que_id());
    }

    @Override
    public void DeleteAllStudentExam(String exam_name, int stu_id, String stu_name) {
        try {
            String sql = "delete from answer_sheet where exam_name = ? and stu_id = ? and stu_name = ?";
            template.update(sql,exam_name,stu_id,stu_name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AddBoolMark(Boolean mark) {
        if (mark){
            String sql = "update answer_sheet set false_counter = false_counter + 1";
            template.update(sql);
        }
        else {
            String sql = "update answer_sheet set true_counter = true_counter + 1";
            template.update(sql);
        }
    }
}
