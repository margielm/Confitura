package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.google.common.collect.ImmutableMap;

@SuppressWarnings({ "serial", "rawtypes" })
public class LinkOrLabel extends Panel {

	public LinkOrLabel(String id, String label, final Page page) {
		super(id);
		Link link = new Link("link") {
			@Override
			public void onClick() {
				if (isVisible())
					setResponsePage(page);
			}

			@Override
			public boolean isVisible() {
				return true;
				// return page != null;
			}
		};
		initComponents(label, link);
	}

	private void initComponents(String label, Link link) {
		link.add(new Label("link_label", label));
		add(link);
		add(new Label("label", label).setVisible(false));
	}

	public LinkOrLabel(String id, String label, final Class<? extends Page> pageClazz, final Integer itemId) {
		super(id);
		Link link = new Link("link") {
			@Override
			public void onClick() {
				if (isVisible())
					if (itemId != null)
						setResponsePage(pageClazz, new PageParameters(ImmutableMap.of("id", itemId)));
					else
						setResponsePage(pageClazz);
			}

			@Override
			public boolean isVisible() {
				return true;
				// return page != null;
			}
		};
		initComponents(label, link);
	}

}
