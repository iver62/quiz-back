package org.sid.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
