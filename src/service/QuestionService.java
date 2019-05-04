package service;

import domain.Question;

import java.util.List;

public interface QuestionService {
    void AddQuestion(Question question);

    List<Question> FindAllQuestionByTeacherId(int id);

    void DeleteQuestion(Question question);
}
