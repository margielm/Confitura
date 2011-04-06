package eu.margiel.utils;

import static javax.mail.Message.RecipientType.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private Session session;

	public MailSender() {
		Properties properties = new Properties();
		properties.put("mail.smtps.auth", true);
		session = Session.getInstance(properties, null);
	}

	public void sendMessage(String recipient, String subject) {
		try {
			Message msg = createMessage(recipient, subject);
			Transport t = session.getTransport("smtps");
			t.connect("smtp.gmail.com", "rejestracja@javarsovia.pl", "java4ever");
			t.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Message createMessage(String recipient, String subject) throws MessagingException, AddressException {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("konferencja@javarsovia.pl"));
		msg.setRecipient(TO, new InternetAddress(recipient));
		msg.setSubject(subject);
		msg.setText("to jest moj mail");
		return msg;
	}
}
