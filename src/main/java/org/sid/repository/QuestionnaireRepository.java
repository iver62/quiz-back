package org.sid.repository;

import org.sid.domain.entities.Questionnaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    Page<Questionnaire> findByCategoryId(Long idCategory, Pageable pageable);

    Page<Questionnaire> findByLevelId(Long idLevel, Pageable pageable);

    Page<Questionnaire> findByPlayerId(Long idPlayer, Pageable pageable);

    Page<Questionnaire> findByCategoryIdAndLevelId(Long idCategory, Long idLevel, Pageable pageable);

    Page<Questionnaire> findByCategoryIdAndPlayerId(Long idCategory, Long idPlayer, Pageable pageable);

    Page<Questionnaire> findByLevelIdAndPlayerId(Long idLevel, Long idPlayer, Pageable pageable);

    Page<Questionnaire> findByCategoryIdAndLevelIdAndPlayerId(Long idCategory, Long idLevel, Long idPlayer, Pageable pageable);

}
