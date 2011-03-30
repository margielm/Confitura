package eu.margiel.pages.javarsovia.news;

import static com.google.common.collect.Lists.*;
import static org.synyx.hades.domain.Order.*;

import java.util.List;

import org.synyx.hades.domain.Sort;
import org.wicketstuff.rome.FeedResource;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

import eu.margiel.domain.News;
import eu.margiel.repositories.NewsRepository;

@SuppressWarnings("serial")
public class NewsFeed extends FeedResource {
	private NewsRepository repository;

	public NewsFeed(NewsRepository repository) {
		this.repository = repository;
	}

	@Override
	protected SyndFeed getFeed() {
		SyndFeedImpl feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("Confitura 2011");
		feed.setLink("http://www.confitura.pl");
		feed.setEncoding("UTF-8");
		feed.setDescription("");
		List<News> newsList = repository.readAll(new Sort(DESCENDING, "creationDate"));
		feed.setPublishedDate(newsList.get(0).getCreationDate());
		feed.setEntries(createEntries(newsList));
		return feed;
	}

	private List<SyndEntry> createEntries(List<News> newsList) {
		List<SyndEntry> entries = newArrayList();
		for (News news : newsList)
			entries.add(createEntry(news));
		return entries;
	}

	private SyndEntry createEntry(News news) {
		SyndEntry entry = new SyndEntryImpl();
		entry.setTitle(news.getTitle());
		entry.setAuthor(news.getAutor().getFullName());
		entry.setPublishedDate(news.getCreationDate());
		entry.setDescription(createDescription(news));
		return entry;
	}

	private SyndContent createDescription(News news) {
		SyndContent description = new SyndContentImpl();
		description.setType("text/plain");
		description.setValue(news.getShortDescription());
		return description;
	}

}
