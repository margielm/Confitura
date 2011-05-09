package eu.margiel.pages.admin.c4p;

import eu.margiel.components.mail.DefineMailPanel;
import eu.margiel.pages.admin.AdminBasePage;

public class DefineRegistrationMailPage extends AdminBasePage {

	public DefineRegistrationMailPage() {
		add(new DefineMailPanel("mail", "speaker"));
	}
}
