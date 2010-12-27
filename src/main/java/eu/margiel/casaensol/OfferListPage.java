package eu.margiel.casaensol;

import java.io.File;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.Offer;
import eu.margiel.domain.OfferType;
import eu.margiel.services.OfferService;
import eu.margiel.utils.FileResource;
import eu.margiel.utils.OfferPhotosProvider;

@SuppressWarnings("serial")
public class OfferListPage extends DynamicContentPage {

	@SpringBean
	private OfferService service;

	public OfferListPage(String title, OfferType offerType) {
		super(title);
		add(new Offers(service.getAllFor(offerType)));
	}

	private class Offers extends ListView<Offer> {
		private Offers(List<? extends Offer> list) {
			super("offer", list);
		}

		@Override
		protected void populateItem(ListItem<Offer> item) {
			final Offer offer = item.getModelObject();

			item.add(new Image("main_photo", getMainPhotoFor(offer)));
			item.add(new Label("short_description", offer.getShortDescription()).setEscapeModelStrings(false));
			Link showOfferLink = new Link("show_offer") {
				@Override
				public void onClick() {
					setResponsePage(new ViewOfferPage(offer));
				}
			};

			showOfferLink.add(new Label("title", offer.getTitle()));
			item.add(showOfferLink);
		}

		private FileResource getMainPhotoFor(Offer offer) {
			OfferPhotosProvider provider = new OfferPhotosProvider(offer);
			File[] photos = provider.getPhotosFolder().listThumbnails();
			return photos.length > 0 ? new FileResource(photos[0]) : null;
		}
	}
}
