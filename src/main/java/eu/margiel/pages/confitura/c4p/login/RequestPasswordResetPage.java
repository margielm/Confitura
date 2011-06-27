package eu.margiel.pages.confitura.c4p.login;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.confitura.BaseWebPage;
import eu.margiel.repositories.SpeakerRepository;
import eu.margiel.utils.TokenUtils;

@MountPath(path = "cp4/password")
public class RequestPasswordResetPage extends BaseWebPage {

	@SpringBean
	private SpeakerRepository repository;
	@SpringBean
	private RequestPasswordResetMailSender sender;

	public RequestPasswordResetPage() {
		add(new RequestPasswordResetForm("form"));
	}

	@SuppressWarnings("serial")
	private final class RequestPasswordResetForm extends Form<Void> {
		private TextField<String> mail = withLabel("e-mail", textField("mail", model()));
		private FeedbackPanel feedback = new FeedbackPanel("feedback");

		private RequestPasswordResetForm(String id) {
			super(id);
			add(feedback);
			add(mail);
		}

		@Override
		protected void onSubmit() {
			Speaker speaker = repository.readByMail(mail.getValue());
			if (speaker == null)
				wrongMail();
			else
				requestPasswordResetFor(speaker);
		}

		private void wrongMail() {
			feedback.warn("Podany e-mail nie istnieje");
		}

		private void requestPasswordResetFor(Speaker speaker) {
			String token = TokenUtils.generateToken();
			speaker.setToken(token);
			repository.save(speaker);
			sender.token(token).sendMessage(speaker.getMail());
			feedback.info("Na podany e-mail wysłaliśmy wiadomość z dalszymi instrukcjami");
		}

	}
}
