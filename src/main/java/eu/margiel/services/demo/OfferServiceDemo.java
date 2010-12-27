package eu.margiel.services.demo;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import eu.margiel.domain.Offer;
import eu.margiel.domain.OfferType;
import eu.margiel.services.OfferService;

@Service
public class OfferServiceDemo implements OfferService {
	Map<Long, Offer> offers = newHashMap();

	@Override
	public void save(Offer offer) {
		if (offer.getId() == null)
			offer.id(IdUtils.nextId());
		offers.put(offer.getId(), offer);
	}

	@Override
	public List<Offer> getAll() {
		return newArrayList(offers.values());
	}

	@Override
	public void remove(Offer offer) {
		offers.remove(offer.getId());
	}

	@Override
	public List<Offer> getAllFor(OfferType offerType) {
		// TODO Auto-generated method stub
		return null;
	}

}
