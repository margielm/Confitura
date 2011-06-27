package eu.margiel.pages.javarsovia.news;

import static org.synyx.hades.domain.Order.*;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.synyx.hades.domain.PageRequest;

import eu.margiel.domain.News;
import eu.margiel.repositories.NewsRepository;
import eu.margiel.repositories.PublishedNews;

@SuppressWarnings("serial")
public class NewsProvider implements IDataProvider<News> {

	private final NewsRepository repository;

	public NewsProvider(NewsRepository repository) {
		this.repository = repository;
	}

	@Override
	public void detach() {

	}

	@Override
	public Iterator<? extends News> iterator(int first, int count) {
		return repository.readAll(new PublishedNews(),
				new PageRequest(first / count, count, DESCENDING, "creationDate")).iterator();
	}

	@Override
	public int size() {
		return repository.count(new PublishedNews()).intValue();
	}

	@Override
	public IModel<News> model(News object) {
		return new Model<News>(object);
	}
}
