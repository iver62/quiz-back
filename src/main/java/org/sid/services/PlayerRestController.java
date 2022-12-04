package org.sid.services;

import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.sid.business.PlayerServiceImpl;
import org.sid.domain.dto.PlayerDTO;
import org.sid.domain.entities.Player;
import org.sid.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/user")
public class PlayerRestController {

    private final PlayerServiceImpl playerService;

    private final ModelMapper modelMapper;

    @Autowired
    public PlayerRestController(PlayerServiceImpl playerService, ModelMapper modelMapper) {
        this.playerService = playerService;
        this.modelMapper = modelMapper;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<Player> getUser(@PathVariable final Long id) {
        return ResponseEntity.ok(playerService.getOne(id));
    }

    /**
     * @param page
     * @param size
     * @param property
     * @param direction
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<Player>> getUsers(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "pseudo") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return ResponseEntity.ok(playerService.getAll(PageRequest.of(page, size, new Sort(dir, property))));
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
    public ResponseEntity<Page<Question>> getQuestions(
            @PathVariable final Long id,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "property", defaultValue = "title") final String property,
            @RequestParam(value = "direction", defaultValue = "asc") final String direction) {
        Sort.Direction dir = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return ResponseEntity.ok(playerService.getQuestions(id, PageRequest.of(page, size, new Sort(dir, property))));
    }

    /**
     * @param playerDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody final PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.create(convertToEntity(playerDTO)));
    }

    /**
     * @param id
     * @param playerDTO
     * @return
     */
    @PutMapping(value = "{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable final Long id, @Valid @RequestBody final PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.update(id, convertToEntity(playerDTO)));
    }

    /**
     * @param id
     */
    @DeleteMapping(value = "{id}")
    public void deletePlayer(@PathVariable final Long id) {
        playerService.delete(id);
    }

    private Player convertToEntity(final PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

}
