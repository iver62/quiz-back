package org.sid.business;

import org.sid.domain.entities.Level;
import org.sid.domain.entities.Question;
import org.sid.repository.LevelRepository;
import org.sid.repository.QuestionRepository;
import org.sid.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Level getLevel(final Long id) {
        return levelRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Level> getLevels(final Pageable pageable) {
        return levelRepository.findAll(pageable);
    }

    @Override
    public Page<Question> getQuestions(Long idLevel, Pageable pageable) {
        return questionRepository.findByLevelId(idLevel, pageable);
    }

    @Override
    public Level createLevel(final Level level) {
        level.setName(Utils.capitalizeFirstLetter(level.getName()));
        level.setCreationDate(new Date());
        level.setLastUpdate(new Date());
        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(final Long id, final Level level) {
        level.setId(id);
        level.setName(Utils.capitalizeFirstLetter(level.getName()));
        return levelRepository.save(level);
    }

    @Override
    public void deleteLevel(final Long id) {
        levelRepository.deleteById(id);
    }
}
