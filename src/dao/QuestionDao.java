package dao;


import domain.Question;

import java.util.List;

public interface QuestionDao {

    void AddQuestion(Question question);

    List<Question> FindAllQuestionByTeacherId(int id);

    void DeleteQuestion(Question question);
}
