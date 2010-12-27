package eu.margiel.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("M")
public class MainMenu extends MenuItem {

	public MainMenu() {
		super("");
	}

}
