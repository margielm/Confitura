package eu.margiel.pages.admin.c4p;

import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.margiel.components.mail.DefineMailPanel;
import eu.margiel.pages.admin.AdminBasePage;
import eu.margiel.repositories.MailTemplateRepository;

public class DefineRegistrationMailPage extends AdminBasePage {

	public DefineRegistrationMailPage() {
		add(new DefineMailPanel("mail", "speaker"));
	}
}
