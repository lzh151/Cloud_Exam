package domain;

public class Question_Answer {
    private int chapter;
    private int que_id;
    private String type;
    private String file_path;
    private String que_describe;
    private String answer_A;
    private String answer_B;
    private String answer_C;
    private String answer_D;
    private String correct_answer;
    private int teacher_id;
    private String exam_name;
    private String answer;
    private String remark;
    private int stu_id;

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer_correct) {
        this.answer = answer_correct;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getQue_id() {
        return que_id;
    }

    public void setQue_id(int que_id) {
        this.que_id = que_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQue_describe() {
        return que_describe;
    }

    public void setQue_describe(String que_describe) {
        this.que_describe = que_describe;
    }

    public String getAnswer_A() {
        return answer_A;
    }

    public void setAnswer_A(String answer_A) {
        this.answer_A = answer_A;
    }

    public String getAnswer_B() {
        return answer_B;
    }

    public void setAnswer_B(String answer_B) {
        this.answer_B = answer_B;
    }

    public String getAnswer_C() {
        return answer_C;
    }

    public void setAnswer_C(String answer_C) {
        this.answer_C = answer_C;
    }

    public String getAnswer_D() {
        return answer_D;
    }

    public void setAnswer_D(String answer_D) {
        this.answer_D = answer_D;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    @Override
    public String toString() {
        return "Question_Answer{" +
                "chapter=" + chapter +
                ", que_id=" + que_id +
                ", type='" + type + '\'' +
                ", file_path='" + file_path + '\'' +
                ", que_describe='" + que_describe + '\'' +
                ", answer_A='" + answer_A + '\'' +
                ", answer_B='" + answer_B + '\'' +
                ", answer_C='" + answer_C + '\'' +
                ", answer_D='" + answer_D + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", teacher_id=" + teacher_id +
                ", exam_name='" + exam_name + '\'' +
                ", answer='" + answer + '\'' +
                ", remark='" + remark + '\'' +
                ", stu_id='" + stu_id + '\'' +
                '}';
    }
}
