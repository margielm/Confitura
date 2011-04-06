package eu.margiel.repositories;

import java.util.List;

import org.synyx.hades.dao.GenericDao;

import eu.margiel.domain.Sponsor;

public interface SponsorRepository extends GenericDao<Sponsor, Integer> {

	List<Sponsor> readByType(String type);

}
