package org.sid.services;

import java.util.List;

import org.sid.repository.QuestionnaireRepository;
import org.sid.entities.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/questionnaire")
public class QuestionnaireRestService {
	
	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Questionnaire getQuestionnaire(@PathVariable Long id) {
		return questionnaireRepository.findOne(id);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Questionnaire> getQuestionnaires() {
		return questionnaireRepository.findAll();
	}

	@RequestMapping(method=RequestMethod.POST)
	public Questionnaire saveQuestionnaire(@RequestBody Questionnaire questionnaire) {
		return questionnaireRepository.save(questionnaire);
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Questionnaire updateQuestionnaire(@PathVariable Long id, @RequestBody Questionnaire questionnaire) {
		questionnaire.setId(id);
		return questionnaireRepository.save(questionnaire);
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void deleteQuestionnaire(@PathVariable Long id) {
		questionnaireRepository.delete(id);
	}
}
