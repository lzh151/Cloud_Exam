package service;

import domain.State;
import domain.Student;
import domain.Teacher;

public interface StateService {

    void AddTeacherState(Teacher teacher);

    void AddStudentState(Student student);

    void DeleteTeacherState(State state);

    void DeleteStudentState(State state);

}
