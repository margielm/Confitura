package eu.margiel.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class News extends AbstractEntity {
	private Date creationDate = new Date();
	private String title;
	@Lob
	private String shortDescription;
	@Lob
	private String description;
	@ManyToOne
	private Admin autor;

	public String getTitle() {
		return title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public News autor(Admin autor) {
		this.autor = autor;
		return this;
	}

	public Admin getAutor() {
		return autor;
	}

}
