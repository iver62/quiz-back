package org.sid.business;

import org.sid.entities.Category;
import org.sid.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategory(Long id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public Category createCategory(Category category) {
        category.setName(this.capitalizeFirstLetter(category.getName()));
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        category.setName(this.capitalizeFirstLetter(category.getName()));
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    private String capitalizeFirstLetter(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s.substring(0, 1).toUpperCase());
        stringBuilder.append(s.substring(1));
        return stringBuilder.toString();
    }
}
