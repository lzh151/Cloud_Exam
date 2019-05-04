package dao.impl;

import dao.StateDao;
import domain.State;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

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
            String sql = "update login_state set state = 0 where type = \"student\" and state = 1";
            template.update(sql);
        }
        else{
            String sql = "update login_state set state = 0 where type = \"teacher\" and state = 1";
            template.update(sql);
        }

    }
}
