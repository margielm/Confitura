package eu.margiel.components.mail;

import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.domain.MailTemplate;
import eu.margiel.pages.admin.AdminHomePage;
import eu.margiel.repositories.MailTemplateRepository;

@SuppressWarnings("serial")
public class DefineMailPanel extends Panel {
	@SpringBean
	private MailTemplateRepository repository;
	private final String mailType;
	private final String availableVars;

	public DefineMailPanel(String id, String mailType, String availableVars) {
		super(id);
		this.mailType = mailType;
		this.availableVars = availableVars;
		add(new DefineMailTemplateForm(getMailTemplate()));
	}

	private MailTemplate getMailTemplate() {
		MailTemplate mailTemplate = repository.readByType(mailType);
		return mailTemplate != null ? mailTemplate : new MailTemplate(mailType);
	}

	private final class DefineMailTemplateForm extends Form<Void> {
		private final MailTemplate mailTemplate;

		private DefineMailTemplateForm(MailTemplate mailTemplate) {
			super("form");
			this.mailTemplate = mailTemplate;
			add(label("vars", availableVars));
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
