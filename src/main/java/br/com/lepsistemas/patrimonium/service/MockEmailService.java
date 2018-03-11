package br.com.lepsistemas.patrimonium.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import br.com.lepsistemas.patrimonium.domain.User;

public class MockEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	private static final String SENDER = "sistemapatrimonium@gmail.com";
	
	@Override
	public void sendPaswordEmail(User user, String password) {
		MimeMessagePreparator mail = prepareTextMimeMessage(user, password);
		LOG.info(user.getUsername());
		LOG.info(password);
		sendEmail(mail);
	}

	@Override
	public void sendEmail(MimeMessagePreparator mail) {
		LOG.info(mail.toString());
	}
	
	@Override
	protected String from() {
		return SENDER;
	}
	
	private MimeMessagePreparator prepareTextMimeMessage(User user, String password) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setTo(user.getUsername());
	        messageHelper.setFrom(from());
	        messageHelper.setSubject("Sistema Patrimonium");
	        messageHelper.setText(createTextForNewPassword(user, password));
	    };
	    return messagePreparator;
	}
	
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
