package org.sid.business;

import org.sid.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<Category> getCategories(Pageable pageable);

    Category getCategory(final Long id);

    Category createCategory(final Category category);

    Category updateCategory(final Long id, final Category category);

    void deleteCategory(final Long id);

}
