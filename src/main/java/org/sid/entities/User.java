package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.sid.security.Role;

@Entity
public class User implements Serializable {

	@Id @GeneratedValue
	private Long id;

	@Column(nullable=false, unique=true)
	private String pseudo;

	@Column(nullable=false)
	private String password;

	@Column(nullable=false, unique=true)
	private String email;

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;

	private String photo;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date inscription;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date lastUpdate;

	private static final long serialVersionUID = 7500412288765767143L;

	public User() {	}

	public User(String pseudo, String password, String email, Role role, String photo, Date inscription,
			Date lastUpdate) {
		this.pseudo = pseudo;
		this.password = password;
		this.email = email;
		this.role = role;
		this.photo = photo;
		this.inscription = inscription;
		this.lastUpdate = lastUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getInscription() {
		return inscription;
	}

	public void setInscription(Date inscription) {
		this.inscription = inscription;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inscription == null) ? 0 : inscription.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inscription == null) {
			if (other.inscription != null)
				return false;
		} else if (!inscription.equals(other.inscription))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pseudo=" + pseudo + ", password=" + password + ", email=" + email + ", role="
				+ role + ", photo=" + photo + ", inscription=" + inscription + ", lastUpdate=" + lastUpdate + "]";
	}

}
