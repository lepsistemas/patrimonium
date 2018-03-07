package br.com.lepsistemas.patrimonium.resource;

import java.io.IOException;
import java.net.URI;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lepsistemas.patrimonium.domain.Archive;
import br.com.lepsistemas.patrimonium.dto.ArchiveDTO;
import br.com.lepsistemas.patrimonium.service.ArchiveService;

@RestController
@RequestMapping("/archives")
public class ArchiveResource {
	
	@Autowired
	private ArchiveService archiveService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ArchiveDTO> findById(@PathVariable String id) {
		Archive archive = archiveService.findById(id);
		return ResponseEntity.ok().body(new ArchiveDTO(archive));
	}
	
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Void> save(@RequestParam("file") MultipartFile multipart) throws IOException {
		Binary binary = new Binary(BsonBinarySubType.BINARY, multipart.getBytes());
		Archive archive = Archive
				.builder()
				.name(multipart.getName())
				.contentType(multipart.getContentType())
				.file(binary)
				.build();
		archiveService.save(archive);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(archive.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
