package org.sid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
