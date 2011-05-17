package eu.margiel.repositories;

import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.Sponsor;

public interface SponsorRepository extends GenericDao<Sponsor, Integer> {

	@Query("FROM Sponsor WHERE type = ?1 ORDER BY money desc")
	List<Sponsor> readByType(String type);

}
