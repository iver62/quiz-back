package org.sid.business;

import org.sid.domain.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    Page<Question> getQuestions(final Long idPlayer, final Pageable pageable);

}
