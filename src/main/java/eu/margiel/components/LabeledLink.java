package eu.margiel.components;

import static com.google.common.collect.ImmutableMap.*;
import static eu.margiel.utils.Components.*;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.panel.Panel;

import eu.margiel.components.nogeneric.Link;

@SuppressWarnings("serial")
public class LabeledLink extends Panel {

	public LabeledLink(String id, String label, final Object objectId, final Class<? extends Page> page) {
		super(id);
		Link link = new Link("link") {
			@Override
			public void onClick() {
				setResponsePage(page, new PageParameters(of("id", objectId)));
			}
		};
		add(link.add(label("label", label)));
	}
}
