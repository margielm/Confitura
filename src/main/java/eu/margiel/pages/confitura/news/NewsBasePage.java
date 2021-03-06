package eu.margiel.pages.confitura.news;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.ExternalLink;

import eu.margiel.domain.Admin;
import eu.margiel.domain.News;
import eu.margiel.pages.confitura.BaseWebPage;
import eu.margiel.pages.confitura.ViewPeoplePage;

public class NewsBasePage extends BaseWebPage {
	ExternalLink linktToAuthor(News news) {
		return new ExternalLink("author", getLinkTo(news.getAutor()), news.getAutor().getFullName());
	}

	private String getLinkTo(Admin author) {
		return "/" + urlFor(ViewPeoplePage.class, new PageParameters()) + "#" + author.getUserName();
	}
}
