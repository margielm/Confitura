package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;

import eu.margiel.domain.User;

public interface UserRepository extends GenericDao<User, Integer> {

	public User readByUserName(String userName);

}
