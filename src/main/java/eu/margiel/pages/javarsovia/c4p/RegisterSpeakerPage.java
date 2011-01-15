package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.MenuRepository;
import eu.margiel.repositories.SpeakerRepository;

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
	private final class SpeakerForm extends Form<Void> {
		private PasswordTextField repassword;
		private PasswordTextField password;
		private FeedbackPanel feedbackPanel;
		private Speaker speaker;

		private SpeakerForm(String id, Speaker speaker) {
			super(id);
			this.speaker = speaker;
			this.repassword = passwordField("password", propertyModel(speaker, "password"), true);
			this.password = passwordField("repassword", new Model<String>(), true);
			this.feedbackPanel = new FeedbackPanel("feedback");
			add(textField("firstName", propertyModel(speaker, "firstName"), true));
			add(textField("lastName", propertyModel(speaker, "lastName"), true));
			add(textField("mail", propertyModel(speaker, "mail"), true));
			add(textField("webPage", propertyModel(speaker, "webPage")));
			add(textField("twitter", propertyModel(speaker, "twitter")));
			add(richEditorSimple("bio", propertyModel(speaker, "bio")));
			add(password);
			add(repassword);
			add(feedbackPanel);
		}

		@Override
		protected void onSubmit() {
			if (password.getValue().equals(repassword.getValue()) == false) {
				feedbackPanel.error("Hasła nie pasują");
				return;
			} else {
				speaker.encryptPassword();
				repository.save(speaker);
			}
		}
	}
}
