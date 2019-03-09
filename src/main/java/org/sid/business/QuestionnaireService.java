package org.sid.business;

import org.sid.entities.Questionnaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionnaireService {

    Questionnaire getQuestionnaire(final Long id);

    Page<Questionnaire> getQuestionnaires(final Long idCategory, final Long idLevel, final Long idPlayer, final Pageable pageable);

    Questionnaire createQuestionnaire(final Questionnaire questionnaire);

    Questionnaire updateQuestionnaire(final Long id, final Questionnaire questionnaire);

    void deleteQuestionnaire(final Long id);
}
