package dao;

import domain.Question;

import java.util.List;

public interface QuestionDao {

    void AddQuestion(Question question);

    List<Question> FindAllQuestionByTeacherId(int id);

    List<Question> FindAllQuestion();

    void DeleteQuestion(Question question);

    Question SearchCorrectAnswer(int chapter, int que_id, int teacher_id);

    Question SearchAnswer(int chapter,int que_id,int teacher_id);

    List<Question> SelectQuestion(int chapter,String type, int teacher_id);
}
