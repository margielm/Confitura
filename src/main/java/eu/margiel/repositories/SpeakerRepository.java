package eu.margiel.repositories;

import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.Speaker;

public interface SpeakerRepository extends GenericDao<Speaker, Integer> {
	@Query("FROM Speaker WHERE lower(mail) = lower(?1)")
	Speaker readByMail(String eMail);

	Speaker readByToken(String token);

	@Query("SELECT DISTINCT (s) FROM Speaker s  JOIN s.presentations  p WHERE p in " +
			"(SELECT p1 FROM Presentation p1 WHERE p1.accepted = true ) ORDER BY s.lastName, s.firstName")
	List<Speaker> readWithAcceptedPresentations();

}
