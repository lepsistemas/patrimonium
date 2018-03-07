package br.com.lepsistemas.patrimonium.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/wake")
public class WakeResource {
	
	@GetMapping
	public String wake() {
		return "woke";
	}

}
