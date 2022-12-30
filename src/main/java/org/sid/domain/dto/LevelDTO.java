package org.sid.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class LevelDTO {

    private Long id;
    @NotNull
    @Size(min = 1, max = 256)
    private String name;
    private String image;
    private Date creationDate;
    private Date lastUpdate;
}
