package br.com.lepsistemas.patrimonium.configuration;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.lepsistemas.patrimonium.service.DBProdService;

@Component
@Profile("prod")
public class SpringContextProductionListener implements ApplicationListener<ContextRefreshedEvent> {
	
	private final Logger log = LoggerFactory.getLogger(SpringContextProductionListener.class);
	
	@Autowired
	private DBProdService db;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			db.instantiateDatabase();
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
	}

}
