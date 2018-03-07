package br.com.lepsistemas.patrimonium.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lepsistemas.patrimonium.domain.User;
import br.com.lepsistemas.patrimonium.dto.UserDTO;
import br.com.lepsistemas.patrimonium.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAnyRole('SUPER', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = userService.findAll();
		List<UserDTO> dtos = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	@PreAuthorize("hasAnyRole('SUPER', 'ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@GetMapping("/username")
	public ResponseEntity<UserDTO> findByUsername(@RequestParam("value") String value) {
		User user = userService.findByUsername(value);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

}
