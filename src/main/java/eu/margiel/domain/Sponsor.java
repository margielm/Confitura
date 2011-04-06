package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class Sponsor extends AbstractEntity {
	private String name;
	private String type;
	private String webPage;
	@SuppressWarnings("unused")
	@Lob
	private String description;
	@SuppressWarnings("unused")
	@Lob
	private String folderDescription;

	public Sponsor() {
		// TODO Auto-generated constructor stub
	}

	public Sponsor(String name, String webPage) {
		this.name = name;
		this.webPage = webPage;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getWebPage() {
		return webPage;
	}

}
