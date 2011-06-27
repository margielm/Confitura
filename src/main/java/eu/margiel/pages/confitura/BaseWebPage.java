package eu.margiel.pages.confitura;

import static com.google.common.collect.Lists.*;
import static eu.margiel.domain.SponsorType.*;

import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.rome.FeedResource;

import eu.margiel.ConfituraBasePage;
import eu.margiel.ConfituraSession;
import eu.margiel.components.menu.MainMenuPanel;
import eu.margiel.components.menu.MenuLinks;
import eu.margiel.domain.SponsorType;
import eu.margiel.pages.confitura.news.NewsFeed;
import eu.margiel.pages.confitura.registration.RegistrationWidget;
import eu.margiel.pages.confitura.sponsor.SponsorWidget;
import eu.margiel.repositories.MenuRepository;
import eu.margiel.repositories.NewsRepository;

@SuppressWarnings("serial")
public class BaseWebPage extends ConfituraBasePage {
	@SpringBean
	protected MenuRepository menuRepository;
	@SpringBean
	private MenuLinks menuItemList;
	@SpringBean
	private NewsRepository newsRepository;

	private WebMarkupContainer wrapper;

	public BaseWebPage() {
		wrapper = new WebMarkupContainer("main_content") {
			@Override
			public boolean isTransparentResolver() {
				return true;
			}
		};
		wrapper.setOutputMarkupId(true);
		add(FeedResource.autodiscoveryLink(new ResourceReference("rss") {
			@Override
			protected Resource newResource() {
				return new NewsFeed(newsRepository);
			}
		}));
		add(wrapper);
		add(new MainMenuPanel("mainMenu", menuRepository.getMainMenu(), menuItemList));
		add(new SponsorWidget("sponsors", SponsorType.sponsors()));
		add(new SponsorWidget("media", newArrayList(MEDIA)));
		// add(new SpeakersWidget("speakers"));
		add(new RegistrationWidget("registration"));
	}

	@Override
	public ConfituraSession getSession() {
		return ConfituraSession.get();
	}
}
