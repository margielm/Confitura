package eu.margiel.pages.javarsovia.c4p;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.margiel.domain.User;
import eu.margiel.pages.admin.c4p.speaker.AdminSpeakerMailSender;
import eu.margiel.repositories.MailTemplateRepository;
import eu.margiel.utils.MailSender;

@Component
public class SpeakerMailSender extends MailSender {
	@Autowired
	private MailTemplateRepository repository;
	@Autowired
	AdminSpeakerMailSender adminSender;

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

	public void sendMessage(User speaker) {
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
