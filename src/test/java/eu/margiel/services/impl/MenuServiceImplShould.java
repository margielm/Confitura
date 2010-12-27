package eu.margiel.services.impl;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import eu.margiel.domain.HorizontalMenu;
import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;
import eu.margiel.repositories.MenuRepository;

public class MenuServiceImplShould {
	private MenuServiceImpl service = new MenuServiceImpl();
	private MenuRepository reposiotory = mock(MenuRepository.class);

	@Before
	public void setRepository() {
		service.repository = reposiotory;
	}

	@Test
	public void returnEmptyMenuItemIfHorizontalMenuIsNull() {
		when(reposiotory.getHorizontalMenu()).thenReturn(null);

		MenuItem menu = service.getHorizontalMenu();

		assertEquals("", menu.getName());
		assertThat(menu.getChildren()).isEmpty();
	}

	@Test
	public void returnHorizontalMenuFromRepository() {
		HorizontalMenu horizontalMenu = new HorizontalMenu();
		when(reposiotory.getHorizontalMenu()).thenReturn(horizontalMenu);

		MenuItem menu = service.getHorizontalMenu();

		assertSame(horizontalMenu, menu);
	}

	@Test
	public void returnEmptyMenuItemIfMainMenuIsNull() {
		when(reposiotory.getMainMenu()).thenReturn(null);

		MainMenu menu = service.getMainMenu();

		assertEquals("", menu.getName());
		assertThat(menu.getChildren()).isEmpty();
	}

	@Test
	public void returnMainMenuFromRepository() {
		MainMenu mainMenu = new MainMenu();
		when(reposiotory.getMainMenu()).thenReturn(mainMenu);

		MenuItem menu = service.getMainMenu();

		assertSame(mainMenu, menu);
	}

}
