package org.sid.services;

import org.sid.business.LevelService;
import org.sid.entities.Level;
import org.sid.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/level")
public class LevelRestController {

    @Autowired
    private LevelService levelService;

    @GetMapping(value = "{id}")
    public Level getLevel(@PathVariable final Long id) {
        return levelService.getLevel(id);
    }

    @GetMapping
    public Page<Level> getLevels(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "name") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return levelService.getLevels(PageRequest.of(page, size, new Sort(dir, property)));
    }

    @GetMapping(value = "{id}/questions")
    public Page<Question> getQuestions(
            @PathVariable final Long id,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "title") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return levelService.getQuestions(id, PageRequest.of(page, size, new Sort(dir, property)));
    }

    @PostMapping
    public Level create(@Valid @RequestBody final Level level) {
        return levelService.createLevel(level);
    }

    @PutMapping(value = "{id}")
    public Level update(@PathVariable final Long id, @Valid @RequestBody final Level level) {
        return levelService.updateLevel(id, level);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final Long id) {
        levelService.deleteLevel(id);
    }

}
