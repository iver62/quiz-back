package org.sid.services;

import org.sid.business.QuestionService;
import org.sid.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/question")
public class QuestionRestController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "{id}")
    public Question getQuestion(@PathVariable final Long id) {
        return questionService.getQuestion(id);
    }

    @GetMapping
    public Page<Question> getQuestions(
            @RequestParam(value = "category", required = false) final Long idCategory,
            @RequestParam(value = "level", required = false) final Long idLevel,
            @RequestParam(value = "player", required = false) final Long idPlayer,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "title") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return questionService.getQuestions(idCategory, idLevel, idPlayer, PageRequest.of(page, size, new Sort(dir, property)));
    }

    @PostMapping
    public Question createQuestion(@Valid @RequestBody final Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping(value = "{id}")
    public Question updateQuestion(@PathVariable final Long id, @Valid @RequestBody final Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping(value = "{id}")
    public void deleteQuestion(@PathVariable final Long id) {
        questionService.deleteQuestion(id);
    }

}
