package org.sid.services;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.sid.business.CategoryServiceImpl;
import org.sid.domain.dto.CategoryDTO;
import org.sid.domain.entities.Category;
import org.sid.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/category")
public class CategoryRestController {

    private final CategoryServiceImpl categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryRestController(CategoryServiceImpl categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<Category> getCategory(@PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.getOne(id));
    }

    /**
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "name") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return ResponseEntity.ok(categoryService.getAll(PageRequest.of(page, size, Sort.by(dir, property))));
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
        return ResponseEntity.ok(categoryService.getQuestions(id, PageRequest.of(page, size, Sort.by(dir, property))));
    }

    /**
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody final CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        return ResponseEntity.ok(categoryService.create(category));
    }

    /**
     * @param id
     * @param categoryDTO
     * @return
     */
    @PutMapping(value = "{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable final Long id, @Valid @RequestBody final CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    /**
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deleteCategory(@PathVariable final Long id) {
        categoryService.delete(id);
    }

    private Category convertToEntity(final CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

}
