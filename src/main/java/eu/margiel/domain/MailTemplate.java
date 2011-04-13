package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class MailTemplate extends AbstractEntity {

	@SuppressWarnings("unused")
	private String type;
	@Lob
	private String template;
	private String subject;

	public MailTemplate() {
	}

	public MailTemplate(String type) {
		this.type = type;
	}

	public MailTemplate template(String template) {
		this.template = template;
		return this;
	}

	public MailTemplate subject(String subject) {
		this.subject = subject;
		return this;
	}

	public String getTemplate() {
		return template;
	}

	public String getSubject() {
		return subject;
	}

}
