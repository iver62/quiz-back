package org.sid.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Questionnaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_player")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_category")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_level")
    private Level level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "questionnaire_question", joinColumns = @JoinColumn(name = "questionnaire_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;

    private Double score;

    private Double time;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validationDate;

}
