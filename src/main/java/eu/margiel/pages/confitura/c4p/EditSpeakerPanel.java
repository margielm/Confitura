package eu.margiel.pages.confitura.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.Speaker;
import eu.margiel.repositories.SpeakerRepository;

@SuppressWarnings("serial")
public abstract class EditSpeakerPanel extends Panel {

	@SpringBean
	private SpeakerRepository repository;

	public EditSpeakerPanel(String id, Speaker speaker) {
		super(id);
		add(new EditSpeakerForm(speaker));

	}

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
			add(fileUploadField);
			add(new Link("cancel") {

				@Override
				public void onClick() {
					onCancelOrSubmit();
				}

			});
		}

		@Override
		protected void onSubmit() {
			repository.save(speaker);
			if (fileUploadField.getFileUpload() != null)
				speaker.savePhoto(fileUploadField.getFileUpload());
			onCancelOrSubmit();
		}
	}

	public abstract void onCancelOrSubmit();
}
