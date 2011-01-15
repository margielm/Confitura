package eu.margiel.pages.admin;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

import eu.margiel.Javarsovia;
import eu.margiel.JavarsoviaSession;
import eu.margiel.components.nogeneric.Link;
import eu.margiel.pages.admin.login.LoginPage;

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
		add(new Link("logout") {

			@Override
			public void onClick() {
				JavarsoviaSession.get().invalidateNow();
				setResponsePage(LoginPage.class);
			}

		});
		add(wrapper);
		if (JavarsoviaSession.get().isUserAvailable() == false)
			setResponsePage(LoginPage.class);
	}

	protected Javarsovia getApp() {
		return (Javarsovia) Application.get();
	}
}
