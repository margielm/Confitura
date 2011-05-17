package eu.margiel.pages.admin.c4p.presentation;

import static eu.margiel.utils.Models.*;
import static eu.margiel.utils.PageParametersBuilder.*;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Presentation;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.pages.admin.c4p.speaker.ViewSpeakerPage;
import eu.margiel.pages.javarsovia.c4p.PresentationPanel;
import eu.margiel.repositories.PresentationRepository;

@SuppressWarnings("serial")
@MountPath(path = "admin/presentations/view")
@MountMixedParam(parameterNames = "id")
public class ViewPresentationPage extends AdminBasePage {
	@SpringBean
	private PresentationRepository repository;

	public ViewPresentationPage(PageParameters params) {
		final Presentation presentation = repository.readByPrimaryKey(params.getAsInteger("id"));
		add(new PresentationPanel("presentation", presentation) {

			@Override
			public void onCancelOrSubmit() {
				setResponsePage(ViewSpeakerPage.class, paramsFor("id", presentation.getSpeaker().getId()));
			}

		});
		add(createAcceptLink(presentation));
	}

	private Component createAcceptLink(final Presentation presentation) {
		final Button acceptButton = new Button("accept", model(getLinkLabel(presentation)));
		Form<Void> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				repository.save(presentation.toggleAccepted());
				acceptButton.setModelObject(getLinkLabel(presentation));
				renderPage();
			}
		};
		form.add(acceptButton);
		return form;
	}

	private String getLinkLabel(Presentation presentation) {
		return presentation.isAccepted() ? "Anuluj" : "Akceptuj";
	}
}
