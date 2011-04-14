package eu.margiel.utils;

import org.apache.wicket.PageParameters;

public class PageParametersBuilder {
	private PageParameters params = new PageParameters();

	public static PageParameters paramsFor(String key, Object value) {
		new PageParametersBuilder().put(key, value);
		return new PageParametersBuilder().put(key, value).params;
	}

	private PageParametersBuilder put(String key, Object value) {
		params.put(key, value);
		return this;
	}
}
