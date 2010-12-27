package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import eu.margiel.casaensol.OfferListPage;
import eu.margiel.casaensol.ViewContentPage;
import eu.margiel.domain.DynamicContent;
import eu.margiel.domain.MenuItem;
import eu.margiel.domain.OfferContent;
import eu.margiel.domain.StaticContent;

@SuppressWarnings({ "serial" })
public class MainMenuPanel extends Panel {

	public MainMenuPanel(String id, MenuItem mainMenu) {
		super(id);
		add(new ListView<MenuItem>("menuItems", mainMenu.getChildren()) {
			@Override
			protected void populateItem(ListItem<MenuItem> item) {
				MenuItem menuItem = item.getModelObject();
				item.setVisible(menuItem.isPublished());
				item.add(createLink("menuItem", menuItem));
				item.add(new ListView<MenuItem>("submenuItems", menuItem.getChildren()) {
					@Override
					protected void populateItem(ListItem<MenuItem> item) {
						MenuItem submenu = item.getModelObject();
						item.setVisible(submenu.isPublished());
						item.add(createLink("submenuItem", submenu));
					}

				});

			}

		});
	}

	private LinkOrLabel createLink(String id, MenuItem menuItem) {
		return new LinkOrLabel(id, menuItem.getName(), getPageForContentType(menuItem));
	}

	private Page getPageForContentType(MenuItem menuItem) {
		DynamicContent content = menuItem.getContent();
		if (content instanceof OfferContent)
			return new OfferListPage(menuItem.getName(), ((OfferContent) content).getOfferType());
		else if (content instanceof StaticContent)
			return new ViewContentPage(menuItem.getName(), (StaticContent) content);
		else
			return null;
	}
}
