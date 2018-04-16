package br.com.lepsistemas.patrimonium.resource;

import java.net.URI;
import java.util.List;

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

import br.com.lepsistemas.patrimonium.domain.RealState;
import br.com.lepsistemas.patrimonium.dto.RealStateDTO;
import br.com.lepsistemas.patrimonium.service.RealStateService;

@RestController
@RequestMapping("/realstates")
public class RealStateResource {
	
	@Autowired
	private RealStateService realStateService;
	
	@GetMapping
	public ResponseEntity<List<RealState>> findAll() {
		List<RealState> realStates = realStateService.findAll();
		return ResponseEntity.ok().body(realStates);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		realStateService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody RealStateDTO dto, @PathVariable String id) {
		dto.setId(id);
		realStateService.update(new RealState(dto));
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody RealStateDTO dto) {
		RealState realState = new RealState(dto);
		RealState saved = realStateService.save(realState);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RealState> findById(@PathVariable String id) {
		RealState realState = realStateService.findById(id);
		return ResponseEntity.ok().body(realState);
	}

}
