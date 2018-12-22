package org.sid.services;

import java.util.List;

import org.sid.repository.QuestionRepository;
import org.sid.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/question")
public class QuestionRestService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Question getQuestion(@PathVariable Long id) {
		return questionRepository.findOne(id);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Question> getQuestions() {
		return questionRepository.findAll(new Sort(Sort.Direction.ASC, "title"));
	}

	@RequestMapping(method=RequestMethod.POST)
	public Question saveQuestion(@RequestBody Question question) {
		return questionRepository.save(question);
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
		question.setId(id);
		return questionRepository.save(question);
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void deleteQuestion(@PathVariable Long id) {
		questionRepository.delete(id);
	}

}
