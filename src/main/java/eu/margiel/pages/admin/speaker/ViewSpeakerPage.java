package eu.margiel.pages.admin.speaker;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.components.user.UserInfoPanel;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "admin/speakers/view")
@MountMixedParam(parameterNames = "id")
public class ViewSpeakerPage extends AdminBasePage {
	@SpringBean
	private SpeakerRepository repository;

	public ViewSpeakerPage(PageParameters params) {
		Speaker speaker = repository.readByPrimaryKey(params.getAsInteger("id"));
		add(new UserInfoPanel("info", speaker));
	}
}
