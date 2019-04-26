package dao.impl;

import dao.TeacherDao;
import domain.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;
import java.util.Map;

public class TeacherDaoImpl implements TeacherDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Teacher> findAll() {
        return null;
    }

    @Override
    public Teacher findTeacherByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public void add(Teacher teacher_temp) {
        String sql = "insert into teacher values(?,?,?,?,?,?)";
        template.update(sql,teacher_temp.getId(),teacher_temp.getUsername(),teacher_temp.getPassword(),teacher_temp.getEmail(),teacher_temp.getName(),teacher_temp.getGender());

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Teacher findById(int i) {
        return null;
    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        return 0;
    }

    @Override
    public List<Teacher> findByPage(int start, int rows, Map<String, String[]> condition) {
        return null;
    }
}
