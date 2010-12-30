package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class SimpleContent implements WithTitle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

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

	@Override
	public Integer getId() {
		return id;
	}
}
