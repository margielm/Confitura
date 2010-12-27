package eu.margiel.admin;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

import eu.margiel.CasaensolApplication;

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

	protected CasaensolApplication getCasaensolApp() {
		return (CasaensolApplication) Application.get();
	}
}
