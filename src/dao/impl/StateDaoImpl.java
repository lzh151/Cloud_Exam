package dao.impl;

import dao.StateDao;
import domain.State;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class StateDaoImpl implements StateDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void add(State state,String type) {
        if(type.equals("student")){
            String sql = "insert into login_state values(null,?,?,null,?)";
            template.update(sql,state.getType(),state.getStu_id(),state.getState());
        }
        else{
            String sql = "insert into login_state values(null,?,null,?,?)";
            template.update(sql,state.getType(),state.getTeacher_id(),state.getState());
        }
    }

    @Override
    public void update(State state) {
        if(state.getType().equals("student")){
            String sql = "update login_state set state = 0 where type = ? and state = 1 and stu_id = ?";
            template.update(sql,state.getType(),state.getStu_id());
        }
        else{
            String sql = "update login_state set state = 0 where type = ? and state = 1 and teacher_id = ?";
            template.update(sql,state.getType(),state.getTeacher_id());
        }
    }

    @Override
    public List<State> findStateByTeacherId(int teacher_id) {
        try {
            String type = "teacher";
            String sql = "select * from login_state where type = ? and teacher_id = ? and state = 1";
            return template.query(sql,new BeanPropertyRowMapper<State>(State.class),type,teacher_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<State> findStateByStudentId(int student_id) {
        try {
            String type = "student";
            String sql = "select * from login_state where type = ? and stu_id = ? and state = 1";
            return template.query(sql,new BeanPropertyRowMapper<State>(State.class),type,student_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
