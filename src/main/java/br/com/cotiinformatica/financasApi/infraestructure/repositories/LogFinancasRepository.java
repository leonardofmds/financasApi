package br.com.cotiinformatica.financasApi.infraestructure.repositories;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.cotiinformatica.financasApi.domain.collections.LogFinancas;

@Repository
public interface LogFinancasRepository extends MongoRepository<LogFinancas, UUID> {
}


