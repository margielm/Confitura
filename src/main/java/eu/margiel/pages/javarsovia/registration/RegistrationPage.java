package eu.margiel.pages.javarsovia.registration;

import static com.google.common.collect.Lists.*;
import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Participant;
import eu.margiel.pages.admin.registration.ParticipantMailSender;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.ParticipantRepository;
import eu.margiel.utils.Models;

@MountPath(path = "registration")
public class RegistrationPage extends BaseWebPage {

	@SpringBean
	private ParticipantRepository repository;
	@SpringBean
	private ParticipantMailSender mailSender;

	public RegistrationPage() {
		add(new RegistrationForm("form"));
	}

	@SuppressWarnings("serial")
	private final class RegistrationForm extends Form<Void> {
		private Participant participant = new Participant();
		private FeedbackPanel feedback = new FeedbackPanel("feedback");

		private RegistrationForm(String id) {
			super(id);
			add(feedback);
			add(withLabel("Imię", textField("firstName", propertyModel(participant, "firstName"), true)));
			add(withLabel("Nazwisko", textField("lastName", propertyModel(participant, "lastName"), true)));
			add(withLabel("e-mail",
					textField("mail", Models.<String> propertyModel(participant, "mail"), true)
							.add(RfcCompliantEmailAddressValidator.getInstance())));
			add(withLabel("Płeć", dropDown("sex", propertyModel(participant, "sex"), newArrayList("K", "M"))
					.setRequired(true)));
		}

		@Override
		protected void onSubmit() {
			if (repository.readByMail(participant.getMail()) != null)
				feedback.warn("Podany e-mail jest już zarejestrowany");
			repository.save(participant);
			mailSender.sendMessage(participant);
		}
	}
}