package br.com.lepsistemas.patrimonium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.lepsistemas.patrimonium.domain.Role;
import br.com.lepsistemas.patrimonium.domain.User;
import br.com.lepsistemas.patrimonium.repository.UserRepository;
import br.com.lepsistemas.patrimonium.security.UserSecurity;
import br.com.lepsistemas.patrimonium.service.exception.AuthorizationException;
import br.com.lepsistemas.patrimonium.service.exception.NotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<User> findAll() {
		UserSecurity user = UserService.authenticated();
		if (user == null || (!user.hasRole(Role.SUPER) && !user.hasRole(Role.ADMIN))) {
			throw new AuthorizationException("Acesso negado");
		}
		return userRepository.findAll();
	}
	
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new NotFoundException("User not found with username: " + username);
		}
		return user;
	}
	
	public User findById(String id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException("User not found with id: " + id);
		}
		return user;
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void delete(String id) {
		if (userRepository.findOne(id) == null) {
			throw new NotFoundException("User not found with id: " + id);
		}
		userRepository.delete(id);
	}

}
