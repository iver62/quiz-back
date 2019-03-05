package org.sid.services;

import org.sid.business.CategoryService;
import org.sid.entities.Category;
import org.sid.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "{id}")
    public Category getCategory(@PathVariable final Long id) {
        return categoryService.getCategory(id);
    }

    @GetMapping
    public Page<Category> getCategories(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "name") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return categoryService.getCategories(PageRequest.of(page, size, new Sort(dir, property)));
    }

    @GetMapping(value = "{id}/questions")
    public Page<Question> getQuestions(
            @PathVariable final Long id,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "title") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return categoryService.getQuestions(id, PageRequest.of(page, size, new Sort(dir, property)));
    }

    @PostMapping
    public Category createCategory(@Valid @RequestBody final Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping(value = "{id}")
    public Category updateCategory(@PathVariable final Long id, @Valid @RequestBody final Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCategory(@PathVariable final Long id) {
        categoryService.deleteCategory(id);
    }

}
