package dao;

import domain.Student;

import java.util.List;
import java.util.Map;

/**
 * 学生操作的DAO
 */
public interface StudentDao {

    List<Student> findAll();

    Student findStudentByUsernameAndPassword(String username, String password);

    void add(Student student);

    void delete(int id);

    Student findById(int id);

    List<Student> findByTeacherId(int id);

    void deleteTeacherId(int student_id,int teacher_id);

    void update(Student student);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<Student> findByPage(int start, int rows, Map<String, String[]> condition);

    int findTeacherId(int student_id);
}
