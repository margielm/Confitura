package eu.margiel.components.menu;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Lists.*;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.margiel.domain.MenuItem;
import eu.margiel.domain.MenuLinkItem;
import eu.margiel.domain.SimpleContent;
import eu.margiel.pages.javarsovia.ViewPeoplePage;
import eu.margiel.pages.javarsovia.ViewSimpleContentPage;
import eu.margiel.pages.javarsovia.c4p.ViewSpeakerPage;
import eu.margiel.pages.javarsovia.news.ViewNewsPage;
import eu.margiel.pages.javarsovia.registration.RegistrationPage;
import eu.margiel.pages.javarsovia.speaker.ListPresentationPage;
import eu.margiel.pages.javarsovia.speaker.ListSpeakerPage;
import eu.margiel.pages.javarsovia.sponsor.ViewSponsorsPage;
import eu.margiel.repositories.SimpleContentRepository;

@Component
public class MenuLinks {
	@Autowired
	private SimpleContentRepository repository;
	private List<MenuLink> links = newArrayList();

	@PostConstruct
	public void initLinks() {
		links.add(new SingleMenuLink("Kapitula", ViewPeoplePage.class));
		links.add(new SingleMenuLink("Aktualnosci", ViewNewsPage.class));
		links.add(new SingleMenuLink("C4P", ViewSpeakerPage.class));
		links.add(new SingleMenuLink("Sponsorzy", ViewSponsorsPage.class));
		links.add(new SingleMenuLink("Rejestracja", RegistrationPage.class));
		links.add(new SingleMenuLink("Prelegenci", ListSpeakerPage.class));
		links.add(new SingleMenuLink("Prezentacje", ListPresentationPage.class));
		links.add(new DynamicMenuLink<SimpleContent>(repository, ViewSimpleContentPage.class));
	}

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
