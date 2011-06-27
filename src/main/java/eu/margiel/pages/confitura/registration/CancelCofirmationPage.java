package eu.margiel.pages.confitura.registration;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Participant;
import eu.margiel.repositories.ParticipantRepository;

@MountPath(path = "registration/cancel")
@MountMixedParam(parameterNames = "token")
public class CancelCofirmationPage extends RegistrationConfirmPage {

	@SpringBean
	private ParticipantRepository repository;

	public CancelCofirmationPage(PageParameters params) {
		super(params);
	}

	@Override
	String performActionFor(Participant participant) {
		if (participant.isCancel())
			return "Twoja rejestracja już została anulowana";
		else {
			participant.cancel();
			repository.save(participant);
			return "Twoja rejestracja została anulowana";
		}
	}

}
