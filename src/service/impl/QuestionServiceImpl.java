package service.impl;

import dao.QuestionDao;
import dao.impl.QuestionDaoImpl;
import domain.Question;
import service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDao dao = new QuestionDaoImpl();

    @Override
    public void AddQuestion(Question question) {
        dao.AddQuestion(question);
    }

    @Override
    public List<Question> FindAllQuestionByTeacherId(int id) {
        return dao.FindAllQuestionByTeacherId(id);
    }

    @Override
    public void DeleteQuestion(Question question) {
        dao.DeleteQuestion(question);
    }

    @Override
    public Question SearchCorrectAnswer(int chapter, int que_id, int teacher_id) {
        return dao.SearchCorrectAnswer(chapter,que_id,teacher_id);
    }

    @Override
    public Question SearchAnswer(int chapter, int que_id, int teacher_id) {
        return dao.SearchAnswer(chapter,que_id,teacher_id);
    }

    @Override
    public List<Question> SelectQuestion(int chapter, String type) {
        return dao.SelectQuestion(chapter,type);
    }

}
