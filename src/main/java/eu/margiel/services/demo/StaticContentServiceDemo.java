package eu.margiel.services.demo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import eu.margiel.domain.StaticContent;
import eu.margiel.services.StaticContentService;

@Service
public class StaticContentServiceDemo implements StaticContentService {

	@Override
	public void save(StaticContent content) {
	}

	@Override
	public List<StaticContent> getAll() {
		return Lists.newArrayList();
	}

	@Override
	@Transactional
	public void remove(StaticContent staticContent) {
	}

}
