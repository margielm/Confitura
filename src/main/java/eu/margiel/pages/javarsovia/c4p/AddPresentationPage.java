package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.repositories.PresentationRepository;

@MountPath(path = "c4p/presentation")
@MountMixedParam(parameterNames = "id")
public class AddPresentationPage extends SpeakerBasePage {

	@SpringBean
	private PresentationRepository repository;

	public AddPresentationPage() {
		Speaker speaker = getSession().getSpeaker();
		Presentation presentation = new Presentation();
		speaker.addPresentation(presentation);
		init(presentation);
	}

	public AddPresentationPage(PageParameters params) {
		init(repository.readByPrimaryKey(params.getAsInteger("id")));
	}

	private void init(final Presentation presentation) {
		add(new PresentationForm(presentation));
	}

	@SuppressWarnings("serial")
	private final class PresentationForm extends Form<Void> {
		private final Presentation presentation;

		private PresentationForm(Presentation presentation) {
			super("form");
			this.presentation = presentation;
			add(textField("title", propertyModel(presentation, "title"), true));
			add(richEditorSimple("description", propertyModel(presentation, "description")));
			add(redirectLink("cancel", ViewSpeakerPage.class));
		}

		@Override
		protected void onSubmit() {
			Presentation savedPresentation = repository.save(presentation);
			presentation.id(savedPresentation.getId());
			setResponsePage(ViewSpeakerPage.class);
		}
	}
}
