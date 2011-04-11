package eu.margiel.pages.admin.c4p;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.MailTemplate;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.pages.admin.AdminHomePage;
import eu.margiel.repositories.MailTemplateRepository;

public class DefineRegistrationMailPage extends AdminBasePage {
	@SpringBean
	private MailTemplateRepository repository;

	public DefineRegistrationMailPage() {
		add(new DefineMailTemplateForm(getMailTemplate()));
	}

	private MailTemplate getMailTemplate() {
		MailTemplate mailTemplate = repository.readByType("speaker");
		return mailTemplate != null ? mailTemplate : new MailTemplate("speaker");
	}

	@SuppressWarnings("serial")
	private final class DefineMailTemplateForm extends Form<Void> {
		private final MailTemplate mailTemplate;

		private DefineMailTemplateForm(MailTemplate mailTemplate) {
			super("form");
			this.mailTemplate = mailTemplate;
			add(textField("subject", propertyModel(mailTemplate, "subject")));
			add(richEditor("template", propertyModel(mailTemplate, "template")));
			add(cancelLink(AdminHomePage.class));
		}

		@Override
		protected void onSubmit() {
			repository.save(mailTemplate);
		}
	}
}
