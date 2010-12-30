package eu.margiel.domain;

import javax.persistence.Entity;

@SuppressWarnings({ "unused", "serial" })
@Entity
public class User extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String userName;
	private String eMail;
	private String password;

	public String getPassword() {
		return password;
	}

	public User password(String password) {
		this.password = password;
		return this;
	}

}
