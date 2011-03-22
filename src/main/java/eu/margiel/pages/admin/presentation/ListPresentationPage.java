package eu.margiel.pages.admin.presentation;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.domain.Presentation;
import eu.margiel.domain.User;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.PresentationRepository;

@MountPath(path = "admin/presentations")
public class ListPresentationPage extends AdminBasePage {

	@SpringBean
	private PresentationRepository repository;

	public ListPresentationPage() {
		add(new PresentationGrid(repository.readAll()));
	}

	@SuppressWarnings("serial")
	private final class PresentationGrid extends DataView<Presentation> {
		private PresentationGrid(List<Presentation> list) {
			super("rows", new ListDataProvider<Presentation>(list));
		}

		@Override
		protected void populateItem(Item<Presentation> item) {
			Presentation presentation = item.getModelObject();
			User speaker = presentation.getSpeaker();
			item.add(label("firstName", speaker.getFirstName()));
			item.add(label("lastName", speaker.getLastName()));
			item.add(label("title", presentation.getTitle()));
			item.add(new RedirectLink("view", presentation, ViewPresentationPage.class));
			item.add(new DeleteLink(presentation, repository, ListPresentationPage.class));
		}
	}
}
