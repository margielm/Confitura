package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;

import com.google.common.collect.ImmutableMap;

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
			setResponsePage(page, new PageParameters(ImmutableMap.of("id", entity.getId())));
		else
			setResponsePage(page);
	}
}