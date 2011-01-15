package eu.margiel.pages.javarsovia.c4p;

import org.apache.wicket.RestartResponseAtInterceptPageException;

import eu.margiel.JavarsoviaSession;
import eu.margiel.pages.javarsovia.BaseWebPage;
import eu.margiel.pages.javarsovia.c4p.login.LoginSpeakerPage;

public class SpeakerBasePage extends BaseWebPage {
	public SpeakerBasePage() {
		if (JavarsoviaSession.get().isSpeakerAvailable() == false)
			throw new RestartResponseAtInterceptPageException(LoginSpeakerPage.class);
	}

}
