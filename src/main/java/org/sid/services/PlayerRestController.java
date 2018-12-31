package org.sid.services;

import org.sid.business.PlayerService;
import org.sid.entities.Player;
import org.sid.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/user")
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "{id}")
    public Player getUser(@PathVariable final Long id) {
        return this.playerService.getPlayer(id);
    }

    @GetMapping
    public Page<Player> getUsers(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "pseudo") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return this.playerService.getPlayers(PageRequest.of(page, size, new Sort(dir, property)));
    }

    @GetMapping(value = "{id}/questions")
    public Page<Question> getQuestions(
            @PathVariable final Long id,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "title") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return this.playerService.getQuestions(id, PageRequest.of(page, size, new Sort(dir, property)));
    }

    @PostMapping
    public Player create(@Valid @RequestBody final Player player) {
        return this.playerService.createPlayer(player);
    }

    @PutMapping(value = "{id}")
    public Player update(@PathVariable final Long id, @Valid @RequestBody final Player player) {
        return this.playerService.updatePlayer(id, player);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final Long id) {
        this.playerService.deletePlayer(id);
    }

}
