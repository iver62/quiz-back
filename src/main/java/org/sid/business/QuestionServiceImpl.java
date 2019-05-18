package org.sid.business;

import org.sid.domain.entities.Question;
import org.sid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Question getQuestion(final Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Question> getQuestions(final Long idCategory, final Long idLevel, final Long idPlayer, final Pageable pageable) {
        if (idCategory == null) {
            if (idLevel == null) {
                if (idPlayer == null) {
                    return questionRepository.findAll(pageable);
                }
                return questionRepository.findByPlayerId(idPlayer, pageable);
            } else if (idPlayer == null) {
                return questionRepository.findByLevelId(idLevel, pageable);
            }
            return questionRepository.findByLevelIdAndPlayerId(idLevel, idPlayer, pageable);
        } else if (idLevel == null) {
            if (idPlayer == null) {
                return questionRepository.findByCategoryId(idCategory, pageable);
            }
            return questionRepository.findByCategoryIdAndPlayerId(idCategory, idPlayer, pageable);
        } else if (idPlayer == null) {
            return questionRepository.findByCategoryIdAndLevelId(idCategory, idLevel, pageable);
        }
        return questionRepository.findByCategoryIdAndLevelIdAndPlayerId(idCategory, idLevel, idPlayer, pageable);
    }

    @Override
    public Question createQuestion(final Question question) {
        question.getAnswers().forEach(answer -> answer.setQuestion(question));
        question.setCreationDate(new Date());
        question.setLastUpdate(new Date());
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(final Long id, final Question question) {
        question.setId(id);
        question.setLastUpdate(new Date());
        question.getAnswers().forEach(answer -> answer.setQuestion(question));
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(final Long id) {
        questionRepository.deleteById(id);
    }
}
