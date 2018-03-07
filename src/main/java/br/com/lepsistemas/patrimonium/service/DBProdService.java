package br.com.lepsistemas.patrimonium.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lepsistemas.patrimonium.domain.User;
import br.com.lepsistemas.patrimonium.repository.UserRepository;

@Service
public class DBProdService {
	
	@Value("${user.super.password}")
	private String password;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	public void instantiateDatabase() throws ParseException {
		
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
	}

}
