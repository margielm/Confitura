package eu.margiel.pages.javarsovia.sponsor;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.StaticImage;
import eu.margiel.domain.Sponsor;
import eu.margiel.domain.SponsorType;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.SponsorRepository;

@MountPath(path = "partners")
public class ViewSponsorsPage extends BaseWebPage {
	@SpringBean
	private SponsorRepository repository;

	public ViewSponsorsPage(PageParameters params) {
		add(createSponsorTypeList());

	}

	@SuppressWarnings("serial")
	private ListView<SponsorType> createSponsorTypeList() {
		return new ListView<SponsorType>("sponsorTypes", SponsorType.sponsors()) {

			@Override
			protected void populateItem(ListItem<SponsorType> item) {
				SponsorType type = item.getModelObject();
				item.add(label("sponsorType", type.getFullName()));
				List<Sponsor> sponsors = repository.readByType(type.getShortName());
				item.add(createSponsorsListFor(sponsors)).setVisible(sponsors.size() > 0);
			}
		};
	}

	@SuppressWarnings("serial")
	private ListView<Sponsor> createSponsorsListFor(List<Sponsor> sponsors) {
		return new ListView<Sponsor>("sponsor", sponsors) {

			@Override
			protected void populateItem(ListItem<Sponsor> item) {
				Sponsor sponsor = item.getModelObject();
				item.add(createLogo(sponsor));
				item.add(createInfoCell(item, sponsor));
			}

			private ExternalLink createLogo(Sponsor sponsor) {
				ExternalLink link = new ExternalLink("link", sponsor.getWebPage());
				link.add(new StaticImage("logo", sponsor.getPathToPhoto()));
				return link;
			}

			private WebMarkupContainer createInfoCell(ListItem<Sponsor> item, Sponsor sponsor) {
				WebMarkupContainer info = new WebMarkupContainer("info");
				info.add(new AttributeModifier("class", getCssClass(item)));
				info.add(richLabel("name", sponsor.getName()));
				info.add(richLabel("desc", sponsor.getDescription()));
				return info;
			}

		};
	}

	private Model<String> getCssClass(ListItem<?> item) {
		return model(item.getIndex() % 2 == 0 ? "odd" : "");
	}
}
