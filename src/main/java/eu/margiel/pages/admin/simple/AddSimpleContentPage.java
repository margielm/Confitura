package eu.margiel.pages.admin.simple;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.SimpleContent;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.SimpleContentRepository;

@SuppressWarnings("serial")
@MountPath(path = "/admin/simpleContent")
@MountMixedParam(parameterNames = "id")
public class AddSimpleContentPage extends AdminBasePage {
	@SpringBean
	private SimpleContentRepository repository;

	public AddSimpleContentPage(PageParameters params) {
		initPage(repository.readByPrimaryKey(params.getAsInteger("id")));
	}

	public AddSimpleContentPage() {
		initPage(new SimpleContent());
	}

	public void initPage(final SimpleContent simpleContent) {
		Form<SimpleContent> form = new Form<SimpleContent>("form") {
			@Override
			protected void onSubmit() {
				repository.save(simpleContent);
				setResponsePage(ListSimpleContentPage.class);
			}
		};
		form.add(textField("title", new PropertyModel<String>(simpleContent, "title")));
		form.add(richEditor("content", new PropertyModel<String>(simpleContent, "content")));
		form.add(cancelLink(ListSimpleContentPage.class));
		add(form);
	}

}
