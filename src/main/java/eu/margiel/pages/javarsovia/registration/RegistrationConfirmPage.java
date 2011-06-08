package eu.margiel.pages.javarsovia.registration;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Participant;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.ParticipantRepository;

@MountPath(path = "registration/confirm")
@MountMixedParam(parameterNames = "token")
public class RegistrationConfirmPage extends BaseWebPage {

	@SpringBean
	private ParticipantRepository repository;

	public RegistrationConfirmPage() {
	}

	public RegistrationConfirmPage(PageParameters params) {
		setResponsePage(new InfoPage("Zakończyliśmy etap potwierdzania rejestracji, dziękujemy!"));
		// Participant participant = getParticipantByToken(params);
		// if (participant != null)
		// setResponsePage(new InfoPage(performActionFor(participant)));
		// else
		// setResponsePage(PageNotFound.class);
	}

	private Participant getParticipantByToken(PageParameters params) {
		return repository.readByToken(params.getString("token"));
	}

	String performActionFor(Participant participant) {
		if (participant.isConfirmed())
			return "Twoja rejestracja została już potwierdzona!";
		else {
			participant.confirm();
			repository.save(participant);
			return "Dziękujemy za potwierdzenie rejestracji";
		}
	}

}
