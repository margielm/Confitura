package eu.margiel.domain;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.jasypt.util.password.StrongPasswordEncryptor;

import eu.margiel.pages.javarsovia.c4p.SubfolderPhotoProvider;

@SuppressWarnings("serial")
@MappedSuperclass
public class User extends AbstractEntity {
	@Transient
	private transient StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
	private String firstName;
	private String lastName;
	protected String mail;
	private String password;
	@Lob
	private String bio;
	@Transient
	private transient SubfolderPhotoProvider provider = new SubfolderPhotoProvider(getSubfolderName());

	public User() {
	}

	public String getPassword() {
		return password;
	}

	public User password(String password) {
		this.password = password;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T firstName(String firstName) {
		this.firstName = firstName;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T lastName(String lastName) {
		this.lastName = lastName;
		return (T) this;
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

	public void passwordWithEncryption(String password) {
		this.password = password;
		encryptPassword();
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public String getBio() {
		return bio;
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T mail(String mail) {
		this.mail = mail;
		return (T) this;
	}

	String getSubfolderName() {
		return "";
	}

	public String getPathToPhoto() {
		return provider.getPathTo(this);
	}

	public void savePhoto(FileUpload fileUpload) {
		provider.savePhoto(fileUpload, this);
	}

}
