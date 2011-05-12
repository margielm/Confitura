package eu.margiel.pages.admin.c4p.presentation;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.components.LabeledLink;
import eu.margiel.domain.Presentation;
import eu.margiel.domain.User;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.pages.admin.c4p.speaker.ViewSpeakerPage;
import eu.margiel.repositories.PresentationRepository;

@MountPath(path = "admin/presentations/view")
@MountMixedParam(parameterNames = "id")
public class ViewPresentationPage extends AdminBasePage {
	@SpringBean
	private PresentationRepository repository;

	public ViewPresentationPage(PageParameters params) {
		Presentation presentation = repository.readByPrimaryKey(params.getAsInteger("id"));
		User speaker = presentation.getSpeaker();
		add(new LabeledLink("speaker", speaker.getFullName(), speaker.getId(), ViewSpeakerPage.class));
		add(label("title", presentation.getTitle()));
		add(richLabel("desc", presentation.getDescription()));
		add(createAcceptLink(presentation));
	}

	@SuppressWarnings("serial")
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
