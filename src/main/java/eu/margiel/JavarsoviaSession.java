package eu.margiel;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import eu.margiel.domain.Speaker;
import eu.margiel.domain.User;

@SuppressWarnings("serial")
public class JavarsoviaSession extends WebSession {

	private User user;

	public JavarsoviaSession(Request request) {
		super(request);
	}

	public static JavarsoviaSession get() {
		return (JavarsoviaSession) Session.get();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserAvailable() {
		return user != null;
	}

	public boolean isSpeakerAvailable() {
		return isUserAvailable() && user instanceof Speaker;
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T getUser() {
		return (T) this.user;
	}

	public Speaker getSpeaker() {
		return (Speaker) this.user;
	}

}
