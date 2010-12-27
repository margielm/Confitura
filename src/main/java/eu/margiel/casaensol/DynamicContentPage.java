package eu.margiel.casaensol;

import org.apache.wicket.markup.html.basic.Label;

public class DynamicContentPage extends BaseWebPage {
	public DynamicContentPage(String title) {
		add(new Label("title", title));
	}

}
