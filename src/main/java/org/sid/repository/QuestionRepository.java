package org.sid.repository;

import org.sid.domain.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findByCategoryId(Long idCategory, Pageable pageable);

    Page<Question> findByLevelId(Long idLevel, Pageable pageable);

    Page<Question> findByPlayerId(Long idPlayer, Pageable pageable);

    Page<Question> findByCategoryIdAndLevelId(Long idCategory, Long idLevel, Pageable pageable);

    Page<Question> findByCategoryIdAndPlayerId(Long idCategory, Long idPlayer, Pageable pageable);

    Page<Question> findByLevelIdAndPlayerId(Long idLevel, Long idPlayer, Pageable pageable);

    Page<Question> findByCategoryIdAndLevelIdAndPlayerId(Long idCategory, Long idLevel, Long idPlayer, Pageable pageable);

    List<Question> findByCategoryIdAndLevelIdAndPlayerIdNot(Long idCategory, Long idLevel, Long idPlayer);

}
