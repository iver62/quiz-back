package org.sid.services;

import org.modelmapper.ModelMapper;
import org.sid.business.QuestionnaireService;
import org.sid.domain.dto.QuestionnaireDTO;
import org.sid.domain.entities.Questionnaire;
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

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public Questionnaire getQuestionnaire(@PathVariable final Long id) {
        return questionnaireService.getQuestionnaire(id);
    }

    /**
     * @param idCategory
     * @param idLevel
     * @param idPlayer
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
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

    /**
     * @param questionnaireDTO
     * @return
     */
    @PostMapping
    public Questionnaire ceateQuestionnaire(@Valid @RequestBody final QuestionnaireDTO questionnaireDTO) {
        return questionnaireService.createQuestionnaire(convertToEntity(questionnaireDTO));
    }

    /**
     * @param id
     * @param questionnaireDTO
     * @return
     */
    @PutMapping(value = "{id}")
    public Questionnaire updateQuestionnaire(@PathVariable final Long id, @RequestBody final QuestionnaireDTO questionnaireDTO) {
        return questionnaireService.updateQuestionnaire(id, convertToEntity(questionnaireDTO));
    }

    /**
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteQuestionnaire(@PathVariable final Long id) {
        questionnaireService.deleteQuestionnaire(id);
    }

    private Questionnaire convertToEntity(QuestionnaireDTO questionnaireDTO) {
        return modelMapper.map(questionnaireDTO, Questionnaire.class);
    }
}
