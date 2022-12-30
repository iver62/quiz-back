package org.sid.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.sid.security.Role;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class PlayerDTO {

    private Long id;
    @NotBlank
    @Size(min = 1, max = 256)
    private String pseudo;
    @Size(min = 1, max = 256)
    private String lastname;
    @Size(min = 1, max = 256)
    private String firstname;
    @Size(min = 1, max = 256)
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 1, max = 256)
    private String password;
    @NotNull
    private Date birthDate;
    @NotNull
    private Role role;
    private String photo;
    private LocalDateTime inscription;
    private LocalDateTime lastUpdate;

}
