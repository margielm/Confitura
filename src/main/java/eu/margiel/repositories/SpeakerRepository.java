package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.Speaker;
import eu.margiel.domain.User;

public interface SpeakerRepository extends GenericDao<Speaker, Integer> {
	@Query("FROM Speaker WHERE lower(mail) = lower(?1)")
	User readByMail(String eMail);

}
