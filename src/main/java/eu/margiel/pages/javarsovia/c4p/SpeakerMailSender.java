package eu.margiel.pages.javarsovia.c4p;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.admin.speaker.AdminSpeakerMailSender;
import eu.margiel.utils.MailSender;

public class SpeakerMailSender extends MailSender {

	AdminSpeakerMailSender adminSender = new AdminSpeakerMailSender();

	@Override
	public String getSubjectTemplate() {
		return "Dziękujemy za rejestrację";
	}

	@Override
	public String getTemplate() {
		return "<html><p>" +
				"Witaj $v.firstName,<br />" +
				"Dziękujemy za zgłoszemnie się jako prelegent na konferencję Confitura 2011.<br />" +
				"Pozdrawiamy!<br /><br />" +
				"Kapituła Confitury 2011" +
				"</p></html>";
	}

	public SpeakerMailSender firstName(String firstName) {
		put("firstName", firstName);
		return this;
	}

	public void sendMessage(Speaker speaker) {
		firstName(speaker.getFirstName()).sendMessage(speaker.getMail());
		adminSender.fullName(speaker.getFullName()).sendMessage();
	}

	public Object get(String name) {
		return properties.get(name);
	}

}
