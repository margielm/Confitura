package eu.margiel.pages.javarsovia.sponsor;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.StaticImage;
import eu.margiel.domain.Sponsor;
import eu.margiel.domain.SponsorType;
import eu.margiel.repositories.SponsorRepository;

@SuppressWarnings("serial")
public class SponsorWidget extends Panel {

	@SpringBean
	private SponsorRepository repository;

	public SponsorWidget(String id, List<SponsorType> types) {
		super(id);
		add(new SponsorsByTypeList(types));
	}

	private final class SponsorsByTypeList extends ListView<SponsorType> {
		private SponsorsByTypeList(List<SponsorType> sponsorTypes) {
			super("sponsors", sponsorTypes);
		}

		@Override
		protected void populateItem(ListItem<SponsorType> item) {
			SponsorType sponsorType = item.getModelObject();
			item.add(label("type", sponsorType.getFullName()));
			item.add(new SponsorList(sponsorType));

		}
	}

	private final class SponsorList extends ListView<Sponsor> {

		private SponsorList(SponsorType sponsorType) {
			super("sponsor", new SponsorFetcher().fetchSponsorsBy(sponsorType));
		}

		@Override
		protected void populateItem(ListItem<Sponsor> item) {
			item.add(createSponsorLogo(item.getModelObject()));
		}
	}

	private Component createSponsorLogo(Sponsor sponsor) {
		ExternalLink link = new ExternalLink("webPage", sponsor.getWebPage());
		link.add(new StaticImage("logo", getPath(sponsor)).alt(sponsor.getName()));
		return link;
	}

	private String getPath(Sponsor sponsor) {
		if (sponsor.isNew())
			return "/files/photos/sponsor.png";
		else
			return sponsor.getPathToPhoto();
	}

	private final class SponsorFetcher {
		public List<Sponsor> fetchSponsorsBy(SponsorType type) {
			List<Sponsor> sponsors = SponsorWidget.this.repository.readByType(type.getShortName());
			addUpToTwoEmptySponsors(sponsors);
			return sponsors;
		}

		private void addUpToTwoEmptySponsors(List<Sponsor> sponsors) {
			while (sponsors.size() < 2)
				sponsors.add(new Sponsor("Zostań naszym partnerem", "mailto:confitura@confitura.pl"));
		}
	}
}
