package eu.margiel.pages.javarsovia;

import eu.margiel.JavarsoviaBasePage;
import eu.margiel.JavarsoviaSession;
import eu.margiel.components.menu.MainMenuPanel;
import eu.margiel.components.menu.MenuLinks;
import eu.margiel.domain.SponsorType;
import eu.margiel.pages.javarsovia.news.NewsFeed;
import eu.margiel.pages.javarsovia.sponsor.SponsorWidget;
import eu.margiel.repositories.MenuRepository;
import eu.margiel.repositories.NewsRepository;
import eu.margiel.repositories.ParticipantRepository;
import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.rome.FeedResource;

import java.io.Serializable;

import static com.google.common.collect.Lists.newArrayList;
import static eu.margiel.domain.SponsorType.MEDIA;

@SuppressWarnings("serial")
public class BaseWebPage extends JavarsoviaBasePage {
	@SpringBean
	protected MenuRepository menuRepository;
	@SpringBean
	private MenuLinks menuItemList;
	@SpringBean
	private NewsRepository newsRepository;
    @SpringBean
    private ParticipantRepository participantRepository;

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
        add(new Label("registrationCounter", new Model<Serializable>(participantRepository.count())));
		//add(new SpeakersWidget("speakers"));
	}

	@Override
	public JavarsoviaSession getSession() {
		return JavarsoviaSession.get();
	}
}
