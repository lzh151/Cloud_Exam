package domain;

public class Answer_sheet {
    private int stu_id;
    private String stu_name;
    private int sel_chapter;
    private int sel_que_id;
    private String answer;
    private int teacher_id;
    private String exam_name;
    private String answer_correct;
    private String remark;
    private int true_counter;
    private int false_counter;

    @Override
    public String toString() {
        return "Answer_sheet{" +
                "stu_id=" + stu_id +
                ", stu_name='" + stu_name + '\'' +
                ", sel_chapter=" + sel_chapter +
                ", sel_que_id=" + sel_que_id +
                ", answer='" + answer + '\'' +
                ", teacher_id=" + teacher_id +
                ", exam_name='" + exam_name + '\'' +
                ", answer_correct='" + answer_correct + '\'' +
                ", remark='" + remark + '\'' +
                ", true_counter=" + true_counter +
                ", false_counter=" + false_counter +
                '}';
    }

    public int getTrue_counter() {
        return true_counter;
    }

    public void setTrue_counter(int true_counter) {
        this.true_counter = true_counter;
    }

    public int getFalse_counter() {
        return false_counter;
    }

    public void setFalse_counter(int false_counter) {
        this.false_counter = false_counter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_name() {
        return stu_name;
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

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }
}
