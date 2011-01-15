package eu.margiel.domain;

import javax.persistence.Entity;

import org.jasypt.util.password.StrongPasswordEncryptor;

@SuppressWarnings("serial")
@Entity
public class User extends AbstractEntity {
	private transient StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
	private String firstName;
	private String lastName;
	private String userName;
	protected String mail;
	private String password;

	public User() {
	}

	public User(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public User password(String password) {
		this.password = password;
		return this;
	}

	public User firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public User lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMail() {
		return mail;
	}

	public boolean passwordIsCorrect(String plainPassword) {
		return encryptor.checkPassword(plainPassword, this.password);
	}

	public void encryptPassword() {
		this.password = encryptor.encryptPassword(this.password);
	}

}
