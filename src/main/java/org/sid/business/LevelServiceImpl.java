package org.sid.business;

import org.sid.domain.entities.Level;
import org.sid.domain.entities.Question;
import org.sid.repository.LevelRepository;
import org.sid.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl extends AbstractServiceImpl<Level> implements LevelService {

    private final LevelRepository levelRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, QuestionRepository questionRepository) {
        this.levelRepository = levelRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<Question> getQuestions(final Long idLevel, final Pageable pageable) {
        return questionRepository.findByLevelId(idLevel, pageable);
    }

    @Override
    protected PagingAndSortingRepository<Level, Long> getRepository() {
        return levelRepository;
    }

//    @Override
//    public Level createLevel(final Level level) {
//        level.setName(Utils.capitalizeFirstLetter(level.getName()));
//        level.setCreationDate(new Date());
//        level.setLastUpdate(new Date());
//        return levelRepository.save(level);
//    }
//
//    @Override
//    public Level updateLevel(final Long id, final Level level) {
//        level.setId(id);
//        level.setName(Utils.capitalizeFirstLetter(level.getName()));
//        return levelRepository.save(level);
//    }
}
