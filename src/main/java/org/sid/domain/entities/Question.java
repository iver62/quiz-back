package org.sid.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    private Set<Answer> answers;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_category")
    @ToString.Exclude
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_level")
    @ToString.Exclude
    private Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_player")
    @ToString.Exclude
    private Player player;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdate;

}
