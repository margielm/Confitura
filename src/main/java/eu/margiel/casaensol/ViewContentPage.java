package eu.margiel.casaensol;

import org.apache.wicket.markup.html.basic.Label;

import eu.margiel.domain.StaticContent;

public class ViewContentPage extends DynamicContentPage {
	public ViewContentPage(String title, StaticContent staticContent) {
		super(title);
		add(new Label("content", staticContent.getContent()).setEscapeModelStrings(false));
	}
}
