package eu.margiel.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.margiel.domain.Offer;
import eu.margiel.domain.OfferType;
import eu.margiel.repositories.OfferRepository;
import eu.margiel.services.OfferService;

@Service
public class OfferServiceImpl implements OfferService {
	@Autowired
	private OfferRepository repository;

	@Override
	public void save(Offer offer) {
		repository.save(offer);
	}

	@Override
	public List<Offer> getAll() {
		return repository.readAll();
	}

	@Override
	public void remove(Offer offer) {
		repository.deleteById(offer.getId());
	}

	@Override
	public List<Offer> getAllFor(OfferType offerType) {
		return repository.getAllFor(offerType);
	}

}
