package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Speaker;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "c4p/speaker/changePassword")
public class ChangePasswordPage extends SpeakerBasePage {
	@SpringBean
	private SpeakerRepository repository;

	public ChangePasswordPage() {
		add(new ChangePasswordForm(getSession().getSpeaker()));
	}

	@SuppressWarnings("serial")
	private final class ChangePasswordForm extends Form<Void> {
		private PasswordTextField oldPassword = passwordField("oldPassword", new Model<String>(), true);
		private PasswordTextField password = passwordField("password", new Model<String>(), true);
		private PasswordTextField repassword = passwordField("repassword", new Model<String>(), true);
		private FeedbackPanel feedback = new FeedbackPanel("feedback");
		private final Speaker speaker;

		private ChangePasswordForm(Speaker speaker) {
			super("form");
			this.speaker = speaker;
			add(feedback);
			add(oldPassword);
			add(password);
			add(repassword);
			add(cancelLink(ViewSpeakerPage.class));
			add(new EqualPasswordInputValidator(password, repassword));
		}

		@Override
		protected void onSubmit() {
			if (speaker.passwordIsCorrect(oldPassword.getValue())) {
				speaker.passwordWithEncryption(password.getValue());
				repository.save(speaker);
				setResponsePage(ViewSpeakerPage.class);
			} else
				feedback.warn("Niepoprawne stare hasło");
		}
	}
}
