package eu.margiel.pages.confitura.registration;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.wicketstuff.annotation.mount.MountPath;

import eu.margiel.pages.confitura.BaseWebPage;

@MountPath(path = "info")
public class InfoPage extends BaseWebPage {

	private FeedbackPanel feedback = new FeedbackPanel("feedback");

	public InfoPage(String info) {
		add(feedback);
		feedback.info(info);
	}

}
