package dao;

import domain.State;

import java.util.List;

public interface StateDao {

    void add(State state,String type);

    void update(State state);

    List<State> findStateByTeacherId(int teacher_id);

    List<State> findStateByStudentId(int student_id);
}
