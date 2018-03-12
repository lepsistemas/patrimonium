package br.com.lepsistemas.patrimonium.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	private static final Locale LOCALE_BR = new Locale("pt", "BR");

	@Bean
	public MessageSource messageSource() {
	    Locale.setDefault(LOCALE_BR);
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.addBasenames("classpath:org/springframework/security/messages");
	    return messageSource;
	}

}
