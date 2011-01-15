package eu.margiel.pages.javarsovia;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class TestPageShould {
	private WicketTester tester = new WicketTester();

	@Test
	public void beStarted() {
		tester.startPage(TestPage.class);
	}
}
