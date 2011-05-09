package eu.margiel.pages.admin.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.margiel.domain.Participant;
import eu.margiel.repositories.MailTemplateRepository;
import eu.margiel.utils.MailSender;

@Component
public class ParticipantMailSender extends MailSender {
	@Autowired
	private MailTemplateRepository repository;

	@Override
	public String getSubjectTemplate() {
		return repository.readByType("participant").getSubject();
	}

	@Override
	public String getTemplate() {
		return repository.readByType("participant").getTemplate();
	}

	public void sendMessage(Participant participant) {
		put("firstName", participant.getFirstName());
		put("lastName", participant.getLastName());
		sendMessage(participant.getMail());
	}

}
