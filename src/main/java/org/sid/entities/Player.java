package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.sid.security.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pseudo;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String photo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date inscription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdate;

}
