package org.sid.business;

import org.sid.entities.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LevelService {

    Level getLevel(final Long id);

    Page<Level> getLevels(final Pageable pageable);

    Level createLevel(final Level level);

    Level updateLevel(final Long id, final Level level);

    void deleteLevel(final Long id);
}
