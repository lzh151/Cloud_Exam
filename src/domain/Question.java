package domain;

public class Question {
    private int chapter;
    private String que_id;
    private String type;
    private String que_describe;
    private String answer_A;
    private String answer_B;
    private String answer_C;
    private String answer_D;
    private String correct_answer;

    @Override
    public String toString() {
        return "Question{" +
                "chapter=" + chapter +
                ", que_id='" + que_id + '\'' +
                ", type='" + type + '\'' +
                ", que_describe='" + que_describe + '\'' +
                ", answer_A='" + answer_A + '\'' +
                ", answer_B='" + answer_B + '\'' +
                ", answer_C='" + answer_C + '\'' +
                ", answer_D='" + answer_D + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                '}';
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getQue_id() {
        return que_id;
    }

    public void setQue_id(String que_id) {
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
}
