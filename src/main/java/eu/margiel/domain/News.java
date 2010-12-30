package eu.margiel.domain;

import java.util.Date;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class News extends AbstractEntity {
	private Date creationDate = new Date();
	private String title;
	private String shortDescription;
	private String description;

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

}
