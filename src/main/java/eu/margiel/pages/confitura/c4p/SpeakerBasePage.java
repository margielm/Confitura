package eu.margiel.pages.confitura.c4p;

import org.apache.wicket.RestartResponseAtInterceptPageException;

import eu.margiel.ConfituraSession;
import eu.margiel.pages.confitura.BaseWebPage;
import eu.margiel.pages.confitura.c4p.login.LoginSpeakerPage;

public class SpeakerBasePage extends BaseWebPage {
	public SpeakerBasePage() {
		if (ConfituraSession.get().isSpeakerAvailable() == false)
			throw new RestartResponseAtInterceptPageException(LoginSpeakerPage.class);
	}

}
