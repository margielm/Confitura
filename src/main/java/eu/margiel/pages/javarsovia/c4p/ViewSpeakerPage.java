package eu.margiel.pages.javarsovia.c4p;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.user.UserInfoPanel;
import eu.margiel.domain.Speaker;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "c4p/speaker")
public class ViewSpeakerPage extends SpeakerBasePage {

	@SpringBean
	private SpeakerRepository speakerRepository;

	public ViewSpeakerPage() {
		add(new UserInfoPanel("info", fetchSpeaker(), true));
	}

	private Speaker fetchSpeaker() {
		return speakerRepository.readByPrimaryKey(getSession().getUser().getId());
	}

}
