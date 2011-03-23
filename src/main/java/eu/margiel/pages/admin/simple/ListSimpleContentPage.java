package eu.margiel.pages.admin.simple;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.domain.SimpleContent;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.SimpleContentRepository;

@SuppressWarnings("serial")
@MountPath(path = "/admin/staticContentList")
public class ListSimpleContentPage extends AdminBasePage {

	@SpringBean
	private SimpleContentRepository repository;

	public ListSimpleContentPage() {
		add(new SimpleContentGrid("rows", repository.readAll()));
	}

	private final class SimpleContentGrid extends DataView<SimpleContent> {
		private SimpleContentGrid(String id, List<SimpleContent> list) {
			super(id, new ListDataProvider<SimpleContent>(list));
		}

		@Override
		protected void populateItem(Item<SimpleContent> item) {
			SimpleContent simpleContent = item.getModelObject();
			item.add(label("title", simpleContent.getTitle()));
			item.add(new RedirectLink("edit", simpleContent, AddSimpleContentPage.class));
			item.add(new DeleteLink(simpleContent, repository, ListSimpleContentPage.class));
		}
	}
}
