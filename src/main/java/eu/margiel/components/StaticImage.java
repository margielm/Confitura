package eu.margiel.components;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;

@SuppressWarnings("serial")
public class StaticImage extends WebComponent {

	private final String path;
	private String alt;

	public StaticImage(String id, String path) {
		super(id);
		this.path = path;
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		checkComponentTag(tag, "img");
		tag.put("src", path);
		if (alt != null)
			tag.put("alt", alt);
	}

	public StaticImage alt(String alt) {
		this.alt = alt;
		return this;
	}

}