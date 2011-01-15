package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings({ "serial" })
@Entity
public class Presentation extends AbstractEntity {
	@ManyToOne
	private Speaker speaker;
	private String title;
	private String description;

	public String getTitle() {
		return title;
	}

	public Speaker getSpeaker() {
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
