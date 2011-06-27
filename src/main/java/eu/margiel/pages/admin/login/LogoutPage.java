package eu.margiel.pages.admin.login;

import org.apache.wicket.markup.html.WebPage;

import eu.margiel.ConfituraSession;

public class LogoutPage extends WebPage {

	public LogoutPage() {
		ConfituraSession.get().invalidate();
	}

}
