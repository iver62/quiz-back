package org.sid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class AnswerDTO {

    private Long id;
    @NotNull
    @Size(min = 1, max = 256)
    private String title;
    @NotNull
    private boolean correct;
    @NotNull
    private QuestionDTO question;
    private String image;
}
