package eu.margiel.pages.admin;

import org.apache.wicket.Application;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.google.common.collect.ImmutableMap;

import eu.margiel.Confitura;
import eu.margiel.ConfituraBasePage;
import eu.margiel.ConfituraSession;
import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.AbstractEntity;
import eu.margiel.pages.admin.login.LoginPage;

@SuppressWarnings("serial")
public class AdminBasePage extends ConfituraBasePage {
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
				ConfituraSession.get().invalidateNow();
				setResponsePage(LoginPage.class);
			}

		});
		add(wrapper);
		if (ConfituraSession.get().isAdminAvailable() == false)
			setResponsePage(LoginPage.class);
	}

	protected Confitura getApp() {
		return (Confitura) Application.get();
	}

	protected PageParameters paramsWithId(AbstractEntity entity) {
		return new PageParameters(ImmutableMap.of("id", entity.getId()));
	}
}
