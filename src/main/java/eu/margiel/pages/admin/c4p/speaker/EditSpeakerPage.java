package eu.margiel.pages.admin.c4p.speaker;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.pages.javarsovia.c4p.EditSpeakerPanel;
import eu.margiel.repositories.SpeakerRepository;
import eu.margiel.utils.PageParametersBuilder;

@MountPath(path = "admin/speakers/edit")
@MountMixedParam(parameterNames = "id")
@SuppressWarnings("serial")
public class EditSpeakerPage extends AdminBasePage {
	@SpringBean
	private SpeakerRepository repository;

	public EditSpeakerPage(PageParameters params) {
		final Speaker speaker = repository.readByPrimaryKey(params.getAsInteger("id"));
		add(new EditSpeakerPanel("speaker", speaker) {

			@Override
			public void onCancelOrSubmit() {
				setResponsePage(ViewSpeakerPage.class, PageParametersBuilder.paramsFor("id", speaker.getId()));
			}

		});
	}
}
