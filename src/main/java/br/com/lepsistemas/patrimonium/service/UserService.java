package br.com.lepsistemas.patrimonium.service;

import java.util.Arrays;
import java.util.HashSet;
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
		UserSecurity authenticated = UserService.authenticated();
		if (!authenticated.hasRole(Role.SUPER) && !authenticated.hasRole(Role.ADMIN)) {
			if (!authenticated.getUsername().equals(username)) {
				throw new AuthorizationException("Not authorized to retrieve user with username: " + username);
			}
		}
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new NotFoundException("User not found with username: " + username);
		}
		return user;
	}
	
	public User findById(String id) {
		UserSecurity authenticated = UserService.authenticated();
		if (!authenticated.hasRole(Role.SUPER) && !authenticated.hasRole(Role.ADMIN)) {
			if (!authenticated.getId().equals(id)) {
				throw new AuthorizationException("Not authorized to retrieve user with id: " + id);
			}
		}
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException("User not found with id: " + id);
		}
		return user;
	}
	
	public User saveSuper(User user) {
		user.setRoles(new HashSet<Role>(Arrays.asList(Role.SUPER, Role.ADMIN, Role.USER)));
		return save(user);
	}
	
	public User saveAdmin(User user) {
		user.setRoles(new HashSet<Role>(Arrays.asList(Role.ADMIN, Role.USER)));
		return save(user);
	}
	
	public User saveUser(User user) {
		user.setRoles(new HashSet<Role>(Arrays.asList(Role.USER)));
		return save(user);
	}
	
	private User save(User user) {
		return userRepository.save(user);
	}
	
	public void delete(String id) {
		if (userRepository.findOne(id) == null) {
			throw new NotFoundException("User not found with id: " + id);
		}
		userRepository.delete(id);
	}

}
