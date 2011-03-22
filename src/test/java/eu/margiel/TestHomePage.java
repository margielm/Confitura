package eu.margiel;

import static org.mockito.Mockito.*;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import eu.margiel.components.menu.MainMenuPanel;
import eu.margiel.components.menu.MenuLinks;
import eu.margiel.domain.MenuItem;

@SuppressWarnings("serial")
public class TestHomePage {
	private WicketTester tester = new WicketTester();

	@Test
	public void testRenderMyPage() {
		final MenuItem mainMenu = new MenuItem("parent").addMenuItem(new MenuItem("1"));
		tester.startPanel(new TestPanelSource() {

			@Override
			public Panel getTestPanel(String id) {
				MenuLinks menuLinks = mock(MenuLinks.class);
				return new MainMenuPanel(id, mainMenu, menuLinks);
			}
		});

	}
}
