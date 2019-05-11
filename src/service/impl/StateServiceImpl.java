package service.impl;

import dao.StateDao;
import dao.impl.StateDaoImpl;
import domain.State;
import domain.Student;
import domain.Teacher;
import service.StateService;

import java.util.List;

public class StateServiceImpl implements StateService {
    StateDao dao =  new StateDaoImpl();

    @Override
    public void AddTeacherState(Teacher teacher) {
        State state = new State();
        state.setType("teacher");
        state.setTeacher_id(teacher.getId());
        state.setState(true);
        dao.add(state,"teacher");
    }

    @Override
    public void AddStudentState(Student student) {
        State state = new State();
        state.setType("student");
        state.setStu_id(student.getId());
        state.setState(true);
        dao.add(state,"student");
    }

    @Override
    public void DeleteTeacherState(State state) {
        dao.update(state);
    }

    @Override
    public void DeleteStudentState(State state) {
        dao.update(state);
    }

    @Override
    public List<State> findStateByTeacherId(int teacher_id) {
        return dao.findStateByTeacherId(teacher_id);
    }

    @Override
    public List<State> findStateByStudentId(int student_id) {
        return dao.findStateByStudentId(student_id);
    }
}
