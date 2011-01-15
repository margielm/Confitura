package eu.margiel.components.user;

import static eu.margiel.utils.Components.*;

import org.apache.wicket.markup.html.panel.Panel;

import eu.margiel.domain.Speaker;

@SuppressWarnings("serial")
public class UserInfoPanel extends Panel {

	public UserInfoPanel(String id, Speaker speaker) {
		super(id);
		add(label("firstName", speaker.getFirstName()));
		add(label("lastName", speaker.getLastName()));
		add(label("eMail", speaker.getMail()));
		add(label("webPage", speaker.getWebPage()));
		add(label("twitter", speaker.getTwitter()));
		add(richLabel("bio", speaker.getBio()));
	}

}
