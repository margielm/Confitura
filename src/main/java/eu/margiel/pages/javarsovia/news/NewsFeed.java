package eu.margiel.pages.javarsovia.news;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import eu.margiel.domain.News;
import eu.margiel.repositories.NewsRepository;
import org.synyx.hades.domain.Sort;
import org.wicketstuff.rome.FeedResource;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.synyx.hades.domain.Order.DESCENDING;

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
        if (newsList != null && !newsList.isEmpty()) {
            feed.setPublishedDate(newsList.get(0).getCreationDate());
            feed.setEntries(createEntries(newsList));
        } else {
            feed.setPublishedDate(new Date());
            feed.setEntries(Collections.EMPTY_LIST);
        }
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
        description.setType("text/plain; charset=UTF-8");
        description.setValue(news.getShortDescription());
        return description;
    }

}
