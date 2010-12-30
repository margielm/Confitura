package eu.margiel.pages.javarsovia;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.SimpleContent;
import eu.margiel.repositories.SimpleContentRepository;

public class ViewSimpleContentPage extends BaseWebPage {
	@SpringBean
	private SimpleContentRepository repository;

	public ViewSimpleContentPage(Integer simpleContentId) {
		SimpleContent simpleContent = repository.readByPrimaryKey(simpleContentId);
		add(label("title", simpleContent.getTitle()));
		add(richLabel("content", simpleContent.getContent()));
	}
}
