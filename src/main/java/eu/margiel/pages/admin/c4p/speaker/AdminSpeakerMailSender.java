package eu.margiel.pages.admin.c4p.speaker;

import org.springframework.stereotype.Component;

import eu.margiel.utils.MailSender;

@Component
public class AdminSpeakerMailSender extends MailSender {

	@Override
	public String getSubjectTemplate() {
		return "Nowy Prelegent: $fullName";
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
