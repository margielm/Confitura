package eu.margiel.admin;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.Offer;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.services.OfferService;

@SuppressWarnings("serial")
public class OfferListPage extends AdminBasePage {

	@SpringBean
	private OfferService service;

	public OfferListPage() {
		add(new OfferList(service.getAll()));
	}

	private final class OfferList extends ListView<Offer> {

		private OfferList(List<Offer> list) {
			super("offer", list);
		}

		@Override
		protected void populateItem(ListItem<Offer> item) {
			Offer offer = item.getModelObject();
			item.add(new Label("title", offer.getTitle()));
			item.add(new Label("category", offer.getCategory().toString()));
			item.add(new Label("price", offer.getPrice().toString()));
			item.add(removeLink(offer));
			item.add(editLink(offer));
		}

		private Link editLink(final Offer offer) {
			return new Link("edit") {
				@Override
				public void onClick() {
					setResponsePage(new AddOfferPage(offer));
				}
			};
		}

		private Link removeLink(final Offer offer) {
			return new Link("remove") {
				@Override
				public void onClick() {
					service.remove(offer);
					OfferList.this.setModelObject(service.getAll());
				}
			};
		}
	}
}
