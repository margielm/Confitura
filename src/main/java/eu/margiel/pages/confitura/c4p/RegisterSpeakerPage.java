package eu.margiel.pages.confitura.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.ConfituraSession;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.confitura.BaseWebPage;
import eu.margiel.pages.confitura.c4p.login.LoginSpeakerPage;
import eu.margiel.repositories.SpeakerRepository;
import eu.margiel.utils.Models;

@SuppressWarnings("serial")
@MountPath(path = "c4p/speaker")
public class RegisterSpeakerPage extends BaseWebPage {

	@SpringBean
	private SpeakerRepository repository;
	@SpringBean
	private SpeakerMailSender mailSender;

	public RegisterSpeakerPage(PageParameters params) {
		add(new SpeakerForm(getSpeaker()));
	}

	private Speaker getSpeaker() {
		return getSession().isSpeakerAvailable() ? getSession().getSpeaker() : new Speaker();
	}

	final class SpeakerForm extends Form<Void> {
		private PasswordTextField repassword = passwordField("repassword", new Model<String>(), true);
		private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		private FileUploadField fileUploadField = new FileUploadField("photo");;
		private PasswordTextField password;
		private Speaker speaker;

		private SpeakerForm(Speaker speaker) {
			super("form");
			this.speaker = speaker;
			this.password = passwordField("password", propertyModel(speaker, "password"), true);
			add(withLabel("imię", textField("firstName", propertyModel(speaker, "firstName"), true)));
			add(withLabel("nazwisko", textField("lastName", propertyModel(speaker, "lastName"), true)));
			add(withLabel("e-mail", textField("mail", Models.<String> propertyModel(speaker, "mail"), true)
					.add(RfcCompliantEmailAddressValidator.getInstance())));
			add(textField("webPage", propertyModel(speaker, "webPage")));
			add(textField("twitter", propertyModel(speaker, "twitter")));
			add(withLabel("O sobie", richEditorSimple("bio", propertyModel(speaker, "bio"))));
			add(withLabel("hasło", password));
			add(withLabel("powtórz hasło", repassword));
			add(feedbackPanel);
			add(fileUploadField);
			add(cancelLink(LoginSpeakerPage.class));
			add(new EqualPasswordInputValidator(password, repassword));
		}

		@Override
		protected void onSubmit() {
			saveSpeaker();
		}

		private void saveSpeaker() {
			if (repository.readByMail(speaker.getMail()) != null)
				feedbackPanel.info("Podany e-mail jest już zarejestrowany!");
			else {
				speaker.encryptPassword();
				repository.save(speaker);
				speaker.savePhoto(fileUploadField.getFileUpload());
				mailSender.sendMessage(speaker);
				ConfituraSession.get().setUser(speaker);
				setResponsePage(ViewSpeakerPage.class);
			}
		}
	}
}
