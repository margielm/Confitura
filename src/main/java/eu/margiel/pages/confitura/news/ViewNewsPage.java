package eu.margiel.pages.confitura.news;

import static eu.margiel.utils.Components.*;
import static org.apache.commons.lang.StringUtils.*;
import static org.joda.time.LocalDate.*;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.LabeledLink;
import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.News;
import eu.margiel.repositories.NewsRepository;

@SuppressWarnings("serial")
@MountPath(path = "news")
public class ViewNewsPage extends NewsBasePage {

	@SpringBean
	private NewsRepository repository;

	public ViewNewsPage() {
		NewsList newsList = new NewsList("news", repository);
		add(newsList);
		add(new PagingNavigator("navigator", newsList));

	}

	private final class NewsList extends DataView<News> {
		private NewsList(String id, NewsRepository repository) {
			super(id, new NewsProvider(repository), 7);
		}

		@Override
		protected void populateItem(Item<News> item) {
			final News news = item.getModelObject();
			item.add(new LabeledLink("title", news.getTitle(), news.getTitle(), ViewNewsDetailsPage.class));
			item.add(linktToAuthor(news));
			item.add(label("creationDate", fromDateFields(news.getCreationDate()).toString("dd.MM.yyyy")));
			item.add(richLabel("shortDescription", news.getShortDescription()));
			item.add(createMoreLinkFor(news));
		}

		private Component createMoreLinkFor(final News news) {
			Link link = new Link("more") {
				@Override
				public void onClick() {
					setResponse(ViewNewsDetailsPage.class, news.getTitle());
				}
			};
			return link.setVisible(isNotBlank(news.getDescription()));
		}

	}

}
