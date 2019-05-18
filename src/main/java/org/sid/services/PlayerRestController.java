package org.sid.services;

import org.modelmapper.ModelMapper;
import org.sid.business.PlayerService;
import org.sid.domain.dto.PlayerDTO;
import org.sid.domain.entities.Player;
import org.sid.domain.entities.Question;
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

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public Player getUser(@PathVariable final Long id) {
        return playerService.getPlayer(id);
    }

    /**
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
    @GetMapping
    public Page<Player> getUsers(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "pseudo") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return playerService.getPlayers(PageRequest.of(page, size, new Sort(dir, property)));
    }

    /**
     * @param id
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
    @GetMapping(value = "{id}/questions")
    public Page<Question> getQuestions(
            @PathVariable final Long id,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "title") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return playerService.getQuestions(id, PageRequest.of(page, size, new Sort(dir, property)));
    }

    /**
     * @param playerDTO
     * @return
     */
    @PostMapping
    public Player createPlayer(@Valid @RequestBody final PlayerDTO playerDTO) {
        return playerService.createPlayer(convertToEntity(playerDTO));
    }

    /**
     * @param id
     * @param playerDTO
     * @return
     */
    @PutMapping(value = "{id}")
    public Player updatePlayer(@PathVariable final Long id, @Valid @RequestBody final PlayerDTO playerDTO) {
        return playerService.updatePlayer(id, convertToEntity(playerDTO));
    }

    /**
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deletePlayer(@PathVariable final Long id) {
        playerService.deletePlayer(id);
    }

    private Player convertToEntity(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

}
