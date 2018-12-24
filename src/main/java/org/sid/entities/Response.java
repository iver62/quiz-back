package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean correct;

    private String image;

//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="question_id")
//	private Question question;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date lastUpdate;

}
