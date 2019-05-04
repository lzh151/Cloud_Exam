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

}
