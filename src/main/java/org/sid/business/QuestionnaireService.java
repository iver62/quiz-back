package org.sid.business;

import org.sid.domain.entities.Questionnaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionnaireService {

    Page<Questionnaire> getQuestionnaires(Long idCategory, Long idLevel, Long idPlayer, Pageable pageable);
}
