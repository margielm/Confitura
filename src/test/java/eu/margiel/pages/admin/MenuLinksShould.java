package eu.margiel.pages.admin;

import static org.fest.assertions.Assertions.*;

import java.util.List;

import org.apache.wicket.Page;
import org.junit.Test;

import eu.margiel.domain.MenuLinkItem;
import eu.margiel.pages.javarsovia.SingleMenuLink;

public class MenuLinksShould {
	private MenuLinks menuItemList = new MenuLinks();
	private SingleMenuLink a = new SingleMenuLink("a", TestWebPage1.class);
	private SingleMenuLink b = new SingleMenuLink("b", TestWebPage2.class);

	@Test
	public void collectAllItems() {
		menuItemList.add(a);
		menuItemList.add(b);

		List<MenuLinkItem> links = menuItemList.getAllItems();

		assertThat(links).containsOnly(new MenuLinkItem(null, "a"), new MenuLinkItem(null, "b"));
	}

	@Test
	public void getMenuLinkForLink() {
		menuItemList.add(a);
		menuItemList.add(b);

		assertThat(menuItemList.getMenuLinkFor(new MenuLinkItem(null, "a"))).isSameAs(a);
		assertThat(menuItemList.getMenuLinkFor(new MenuLinkItem(null, "b"))).isSameAs(b);
	}

	class TestWebPage1 extends Page {
	}

	class TestWebPage2 extends Page {
	}
}