package org.sid.business;

import org.sid.entities.Question;
import org.sid.entities.Questionnaire;
import org.sid.repository.QuestionRepository;
import org.sid.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Questionnaire getQuestionnaire(final Long id) {
        return questionnaireRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Questionnaire> getQuestionnaires(Long idCategory, Long idLevel, Long idPlayer, Pageable pageable) {
        if (idCategory == null) {
            if (idLevel == null) {
                if (idPlayer == null) {
                    return questionnaireRepository.findAll(pageable);
                }
                return questionnaireRepository.findByPlayerId(idPlayer, pageable);
            } else if (idPlayer == null) {
                return questionnaireRepository.findByLevelId(idLevel, pageable);
            }
            return questionnaireRepository.findByLevelIdAndPlayerId(idLevel, idPlayer, pageable);
        } else if (idLevel == null) {
            if (idPlayer == null) {
                return questionnaireRepository.findByCategoryId(idCategory, pageable);
            }
            return questionnaireRepository.findByCategoryIdAndPlayerId(idCategory, idPlayer, pageable);
        } else if (idPlayer == null) {
            return questionnaireRepository.findByCategoryIdAndLevelId(idCategory, idLevel, pageable);
        }
        return questionnaireRepository.findByCategoryIdAndLevelIdAndPlayerId(idCategory, idLevel, idPlayer, pageable);
    }

    @Override
    public Questionnaire createQuestionnaire(final Questionnaire questionnaire) {
        List<Question> questions = questionRepository.findByCategoryIdAndLevelIdAndPlayerIdNot(questionnaire.getCategory().getId(),
                questionnaire.getLevel().getId(), questionnaire.getPlayer().getId());
        Collections.shuffle(questions);
        questionnaire.setQuestions(new HashSet<>(questions.subList(0, questionnaire.getNumber())));
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public Questionnaire updateQuestionnaire(Long id, Questionnaire questionnaire) {
        questionnaire.setId(id);
        questionnaire.setValidationDate(new Date());
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public void deleteQuestionnaire(final Long id) {
        questionnaireRepository.deleteById(id);
    }
}
