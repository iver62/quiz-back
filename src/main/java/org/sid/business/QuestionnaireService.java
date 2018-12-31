package org.sid.business;

import org.sid.entities.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> getQuestionnaires();

    Questionnaire getQuestionnaire(final Long id);

    Questionnaire createQuestionnaire(final Questionnaire questionnaire);

    Questionnaire updateQuestionnaire(final Long id, final Questionnaire questionnaire);

    void deleteQuestionnaire(final Long id);
}
