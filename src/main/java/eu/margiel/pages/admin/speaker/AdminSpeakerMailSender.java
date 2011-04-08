package eu.margiel.pages.admin.speaker;

import eu.margiel.utils.MailSender;

public class AdminSpeakerMailSender extends MailSender {

	@Override
	public String getSubjectTemplate() {
		return "Nowy Prelegent: $v.fullName";
	}

	@Override
	public String getTemplate() {
		return "";
	}

	public AdminSpeakerMailSender fullName(String fullName) {
		put("fullName", fullName);
		return this;
	}

}
