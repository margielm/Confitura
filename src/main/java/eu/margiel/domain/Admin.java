package eu.margiel.domain;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Admin extends User {

	private String userName;

	public Admin() {
	}

	public Admin(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
