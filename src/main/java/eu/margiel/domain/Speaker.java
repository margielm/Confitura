package eu.margiel.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Speaker extends User {
	private String webPage;
	private String twitter;
	@Lob
	private String bio;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "speaker")
	private List<Presentation> presentations;

	public String getWebPage() {
		return webPage;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getBio() {
		return bio;
	}

	public Speaker mail(String mail) {
		this.mail = mail;
		return this;
	}

	public List<Presentation> getPresentations() {
		return presentations;
	}

	public void addPresentation(Presentation presentation) {
		presentation.speaker(this);
		this.presentations.add(presentation);
	}

}
