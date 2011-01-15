package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import eu.margiel.domain.MenuItem;
import eu.margiel.domain.MenuLinkItem;
import eu.margiel.pages.admin.MenuItemList;
import eu.margiel.pages.javarsovia.MenuLink;

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
		MenuLink menuLink = getMenuLinkFor(menuItem);
		if (menuLink != null)
			return new LinkOrLabel(id, menuItem.getName(), menuLink.getPageClazz(), menuItem.getLinkItem().getLinkTo());
		return null;
	}

	private Class<? extends Page> getPageFor(MenuItem menuItem) {
		MenuLinkItem linkItem = menuItem.getLinkItem();
		if (linkItem == null)
			return null;
		return menuItemList.getPage(linkItem);
	}

	private MenuLink getMenuLinkFor(MenuItem menuItem) {
		MenuLinkItem linkItem = menuItem.getLinkItem();
		if (linkItem == null)
			return null;
		return menuItemList.getMenuLinkFor(linkItem);
	}
}
