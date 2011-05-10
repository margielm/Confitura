package eu.margiel.pages.admin.registration;

import eu.margiel.components.mail.DefineMailPanel;
import eu.margiel.pages.admin.AdminBasePage;

public class DefineParticipantMailPage extends AdminBasePage {

	public DefineParticipantMailPage() {
		add(new DefineMailPanel("mail", "participant", "$firstName, $lastName, $link"));
	}
}
