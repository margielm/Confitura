package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.MenuRepository;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "c4p/speaker/edit")
public class RegisterSpeakerPage extends BaseWebPage {

	@SpringBean
	private SpeakerRepository repository;

	public RegisterSpeakerPage(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public RegisterSpeakerPage() {
		add(new SpeakerForm("form", getSpeaker()));
	}

	private Speaker getSpeaker() {
		return getSession().isSpeakerAvailable() ? getSession().getSpeaker() : new Speaker();
	}

	@SuppressWarnings("serial")
	final class SpeakerForm extends Form<Void> {
		private PasswordTextField repassword = passwordField("repassword", new Model<String>(), true);
		private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");;
		FileUploadField fileUploadField = new FileUploadField("photo");;
		private PasswordTextField password;
		Speaker speaker;

		private SpeakerForm(String id, Speaker speaker) {
			super(id);
			this.speaker = speaker;
			this.password = passwordField("password", propertyModel(speaker, "password"), true);
			add(textField("firstName", propertyModel(speaker, "firstName"), true));
			add(textField("lastName", propertyModel(speaker, "lastName"), true));
			add(textField("mail", propertyModel(speaker, "mail"), true));
			add(textField("webPage", propertyModel(speaker, "webPage")));
			add(textField("twitter", propertyModel(speaker, "twitter")));
			add(richEditorSimple("bio", propertyModel(speaker, "bio")));
			add(password);
			add(repassword);
			add(feedbackPanel);
			add(fileUploadField);
		}

		@Override
		protected void onSubmit() {
			if (password.getValue().equals(repassword.getValue()) == false) {
				feedbackPanel.error("Hasła nie pasują");
				return;
			} else
				saveSpeaker();
		}

		private void saveSpeaker() {
			speaker.encryptPassword();
			repository.save(speaker);
			new SpeakerPhotoProvider().savePhoto(fileUploadField.getFileUpload(), speaker);

		}
	}
}
