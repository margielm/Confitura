package eu.margiel.pages.javarsovia.c4p;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Ignore;
import org.junit.Test;

import eu.margiel.domain.Speaker;
import eu.margiel.pages.admin.speaker.AdminSpeakerMailSender;

public class SpeakerMailSenderShould {
	SpeakerMailSender sender = new SpeakerMailSender();

	@Test
	public void getTemplatePopulatedWithFirstName() {
		sender.firstName("Michał");

		String message = sender.getContent();

		assertThat(message)
					.isEqualTo("<html><p>" +
							"Witaj Michał,<br />" +
							"Dziękujemy za zgłoszemnie się jako prelegent na konferencję Confitura 2011.<br />" +
							"Pozdrawiamy!<br /><br />" +
							"Kapituła Confitury 2011" +
							"</p></html>");
	}

	@Test
	public void sendEmailToSpeakerAndToAdmin() {
		AdminSpeakerMailSender adminSender = mock(AdminSpeakerMailSender.class);
		when(adminSender.fullName(anyString())).thenReturn(adminSender);
		sender = spy(sender);
		sender.adminSender = adminSender;
		doNothing().when(sender).sendMessage(anyString());
		Speaker speaker = new Speaker().firstName("Michał").lastName("Margiel").mail("m@m.pl");

		sender.sendMessage(speaker);

		verify(sender).sendMessage("m@m.pl");
		assertEquals("Michał", sender.get("firstName"));
		verify(adminSender).sendMessage();
		verify(adminSender).fullName("Michał Margiel");

	}

	@Test
	@Ignore
	public void sendMail() {
		Speaker speaker = new Speaker().firstName("Michał").lastName("Margiel").mail("michal.margiel@gmail.com");
		sender.sendMessage(speaker);
	}

}
