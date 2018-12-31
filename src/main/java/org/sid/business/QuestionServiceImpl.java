package org.sid.business;

import org.sid.entities.Answer;
import org.sid.entities.Question;
import org.sid.repository.AnswerRepository;
import org.sid.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Question getQuestion(final Long id) {
        return this.questionRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Question> getQuestions(final Pageable pageable) {
        return this.questionRepository.findAll(pageable);
    }

    @Override
    public List<Answer> getAnswers(final Long id) {
        return null;
    }

    @Override
    public Question createQuestion(final Question question) {
        question.getAnswers().forEach(answer -> answer.setQuestion(question));
        question.setCreationDate(new Date());
        question.setLastUpdate(new Date());
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(final Long id, final Question question) {
        question.setId(id);
        question.setLastUpdate(new Date());
        question.getAnswers().forEach(answer -> answer.setQuestion(question));
        return this.questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(final Long id) {
        this.questionRepository.deleteById(id);
    }
}
