package eu.margiel.pages.javarsovia.sponsor;

import static com.google.common.collect.Lists.*;
import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;
import static eu.margiel.utils.PageParametersBuilder.*;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.components.StaticImage;
import eu.margiel.domain.Sponsor;
import eu.margiel.domain.SponsorType;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.repositories.SponsorRepository;

@MountPath(path = "partners")
@MountMixedParam(parameterNames = "type")
public class ViewSponsorsPage extends BaseWebPage {
	@SpringBean
	private SponsorRepository repository;
	private SponsorType sponsorType;

	public ViewSponsorsPage(PageParameters params) {
		sponsorType = getSponsorType(params);
		add(createSponsorTypeList());
		add(createSponsorsList());

	}

	private SponsorType getSponsorType(PageParameters params) {
		SponsorType type = params.getAsEnum("type", SponsorType.class);
		return type != null ? type : SponsorType.GOLD;
	}

	@SuppressWarnings("serial")
	private ListView<SponsorType> createSponsorTypeList() {
		return new ListView<SponsorType>("sponsorType", newArrayList(SponsorType.values())) {

			@Override
			protected void populateItem(ListItem<SponsorType> item) {
				SponsorType type = item.getModelObject();
				BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>("type", ViewSponsorsPage.class,
						paramsFor("type", type));
				if (type == sponsorType)
					link.add(new AttributeModifier("style", model("font-size: large; text-decoration: underline;")));
				link.add(label("label", type.getFullName()));
				item.add(link);
			}
		};
	}

	@SuppressWarnings("serial")
	private ListView<Sponsor> createSponsorsList() {
		return new ListView<Sponsor>("sponsor", repository.readByType(sponsorType.getShortName())) {

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
