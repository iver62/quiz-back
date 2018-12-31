package org.sid.repository;

import org.sid.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.category.id = :c")
    Page<Question> findByCategory(@Param("c") final Long idCategory, Pageable pageable);

    @Query("SELECT q FROM Question q WHERE q.level.id = :l")
    Page<Question> findByLevel(@Param("l") final Long idLevel, Pageable pageable);

    @Query("SELECT q FROM Question q WHERE q.player.id = :p")
    Page<Question> findByPlayer(@Param("p") final Long idPlayer, Pageable pageable);

}
