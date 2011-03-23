package eu.margiel.pages.admin.user;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

import eu.margiel.domain.Admin;
import eu.margiel.domain.User;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.AdminRepository;

@MountPath(path = "admin/users/view")
@MountMixedParam(parameterNames = "id")
public class AddUserPage extends AdminBasePage {

	@SpringBean
	private AdminRepository repository;

	public AddUserPage() {
		initPage(new Admin());
	}

	public AddUserPage(PageParameters params) {
		initPage(repository.readByPrimaryKey(params.getAsInteger("id")));
	}

	private void initPage(Admin user) {
		add(new UserForm(user));
	}

	@SuppressWarnings("serial")
	private final class UserForm extends Form<User> {
		private final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		private final PasswordTextField repassword = passwordField("repassword", new Model<String>(), true);
		private final PasswordTextField password;
		private final Admin admin;

		private UserForm(Admin admin) {
			super("form");
			this.admin = admin;
			this.password = passwordField("password", propertyModel(admin, "password"), true);
			add(feedbackPanel);
			add(textField("firstName", propertyModel(admin, "firstName"), true));
			add(textField("lastName", propertyModel(admin, "lastName"), true));
			add(textField("userName", propertyModel(admin, "userName"), true));
			add(textField("mail", propertyModel(admin, "mail"), true));
			add(password);
			add(repassword);
			add(cancelLink(ListUserPage.class));
		}

		@Override
		protected void onSubmit() {
			if (password.getInput().equals(repassword.getInput()) == false) {
				feedbackPanel.error("Hasła nie pasują");
				return;
			} else {
				admin.encryptPassword();
				repository.save(admin);
				setResponsePage(ListUserPage.class);
			}
		}

	}
}
