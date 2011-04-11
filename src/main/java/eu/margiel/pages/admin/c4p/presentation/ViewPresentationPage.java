package eu.margiel.pages.admin.c4p.presentation;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.PageParameters;
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
	}
}
