package br.com.lepsistemas.patrimonium.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.lepsistemas.patrimonium.domain.Archive;

@Repository
public interface ArchiveRepository extends MongoRepository<Archive, String> {

}
