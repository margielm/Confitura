package eu.margiel;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import eu.margiel.domain.Admin;
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
