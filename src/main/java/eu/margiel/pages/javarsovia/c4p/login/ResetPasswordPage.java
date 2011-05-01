package eu.margiel.pages.javarsovia.c4p.login;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.PageNotFound;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "c4p/password/reset")
@MountMixedParam(parameterNames = "token")
public class ResetPasswordPage extends BaseWebPage {
	@SpringBean
	private SpeakerRepository repository;

	public ResetPasswordPage(PageParameters params) {
		Speaker speaker = repository.readByToken(params.getString("token"));
		if (speaker == null)
			setResponsePage(PageNotFound.class);
		else
			add(new ChangePasswordForm(speaker));
	}

	@SuppressWarnings("serial")
	private final class ChangePasswordForm extends Form<Void> {
		private PasswordTextField password = withLabel("nowe hasło", passwordField("password", model(), true));
		private PasswordTextField repassword = withLabel("powtórz nowe hasło",
				passwordField("repassword", model(), true));
		private FeedbackPanel feedback = new FeedbackPanel("feedback");
		private final Speaker speaker;

		private ChangePasswordForm(Speaker speaker) {
			super("form");
			this.speaker = speaker;
			add(feedback);
			add(password);
			add(repassword);
			add(new EqualPasswordInputValidator(password, repassword));
		}

		@Override
		protected void onSubmit() {
			changePassword();
			redirectToLoginPageWithMessage();
		}

		private void changePassword() {
			speaker.setToken("");
			speaker.passwordWithEncryption(password.getValue());
			repository.save(speaker);
		}

		private void redirectToLoginPageWithMessage() {
			feedback.info("Twoje hasło zostało zmienione");
			LoginSpeakerPage page = new LoginSpeakerPage();
			page.remove("feedback");
			page.add(feedback);
			setResponsePage(page);
		}
	}
}
