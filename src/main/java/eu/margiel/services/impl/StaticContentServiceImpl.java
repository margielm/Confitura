package eu.margiel.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.margiel.domain.StaticContent;
import eu.margiel.repositories.StaticContentRepository;
import eu.margiel.services.StaticContentService;

@Service
public class StaticContentServiceImpl implements StaticContentService {

	@Autowired
	private StaticContentRepository repository;

	@Override
	public void save(StaticContent content) {
		repository.save(content);
	}

	@Override
	public List<StaticContent> getAll() {
		return repository.readAll();
	}

	@Override
	@Transactional
	public void remove(StaticContent staticContent) {
		repository.deleteById(staticContent.getId());
	}

}
