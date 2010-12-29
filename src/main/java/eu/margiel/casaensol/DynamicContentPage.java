package eu.margiel.casaensol;

import org.apache.wicket.markup.html.basic.Label;

import eu.margiel.pages.javarsovia.BaseWebPage;

public class DynamicContentPage extends BaseWebPage {
	public DynamicContentPage(String title) {
		add(new Label("title", title));
	}

}
