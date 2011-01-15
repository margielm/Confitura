package eu.margiel.components.menu;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.google.common.collect.ImmutableMap;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.domain.MenuItem;

@SuppressWarnings("serial")
public class MenuVisualLink extends Panel {

	public MenuVisualLink(String id, MenuItem menuItem, MenuLink menuLink) {
		super(id);
		initComponents(menuItem.getName(),
				new LinkToPage("link", menuItem.getLinkItem().getItemId(), menuLink.getPageClass()));
	}

	private void initComponents(String label, Link link) {
		add(link.add(new Label("link_label", label)));
		add(new Label("label", label).setVisible(false));
	}

	private final class LinkToPage extends Link {
		private final Integer itemId;
		private final Class<? extends Page> pageClass;

		private LinkToPage(String id, Integer itemId, Class<? extends Page> pageClass) {
			super(id);
			this.itemId = itemId;
			this.pageClass = pageClass;
		}

		@Override
		public void onClick() {
			if (isVisible()) {
				if (itemId != null) {
					setResponsePage(pageClass, new PageParameters(ImmutableMap.of("id", itemId)));
				} else
					setResponsePage(pageClass);
			}
		}

		@Override
		public boolean isVisible() {
			return true;
		}
	}
}
