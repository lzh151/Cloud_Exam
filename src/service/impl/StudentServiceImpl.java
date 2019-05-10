package service.impl;
import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import service.StudentService;

import domain.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao dao = new StudentDaoImpl();

    @Override
    public List<Student> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public Student login(Student student) {
        return dao.findStudentByUsernameAndPassword(student.getUsername(),student.getPassword());
    }

    @Override
    public void addStudent(Student student) {
        dao.add(student);
    }

    @Override
    public void deleteStudent(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public Student findStudentById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateStudent(Student student) {
        dao.update(student);
    }

    @Override
    public void delSelectedStudent(String[] ids) {
        if(ids != null && ids.length > 0){
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao删除
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    @Override
    public List<Student> findByTeacherId(int id) {
        return dao.findByTeacherId(id);
    }

    @Override
    public void deleteTeacherId(int student_id, int teacher_id) {
        dao.deleteTeacherId(student_id,teacher_id);
    }
}
