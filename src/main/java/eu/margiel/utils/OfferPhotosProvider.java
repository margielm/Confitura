package eu.margiel.utils;

import eu.margiel.domain.Offer;

public class OfferPhotosProvider extends PhotosProvider {

	static final String OFFER_DATE_FORMAT = "yyy_MM_dd_HH_mm_ss";
	private Offer offer;

	public OfferPhotosProvider(Offer offer) {
		this.offer = offer;
	}

	@Override
	String getSubpath() {
		return "offer/" + offer.getCreationDate().toString(OFFER_DATE_FORMAT);
	}

}
