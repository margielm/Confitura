package eu.margiel.domain;

import static com.google.common.collect.Lists.*;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Speaker extends User {
	private String webPage;
	private String twitter;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "speaker", orphanRemoval = true)
	private List<Presentation> presentations = newArrayList();

	public String getWebPage() {
		return webPage;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getTwitterUrl() {
		return "http://www.twitter.com/" + twitter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Speaker mail(String mail) {
		return super.mail(mail);
	}

	public List<Presentation> getPresentations() {
		return presentations;
	}

	public void addPresentation(Presentation presentation) {
		presentation.speaker(this);
		this.presentations.add(presentation);
	}

}
