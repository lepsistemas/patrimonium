package br.com.lepsistemas.patrimonium.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lepsistemas.patrimonium.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	@Transactional
	User findByUsername(String username);

}
