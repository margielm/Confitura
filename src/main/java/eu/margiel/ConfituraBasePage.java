package eu.margiel;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import com.google.common.collect.ImmutableMap;

public class ConfituraBasePage extends WebPage {

	@Override
	public ConfituraSession getSession() {
		return ConfituraSession.get();
	}

	protected void setResponse(Class<? extends Page> page, Object id) {
		setResponsePage(page, new PageParameters(ImmutableMap.of("id", id)));
	}

}
