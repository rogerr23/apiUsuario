package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.collections.LogMensageria;

@Repository
public interface LogMensageriaRepository extends MongoRepository<LogMensageria, UUID> {

	
	
	
	
	
	
}
