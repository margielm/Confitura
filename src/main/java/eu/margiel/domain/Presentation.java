package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@SuppressWarnings({ "serial" })
@Entity
public class Presentation extends AbstractEntity {
	private String title;
	@Lob
	private String description;
	@ManyToOne
	private Speaker speaker;

	private boolean accepted = false;

	public String getTitle() {
		return title;
	}

	public Presentation setTitle(String title) {
		this.title = title;
		return this;
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

	public boolean isAccepted() {
		return accepted;
	}

	public Presentation toggleAccepted() {
		this.accepted = !this.accepted;
		return this;
	}
}
