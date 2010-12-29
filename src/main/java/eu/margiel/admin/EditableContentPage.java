package eu.margiel.admin;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.domain.StaticContent;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.services.StaticContentService;

@SuppressWarnings("serial")
@MountPath(path = "/admin/editableContent")
public class EditableContentPage extends AdminBasePage {
	@SpringBean
	private StaticContentService service;

	public EditableContentPage() {
		this(new StaticContent());
	}

	public EditableContentPage(final StaticContent staticContent) {
		Form<StaticContent> form = new Form<StaticContent>("form") {
			@Override
			protected void onSubmit() {
				service.save(staticContent);
				setResponsePage(EditableContentListPage.class);
			}
		};
		form.add(textField("title", new PropertyModel<String>(staticContent, "title")));
		form.add(richEditor("editor", new PropertyModel<String>(staticContent, "content")));
		add(form);
	}

}
