package eu.margiel.utils;

import static javax.mail.Message.RecipientType.*;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public abstract class MailSender {
	protected VelocityContext ctx = new VelocityContext();
	private Session session;

	public MailSender() {
		Properties properties = new Properties();
		properties.put("mail.smtps.auth", true);
		session = Session.getInstance(properties, null);
	}

	public void sendMessage() {
		sendMessage("rejestracja@javarsovia.pl");
	}

	public void sendMessage(String recipient) {
		try {
			Message msg = createMessage(recipient);
			Transport t = session.getTransport("smtps");
			t.connect("smtp.gmail.com", "rejestracja@javarsovia.pl", "java4ever");
			t.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Message createMessage(String recipient) throws Exception {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("rejestracja@javarsovia.pl", "Confitura 2011", "UTF-8"));
		msg.setRecipient(TO, new InternetAddress(recipient));
		msg.setContent(getContent(), "text/html; charset=UTF-8");
		msg.setSubject(getSubject());
		return msg;
	}

	public String getContent() {
		return getText(getTemplate());
	}

	public String getText(String template) {
		StringWriter message = new StringWriter();
		try {
			Velocity.evaluate(ctx, message, "message", template);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message.toString();
	}

	public String getSubject() {
		return getText(getSubjectTemplate());
	}

	public abstract String getSubjectTemplate();

	public abstract String getTemplate();

	protected void put(String name, Object value) {
		ctx.put(name, value);
	}

}
