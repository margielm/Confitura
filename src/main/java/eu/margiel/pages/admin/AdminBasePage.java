package eu.margiel.pages.admin;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

import eu.margiel.Javarsovia;

@SuppressWarnings("serial")
public class AdminBasePage extends WebPage {
	private WebMarkupContainer wrapper;

	public AdminBasePage() {
		wrapper = new WebMarkupContainer("wrapper") {
			@Override
			public boolean isTransparentResolver() {
				return true;
			}
		};
		wrapper.setOutputMarkupId(true);
		add(wrapper);
	}

	protected Javarsovia getCasaensolApp() {
		return (Javarsovia) Application.get();
	}
}
