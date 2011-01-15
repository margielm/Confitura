package eu.margiel.repositories;

import org.synyx.hades.dao.GenericDao;

import eu.margiel.domain.News;

public interface NewsRepository extends GenericDao<News, Integer> {

	public News readByTitle(String title);

}
