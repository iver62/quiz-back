package org.sid.services;

import org.sid.business.QuestionService;
import org.sid.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @PostMapping
    public Question saveQuestion(@RequestBody final Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping(value = "{id}")
    public Question updateQuestion(@PathVariable final Long id, @RequestBody final Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping(value = "{id}")
    public void deleteQuestion(@PathVariable final Long id) {
        questionService.deleteQuestion(id);
    }

}
