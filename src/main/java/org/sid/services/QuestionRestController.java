package org.sid.services;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.sid.business.QuestionServiceImpl;
import org.sid.domain.dto.QuestionDTO;
import org.sid.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/question")
public class QuestionRestController {

    private final QuestionServiceImpl questionService;

    private final ModelMapper modelMapper;

    @Autowired
    public QuestionRestController(QuestionServiceImpl questionService, ModelMapper modelMapper) {
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable final Long id) {
        return ResponseEntity.ok(questionService.getOne(id));
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
    public ResponseEntity<Page<Question>> getQuestions(
            @RequestParam(value = "category", required = false) final Long idCategory,
            @RequestParam(value = "level", required = false) final Long idLevel,
            @RequestParam(value = "player", required = false) final Long idPlayer,
            @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") final int size,
            @RequestParam(value = "property", required = false, defaultValue = "title") final String property,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return ResponseEntity.ok(questionService.getQuestions(idCategory, idLevel, idPlayer, PageRequest.of(page, size, Sort.by(dir, property))));
    }

    /**
     * @param questionDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody final QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.createQuestion(convertToEntity(questionDTO)));
    }

    /**
     * @param id
     * @param questionDTO
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable final Long id, @Valid @RequestBody final QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.updateQuestion(id, convertToEntity(questionDTO)));
    }

    /**
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteQuestion(@PathVariable final Long id) {
        questionService.delete(id);
    }

    private Question convertToEntity(final QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }

}
