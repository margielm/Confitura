package eu.margiel.pages.javarsovia.c4p;

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

import eu.margiel.JavarsoviaSession;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.pages.javarsovia.c4p.login.LoginSpeakerPage;
import eu.margiel.repositories.MailTemplateRepository;
import eu.margiel.repositories.SpeakerRepository;
import eu.margiel.utils.Models;

@SuppressWarnings("serial")
@MountPath(path = "c4p/speaker")
public class RegisterSpeakerPage extends BaseWebPage {

	@SpringBean
	private SpeakerRepository repository;
	@SpringBean
	private MailTemplateRepository mailTemplateRepository;

	private transient SpeakerPhotoProvider provider = new SpeakerPhotoProvider("speaker");

	public RegisterSpeakerPage(PageParameters params) {
		add(new SpeakerForm(getSpeaker()));
	}

	private Speaker getSpeaker() {
		return getSession().isSpeakerAvailable() ? getSession().getSpeaker() : new Speaker();
	}

	final class SpeakerForm extends Form<Void> {
		private PasswordTextField repassword = passwordField("repassword", new Model<String>(), true);
		private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");;
		private FileUploadField fileUploadField = new FileUploadField("photo");;
		private PasswordTextField password;
		private Speaker speaker;

		private SpeakerForm(Speaker speaker) {
			super("form");
			this.speaker = speaker;
			this.password = passwordField("password", propertyModel(speaker, "password"), true);
			add(textField("firstName", propertyModel(speaker, "firstName"), true).setLabel(new Model<String>("imię")));
			add(textField("lastName", propertyModel(speaker, "lastName"), true));
			add(textField("mail", Models.<String> propertyModel(speaker, "mail"), true)
					.add(RfcCompliantEmailAddressValidator.getInstance()));
			add(textField("webPage", propertyModel(speaker, "webPage")));
			add(textField("twitter", propertyModel(speaker, "twitter")));
			add(richEditorSimple("bio", propertyModel(speaker, "bio")));
			add(password);
			add(repassword);
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
			speaker.encryptPassword();
			repository.save(speaker);
			provider.savePhoto(fileUploadField.getFileUpload(), speaker);
			new SpeakerMailSender(mailTemplateRepository).sendMessage(speaker);
			JavarsoviaSession.get().setUser(speaker);
			setResponsePage(ViewSpeakerPage.class);
		}
	}
}
