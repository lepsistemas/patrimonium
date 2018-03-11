package br.com.lepsistemas.patrimonium.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lepsistemas.patrimonium.domain.User;
import br.com.lepsistemas.patrimonium.repository.ArchiveRepository;
import br.com.lepsistemas.patrimonium.repository.UserRepository;

@Service
public class DBDevService {
	
	@Value("${user.super.password}")
	private String password;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArchiveRepository archiveRepository;
	
	@Autowired
	private UserService userService;

	public void instantiateDatabase() throws ParseException {
		
		archiveRepository.deleteAll();
		
		User superUserDB = userRepository.findByUsername("super@gmail.com");
		if (superUserDB == null) {
			User superUser = User
					.builder()
					.username("super@gmail.com")
					.password(encoder.encode(password))
					.name("Super")
					.enabled(true)
					.expire(null)
					.build();
			userService.saveSuper(superUser);
		}
		
		User adminUserDB = userRepository.findByUsername("admin@gmail.com");
		if (adminUserDB == null ) {
			User adminUser = User
					.builder()
					.username("admin@gmail.com")
					.password(encoder.encode(password))
					.name("Admin")
					.enabled(true)
					.expire(null)
					.build();
			userService.saveAdmin(adminUser);
		}
		User normalUserDB = userRepository.findByUsername("user@gmail.com");
		if (normalUserDB == null ) {
			User normalUser = User
					.builder()
					.username("user@gmail.com")
					.password(encoder.encode("user"))
					.name("User")
					.enabled(true)
					.expire(Date.from(LocalDate.now().plusYears(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
					.build();
			userService.saveUser(normalUser);
		}
	}

}
