package eu.margiel.pages.javarsovia;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.apache.wicket.Page;

import eu.margiel.domain.MenuLinkItem;

public abstract class MenuLink {
	final Class<? extends Page> pageClazz;

	public MenuLink(Class<? extends Page> pageClazz) {
		this.pageClazz = pageClazz;
	}

	public Boolean containsLink(String link) {
		return selectFirst(getAllItems(), having(on(MenuLinkItem.class).getName(), is(link))) != null;
	}

	public abstract List<MenuLinkItem> getAllItems();

	public Class<? extends Page> getPageClazz() {
		return pageClazz;
	}

	public Page getPage(Integer id) {
		try {
			if (id == null)
				return pageClazz.newInstance();
			else
				return pageClazz.getConstructor(Integer.class).newInstance(id);
		} catch (Exception ex) {
			throw new RuntimeException("Can not create page for " + pageClazz, ex);
		}
	}
}
