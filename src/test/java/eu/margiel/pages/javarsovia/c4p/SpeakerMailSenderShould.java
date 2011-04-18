package eu.margiel.pages.javarsovia.c4p;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.margiel.domain.MailTemplate;
import eu.margiel.domain.Speaker;
import eu.margiel.domain.User;
import eu.margiel.pages.admin.c4p.speaker.AdminSpeakerMailSender;
import eu.margiel.repositories.MailTemplateRepository;

@RunWith(MockitoJUnitRunner.class)
public class SpeakerMailSenderShould {
	@Mock
	private MailTemplateRepository repository;
	@InjectMocks
	private SpeakerMailSender sender = new SpeakerMailSender();

	@Test
	public void getTemplatePopulatedWithFirstName() {
		when(repository.readByType("speaker")).thenReturn(new MailTemplate().template("Witaj $firstName $lastName"));
		sender.firstName("Michał").lastName("Margiel");

		String message = sender.getContent();

		assertThat(message).isEqualTo("Witaj Michał Margiel");
	}

	@Test
	public void sendEmailToSpeakerAndToAdmin() {
		AdminSpeakerMailSender adminSender = mock(AdminSpeakerMailSender.class);
		when(adminSender.fullName(anyString())).thenReturn(adminSender);
		sender = spy(sender);
		sender.adminSender = adminSender;
		doNothing().when(sender).sendMessage(anyString());
		User speaker = new Speaker().firstName("Michał").lastName("Margiel").mail("m@m.pl");

		sender.sendMessage(speaker);

		verify(sender).sendMessage("m@m.pl");
		assertEquals("Michał", sender.get("firstName"));
		verify(adminSender).sendMessage();
		verify(adminSender).fullName("Michał Margiel");

	}

	@Test
	@Ignore
	public void sendMail() {
		when(repository.readByType("speaker")).thenReturn(
				new MailTemplate().template("Witaj $firstName $lastName").subject(""));

		User speaker = new Speaker().firstName("Michał").lastName("Margiel").mail("michal.margiel@gmail.com");
		sender.sendMessage(speaker);
	}

}
