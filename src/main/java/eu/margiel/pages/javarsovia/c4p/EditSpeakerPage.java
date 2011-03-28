package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Speaker;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "c4p/speaker/edit")
public class EditSpeakerPage extends SpeakerBasePage {

	@SpringBean
	private SpeakerRepository repository;
	private transient SpeakerPhotoProvider photoProvider = new SpeakerPhotoProvider("speaker");

	public EditSpeakerPage() {
		add(new EditSpeakerForm(getSession().getSpeaker()));

	}

	@SuppressWarnings("serial")
	private final class EditSpeakerForm extends Form<Void> {
		private final Speaker speaker;
		private FileUploadField fileUploadField = new FileUploadField("photo");

		private EditSpeakerForm(Speaker speaker) {
			super("form");
			this.speaker = speaker;
			add(new FeedbackPanel("feedback"));
			add(textField("firstName", propertyModel(speaker, "firstName"), true));
			add(textField("lastName", propertyModel(speaker, "lastName"), true));
			add(textField("mail", propertyModel(speaker, "mail"), true));
			add(textField("twitter", propertyModel(speaker, "twitter")));
			add(textField("webPage", propertyModel(speaker, "webPage")));
			add(richEditorSimple("bio", propertyModel(speaker, "bio"), true));
			add(cancelLink(ViewSpeakerPage.class));
			add(fileUploadField);
		}

		@Override
		protected void onSubmit() {
			repository.save(speaker);
			if (fileUploadField.getFileUpload() != null)
				photoProvider.savePhoto(fileUploadField.getFileUpload(), speaker);
			setResponsePage(ViewSpeakerPage.class);
		}
	}
}
