package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;

import eu.margiel.domain.Admin;

public interface AdminRepository extends GenericDao<Admin, Integer> {

	public Admin readByUserName(String userName);

}
