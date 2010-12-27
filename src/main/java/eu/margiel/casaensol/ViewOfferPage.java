package eu.margiel.casaensol;

import static com.google.common.collect.Lists.*;
import static eu.margiel.utils.Components.*;

import java.io.File;
import java.util.List;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import eu.margiel.domain.Offer;
import eu.margiel.utils.FileResource;
import eu.margiel.utils.OfferPhotosProvider;

public class ViewOfferPage extends DynamicContentPage {

	public ViewOfferPage(Offer offer) {
		super(offer.getTitle());
		File[] photos = new OfferPhotosProvider(offer).getPhotosFolder().listPhotos();
		ListView<File> photosList = new PhotosList(newArrayList(photos));
		add(photosList);
		add(richLabel("short_description", offer.getShortDescription()));
		add(richLabel("description", offer.getDescription()));
	}

	@SuppressWarnings("serial")
	private class PhotosList extends ListView<File> {
		private PhotosList(List<File> list) {
			super("photos", list);
		}

		@Override
		protected void populateItem(ListItem<File> item) {
			item.add(new Image("photo", new FileResource(item.getModelObject())));
		}
	}
}
