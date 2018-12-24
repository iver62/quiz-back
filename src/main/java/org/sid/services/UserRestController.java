package org.sid.services;

import org.sid.business.UserService;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    public User getUser(@PathVariable final Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User save(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @PutMapping(value = "{id}")
    public User update(@PathVariable final Long id, @RequestBody final User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final Long id) {
        userService.deleteUser(id);
    }

}
