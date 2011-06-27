package eu.margiel.pages.admin.registration;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Lists.*;
import static org.hamcrest.Matchers.*;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
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
		private CheckBoxMultipleChoice<RegistrationStatus> statusChoice = new CheckBoxMultipleChoice<RegistrationStatus>(
				"statuses", new CollectionModel<RegistrationStatus>(), newArrayList(RegistrationStatus.values()));
		private CheckBox participated = new CheckBox("participated", new Model<Boolean>());

		private SendMailForm(String id) {
			super(id);
			statusChoice.setPrefix("");
			statusChoice.setSuffix("");
			add(statusChoice);
			add(participated);
		}

		@Override
		protected void onSubmit() {
			sendEmailsToParticipantWith(statusChoice.getModelObject());
		}

		private void sendEmailsToParticipantWith(Collection<RegistrationStatus> statuses) {
			List<Participant> participantsByStatuses = getParticipantsWith(statuses);
			sender.sendMessages(filterRealParticipantsOnly(participated.getModelObject(), participantsByStatuses));
		}

		private List<Participant> filterRealParticipantsOnly(boolean filter, List<Participant> participants) {
			if (filter)
				return select(participants, having(participant().participated()));
			else
				return participants;
		}

		private List<Participant> getParticipantsWith(Collection<RegistrationStatus> statuses) {
			List<Participant> all = getAllParticipants();
			if (statuses.isEmpty())
				return all;
			else
				return select(all, having(participant().getRegistrationStatus(), isIn(statuses)));
		}

		private Participant participant() {
			return on(Participant.class);
		}

		private List<Participant> getAllParticipants() {
			return SendGroupMailPage.this.repository.readAll();
		}
	}
}
