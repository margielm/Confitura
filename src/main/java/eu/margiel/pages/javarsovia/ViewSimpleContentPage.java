package eu.margiel.pages.javarsovia;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.SimpleContent;
import eu.margiel.repositories.SimpleContentRepository;

@MountPath(path = "view")
@MountMixedParam(parameterNames = "id")
public class ViewSimpleContentPage extends BaseWebPage {
	@SpringBean
	private SimpleContentRepository repository;

	public ViewSimpleContentPage(PageParameters params) {
		SimpleContent simpleContent = repository.readByPrimaryKey(params.getAsInteger("id"));
		add(label("title", simpleContent.getTitle()));
		add(richLabel("content", simpleContent.getContent()));
	}
}
