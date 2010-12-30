package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import eu.margiel.domain.MenuItem;
import eu.margiel.domain.MenuLinkItem;
import eu.margiel.pages.admin.MenuItemList;

@SuppressWarnings({ "serial" })
public class MainMenuPanel extends Panel {

	private final MenuItemList menuItemList;

	public MainMenuPanel(String id, MenuItem mainMenu, MenuItemList menuItemList) {
		super(id);
		this.menuItemList = menuItemList;
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
		return new LinkOrLabel(id, menuItem.getName(), getPageFor(menuItem));
	}

	private Page getPageFor(MenuItem menuItem) {
		MenuLinkItem linkItem = menuItem.getLinkItem();
		if (linkItem == null)
			return null;
		return menuItemList.getPageFor(linkItem);
	}
}
