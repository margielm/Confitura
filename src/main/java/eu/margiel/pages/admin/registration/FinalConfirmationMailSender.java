package eu.margiel.pages.admin.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import eu.margiel.domain.Participant;
import eu.margiel.repositories.MailTemplateRepository;
import eu.margiel.utils.MailSender;

@Component
public class FinalConfirmationMailSender extends MailSender {
	@Autowired
	private MailTemplateRepository repository;

	@Override
	public String getSubjectTemplate() {
		return repository.readByType("finalConfirmation").getSubject();
	}

	@Override
	public String getTemplate() {
		return repository.readByType("finalConfirmation").getTemplate();
	}

	@Async
	public void sendMessages(List<Participant> participants) {
		for (Participant participant : participants) {
			System.out.println(String.format("%s/%s Sending email to " + participant.getMail(),
					participants.indexOf(participant) + 1, participants.size()));
			sendMessage(participant);
		}
	}

	public void sendMessage(Participant participant) {
		put("firstName", participant.getFirstName());
		put("lastName", participant.getLastName());
		put("confirmLink", getApproveLink(participant.getToken()));
		put("cancelLink", getCancelLink(participant.getToken()));
		sendMessage(participant.getMail());
	}

	private String getApproveLink(String token) {
		return "http://www.confitura.pl/registration/confirm/final/" + token;
	}

	private String getCancelLink(String token) {
		return "http://www.confitura.pl/registration/cancel/" + token;
	}

}
