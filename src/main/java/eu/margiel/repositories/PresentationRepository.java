package eu.margiel.repositories;

import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.Presentation;

public interface PresentationRepository extends GenericDao<Presentation, Integer> {
	@Query("FROM Presentation WHERE accepted = true")
	List<Presentation> readAllAccepted();

}
