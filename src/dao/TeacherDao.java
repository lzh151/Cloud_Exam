package dao;

import domain.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherDao {


    List<Teacher> findAll();

    Teacher findTeacherByUsernameAndPassword(String username, String password);

    void add(Teacher teacher);

    void delete(int id);

    Teacher findByName(String name);

    void update(Teacher teacher);

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
    List<Teacher> findByPage(int start, int rows, Map<String, String[]> condition);
}
