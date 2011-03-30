package eu.margiel.pages.javarsovia;

import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.rome.FeedResource;

import eu.margiel.JavarsoviaBasePage;
import eu.margiel.JavarsoviaSession;
import eu.margiel.components.menu.MainMenuPanel;
import eu.margiel.components.menu.MenuLinks;
import eu.margiel.pages.javarsovia.news.NewsFeed;
import eu.margiel.repositories.MenuRepository;
import eu.margiel.repositories.NewsRepository;

@SuppressWarnings("serial")
public class BaseWebPage extends JavarsoviaBasePage {
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
	}

	@Override
	public JavarsoviaSession getSession() {
		return JavarsoviaSession.get();
	}
}
