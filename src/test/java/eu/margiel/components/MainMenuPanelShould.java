package eu.margiel.components;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Ignore;
import org.junit.Test;

import eu.margiel.domain.MainMenu;
import eu.margiel.domain.MenuItem;

@SuppressWarnings("serial")
public class MainMenuPanelShould extends WicketBaseTest {
	private MenuItem mainMenu = new MainMenu();

	@Test
	@Ignore
	public void renderTopLevelItems() {
		mainMenu.children(new MenuItem("item 1"), new MenuItem("item 2"));

		createPanelWith(mainMenu);

		assertMenuItem("item 1", 0);
		assertMenuItem("item 2", 1);
	}

	@Test
	@Ignore
	public void renderSubitems() {
		mainMenu.children(new MenuItem("item").children(new MenuItem("subitem 1"), new MenuItem("subitem 2")));

		createPanelWith(mainMenu);

		assertMenuItem("item", 0);
		assertMenuSubitems("menuItems", "subitem 1", "subitem 2");
	}

	private void assertMenuSubitems(String item, String... subitems) {
		String itemValue = getTags(item).get(0).getValue();
		List<TagTester> subitemsTags = TagTester.createTagsByAttribute(itemValue, "wicket:id", "submenuItem", false);
		for (int idx = 0; idx < subitems.length; idx++)
			assertEquals(subitems[idx], subitemsTags.get(idx).getChild("wicket:id", "label").getValue());
	}

	private void assertMenuItem(String name, int menuIdx) {
		assertEquals(name, getTags("menuItem").get(menuIdx).getChild("wicket:id", "label").getValue());
	}

	private void createPanelWith(final MenuItem mainMenu) {
		tester.startPanel(new TestPanelSource() {
			@Override
			public Panel getTestPanel(String panelId) {
				return new MainMenuPanel(panelId, mainMenu, null);
			}
		});
	}
}
