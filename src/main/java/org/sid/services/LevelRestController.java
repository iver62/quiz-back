package org.sid.services;

import org.modelmapper.ModelMapper;
import org.sid.business.LevelServiceImpl;
import org.sid.domain.dto.LevelDTO;
import org.sid.domain.entities.Level;
import org.sid.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/level")
public class LevelRestController {

    private final LevelServiceImpl levelService;

    private final ModelMapper modelMapper;

    @Autowired
    public LevelRestController(LevelServiceImpl levelService, ModelMapper modelMapper) {
        this.levelService = levelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Level> getLevel(@PathVariable final Long id) {
        return ResponseEntity.ok(levelService.getOne(id));
    }

    /**
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<Level>> getLevels(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "name") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return ResponseEntity.ok(levelService.getAll(PageRequest.of(page, size, new Sort(dir, property))));
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
    public ResponseEntity<Page<Question>> getQuestions(
            @PathVariable final Long id,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "title") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return ResponseEntity.ok(levelService.getQuestions(id, PageRequest.of(page, size, new Sort(dir, property))));
    }

    /**
     * @param levelDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Level> createLevel(@Valid @RequestBody final LevelDTO levelDTO) {
        return ResponseEntity.ok(levelService.create(convertToEntity(levelDTO)));
    }

    /**
     * @param id
     * @param levelDTO
     * @return
     */
    @PutMapping(value = "{id}")
    public ResponseEntity<Level> updateLevel(@PathVariable final Long id, @Valid @RequestBody final LevelDTO levelDTO) {
        return ResponseEntity.ok(levelService.update(id, convertToEntity(levelDTO)));
    }

    /**
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteLevel(@PathVariable final Long id) {
        levelService.delete(id);
    }

    private Level convertToEntity(final LevelDTO levelDTO) {
        return modelMapper.map(levelDTO, Level.class);
    }
}
