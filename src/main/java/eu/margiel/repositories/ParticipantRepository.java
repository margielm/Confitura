package eu.margiel.repositories;

import eu.margiel.domain.Participant;
import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

public interface ParticipantRepository extends GenericDao<Participant, Integer> {

    @Query("FROM Participant WHERE lower(mail) = lower(?1)")
	Participant readByMail(String eMail);

	Participant readByToken(String token);

    @Query("SELECT COUNT(p.id) FROM Participant p")
    Long wantDinner();

}
