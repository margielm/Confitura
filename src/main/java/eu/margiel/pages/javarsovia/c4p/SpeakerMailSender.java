package eu.margiel.pages.javarsovia.c4p;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.admin.c4p.speaker.AdminSpeakerMailSender;
import eu.margiel.repositories.MailTemplateRepository;
import eu.margiel.utils.MailSender;

public class SpeakerMailSender extends MailSender {

	AdminSpeakerMailSender adminSender = new AdminSpeakerMailSender();
	private final MailTemplateRepository repository;

	public SpeakerMailSender(MailTemplateRepository repository) {
		this.repository = repository;
	}

	@Override
	public String getSubjectTemplate() {
		return repository.readByType("speaker").getSubject();
	}

	@Override
	public String getTemplate() {
		return repository.readByType("speaker").getTemplate();
	}

	public SpeakerMailSender firstName(String firstName) {
		put("firstName", firstName);
		return this;
	}

	public void sendMessage(Speaker speaker) {
		firstName(speaker.getFirstName())
				.lastName(speaker.getLastName())
				.sendMessage(speaker.getMail());
		adminSender.fullName(speaker.getFullName()).sendMessage();
	}

	public Object get(String name) {
		return ctx.get(name);
	}

	public SpeakerMailSender lastName(String lastName) {
		put("lastName", lastName);
		return this;
	}

}
