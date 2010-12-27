package eu.margiel.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Modifying;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.Offer;
import eu.margiel.domain.OfferType;

public interface OfferRepository extends GenericDao<Offer, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Offer WHERE id = ?")
	void deleteById(Long id);

	@Query("FROM Offer WHERE type = ?")
	List<Offer> getAllFor(OfferType offerType);

}
