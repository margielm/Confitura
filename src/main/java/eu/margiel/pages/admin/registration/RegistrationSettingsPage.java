package eu.margiel.pages.admin.registration;

import static com.google.common.collect.Lists.*;
import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.Registration;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.RegistrationRepository;

public class RegistrationSettingsPage extends AdminBasePage {

	@SpringBean
	private RegistrationRepository repository;

	public RegistrationSettingsPage() {
		add(new RegistrationForm(getRegistration()));
	}

	private Registration getRegistration() {
		List<Registration> all = repository.readAll();
		return all.isEmpty() ? new Registration() : all.get(0);
	}

	@SuppressWarnings("serial")
	private final class RegistrationForm extends Form<Void> {
		private static final String CLOSE = "Zamknięta";
		private static final String ACTIVE = "Otwarta";
		private final RadioChoice<String> radio = new RadioChoice<String>("state", model(), newArrayList(ACTIVE, CLOSE));
		private final Registration registration;

		private RegistrationForm(Registration registration) {
			super("form");
			this.registration = registration;
			radio.setModelObject(registration.isActive() ? ACTIVE : CLOSE);
			add(radio);
			add(richEditor("info", propertyModel(registration, "info")));
		}

		@Override
		protected void onSubmit() {
			registration.setActive(ACTIVE.equals(radio.getModelObject()) ? true : false);
			repository.save(registration);
		}

	}
}
