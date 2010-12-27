package eu.margiel.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("H")
public class HorizontalMenu extends MenuItem {

	public HorizontalMenu() {
		super("");
	}

}
