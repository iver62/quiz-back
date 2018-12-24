package org.sid.services;

import org.sid.business.CategoryService;
import org.sid.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public Category save(@RequestBody final Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping(value = "{id}")
    public Category update(@PathVariable final Long id, @RequestBody final Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final Long id) {
        categoryService.deleteCategory(id);
    }

}
