package org.sid.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Questionnaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_player")
    private Player player;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "questionnaire_question", joinColumns = @JoinColumn(name = "questionnaire_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private double time;

    @Temporal(TemporalType.DATE)
    private Date validationDate;

}
