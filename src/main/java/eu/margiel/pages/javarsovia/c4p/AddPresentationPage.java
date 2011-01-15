package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.repositories.PresentationRepository;

@MountPath(path = "c4p/presentation")
public class AddPresentationPage extends SpeakerBasePage {

	@SpringBean
	private PresentationRepository repository;

	public AddPresentationPage() {
		Speaker speaker = getSession().getSpeaker();
		Presentation presentation = new Presentation();
		speaker.addPresentation(presentation);
		init(presentation);
	}

	public AddPresentationPage(Presentation presentation) {
		init(presentation);
	}

	private void init(final Presentation presentation) {
		add(new PresentationForm("form", presentation));
	}

	@SuppressWarnings("serial")
	private final class PresentationForm extends Form<Void> {
		private final Presentation presentation;

		private PresentationForm(String id, Presentation presentation) {
			super(id);
			this.presentation = presentation;
			add(textField("title", propertyModel(presentation, "title"), true));
			add(richEditorSimple("description", propertyModel(presentation, "description")));
		}

		@Override
		protected void onSubmit() {
			repository.save(presentation);
			setResponsePage(ViewSpeakerPage.class);
		}
	}
}
