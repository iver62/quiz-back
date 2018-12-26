package org.sid.business;

import org.sid.entities.Level;
import org.sid.repository.LevelRepository;
import org.sid.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public Level getLevel(final Long id) {
        return this.levelRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Level> getLevels(final Pageable pageable) {
        return this.levelRepository.findAll(pageable);
    }

    @Override
    public Level createLevel(final Level level) {
        level.setName(Utils.capitalizeFirstLetter(level.getName()));
        return this.levelRepository.save(level);
    }

    @Override
    public Level updateLevel(final Long id, final Level level) {
        level.setId(id);
        level.setName(Utils.capitalizeFirstLetter(level.getName()));
        return this.levelRepository.save(level);
    }

    @Override
    public void deleteLevel(final Long id) {
        this.levelRepository.deleteById(id);
    }
}
