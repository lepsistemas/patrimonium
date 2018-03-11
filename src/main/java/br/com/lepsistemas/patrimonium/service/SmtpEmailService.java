package br.com.lepsistemas.patrimonium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import br.com.lepsistemas.patrimonium.domain.User;

public class SmtpEmailService extends AbstractEmailService {
	
	@Autowired
	MailContentBuilder mailContentBuilder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendPaswordEmail(User user, String password) {
		MimeMessagePreparator mail = prepareHtmlMimeMessage(user, password);
		sendEmail(mail);
	}

	@Override
	public void sendEmail(MimeMessagePreparator mail) {
		mailSender.send(mail);
	}
	
	@Override
	protected String from() {
		return UserService.authenticated().getUsername();
	}
	
	private MimeMessagePreparator prepareHtmlMimeMessage(User user, String password) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setTo(user.getUsername());
	        messageHelper.setFrom(from());
	        messageHelper.setSubject("Sistema Patrimonium");
	        String content = mailContentBuilder.build(user.getName(), password);
	        messageHelper.setText(content, true);
	    };
	    return messagePreparator;
	}

}
