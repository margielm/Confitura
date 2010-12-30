package eu.margiel.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@SuppressWarnings({ "serial", "unused" })
@Entity
public class Speaker extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String eMail;
	private String webPage;
	private String twitter;
	@Lob
	private String bio;
	@OneToOne(cascade = CascadeType.ALL)
	private Presentation presentation;

	public Speaker presentation(Presentation presentation) {
		this.presentation = presentation;
		return this;
	}

}
