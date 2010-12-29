package eu.margiel.admin;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.StaticContent;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.services.StaticContentService;

@SuppressWarnings("serial")
@MountPath(path = "/admin/editableContentList")
public class EditableContentListPage extends AdminBasePage {
	@SpringBean
	private StaticContentService service;
	private List<StaticContent> contentList;
	private ListView<StaticContent> editableContentList;
	private WebMarkupContainer parent;

	public EditableContentListPage() {
		contentList = service.getAll();
		editableContentList = new ListView<StaticContent>("editable_content_list", contentList) {

			@Override
			protected void populateItem(ListItem<StaticContent> item) {
				StaticContent staticContent = item.getModelObject();
				item.add(new Label("title", staticContent.getTitle()));
				item.add(createRemoveLink(staticContent));
				item.add(createEditLink(staticContent));
			}

		};
		parent = new WebMarkupContainer("table");
		parent.setOutputMarkupId(true);
		parent.add(editableContentList);
		add(parent);
	}

	private void removeContent(StaticContent staticContent) {
		service.remove(staticContent);
		contentList.remove(staticContent);
		editableContentList.setModelObject(contentList);
	}

	private AjaxLink<StaticContent> createRemoveLink(final StaticContent staticContent) {
		return new AjaxLink<StaticContent>("remove") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				removeContent(staticContent);
				target.addComponent(parent);
			}

		};
	}

	private Link createEditLink(final StaticContent staticContent) {
		return new Link("edit") {
			@Override
			public void onClick() {
				setResponsePage(new EditableContentPage(staticContent));
			}
		};
	}
}
