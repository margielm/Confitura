package eu.margiel.pages.confitura.registration;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.Registration;
import eu.margiel.repositories.ParticipantRepository;
import eu.margiel.repositories.RegistrationRepository;

@SuppressWarnings("serial")
public class RegistrationWidget extends Panel {
	@SpringBean
	private RegistrationRepository repository;
	@SpringBean
	private ParticipantRepository participantRepository;

	public RegistrationWidget(String id) {
		super(id);
		Registration registration = repository.readAll().get(0);
		String info = registration.getWidgetInfo();
		add(richLabel("info", info.replace("$counter", participantRepository.count() + "")));
	}
}
