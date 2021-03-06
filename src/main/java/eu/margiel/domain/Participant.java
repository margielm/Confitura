package eu.margiel.domain;

import static eu.margiel.domain.RegistrationStatus.*;

import java.util.Date;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Participant extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String mail;
	private String sex;
	private String city;
	private Date registrationDate = new Date();
	private String token;
	private RegistrationStatus registrationType = RegistrationStatus.NEW;
	private boolean participated = false;
	private boolean lunch;

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

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void confirm() {
		this.registrationType = CONFIRMED;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationType;
	}

	public boolean isConfirmed() {
		return this.registrationType == CONFIRMED;
	}

	public boolean isFinalConfirmed() {
		return this.registrationType == FINAL_CONFIRMED;
	}

	public void lunch(boolean lunch) {
		this.lunch = lunch;
	}

	public boolean isLunch() {
		return lunch;
	}

	public String getCity() {
		return city == null ? "" : city;
	}

	public Participant city(String city) {
		this.city = city;
		return this;
	}

	public String getLowerCaseCity() {
		return getCity().toLowerCase();
	}

	public void finalConfirmation() {
		this.registrationType = FINAL_CONFIRMED;
	}

	public boolean isCancel() {
		return registrationType == CANCELED;
	}

	public void cancel() {
		this.registrationType = CANCELED;
	}

	public boolean participated() {
		return participated;
	}

}
