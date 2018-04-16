package br.com.lepsistemas.patrimonium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lepsistemas.patrimonium.domain.RealState;
import br.com.lepsistemas.patrimonium.domain.Owner;
import br.com.lepsistemas.patrimonium.dto.RealStateOwnerDTO;
import br.com.lepsistemas.patrimonium.repository.RealStateRepository;
import br.com.lepsistemas.patrimonium.repository.OwnerRepository;
import br.com.lepsistemas.patrimonium.service.exception.NotFoundException;

@Service
public class RealStateService {
	
	@Autowired
	private RealStateRepository realStateRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	public List<RealState> findAll() {
		return realStateRepository.findAll();
	}
	
	public RealState findById(String id) {
		RealState realState = realStateRepository.findOne(id);
		if (realState == null) {
			throw new NotFoundException("Real state not found with id: " + id);
		}
		return realState;
	}
	
	public RealState save(RealState realState) {
		String id = realState.getOwner().getId();
		Owner owner = ownerRepository.findOne(id);
		if (owner == null) {
			throw new NotFoundException("Owner not found with id: " + id);
		}
		realState.setOwner(new RealStateOwnerDTO(owner));
		return realStateRepository.save(realState);
	}

	public RealState update(RealState realState) {
		RealState saved = realStateRepository.findOne(realState.getId());
		if (saved == null) {
			throw new NotFoundException("Real state not found with id: " + realState.getId());
		}
		Owner owner = ownerRepository.findOne(realState.getOwner().getId());
		if (owner == null) {
			throw new NotFoundException("Owner not found with id: " + realState.getOwner().getId());
		}
		saved.setName(realState.getName());
		saved.setRegistration(realState.getRegistration());
		realState.setOwner(new RealStateOwnerDTO(owner));
		return realStateRepository.save(saved);
	}
	
	public void delete(String id) {
		if (realStateRepository.findOne(id) == null) {
			throw new NotFoundException("Real state not found with id: " + id);
		}
		realStateRepository.delete(id);
	}

}
