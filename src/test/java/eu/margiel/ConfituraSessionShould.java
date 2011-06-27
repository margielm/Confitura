package eu.margiel;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.apache.wicket.Request;
import org.junit.Before;
import org.junit.Test;

import eu.margiel.domain.Admin;
import eu.margiel.domain.Speaker;

public class ConfituraSessionShould {
	private Request request = mock(Request.class);
	private ConfituraSession session;

	@Before
	public void setupLocale() {
		when(request.getLocale()).thenReturn(new Locale("pl"));
		session = new ConfituraSession(request);
	}

	@Test
	public void findLoggedInAdmin() {
		session.setUser(new Admin());

		assertThat(session.isAdminAvailable()).isTrue();
		assertThat(session.isSpeakerAvailable()).isFalse();
	}

	@Test
	public void findLoggedInUser() {
		session.setUser(new Speaker());

		assertThat(session.isSpeakerAvailable()).isTrue();
		assertThat(session.isAdminAvailable()).isFalse();
	}
}
