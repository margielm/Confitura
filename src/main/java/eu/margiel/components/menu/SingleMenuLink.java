package eu.margiel.components.menu;

import static com.google.common.collect.Lists.*;

import java.util.List;

import org.apache.wicket.Page;

import eu.margiel.domain.MenuLinkItem;

public class SingleMenuLink extends MenuLink {

	private List<MenuLinkItem> items;

	public SingleMenuLink(String title, Class<? extends Page> pageClazz) {
		super(pageClazz);
		this.items = newArrayList(new MenuLinkItem(null, title));
	}

	@Override
	public List<MenuLinkItem> getAllItems() {
		return items;
	}
}
