package eu.margiel.pages.admin;

import org.apache.wicket.Application;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.google.common.collect.ImmutableMap;

import eu.margiel.Javarsovia;
import eu.margiel.JavarsoviaBasePage;
import eu.margiel.JavarsoviaSession;
import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.AbstractEntity;
import eu.margiel.pages.admin.login.LoginPage;

@SuppressWarnings("serial")
public class AdminBasePage extends JavarsoviaBasePage {
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
		if (JavarsoviaSession.get().isAdminAvailable() == false)
			setResponsePage(LoginPage.class);
	}

	protected Javarsovia getApp() {
		return (Javarsovia) Application.get();
	}

	protected PageParameters paramsWithId(AbstractEntity entity) {
		return new PageParameters(ImmutableMap.of("id", entity.getId()));
	}
}
