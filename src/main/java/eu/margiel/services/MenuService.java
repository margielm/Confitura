package eu.margiel.services;

import eu.margiel.domain.HorizontalMenu;
import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;

public interface MenuService {

	MainMenu getMainMenu();

	HorizontalMenu getHorizontalMenu();

	<T extends MenuItem> T save(T mainMenu);

}
