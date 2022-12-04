package org.sid.business;

import org.sid.domain.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

    Page<Question> getQuestions(Long idCategory, Long idLevel, Long idPlayer, Pageable pageable);

    Question createQuestion(Question question);

    Question updateQuestion(Long id, Question question);

}
