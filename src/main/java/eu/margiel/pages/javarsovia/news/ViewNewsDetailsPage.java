package eu.margiel.pages.javarsovia.news;

import static eu.margiel.utils.Components.*;
import static org.joda.time.LocalDate.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.News;
import eu.margiel.repositories.NewsRepository;

@MountPath(path = "news/view")
@MountMixedParam(parameterNames = "id")
public class ViewNewsDetailsPage extends NewsBasePage {
	@SpringBean
	private NewsRepository repository;

	public ViewNewsDetailsPage(PageParameters params) {
		News news = repository.readByTitle(params.getString("id"));
		add(linktToAuthor(news));
		add(label("title", news.getTitle()));
		add(label("creationDate", fromDateFields(news.getCreationDate()).toString("dd.MM.yyyy")));
		add(richLabel("shortDescription", news.getShortDescription()));
		add(richLabel("description", news.getDescription()));
	}
}
