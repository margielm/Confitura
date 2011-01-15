package eu.margiel.pages.admin.user;

import static eu.margiel.utils.Components.*;

import java.util.List;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.User;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.pages.admin.simple.ListSimpleContentPage;
import eu.margiel.repositories.UserRepository;

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
			item.add(new Link("edit") {
				@Override
				public void onClick() {
					// setResponsePage(new AddSimpleContentPage(user));
				}
			});
			item.add(new Link("delete") {
				@Override
				public void onClick() {
					repository.delete(user);
					setResponsePage(ListSimpleContentPage.class);
				}
			});
		}
	}

}
