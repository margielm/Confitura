package eu.margiel.pages.admin.sponsor;

import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.domain.Sponsor;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.SponsorRepository;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.List;

import static eu.margiel.utils.Components.label;

@MountPath(path = "admin/sponsors")
public class ListSponsorPage extends AdminBasePage {

	@SpringBean
	private SponsorRepository repository;

	public ListSponsorPage() {
		add(new SponsorGrid(repository.readAll()));
	}

	@SuppressWarnings("serial")
	private final class SponsorGrid extends DataView<Sponsor> {
		private SponsorGrid(List<Sponsor> list) {
			super("rows", new ListDataProvider<Sponsor>(list));
		}

		@Override
		protected void populateItem(Item<Sponsor> item) {
			Sponsor sponsor = item.getModelObject();
			item.add(label("name", sponsor.getName()));
			item.add(label("type", sponsor.getType()));
			item.add(label("money", String.valueOf(sponsor.getMoney())));
			item.add(new RedirectLink("edit", sponsor, AddSponsorPage.class));
			item.add(new DeleteLink(sponsor, repository, ListSponsorPage.class));
		}
	}
}
