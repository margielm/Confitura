package eu.margiel.components;

import eu.margiel.components.nogeneric.Link;
import eu.margiel.utils.Components;

@SuppressWarnings("serial")
public abstract class LabeledLink extends Link {

	public LabeledLink(String id, String label) {
		super(id);
		add(Components.label(id + "_label", label));
	}

}
