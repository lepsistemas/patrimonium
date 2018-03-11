package br.com.lepsistemas.patrimonium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

	private TemplateEngine templateEngine;

	@Autowired
	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String build(String name, String password) {
		Context context = new Context();
		context.setVariable("name", name);
		context.setVariable("password", password);
		return templateEngine.process("passwordTemplate", context);
	}

}
