package org.sid.services;

import java.util.List;

import org.sid.repository.CategoryRepository;
import org.sid.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/category")
public class CategoryRestService {

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Category getCategory(@PathVariable Long id) {
		return categoryRepository.findOne(id);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Category> getCategories() {
		return categoryRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	@RequestMapping(method=RequestMethod.POST)
	public Category save(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Category update(@PathVariable Long id, @RequestBody Category category) {
		category.setId(id);
		return categoryRepository.save(category);
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		categoryRepository.delete(id);
	}

}
