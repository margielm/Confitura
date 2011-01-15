package eu.margiel.pages.javarsovia.c4p.login;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.JavarsoviaSession;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.pages.javarsovia.c4p.ViewSpeakerPage;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "cp4/login")
public class LoginSpeakerPage extends BaseWebPage {

	@SpringBean
	private SpeakerRepository repository;

	public LoginSpeakerPage() {
		add(new LoginForm("form"));
	}

	@SuppressWarnings("serial")
	private final class LoginForm extends Form<Void> {
		private FeedbackPanel feedback = new FeedbackPanel("feedback");
		private TextField<String> mail = textField("mail", new Model<String>(), true);
		private TextField<String> password = passwordField("password", new Model<String>(), true);

		private LoginForm(String id) {
			super(id);
			add(feedback);
			add(mail);
			add(password);
		}

		@Override
		protected void onSubmit() {
			Speaker speaker = repository.readByMail(mail.getValue());
			if (speaker != null && speaker.passwordIsCorrect(password.getValue())) {
				JavarsoviaSession.get().setUser(speaker);
				setResponsePage(ViewSpeakerPage.class);
			} else
				feedback.error("Zła nazwa użytkownika lub hasło");
		}
	}
}
