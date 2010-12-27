package eu.margiel.services.demo;

import org.springframework.stereotype.Component;

import eu.margiel.domain.HorizontalMenu;
import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;
import eu.margiel.services.MenuService;

@Component
public class MenuServiceDemo implements MenuService {
	private static final MainMenu mainMenu = createMainMenu();
	private static final HorizontalMenu horizontalMenu = createHorizontalMenu();

	@Override
	public MainMenu getMainMenu() {
		return mainMenu;
	}

	@Override
	public HorizontalMenu getHorizontalMenu() {
		return horizontalMenu;
	}

	private static MainMenu createMainMenu() {
		return (MainMenu) new MainMenu().children(
					m("Lokalizacja")
							.children(
									m("Costa Brava"),
									m("Costa Dorada")),
					m("Oferty")
							.children(
									m("Najtańsze"),
									m("Najbardziej korzystne"),
									m("Najbardziej ekskluzywne")),
					m("Dlaczego Hiszpania")
							.children(
									m("Bo tak"),
									m("Bo tak 2")
									)
					);
	}

	private static HorizontalMenu createHorizontalMenu() {
		return (HorizontalMenu) new HorizontalMenu().children(
				m("HOME"),
				m("O nas"),
				m("Doradztwo"),
				m("Partnerzy"),
				m("Kontakty")
				);
	}

	private static MenuItem m(String name) {
		return new MenuItem(name);
	}

	@Override
	public <T extends MenuItem> T save(T mainMenu) {
		return mainMenu;
	}

}
