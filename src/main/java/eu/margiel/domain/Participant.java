package eu.margiel.domain;

import java.util.Date;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Participant extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String mail;
	private String sex;
	private Date registrationDate = new Date();

	public String getMail() {
		return mail;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSex() {
		return sex;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}
}
