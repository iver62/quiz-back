package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Response implements Serializable {

	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private boolean correct;
	
	private String image;
	
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="question_id")
//	private Question question;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date creationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date lastUpdate;
	
	private static final long serialVersionUID = -3223103057340300097L;

	public Response() {	}

	public Response(String title, boolean correct, String image, Date creationDate, Date lastUpdate) {
		this.title = title;
		this.correct = correct;
		this.image = image;
		this.creationDate = creationDate;
		this.lastUpdate = lastUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public Question getQuestion() {
//		return question;
//	}

//	public void setQuestion(Question question) {
//		this.question = question;
//	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (correct ? 1231 : 1237);
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
//		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Response other = (Response) obj;
		if (correct != other.correct)
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
//		if (question == null) {
//			if (other.question != null)
//				return false;
//		} else if (!question.equals(other.question))
//			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Response [id=" + id + ", title=" + title + ", correct=" + correct + ", image=" + image
				+ ", creationDate=" + creationDate + ", lastUpdate=" + lastUpdate + "]";
	}

}
