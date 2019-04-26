package service.impl;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import domain.Teacher;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao dao = new TeacherDaoImpl();

    @Override
    public List<Teacher> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public Teacher login(Teacher teacher) {
        return dao.findTeacherByUsernameAndPassword(teacher.getUsername(),teacher.getPassword());
    }

    @Override
    public void addTeacher(Teacher teacher) {
        dao.add(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public Teacher findTeacherById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        dao.update(teacher);
    }

    @Override
    public void delSelectedTeacher(String[] ids) {
        if(ids != null && ids.length > 0){
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao删除
                dao.delete(Integer.parseInt(id));
            }
        }

    }
}
