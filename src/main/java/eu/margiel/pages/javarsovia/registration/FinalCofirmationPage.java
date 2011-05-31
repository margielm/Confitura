package eu.margiel.pages.javarsovia.registration;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Participant;
import eu.margiel.repositories.ParticipantRepository;

@MountPath(path = "registration/confirm/final")
@MountMixedParam(parameterNames = "token")
public class FinalCofirmationPage extends RegistrationConfirmPage {

	@SpringBean
	private ParticipantRepository repository;

	public FinalCofirmationPage(PageParameters params) {
		super(params);
	}

	@Override
	String performActionFor(Participant participant) {
		if (participant.isFinalConfirmed())
			return "Twoja rejestracja została już potwierdzona!";
		else {
			participant.finalConfirmation();
			repository.save(participant);
			return "Dziękujemy za ostateczne potwierdzenie rejestracji";
		}
	}
}