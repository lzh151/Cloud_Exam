package domain;

public class State {
    private int number;
    private String type;
    private int stu_id;
    private int teacher_id;
    private boolean state;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State{" +
                "number=" + number +
                ", type='" + type + '\'' +
                ", stu_id=" + stu_id +
                ", teacher_id=" + teacher_id +
                ", state=" + state +
                '}';
    }
}
