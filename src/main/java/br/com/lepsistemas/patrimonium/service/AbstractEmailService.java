package br.com.lepsistemas.patrimonium.service;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

import br.com.lepsistemas.patrimonium.domain.User;

public abstract class AbstractEmailService implements EmailService {
	
	@Override
	public void sendPaswordEmail(User user, String password) {
		SimpleMailMessage mail = prepareSimpleMailMessageForNewPassword(user, password);
		sendEmail(mail);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageForNewPassword(User user, String password) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUsername());
		mail.setFrom(from());
		mail.setSubject("Sistema Patrimonium");
		mail.setSentDate(new Date(System.currentTimeMillis()));
		mail.setText(createTextForNewPassword(user, password));
		return mail;
	}
	
	protected abstract String from();
	
	private String createTextForNewPassword(User user, String password) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder
			.append("Olá, ")
			.append(user.getName())
			.append("!\r\n")
			.append("Bem-vindo(a) ao Sistema Patrimonium. Para utilizá-lo, faça utilizando este e-mail com a senha:\r\n\r\n")
			.append(password)
			.append("\r\n\r\n")
			.append("Se você não tem o aplicativo, baixe na sua loja procurando por 'Sistema Patrimonium'.");
		return strBuilder.toString();
	}

}
