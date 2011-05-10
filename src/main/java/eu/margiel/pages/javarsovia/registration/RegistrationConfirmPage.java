package eu.margiel.pages.javarsovia.registration;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Participant;
import eu.margiel.pages.PageNotFound;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.ParticipantRepository;

@MountPath(path = "registration/confirm")
@MountMixedParam(parameterNames = "token")
public class RegistrationConfirmPage extends BaseWebPage {

	@SpringBean
	private ParticipantRepository repository;

	public RegistrationConfirmPage(PageParameters params) {
		Participant participant = repository.readByToken(params.getString("token"));
		System.out.println("aaaa" + participant);
		if (participant != null)
			approveParticipant(participant);
		else
			setResponsePage(PageNotFound.class);
	}

	private void approveParticipant(Participant participant) {
		String info;
		if (participant.isConfirm())
			info = "Twoja rejestracja została już potwierdzona!";
		else {
			participant.setConfirm(true);
			repository.save(participant);
			info = "Dziękujemy za potwierdzenie rejestracji";
		}
		setResponsePage(new InfoPage(info));
	}

}
