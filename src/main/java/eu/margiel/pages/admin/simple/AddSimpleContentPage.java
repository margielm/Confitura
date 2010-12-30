package eu.margiel.pages.admin.simple;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.SimpleContent;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.SimpleContentRepository;

@SuppressWarnings("serial")
@MountPath(path = "/admin/simpleContent")
public class AddSimpleContentPage extends AdminBasePage {
	@SpringBean
	private SimpleContentRepository repository;

	public AddSimpleContentPage() {
		this(new SimpleContent());
	}

	public AddSimpleContentPage(final SimpleContent simpleContent) {
		Form<SimpleContent> form = new Form<SimpleContent>("form") {
			@Override
			protected void onSubmit() {
				repository.save(simpleContent);
				setResponsePage(ListSimpleContentPage.class);
			}
		};
		form.add(textField("title", new PropertyModel<String>(simpleContent, "title")));
		form.add(richEditor("content", new PropertyModel<String>(simpleContent, "content")));
		add(form);
	}

}
