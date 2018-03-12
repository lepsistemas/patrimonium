package br.com.lepsistemas.patrimonium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lepsistemas.patrimonium.domain.Owner;
import br.com.lepsistemas.patrimonium.repository.OwnerRepository;
import br.com.lepsistemas.patrimonium.service.exception.NotFoundException;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	public List<Owner> findAll() {
		return ownerRepository.findAll();
	}
	
	public Owner findById(String id) {
		Owner owner = ownerRepository.findOne(id);
		if (owner == null) {
			throw new NotFoundException("Owner not found with id: " + id);
		}
		return owner;
	}
	
	public void delete(String id) {
		if (ownerRepository.findOne(id) == null) {
			throw new NotFoundException("Owner not found with id: " + id);
		}
		ownerRepository.delete(id);
	}
	
	public Owner update(Owner owner) {
		Owner saved = ownerRepository.findOne(owner.getId());
		if (saved == null) {
			throw new NotFoundException("Owner not found with id: " + owner.getId());
		}
		saved.setName(owner.getName());
		saved.setDocument(owner.getDocument());
		return ownerRepository.save(saved);
	}

	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}

}
