package eu.margiel.pages.admin.registration;

import eu.margiel.components.mail.DefineMailPanel;
import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.Participant;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.ParticipantRepository;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;

public class FinalConfirmationMailPage extends AdminBasePage {
	@SpringBean
	private FinalConfirmationMailSender sender;
	@SpringBean
	private ParticipantRepository repository;

	public FinalConfirmationMailPage() {
		add(createSendLink());
		add(new DefineMailPanel("mail", "finalConfirmation", "$firstName, $lastName, $confirmLink, $cancelLink"));
	}

	@SuppressWarnings("serial")
	private Link createSendLink() {
		Link link = new Link("send") {

			@Override
			public void onClick() {
				sendEmailsToAllConfirmed();
			}

		};
		link.add(new SimpleAttributeModifier("onclick",
				"return confirm('Wysyłać prośbę o potwierdzenie rejestracji?');"));
		return link;
	}

	private void sendEmailsToAllConfirmed() {
		sender.sendMessages(getConfirmedParticipants());
	}

	private List<Participant> getConfirmedParticipants() {
		List<Participant> participants = FinalConfirmationMailPage.this.repository.readAll();
        return select(participants, having(on(Participant.class).isConfirmed() && !on(Participant.class).isFinalConfirmed()));
	}
}
