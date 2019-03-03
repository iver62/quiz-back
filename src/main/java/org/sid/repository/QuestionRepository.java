package org.sid.repository;

import org.sid.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findByCategoryId(final Long idCategory, final Pageable pageable);

    Page<Question> findByLevelId(final Long idLevel, final Pageable pageable);

    Page<Question> findByPlayerId(final Long idPlayer, final Pageable pageable);

    Page<Question> findByCategoryIdAndLevelId(final Long idCategory, final Long idLevel, final Pageable pageable);

    Page<Question> findByCategoryIdAndPlayerId(final Long idCategory, final Long idPlayer, final Pageable pageable);

    Page<Question> findByLevelIdAndPlayerId(final Long idLevel, final Long idPlayer, final Pageable pageable);

    Page<Question> findByCategoryIdAndLevelIdAndPlayerId(final Long idCategory, final Long idLevel, final Long idPlayer, final Pageable pageable);

    List<Question> findByCategoryIdAndLevelIdAndPlayerIdNot(final Long idCategory, final Long idLevel, final Long idPlayer);

}
