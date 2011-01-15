package eu.margiel.pages.admin.login;

import org.apache.wicket.markup.html.WebPage;

import eu.margiel.JavarsoviaSession;

public class LogoutPage extends WebPage {

	public LogoutPage() {
		JavarsoviaSession.get().invalidate();
	}

}
