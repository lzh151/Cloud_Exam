package dao.impl;

import dao.StudentDao;
import domain.Student;
import domain.Teacher;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findStudentByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public void add(Student student_temp) {
        String sql = "insert into student values(?,?,?,?,?,?,?,?)";
        template.update(sql,student_temp.getId(),student_temp.getUsername(),student_temp.getPassword(),student_temp.getEmail(),student_temp.getName(),student_temp.getGender(),student_temp.getBirthday(),student_temp.getTeacher_id());
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Student findById(int id) {
        try {
            String sql = "select * from student where id = ?";
            Student student = template.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), id);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Student> findByTeacherId(int id) {
        try {
            String sql = "select * from student where teacher_id = ?";
            List<Student> list = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class), id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteTeacherId(int student_id, int teacher_id) {
        String sql = "update student set teacher_id = null where id = ? and teacher_id = ?";
        template.update(sql,student_id,teacher_id);
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        return 0;
    }

    @Override
    public List<Student> findByPage(int start, int rows, Map<String, String[]> condition) {
        return null;
    }
}
