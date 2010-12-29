package eu.margiel.pages.javarsovia;

import static eu.margiel.utils.Components.*;
import static org.joda.time.LocalDate.*;
import eu.margiel.domain.News;

public class ViewNewsDetailsPage extends BaseWebPage {
	public ViewNewsDetailsPage(News news) {
		add(label("title", news.getTitle()));
		add(label("creationDate", fromDateFields(news.getCreationDate()).toString("dd-MM-yyyy")));
		add(richLabel("shortDescription", news.getShortDescription()));
		add(richLabel("description", news.getDescription()));
	}
}
