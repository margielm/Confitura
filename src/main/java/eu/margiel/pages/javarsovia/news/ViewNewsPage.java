package eu.margiel.pages.javarsovia.news;

import static eu.margiel.utils.Components.*;
import static org.apache.commons.lang.StringUtils.*;
import static org.joda.time.LocalDate.*;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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
		add(new NewsList("news", repository.fetchPublished()));

	}

	private final class NewsList extends ListView<News> {
		private NewsList(String id, List<News> newsList) {
			super(id, newsList);
		}

		@Override
		protected void populateItem(ListItem<News> item) {
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
