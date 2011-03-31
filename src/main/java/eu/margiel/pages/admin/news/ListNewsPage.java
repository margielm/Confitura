package eu.margiel.pages.admin.news;

import static eu.margiel.utils.Components.*;
import static org.synyx.hades.domain.Order.*;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDateTime;
import org.synyx.hades.domain.Sort;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.domain.News;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.NewsRepository;

@SuppressWarnings("serial")
@MountPath(path = "admin/news")
public class ListNewsPage extends AdminBasePage {

	@SpringBean
	private NewsRepository repository;

	public ListNewsPage() {
		add(new NewsGrid());
	}

	private final class NewsGrid extends DataView<News> {
		private NewsGrid() {
			super("rows", new ListDataProvider<News>(repository.readAll(new Sort(DESCENDING, "creationDate"))));
		}

		@Override
		protected void populateItem(Item<News> item) {
			News news = item.getModelObject();
			item.add(label("title", news.getTitle()));
			item.add(label("author", news.getAutor().getFullName()));
			item.add(label("date", new LocalDateTime(news.getCreationDate()).toString("HH:mm dd-MM-yyyy")));
			item.add(label("published", news.isPublished() ? "T" : "N"));
			item.add(new RedirectLink("edit", news, AddNewsPage.class));
			item.add(new DeleteLink(news, repository, ListNewsPage.class));

		}
	}
}
