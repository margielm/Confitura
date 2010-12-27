package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.HorizontalMenu;
import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;

public interface MenuRepository extends GenericDao<MenuItem, Integer> {

	@Query("FROM HorizontalMenu WHERE parent IS NULL")
	public HorizontalMenu getHorizontalMenu();

	@Query("FROM MainMenu WHERE parent IS NULL")
	public MainMenu getMainMenu();

}
