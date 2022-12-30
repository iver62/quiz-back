package org.sid.business;

import org.sid.domain.entities.Category;
import org.sid.domain.entities.Question;
import org.sid.repository.CategoryRepository;
import org.sid.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractServiceImpl<Category> implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, QuestionRepository questionRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<Question> getQuestions(final Long idCategory, final Pageable pageable) {
        return questionRepository.findByCategoryId(idCategory, pageable);
    }

//    @Override
//    public Category createCategory(final Category category) {
//        category.setName(Utils.capitalizeFirstLetter(category.getName()));
//        category.setCreationDate(new Date());
//        category.setLastUpdate(new Date());
//        return categoryRepository.save(category);
//    }

//    @Override
//    public Category updateCategory(final Long id, final Category category) {
//        category.setId(id);
//        category.setName(Utils.capitalizeFirstLetter(category.getName()));
//        return categoryRepository.save(category);
//    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

//    @Override
//    public void deleteCategory(final Long id) {
//        categoryRepository.deleteById(id);
//    }


}
