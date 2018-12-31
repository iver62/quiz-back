package org.sid.business;

import org.sid.entities.Answer;
import org.sid.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    Question getQuestion(final Long id);

    Page<Question> getQuestions(final Pageable pageable);

    List<Answer> getAnswers(final Long id);

    Question createQuestion(final Question question);

    Question updateQuestion(final Long id, final Question question);

    void deleteQuestion(final Long id);
}
