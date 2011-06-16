package eu.margiel.pages.admin.registration;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Lists.*;
import static org.hamcrest.Matchers.*;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.mail.DefineMailPanel;
import eu.margiel.domain.Participant;
import eu.margiel.domain.RegistrationStatus;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.ParticipantRepository;

public class SendGroupMailPage extends AdminBasePage {

	@SpringBean
	private GroupMailSender sender;
	@SpringBean
	private ParticipantRepository repository;

	public SendGroupMailPage() {
		Form<Void> form = new SendMailForm("form");
		form.add(new SimpleAttributeModifier("onsubmit",
				"return confirm('Wysyłać maila do uczestników o wybranym statusie?');"));
		add(form);
		add(new DefineMailPanel("mail", "finalConfirmation", "$firstName, $lastName, $confirmLink, $cancelLink"));
	}

	@SuppressWarnings("serial")
	private final class SendMailForm extends Form<Void> {
		private CheckBoxMultipleChoice<RegistrationStatus> choice = new CheckBoxMultipleChoice<RegistrationStatus>(
				"statuses", new CollectionModel<RegistrationStatus>(), newArrayList(RegistrationStatus.values()));

		private SendMailForm(String id) {
			super(id);
			choice.setPrefix("");
			choice.setSuffix("");
			add(choice);
		}

		@Override
		protected void onSubmit() {
			sendEmailsToParticipantWith(choice.getModelObject());
		}

		private void sendEmailsToParticipantWith(Collection<RegistrationStatus> statuses) {
			sender.sendMessages(getParticipantsWith(statuses));
		}

		private List<Participant> getParticipantsWith(Collection<RegistrationStatus> statuses) {
			return select(getAllParticipants(), having(on(Participant.class).getRegistrationStatus(), isIn(statuses)));
		}

		private List<Participant> getAllParticipants() {
			return SendGroupMailPage.this.repository.readAll();
		}
	}
}
