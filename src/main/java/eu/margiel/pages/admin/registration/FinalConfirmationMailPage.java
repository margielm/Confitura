package eu.margiel.pages.admin.registration;

import eu.margiel.components.mail.DefineMailPanel;
import eu.margiel.pages.admin.AdminBasePage;

public class FinalConfirmationMailPage extends AdminBasePage {

	public FinalConfirmationMailPage() {
		add(new DefineMailPanel("mail", "finalConfirmation", "$firstName, $lastName, $confirmLink, $cancelLink"));
	}
}
