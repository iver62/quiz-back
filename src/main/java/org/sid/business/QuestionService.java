package org.sid.business;

import org.sid.domain.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

    Question getQuestion(final Long id);

    Page<Question> getQuestions(final Long idCategory, final Long idLevel, final Long idPlayer, final Pageable pageable);

    Question createQuestion(final Question question);

    Question updateQuestion(final Long id, final Question question);

    void deleteQuestion(final Long id);
}
