package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class SimpleContent extends AbstractEntity implements WithTitle {

	private String title;

	@Lob
	private String content;

	@Override
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

}
