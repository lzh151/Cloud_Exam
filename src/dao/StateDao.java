package dao;

import domain.State;

public interface StateDao {

    void add(State state,String type);

    void update(State state);
}
