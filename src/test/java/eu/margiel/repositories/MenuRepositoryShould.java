package eu.margiel.repositories;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import eu.margiel.domain.HorizontalMenu;
import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-spring.xml")
@Transactional
public class MenuRepositoryShould {
	@Autowired
	private MenuRepository repository;
	private MenuItem mainMenu = new MainMenu().children(new MenuItem("main item"));
	private MenuItem horizontalMenu = new HorizontalMenu().children(new MenuItem("horizontal item"));

	@Test
	public void saveMenuItem() {
		repository.save(new MenuItem("name"));

		assertThat(repository.readAll()).hasSize(1);
	}

	@Test
	public void saveAndFetchHorizontalMenu() {
		repository.save(mainMenu);
		repository.save(horizontalMenu);

		MenuItem fetchedMenu = repository.getHorizontalMenu();

		assertEquals(horizontalMenu, fetchedMenu);
	}

	@Test
	public void saveAndFetchMainMenu() {
		repository.save(mainMenu);
		repository.save(horizontalMenu);

		MenuItem fetchedMenu = repository.getMainMenu();

		assertEquals(mainMenu, fetchedMenu);
	}

	@Test
	public void removeNotExistingMenuItem() {
		repository.delete(new MenuItem(""));
	}

}
