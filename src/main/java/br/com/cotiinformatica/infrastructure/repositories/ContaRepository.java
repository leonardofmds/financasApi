package br.com.cotiinformatica.infrastructure.repositories;

import br.com.cotiinformatica.domain.models.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
}
