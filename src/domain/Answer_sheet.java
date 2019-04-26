package domain;

public class Answer_sheet {
    private int stu_id;
    private int sel_chapter;
    private int sel_que_id;
    private String answer;
    private int correct_number;
    private int wrong_number;

    @Override
    public String toString() {
        return "Answer_sheet{" +
                "stu_id=" + stu_id +
                ", sel_chapter=" + sel_chapter +
                ", sel_que_id=" + sel_que_id +
                ", answer='" + answer + '\'' +
                ", correct_number=" + correct_number +
                ", wrong_number=" + wrong_number +
                '}';
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getSel_chapter() {
        return sel_chapter;
    }

    public void setSel_chapter(int sel_chapter) {
        this.sel_chapter = sel_chapter;
    }

    public int getSel_que_id() {
        return sel_que_id;
    }

    public void setSel_que_id(int sel_que_id) {
        this.sel_que_id = sel_que_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCorrect_number() {
        return correct_number;
    }

    public void setCorrect_number(int correct_number) {
        this.correct_number = correct_number;
    }

    public int getWrong_number() {
        return wrong_number;
    }

    public void setWrong_number(int wrong_number) {
        this.wrong_number = wrong_number;
    }
}
