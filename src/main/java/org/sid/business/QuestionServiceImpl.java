package org.sid.business;

import org.sid.domain.entities.Question;
import org.sid.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl<Question> implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<Question> getQuestions(final Long idCategory, final Long idLevel, final Long idPlayer, final Pageable pageable) {
        if (Objects.isNull(idCategory)) {
            if (Objects.isNull(idLevel)) {
                if (Objects.isNull(idPlayer)) {
                    return questionRepository.findAll(pageable);
                }
                return questionRepository.findByPlayerId(idPlayer, pageable);
            } else if (Objects.isNull(idPlayer)) {
                return questionRepository.findByLevelId(idLevel, pageable);
            }
            return questionRepository.findByLevelIdAndPlayerId(idLevel, idPlayer, pageable);
        } else if (Objects.isNull(idLevel)) {
            if (Objects.isNull(idPlayer)) {
                return questionRepository.findByCategoryId(idCategory, pageable);
            }
            return questionRepository.findByCategoryIdAndPlayerId(idCategory, idPlayer, pageable);
        } else if (Objects.isNull(idPlayer)) {
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
    protected PagingAndSortingRepository<Question, Long> getRepository() {
        return questionRepository;
    }
}
