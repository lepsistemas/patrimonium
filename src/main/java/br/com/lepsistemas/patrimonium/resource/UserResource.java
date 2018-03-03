package br.com.lepsistemas.patrimonium.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lepsistemas.patrimonium.domain.User;
import br.com.lepsistemas.patrimonium.dto.UserDTO;
import br.com.lepsistemas.patrimonium.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = userService.findAll();
		List<UserDTO> dtos = users.stream().map(
				user -> UserDTO.builder()
					.id(user.getId())
					.username(user.getUsername())
					.name(user.getName())
					.enabled(user.isEnabled())
					.expire(user.getExpire())
					.roles(user.getRoles())
					.build()
				).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}

}
