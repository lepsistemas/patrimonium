package br.com.lepsistemas.patrimonium.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	private static final String SENDER = "sistemapatrimonium@gmail.com";

	@Override
	public void sendEmail(SimpleMailMessage mail) {
		LOG.info("Simulando envio de e-mail...");
		LOG.info(mail.toString());
		LOG.info("E-mail enviado...");
	}
	
	@Override
	protected String from() {
		return SENDER;
	}

}
