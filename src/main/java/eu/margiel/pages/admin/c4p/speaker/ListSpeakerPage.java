package eu.margiel.pages.admin.c4p.speaker;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.domain.Speaker;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "admin/speakers")
public class ListSpeakerPage extends AdminBasePage {

	@SpringBean
	private SpeakerRepository repository;

	public ListSpeakerPage() {
		add(new SpeakerGrid(repository.readAll()));
	}

	@SuppressWarnings("serial")
	private final class SpeakerGrid extends DataView<Speaker> {
		private SpeakerGrid(List<Speaker> list) {
			super("rows", new ListDataProvider<Speaker>(list));
		}

		@Override
		protected void populateItem(Item<Speaker> item) {
			final Speaker speaker = item.getModelObject();
			item.add(label("firstName", speaker.getFirstName()));
			item.add(label("lastName", speaker.getLastName()));
			item.add(label("mail", speaker.getMail()));
			item.add(label("webPage", speaker.getWebPage()));
			item.add(new RedirectLink("view", speaker, ViewSpeakerPage.class));
			item.add(new DeleteLink(speaker, repository, ListSpeakerPage.class));
		}
	}
}
