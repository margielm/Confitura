package eu.margiel.utils;

import static javax.mail.Message.RecipientType.*;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public abstract class MailSender {

	protected static final String MAIN_ADDRESS = "confitura@confitura.pl";
	protected static final String CHARSET = "UTF-8";

	protected VelocityContext ctx = new VelocityContext();

	private Session session;

	public MailSender() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "localhost");
		session = Session.getDefaultInstance(properties);
	}

	public void sendMessage() {
		sendMessage(MAIN_ADDRESS);
	}

	public void sendMessage(String recipient) {
		try {
			Message msg = createMessage(recipient);
			Transport.send(msg, msg.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Message createMessage(String recipient) throws Exception {
		InternetAddress from = new InternetAddress(MAIN_ADDRESS, "Confitura 2011", CHARSET);
		MimeMessage msg = new MimeMessage(session);
		msg.setReplyTo(new Address[] { from });
		msg.setFrom(from);
		msg.setRecipient(TO, new InternetAddress(recipient));
		msg.setContent(getContent(), "text/html; charset=UTF-8");
		msg.setSubject(getSubject(), CHARSET);
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
