package br.com.lepsistemas.patrimonium.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.lepsistemas.patrimonium.domain.User;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage mail);
	
	public void sendPaswordEmail(User user, String password);

}
