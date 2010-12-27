package eu.margiel.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("STATIC")
public class StaticContent extends DynamicContent {
	private String title;

	@Lob
	private String content;

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "StaticContent [id=" + getId() + ", title=" + title + "]";
	}

	@Override
	public String getName() {
		return title;
	}

}
