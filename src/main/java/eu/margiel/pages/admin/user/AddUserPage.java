package eu.margiel.pages.admin.user;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.User;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.UserRepository;

public class AddUserPage extends AdminBasePage {

	private User user = new User();
	@SpringBean
	private UserRepository repository;

	public AddUserPage() {
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		final PasswordTextField password = passwordField("password", propertyModel(user, "password"), true);
		final PasswordTextField repassword = passwordField("repassword", new Model<String>(), true);
		Form<User> form = new UserForm(password, repassword, feedbackPanel);
		form.add(feedbackPanel);
		form.add(textField("firstName", propertyModel(user, "firstName"), true));
		form.add(textField("lastName", propertyModel(user, "lastName"), true));
		form.add(textField("userName", propertyModel(user, "userName"), true));
		form.add(textField("mail", propertyModel(user, "mail"), true));
		form.add(password);
		form.add(repassword);
		add(form);
	}

	@SuppressWarnings("serial")
	private final class UserForm extends Form<User> {
		private final PasswordTextField password;
		private final PasswordTextField repassword;
		private final FeedbackPanel feedbackPanel;

		private UserForm(PasswordTextField password, PasswordTextField repassword, FeedbackPanel feedbackPanel) {
			super("form");
			this.password = password;
			this.repassword = repassword;
			this.feedbackPanel = feedbackPanel;
		}

		@Override
		protected void onSubmit() {
			if (password.getInput().equals(repassword.getInput()) == false) {
				feedbackPanel.error("Hasła nie pasują");
				return;
			} else {
				user.encryptPassword();
				repository.save(user);
			}
		}

	}
}
