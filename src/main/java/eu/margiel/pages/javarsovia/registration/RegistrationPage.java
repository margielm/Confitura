package eu.margiel.pages.javarsovia.registration;

import static com.google.common.collect.Lists.*;
import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.commons.lang.BooleanUtils;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Participant;
import eu.margiel.domain.Registration;
import eu.margiel.pages.admin.registration.ParticipantMailSender;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.ParticipantRepository;
import eu.margiel.repositories.RegistrationRepository;
import eu.margiel.utils.Models;
import eu.margiel.utils.TokenUtils;

@MountPath(path = "registration")
public class RegistrationPage extends BaseWebPage {

	@SpringBean
	private ParticipantRepository repository;
	@SpringBean
	private RegistrationRepository registrationRepository;
	@SpringBean
	private ParticipantMailSender mailSender;
	private FeedbackPanel feedback = new FeedbackPanel("feedback");

	public RegistrationPage() {
		RegistrationForm form = new RegistrationForm("form");
		add(form);
		add(feedback);
		setUpRegistration(form);
	}

	private void setUpRegistration(RegistrationForm form) {
		Registration registration = registrationRepository.readAll().get(0);
		if (registration.isClosed()) {
			form.setVisible(false);
			add(richLabel("closeInfo", registration.getInfo()));
		} else
			add(richLabel("closeInfo", "").setVisible(false));
	}

	@SuppressWarnings("serial")
	private final class RegistrationForm extends Form<Void> {
		private Participant participant = new Participant();
		private DropDownChoice<String> lunch = dropDown("lunch", model(), newArrayList("Tak", "Nie"));

		private RegistrationForm(String id) {
			super(id);
			add(withLabel("Imię", textField("firstName", propertyModel(participant, "firstName"), true)));
			add(withLabel("Nazwisko", textField("lastName", propertyModel(participant, "lastName"), true)));
			add(withLabel("e-mail",
					textField("mail", Models.<String> propertyModel(participant, "mail"), true)
							.add(RfcCompliantEmailAddressValidator.getInstance())));
			add(withLabel("Miasto", textField("city", propertyModel(participant, "city"), true)));
			add(withLabel("Płeć", dropDown("sex", propertyModel(participant, "sex"), newArrayList("K", "M"))
					.setRequired(true)));
			add(withLabel("Obiad", lunch.setRequired(true)));
		}

		@Override
		protected void onSubmit() {
			if (repository.readByMail(participant.getMail()) != null)
				feedback.warn("Podany e-mail jest już zarejestrowany");
			else
				saveParticipant();
		}

		private void saveParticipant() {
			participant.lunch(BooleanUtils.toBoolean(lunch.getModelObject(), "Tak", "Nie"));
			participant.setToken(TokenUtils.generateToken());
			repository.save(participant);
			mailSender.sendMessage(participant);
			setResponsePage(new InfoPage("Na adres e-mail podany w formularzu przesłaliśmy dalsze informacje!"));
		}
	}
}
