package eu.margiel.repositories;

import eu.margiel.domain.Sponsor;
import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import java.util.List;

public interface SponsorRepository extends GenericDao<Sponsor, Integer> {

    @Query("from Sponsor s where s.type = ?1 order by money desc")
	List<Sponsor> readByType(String type);

}
