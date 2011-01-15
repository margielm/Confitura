package eu.margiel;

import org.apache.wicket.markup.html.WebPage;

public class JavarsoviaBasePage extends WebPage {

	@Override
	public JavarsoviaSession getSession() {
		return JavarsoviaSession.get();
	}

}
