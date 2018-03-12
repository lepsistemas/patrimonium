package br.com.lepsistemas.patrimonium.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lepsistemas.patrimonium.domain.Owner;
import br.com.lepsistemas.patrimonium.dto.OwnerDTO;
import br.com.lepsistemas.patrimonium.service.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerResource {
	
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping
	public ResponseEntity<List<OwnerDTO>> findAll() {
		List<Owner> owners = ownerService.findAll();
		List<OwnerDTO> dtos = owners.stream().map(owner -> new OwnerDTO(owner)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		ownerService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody OwnerDTO dto, @PathVariable String id) {
		dto.setId(id);
		ownerService.update(new Owner(dto));
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody OwnerDTO dto) {
		Owner owner = new Owner(dto);
		Owner saved = ownerService.save(owner);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OwnerDTO> findById(@PathVariable String id) {
		Owner owner = ownerService.findById(id);
		return ResponseEntity.ok().body(new OwnerDTO(owner));
	}

}
