package org.sid.services;

import org.sid.business.LevelService;
import org.sid.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/level")
public class LevelRestController {

    @Autowired
    private LevelService levelService;

    @GetMapping(value = "{id}")
    public Level getLevel(@PathVariable Long id) {
        return levelService.getLevel(id);
    }

    @GetMapping
    public List<Level> getLevels() {
        return levelService.getLevels();
    }

    @PostMapping
    public Level save(@RequestBody Level level) {
        return levelService.createLevel(level);
    }

    @PutMapping(value = "{id}")
    public Level update(@PathVariable Long id, @RequestBody Level level) {
        return levelService.updateLevel(id, level);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        levelService.deleteLevel(id);
    }

}
