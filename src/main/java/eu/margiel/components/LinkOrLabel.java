package eu.margiel.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

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
		link.add(new Label("link_label", label));
		add(link);
		add(new Label("label", label).setVisible(false));
	}

}
