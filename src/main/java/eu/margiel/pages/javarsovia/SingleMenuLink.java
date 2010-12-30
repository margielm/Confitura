package eu.margiel.pages.javarsovia;

import static com.google.common.collect.Lists.*;

import java.util.List;

import eu.margiel.domain.MenuLinkItem;

public class SingleMenuLink extends MenuLink {

	private List<MenuLinkItem> items;

	public SingleMenuLink(String title, Class<?> pageClazz) {
		super(pageClazz);
		this.items = newArrayList(new MenuLinkItem(null, title));
	}

	@Override
	public List<MenuLinkItem> getAllItems() {
		return items;
	}
}
