package br.com.lepsistemas.patrimonium.service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lepsistemas.patrimonium.domain.Archive;
import br.com.lepsistemas.patrimonium.repository.ArchiveRepository;
import br.com.lepsistemas.patrimonium.service.exception.NotFoundException;

@Service
public class ArchiveService {
	
	@Autowired
	private ArchiveRepository archiveRepository;
	
	public Archive findById(String id) {
		Archive archive = archiveRepository.findOne(id);
		if (archive == null) {
			throw new NotFoundException("Archive not found with id: " + id);
		}
		return archive;
	}
	
	public void save(Archive archive) throws FileNotFoundException {
		archiveRepository.save(archive);
	}

}
