package org.sid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;

import org.sid.repository.CategoryRepository;
import org.sid.repository.LevelRepository;
import org.sid.repository.QuestionRepository;
import org.sid.repository.QuestionnaireRepository;
import org.sid.repository.UserRepository;
import org.sid.entities.Category;
import org.sid.entities.Level;
import org.sid.entities.Question;
import org.sid.entities.Questionnaire;
import org.sid.entities.Response;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.sid.security.Role;

@SpringBootApplication
public class QuizzApplication implements CommandLineRunner {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizzApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		Level level1 = new Level("Facile", "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		this.levelRepository.save(level1);
		
		Category category1 = new Category("Sport", "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		this.categoryRepository.save(category1);
		
		User user1 = new User("Jack", "password", "jack@mail.fr", Role.USER, "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		this.userRepository.save(user1);
		
		Response response1 = new Response("réponse1", true, "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		Response response2 = new Response("réponse2", true, "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		Response response3 = new Response("réponse3", false, "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		Response response4 = new Response("réponse4", true, "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		Response response5 = new Response("réponse5", true, "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		Response response6 = new Response("réponse6", false, "", df.parse("08/05/2018"), df.parse("08/05/2018"));
		
		Question question1 = new Question("Question1 ?", new HashSet<Response>(Arrays.asList(response1, response2, response3)), "", level1, category1, user1, df.parse("08/05/2018"), df.parse("08/05/2018"));
		Question question2 = new Question("Question2 ?", new HashSet<Response>(Arrays.asList(response4, response5, response6)), "", level1, category1, user1, df.parse("08/05/2018"), df.parse("08/05/2018"));
		this.questionRepository.save(Arrays.asList(question1, question2));
		
		Questionnaire questionnaire1 = new Questionnaire(user1, Arrays.asList(question1, question2), 10, 10, df.parse("08/05/2018"));
		System.out.println("////////////////////////////////////////////" + questionnaire1);
		this.questionnaireRepository.save(questionnaire1);
	}
}
