package org.sid.business;

import org.sid.domain.entities.Category;
import org.sid.domain.entities.Question;
import org.sid.repository.CategoryRepository;
import org.sid.repository.QuestionRepository;
import org.sid.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Category getCategory(final Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Category> getCategories(final Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Question> getQuestions(Long idCategory, Pageable pageable) {
        return questionRepository.findByCategoryId(idCategory, pageable);
    }

    @Override
    public Category createCategory(final Category category) {
        category.setName(Utils.capitalizeFirstLetter(category.getName()));
        category.setCreationDate(new Date());
        category.setLastUpdate(new Date());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(final Long id, final Category category) {
        category.setId(id);
        category.setName(Utils.capitalizeFirstLetter(category.getName()));
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(final Long id) {
        categoryRepository.deleteById(id);
    }

}
