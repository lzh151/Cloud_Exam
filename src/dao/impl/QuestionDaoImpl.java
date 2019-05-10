package dao.impl;

import dao.QuestionDao;
import domain.Question;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void AddQuestion(Question question_temp) {
        String sql = "insert into question values(?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,question_temp.getChapter(),question_temp.getQue_id(),question_temp.getType(),question_temp.getFile_path(),question_temp.getQue_describe(),question_temp.getAnswer_A(),question_temp.getAnswer_B(),question_temp.getAnswer_C(),question_temp.getAnswer_D(),question_temp.getCorrect_answer(),question_temp.getTeacher_id());
    }

    public List<Question> FindAllQuestionByTeacherId(int id){
        try {
            String sql = "select * from question where teacher_id = ?";
            List<Question> list = template.query(sql, new BeanPropertyRowMapper<Question>(Question.class), id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void DeleteQuestion(Question question) {
        try {
            String sql = "delete from question where chapter = ? and que_id = ? and teacher_id = ?";
            template.update(sql,question.getChapter(),question.getQue_id(),question.getTeacher_id());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question SearchCorrectAnswer(int chapter, int que_id, int teacher_id) {
        String sql = "select * from question where chapter = ? and que_id = ? and teacher_id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Question>(Question.class),chapter,que_id,teacher_id);
    }
}
