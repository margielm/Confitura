package eu.margiel.services;

import java.util.List;

import eu.margiel.domain.StaticContent;

public interface StaticContentService {
	public void save(StaticContent content);

	public List<StaticContent> getAll();

	public void remove(StaticContent staticContent);
}
