package org.sid.business;

import org.sid.domain.entities.Question;
import org.sid.domain.entities.Questionnaire;
import org.sid.repository.QuestionRepository;
import org.sid.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionnaireServiceImpl extends AbstractServiceImpl<Questionnaire> implements QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<Questionnaire> getQuestionnaires(final Long idCategory, final Long idLevel, final Long idPlayer, final Pageable pageable) {
        if (Objects.isNull(idCategory)) {
            if (Objects.isNull(idLevel)) {
                if (Objects.isNull(idPlayer)) {
                    return questionnaireRepository.findAll(pageable);
                }
                return questionnaireRepository.findByPlayerId(idPlayer, pageable);
            } else if (Objects.isNull(idPlayer)) {
                return questionnaireRepository.findByLevelId(idLevel, pageable);
            }
            return questionnaireRepository.findByLevelIdAndPlayerId(idLevel, idPlayer, pageable);
        } else if (Objects.isNull(idLevel)) {
            if (Objects.isNull(idPlayer)) {
                return questionnaireRepository.findByCategoryId(idCategory, pageable);
            }
            return questionnaireRepository.findByCategoryIdAndPlayerId(idCategory, idPlayer, pageable);
        } else if (Objects.isNull(idPlayer)) {
            return questionnaireRepository.findByCategoryIdAndLevelId(idCategory, idLevel, pageable);
        }
        return questionnaireRepository.findByCategoryIdAndLevelIdAndPlayerId(idCategory, idLevel, idPlayer, pageable);
    }

    @Override
    public Questionnaire create(final Questionnaire questionnaire) {
        List<Question> questions = questionRepository.findByCategoryIdAndLevelIdAndPlayerIdNot(questionnaire.getCategory().getId(),
                questionnaire.getLevel().getId(), questionnaire.getPlayer().getId());
        Collections.shuffle(questions);
        questionnaire.setQuestions(new HashSet<>(questions.subList(0, questionnaire.getNumber())));
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    protected PagingAndSortingRepository<Questionnaire, Long> getRepository() {
        return questionnaireRepository;
    }

//    @Override
//    public Questionnaire updateQuestionnaire(Long id, Questionnaire questionnaire) {
//        questionnaire.setId(id);
//        questionnaire.setValidationDate(new Date());
//        return questionnaireRepository.save(questionnaire);
//    }

}
