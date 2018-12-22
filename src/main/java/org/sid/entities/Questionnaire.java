package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Questionnaire implements Serializable {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="questionnaire_question", joinColumns=@JoinColumn(name="questionnaire_id"), inverseJoinColumns=@JoinColumn(name="question_id"))
	private List<Question> questions;

	@Column(nullable=false)
	private double score;

	private double time;

	@Temporal(TemporalType.DATE)
	private Date validationDate;

	private static final long serialVersionUID = -7274342274359381203L;

	public Questionnaire() {}

	public Questionnaire(User user, List<Question> questions, double score, double time, Date validationDate) {
		this.user = user;
		this.questions = questions;
		this.score = score;
		this.time = time;
		this.validationDate = validationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(time);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((validationDate == null) ? 0 : validationDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questionnaire other = (Questionnaire) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
			return false;
		if (Double.doubleToLongBits(time) != Double.doubleToLongBits(other.time))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (validationDate == null) {
			if (other.validationDate != null)
				return false;
		} else if (!validationDate.equals(other.validationDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Questionnaire [id=" + id + ", user=" + user + ", questions=" + questions + ", score=" + score
				+ ", time=" + time + ", validationDate=" + validationDate + "]";
	}

}
