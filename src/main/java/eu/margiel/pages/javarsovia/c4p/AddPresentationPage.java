package eu.margiel.pages.javarsovia.c4p;

import org.apache.wicket.PageParameters;
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

	@SuppressWarnings("serial")
	private void init(final Presentation presentation) {
		add(new PresentationPanel("presentation", presentation) {

			@Override
			public void onCancelOrSubmit() {
				setResponsePage(ViewSpeakerPage.class);
			}

		});
	}

}
