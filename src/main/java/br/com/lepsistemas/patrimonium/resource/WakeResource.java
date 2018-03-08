package br.com.lepsistemas.patrimonium.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lepsistemas.patrimonium.dto.WakeDTO;

@RestController
@RequestMapping("/wake")
public class WakeResource {
	
	@GetMapping
	public ResponseEntity<WakeDTO> wake() throws InterruptedException {
		WakeDTO dto = WakeDTO.builder().woke(true).build();
		return ResponseEntity.ok().body(dto);
	}

}
