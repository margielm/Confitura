package eu.margiel.pages.confitura.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.Presentation;
import eu.margiel.repositories.PresentationRepository;

@SuppressWarnings("serial")
public abstract class PresentationPanel extends Panel {
	@SpringBean
	private PresentationRepository repository;

	public PresentationPanel(String id, Presentation presentation) {
		super(id);
		add(new PresentationForm(presentation));
	}

	private final class PresentationForm extends Form<Void> {
		private final Presentation presentation;

		private PresentationForm(Presentation presentation) {
			super("form");
			this.presentation = presentation;
			add(textField("title", propertyModel(presentation, "title"), true));
			add(richEditorSimple("description", propertyModel(presentation, "description")));
			add(new Link("cancel") {
				@Override
				public void onClick() {
					onCancelOrSubmit();
				}
			});
		}

		@Override
		protected void onSubmit() {
			Presentation savedPresentation = repository.save(presentation);
			presentation.id(savedPresentation.getId());
			onCancelOrSubmit();
		}
	}

	public abstract void onCancelOrSubmit();

}
