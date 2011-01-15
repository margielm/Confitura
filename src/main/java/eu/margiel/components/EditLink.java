package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;

import com.google.common.collect.ImmutableMap;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.AbstractEntity;

@SuppressWarnings("serial")
public class EditLink extends Link {
	private final AbstractEntity entity;
	private final Class<? extends Page> page;

	public EditLink(AbstractEntity entity, Class<? extends Page> page) {
		super("edit");
		this.entity = entity;
		this.page = page;
	}

	@Override
	public void onClick() {
		setResponsePage(page, new PageParameters(ImmutableMap.of("id", entity.getId())));
	}
}