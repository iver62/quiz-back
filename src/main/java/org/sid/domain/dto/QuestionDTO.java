package org.sid.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class QuestionDTO {

    private Long id;
    @NotNull
    @Size(min = 1, max = 256)
    private String title;
    @NotNull
    private Set<AnswerDTO> answers;
    private String image;
    @NotNull
    private CategoryDTO category;
    @NotNull
    private LevelDTO level;
    @NotNull
    private PlayerDTO player;
    private Date creationDate;
    private Date lastUpdate;
}
