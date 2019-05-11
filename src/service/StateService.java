package service;

import domain.State;
import domain.Student;
import domain.Teacher;

import java.util.List;

public interface StateService {

    void AddTeacherState(Teacher teacher);

    void AddStudentState(Student student);

    void DeleteTeacherState(State state);

    void DeleteStudentState(State state);

    List<State> findStateByStudentId(int student_id);


    List<State> findStateByTeacherId(int teacher_id);

}
