package org.sid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class QuestionnaireDTO {

    private Long id;
    @NotNull
    @Range(min = 0, max = 20)
    private int number;
    @NotNull
    private PlayerDTO player;
    @NotNull
    private CategoryDTO category;
    @NotNull
    private LevelDTO level;
    @NotNull
    private Set<QuestionDTO> questions;
    @Range(min = 0, max = 20)
    private Double score;
    private Double time;
    private Date validationDate;
}
