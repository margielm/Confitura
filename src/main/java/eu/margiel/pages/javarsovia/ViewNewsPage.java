package eu.margiel.pages.javarsovia;

import static eu.margiel.utils.Components.*;
import static org.joda.time.LocalDate.*;
import static org.synyx.hades.domain.Order.*;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.synyx.hades.domain.Sort;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.LabeledLink;
import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.News;
import eu.margiel.pages.admin.MenuItemList;
import eu.margiel.repositories.NewsRepository;

@SuppressWarnings("serial")
@MountPath(path = "news")
public class ViewNewsPage extends BaseWebPage {
	static {
		MenuItemList.add("news", ViewNewsPage.class);
	}
	@SpringBean
	private NewsRepository repository;

	public ViewNewsPage() {
		add(new NewsList("news", repository.readAll(new Sort(DESCENDING, "creationDate"))));
	}

	private final class NewsList extends ListView<News> {
		private NewsList(String id, List<News> newsList) {
			super(id, newsList);
		}

		@Override
		protected void populateItem(ListItem<News> item) {
			final News news = item.getModelObject();
			item.add(new LabeledLink("title", news.getTitle()) {

				@Override
				public void onClick() {
					setResponsePage(new ViewNewsDetailsPage(news));
				}

			});
			item.add(label("creationDate", fromDateFields(news.getCreationDate()).toString("dd-MM-yyyy")));
			item.add(richLabel("shortDescription", news.getShortDescription()));
			item.add(new Link("more") {

				@Override
				public void onClick() {
					setResponsePage(new ViewNewsDetailsPage(news));
				}

			});
		}
	}

}
