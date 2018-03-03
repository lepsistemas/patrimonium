package br.com.lepsistemas.patrimonium.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lepsistemas.patrimonium.domain.Role;
import br.com.lepsistemas.patrimonium.domain.User;
import br.com.lepsistemas.patrimonium.repository.UserRepository;

@Service
public class DBDevService {
	
	@Value("${user.super.password}")
	private String password;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;

	public void instantiateDatabase() throws ParseException {
		
		userRepository.deleteAll();
		
		User superUser = User
				.builder()
				.username("super@gmail.com")
				.password(encoder.encode(password))
				.name("Super")
				.enabled(true)
				.expire(null)
				.roles(new HashSet<>(Arrays.asList(Role.SUPER, Role.ADMIN, Role.USER)))
				.build();
		
		userRepository.save(superUser);
	}

}
