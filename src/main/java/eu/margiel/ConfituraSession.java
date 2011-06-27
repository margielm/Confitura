package eu.margiel;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import eu.margiel.domain.Admin;
import eu.margiel.domain.Speaker;
import eu.margiel.domain.User;

@SuppressWarnings("serial")
public class ConfituraSession extends WebSession {

	private User user;

	public ConfituraSession(Request request) {
		super(request);
	}

	public static ConfituraSession get() {
		return (ConfituraSession) Session.get();
	}

	public <T extends User> T setUser(T user) {
		this.user = user;
		return user;
	}

	public boolean isAdminAvailable() {
		return user != null && user instanceof Admin;
	}

	public boolean isSpeakerAvailable() {
		return user != null && user instanceof Speaker;
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T getUser() {
		return (T) this.user;
	}

	public Admin getAdmin() {
		return (Admin) this.user;
	}

	public Speaker getSpeaker() {
		return (Speaker) this.user;
	}

}
