package br.com.lepsistemas.patrimonium.service;

import org.springframework.mail.javamail.MimeMessagePreparator;

import br.com.lepsistemas.patrimonium.domain.User;

public interface EmailService {
	
	void sendEmail(MimeMessagePreparator mail);
	
	public void sendPaswordEmail(User user, String password);

}
