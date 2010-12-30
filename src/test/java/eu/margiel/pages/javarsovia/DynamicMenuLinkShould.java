package eu.margiel.pages.javarsovia;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.apache.wicket.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.synyx.hades.dao.GenericDao;

import eu.margiel.domain.MenuLinkItem;
import eu.margiel.domain.WithTitle;

@RunWith(MockitoJUnitRunner.class)
public class DynamicMenuLinkShould {
	@Mock
	GenericDao<EntityWithTitle, Integer> repository;

	@Test
	public void readAllItemsFromRepository() {
		when(repository.readAll()).thenReturn(newArrayList(new EntityWithTitle(1, "a"), new EntityWithTitle(2, "b")));
		DynamicMenuLink<EntityWithTitle> menuLink = new DynamicMenuLink<EntityWithTitle>(repository,
				Page.class);

		List<MenuLinkItem> items = menuLink.getAllItems();

		assertThat(items).containsOnly(new MenuLinkItem(1, "a"), new MenuLinkItem(2, "b"));
	}

	@SuppressWarnings("serial")
	private class EntityWithTitle implements WithTitle {

		private String title;
		private final Integer id;

		public EntityWithTitle(Integer id, String title) {
			this.id = id;
			this.title = title;
		}

		@Override
		public String getTitle() {
			return title;
		}

		@Override
		public Integer getId() {
			return id;
		}

	}
}
