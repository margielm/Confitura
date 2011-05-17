package eu.margiel.components;

import static eu.margiel.utils.PageParametersBuilder.*;

import org.apache.wicket.Page;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.AbstractEntity;

@SuppressWarnings("serial")
public class RedirectLink extends Link {
	private AbstractEntity entity;
	private Class<? extends Page> page;

	public RedirectLink(String id, AbstractEntity entity, Class<? extends Page> page) {
		super(id);
		this.entity = entity;
		this.page = page;
	}

	public RedirectLink(String id, Class<? extends Page> page) {
		super(id);
		this.page = page;
	}

	@Override
	public void onClick() {
		if (entity != null)
			setResponsePage(page, paramsFor("id", entity.getId()));
		else
			setResponsePage(page);
	}
}