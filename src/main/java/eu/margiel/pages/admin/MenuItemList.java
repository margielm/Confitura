package eu.margiel.pages.admin;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Lists.*;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.wicket.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class MenuItemList {
	@Autowired
	private SimpleContentRepository repository;
	private List<MenuLink> links = newArrayList();

	@PostConstruct
	public void initLinks() {
		links.add(new SingleMenuLink("Aktualności", ViewNewsPage.class));
		links.add(new SingleMenuLink("C4P", LoginSpeakerPage.class));
		links.add(new DynamicMenuLink<SimpleContent>(repository, ViewSimpleContentPage.class));
	}

	public Page getPageFor(MenuLinkItem menuLinkItem) {
		MenuLink menuLink = getMenuLinkFor(menuLinkItem.getName());
		if (menuLink != null)
			return menuLink.getPage(menuLinkItem.getLinkTo());
		return null;
	}

	MenuLink getMenuLinkFor(String link) {
		return selectFirst(links, having(on(MenuLink.class).containsLink(link)));
	}

	public void add(MenuLink menuLink) {
		links.add(menuLink);
	}

	@SuppressWarnings("unchecked")
	public List<MenuLinkItem> getAllItems() {
		return (List<MenuLinkItem>) collect(collect(links, on(MenuLink.class).getAllItems()));
	}

}
