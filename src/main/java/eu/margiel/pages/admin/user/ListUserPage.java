package eu.margiel.pages.admin.user;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.components.DeleteLink;
import eu.margiel.components.RedirectLink;
import eu.margiel.domain.User;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.UserRepository;

@MountPath(path = "admin/users")
public class ListUserPage extends AdminBasePage {
	@SpringBean
	private UserRepository repository;

	public ListUserPage() {
		add(new UserGrid(repository.readAll()));
	}

	@SuppressWarnings("serial")
	private final class UserGrid extends DataView<User> {
		private UserGrid(List<User> list) {
			super("rows", new ListDataProvider<User>(list));
		}

		@Override
		protected void populateItem(Item<User> item) {
			final User user = item.getModelObject();
			item.add(label("userName", user.getUserName()));
			item.add(label("firstName", user.getFirstName()));
			item.add(label("lastName", user.getLastName()));
			item.add(new RedirectLink("edit", user, AddUserPage.class));
			item.add(new DeleteLink(user, repository, ListUserPage.class));
		}
	}

}
