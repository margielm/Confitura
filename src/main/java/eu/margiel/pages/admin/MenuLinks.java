package eu.margiel.pages.admin;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Lists.*;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.margiel.domain.MenuItem;
import eu.margiel.domain.MenuLinkItem;
import eu.margiel.domain.SimpleContent;
import eu.margiel.pages.javarsovia.DynamicMenuLink;
import eu.margiel.pages.javarsovia.MenuLink;
import eu.margiel.pages.javarsovia.SingleMenuLink;
import eu.margiel.pages.javarsovia.ViewNewsPage;
import eu.margiel.pages.javarsovia.ViewSimpleContentPage;
import eu.margiel.pages.javarsovia.c4p.LoginSpeakerPage;
import eu.margiel.repositories.SimpleContentRepository;

@Component
public class MenuLinks {
	@Autowired
	private SimpleContentRepository repository;
	private List<MenuLink> links = newArrayList();

	@PostConstruct
	public void initLinks() {
		links.add(new SingleMenuLink("Aktualności", ViewNewsPage.class));
		links.add(new SingleMenuLink("C4P", LoginSpeakerPage.class));
		links.add(new DynamicMenuLink<SimpleContent>(repository, ViewSimpleContentPage.class));
	}

	// public MenuLink getMenuLinkFor(MenuLinkItem menuLinkItem) {
	// return getMenuLinkFor(menuLinkItem.getTitle());
	// }

	MenuLink getMenuLinkFor(MenuLinkItem menuLinkItem) {
		return selectFirst(links, having(on(MenuLink.class).containsLink(menuLinkItem.getTitle())));
	}

	public void add(MenuLink menuLink) {
		links.add(menuLink);
	}

	@SuppressWarnings("unchecked")
	public List<MenuLinkItem> getAllItems() {
		return (List<MenuLinkItem>) collect(collect(links, on(MenuLink.class).getAllItems()));
	}

	public MenuLink getMenuLinkFor(MenuItem menuItem) {
		if (menuItem != null && menuItem.getLinkItem() != null)
			return getMenuLinkFor(menuItem.getLinkItem());
		return null;
	}

}
