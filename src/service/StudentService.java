package service;

import domain.Student;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface StudentService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<Student> findAll();

    /**
     * 登录方法
     * @param student
     * @return
     */
    Student login(Student student);

    /**
     * 保存Student
     * @param student
     */
    void addStudent(Student student);

    /**
     * 根据id删除Student
     * @param id
     */
    void deleteStudent(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Student findStudentById(String id);

    /**
     * 修改用户信息
     * @param user
     */
    void updateStudent(Student user);

    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedStudent(String[] ids);
}
