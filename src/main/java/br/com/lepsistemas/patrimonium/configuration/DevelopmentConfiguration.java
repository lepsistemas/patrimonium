package br.com.lepsistemas.patrimonium.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.lepsistemas.patrimonium.service.EmailService;
import br.com.lepsistemas.patrimonium.service.MockEmailService;

@Configuration
@Profile("dev")
public class DevelopmentConfiguration {
	
	@Bean
	public EmailService emailServvice() {
		return new MockEmailService();
	}

}
