package eu.margiel.pages.admin.speaker;

import static org.fest.assertions.Assertions.*;

import org.junit.Test;

public class AdminSpeakerMailSenderShould {

	@Test
	public void shouldIncludeFullNameInMessageSubject() {
		AdminSpeakerMailSender sender = new AdminSpeakerMailSender().fullName("Michał Margiel");

		String subject = sender.getSubject();

		assertThat(subject)
				.isEqualTo("Nowy Prelegent: Michał Margiel");
	}
}
