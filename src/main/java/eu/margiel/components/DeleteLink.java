package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.synyx.hades.dao.GenericDao;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.AbstractEntity;

@SuppressWarnings("serial")
public class DeleteLink extends Link {
	private final AbstractEntity entity;
	private final GenericDao<AbstractEntity, Integer> repository;
	private final Class<? extends Page> page;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends GenericDao> DeleteLink(AbstractEntity entity, T repository, Class<? extends Page> page) {
		super("delete");
		this.entity = entity;
		this.repository = repository;
		this.page = page;
		add(new SimpleAttributeModifier("onclick", "return confirm('Na pewno usunąć?');"));
	}

	@Override
	public void onClick() {
		repository.delete(entity);
		setResponsePage(page);
	}
}