package eu.margiel.pages.javarsovia.c4p;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.components.user.UserInfoPanel;
import eu.margiel.domain.Presentation;
import eu.margiel.domain.Speaker;
import eu.margiel.repositories.PresentationRepository;
import eu.margiel.repositories.SpeakerRepository;

@MountPath(path = "c4p/viewSpeaker")
public class ViewSpeakerPage extends SpeakerBasePage {

	@SpringBean
	private PresentationRepository repository;
	@SpringBean
	private SpeakerRepository speakerRepository;

	public ViewSpeakerPage() {
		Speaker speaker = fetchSpeaker();
		add(new UserInfoPanel("info", speaker));
		add(new PresentationGrid(speaker.getPresentations()));
	}

	private Speaker fetchSpeaker() {
		return speakerRepository.readByPrimaryKey(getSession().getUser().getId());
	}

	@SuppressWarnings("serial")
	private class PresentationGrid extends DataView<Presentation> {
		private PresentationGrid(List<Presentation> presentations) {
			super("presentations", new ListDataProvider<Presentation>(presentations));
		}

		@Override
		protected void populateItem(Item<Presentation> item) {
			final Presentation presentation = item.getModelObject();
			item.add(label("title", presentation.getTitle()));
			item.add(new Link("edit") {
				@Override
				public void onClick() {
					setResponsePage(new AddPresentationPage(presentation));
				}
			});
			item.add(new Link("delete") {
				@Override
				public void onClick() {
					repository.delete(presentation);
					setResponsePage(ViewSpeakerPage.class);
				}
			});
		}
	}
}
