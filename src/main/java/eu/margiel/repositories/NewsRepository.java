package eu.margiel.repositories;

import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Query;

import eu.margiel.domain.News;

public interface NewsRepository extends GenericDao<News, Integer> {

	public News readByTitle(String title);

	public List<News> readByPublished(boolean published);

	@Query("FROM News WHERE published = true ORDER BY creationDate DESC")
	public List<News> fetchPublished();

}
