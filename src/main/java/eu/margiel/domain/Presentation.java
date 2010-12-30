package eu.margiel.domain;

import javax.persistence.Entity;

@SuppressWarnings({ "serial", "unused" })
@Entity
public class Presentation extends AbstractEntity {
	private String title;
	private String description;
}
