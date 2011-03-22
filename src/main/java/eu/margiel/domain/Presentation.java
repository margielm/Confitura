package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings({ "serial" })
@Entity
public class Presentation extends AbstractEntity {
	private String title;
	private String description;
	@ManyToOne
	private Speaker speaker;

	public String getTitle() {
		return title;
	}

	public User getSpeaker() {
		return speaker;
	}

	public Presentation speaker(Speaker speaker) {
		this.speaker = speaker;
		return this;
	}

	public String getDescription() {
		return description;
	}
}
