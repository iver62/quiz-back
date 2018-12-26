package org.sid.business;

import org.sid.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category getCategory(final Long id);

    Page<Category> getCategories(final Pageable pageable);

    Category createCategory(final Category category);

    Category updateCategory(final Long id, final Category category);

    void deleteCategory(final Long id);

}
