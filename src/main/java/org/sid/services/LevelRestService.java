package org.sid.services;

import java.util.List;

import org.sid.repository.LevelRepository;
import org.sid.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/level")
public class LevelRestService {
	
	@Autowired
	private LevelRepository levelRepository;

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Level getLevel(@PathVariable Long id) {
		return levelRepository.findOne(id);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Level> getLevels() {
		return levelRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	@RequestMapping(method=RequestMethod.POST)
	public Level save(@RequestBody Level level) {
		return levelRepository.save(level);
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Level update(@PathVariable Long id, @RequestBody Level level) {
		level.setId(id);
		return levelRepository.save(level);
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		System.out.println("'''''''''''''''''''''''''''''''''''" + id);
		levelRepository.delete(id);
	}

}
