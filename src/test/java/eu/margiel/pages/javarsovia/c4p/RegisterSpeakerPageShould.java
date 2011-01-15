package eu.margiel.pages.javarsovia.c4p;

import static org.mockito.Mockito.*;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import eu.margiel.domain.MainMenu;
import eu.margiel.repositories.MenuRepository;

public class RegisterSpeakerPageShould {
	private WicketTester tester = new WicketTester();

	@Test
	public void beStarted() {
		MenuRepository menuRepository = mock(MenuRepository.class);
		when(menuRepository.getMainMenu()).thenReturn(new MainMenu());
		tester.startPage(new RegisterSpeakerPage(menuRepository));
	}
}
