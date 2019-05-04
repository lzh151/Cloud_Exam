package service;

import domain.Teacher;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface TeacherService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<Teacher> findAll();

    /**
     * 登录方法
     * @param teacher
     * @return
     */
    Teacher login(Teacher teacher);

    /**
     * 保存teacher
     * @param teacher
     */
    void addTeacher(Teacher teacher);

    /**
     * 根据id删除teacher
     * @param id
     */
    void deleteTeacher(String id);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    Teacher findTeacherByName(String name);

    /**
     * 修改用户信息
     * @param teacher
     */
    void updateTeacher(Teacher teacher);

    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedTeacher(String[] ids);
}
