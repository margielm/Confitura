package eu.margiel.pages.confitura;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Ignore;
import org.junit.Test;

import eu.margiel.pages.confitura.TestPage;

@Ignore
public class TestPageShould {
	private WicketTester tester = new WicketTester();

	@Test
	public void beStarted() {
		tester.startPage(TestPage.class);
	}
}
