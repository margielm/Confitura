package eu.margiel.pages.javarsovia;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.synyx.hades.domain.Sort;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.StaticImage;
import eu.margiel.domain.Admin;
import eu.margiel.pages.javarsovia.c4p.SpeakerPhotoProvider;
import eu.margiel.repositories.AdminRepository;

@MountPath(path = "kapitula")
public class ViewPeoplePage extends BaseWebPage {

	@SpringBean
	private AdminRepository repository;
	private transient SpeakerPhotoProvider provider = new SpeakerPhotoProvider("kapitula");

	public ViewPeoplePage() {
		add(new PeopleList("person", repository.readAll(new Sort("lastName"))));
	}

	@SuppressWarnings("serial")
	private final class PeopleList extends ListView<Admin> {
		private PeopleList(String id, List<? extends Admin> list) {
			super(id, list);
		}

		@Override
		protected void populateItem(ListItem<Admin> item) {
			Admin admin = item.getModelObject();
			item.add(new AttributeModifier("class", getCssClass(item)));
			item.add(label("name", admin.getFullName()));
			item.add(richLabel("bio", admin.getBio()));
			item.add(new StaticImage("photo", provider.getPathTo(admin)));
		}

		private Model<String> getCssClass(ListItem<Admin> item) {
			return new Model<String>(item.getIndex() % 2 == 0 ? "odd" : "");
		}
	}
}
