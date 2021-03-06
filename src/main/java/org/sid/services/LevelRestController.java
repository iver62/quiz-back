package org.sid.services;

import org.modelmapper.ModelMapper;
import org.sid.business.LevelService;
import org.sid.domain.dto.LevelDTO;
import org.sid.domain.entities.Level;
import org.sid.domain.entities.Question;
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

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "{id}")
    public Level getLevel(@PathVariable final Long id) {
        return levelService.getLevel(id);
    }

    /**
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
    @GetMapping
    public Page<Level> getLevels(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "name") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return levelService.getLevels(PageRequest.of(page, size, new Sort(dir, property)));
    }

    /**
     * @param id
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
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

    /**
     * @param levelDTO
     * @return
     */
    @PostMapping
    public Level createLevel(@Valid @RequestBody final LevelDTO levelDTO) {
        Level level = convertToEntity(levelDTO);
        return levelService.createLevel(level);
    }

    /**
     * @param id
     * @param levelDTO
     * @return
     */
    @PutMapping(value = "{id}")
    public Level updateLevel(@PathVariable final Long id, @Valid @RequestBody final LevelDTO levelDTO) {
        Level level = convertToEntity(levelDTO);
        return levelService.updateLevel(id, level);
    }

    /**
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteLevel(@PathVariable final Long id) {
        levelService.deleteLevel(id);
    }

    private Level convertToEntity(LevelDTO levelDTO) {
        return modelMapper.map(levelDTO, Level.class);
    }
}
