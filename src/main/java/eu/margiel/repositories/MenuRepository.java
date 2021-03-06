package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;

public interface MenuRepository extends GenericDao<MenuItem, Integer> {

	@Query("FROM MainMenu WHERE parent IS NULL")
	public MainMenu getMainMenu();

}
