package eu.margiel.components.menu;

import static com.google.common.collect.Lists.*;

import java.util.List;

import org.apache.wicket.Page;
import org.synyx.hades.dao.GenericDao;

import eu.margiel.domain.MenuLinkItem;
import eu.margiel.domain.WithTitle;

public class DynamicMenuLink<ENTITY extends WithTitle> extends MenuLink {
	private GenericDao<ENTITY, ?> repository;

	public DynamicMenuLink(GenericDao<ENTITY, ?> repository, Class<? extends Page> pageClazz) {
		super(pageClazz);
		this.repository = repository;
	}

	@Override
	public List<MenuLinkItem> getAllItems() {
		List<MenuLinkItem> result = newArrayList();
		for (ENTITY entity : repository.readAll())
			result.add(new MenuLinkItem(entity.getId(), entity.getTitle()));
		return result;
	}

}
