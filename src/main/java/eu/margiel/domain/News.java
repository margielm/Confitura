package eu.margiel.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class News implements Serializable {
	@SuppressWarnings("unused")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
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
