package eu.margiel.pages.confitura.c4p.login;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.ConfituraSession;
import eu.margiel.domain.User;
import eu.margiel.pages.confitura.BaseWebPage;
import eu.margiel.pages.confitura.c4p.ViewSpeakerPage;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "cp4/login")
public class LoginSpeakerPage extends BaseWebPage {

	@SpringBean
	private SpeakerRepository repository;
	private FeedbackPanel feedback = new FeedbackPanel("feedback");

	public LoginSpeakerPage() {
		add(feedback);
		add(new LoginForm("form"));
	}

	@SuppressWarnings("serial")
	private final class LoginForm extends Form<Void> {
		private TextField<String> mail = withLabel("e-mail", textField("mail", new Model<String>(), true));
		private TextField<String> password = withLabel("hasło", passwordField("password", new Model<String>(), true));

		private LoginForm(String id) {
			super(id);
			add(mail);
			add(password);
		}

		@Override
		protected void onSubmit() {
			User speaker = repository.readByMail(mail.getValue());
			if (speaker != null && speaker.passwordIsCorrect(password.getValue())) {
				ConfituraSession.get().setUser(speaker);
				setResponsePage(ViewSpeakerPage.class);
			} else
				feedback.error("Zła nazwa użytkownika lub hasło");
		}
	}
}
