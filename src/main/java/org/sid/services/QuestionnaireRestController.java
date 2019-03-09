package org.sid.services;

import org.sid.business.QuestionnaireService;
import org.sid.entities.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Page<Questionnaire> getQuestionnaires(
            @RequestParam(value = "category", required = false) final Long idCategory,
            @RequestParam(value = "level", required = false) final Long idLevel,
            @RequestParam(value = "player", required = false) final Long idPlayer,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "validationDate") final String property,
            @RequestParam(value = "direction", defaultValue = "desc") final String direction
    ) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return questionnaireService.getQuestionnaires(idCategory, idLevel, idPlayer, PageRequest.of(page, size, new Sort(dir, property)));
    }

    @PostMapping
    public Questionnaire ceateQuestionnaire(@Valid @RequestBody final Questionnaire questionnaire) {
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
