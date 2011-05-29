package eu.margiel.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@SuppressWarnings("serial")
@Entity
public class Registration extends AbstractEntity {
	private boolean active;
	@Lob
	private String info;
	@Lob
	private String widgetInfo;

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isClosed() {
		return active == false;
	}

	public String getInfo() {
		return info;
	}

	public String getWidgetInfo() {
		return widgetInfo;
	}
}
