package eu.margiel.pages.javarsovia.c4p.login;

import org.springframework.stereotype.Component;

import eu.margiel.utils.MailSender;

@Component
public class RequestPasswordResetMailSender extends MailSender {

	public RequestPasswordResetMailSender token(String token) {
		put("token", token);
		return this;
	}

	@Override
	public String getSubjectTemplate() {
		return "Reset hasła na confitura.pl";
	}

	@Override
	public String getTemplate() {
		return "Witaj! <br />"
				+ "Zostało zgłoszone rządanie zresetowania hasła dla Twojego konta na conftura.pl. <br />"
				+ "Aby wprowadzić nowe hasło kliknij poniższy link. Jeśli nie chcesz zresetować swojego hasła zignoruj tą wiadomość<br /><br />"
				+ "<a href=\"http://www.confitura.pl/c4p/password/reset/$token\">RESET</a><br/>"
				+ "Pozdrawiam<br />"
				+ "System Konferencyjny Confitura";
	}
}
