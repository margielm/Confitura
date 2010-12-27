package eu.margiel.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.google.common.collect.Lists;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("OFFER")
public class OfferContent extends DynamicContent {

	private OfferType offerType;

	public static List<OfferContent> getAll() {
		List<OfferContent> offerContents = Lists.newArrayList();
		for (OfferType offerType : OfferType.values())
			offerContents.add(new OfferContent(offerType));
		return offerContents;
	}

	public OfferContent() {
	}

	public OfferContent(OfferType offerType) {
		this.offerType = offerType;
	}

	@Override
	public String getName() {
		return "Oferty->" + offerType;
	}

	public OfferType getOfferType() {
		return offerType;
	}
}
