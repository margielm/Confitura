package eu.margiel.services;

import java.util.List;

import eu.margiel.domain.Offer;
import eu.margiel.domain.OfferType;

public interface OfferService {
	void save(Offer offer);

	List<Offer> getAll();

	void remove(Offer offer);

	List<Offer> getAllFor(OfferType offerType);
}
