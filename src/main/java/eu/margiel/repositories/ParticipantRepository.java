package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.Participant;

public interface ParticipantRepository extends GenericDao<Participant, Integer> {
	@Query("FROM Participant WHERE lower(mail) = lower(?1)")
	Participant readByMail(String eMail);

	Participant readByToken(String token);
}
