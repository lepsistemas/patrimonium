package br.com.lepsistemas.patrimonium.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lepsistemas.patrimonium.domain.User;
import br.com.lepsistemas.patrimonium.dto.RegisterDTO;
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
	
	@PreAuthorize("hasAnyRole('SUPER', 'ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO dto, @PathVariable String id) {
		dto.setId(id);
		userService.update(new User(dto));
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('SUPER', 'ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody RegisterDTO dto) {
		User user = new User(dto);
		User saved = userService.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).build();
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
