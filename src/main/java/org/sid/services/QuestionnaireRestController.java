package org.sid.services;

import org.sid.business.QuestionnaireService;
import org.sid.entities.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/questionnaire")
public class QuestionnaireRestController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping(value = "{id}")
    public Questionnaire getQuestionnaire(@PathVariable final Long id) {
        return questionnaireService.getQuestionnaire(id);
    }

    @GetMapping
    public List<Questionnaire> getQuestionnaires() {
        return questionnaireService.getQuestionnaires();
    }

    @PostMapping
    public Questionnaire saveQuestionnaire(@RequestBody final Questionnaire questionnaire) {
        return questionnaireService.createQuestionnaire(questionnaire);
    }

    @PutMapping(value = "{id}")
    public Questionnaire updateQuestionnaire(@PathVariable final Long id, @RequestBody final Questionnaire questionnaire) {
        return questionnaireService.updateQuestionnaire(id, questionnaire);
    }

    @DeleteMapping(value = "{id}")
    public void deleteQuestionnaire(@PathVariable final Long id) {
        questionnaireService.deleteQuestionnaire(id);
    }
}
