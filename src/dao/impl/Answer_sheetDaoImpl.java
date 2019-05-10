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
        String sql = "insert into answer_sheet values(?,?,?,?,null,?,?)";
        template.update(sql,answer_sheet.getStu_id(),answer_sheet.getStu_name(),answer_sheet.getSel_chapter(),answer_sheet.getSel_que_id(),answer_sheet.getTeacher_id(),answer_sheet.getExam_name());
    }

    @Override
    public List<Answer_sheet> FindAllByTeaId(int tea_id) {
        try {
            String sql = "select * from answer_sheet where teacher_id = ? group by stu_id,exam_name";
            List<Answer_sheet> list = template.query(sql, new BeanPropertyRowMapper<Answer_sheet>(Answer_sheet.class), tea_id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Answer_sheet> FindAllStudentExam(String exam_name, int stu_id, String stu_name) {
        try {
            String sql = "select * from answer_sheet where stu_id = ? and stu_name = ? and exam_name = ?";
            List<Answer_sheet> list = template.query(sql, new BeanPropertyRowMapper<Answer_sheet>(Answer_sheet.class), stu_id, stu_name, exam_name);
            return list;
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
}
