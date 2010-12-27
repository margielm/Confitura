package eu.margiel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.margiel.domain.HorizontalMenu;
import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;
import eu.margiel.repositories.MenuRepository;
import eu.margiel.services.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepository repository;

	@Override
	public MainMenu getMainMenu() {
		MainMenu menu = repository.getMainMenu();
		return menu != null ? menu : new MainMenu();
	}

	@Override
	public HorizontalMenu getHorizontalMenu() {
		HorizontalMenu menu = repository.getHorizontalMenu();
		return menu != null ? menu : new HorizontalMenu();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends MenuItem> T save(T mainMenu) {
		return (T) repository.save(mainMenu);
	}
}
