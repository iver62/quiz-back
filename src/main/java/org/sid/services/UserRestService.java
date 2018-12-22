package org.sid.services;

import java.util.List;

import org.sid.repository.UserRepository;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/user")
public class UserRestService {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		return userRepository.findOne(id);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<User> getUsers() {
		return userRepository.findAll(new Sort(Sort.Direction.ASC, "pseudo"));
	}

	@RequestMapping(method=RequestMethod.POST)
	public User save(@RequestBody User user) {
		return userRepository.save(user);
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public User update(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		userRepository.delete(id);
	}

}
