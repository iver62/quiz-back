package org.sid.repository;

import org.sid.domain.entities.Questionnaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    Page<Questionnaire> findByCategoryId(final Long idCategory, final Pageable pageable);

    Page<Questionnaire> findByLevelId(final Long idLevel, final Pageable pageable);

    Page<Questionnaire> findByPlayerId(final Long idPlayer, final Pageable pageable);

    Page<Questionnaire> findByCategoryIdAndLevelId(final Long idCategory, final Long idLevel, final Pageable pageable);

    Page<Questionnaire> findByCategoryIdAndPlayerId(final Long idCategory, final Long idPlayer, final Pageable pageable);

    Page<Questionnaire> findByLevelIdAndPlayerId(final Long idLevel, final Long idPlayer, final Pageable pageable);

    Page<Questionnaire> findByCategoryIdAndLevelIdAndPlayerId(final Long idCategory, final Long idLevel, final Long idPlayer, final Pageable pageable);

}
