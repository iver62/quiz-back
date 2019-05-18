package org.sid.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sid.security.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PlayerDTO {

    private Long id;
    @NotNull
    @Size(min = 1, max = 256)
    private String pseudo;
    @NotNull
    @Size(min = 1, max = 256)
    private String password;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private Role role;
    private String photo;
    @NotNull
    private Date inscription;
    @NotNull
    private Date lastUpdate;
}
