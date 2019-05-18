package org.sid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    private Date creationDate;
    @NotNull
    private Date lastUpdate;
}
